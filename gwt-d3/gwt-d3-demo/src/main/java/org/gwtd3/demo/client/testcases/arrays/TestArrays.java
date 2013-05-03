package org.gwtd3.demo.client.testcases.arrays;

import org.gwtd3.api.arrays.Array;
import org.gwtd3.api.arrays.FilterCallback;
import org.gwtd3.api.arrays.ForEachCallback;
import org.gwtd3.api.arrays.MapCallback;
import org.gwtd3.demo.client.test.AbstractTestCase;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.ComplexPanel;

public class TestArrays extends AbstractTestCase {

    @Override
    public void doTest(final ComplexPanel sandbox) {
        testFilter();
        testForEach();
        testMap();
    }

	private void testFilter() {
		Array<Person> array = personArray();
        
        FilterCallback<Person> callback = new FilterCallback<Person>() {
			@Override
			public boolean filter(Person element, int index, Array<Person> array) {
				return element.getAge() < 30;
			}
		};
		Array<Person> filteredArray = array.filter(callback);
		
		assertNotNull(filteredArray);
		assertEquals(1, filteredArray.length());
		Person jane = filteredArray.get(0);
		assertEquals("Jane", jane.getName());
		assertEquals(25, jane.getAge());
	}

	private void testForEach() {
		Array<Person> array = personArray();
        
		ForEachCallback<Person> callback = new ForEachCallback<Person>() {
			@Override
			public void forEach(Person element, int index, Array<Person> array) {
				element.setAge(element.getAge() + 1);
			}
		};
		array.forEach(callback);
		
		Person jane = array.get(0);
		Person peter = array.get(1);
		Person sam = array.get(2);
		
		assertEquals(26, jane.getAge());
		assertEquals(37, peter.getAge());
		assertEquals(54, sam.getAge());
	}

	private void testMap() {
		Array<Person> array = personArray();
        
		MapCallback<Person, String> callback = new MapCallback<Person, String>() {
			@Override
			public String map(Person element, int index, Array<Person> array) {
				return element.getName();
			}
		};
		Array<String> mappedArray = array.map(callback);
		
		String jane = mappedArray.get(0);
		String peter = mappedArray.get(1);
		String sam = mappedArray.get(2);
		
		assertEquals("Jane", jane);
		assertEquals("Peter", peter);
		assertEquals("Sam", sam);
	}

	private static final native Array<Person> personArray() /*-{
		var jane = { name: 'Jane', age: 25 };
		var peter = { name: 'Peter', age: 36 };
		var sam = { name: 'Sam', age: 53 };
		return [ jane, peter, sam ];
	}-*/;

}


class Person extends JavaScriptObject {
    protected Person() {
    	super();
    }

    public final native void setName(String name) /*-{
        this.name = name;
    }-*/;

    public final native String getName() /*-{
        return this.name;
    }-*/;

    public final native void setAge(int age) /*-{
        this.age = age;
    }-*/;

    public final native int getAge() /*-{
        return this.age;
    }-*/;
}
