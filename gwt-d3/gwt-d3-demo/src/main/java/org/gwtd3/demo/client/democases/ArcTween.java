/**
 * 
 */
package org.gwtd3.demo.client.democases;

import org.gwtd3.api.D3;
import org.gwtd3.api.core.Datum;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.core.Transition;
import org.gwtd3.api.core.Value;
import org.gwtd3.api.interpolators.CallableInterpolator;
import org.gwtd3.api.interpolators.Interpolator;
import org.gwtd3.api.svg.Arc;
import org.gwtd3.api.tweens.TweenFunction;
import org.gwtd3.demo.client.DemoCase;
import org.gwtd3.demo.client.Factory;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Original demo is <a href="http://bl.ocks.org/mbostock/3808218">here</a>
 * 
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class ArcTween extends FlowPanel implements DemoCase {

	private Timer timer;
	private Selection svg;
	private Arc arc;

	/**
	 * 
	 */
	public ArcTween() {
		super();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.D3Demo#start()
	 */

	@Override
	public void start() {
		int width = 960, height = 500;

		final double TWO_PI = 2 * Math.PI; // http://tauday.com/tau-manifesto

		// the arc function defines constants for inner and outer radius, and start angle
		// but the end angle is deliberately kept undefined
		arc = D3.svg().arc()
				.innerRadius(180)
				.outerRadius(240)
				.startAngle(0);

		// Create the SVG container, and apply a transform such that the origin
		// is the center of the canvas. This way, we don't need to position
		// arcs individually.
		svg = D3.select(this)
				.append("svg")
				.attr("width", width)
				.attr("height", height).append("g")
				.attr("transform",
						"translate(" + (width / 2) + "," + (height / 2) + ")");

		// construct a a stupid object containing the
		// property "endAngle" as a constant.
		Arc json = Arc.constantArc().endAngle(TWO_PI);
		// Add the background arc, from 0 to 100%
		// Here, the path d attribute is filled using the arc function,
		// which will received in parameter the object passed to datum.
		// This function will used the default accessors of the Arc objects,
		// those accessors assuming that the data passed contains
		// attributes named as the accessors.
		svg.append("path")
				// pass a data representing a constant endAngle arc
				.datum(json)
				.style("fill", "#ddd")
				.attr("d", arc);

		// set the angle to 12.7%
		json.endAngle(.127 * TWO_PI);
		// Add the foreground arc in orange, currently showing 12.7%.
		final Selection foreground = svg.append("path")
				.datum(json)
				.style("fill", "orange")
				.attr("d", arc);

		// Every so often, start a transition to a new random angle. Use //
		// transition.call // (identical to selection.call) so that we can
		// encapsulate the logic // for // tweening the arc in a separate
		// function
		// below.

		timer = new Timer() {

			@Override
			public void run() {
				Transition transition =
						foreground
								.transition()
								.duration(750);
				myFunction(transition, Math.random() * TWO_PI);
			}
		};
		timer.scheduleRepeating(1500);
	}

	public static interface TransitionFunction {
		public void apply(Transition t, Object... objects);
	}

	/**
	 * @param transition
	 * @param d
	 */
	protected void myFunction(final Transition transition, final double newAngle) {
		transition.attrTween("d", new TweenFunction<String>() {
			@Override
			public Interpolator<String> apply(final Element context, final Datum datum, final int index,
					final Value currentAttributeValue) {
				final Arc arcDatum = datum.as();
				try {
					double endAngle = arcDatum.endAngle();
					final Interpolator<Double> interpolator = D3.interpolate(endAngle, newAngle);
					CallableInterpolator<String> interp = new CallableInterpolator<String>() {
						@Override
						public String interpolate(final double t) {
							double interpolated = interpolator.interpolate(t);
							arcDatum.endAngle(interpolated);
							return arc.apply(arcDatum);
						}
					};
					return interp;
				}
				catch (Exception e) {
					GWT.log("pas cool", e);
					throw new IllegalStateException("bug", e);
				}
			}
		});

		// transition.attrTween("d", "blah");
	}

	@Override
	public void stop() {
		arc = null;
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	public static Factory factory() {
		return new Factory() {
			@Override
			public DemoCase newInstance() {
				return new ArcTween();
			}
		};
	}
}
