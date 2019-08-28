@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Sendkeys

  Background: Data Setup
    Given I add to data-store "Fields.Login" containing key "id" and value "Input_Email"
    And I add to data-store "Fields.Password" containing key "id" and value "Input_Password"
    And I set configuration "FrameworkUser" to value "test@test.com"
    And I set configuration "FrameworkPass" to value "nothing"

  Scenario: Using clear field
    Given I Opened "{unitTestSite}/Identity/Account/Login"
    When I click element with Attribute and Value using data-store name "Fields.Login"
    And I type "test@test.com"
    And I clear the field
    And I click submit
    Then text displays "The Email field is required."