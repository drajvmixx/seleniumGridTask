@Regression
Feature: User proceed to certain unit via healthmap
  As a user
  I want to be able proceed to certain unit via healthmap
  So that I can specify my details with zoom control or perspective and proceed to unit

  @ToTest
  Scenario: Select Project with Overall perspective on Treemap
    Given I login as user in CHROME
    And I am redirected to a 'Main page'
    When I set zoom control to Project
    And select 'Overall' perspective
    Then 'Treemap' with certain units is shown

  @ToTest
  Scenario: parallel test
    Given I login as user in CHROME
    When I set unit name from 'Treemap'
    And click on certain unit via 'Treemap'
    Then I am redirected to certain unit with appropriate url and name