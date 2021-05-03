package com.seleniumgridtest.runner;

import driver.SingletonDriver;
import io.cucumber.java.After;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        monochrome = true,
        dryRun = false,
        glue = "",
        plugin = { "json:target/cucumber.json", "pretty",
                "html:target/cucumber-reports" }
)
public class RunCucumberTest{

    @After
    public void afterTests() {
        SingletonDriver.closeDriver();
    }

}