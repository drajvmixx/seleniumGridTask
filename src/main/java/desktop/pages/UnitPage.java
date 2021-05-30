package desktop.pages;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverWaiter;

import static driver.SingletonDriver.getDriver;

public class UnitPage extends AbstractPage {
    static Logger logger = Logger.getLogger(UnitPage.class);

    @FindBy(xpath = "//*[@data-e2e=\"unit-name\"]")
    private WebElement unitName;

    public UnitPage(SingletonDriver.Type type) {
        super(getDriver());
    }

    public String currentUnitName(){
        logger.info("Get current unit name");
        new WebDriverWaiter().waitVisibilityOfElement(driver, unitName);
        return unitName.getText();
    }
}
