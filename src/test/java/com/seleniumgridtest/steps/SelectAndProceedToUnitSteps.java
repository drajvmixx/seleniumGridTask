package com.seleniumgridtest.steps;

import desktop.pages.LoginPage;
import desktop.pages.MainPage;
import desktop.pages.UnitPage;
import driver.SingletonDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static driver.SingletonDriver.Type.CHROME;

public class SelectAndProceedToUnitSteps {
    @Given("I login as user in (CHROME|FIREFOX)$")
    public void loginAsUser(SingletonDriver.Type type) {
        new LoginPage(type).loginAsUser();
    }

    @And("I am redirected to a {string}")
    public void redirectToMainPage(String page) {
        new MainPage(CHROME).waitOnHealthMap();
    }

    @When("I set zoom control to Project")
    public void setZoomControlAsProjects() {
        new MainPage(CHROME).moveZoomControl();
    }

    @And("select 'Overall' perspective")
    public void selectOverallItemFromThePerspectiveList() {
        MainPage mainPage = new MainPage(CHROME);
        mainPage.launchPrespectiveList();
        mainPage.selectOverallItem();
    }

    @When("I set unit name from 'Treemap'")
    public void setUnitNameFromTreemap() {
        new MainPage(CHROME).returnUnitNameFromTreemap();
    }

    @And("click on certain unit via 'Treemap'")
    public void proceedToCertainUnit() {
        new MainPage(CHROME).proceedToCertainUnit();
    }

    @Then("'Treemap' with certain units is shown")
    public void testTreemapIsShown() {
        Assert.assertTrue(new MainPage(CHROME).treemapIsShown());
    }

    @Then("I am redirected to certain unit with appropriate url and name")
    public void testUserIsRedirected() {
        Assert.assertEquals(new MainPage(CHROME).returnUnitNameFromTreemap(),  new UnitPage(CHROME).currentUnitName());
    }
}
