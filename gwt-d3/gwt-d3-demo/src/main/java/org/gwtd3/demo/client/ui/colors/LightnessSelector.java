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
public class LightnessSelector extends ComponentSelector {

	private int hue;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#createGradientStop(int, int)
	 */

	@Override
	protected Color createColor(final double value) {
		return D3.hsl(hue, 1.0, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#getComponentMax()
	 */
	@Override
	protected double getComponentMax() {
		return 1.0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#getSelectedValueText()
	 */
	@Override
	protected String getSelectedValueText() {
		return "lightness:" + (int) (value * 100) + "%";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#getGradientSteps()
	 */
	@Override
	protected int getGradientSteps() {
		return 2;
	}

	/**
	 * @param value
	 */
	public void setHue(final int value) {
		this.hue = value;
		updateGradient();
	}
}
