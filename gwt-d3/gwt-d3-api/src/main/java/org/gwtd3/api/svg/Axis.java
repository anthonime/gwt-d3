/**
 * 
 */
package org.gwtd3.api.svg;

import org.gwtd3.api.IsFunction;
import org.gwtd3.api.arrays.Array;
import org.gwtd3.api.core.Format;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.scales.Scale;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A svg object designed to work with D3 {@link Scale}s.
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * @author citaire
 * 
 */
public class Axis extends JavaScriptObject implements IsFunction {

	public static enum Orientation {
		TOP, BOTTOM, LEFT, RIGHT
	}

	protected Axis() {
	}

	/**
	 * Return the associated scale, which defaults to a linear scale.
	 * 
	 * @return the scale.
	 */
	public final native <S extends Scale<S>> S scale()/*-{
		return this.scale();
	}-*/;

	/**
	 * Set the associated scale.
	 * 
	 * @param the
	 *            scale.
	 * @return the current axis
	 */
	public final native <S extends Scale<S>> Axis scale(S s)/*-{
		return this.scale(s);
	}-*/;

	/**
	 * Returns the current orientation, which defaults to
	 * {@link Orientation#BOTTOM}.
	 * <p>
	 * 
	 * @see #orient(Orientation)
	 * 
	 * @return the current orientation
	 */
	public final native Orientation orient()/*-{
		var o = this.orient().toUpperCase();
		return @org.gwtd3.api.svg.Axis.Orientation::valueOf(Ljava/lang/String;)(o);
	}-*/;

	/**
	 * Sets the axis orientation and returns the axis.
	 * <p>
	 * The orientation of an axis is the position of the ticks and their labels
	 * in relation to the axis path.
	 * <p>
	 * For a vertical axis, specify {@link Orientation#LEFT} or
	 * {@link Orientation#RIGHT}; for a horizontal axis, specify
	 * {@link Orientation#TOP} or {@link Orientation#BOTTOM}.
	 * <p>
	 * If instead you want to determine the position of the axis with respect to
	 * the plot, use the transform attribute.
	 * <p>
	 * 
	 * @param o
	 *            the orientation
	 * @return the current axis
	 */
	public final native Axis orient(Orientation o)/*-{
		return this.orient(o.toString().toLowerCase());
	}-*/;

	/**
	 * Specify the argument that will be passed to the associated scale
	 * {@link Scale#ticks()} method to compute the tick values. This version
	 * specify the desired tick count.
	 * <p>
	 * The count parameter is also passed to the
	 * {@link Scale#tickFormat(int, String)} method to generate the default tick
	 * format.
	 * 
	 * 
	 * @param count
	 * @return
	 */
	public final native Axis ticks(int count)/*-{
		return this.ticks(count);
	}-*/;

	/**
	 * Get the arguments that will be passed to the associated scale
	 * {@link Scale#ticks()} method to compute the tick values.
	 * 
	 * @param count
	 * @return
	 */
	public final native Array<?> ticks()/*-{
		return this.ticks();
	}-*/;

	/**
	 * TODO
	 * 
	 * @param jsFunction
	 * @param count
	 * @return
	 */
	public final native Axis ticks(JavaScriptObject jsFunction, int count)/*-{
		return this.ticks(jsFunction, count);
	}-*/;

	/**
	 * Sets the major, minor and end to the given value.
	 * 
	 * @param majorMinorEnd
	 *            the new value
	 * @return the current axis
	 */
	public final native Axis tickSize(int majorMinorEnd)/*-{
		return this.tickSize(majorMinorEnd);
	}-*/;

	/**
	 * Sets the major, minor and end to the given values.
	 * 
	 * @param majorMinor
	 *            the new value for major and minor ticks
	 * @param end
	 *            the new end value
	 * @return the current axis
	 */
	public final native Axis tickSize(int majorMinor, int end)/*-{
		return this.tickSize(majorMinor, end);
	}-*/;

	/**
	 * Sets the major, minor and end to the given values.
	 * 
	 * @param majorMinor
	 *            the new value for major and minor ticks
	 * @param end
	 *            the new end value
	 * @return the current axis
	 */
	public final native Axis tickSize(int major, int minor, int end)/*-{
		return this.tickSize(major, minor, end);
	}-*/;

	/**
	 * Get the tick subdivision count, which defaults to zero.
	 * 
	 * @return the tick subdivision count
	 */
	public final native int tickSubdivide()/*-{
		return this.tickSubdivide();
	}-*/;

	/**
	 * Set the tick subdivision count and returns the axis.
	 * <p>
	 * Sets the number of uniform subdivision ticks to make between major tick
	 * marks. The specified count specifies the number of minor ticks, which is
	 * one less than the number of subdivisions.
	 * <p>
	 * For example, axis.tickSubdivide(1) produces one minor tick per major
	 * tick, thus cutting the space between each major tick in two. As another
	 * example, decimal subdivision is specified as axis.tickSubdivide(9).
	 * 
	 * @param count
	 *            the tick subdivision count
	 * @return the current axis
	 */
	public final native Axis tickSubdivide(int count)/*-{
		return this.tickSubdivide(count);
	}-*/;

	/**
	 * Apply the axis to a selection or a transition.
	 * 
	 * The selection must contain an SVG or G element.
	 * 
	 * @param javaScriptObject
	 *            the selection to apply the axis to
	 * @return the current axis.
	 */
	public final native Axis apply(Selection selection) /*-{
		return selection.call(this);
	}-*/;

	/**
	 * Override the tick formatting for labels.
	 * 
	 * @param format
	 *            the tick value formatter for labels.
	 * @return the current axis.
	 */
	public final native Axis tickFormat(Format format) /*-{
		return this.tickFormat(format);
	}-*/;
}
