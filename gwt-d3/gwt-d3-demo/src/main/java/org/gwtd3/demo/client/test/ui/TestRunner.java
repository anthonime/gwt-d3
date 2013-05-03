/**
 * 
 */
package org.gwtd3.demo.client.test.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.gwtd3.demo.client.test.TestCase;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.ComplexPanel;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class TestRunner implements RunUiHandlers {

    private final List<TestCase> tests = new ArrayList<TestCase>();
    private final ComplexPanel sandbox;
    private final TestSessionContainer container;

    public TestRunner(final TestSessionContainer container) {
        super();
        this.container = container;
        container.setUiHandlers(this);
        sandbox = createSandbox();
    }

    public <T extends TestCase> void setTests(final Collection<T> tests) {
        this.tests.addAll(tests);
        int i = 0;
        for (TestCase test : tests) {
            prepareWidget(test);
            resetWidget(i);
            i++;
        }
    }

    /**
     * @param test
     */
    private void prepareWidget(final TestCase test) {
        UnitTestWidget widget = new UnitTestWidget();
        widget.setTestName(getName(test));
        container.addUnitTestWidget(widget);
    }

    /**
     * @param widget
     */
    private void resetWidget(final int i) {
        container.setTestExecution(i, new TestExecution(TestPhase.WAITING, null, 0, 0));
    }

    /**
     * @return
     */
    private ComplexPanel createSandbox() {
        return container.getSandbox();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gwtd3.demo.client.tests.RunUiHandlers#start()
     */
    @Override
    public void start() {
        stopped = false;
        firstErrorTest = -1;
        if (tests.size() == 0) {
            return;
        }
        for (int i = 1; i < tests.size(); i++) {
            resetWidget(i);
        }
        totalStartTime = new Date().getTime();
        run(0);

    }

    private long totalStartTime = 0;
    private long testStartTime = 0;
    private long phaseStartTime = 0;
    private boolean stopped = false;
    private int firstErrorTest = -1;

    /**
     * @return
     */
    private long testElapsedTime() {
        return new Date().getTime() - testStartTime;
    }

    /**
     * @return
     */
    private long phaseElapsedTime() {
        long elapsed = new Date().getTime() - phaseStartTime;
        phaseStartTime = new Date().getTime();
        return elapsed;
    }

    /**
     * @param i
     */
    private void run(final int i) {
        if (i >= tests.size() || stopped) {
            finishSuite();
            return;
        }
        TestCase test = tests.get(i);
        testStartTime = new Date().getTime();
        phaseStartTime = testStartTime;
        doSetUp(test, i);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gwtd3.demo.client.tests.RunUiHandlers#stop()
     */
    @Override
    public void stop() {
        stopped = true;
    }

    /**
     * @param test
     * @return
     */
    private String getName(final TestCase test) {
        int lastIndexOf = test.getClass().getName().lastIndexOf(".");
        if (lastIndexOf >= 0) {
            return test.getClass().getName().substring(lastIndexOf + 1);
        }
        else {
            return test.getClass().getName();
        }
    }

    private void doSetUp(final TestCase test, final int i) {
        container.setTestExecution(i, new TestExecution(TestPhase.SETTING_UP, null, phaseElapsedTime(),
                testElapsedTime()));
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                try {
                    test.setUp(sandbox);
                    doTest(test, i);
                } catch (Throwable t) {
                    handleThrowable(i, test, t, TestPhase.SETTING_UP);
                }
            }
        });
    }

    /**
     * @param test
     */
    private void doTest(final TestCase test, final int i) {
        container
                .setTestExecution(i, new TestExecution(TestPhase.RUNNING, null, phaseElapsedTime(), testElapsedTime()));

        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                try {
                    test.doTest(sandbox);
                    doTearDown(test, i);
                } catch (Throwable t) {
                    handleThrowable(i, test, t, TestPhase.RUNNING);
                }

            }
        });
    }

    private void doTearDown(final TestCase test, final int i) {
        container.setTestExecution(i, new TestExecution(TestPhase.TEARING_DOWN, null, phaseElapsedTime(),
                testElapsedTime()));
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                try {
                    test.tearDown(sandbox);
                    doFinish(test, i);
                } catch (Throwable t) {
                    handleThrowable(i, test, t, TestPhase.RUNNING);
                }

            }

        });
    }

    private void doFinish(final TestCase test, final int i) {
        container.setTestExecution(i, new TestExecution(TestPhase.FINISHED, TestResult.createSuccess(),
                phaseElapsedTime(), testElapsedTime()));
        run(i + 1);
    }

    private void handleThrowable(final int i, final TestCase test, final Throwable t, final TestPhase endingPhase) {
        if (firstErrorTest == -1) {
            firstErrorTest = i;
        }
        if (t instanceof AssertionError) {
            container.setTestExecution(i, new TestExecution(TestPhase.FINISHED, new TestResult(endingPhase,
                    TestResultType.FAILURE, t), phaseElapsedTime(),
                    testElapsedTime()));

        }
        else if (t instanceof Throwable) {
            container.setTestExecution(i, new TestExecution(TestPhase.FINISHED, new TestResult(endingPhase,
                    TestResultType.ERROR, t), phaseElapsedTime(),
                    testElapsedTime()));
        }
        run(i + 1);
    }

    private void finishSuite() {
        if (firstErrorTest != -1) {
            container.openDetails(firstErrorTest);
        }
    }
}