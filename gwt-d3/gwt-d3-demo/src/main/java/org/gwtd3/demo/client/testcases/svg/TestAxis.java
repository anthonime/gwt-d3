package org.gwtd3.demo.client.testcases.svg;

import org.gwtd3.api.D3;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.scales.LinearScale;
import org.gwtd3.api.svg.Axis;
import org.gwtd3.api.svg.Axis.Orientation;
import org.gwtd3.demo.client.test.AbstractTestCase;

import com.google.gwt.core.client.JavaScriptObject;

public class TestAxis extends AbstractTestCase {

    @Override
    public void doTest(final com.google.gwt.user.client.ui.ComplexPanel sandbox) {
        Axis axis = D3.svg().axis();

        // default scale
        LinearScale s = axis.scale();
        assertNotNull(s);
        int asInt = s.domain().get(0).asInt();
        assertEquals(0, asInt);
        // default orientation
        assertEquals(Orientation.BOTTOM, axis.orient());
        // set orientation
        axis.orient(Orientation.TOP);
        assertEquals(Orientation.TOP, axis.orient());

        // tick size
        axis.tickSize(6);
        axis.tickSize(6, 0);
        axis.tickSize(6, 3, 0);

        // tick subdivide
        assertEquals(0, axis.tickSubdivide());
        axis.tickSubdivide(9);
        assertEquals(9, axis.tickSubdivide());

        // ticks
        assertEquals(1, axis.ticks().length());
        axis.ticks(10);
        assertEquals(10, axis.ticks().get(0).asInt());
        JavaScriptObject tickFunction = JavaScriptObject.createFunction();
        axis.ticks(tickFunction, 15);
        assertEquals(tickFunction, axis.ticks().get(0).as());
        assertEquals(15, axis.ticks().get(1).asInt());

        // apply
        Selection svg = D3.select(sandbox).append("svg")
                .attr("width", 100)
                .attr("height", 100)
                .append("g")
                .attr("transform", "translate(32,50)");

        Axis axis2 = D3.svg().axis().apply(svg);
    }
}
