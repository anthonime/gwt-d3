package org.gwtd3.api.core;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

/**
 * An array-like structure containing several values.
 * 
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class Values extends JavaScriptObject {

    protected Values() {
        super();
    }

    public final native JsArrayNumber asJsArrayNumber()/*-{
		return this;
    }-*/;

    public final native <T extends JavaScriptObject> JsArray<T> asJsArray()/*-{
		return this;
    }-*/;

    public final native JsArrayInteger asJsArrayInteger()/*-{
		return this;
    }-*/;

    public final native JsArrayString asJsArrayString()/*-{
		return this;
    }-*/;

    /**
     * Return the element at the specified index of this domain.
     * 
     * @param index
     * @return
     */
    public final native Value get(int index)/*-{
		return {
			datum : this[index]
		};
    }-*/;

    /**
     * @return the number of elements in this domain
     */
    public final native int length()/*-{
		return this.length;
    }-*/;
}
