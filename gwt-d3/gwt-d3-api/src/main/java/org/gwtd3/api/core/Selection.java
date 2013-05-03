/**
 * 
 */
package org.gwtd3.api.core;

import org.gwtd3.api.D3;
import org.gwtd3.api.IsFunction;
import org.gwtd3.api.functions.DatumFunction;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Node;

/**
 * A selection is an array of elements pulled from the current document. D3 uses CSS3 to select elements. See {@link D3#select(String)} and
 * {@link D3#select(com.google.gwt.dom.client.Node)} methods
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class Selection extends EnteringSelection {

	protected Selection() {
	}

	/**
	 * Returns the first non-null element in the current selection. If the selection is empty, returns null.
	 * 
	 * @return the first non-null element in the current selection or null if the selection is empty.
	 */
	public final native Node node()/*-{
		return this.node();
	}-*/;

	// ======== subselections ==========

	/**
	 * For each element in the current selection, selects descendant elements that match the specified selector string.
	 * <p>
	 * The returned selection is grouped by the ancestor node in the current selection. If no element matches the specified selector for the current element, the group at the
	 * current index will be empty in the returned selection.
	 * <p>
	 * The subselection <strong>does not</strong> inherit data from the current selection; however, if the data value is specified as a function, this function will be based the
	 * data d of the ancestor node and the group index i.
	 * <p>
	 * Grouping by selectAll also affects subsequent entering placeholder nodes. Thus, to specify the parent node when appending entering nodes, use select followed by selectAll:
	 * <code>
	 * d3.select("body").selectAll("div") 
	 * </code> You can see the parent node of each group by inspecting the parentNode property of each group array, such as selection[0].parentNode.
	 * 
	 * <p>
	 * TODO: The selector may also be specified as a function that returns an array of elements (or a NodeList), or the empty array if there are no matching elements. In this case,
	 * the specified selector is invoked in the same manner as other operator functions, being passed the current datum d and index i, with the this context as the current DOM
	 * element.
	 * 
	 * @param selector
	 * @return
	 */
	public final native Selection selectAll(String selector)/*-{
		return this.selectAll(selector);
	}-*/;

	// ======== content ==========
	/**
	 * Returns the value of the specified attribute for the first non-null element in the selection. This is generally
	 * useful only if you know that the selection contains exactly
	 * one element.
	 * <p>
	 * The specified name may have a namespace prefix, such as xlink:href, to specify an "href" attribute in the XLink namespace. By default, D3 supports svg, xhtml, xlink, xml,
	 * and xmlns namespaces. Additional namespaces can be registered by adding to d3.ns.prefix.
	 * 
	 * @param name
	 *            the name of the attribute
	 * @return the value of the attribute
	 */
	public native final <T> T attr(final String name)
	/*-{
		return this.attr(name);
	}-*/;

	/**
	 * Sets the attribute with the specified name to the specified value on all selected elements.
	 * <p>
	 * The specified name may have a namespace prefix, such as xlink:href, to specify an "href" attribute in the XLink namespace. By default, D3 supports svg, xhtml, xlink, xml,
	 * and xmlns namespaces. Additional namespaces can be registered by adding to d3.ns.prefix.
	 * 
	 * @param name
	 *            the name of the attribute
	 * @param value
	 *            the new value to assign
	 * @return the current selection
	 */
	public native final <T> Selection attr(final String name, T value)
	/*-{
		return this.attr(name, value);
	}-*/;

/**
	 * See {@link #attr(String, Object).
	 * @param name
	 * @param value
	 * @return
	 */
	public native final <T> Selection attr(final String name, double value)
	/*-{
	return this.attr(name, value);
}-*/;

/**
	 * See {@link #attr(String, Object).
	 * @param name
	 * @param value
	 * @return
	 */
	public native final <T> Selection attr(final String name, int value)/*-{
	return this.attr(name, value);
}-*/;

