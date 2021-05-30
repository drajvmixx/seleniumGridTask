package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static constants.Constants.WAIT_TIMEOUT;
import static constants.Constants.WEB_URL;
import static driver.CapabilitiesHelper.getChromeOptions;

public class SingletonDriver {
    public enum Type {CHROME, FIREFOX}

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        WebDriver localDriver = driver.get();

        if (localDriver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\work\\FrameworkTemplate\\src\\main\\resources\\chromedriver.exe");
            localDriver = new ChromeDriver(getChromeOptions());
            localDriver.manage().window().maximize();
            localDriver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT, TimeUnit.SECONDS);
            localDriver.get(WEB_URL);

            driver.set(localDriver);
        }

        return localDriver;
    }

    public static boolean isPresent() {
        return (driver.get() == null);
    }

    public static void closeDriver() {
        WebDriver localDriver = driver.get();

        if (localDriver != null) {
            localDriver.quit();
        }
    }
}
