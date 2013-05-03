package org.gwtd3.demo.client.testcases.tsv;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import org.gwtd3.api.D3;
import org.gwtd3.api.dsv.DsvArrayAccessor;
import org.gwtd3.api.dsv.DsvCallback;
import org.gwtd3.api.dsv.DsvObjectAccessor;
import org.gwtd3.api.dsv.DsvRow;
import org.gwtd3.api.dsv.DsvRows;
import org.gwtd3.demo.client.test.AbstractTestCase;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.ui.ComplexPanel;

public class TestTsv extends AbstractTestCase {

    @Override
    public void doTest(final ComplexPanel sandbox) {
        testTsvParse();
        testTsvParseWithAccessor();
        testTsvParseRows();
        testTsvParseRowsWithAccessor();
        testTsvWithAccessorAndCallback();
        testTsvWithCallback();
        testTsvWithChainingAccessorAndCallback();
    }

    private void testTsvParse() {
        DsvRows<DsvRow> rows = D3.<DsvRow> tsv().parse( //
                "Name\tAge\n" + //
                        "Paul\t25\n" + //
                        "John\t38\n" + //
                        "Jane\t15\n" + //
                        "Bruce\t48\n" + //
                        "Emma\t28\n");
        assertEquals(5, rows.length());
        DsvRow jane = rows.get(2);
        assertEquals("Jane", jane.get("Name").asString());
        assertEquals(15, jane.get("Age").asInt());
    }

    private void testTsvParseWithAccessor() {
        DsvRows<Person> rows = D3.<Person> tsv().parse( //
                "Name\tAge\n" + //
                        "Paul\t25\n" + //
                        "John\t38\n" + //
                        "Jane\t15\n" + //
                        "Bruce\t48\n" + //
                        "Emma\t28\n", new PersonAccessor());
        assertEquals(5, rows.length());
        Person jane = rows.get(2);
        assertEquals("Jane", jane.getName());
        assertEquals(15, jane.getAge());
    }

    private void testTsvParseRows() {
        DsvRows<JsArrayString> rows = D3.tsv().parseRows( //
                "Paul\t25\n" + //
                        "John\t38\n" + //
                        "Jane\t15\n" + //
                        "Bruce\t48\n" + //
                        "Emma\t28\n");
        assertEquals(5, rows.length());
        JsArrayString jane = rows.get(2);
        assertEquals("Jane", jane.get(0));
        assertEquals("15", jane.get(1));
    }

    private void testTsvParseRowsWithAccessor() {
        DsvRows<Person> rows = D3.<Person> tsv().parseRows( //
                "Paul\t25\n" + //
                        "John\t38\n" + //
                        "Jane\t15\n" + //
                        "Bruce\t48\n" + //
                        "Emma\t28\n", new PersonArrayAccessor());
        assertEquals(5, rows.length());
        Person jane = rows.get(2);
        assertEquals("Jane", jane.getName());
        assertEquals(15, jane.getAge());
    }

    private void testTsvWithAccessorAndCallback() {
        // FIXME : we are not really sure if accessor and callback are actually called
        PersonAccessor accessor = new PersonAccessor();
        PersonCallback callback = new PersonCallback();
        D3.tsv("test-data/test.tsv", accessor, callback);
    }

    private void testTsvWithCallback() {
        // FIXME : we are not really sure if callback is actually called
        PersonRowCallback callback = new PersonRowCallback();
        D3.tsv("test-data/test.tsv", callback);
    }

    private void testTsvWithChainingAccessorAndCallback() {
        // FIXME : we are not really sure if accessor and callback are actually called
        PersonAccessor accessor = new PersonAccessor();
        PersonCallback callback = new PersonCallback();
        D3.<Person> tsv("test-data/test.tsv").row(accessor).get(callback);
    }
}

class Person {
    private final String name;

    private final int age;

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}

class PersonAccessor implements DsvObjectAccessor<Person> {
    @Override
    public Person apply(final DsvRow row, final int index) {
        return new Person(row.get("Name").asString(), row.get("Age").asInt());
    }
}

class PersonCallback implements DsvCallback<Person> {
    @Override
    public void get(final JavaScriptObject error, final DsvRows<Person> rows) {
        assertNull(error);
        assertEquals(5, rows.length());
        Person jane = rows.get(2);
        assertEquals("Jane", jane.getName());
        assertEquals(15, jane.getAge());
    }
}

class PersonArrayAccessor implements DsvArrayAccessor<Person> {
    @Override
    public Person parse(final JsArrayString row, final int index) {
        return new Person(row.get(0), Integer.parseInt(row.get(1)));
    }
}

class PersonRowCallback implements DsvCallback<DsvRow> {
    @Override
    public void get(final JavaScriptObject error, final DsvRows<DsvRow> rows) {
        assertNull(error);
        assertEquals(5, rows.length());
        DsvRow jane = rows.get(2);
        assertEquals("Jane", jane.get("Name").asString());
        assertEquals(15, jane.get("Age").asInt());
    }
}