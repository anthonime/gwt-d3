/**
 * 
 */
package org.gwtd3.demo.client.test;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public abstract class AbstractBehaviourTest extends AbstractTestCase {

	@SuppressWarnings("unchecked")
	public <T extends Widget> T getWidget(final int index) {
		return (T) sandbox.getWidget(index);
	}

	@SuppressWarnings("unchecked")
	public <T extends Element> T getElement(final int index) {
		return (T) sandbox.getWidget(index).getElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.gwtd3.demo.client.test.TestCase#doTest(com.google.gwt.user.client
	 * .ui.ComplexPanel)
	 */
	@Override
	public void doTest(final ComplexPanel sandbox) {
		given(sandbox);
		when(sandbox);
		then(sandbox);
	}

	/**
	 * @param sandbox
	 */
	protected abstract void then(ComplexPanel sandbox);

	/**
	 * @param sandbox
	 */
	protected abstract void when(ComplexPanel sandbox);

	/**
	 * @param sandbox
	 */
	protected abstract void given(ComplexPanel sandbox);
}
