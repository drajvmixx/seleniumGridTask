package desktop.pages;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static constants.Constants.WEB_URL;
import static driver.SingletonDriver.getDriver;

public class CartPage extends AbstractPage {
    static Logger logger = Logger.getLogger(CartPage.class);

    @FindBy(xpath = "//*[@class=\"cart-summary__text\" and contains(.,'1 product')]")
    private WebElement summaryCart;

    public CartPage(SingletonDriver.Type type) {
        super(getDriver());
        setPageUrl(WEB_URL);
    }

    public void oneProductIsVisible() {
        logger.info("Check if Product is visible");
        summaryCart.isDisplayed();
    }
}
