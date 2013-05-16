/**
 * 
 */
package org.gwtd3.demo.client.ui.colors;

import org.gwtd3.api.core.Color;
import org.gwtd3.api.core.Selection;
import org.gwtd3.demo.client.ui.SVGD3Widget;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class GradientBuilder {

	private final SVGD3Widget widget;
	private Selection gradientSelection;

	public enum GradientType {
		LINEAR,
		RADIAL;
	}

	public enum SpreadMethod {
		pad,
		repeat,
		reflect;
	}

	// TODO: gradientTransform (rotate the gradient before applied)
	// TODO: gradientUnits
	// TODO: xlink:href (inherit from another gradient)

	private GradientBuilder(final SVGD3Widget widget) {
		super();
		this.widget = widget;
	}

	public static GradientBuilder createHorizontalLinearGradient(final SVGD3Widget widget, final String gradientId) {
		GradientBuilder builder = new GradientBuilder(widget);
		widget.defs().select("#" + gradientId).remove();
		builder.gradientSelection = widget.defs().append("linearGradient")
				.attr("id", gradientId)
				.attr("x1", "0%")
				.attr("y1", "0%")
				.attr("x2", "100%")
				.attr("y2", "0%");
		builder.gradientSelection.selectAll("stop").remove();
		return builder;
	}

	public static GradientBuilder createVerticalLinearGradient(final SVGD3Widget widget, final String gradientId) {
		GradientBuilder builder = new GradientBuilder(widget);
		widget.defs().select("#" + gradientId).remove();
		builder.gradientSelection = widget.defs()
				.append("linearGradient")
				.attr("id", gradientId)
				.attr("x1", "0%")
				.attr("y1", "0%")
				.attr("x2", "0%")
				.attr("y2", "100%");
		return builder;
	}

	public GradientBuilder appendStop(final int offsetPercent, final Color stopColor) {
		return appendStop(offsetPercent, stopColor, 1.0);
	}

	public GradientBuilder appendStop(final int offsetPercent, final Color stopColor, final double stopOpacity) {
		this.gradientSelection.append("stop")
				.attr("offset", offsetPercent + "%")
				.attr("stop-color", stopColor.toHexaString())
				.attr("stop-opacity", stopOpacity);
		return this;
	}

	public GradientBuilder setSpreadMethod(final SpreadMethod method) {
		this.gradientSelection.attr("spread-method", method.name());
		return this;
	}

	public static GradientBuilder createLinearGradient(final SVGD3Widget widget, final String gradientId) {
		GradientBuilder builder = new GradientBuilder(widget);
		widget.defs().select("#" + gradientId).remove();
		builder.gradientSelection = widget.defs().append("linearGradient")
				.attr("id", gradientId)
				.attr("x1", "0%")
				.attr("y1", "0%")
				.attr("x2", "0%")
				.attr("y2", "100%");
		builder.gradientSelection.selectAll("stop").remove();
		return builder;
	}

	/**
	 * @return
	 */
	public String toFillUrl() {
		return "url('#" + gradientSelection.attr("id") + "')";
	}

}
