@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Sendkeys

  Background: Setting up configuration for framework user tests
    And I set configuration "WebAppUser" to value "test@test.io"
    And I set configuration "WebAppPass" to value "nothing"

  Scenario Outline: Setting up data for the scenario
    Given I add to data-store "<Name>" containing key "<Key>" and value "<Value>"

    Examples:
      | Name           | Key | Value          |
      | email-field    | id  | Input_Email    |
      | password-field | id  | Input_Password |

  Scenario:  Type into fields - Reference - Attribute-Value
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I type "admin" into field with Attribute and Value using data-store "email-field"
    And I click submit
    Then text displays "The Email field is not a valid e-mail address."


  Scenario:  Type into fields - Framework user and password - Reference - Attribute-Value
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I type FrameworkUser into field with Attribute and Value using data-store "email-field"
    When I type FrameworkUserPass into field with Attribute and Value using data-store "password-field"
    And I click submit
    Then text displays "Invalid login attempt."

  Scenario Outline: Setting up data for the scenario
    Given I add the following data-store "<Name>" "<Value>"

    Examples:
      | Name                 | Value                     |
      | email-field-xpath    | //*[@id='Input_Email']    |
      | password-field-xpath | //*[@id='Input_Password'] |

  Scenario:  Type into fields - Reference - XPATH
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I type "admin" into field with xpath using data-store "email-field-xpath"
    And I click submit
    Then text displays "The Email field is not a valid e-mail address."


  Scenario:  Type into fields - Reference - Framework user and password - XPATH
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I type FrameworkUser into field with xpath using data-store "email-field-xpath"
    When I type FrameworkUserPass into field with xpath using data-store "password-field-xpath"
    And I click submit
    Then text displays "Invalid login attempt."