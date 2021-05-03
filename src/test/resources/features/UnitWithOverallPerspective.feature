@Regression
Feature: parallel feature

  @ToTest
  Scenario: Proceed to unit Project with Overall perspective
    Given I login as user in FIREFOX
    When I set unit name from 'Treemap'
    And click on certain unit via 'Treemap'
    Then I am redirected to certain unit with appropriate url and name