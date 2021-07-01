package com.seleniumgridtest.restassured;

import builder.AddToCartRequestBuilder;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import model.AddToCartRequest;
import java.util.Map;

import static constants.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CartTest {
    private static RequestSpecification requestSpec;
    private static String sessionId;

//    @BeforeClass
//    public static void init() {
//        // Building request by using requestSpecBuilder
//        RequestSpecBuilder builder = new RequestSpecBuilder();
//        builder.setBaseUri(WEB_URL);
//        builder.setContentType("application/json; charset=UTF-8");
//        requestSpec = builder.build();
//
//        sessionId = given().spec(requestSpec)
//                .when().post(ANONYMOUS_CART_PATH)
//                .then().statusCode(201)
//                .extract().response().jsonPath().getString("guid");
//    }
//
//    @Test
//    public void whenUserAddsItemToCartThenItemIsAdded() {
//        RequestSpecification request = given().spec(requestSpec);
//        request.pathParam("sessionId", sessionId);
//        request.body(ADD_ITEM_TO_CART_BODY);
//
//        request.post(ADD_ITEM_TO_CART_PATH)
//                .then().statusCode(200);
//    }
//
//    @Test
//    public void whenUserAddsItemToCartBuilderTest() {
//        AddToCartRequest requestBody = new AddToCartRequestBuilder().setProductCode(PRODUCT_CODE).setQuantity(1).build();
//
//        RequestSpecification request = given().spec(requestSpec);
//        request.pathParam("sessionId", sessionId);
//        request.body(new Gson().toJson(requestBody));
//
//        request.post(ADD_ITEM_TO_CART_PATH)
//                .then().statusCode(200)
//                .assertThat().body("quantityAdded", equalTo(1))
//                .and()
//                .body("entry.product.code", equalTo(PRODUCT_CODE));
//    }
//
//    @Test
//    public void whenUserAddsItemToCartMapTest() {
//        Map<String, Object> requestBodyMap = Map.of("quantity", Integer.valueOf(1), "product", Map.of("code", PRODUCT_CODE));
//
//        String bodyJson = new Gson().toJson(requestBodyMap);
//        AddToCartRequest bodyPOJO = new Gson().fromJson(bodyJson, AddToCartRequest.class);
//
//        RequestSpecification request = given().spec(requestSpec);
//        request.pathParam("sessionId", sessionId);
//        request.body(bodyPOJO);
//
//        request.post(ADD_ITEM_TO_CART_PATH)
//                .then()
//                .log().everything()
//                .statusCode(200)
//                .assertThat().body("quantityAdded", equalTo(1))
//                .and()
//                .body("entry.product.code", equalTo(PRODUCT_CODE));
//
//    }
//
//    @Test
//    public void whenUserAddsItemToCartSchemaTest() {
//        AddToCartRequest requestBody = new AddToCartRequestBuilder().setProductCode(PRODUCT_CODE).setQuantity(1).build();
//
//        RequestSpecification request = given().spec(requestSpec);
//        request.pathParam("sessionId", sessionId);
//        request.body(new Gson().toJson(requestBody));
//
//        request.post(ADD_ITEM_TO_CART_PATH)
//                .then()
//                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("addToCartResponse.json"));
//    }

}
