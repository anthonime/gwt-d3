/**
 * 
 */
package org.gwtd3.demo.client.testcases.selection;

import org.gwtd3.api.D3;
import org.gwtd3.api.core.Datum;
import org.gwtd3.api.functions.DatumFunction;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class TestSelectionAttrSetFunction extends AbstractSelectionTest {

	@Override
	protected void when(final ComplexPanel sandbox) {
		// .data(JsArrays.asJsArray(1, 2, 3))
		D3.select(sandbox).selectAll("div").attr("name", new DatumFunction<String>() {
			@Override
			public String apply(final Element context, final Datum datum, final int index) {
				return String.valueOf(123);
			}
		});
	}

	@Override
	protected void then(final ComplexPanel sandbox) {
		assertTrue(getElementAttribute(0, "name").equals("123"));
		assertTrue(getElementAttribute(1, "name").equals("123"));
		assertTrue(getElementAttribute(2, "name").equals("123"));
	}
}
