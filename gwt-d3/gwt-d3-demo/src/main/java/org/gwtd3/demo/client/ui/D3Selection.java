/**
 * 
 */
package org.gwtd3.demo.client.ui;

import org.gwtd3.api.D3;
import org.gwtd3.api.core.Selection;

import com.google.gwt.dom.client.Element;

/**
 * An object wrapping a d3 {@link Selection} and the corresponding element.
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class D3Selection {

	private final Selection selection;
	private final Element element;

	private D3Selection(final Element element) {
		super();
		this.element = element;
		this.selection = D3.select(element);
	}

	/**
	 * @return the element
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * @return the selection
	 */
	public Selection getSelection() {
		return selection;
	}

}
