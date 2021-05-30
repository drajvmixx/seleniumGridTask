package desktop.pages;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverWaiter;

import static constants.Constants.WEB_URL;
import static driver.SingletonDriver.getDriver;

public class MainPage extends AbstractPage {
    static Logger logger = Logger.getLogger(MainPage.class);

    @FindBy(xpath = "//*[@class=\"icon icon-basket\"]")
    private WebElement cartIcon;

    public MainPage(SingletonDriver.Type type) {
        super(getDriver());
    }

    public void refreshPage() {
        logger.info("Refresh page");
        driver.navigate().refresh();
    }

    public void clickOnCartIcon() {
        logger.info("Click on cart icon");
        cartIcon.click();
    }

    public void navigateToMainPage() {
        logger.info("Navigate to: " + WEB_URL);
        driver.navigate().to(WEB_URL);
    }
}
