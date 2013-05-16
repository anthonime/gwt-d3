/**
 * 
 */
package org.gwtd3.demo.client.ui.colors;

import org.gwtd3.api.D3;
import org.gwtd3.api.core.Color;
import org.gwtd3.api.core.Datum;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.functions.DatumFunction;
import org.gwtd3.api.svg.Symbol;
import org.gwtd3.api.svg.Symbol.Type;
import org.gwtd3.demo.client.ui.SVGD3Widget;

import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasValue;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public abstract class ComponentSelector extends SVGD3Widget implements HasValue<Double> {

	/**
	 * 
	 */
	private static final int rectangeWidth = 20;

	protected Selection rectangleSelector;
	// protected Selection text;
	// protected Selection selectedValueText;

	private int steps;
	protected Selection componentSymbol;
	private Symbol symbol;

	private boolean dragging;

	protected double value;

	private HandlerRegistration registration;

	/**
	 * 
	 */
	public ComponentSelector() {
		super();
		getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		getElement().getStyle().setProperty("userSelect", "none");
		getElement().getStyle().setProperty("webkitUserSelect", "none");
		setPixelSize(60, 300);
		translate(0, 10);
		init();
	}

	private void init() {
		symbol = D3.svg().symbol().type(Type.TRIANGLE_UP);

		// text = selection().append("div").text("number of steps:?");
		// text.on(BrowserEvents.CLICK, new DatumFunction<Void>() {
		// @Override
		// public Void apply(final Element context, final Datum d, final int index) {
		// createGradient(--steps);
		// return null;
		// }
		// });
		// selectedValueText = selection().append("div").text(getSelectedValueText());

		rectangleSelector = g().append("rect")
				.attr("x", 0)
				.attr("y", 0)
				.attr("width", ComponentSelector.rectangeWidth)
				.attr("height", 250);

		rectangleSelector.node().getStyle().setCursor(Cursor.POINTER);
		rectangleSelector.node().getStyle().setProperty("userSelect", "none");
		rectangleSelector.node().getStyle().setProperty("webkitUserSelect", "none");
		rectangleSelector.on(BrowserEvents.MOUSEDOWN, new DatumFunction<Void>() {

			@Override
			public Void apply(final Element context, final Datum d, final int index) {
				updateColorFromMouse(rectangleSelector);
				rectangleSelector.on(BrowserEvents.MOUSEMOVE, new DatumFunction<Void>() {
					@Override
					public Void apply(final Element context, final Datum d, final int index) {
						updateColorFromMouse(rectangleSelector);
						return null;
					}
				}, true);
				rectangleSelector.on(BrowserEvents.MOUSEUP, new DatumFunction<Void>() {
					@Override
					public Void apply(final Element context, final Datum d, final int index) {
						rectangleSelector.on("." + BrowserEvents.MOUSEMOVE, null);
						rectangleSelector.on("." + BrowserEvents.MOUSEDOWN, null);
						return null;
					}
				}, true);
				return null;
			}
		});

		componentSymbol = g().append("path")
				.attr("fill", "blue")
				.attr("transform", "translate(" + (Integer.parseInt(rectangleSelector.attr("width")) + 4) + "," + 0 + ") rotate(-90)")
				.attr("d", symbol);

		componentSymbol.on(BrowserEvents.MOUSEDOWN, new DatumFunction<Void>() {
			@Override
			public Void apply(final Element context, final Datum d, final int index) {
				dragging = true;
				System.out.println("capture on");
				DOM.setCapture((com.google.gwt.user.client.Element) componentSymbol.node().cast());

				return null;
			}
		});
		componentSymbol.node().getStyle().setCursor(Cursor.POINTER);
		componentSymbol.on(BrowserEvents.MOUSEDOWN, new DatumFunction<Void>() {
			@Override
			public Void apply(final Element context, final Datum d, final int index) {
				dragging = false;
				DOM.releaseCapture((com.google.gwt.user.client.Element) componentSymbol.node().cast());
				return null;
			}

		});

		componentSymbol.on(BrowserEvents.MOUSEMOVE, new DatumFunction<Void>() {
			@Override
			public Void apply(final Element context, final Datum d, final int index) {
				if (dragging) {
					int mouseY = (int) D3.mouseY(rectangleSelector.node());
					mouseY = Math.min(mouseY, Integer.parseInt(rectangleSelector.attr("height")));
					mouseY = Math.max(mouseY, 0);
					double value = yToValue(mouseY);
					setValue(value, true);
					position(mouseY);
				}
				return null;
			}
		});
		createGradient(getGradientSteps());
	}

	/**
	 * @return
	 */
	protected abstract int getGradientSteps();

	/**
	 * @return
	 */
	protected abstract String getSelectedValueText();

	/**
	 * @return
	 */
	protected double yToValue(final double y) {
		String attr = rectangleSelector.attr("height");
		int height = Integer.parseInt(attr);
		return ((getComponentMax() * y) / height);
	}

	/**
	 * @return the maximum value of the value
	 */
	protected abstract double getComponentMax();

	private void createGradient(final int steps) {
		this.steps = steps;
		// text.text("number of steps: " + steps);
		String name = this.getClass().getName();
		name = name.substring(name.lastIndexOf('.') + 1);
		GradientBuilder builder = GradientBuilder.createVerticalLinearGradient(this, name + steps);
		for (int i = 0; i <= steps; i += 1) {
			builder.appendStop(i * (100 / steps), createColor(i * (getComponentMax() / steps)));
		}

		rectangleSelector.attr("fill", builder.toFillUrl());
	}

	/**
	 * @param i
	 * @return
	 */
	protected abstract Color createColor(double value);

	private void position(final double y) {

		componentSymbol
				.attr("transform", "translate(" + (Integer.parseInt(rectangleSelector.attr("width")) + 4) + "," + y + ") rotate(-90)");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.TakesValue#getValue()
	 */
	@Override
	public Double getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.TakesValue#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(final Double value) {
		setValue(value, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object, boolean)
	 */
	@Override
	public void setValue(final Double value, final boolean fireEvents) {
		double oldValue = this.value;
		this.value = value;
		// selectedValueText.text(getSelectedValueText());
		componentSymbol.attr("fill", createColor(value.doubleValue()).toString());
		if (fireEvents) {
			ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt.event.logical.shared.ValueChangeHandler)
	 */
	@Override
	public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Double> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}

	/**
	 * 
	 */
	protected void updateGradient() {
		createGradient(steps);
	}

	private void updateColorFromMouse(final Selection selection) {
		double mouseY = D3.mouseY(selection.node());
		double value = yToValue(mouseY);
		setValue(value, true);
		position(mouseY);
	}

}
