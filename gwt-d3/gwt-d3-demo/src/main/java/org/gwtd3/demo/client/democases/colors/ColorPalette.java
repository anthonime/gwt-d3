/**
 * 
 */
package org.gwtd3.demo.client.democases.colors;

import org.gwtd3.demo.client.DemoCase;
import org.gwtd3.demo.client.Factory;
import org.gwtd3.demo.client.ui.colors.ComponentSelector;
import org.gwtd3.demo.client.ui.colors.HueSelector;
import org.gwtd3.demo.client.ui.colors.LightnessSelector;
import org.gwtd3.demo.client.ui.colors.SaturationSelector;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class ColorPalette extends FlowPanel implements DemoCase {

	/**
	 * 
	 */
	public ColorPalette() {
		super();
		init();
	}

	private void init() {
		ComponentSelector hue = new HueSelector();
		add(hue);
		final SaturationSelector saturation = new SaturationSelector();
		add(saturation);
		final LightnessSelector lightness = new LightnessSelector();
		add(lightness);

		hue.addValueChangeHandler(new ValueChangeHandler<Double>() {
			@Override
			public void onValueChange(final ValueChangeEvent<Double> event) {
				saturation.setHue(event.getValue().intValue());
				lightness.setHue(event.getValue().intValue());
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.DemoCase#start()
	 */
	@Override
	public void start() {
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
			public DemoCase newInstance() {
				return new ColorPalette();
			}
		};
	}

}
