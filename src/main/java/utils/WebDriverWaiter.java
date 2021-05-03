package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constants.WAIT_TIMEOUT;

public class WebDriverWaiter {
    public static void waitForPageLoadComplete(WebDriver driver) {
        new WebDriverWait(driver, WAIT_TIMEOUT).until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    public static void waitVisibilityOfElement(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
    }
}
