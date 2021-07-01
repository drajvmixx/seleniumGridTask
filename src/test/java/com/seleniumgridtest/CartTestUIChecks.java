package com.seleniumgridtest;

import builder.AddToCartRequestBuilder;
import com.google.gson.Gson;
import desktop.pages.CartPage;
import desktop.pages.LoginPage;
import desktop.pages.MainPage;
import driver.SingletonDriver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.AddToCartRequest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;

import static constants.Constants.*;
import static driver.SingletonDriver.Type.CHROME;
import static driver.SingletonDriver.getDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class CartTestUIChecks {
    static volatile Logger logger = Logger.getLogger(CartTestUIChecks.class);
    private static RequestSpecification requestSpec;
    private static ValidatableResponse response;
    private static String sessionId;

    static {
        //PropertiesConfigurator is used to configure log4j from properties file
        PropertyConfigurator.configure("./log4j.properties");
        logger.info("Log4j initialized.");
    }

    @After
    public void makeScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            String fileName = "logs/" + Instant.now().getEpochSecond() + scenario.getName() + ".png";
            logger.error("The scenario '" + scenario.getName() + "' failed; Please see the " + fileName);

            byte[] screenshotBytes = null;
            FileOutputStream fileStream = null;
            try {
                // make screenshot
                if (SingletonDriver.isPresent()) {
                    // if selenium step failed
                    WebDriver driver = getDriver();
                    screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                } else {
                    // if API step filed
                    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                    BufferedImage screenshot = new Robot().createScreenCapture(screenRect);
                    screenshotBytes = toByteArray(screenshot, "png");
                }

                // save screenshot on disk
                fileStream = new FileOutputStream(fileName);
                fileStream.write(screenshotBytes);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            } finally {
                try {
                    fileStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }

            scenario.attach(screenshotBytes, "image/png", "" + Instant.now().getEpochSecond() + ".png");
        }
    }

    // convert BufferedImage to byte[]
    private static byte[] toByteArray(BufferedImage screenshot, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenshot, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

// REST ASSURED tests

    @Given("I create session")
    public void createSessionViaAPI() {
        logger.info("Given I create session");

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(WEB_URL);
        builder.setContentType("application/json; charset=UTF-8");
        requestSpec = builder.build();

        sessionId = given().spec(requestSpec)
                .when().post(ANONYMOUS_CART_PATH)
                .then().statusCode(201)
                .extract().response().jsonPath().getString("guid");

        logger.debug("Created sessionId:" + sessionId);
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
        response.assertThat().body("quantityAdded",equalTo(1))
                .and()
                .body("entry.product.code", equalTo(PRODUCT_CODE));
    }

// Selenium tests

    @Given("I proceed to app")
    public void proceedToApp() {
        new LoginPage(CHROME).proceedToApp();
    }

    @And("I clear all cookies before setting 'kvn-cart' cookie")
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
