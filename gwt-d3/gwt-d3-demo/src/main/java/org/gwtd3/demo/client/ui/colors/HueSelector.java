/**
 * 
 */
package org.gwtd3.demo.client.ui.colors;

import org.gwtd3.api.D3;
import org.gwtd3.api.core.Color;

/**
 * @author <a href="mailto:schiochetanthoni@gmail.com">Anthony Schiochet</a>
 * 
 */
public class HueSelector extends ComponentSelector {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#createGradientStop(int, int)
	 */

	@Override
	protected Color createColor(final double value) {
		System.out.println("create color" + D3.hsl((int) value, 1.0, 0.0).toHexaString());
		return D3.hsl((int) value, 1.0, 0.5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#getComponentMax()
	 */
	@Override
	protected double getComponentMax() {
		return 360;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#getSelectedValueText()
	 */
	@Override
	protected String getSelectedValueText() {
		return "hue:" + (int) value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#getGradientSteps()
	 */
	@Override
	protected int getGradientSteps() {
		return 10;
	}
}
