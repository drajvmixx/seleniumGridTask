package com.seleniumgridtest.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import static driver.SingletonDriver.Type.CHROME;
import static driver.SingletonDriver.getDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        monochrome = true,
        glue = "com.seleniumgridtest.steps",
        plugin = {"json:target/cucumber.json", "pretty",
                "html:target/cucumber-reports"}
)
public class RunCucumberTest {

    @AfterClass
    public static void quitWindows() {
        getDriver(CHROME).quit();
    }

}