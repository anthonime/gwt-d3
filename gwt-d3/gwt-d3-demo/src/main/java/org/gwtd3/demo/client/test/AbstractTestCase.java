/**
 * 
 */
package org.gwtd3.demo.client.test;

import junit.framework.Assert;

import com.google.gwt.user.client.ui.ComplexPanel;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public abstract class AbstractTestCase extends Assert implements TestCase {
    protected ComplexPanel sandbox;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.gwtd3.demo.client.tests.UnitTest#tearDown(com.google.gwt.user.client
     * .ui .RootPanel)
     */
    @Override
    public void tearDown(final ComplexPanel sandbox) {
        sandbox.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.gwtd3.demo.client.tests.UnitTest#setUp(com.google.gwt.user.client
     * .ui.RootPanel )
     */
    @Override
    public void setUp(final ComplexPanel sandbox) {
        this.sandbox = sandbox;

    }
}
