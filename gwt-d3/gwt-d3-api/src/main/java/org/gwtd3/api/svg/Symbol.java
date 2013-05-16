/**
 * 
 */
package org.gwtd3.api.svg;

import org.gwtd3.api.functions.DatumFunction;

/**
 * A {@link PathDataGenerator} generating symbols shapes.
 * <p>
 * While the default accessors generate static symbols, it is common to set one or more of the accessors using a function, such as setting the size proportional to a dimension of
 * data for a scatterplot. The returned function generates path data for various symbols (see {@link Type} for all the symbols).
 * <p>
 * Note that the symbol does not include accessors for x and y. Instead, you can use the path element's transform attribute to position the symbols, as in:
 * 
 * <pre>
 * {@code
 * vis.selectAll("path")
 * .data(data)
 * .enter().append("path")
 * .attr("transform", function(d) { 
 * 		return "translate(" + x(d.x) + "," + y(d.y) + ")"; 
 * })
 * .attr("d", d3.svg.symbol());
 * 
 * </pre>
 * 
 * In the future, we may add x- and y-accessors for parity with the line and area generators. The symbol will be centered at the origin (0,0) of the local coordinate system. You
 * can also use SVG's built-in basic shapes to produce many of these symbol types, though D3's symbol generator is useful in conjunction with path elements because you can easily
 * change the symbol type and size as a function of data.
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class Symbol extends PathDataGenerator {

	/**
	 * Constructs a new symbol generator with the default type- and size-accessor functions
	 * (that make no assumptions about input data, and produce a circle sized 64 square pixels).
	 * 
	 */
	protected Symbol() {
		super();
	}

	/**
	 * Shape of the symbol.
	 * <p>
	 * Types are normalized to have the same area in square pixels, according to the specified size. However, note that different types' sizes may be affected by the stroke and
	 * stroke width in different ways. All of the types are designed to be visible when only a fill style is used (unlike the Protovis cross), although they generally look better
	 * when both a fill and stroke is used.
	 * 
	 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
	 * 
	 */
	public static enum Type {
		/**
		 * A circle
		 */
		CIRCLE("circle"),
		/**
		 * A greek cross or plus sign
		 */
		CROSS("cross"),
		/**
		 * A rhombus
		 */
		DIAMOND("diamond"),
		/**
		 * An axis-aligned square.
		 */
		SQUARE("square"),
		/**
		 * A downward-pointing equilateral triangle.
		 */
		TRIANGLE_DOWN("triangle-down"),
		/**
		 * a upward-pointing equilateral triangle.
		 */
		TRIANGLE_UP("triangle-up");

		private final String value;

		private Type(final String value) {
			this.value = value;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
	}

	/**
	 * Set the type of the symbol using the specified {@link Type} constant.
	 * <p>
	 * The default type is {@link Type#CIRCLE}.
	 * 
	 * 
	 * 
	 * @param type
	 * @return
	 */
	public native final Symbol type(Type type)/*-{
		return this.type(type.@org.gwtd3.api.svg.Symbol.Type::getValue()());
	}-*/;

	/**
	 * Set the type of the symbol using the specified function returning a Type.
	 * <p>
	 * 
	 * @param typeAccessorFunction
	 *            the function that return the {@link Type} of symbol.
	 * @return this instance for chaining
	 */
	public native final Symbol type(DatumFunction<Type> typeAccessorFunction)/*-{
		return this
				.type(function(d, i) {
					return typeAccessorFunction.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)(this,{datum:d},i);
				});
	}-*/;

	/**
	 * Set the size of the symbols.
	 * <p>
	 * The default size is 64.
	 * 
	 * @param sizeInSquarePixels
	 *            the size in square pixels
	 * @return this instance for chaining
	 */
	public native final Symbol size(int sizeInSquarePixels)/*-{
		return this.size(sizeInSquarePixels);
	}-*/;

	/**
	 * Set the size of the symbols using the specified function returning an integer.
	 * <p>
	 * 
	 * @param sizeAccessorFunction
	 *            the function that return the {@link Type} of symbol.
	 * @return this instance for chaining
	 */
	public native final Symbol size(DatumFunction<Integer> sizeAccessorFunction)/*-{
		return this
				.size(function(d, i) {
					return sizeAccessorFunction.@org.gwtd3.api.functions.DatumFunction::apply(Lcom/google/gwt/dom/client/Element;Lorg/gwtd3/api/core/Datum;I)(this,{datum:d},i);
				});
	}-*/;

	/**
	 * Return the size in square pixels of the symbol
	 * or -1 if the size is defined as a function
	 * 
	 * @return
	 */
	public native final int size()/*-{
		var s = this.size();
		if (!!(s && s.constructor && s.call && s.apply)) {
			return -1;
		}
		return s;
	}-*/;

}
