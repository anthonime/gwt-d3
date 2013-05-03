/**
 * 
 */
package org.gwtd3.demo.client.testcases.selection;

import org.gwtd3.api.D3;

import com.google.gwt.user.client.ui.ComplexPanel;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class TestSelectionAttrSetInt extends AbstractSelectionTest {

	@Override
	protected void when(final ComplexPanel sandbox) {
		D3.select(sandbox).selectAll("div").attr("name", 123);
	}

	@Override
	protected void then(final ComplexPanel sandbox) {
		assertTrue(getElementAttribute(0, "name").equals("123"));
		assertTrue(getElementAttribute(1, "name").equals("123"));
		assertTrue(getElementAttribute(2, "name").equals("123"));
	}
}
