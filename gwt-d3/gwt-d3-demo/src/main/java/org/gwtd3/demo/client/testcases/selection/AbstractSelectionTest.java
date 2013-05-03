/**
 * 
 */
package org.gwtd3.demo.client.testcases.selection;

import org.gwtd3.demo.client.test.AbstractBehaviourTest;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public abstract class AbstractSelectionTest extends AbstractBehaviourTest {

	public String getElementAttribute(final int index, final String attribute) {
		return getElement(index).getAttribute(attribute);
	}

	@Override
	protected void given(final ComplexPanel sandbox) {
		sandbox.add(new Label("1"));
		sandbox.add(new Label("2"));
		sandbox.add(new Label("3"));
	}

}
