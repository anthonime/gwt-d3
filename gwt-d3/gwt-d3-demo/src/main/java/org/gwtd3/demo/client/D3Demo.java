package org.gwtd3.demo.client;

import org.gwtd3.api.D3;
import org.gwtd3.demo.client.democases.ArcTween;
import org.gwtd3.demo.client.democases.AxisComponent;
import org.gwtd3.demo.client.democases.BarChart;
import org.gwtd3.demo.client.democases.ChordDiagram;
import org.gwtd3.demo.client.democases.FocusAndContext;
import org.gwtd3.demo.client.democases.GeneralUpdatePattern1;
import org.gwtd3.demo.client.democases.GeneralUpdatePattern2;
import org.gwtd3.demo.client.democases.GeneralUpdatePattern3;
import org.gwtd3.demo.client.democases.StupidExample;
import org.gwtd3.demo.client.democases.StupidExample2;
import org.gwtd3.demo.client.democases.arcs.ArcDemo;
import org.gwtd3.demo.client.democases.behaviors.DragMultiples;
import org.gwtd3.demo.client.test.ui.TestRunner;
import org.gwtd3.demo.client.test.ui.TestSessionContainer;
import org.gwtd3.demo.client.testcases.D3TestSuite;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.debug.client.DebugInfo;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class D3Demo implements EntryPoint {

    public static final String DEMO_CONTAINER_ID = "demoContainer";
    private ComplexPanel demoContainer;
    private DemoCase currentDemo;
    private TestSessionContainer testContainer;

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        DebugInfo.setDebugIdPrefix("");
        GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void onUncaughtException(final Throwable e) {
                GWT.log("Uncaught error", e);
                Window.alert("Error. Go to see the logs");
            }
        });

        DockLayoutPanel container = new DockLayoutPanel(Unit.PX);
        container.setSize("100%", "100%");

        container.addNorth(new Label("GWT-D3 : A thin GWT wrapper around D3.", false), 20);
        container.addNorth(new Label("D3 API version: " + D3.version(), false), 20);

        FlowPanel p = new FlowPanel();
        ComplexPanel buttonContainer = new VerticalPanel();
        buttonContainer.add(new TestButton());
        buttonContainer.add(new DemoButton("Arc", ArcDemo.factory()));
        buttonContainer.add(new DemoButton("Stupid example", StupidExample.factory()));
        buttonContainer.add(new DemoButton("Stupid example 2", StupidExample2.factory()));
        buttonContainer.add(new DemoButton("General Update Pattern I", GeneralUpdatePattern1.factory()));
        buttonContainer.add(new DemoButton("General Update Pattern II", GeneralUpdatePattern2.factory()));
        buttonContainer.add(new DemoButton("General Update Pattern III", GeneralUpdatePattern3.factory()));
        buttonContainer.add(new DemoButton("Arc Tween", ArcTween.factory()));
        buttonContainer.add(new DemoButton("Axis component", AxisComponent.factory()));
        buttonContainer.add(new DemoButton("Focus and context", FocusAndContext.factory()));
        buttonContainer.add(new DemoButton("Bar chart", BarChart.factory()));
        buttonContainer.add(new DemoButton("Chord diagram", ChordDiagram.factory()));

        buttonContainer.add(new DemoButton("Drag Multiples", DragMultiples.factory()));
        p.add(buttonContainer);
        container.addWest(p, 200);

        demoContainer = new FlowPanel();
        demoContainer.ensureDebugId(DEMO_CONTAINER_ID);
        demoContainer.setSize("100%", "100%");
        container.add(demoContainer);

        RootLayoutPanel.get().add(container);

        container.forceLayout();
    }

    public class DemoButton extends Button {

        public DemoButton(final String title, final Factory demoClass) {
            super(title, new DemoClickHandler(demoClass));
            ensureDebugId(demoClass.id());
        }

    }

    public class DemoClickHandler implements ClickHandler {
        private final Factory demoClass;

        public DemoClickHandler(final Factory demoClass) {
            super();
            this.demoClass = demoClass;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt
         * .event.dom.client.ClickEvent)
         */
        @Override
        public void onClick(final ClickEvent event) {
            stopCurrentDemo();

            DemoCase demo = demoClass.newInstance();
            demoContainer.add(demo);
            currentDemo = demo;
            demo.start();
        }

    }

    public class TestButton extends Button {
        public static final String ID = "testSuiteButton";

        public TestButton() {
            super("Test Suite", new ClickHandler() {
                private boolean firstTime = true;
                private TestRunner runner;

                @Override
                public void onClick(final ClickEvent event) {
                    stopCurrentDemo();
                    if (firstTime) {
                        testContainer = new TestSessionContainer();
                        runner = new TestRunner(testContainer);
                        runner.setTests(D3TestSuite.get().getTests());
                        firstTime = false;
                    }
                    demoContainer.add(testContainer);
                }
            });
            ensureDebugId(ID);
        }
    }

    private void stopCurrentDemo() {
        if (currentDemo != null) {
            currentDemo.stop();
            demoContainer.remove(currentDemo);
            currentDemo = null;
        } else if ((testContainer != null) && testContainer.getParent().equals(demoContainer)) {
            demoContainer.remove(testContainer);
        }

    }
}
