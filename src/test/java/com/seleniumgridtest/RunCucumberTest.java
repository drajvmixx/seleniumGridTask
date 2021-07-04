package com.seleniumgridtest;

import io.cucumber.junit.platform.engine.Cucumber;

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

}