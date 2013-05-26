/**
 * 
 */
package org.gwtd3.api.behaviour;

import org.gwtd3.api.D3;
import org.gwtd3.api.IsFunction;
import org.gwtd3.api.functions.DatumFunction;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This behavior automatically creates event listeners to handle drag gestures on an element. Both mouse events and touch events are supported.
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class Drag extends JavaScriptObject implements IsFunction {

	protected Drag() {

	}

	public static enum DragEventType {
		/**
		 * Fired when a drag gesture is started
		 */
		dragstart,
		/**
		 * Fired when the element is dragged.
		 * d3.event will contain "x" and "y" properties representing the current absolute drag coordinates of the element.
		 * It will also contain "dx" and "dy" properties representing the element's coordinates relative to
		 * its position at the beginning of the gesture.
		 */
		drag,
		/**
		 * Fired when the drag gesture has finished
		 */
		dragend;
	}

	/**
	 * Registers the specified listener to receive events of the specified type from the drag behavior.
	 * <p>
	 * See {@link DragEventType} for more information.
	 * 
	 * @param type
	 * @param listener
	 * @return
	 */
	public final native Drag on(DragEventType type, DatumFunction<Void> listener)/*-{
		return this
				.on(
						type.@org.gwtd3.api.behaviour.Drag.DragEventType::name()(),
						function(d, index) {
							listener.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)(this,{datum:d},index);
						});
	}-*/;

	/**
	 * Set the origin accessor to the specified function.
	 * <p>
	 * An origin accessor is used to determine the starting position (the “origin”) of the element being dragged; this allows the drag behavior to preserve the offset between the
	 * mouse position and the starting element position during drag. If the origin accessor is null, then the element position is set to the mouse position on drag; this can cause
	 * a noticeable jump on large elements. If an origin accessor is specified, the function is called on mousedown. The function is invoked in the same manner as other operator
	 * functions, being passed the current datum d and index i, with the this context as the clicked-on DOM element. To access the current event, use the global {@link D3#event()}.
	 * The origin accessor must return an object with x and y properties representing the starting coordinates of the element being dragged.
	 * <p>
	 * Frequently the origin accessor is specified as Object, or {@link D3#identity()}, which is equivalent to the identity function. This is suitable when the datum bound to the
	 * dragged element is already an object with x and y attributes representing its current position.
	 * <p>
	 * For example: <a href="http://bl.ocks.org/1557377">http://bl.ocks.org/1557377</a>
	 * 
	 * @param o
	 *            the origin accessor function, returning an object with x and y attributes.
	 * @return the object Drag.
	 */
	public final native Drag origin(JavaScriptObject o)/*-{
		return this.origin(o);
	}-*/;
}
