/**
 * 
 */
package org.gwtd3.api;

import java.util.Collections;
import java.util.List;

import org.gwtd3.api.core.Format;
import org.gwtd3.api.core.HSLColor;
import org.gwtd3.api.core.NumericAccessor;
import org.gwtd3.api.core.ObjectAccessor;
import org.gwtd3.api.core.RGBColor;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.core.Value;
import org.gwtd3.api.dsv.Dsv;
import org.gwtd3.api.dsv.DsvCallback;
import org.gwtd3.api.dsv.DsvObjectAccessor;
import org.gwtd3.api.interpolators.Interpolator;
import org.gwtd3.api.interpolators.JavascriptFunctionInterpolator;
import org.gwtd3.api.interpolators.JavascriptFunctionInterpolatorDecorator;
import org.gwtd3.api.layout.Layout;
import org.gwtd3.api.scales.Scale;
import org.gwtd3.api.svg.SVG;
import org.gwtd3.api.time.Time;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.UnsafeNativeLong;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class D3 extends JavaScriptObject {

	protected D3() {
	}

	/**
	 * @return the version of the d3 API
	 */
	public static final native String version()/*-{
		return $wnd.d3.version;
	}-*/;

	// =========== selections ==============
	/**
	 * Selects the first element that matches the specified selector string, returning a single-element selection. If no elements in the current document match the specified
	 * selector, returns the empty selection. If multiple elements match the selector, only the first matching element (in document traversal order) will be selected.
	 * <p>
	 * The selector is a valid CSS3 selector. For example, you can select by tag ("div"), class (".awesome"), unique identifier ("#foo"), attribute ("[color=red]"), or containment
	 * ("parent child"). Selectors can also be intersected (".this.that" for logical AND) or unioned (".this, .that" for logical OR)
	 * 
	 * @param selector
	 *            a CSS3 selector
	 * @return the {@link Selection}
	 */
	public static final native Selection select(String selector)/*-{
		return $wnd.d3.select(selector);
	}-*/;

	/**
	 * Selects the specified element. This is useful if you already have a reference to an element, or a global such as {@link Document#getBody()}
	 * 
	 * @param element
	 *            the element to select
	 * @return the {@link Selection}
	 */
	public static final native Selection select(Element element)/*-{
		return $wnd.d3.select(element);
	}-*/;

	/**
	 * Selects the specified widget. This is useful if you already have a reference to a widget, or a global such as {@link RootPanel#get()}
	 * 
	 * @param widget
	 *            the widget to select
	 * @return the {@link Selection}
	 */
	public static final Selection select(final Widget widget) {
		return select(widget.getElement());
	}

	/**
	 * Selects all elements that match the specified selector. The elements will be selected in document traversal order (top-to-bottom). If no elements in the current document
	 * match the specified selector, returns the empty selection.
	 * 
	 * @param selector
	 * @return
	 */
	public static final native Selection selectAll(String selector)/*-{
		return $wnd.d3.selectAll(selector);
	}-*/;

	/**
	 * Selects the specified array of elements. This is useful if you already have a reference to nodes, such as d3.selectAll(this.childNodes) within an event listener, or a global
	 * such as document.links. The nodes argument doesn't have to be an array, exactly; any pseudo-array that can be coerced into an array (e.g., a NodeList or arguments) will
	 * work.
	 * 
	 * @param nodes
	 * @return
	 */
	public static final native Selection selectAll(NodeList<?> nodes)/*-{
		return $wnd.d3.selectAll(nodes);
	}-*/;

	// =========== shuffle ==============

	/**
	 * Randomly shuffle the list of objects provided.
	 * <p>
	 * <i>Note: {@link Collections#shuffle(List)} is not GWT compatible</i>
	 * 
	 * @param objects
	 *            the list to shuffle
	 */
	public static final void shuffle(final List<?> objects) {
		for (int i = objects.size(); i > 1; i--) {
			swap(objects, i - 1, Random.nextInt(i));
		}
	}

	private static final <T> void swap(final List<T> list, final int idx1, final int idx2) {
		T temp = list.get(idx1);
		list.set(idx1, list.get(idx2));
		list.set(idx2, temp);
	}

	public static final void shuffle(final int[] input) {
		// --- Shuffle by exchanging each element randomly
		for (int i = 0; i < input.length; i++) {
			int randomPosition = Random.nextInt(input.length);
			int temp = input[i];
			input[i] = input[randomPosition];
			input[randomPosition] = temp;
		}
	}

	public static final void shuffle(final char[] input) {
		// --- Shuffle by exchanging each element randomly
		for (int i = 0; i < input.length; i++) {
			int randomPosition = Random.nextInt(input.length);
			char temp = input[i];
			input[i] = input[randomPosition];
			input[randomPosition] = temp;
		}
	}

	// ================ Colors ================

	/**
	 * Constructs a new RGB color with the specified r, g and b channel values. Each channel must be specified as an integer in the range [0,255]. The channels are available as the
	 * r, g and b attributes of the returned object.
	 * 
	 * @param r
	 *            the red channel
	 * @param g
	 *            the green channel
	 * @param b
	 *            the blue channel
	 * @return the new color instance
	 */
	public static final native RGBColor rgb(int r, int g, int b)/*-{
		return $wnd.d3.rgb(r, g, b);
	}-*/;

	/**
	 * Constructs a new HSL color by parsing the specified color string. The color string may be in a variety of formats:
	 * <ul>
	 * <li>rgb decimal - "rgb(255,255,255)"
	 * <li>hsl decimal - "hsl(120,50%,20%)"
	 * <li>rgb hexadecimal - "#ffeeaa"
	 * <li>rgb shorthand hexadecimal - "#fea"
	 * <li>named - "red", "white", "blue"
	 * </ul>
	 * 
	 * @param color
	 *            the color string representation
	 * @return the new color
	 */
	public static final native RGBColor rgb(final String color)/*-{
		return $wnd.d3.rgb(color);
	}-*/;

	/**
	 * Constructs a new HSL color with the specified hue h, saturation s and lightness l. The hue must be a number in the range [0,360]. The saturation and lightness must be in the
	 * range 0,1. These values are available as the h, s and l attributes of the returned object.
	 * 
	 * @param h
	 *            the hue channel [0;360]
	 * @param s
	 *            the saturation channel [0;1]
	 * @param l
	 *            the light channel [0;1]
	 * @return the new color instance
	 */
	public static final native HSLColor hsl(int h, double s, double l)/*-{
		return $wnd.d3.hsl(h, s, l);
	}-*/;

	/**
	 * Constructs a new HSL color by parsing the specified color string. The color string may be in a variety of formats:
	 * <ul>
	 * <li>rgb decimal - "rgb(255,255,255)"
	 * <li>hsl decimal - "hsl(120,50%,20%)"
	 * <li>rgb hexadecimal - "#ffeeaa"
	 * <li>rgb shorthand hexadecimal - "#fea"
	 * <li>named - "red", "white", "blue"
	 * </ul>
	 * 
	 * @param color
	 *            the color string representation
	 * @return the new color
	 */
	public static final native HSLColor hsl(final String color)/*-{
		return $wnd.d3.hsl(color);
	}-*/;

	// =========== svg ==============
	/**
	 * @return the svg module
	 */
	public static final native SVG svg()/*-{
		return $wnd.d3.svg;
	}-*/;

	// =========== scales ==============
	/**
	 * @return the scale module
	 */
	public static final native Scale<?> scale()/*-{
		return $wnd.d3.scale;
	}-*/;

	// =========== layouts ==============
	/**
	 * @return the layout module
	 */
	public static final native Layout<?> layout()/*-{
		return $wnd.d3.layout;
	}-*/;

	// =========== interpolation ==============

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static final Interpolator<String> interpolate(final String a, final String b) {
		return new JavascriptFunctionInterpolatorDecorator<String>(interpolate0(a, b));
	}

	public static final Interpolator<Integer> interpolate(final int a, final int b) {
		return new JavascriptFunctionInterpolatorDecorator<Integer>(interpolateNumber0(a, b)) {
			@Override
			public Integer cast(final Value v) {
				return new Integer(v.asInt());
			}
		};
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static final Interpolator<Boolean> interpolate(final boolean a, final boolean b) {
		return new JavascriptFunctionInterpolatorDecorator<Boolean>(interpolateNumber0(a ? 1.0 : 0.0, b ? 1.0 : 0.0)) {
			@Override
			public Boolean cast(final Value v) {
				return new Boolean(v.asInt() == 1 ? true : false);
			}
		};
	}

	public static final Interpolator<Byte> interpolate(final byte a, final byte b) {
		return new JavascriptFunctionInterpolatorDecorator<Byte>(interpolateNumber0(a, b)) {
			@Override
			public Byte cast(final Value v) {
				return new Byte(v.asByte());
			}
		};
	}

	public static final Interpolator<Character> interpolate(final char a, final char b) {
		return new JavascriptFunctionInterpolatorDecorator<Character>(interpolateNumber0(a, b)) {
			@Override
			public Character cast(final Value v) {
				return new Character(v.asChar());
			}
		};
	}

	public static final Interpolator<Double> interpolate(final double a, final double b) {
		return new JavascriptFunctionInterpolatorDecorator<Double>(interpolateNumber0(a, b)) {
			@Override
			public Double cast(final Value v) {
				return new Double(v.asDouble());
			}
		};
	}

	public static final Interpolator<Float> interpolate(final float a, final float b) {
		return new JavascriptFunctionInterpolatorDecorator<Float>(interpolateNumber0(a, b)) {
			@Override
			public Float cast(final Value v) {
				return new Float(v.asFloat());
			}
		};
	}

	public static final Interpolator<Long> interpolate(final long a, final long b) {
		return new JavascriptFunctionInterpolatorDecorator<Long>(interpolateNumber0(a, b)) {
			@Override
			public Long cast(final Value v) {
				// this will not work !!!
				// see https://developers.google.com/web-toolkit/doc/latest/DevGuideCodingBasicsJSNI#important
				// v.asLong()
				return new Long((long) v.asDouble());
			}
		};
	}

	public static final Interpolator<Short> interpolate(final short a, final short b) {
		return new JavascriptFunctionInterpolatorDecorator<Short>(interpolateNumber0(a, b)) {
			@Override
			public Short cast(final Value v) {
				return new Short(v.asShort());
			}
		};
	}

	public static final <T> Interpolator<T> interpolate(final T a, final T b) {
		return new JavascriptFunctionInterpolatorDecorator<T>(interpolate0(a, b)) {
			@Override
			public T interpolate(final double t) {
				return delegate.interpolate(t).as();
			}
		};
	}

	/**
	 * Actual JSNI implementation; the result is auto-casted to a {@link JavascriptFunctionInterpolator} and can be used by more specific versions of the
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	@UnsafeNativeLong
	private static final native <T> JavascriptFunctionInterpolator interpolate0(T a, T b) /*-{
		var result = $wnd.d3.interpolate(a, b);
		return result;
	}-*/;

	@UnsafeNativeLong
	private static final native JavascriptFunctionInterpolator interpolateNumber0(double a, double b) /*-{
		var result = $wnd.d3.interpolateNumber(a, b);
		return result;
	}-*/;

	// =========== time ==============

	/**
	 * @return the time module
	 */
	public static final native Time time() /*-{
		return $wnd.d3.time;
	}-*/;

	// ========= events and interactions ============
	/**
	 * Stores the current event, if any.
	 * <p>
	 * This global variable exists during an event listener callback registered with the on operator. The current event is reset after the listener is notified in a finally block.
	 * This allows the listener function to have the same form as other operator functions, being passed the current datum d and index i.
	 * <p>
	 * The {@link D3#event()} object is a DOM event and implements the standard event fields like timeStamp and keyCode as well as methods like preventDefault() and
	 * stopPropagation(). While you can use the native event's pageX and pageY, it is often more convenient to transform the event position to the local coordinate system of the
	 * container that received the event. For example, if you embed an SVG in the normal flow of your page, you may want the event position relative to the top-left corner of the
	 * SVG image. If your SVG contains transforms, you might also want to know the position of the event relative to those transforms.
	 * <p>
	 * Use the d3.mouse operator for the standard mouse pointer, and use d3.touches for multitouch events on iOS.
	 * 
	 * @return
	 */
	public static final native Event event()/*-{
		return $wnd.d3.event;
	}-*/;

	/**
	 * Returns the x and y coordinates of the current d3.event, relative to the specified container.
	 * The container may be an HTML or SVG container element, such as an svg:g or svg:svg.
	 * The coordinates are returned as a two-element array [ x, y].
	 * 
	 * @param container
	 * @return
	 */
	public static final native JsArrayNumber mouse(Element container)/*-{
		return $wnd.d3.mouse(container);
	}-*/;

	/**
	 * Returns the x coordinate of the current d3.event, relative to the specified container.
	 * The container may be an HTML or SVG container element, such as an svg:g or svg:svg.
	 * The coordinates are returned as a two-element array [ x, y].
	 * 
	 * @param container
	 * @return
	 */
	public static final native double mouseX(Element container)/*-{
		return $wnd.d3.mouse(container)[0];
	}-*/;

	/**
	 * Returns the y coordinate of the current d3.event, relative to the specified container.
	 * The container may be an HTML or SVG container element, such as an svg:g or svg:svg.
	 * The coordinates are returned as a two-element array [ x, y].
	 * 
	 * @param container
	 * @return
	 */
	public static final native double mouseY(Element container)/*-{
		return $wnd.d3.mouse(container)[1];
	}-*/;

	// =========== csv ==============

	/**
	 * @return the CSV module
	 */
	public static final native <T> Dsv<T> csv() /*-{
		return $wnd.d3.csv;
	}-*/;

	/**
	 * Issues an HTTP GET request for the comma-separated values (CSV) file at the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of the request will be "text/csv". The request is processed asynchronously, such that this method
	 * returns immediately after opening the request. When the CSV data is available, the specified callback will be invoked with the parsed rows as the argument. If an error
	 * occurs, the callback function will instead be invoked with null.
	 */
	public static final native <T> Dsv<T> csv(String url, DsvCallback<T> callback) /*-{
		return $wnd.d3
				.csv(
						url,
						function(error, rows) {
							callback.@org.gwtd3.api.dsv.DsvCallback::get(Lcom/google/gwt/core/client/JavaScriptObject;Lorg/gwtd3/api/dsv/DsvRows;)(error, rows);
						});
	}-*/;

	/**
	 * Issues an HTTP GET request for the comma-separated values (CSV) file at the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of the request will be "text/csv". The request is processed asynchronously, such that this method
	 * returns immediately after opening the request. When the CSV data is available, the specified callback will be invoked with the parsed rows as the argument. If an error
	 * occurs, the callback function will instead be invoked with null. The accessor may be specified, which is then passed to d3.csv.parse; the accessor may also be specified by
	 * using the return request object’s row function.
	 */
	public static final native <T> Dsv<T> csv(String url, DsvObjectAccessor<T> accessor, DsvCallback<T> callback)/*-{
		return $wnd.d3
				.csv(
						url,
						function(row, index) {
							return accessor.@org.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I)(row, index);
						},
						function(error, rows) {
							callback.@org.gwtd3.api.dsv.DsvCallback::get(Lcom/google/gwt/core/client/JavaScriptObject;Lorg/gwtd3/api/dsv/DsvRows;)(error, rows);
						});
	}-*/;

	/**
	 * Issues an HTTP GET request for the comma-separated values (CSV) file at the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of the request will be "text/csv". The request is processed asynchronously, such that this method
	 * returns immediately after opening the request. When the CSV data is available, the specified callback will be invoked with the parsed rows as the argument. If an error
	 * occurs, the callback function will instead be invoked with null. The accessor may be specified, which is then passed to d3.csv.parse; the accessor may also be specified by
	 * using the return request object’s row function.
	 */
	public static final native <T> Dsv<T> csv(String url, DsvObjectAccessor<T> accessor)/*-{
		return $wnd.d3
				.csv(
						url,
						function(row, index) {
							return accessor.@org.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I)(row, index);
						});
	}-*/;

	/**
	 * Issues an HTTP GET request for the comma-separated values (CSV) file at the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of the request will be "text/csv". The request is processed asynchronously, such that this method
	 * returns immediately after opening the request.
	 */
	public static final native <T> Dsv<T> csv(String url)/*-{
		return $wnd.d3.csv(url);
	}-*/;

	// =========== tsv ==============

	/**
	 * @return the TSV module
	 */
	public static final native <T> Dsv<T> tsv() /*-{
		return $wnd.d3.tsv;
	}-*/;

	/**
	 * Issues an HTTP GET request for the comma-separated values (TSV) file at the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of the request will be "text/tsv". The request is processed asynchronously, such that this method
	 * returns immediately after opening the request. When the TSV data is available, the specified callback will be invoked with the parsed rows as the argument. If an error
	 * occurs, the callback function will instead be invoked with null.
	 */
	public static final native <T> Dsv<T> tsv(String url, DsvCallback<T> callback) /*-{
		return $wnd.d3
				.tsv(
						url,
						function(error, rows) {
							callback.@org.gwtd3.api.dsv.DsvCallback::get(Lcom/google/gwt/core/client/JavaScriptObject;Lorg/gwtd3/api/dsv/DsvRows;)(error, rows);
						});
	}-*/;

	/**
	 * Issues an HTTP GET request for the comma-separated values (TSV) file at the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of the request will be "text/tsv". The request is processed asynchronously, such that this method
	 * returns immediately after opening the request. When the TSV data is available, the specified callback will be invoked with the parsed rows as the argument. If an error
	 * occurs, the callback function will instead be invoked with null. The accessor may be specified, which is then passed to d3.tsv.parse; the accessor may also be specified by
	 * using the return request object’s row function.
	 */
	public static final native <T> Dsv<T> tsv(String url, DsvObjectAccessor<T> accessor, DsvCallback<T> callback)/*-{
		return $wnd.d3
				.tsv(
						url,
						function(row, index) {
							return accessor.@org.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I)(row, index);
						},
						function(error, rows) {
							callback.@org.gwtd3.api.dsv.DsvCallback::get(Lcom/google/gwt/core/client/JavaScriptObject;Lorg/gwtd3/api/dsv/DsvRows;)(error, rows);
						});
	}-*/;

	/**
	 * Issues an HTTP GET request for the comma-separated values (TSV) file at the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of the request will be "text/tsv". The request is processed asynchronously, such that this method
	 * returns immediately after opening the request. When the TSV data is available, the specified callback will be invoked with the parsed rows as the argument. If an error
	 * occurs, the callback function will instead be invoked with null. The accessor may be specified, which is then passed to d3.tsv.parse; the accessor may also be specified by
	 * using the return request object’s row function.
	 */
	public static final native <T> Dsv<T> tsv(String url, DsvObjectAccessor<T> accessor)/*-{
		return $wnd.d3
				.tsv(
						url,
						function(row, index) {
							return accessor.@org.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I)(row, index);
						});
	}-*/;

	/**
	 * Issues an HTTP GET request for the comma-separated values (TSV) file at the specified url.
	 * <p>
	 * The file contents are assumed to be RFC4180-compliant. The mime type of the request will be "text/tsv". The request is processed asynchronously, such that this method
	 * returns immediately after opening the request.
	 */
	public static final native <T> Dsv<T> tsv(String url)/*-{
		return $wnd.d3.tsv(url);
	}-*/;

	// ============= math ============

	public static final native <T> double max(JavaScriptObject array, NumericAccessor<T> accessor) /*-{
		var rs = $wnd.d3
				.max(
						array,
						function(d) {
							return accessor.@org.gwtd3.api.core.NumericAccessor::apply(Ljava/lang/Object;)(d);
						});
		return rs;
	}-*/;

	/**
	 * Find the minimum and maximum value in an array. This is equivalent to calling d3.min and d3.max simultaneously.
	 * 
	 * @param array
	 *            the given array.
	 * @return the minimum and maximum value in the given array using natural order.
	 */
	public static final native JsArrayMixed extent(JavaScriptObject array) /*-{
		return $wnd.d3.extent(array);
	}-*/;

	/**
	 * Find the minimum and maximum value in an array. This is equivalent to calling d3.min and d3.max simultaneously.
	 * 
	 * @param array
	 *            the given array.
	 * @return the minimum and maximum value in the given array using natural order.
	 */
	public static final native <D, R> JsArrayMixed extent(JavaScriptObject array, ObjectAccessor<D, R> accessor) /*-{
        var accessor = accessor.@org.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I);
        return $wnd.d3.extent(array, function(d, i) {
            return accessor.@org.gwtd3.api.core.ObjectAccessor::apply(Ljava/lang/Object;I)(d, i);
        });
    }-*/;

	/**
	 * Format a number as a string.
	 * <p>
	 * Returns a new format function with the given string specifier. A format function takes a number as the only argument, and returns a string representing the formatted number.
	 * 
	 * @see <a href="https://github.com/mbostock/d3/wiki/Formatting#wiki-d3_format">D3.js official documentation</a>
	 * @param specifier
	 *            the given string specifier.
	 * @return the format function.
	 */
	public static final native Format format(String specifier) /*-{
		return $wnd.d3.format(specifier);
	}-*/;

	/**
	 * Compare two values for sorting.
	 * <p>
	 * This is the comparator function for natural order.
	 * 
	 * @return
	 */
	public static final native Sort ascending() /*-{
		return $wnd.d3.ascending;
	}-*/;

	/**
	 * Compare two values for sorting.
	 * <p>
	 * This is the comparator function for reverse natural order.
	 * 
	 * @return
	 */
	public static final native Sort descending() /*-{
		return $wnd.d3.descending;
	}-*/;

	/**
	 * Generate a range of numeric values.
	 * 
	 * @see D3#range(double, double, double)
	 * @param stop
	 * @return
	 */
	public static final native JavaScriptObject range(double stop) /*-{
		return $wnd.d3.range(stop);
	}-*/;

	/**
	 * Generate a range of numeric values.
	 * 
	 * @see D3#range(double, double, double)
	 * @param stop
	 * @param step
	 * @return
	 */
	public static final native JavaScriptObject range(double stop, double step) /*-{
		return $wnd.d3.range(stop, step);
	}-*/;

	/**
	 * Generate a range of numeric values.
	 * <p>
	 * Generates an array containing an arithmetic progression, similar to the Python built-in range. This method is often used to iterate over a sequence of numeric or integer
	 * values, such as the indexes into an array. Unlike the Python version, the arguments are not required to be integers, though the results are more predictable if they are due
	 * to floating point precision. If step is omitted, it defaults to 1. If start is omitted, it defaults to 0. The stop value is not included in the result. The full form returns
	 * an array of numbers [*start*, start + step, start + 2 * step, ...]. If step is positive, the last element is the largest start + i * step less than stop; if step is
	 * negative, the last element is the smallest start + i * step greater than stop. If the returned array would contain an infinite number of values, an error is thrown rather
	 * than causing an infinite loop.
	 * 
	 * @param start
	 * @param stop
	 * @param step
	 * @return
	 */
	public static final native JavaScriptObject range(double start, double stop, double step) /*-{
		return $wnd.d3.range(start, stop, step);
	}-*/;

}