package desktop.pages;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverWaiter;

import static constants.Constants.WEB_URL;
import static driver.SingletonDriver.getDriver;

public class MainPage extends AbstractPage {

    @FindBy(xpath = "//*[@class=\"icon icon-basket\"]")
    private WebElement cartIcon;

    public MainPage(SingletonDriver.Type type) {
        super(getDriver(type));
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clickOnCartIcon() {
        cartIcon.click();
    }

    public void navigateToMainPage() {
        driver.navigate().to(WEB_URL);
    }
}
