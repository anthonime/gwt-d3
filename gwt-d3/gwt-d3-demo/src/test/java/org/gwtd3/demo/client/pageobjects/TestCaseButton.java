package org.gwtd3.demo.client.pageobjects;

import org.gwtd3.demo.client.D3Demo.TestButton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCaseButton extends PageObject {

    private WebElement element;

    public TestCaseButton(final WebDriver driver) {
        super(driver);
        element = findClickableById(TestButton.ID, 1);
    }

    public TestSuiteScreen click() {
        element.click();

        return new TestSuiteScreen(driver);
    }

    public void wait(final int second) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someid")));
    }
}
