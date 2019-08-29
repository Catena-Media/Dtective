@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Sendkeys

  Background: Setting up configuration for framework user tests
    And I set configuration "WebAppUser" to value "test@test.io"
    And I set configuration "WebAppPass" to value "nothing"

  Scenario:  Type into fields - Attribute-Value
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I type "admin" into Attribute "id" Value "Input_Email"
    And I click submit
    Then text displays "The Email field is not a valid e-mail address."

  Scenario:  Type into fields - Framework User and Password - Attribute-Value
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I type FrameworkUser into Attribute "id" Value "Input_Email"
    When I type FrameworkUserPass into Attribute "id" Value "Input_Password"
    And I click submit
    Then text displays "Invalid login attempt."