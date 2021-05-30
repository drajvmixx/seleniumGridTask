package com.seleniumgridtest.runner;

import com.seleniumgridtest.steps.CartTestUIChecks;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import static driver.SingletonDriver.getDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        monochrome = true,
        glue = "com.seleniumgridtest.steps",
        plugin = {"pretty", "html:report.html",
        "com.epam.reportportal.cucumber.StepReporter"})
public class RunCucumberTest {
    private static Logger logger = Logger.getLogger(CartTestUIChecks.class);

    @BeforeClass
    public static void init() {
        //PropertiesConfigurator is used to configure log4j from properties file
        PropertyConfigurator.configure("log4j.properties");
        logger.info("Log4j initialized.");
    }

    @AfterClass
    public static void quitWindows() {
        getDriver().quit();
    }
}