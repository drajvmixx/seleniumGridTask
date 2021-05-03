package desktop.pages;

import abstractClasses.page.AbstractPage;
import driver.SingletonDriver;
import org.openqa.selenium.WebDriver;

import static driver.SingletonDriver.getDriver;

public class LoginPage extends AbstractPage {
    private static final String pageUrl = "https://qa.health.epam.com/v1/sso/imitation/login?username=Vitali_Kastsian@epam.com&upsaId=Vitali_Kastsian@epam.com&fullName=1";

    public LoginPage(SingletonDriver.Type type) {
        super(getDriver(type));
        setPageUrl(pageUrl);
    }

    public void loginAsUser() {
        driver.navigate().to(pageUrl);
    }
}
