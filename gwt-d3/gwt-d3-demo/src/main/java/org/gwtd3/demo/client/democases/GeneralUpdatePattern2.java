/**
 * 
 */
package org.gwtd3.demo.client.democases;

import java.util.Arrays;

import org.gwtd3.api.D3;
import org.gwtd3.api.JsArrays;
import org.gwtd3.api.core.Datum;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.core.UpdatingSelection;
import org.gwtd3.api.functions.DatumFunction;
import org.gwtd3.demo.client.DemoCase;
import org.gwtd3.demo.client.Factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class GeneralUpdatePattern2 extends FlowPanel implements DemoCase {

    private Timer timer;
    private Selection svg;

    public interface Bundle extends ClientBundle {
        public static final Bundle INSTANCE = GWT.create(Bundle.class);

        @Source("GeneralUpdatePattern2Styles.css")
        public MyResources css();
    }

    interface MyResources extends CssResource {
        String update();

        String enter();
    }

    /**
	 * 
	 */
    public GeneralUpdatePattern2() {
        super();

        Bundle.INSTANCE.css().ensureInjected();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gwtd3.demo.client.D3Demo#start()
     */
    @Override
    public void start() {
        String source = "abcdefghijklmnopqrstuvwxyz";
        final char[] alphabet = new char[source.length()];
        source.getChars(0, source.length(), alphabet, 0);

        int width = 960, height = 500;

        svg = D3.select(this).append("svg")
                .attr("width", width)
                .attr("height", height)
                .append("g")
                .attr("transform", "translate(32," + (height / 2) + ")");

        // The initial display.
        update(alphabet);

        timer = new Timer() {
            @Override
            public void run() {
                // Grab a random sample of letters from the alphabet, in
                // alphabetical order.
                D3.shuffle(alphabet);
                char[] range = new char[(int) Math.floor(Math.random() * 26)];
                System.arraycopy(alphabet, 0, range, 0, range.length);
                Arrays.sort(range);
                update(range);
            }
        };
        timer.scheduleRepeating(1500);
    }

    public void update(final char[] data) {

        // DATA JOIN
        // Join new data with old elements, if any.
        UpdatingSelection selection = svg.selectAll("text")
                .data(JsArrays.asJsArray(data), new DatumFunction<Integer>() {
                    @Override
                    public Integer apply(final Element context, final Datum datum, final int index) {
                        return datum.asInt();
                    }
                });

        // UPDATE
        // Update old elements as needed.
        selection.attr("class", Bundle.INSTANCE.css().update());

        // ENTER
        // Create new elements as needed.
        selection.enter().append("text")
                .attr("class", Bundle.INSTANCE.css().enter())
                .attr("dy", ".35em")
                .text(new DatumFunction<Character>() {
                    @Override
                    public Character apply(final Element context, final Datum datum, final int index) {
                        return datum.asChar();
                    }
                });

        // ENTER + UPDATE
        // Appending to the enter selection expands the update selection to
        // include
        // entering elements; so, operations on the update selection after
        // appending to
        // the enter selection will apply to both entering and updating nodes.
        selection.attr("x", new DatumFunction<Integer>() {
            @Override
            public Integer apply(final Element context, final Datum datum, final int index) {
                return index * 32;
            }
        });

        // EXIT
        // Remove old elements as needed.
        selection.exit().remove();
    }

    @Override
    public void stop() {
        timer.cancel();
        timer = null;
    }

    public static Factory factory() {
        return new Factory() {
            @Override
            public DemoCase newInstance() {
                return new GeneralUpdatePattern2();
            }
        };
    }
}