/**
	 * See {@link #attr(String, Object).
	 * @param name
	 * @param value
	 * @return
	 */
	public native final <T> Selection attr(final String name, boolean value)/*-{
	return this.attr(name, value);
}-*/;

	/**
	 * Sets the attribute with the specified name to the value returned by the specified function on all selected
	 * elements.
	 * <p>
	 * The function is evaluated for each selected element (in order), being passed the current datum d and the current index i. The function's return value is then used to set
	 * each element's attribute. A null value will remove the specified attribute.
	 * <p>
	 * The specified name may have a namespace prefix, such as xlink:href, to specify an "href" attribute in the XLink namespace. By default, D3 supports svg, xhtml, xlink, xml,
	 * and xmlns namespaces. Additional namespaces can be registered by adding to d3.ns.prefix.
	 * 
	 * @param name
	 *            the name of the attribute
	 * @param callback
	 *            the function used to compute the new value of the attribute
	 * @return the current selection
	 */
	public native final Selection attr(final String name, final DatumFunction<?> callback)
	/*-{
		return this
				.attr(
						name,
						function(d, i) {
							return callback.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)(this,{datum:d},i);
						});
	}-*/;

	/**
	 * Returns the current computed value of the specified style property for the first non-null element in the
	 * selection.
	 * <p>
	 * This is generally useful only if you know the selection contains exactly one element.
	 * <p>
	 * Note that the computed value may be different than the value that was previously set, particularly if the style property was set using a shorthand property (such as the
	 * "font" style, which is shorthand for "font-size", "font-face", etc.).
	 * 
	 * @param name
	 *            the name of the style to return
	 * @return the style value
	 */
	public native final String style(String name) /*-{
		return this.style(name);
	}-*/;

	/**
	 * See {@link Selection#style(String, T, boolean)}.
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public native final Selection style(String name, String value) /*-{
		return this.style(name, value);
	}-*/;

	/**
	 * See {@link Selection#style(String, T, boolean)}.
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public native final Selection style(String name, double value) /*-{
		return this.style(name, value);
	}-*/;

	/**
	 * See {@link Selection#style(String, T, boolean)}.
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public native final Selection style(String name, DatumFunction<?> callback) /*-{
		return this
				.style(
						name,
						function(d, i) {
							return callback.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)(this,{datum:d},i);
						});
	}-*/;

	/**
	 * If value is specified, sets the CSS style property with the specified name to the specified value on all selected
	 * elements. A null value will remove the style property.
	 * 
	 * @param name
	 *            the name of the style to set
	 * @param value
	 *            the value to set
	 * @param important
	 *            true if the style value should be marked as !important, false otherwise
	 * @return the current selection
	 */
	public native final <T> Selection style(String name, T value, boolean important)/*-{
		if (important) {
			return this.style(name, value, "important");
		} else {
			return this.style(name, value);
		}
	}-*/;

	/**
	 * Sets the CSS style property with the specified name to the value returned by the given function on all selected
	 * elements.
	 * <p>
	 * The function is evaluated for each selected element (in order), being passed the current datum d and the current index i. The function's return value is then used to set
	 * each element's style property.
	 * <p>
	 * A null value will remove the style property.
	 * 
	 * @param name
	 *            the name of the style to set
	 * @param callback
	 *            the function to be called on each element and returning the value of the style
	 * @param important
	 *            true if the style value should be marked as !important, false otherwise
	 * @return the current selection
	 */
	public native final Selection style(String name, DatumFunction<Object> callback, boolean important)/*-{
		var imp = important ? 'important' : null;
		return this
				.style(
						name,
						function(d, i) {
							return 
							callback.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)
								(this,{datum:d},i);
						}, imp);
	}-*/;

	public native final Selection classed(String className, boolean add)/*-{
		throw new UnsupportedOperationException("not yet implemented");
	}-*/;

	public native final Selection property(String styleName, String value)/*-{
		throw new UnsupportedOperationException("not yet implemented");
	}-*/;

	/**
	 * Returns the value of the text content for the first non-null element in the selection. This is generally useful
	 * only if you know that the selection contains exactly one
	 * element.
	 * 
	 * @return the value of the text property
	 */
	public native final String text()/*-{
		return this.text();
	}-*/;

	/**
	 * Sets the text content of all selected elements to the given value.
	 * <p>
	 * The text operator is based on the textContent property; setting the text content will replace any existing child elements.
	 * 
	 * @param value
	 *            the new text value to set
	 * @return the current selection
	 */
	public native final <T> Selection text(T value)/*-{
		return this.text(value);
	}-*/;

	/**
	 * Sets the text content to the value returned by the specified function on all selected elements.
	 * <p>
	 * The function is evaluated for each selected element (in order), being passed the current datum d and the current index i. The function's return value is then used to set
	 * each element's text content.
	 * <p>
	 * 
	 * @param callback
	 *            the function used to compute the new text property
	 * @return the current selection
	 */
	public native final Selection text(final DatumFunction<?> callback) /*-{
		return this
				.text(function(d, i) {
					return callback.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)(this,{datum:d},i);
				});
	}-*/;

	public native final Selection html(String value)/*-{
		throw new UnsupportedOperationException("not yet implemented");
	}-*/;

	/**
	 * Removes the elements in the current selection from the current document. Generally speaking, you should stop
	 * using selections once you've removed them, because there's not
	 * currently a way to add them back to the document. (See the {@link #append(String)} and {@link #insert(String,String)} operators above for details.)
	 * 
	 * @return the current selection
	 */
	public native final Selection remove()/*-{
		return this.remove();
	}-*/;

	// ================================ data functions ========
	public native final UpdatingSelection data(JavaScriptObject data)/*-{
		return this.data(data);
	}-*/;

	public native final UpdatingSelection data(JsArrayInteger array, DatumFunction<Integer> callback)/*-{
		return this
				.data(
						array,
						function(d, i) {
							return callback.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)(this,{datum:d},i);
						});
	}-*/;

	public native final UpdatingSelection data(DatumFunction<?> callback) /*-{
		return this
				.data(function(d, i) {
					return callback.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)(this,{datum:d},i);
				});
	}-*/;

	/**
	 * Sets the element's bound data to the specified value on all selected elements. Unlike the {@link #data} methods,
	 * this method does not compute a join (and thus does not
	 * compute enter and exit selections).
	 * 
	 * See <a href="https://github.com/mbostock/d3/wiki/Selections#wiki-datum">datum </a>
	 * 
	 * @param object
	 * @return
	 */
	public native final <T> Selection datum(T object)/*-{
		return this.datum(object);
	}-*/;

	// ==================== TRANSITION =======
	/**
	 * Starts a transition for the current selection. Transitions behave much like selections, except operators animate
	 * smoothly over time rather than applying instantaneously.
	 * 
	 * @return the new transition
	 */
	public native final Transition transition()/*-{
		return this.transition();
	}-*/;

	/**
	 * Invokes the specified function once, passing in the current selection as a single parameter.
	 * 
	 * @param jsFunction
	 * @return the current selection
	 */
	public native final Selection call(IsFunction jsFunction) /*-{
		return this.call(jsFunction);
	}-*/;

	/**
	 * Adds or removes an event listener to each element in the current selection, for the specified type.
	 * <p>
	 * The type is a string event type name, such as "click", "mouseover", or "submit". You may use {@link BrowserEvents} constants for convenience.
	 * <p>
	 * The specified listener is invoked in the same manner as other operator functions, being passed the current datum d and index i, with the this context as the current DOM
	 * element.
	 * <p>
	 * To access the current event within a listener, use the global d3.event. The return value of the event listener is ignored.
	 * 
	 * If an event listener was already registered for the same type on the selected element, the existing listener is removed before the new listener is added. To register
	 * multiple listeners for the same event type, the type may be followed by an optional namespace, such as "click.foo" and "click.bar".
	 * 
	 * To remove a listener, pass null as the listener. To remove all listeners for a particular event type, pass null as the listener, and .type as the type, e.g.
	 * selection.on(".foo", null). An optional capture flag may be specified, which corresponds to the W3C useCapture flag:
	 * "After initiating capture, all events of the specified type will be dispatched to the registered EventListener before being dispatched to any EventTargets beneath them in the tree. Events which are bubbling upward through the tree will not trigger an EventListener designated to use capture."
	 * 
	 * If listener is not specified, returns the currently-assigned listener for the specified type, if any.
	 * 
	 * @param eventType
	 * @param listener
	 * @return
	 */
	public native final Selection on(String eventType, DatumFunction<Void> listener) /*-{
		return this
				.on(
						eventType,
						function(d, i) {
							listener.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)(this,{datum:d},i);
						});
	}-*/;

}