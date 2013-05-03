/**
 * 
 */
package org.gwtd3.demo.client.democases;

import org.gwtd3.api.JsArrays;
import org.gwtd3.api.core.Datum;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.functions.DatumFunction;
import org.gwtd3.demo.client.DemoCase;
import org.gwtd3.demo.client.Factory;
import org.gwtd3.demo.client.ui.SVGD3Widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class StupidExample2 implements DemoCase {

    private final SVGD3Widget widget;

    /**
	 * 
	 */
    public StupidExample2() {
        widget = new SVGD3Widget(960, 800, 40, 0);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
        // TODO Auto-generated method stub
        return widget;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gwtd3.demo.client.DemoCase#start()
     */
    @Override
    public void start() {
        Selection svg = widget.g();

        svg.selectAll("circle").data(JsArrays.asJsArray(32, 57, 112, 293))
                .enter()
                .append("circle")
                .attr("cy", 90)
                .attr("cx", new DatumFunction<Integer>() {
                    @Override
                    public Integer apply(final Element context, final Datum d, final int index) {
                        return d.asInt();
                    }
                })// String
                .attr("r", new DatumFunction<Double>() {
                    @Override
                    public Double apply(final Element context, final Datum d, final int index) {
                        return Math.sqrt(d.asDouble());
                    }
                });// sqrt

        svg.selectAll("circle").data(JsArrays.asJsArray(12, 32, 44))
                .enter().append("circle")
                .attr("cy", 90)
                .attr("cx", new DatumFunction<Integer>() {
                    @Override
                    public Integer apply(final Element context, final Datum d, final int index) {
                        return d.asInt();
                    }
                })// String
                .attr("r", new DatumFunction<Double>() {
                    @Override
                    public Double apply(final Element context, final Datum d, final int index) {
                        return Math.sqrt(d.asDouble());
                    }
                });// sqrt
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gwtd3.demo.client.DemoCase#stop()
     */
    @Override
    public void stop() {}

    public static Factory factory() {
        return new Factory() {
            @Override
            public DemoCase newInstance() {
                return new StupidExample2();
            }
        };
    }

}
