package org.gwtd3.api.arrays;


public interface ForEachCallback<T> {
    void forEach(T element, int index, Array<T> array);
}
