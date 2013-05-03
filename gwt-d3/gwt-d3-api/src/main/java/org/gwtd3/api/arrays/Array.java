package org.gwtd3.api.arrays;

import com.google.gwt.core.client.JavaScriptObject;

public class Array<T> extends JavaScriptObject {
	protected Array() {
		super();
	}
    
    public native final T get(int i) /*-{
        return this[i];
    }-*/;
    
    public native final int length() /*-{
        return this.length;
    }-*/;
    
    public native final Array<T> filter(FilterCallback<T> callback) /*-{
        return this.filter(function (element, index, array) {
            return callback.@org.gwtd3.api.arrays.FilterCallback::filter(Ljava/lang/Object;ILorg/gwtd3/api/arrays/Array;)(element, index, array);
        });
    }-*/;
    
    public native final Array<T> forEach(ForEachCallback<T> callback) /*-{
        return this.forEach(function (element, index, array) {
            callback.@org.gwtd3.api.arrays.ForEachCallback::forEach(Ljava/lang/Object;ILorg/gwtd3/api/arrays/Array;)(element, index, array);
        });
    }-*/;
    
    public native final <R> Array<R> map(MapCallback<T, R> callback) /*-{
        return this.map(function (element, index, array) {
            return callback.@org.gwtd3.api.arrays.MapCallback::map(Ljava/lang/Object;ILorg/gwtd3/api/arrays/Array;)(element, index, array);
        });
    }-*/;
    
    public native final <R> Array<R> map(MapNumbersCallback<R> callback) /*-{
        return this.map(function (element, index, array) {
            return callback.@org.gwtd3.api.arrays.MapNumbersCallback::map(DILorg/gwtd3/api/arrays/Array;)(element, index, array);
        });
    }-*/;
    
    public native final void push(T item) /*-{
        this.push(item);
    }-*/;

    /**
     * @param data
     * @return
     */
    public static <T> Array<T> create(final Iterable<T> array) {
        Array<T> dest = JavaScriptObject.createArray().cast();
        for (T t : array) {
            dest.push(t);
		}
        return dest;
    }
}
