package driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

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

    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
//        System.setProperty("webdriver.chrome.logfile", "D:\\chromedriver.log");
//        System.setProperty("webdriver.chrome.verboseLogging", "true");
        System.setProperty("webdriver.chrome.driver", "C:\\work\\FrameworkTemplate\\src\\main\\resources\\chromedriver.exe");
        return chromeOptions;
    }
}
