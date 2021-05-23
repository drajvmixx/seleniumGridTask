package driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesHelper {

    public static DesiredCapabilities getDesiredCapabilities(SingletonDriver.Type type) {
        DesiredCapabilities capability = null;
        if (SingletonDriver.Type.CHROME.equals(type)) {
            capability = getChromeCapabilities();
        } else if (SingletonDriver.Type.FIREFOX.equals(type)) {
            capability = getFirefoxCapabilities();
        }
        return capability;
    }

    public static DesiredCapabilities getFirefoxCapabilities() {
        System.setProperty("webdriver.gecko.driver", "C:\\work\\FrameworkTemplate\\src\\main\\resources\\geckodriver.exe");
        DesiredCapabilities capability;
        capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        return capability;
    }

    public static DesiredCapabilities getChromeCapabilities() {
        System.setProperty("webdriver.chrome.driver", "C:\\work\\FrameworkTemplate\\src\\main\\resources\\chromedriver.exe");
        DesiredCapabilities capability;
        capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        return capability;
    }
}
