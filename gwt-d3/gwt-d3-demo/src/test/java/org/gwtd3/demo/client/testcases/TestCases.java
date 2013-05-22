package org.gwtd3.demo.client.testcases;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.gwtd3.demo.client.pageobjects.TestCaseButton;
import org.gwtd3.demo.client.pageobjects.TestSuiteScreen;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCases {

	private static WebDriver driver;

	@BeforeClass
	public static void setup() throws MalformedURLException {
		DesiredCapabilities capabillities = DesiredCapabilities.firefox();
		//capabillities.setCapability("version", "5.0");
		capabillities.setCapability("platform", Platform.WINDOWS);
		driver = new RemoteWebDriver(
				new URL(
						"http://cb_anthonime:b7a2e206-1091-4556-99b4-6e5c6b3529d5@ondemand.saucelabs.com:80/wd/hub"),
				capabillities);
	}

	@Test
	public void basic() throws Exception {
		driver.get("http://hourly.gwt-d3.appspot.com/");
		assertThat(driver.getTitle()).isEqualTo("GWT-D3 Demo");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {

		// driver = new RemoteWebDriver("");
		// wait = new WebDriverWait(driver, 30);
		// driver.get("http://www.google.com/");
		//
		// boolean result;
		// try {
		// result = firstPageContainsQAANet();
		// } catch (Exception e) {
		// e.printStackTrace();
		// result = false;
		// } finally {
		// driver.close();
		// }
		//
		// System.out.println("Test " + (result ? "passed." : "failed."));
		// if (!result) {
		// System.exit(1);
		// }
	}

	@Test
	public void testCasesPass() {
		driver.get("http://hourly.gwt-d3.appspot.com/");
		TestSuiteScreen testSuite = new TestCaseButton(driver).click();
		testSuite.clickRun().waitTestsAreAllDone();
		assertThat(testSuite.unitTestsAreSuccess()).isTrue();
		// type search query
		// driver.findElement(By.name("q")).sendKeys("qa automation\n");
		//
		// // click search
		// driver.findElement(By.name("btnG")).click();
		//
		// // Wait for search to complete
		// wait.until(new ExpectedCondition<Boolean>() {
		// @Override
		// public Boolean apply(final WebDriver webDriver) {
		// System.out.println("Searching ...");
		// return webDriver.findElement(By.id("resultStats")) != null;
		// }
		// });

		// Look for QAAutomation.net in the results
		// return
		// driver.findElement(By.tagName("body")).getText().contains("qaautomation.net");
	}

}
