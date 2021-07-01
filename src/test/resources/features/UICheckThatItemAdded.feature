@Netherlands
Feature: REST API testing hometask

  @ToTest
  @MyTag
  Scenario: Add product via API
    Given I create session
    When I add product to cart
    Then product is added

  @ToTest
  Scenario: Authenticate to web application by adding 'kvn-cart' cookie with 'guid' value to the browser via UI
  Given I proceed to app
    And I clear all cookies before setting 'kvn-cart' cookie
    And I close Allow Cookies message
    When I add certain cookies
    Then refresh page

  @ToTest
  Scenario: Navigate to Cart page and verify that it contains expected product via UI parallel test
    Given I am at Main page
    When I click on Cart icon
    Then I check that added by API item is shown in the cart