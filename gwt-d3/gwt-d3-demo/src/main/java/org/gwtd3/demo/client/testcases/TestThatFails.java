/**
 * 
 */
package org.gwtd3.demo.client.testcases;

import org.gwtd3.demo.client.test.AbstractTestCase;

import com.google.gwt.user.client.ui.ComplexPanel;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class TestThatFails extends AbstractTestCase {

	@Override
	public void doTest(final ComplexPanel sandbox) {
		throw new AssertionError("fake assertionerror to test");
	}

}
