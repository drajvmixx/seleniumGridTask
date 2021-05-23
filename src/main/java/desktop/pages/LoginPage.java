package desktop.pages;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverWaiter;

import static constants.Constants.WEB_URL;
import static driver.SingletonDriver.getDriver;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"onetrust-accept-btn-handler\"]")
    private WebElement allowCookies;

    @FindBy(xpath = "//*[@button-label=\"Naar Kruidvat.nl\"]")
    private WebElement chooseLanguage;

    public LoginPage(SingletonDriver.Type type) {
        super(getDriver(type));
        setPageUrl(WEB_URL);
    }

    public void proceedToApp() {
        driver.navigate().to(WEB_URL);
    }

    public void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }

    public void addCertainCookies(Cookie cookie){
        driver.manage().addCookie(cookie);
    }

    public void closeAllowCookiesMessage() {
        WebDriverWaiter.waitVisibilityOfElement(driver, allowCookies);
        allowCookies.click();

        WebDriverWaiter.waitVisibilityOfElement(driver, chooseLanguage);
        chooseLanguage.click();
    }
}
