package org.gwtd3.demo.client.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    protected WebDriver driver;

    public PageObject(final WebDriver driver) {
        super();
        this.driver = driver;
    }

    protected WebElement find(final By by) {
        return driver.findElement(by);
    }

    protected WebElement findClickable(final By by, final int waitSec) {
        WebDriverWait w = new WebDriverWait(driver, waitSec);
        w.until(ExpectedConditions.elementToBeClickable(by));
        return driver.findElement(by);
    }

    protected WebElement findById(final String id) {
        return find(By.id(id));
    }

    protected WebElement findClickableById(final String id, final int waitSec) {
        return findClickable(By.id(id), waitSec);
    }

}
