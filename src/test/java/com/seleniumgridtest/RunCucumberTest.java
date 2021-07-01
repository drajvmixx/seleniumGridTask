package com.seleniumgridtest;

import io.cucumber.junit.platform.engine.Cucumber;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.runner.RunWith;

/*
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        monochrome = true,
        glue = "com.seleniumgridtest.steps",
        plugin = {"pretty", "html:report.html",
        "com.epam.reportportal.cucumber.StepReporter"},
        tags = "@ToTest"
)*/
@Cucumber
public class RunCucumberTest {
    //    private static Logger logger = Logger.getLogger(CartTestUIChecks.class);
   /* @BeforeClass
    public static void init() {
        //PropertiesConfigurator is used to configure log4j from properties file
        PropertyConfigurator.configure("./log4j.properties");
        logger.info("Log4j initialized.");
    }

    @AfterClass
    public static void quitWindows() {
        getDriver().quit();
    }*/
}