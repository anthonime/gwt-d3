/**
 * 
 */
package org.gwtd3.api.svg;

import org.gwtd3.api.IsFunction;
import org.gwtd3.api.core.Format;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.core.Values;
import org.gwtd3.api.scales.Scale;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A svg object designed to work with D3 {@link Scale}s.
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
/**
 * @author citaire
 *
 */
public class Axis extends JavaScriptObject implements IsFunction {

	public static enum Orientation {
		TOP,
		BOTTOM,
		LEFT,
		RIGHT
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
	 * Returns the current orientation, which defaults to {@link Orientation#BOTTOM}.
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
	 * The orientation of an axis is the position of the ticks and their labels in relation to the axis path.
	 * <p>
	 * For a vertical axis, specify {@link Orientation#LEFT} or {@link Orientation#RIGHT}; for a horizontal axis, specify {@link Orientation#TOP} or {@link Orientation#BOTTOM}.
	 * <p>
	 * If instead you want to determine the position of the axis with respect to the plot, use the transform attribute.
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
	 * Specify the argument that will be passed to the associated scale {@link Scale#ticks()} method to compute the tick values. This version specify the desired tick count.
	 * <p>
	 * The count parameter is also passed to the {@link Scale#tickFormat(int, String)} method to generate the default tick format.
	 * 
	 * 
	 * @param count
	 * @return
	 */
	public final native Axis ticks(int count)/*-{
		return this.ticks(count);
	}-*/;

	/**
	 * Get the arguments that will be passed to the associated scale {@link Scale#ticks()} method to compute the tick values.
	 * 
	 * @param count
	 * @return
	 */
	public final native Values ticks()/*-{
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
	 * Sets the number of uniform subdivision ticks to make between major tick marks. The specified count specifies the number of minor ticks, which is one less than the number of
	 * subdivisions.
	 * <p>
	 * For example, axis.tickSubdivide(1) produces one minor tick per major tick, thus cutting the space between each major tick in two. As another example, decimal subdivision is
	 * specified as axis.tickSubdivide(9).
	 * 
	 * @param count
	 *            the tick subdivision count
	 * @return the current axis
	 */
	public final native Axis tickSubdivide(int count)/*-{
		return this.tickSubdivide(count);
	}-*/;

	/**
	 * @return the inner radius of the arc.
	 */
	public final native int innerRadius()/*-{
		return this.innerRadius();
	}-*/;

	/**
	 * Set the innerradius of the Arc.
	 * 
	 * @param radius
	 * @return the current arc generator
	 */
	public final native Axis innerRadius(int radius)/*-{
		return this.innerRadius(radius);
	}-*/;

	/**
	 * @return the outerRadius of the arc.
	 */
	public final native int outerRadius()/*-{
		return this.outerRadius();
	}-*/;

	/**
	 * Set the outerRadius of the Arc.
	 * 
	 * @param radius
	 * @return the current arc generator
	 */
	public final native Axis outerRadius(int radius)/*-{
		return this.outerRadius(radius);
	}-*/;

	/**
	 * @return the startAngle of the arc.
	 */
	public final native double startAngle()/*-{
		return this.startAngle();
	}-*/;

	/**
	 * Set the start angle in radians of the Arc.
	 * <p>
	 * Angles are specified in radians, even though SVG typically uses degrees. The angle 0 corresponds to 12 o'clock (negative y) and continues clockwise repeating at 2xPI.
	 * 
	 * @param angleRadians
	 *            the angle in radians
	 * @return the current arc generator
	 */
	public final native Axis startAngle(double angleRadians)/*-{
		return this.startAngle(angleRadians);
	}-*/;

	/**
	 * @return the endAngle of the arc.
	 */
	public final double endAngle() {
		return getOrInvokeGetter("endAngle");
	}

	/**
	 * Set the endAngle in radians of the Arc.
	 * <p>
	 * Angles are specified in radians, even though SVG typically uses degrees. The angle 0 corresponds to 12 o'clock (negative y) and continues clockwise repeating at 2xPI.
	 * 
	 * @param angleRadians
	 *            the angle in radians
	 * @return the current arc generator
	 */
	public final Axis endAngle(final double angleRadians) {
		return setOrInvokeSetter("endAngle", angleRadians);
	}

	public final native double getOrInvokeGetter(String propName)/*-{
		if (typeof this[propName] === 'function') {
			return this[propName]();
		} else {
			return this[propName];
		}
	}-*/;

	public final native Axis setOrInvokeSetter(String propName, double value)/*-{
		if (typeof this[propName] === 'function') {
			return this[propName](value);
		} else {
			this[propName] = value;
			return this;
		}
	}-*/;

	/**
	 * Computes the centroid of the arc that would be generated from the specified input arguments; typically, the arguments are the current datum (d), and optionally the current
	 * index (i).
	 * <p>
	 * The centroid is defined as the midpoint in polar coordinates of the inner and outer radius, and the start and end angle. This provides a convenient location for arc labels.
	 * <p>
	 * TODO: test the result and the possible arguments
	 * 
	 * @param datum
	 * @param index
	 * @return
	 */
	public final native String centroid(int datum, int index)/*-{
		return this.centroid(datum, index);
	}-*/;

	/**
	 * Apply the axis to a selection or a transition.
	 * 
	 * The selection must contain an SVG or G element.
	 * 
	 * @param javaScriptObject the selection to apply the axis to
	 * @return the current axis.
	 */
	public final native Axis apply(Selection selection) /*-{
		return selection.call(this);
	}-*/;

	/**
	 * Override the tick formatting for labels.
	 * 
	 * @param format the tick value formatter for labels.
	 * @return the current axis.
	 */
	public final native Axis tickFormat(Format format) /*-{
		return this.tickFormat(format);
	}-*/;
}
