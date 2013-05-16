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
public class SaturationSelector extends ComponentSelector {

	private int hue;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#createGradientStop(int, int)
	 */

	@Override
	protected Color createColor(final double value) {
		return D3.hsl(hue, value, 0.5);
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
		return "saturation:" + (int) (value * 100) + "%";
	}

	/**
	 * @param value
	 */
	public void setHue(final int value) {
		this.hue = value;
		updateGradient();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwtd3.demo.client.ui.colors.ComponentSelector#getGradientSteps()
	 */
	@Override
	protected int getGradientSteps() {
		return 1;
	}

}
