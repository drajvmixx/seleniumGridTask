package desktop.pages;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverWaiter;

import static driver.SingletonDriver.getDriver;

public class UnitPage extends AbstractPage {
    @FindBy(xpath = "//*[@data-e2e=\"unit-name\"]")
    private WebElement unitName;

    public UnitPage(SingletonDriver.Type type) {
        super(getDriver(type));
    }

    public String currentUnitName(){
        new WebDriverWaiter().waitVisibilityOfElement(driver, unitName);
        return unitName.getText();
    }
}
