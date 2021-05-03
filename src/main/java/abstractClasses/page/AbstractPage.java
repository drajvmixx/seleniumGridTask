package abstractClasses.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static driver.SingletonDriver.getDriver;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public abstract class AbstractPage {
    private String pageUrl;
    private String pageUrlPattern;
    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public String setPageUrlPattern(String pageUrlPattern) {
        return this.pageUrlPattern = pageUrlPattern;
    }

    public String getPageUrlPattern() {
        return pageUrlPattern;
    }

    public boolean checkUrl() {
        boolean result = pageUrl.equals(driver.getCurrentUrl());
        if (!result && isNotEmpty(pageUrlPattern)) {
            return driver.getCurrentUrl().matches(pageUrlPattern);
        }
        return result;
    }
}
