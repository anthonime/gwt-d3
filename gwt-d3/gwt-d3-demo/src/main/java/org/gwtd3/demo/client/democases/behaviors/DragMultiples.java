package org.gwtd3.demo.client.democases.behaviors;

import org.gwtd3.api.Coords;
import org.gwtd3.api.D3;
import org.gwtd3.api.arrays.Array;
import org.gwtd3.api.arrays.ForEachCallback;
import org.gwtd3.api.behaviour.Drag;
import org.gwtd3.api.core.Datum;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.core.Value;
import org.gwtd3.api.functions.DatumFunction;
import org.gwtd3.demo.client.DemoCase;
import org.gwtd3.demo.client.Factory;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class DragMultiples extends FlowPanel implements DemoCase {

	public DragMultiples() {
		super();
		init();
	}

	private final int width = 238,
			height = 123,
			radius = 20;

	/**
	 * 
	 */
	private void init() {

		Drag drag = D3.behavior().drag()
				// the origin will be set with the data of svg
				// on mousedown
				.origin(D3.identity())
				.on(Drag.DragEventType.drag, new OnDragMove());

		Selection svg = D3.select(this).selectAll("svg")
				// set the data as the center of the squares
				.data(D3.range(16).map(
						new ForEachCallback<Coords>() {
							@Override
							public Coords forEach(final Object thisArg, final Value element, final int index, final Array<?> array) {
								return Coords.create(width / 2, height / 2);
							}
						})
				)
				.enter().append("svg")
				.attr("width", width)
				.attr("height", height)
				.style("float", "left")
				.style("border", "solid 1px #aaa");

		svg.append("circle")
				.attr("r", radius)
				.attr("cx", new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Datum d, final int index) {
						return d.as(Coords.class).x();
					}
				})
				.attr("cy", new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Datum d, final int index) {
						return d.as(Coords.class).y();
					}
				})
				.style("cursor", "pointer")
				// listeners are registered
				.call(drag);

	}

	private class OnDragMove implements DatumFunction<Void> {
		@Override
		public Void apply(final Element context, final Datum d, final int index) {
			Coords datum = d.as();
			// compute the new x and y using the mouse position
			// note: the mouse position has been adjusted to the drag 'origin'
			double newX = Math.max(radius, Math.min(width - radius, D3.eventAsCoords().x()));
			double newY = Math.max(radius, Math.min(height - radius, D3.eventAsCoords().y()));
			// update the datum itself, to adjust the origin
			datum.x(newX).y(newY);
			// update the position of the circle
			D3.select(context)
					.attr("cx", datum.x())
					.attr("cy", datum.y());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.DemoCase#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.DemoCase#stop()
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return
	 */
	public static Factory factory() {
		return new Factory() {
			@Override
			public DragMultiples newInstance() {
				return new DragMultiples();
			}
		};
	}

}
