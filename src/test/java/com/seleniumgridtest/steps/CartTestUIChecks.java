package com.seleniumgridtest.steps;

import builder.AddToCartRequestBuilder;
import com.google.gson.Gson;
import com.seleniumgridtest.runner.RunCucumberTest;
import desktop.pages.CartPage;
import desktop.pages.LoginPage;
import desktop.pages.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.AddToCartRequest;
import org.openqa.selenium.Cookie;

import static constants.Constants.*;
import static driver.SingletonDriver.Type.CHROME;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class CartTestUIChecks {
    private static RequestSpecification requestSpec;
    private static ValidatableResponse response;
    private static String sessionId;

// REST ASSURED tests

    @Given("I create session")
    public void createSessionViaAPI() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(WEB_URL);
        builder.setContentType("application/json; charset=UTF-8");
        requestSpec = builder.build();

        sessionId = given().spec(requestSpec)
                .when().post(ANONYMOUS_CART_PATH)
                .then().statusCode(201)
                .extract().response().jsonPath().getString("guid");
    }

    @When("I add product to cart")
    public void addProductViaAPI() {
        AddToCartRequest requestBody = new AddToCartRequestBuilder()
                .setProductCode(PRODUCT_CODE)
                .setQuantity(1)
                .build();

        RequestSpecification request = given().spec(requestSpec);
        request.pathParam("sessionId", sessionId);
        request.body(new Gson().toJson(requestBody));

        response = request.post(ADD_ITEM_TO_CART_PATH)
                .then().statusCode(200);
    }

    @Then("product is added")
    public void validateProductIsAdded() {
        response.assertThat().body("quantityAdded", equalTo(1))
                .and()
                .body("entry.product.code", equalTo(PRODUCT_CODE));
    }

// Selenium tests

    @Given("I proceed to app")
    public void proceedToApp() {
        new LoginPage(CHROME).proceedToApp();
    }

    @And("I clear all cookies before setting “kvn-cart” cookie")
    public void deleteCookies() {
        new LoginPage(CHROME).deleteAllCookies();
    }

    @When("I add certain cookies")
    public void addCookiesToLogin() {
        new LoginPage(CHROME).addCertainCookies(new Cookie("kvn-cart", sessionId));
    }

    @And("I close Allow Cookies message")
    public void closeAllowCookiesMessage() {
        new LoginPage(CHROME).closeAllowCookiesMessage();
    }

    @Then("refresh page")
    public void refreshPage() {
        new MainPage(CHROME).refreshPage();
    }

    @Given("I am at Main page")
    public void onMainPage() {
        new MainPage(CHROME).navigateToMainPage();
    }

    @When("I click on Cart icon")
    public void proceedToCart() {
        new MainPage(CHROME).clickOnCartIcon();
    }

    @Then("I check that added by API item is shown in the cart")
    public void checkItemIsAdded() {
        new CartPage(CHROME).oneProductIsVisible();
    }
}
