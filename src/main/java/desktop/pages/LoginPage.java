package desktop.pages;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverWaiter;

import static constants.Constants.WEB_URL;
import static driver.SingletonDriver.getDriver;

public class LoginPage extends AbstractPage {
    static Logger logger = Logger.getLogger(LoginPage.class);

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    private WebElement allowCookies;

    @FindBy(xpath = "//*[@button-label=\"Naar Kruidvat.nl\"]")
    private WebElement chooseLanguage;

    public LoginPage(SingletonDriver.Type type) {
        super(getDriver());
        setPageUrl(WEB_URL);
    }

    public void proceedToApp() {
        logger.info("Go to: " + WEB_URL);
        driver.navigate().to(WEB_URL);
    }

    public void deleteAllCookies(){
        logger.info("Delete cookies");
        driver.manage().deleteAllCookies();
    }

    public void addCertainCookies(Cookie cookie){
        logger.info("Add cookies");
        driver.manage().addCookie(cookie);
    }

    public void closeAllowCookiesMessage() {
        logger.info("Close Allow cookies window");
        WebDriverWaiter.waitVisibilityOfElement(driver, allowCookies);
        allowCookies.click();

        logger.info("Close language window");
        WebDriverWaiter.waitVisibilityOfElement(driver, chooseLanguage);
        chooseLanguage.click();
    }
}
