package org.gwtd3.demo.client.democases;

import org.gwtd3.api.D3;
import org.gwtd3.api.JsArrays;
import org.gwtd3.api.arrays.Array;
import org.gwtd3.api.arrays.ForEachCallback;
import org.gwtd3.api.core.Datum;
import org.gwtd3.api.core.NumericAccessor;
import org.gwtd3.api.core.Selection;
import org.gwtd3.api.core.Value;
import org.gwtd3.api.dsv.DsvCallback;
import org.gwtd3.api.dsv.DsvObjectAccessor;
import org.gwtd3.api.dsv.DsvRow;
import org.gwtd3.api.dsv.DsvRows;
import org.gwtd3.api.events.EventListener;
import org.gwtd3.api.functions.DatumFunction;
import org.gwtd3.api.scales.LinearScale;
import org.gwtd3.api.svg.Area;
import org.gwtd3.api.svg.Area.InterpolationMode;
import org.gwtd3.api.svg.Axis;
import org.gwtd3.api.svg.Axis.Orientation;
import org.gwtd3.api.svg.Brush;
import org.gwtd3.api.svg.Brush.BrushEvent;
import org.gwtd3.api.time.Format;
import org.gwtd3.api.time.TimeScale;
import org.gwtd3.demo.client.DemoCase;
import org.gwtd3.demo.client.Factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.FlowPanel;

public class FocusAndContext extends FlowPanel implements DemoCase {

	public interface Bundle extends ClientBundle {
		public static final Bundle INSTANCE = GWT.create(Bundle.class);

		@Source("FocusAndContextStyles.css")
		public MyResources css();
	}

	interface MyResources extends CssResource {

		String brush();

		String axis();
	}

	public FocusAndContext() {
		super();
		Bundle.INSTANCE.css().ensureInjected();
	}

	@Override
	public void start() {

		final MyResources css = Bundle.INSTANCE.css();

		final Margin margin = new Margin(10, 10, 100, 40);
		final Margin margin2 = new Margin(430, 10, 20, 40);
		final int width = 960 - margin.left - margin.right;
		final int height = 500 - margin.top - margin.bottom;
		final int height2 = 500 - margin2.top - margin2.bottom;

		final Format dateFormat = D3.time().format("%b %Y");

		final TimeScale x = D3.time().scale()
				.range(JsArrays.asJsArray(0, width));
		final TimeScale x2 = D3.time().scale()
				.range(JsArrays.asJsArray(0, width));
		final LinearScale y = D3.scale.linear()
				.range(JsArrays.asJsArray(height, 0));
		final LinearScale y2 = D3.scale.linear()
				.range(JsArrays.asJsArray(height2, 0));

		final Axis xAxis = D3.svg().axis().scale(x).orient(Orientation.BOTTOM);
		final Axis xAxis2 = D3.svg().axis().scale(x2)
				.orient(Orientation.BOTTOM);
		final Axis yAxis = D3.svg().axis().scale(y).orient(Orientation.LEFT);

		final Area area = D3.svg().area()
				.interpolate(InterpolationMode.MONOTONE)
				.x(new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Datum d, final int index) {
						return x.apply(d.<Data> as().getDate()).asDouble();
					}
				}).y0(height).y1(new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Datum d, final int index) {
						return y.apply(d.<Data> as().getPrice()).asDouble();
					}
				});

		final Area area2 = D3.svg().area()
				.interpolate(InterpolationMode.MONOTONE)
				.x(new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Datum d, final int index) {
						return x2.apply(d.<Data> as().getDate()).asDouble();
					}
				}).y0(height2).y1(new DatumFunction<Double>() {
					@Override
					public Double apply(final Element context, final Datum d, final int index) {
						return y2.apply(d.<Data> as().getPrice()).asDouble();
					}
				});

		Selection svg = D3.select(FocusAndContext.this).append("svg")
				.attr("width", width + margin.left + margin.right)
				.attr("height", height + margin.top + margin.bottom);

		svg.append("defs").append("clipPath").attr("id", "clip").append("rect")
				.attr("width", width).attr("height", height);

		final Selection focus = svg.append("g").attr("transform",
				"translate(" + margin.left + "," + margin.top + ")");

		final Selection context = svg.append("g").attr("transform",
				"translate(" + margin2.left + "," + margin2.top + ")");

		final Brush brush = D3.svg().brush().x(x2);
		brush.on(BrushEvent.BRUSH, new EventListener() {
			@Override
			public void onEvent() {
				x.domain(brush.empty() ? x2.domain() : brush.extent());
				focus.select("path").attr("d", area);
				focus.select(".x." + css.axis()).call(xAxis);
			}
		});

		D3.csv("demo-data/sp500.csv", new DsvObjectAccessor<Data>() {
			@Override
			public Data apply(final DsvRow row, final int index) {
				return new Data(dateFormat.parse(row.get("date").asString()), row.get("price").asDouble());
			}
		}, new DsvCallback<Data>() {
			@Override
			public void get(final JavaScriptObject error, final DsvRows<Data> data) {
				x.domain(D3.extent(data
						.map(new ForEachCallback<JsDate>() {
							@Override
							public JsDate forEach(final Object thisArg, final Value d, final int index,
									final Array<Value> array) {
								return d.as(Data.class).getDate();
							}
						})));
				y.domain(JsArrays.asJsArray(0, D3.max(data, new NumericAccessor<Data>() {
					@Override
					public double apply(final Data d) {
						return d.getPrice();
					}
				})));
				x2.domain(x.domain());
				y2.domain(y.domain());

				focus.append("path").datum(data)
						.attr("clip-path", "url(#clip)").attr("d", area);

				focus.append("g").attr("class", "x " + css.axis())
						.attr("transform", "translate(0," + height + ")")
						.call(xAxis);

				focus.append("g").attr("class", "y " + css.axis()).call(yAxis);

				context.append("path").datum(data).attr("d", area2);

				context.append("g").attr("class", "x " + css.axis())
						.attr("transform", "translate(0," + height2 + ")")
						.call(xAxis2);

				context.append("g").attr("class", "x " + css.brush())
						.call(brush).selectAll("rect").attr("y", -6)
						.attr("height", height2 + 7);

			}
		});

	}

	@Override
	public void stop() {
	}

	public final static native String dump(JavaScriptObject obj) /*-{
		var dump = "";
		for ( var p in obj) {
			dump += p + " : " + obj[p] + "\n";
		}
		return dump;
	}-*/;

	private static class Margin {
		public final int top;
		public final int right;
		public final int bottom;
		public final int left;

		public Margin(final int top, final int right, final int bottom, final int left) {
			super();
			this.top = top;
			this.right = right;
			this.bottom = bottom;
			this.left = left;
		}
	}

	private static class Data {
		private final JsDate date;

		private final double price;

		public Data(final JsDate date, final double price) {
			super();
			this.date = date;
			this.price = price;
		}

		public JsDate getDate() {
			return date;
		}

		public double getPrice() {
			return price;
		}
	}

	public static Factory factory() {
		return new Factory() {
			@Override
			public DemoCase newInstance() {
				return new FocusAndContext();
			}
		};
	}
}
