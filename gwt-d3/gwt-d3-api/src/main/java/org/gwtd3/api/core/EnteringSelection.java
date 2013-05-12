/**
 * 
 */
package org.gwtd3.api.core;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A selection returned by a call to {@link UpdatingSelection#enter()}.
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class EnteringSelection extends JavaScriptObject {

	protected EnteringSelection() {
	}

	/**
	 * Appends a new element with the specified name as the last child of each
	 * element in the current selection.
	 * <p>
	 * Returns a new selection containing the appended elements.
	 * <p>
	 * Each new element inherits the data of the current elements, if any, in the same manner as select for subselections.
	 * <p>
	 * The name must be specified as a constant, though in the future we might allow appending of existing elements or a function to generate the name dynamically.
	 * 
	 * The element's tag name may have a namespace prefix, such as "svg:text" to create a "text" element in the SVG namespace. By default, D3 supports svg, xhtml, xlink, xml and
	 * xmlns namespaces. Additional namespaces can be registered by adding to d3.ns.prefix.
	 * 
	 * @param value
	 * @return
	 */
	public native final Selection append(String name)/*-{
		return this.append(name);
	}-*/;

	/**
	 * Insert a new element before any other existing children.
	 * <p>
	 * The method returns a new Selection containing the inserted element.
	 * 
	 * @param string
	 * @return a new selection containing the inserted element
	 */
	public final Selection prepend(final String name) {
		return insert(name, ":first-child");
	}

	/**
	 * For each element in the current selection, selects the first descendant
	 * element that matches the specified selector string.
	 * <p>
	 * If no element matches the specified selector for the current element, the element at the current index will be null in the returned selection; operators (with the exception
	 * of data) automatically skip null elements, thereby preserving the index of the existing selection.
	 * <p>
	 * If the current element has associated data, this data is inherited by the returned subselection, and automatically bound to the newly selected elements.
	 * <p>
	 * If multiple elements match the selector, only the first matching element in document traversal order will be selected.
	 * <p>
	 * TODO: The selector may also be specified as a function that returns an element, or null if there is no matching element. In this case, the specified selector is invoked in
	 * the same manner as other operator functions, being passed the current datum d and index i, with the this context as the current DOM element.
	 * 
	 * @param selector
	 * @return
	 */
	public final native Selection select(String selector)/*-{
		return this.select(selector);
	}-*/;

	/**
	 * Inserts a new element with the specified name before the element matching
	 * the specified before selector, for each element in the current selection.
	 * <p>
	 * Returns a new selection containing the inserted elements.
	 * <p>
	 * If the before selector does not match any elements, then the new element will be the last child as with {@link #append(String)}.
	 * <p>
	 * Each new element inherits the data of the current elements (if any), in the same manner as select for subselections. The name and before selector must be specified as
	 * constants, though in the future we might allow inserting of existing elements or a function to generate the name or selector dynamically.
	 * <p>
	 * For instance, insert("div", ":first-child") will prepend child div nodes to the current selection.
	 * <p>
	 * The element's tag name may have a namespace prefix, such as "svg:text" to create a "text" element in the SVG namespace. By default, D3 supports svg, xhtml, xlink, xml and
	 * xmlns namespaces. Additional namespaces can be registered by adding to d3.ns.prefix.
	 * 
	 * @param name
	 * @param beforeSelector
	 *            a selector to select element to insert the new element before
	 * @return a new selection containing the inserted elements
	 */
	public native final Selection insert(String name, String beforeSelector)/*-{
		return this.insert(name, beforeSelector);
	}-*/;

	/**
	 * Returns true if the current selection is empty; a selection is empty if
	 * it contains no non-null elements.
	 * 
	 * @return true if selection is empty, false otherwise.
	 */
	public final native boolean empty()/*-{
		return this.empty();
	}-*/;
}
