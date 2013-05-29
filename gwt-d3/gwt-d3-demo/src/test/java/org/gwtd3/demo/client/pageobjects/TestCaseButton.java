package org.gwtd3.demo.client.pageobjects;

import org.gwtd3.demo.client.D3Demo.TestButton;

public class TestCaseButton extends NavigationButton<TestSuiteScreen, DemoApplication> {

	protected TestCaseButton(DemoApplication app) {
		super(app, TestButton.ID);
	}

	@Override
	protected TestSuiteScreen navigateToPageObject() {
		return new TestSuiteScreen(getParent());
	}

}
