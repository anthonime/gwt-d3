/**
 * 
 */
package org.gwtd3.api.core;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class HSLColor extends JavaScriptObject {

    protected HSLColor() {}

    /**
     * @return the hue component in the range [0;360]
     */
    public native final int h()/*-{
		return this.h;
    }-*/;

    /**
     * @return the saturation component in the range [0;1]
     */
    public native final double s()/*-{
		return this.s;
    }-*/;

    /**
     * @return the lightness component in the range [0;1]
     */
    public native final double l()/*-{
		return this.l;
    }-*/;

    /**
     * Returns the equivalent color in RGB space; see d3.rgb for details on the returned object. The conversion from HSL
     * to RGB is described in CSS3 Color Module Level 3.
     * 
     * @return
     */
    public native final RGBColor rgb()/*-{
		return this.rgb();
    }-*/;

    /**
     * Converts to a RGB hexadecimal string, such as "#f7eaba".
     * 
     * @return hexa representation of the color
     */
    public final native String toHexaString()/*-{
		return this.toString();
    }-*/;

    /**
     * Returns a brighter copy of this color yith a gamme of 1.
     * 
     * @return the new copy
     */
    public native final HSLColor brighter()/*-{
		return this.brighter();
    }-*/;

    /**
     * Returns a brighter copy of this color.
     * <p>
     * The lightness channel is multiplied by 0.7 ^ -k.
     * 
     * @param k
     *            the gamma value
     * @return the new copy
     */
    public native final HSLColor brighter(int k)/*-{
		return this.brighter(k);
    }-*/;

    /**
     * Returns a darker copy of this color yith a gamme of 1.
     * 
     * @return the new copy
     */
    public native final HSLColor darker()/*-{
		return this.darker();
    }-*/;

    /**
     * Returns a darker copy of this color.
     * <p>
     * The lightness channel is multiplied by 0.7 ^ k.
     * 
     * @param k
     *            the gamma value
     * @return the new copy
     */
    public native final HSLColor darker(int k)/*-{
		return this.darker(k);
    }-*/;

}
