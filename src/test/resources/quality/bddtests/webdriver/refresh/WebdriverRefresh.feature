@UnitTest
@InternalSeleniumTest
Feature: Web-Driver refresh

  Scenario: Simple refresh Test
    Given I Opened "{unitTestSite}/Identity/Account/Login"
    When I click element with Attribute "type" and Value "submit"
    Then text displays "The Email field is required."
    When I refreshed
    Then text does not display "The Email field is required."