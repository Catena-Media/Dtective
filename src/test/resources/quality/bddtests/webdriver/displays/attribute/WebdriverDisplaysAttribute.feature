@UnitTest
@InternalSeleniumTest
@WebdriverDisplays
Feature: Web-Driver Displays

  Scenario: Displays Test - Find an Element using Attribute and Value
    Given I open website "{unitTestSite}"
    Then element is displayed by Attribute "href" Value "/Identity/Account/Login"

  Scenario: Displays Test - Confirm Element does not dipslay using Attribute and Value
    Given I open website "{unitTestSite}"
    Then element is not displayed by Attribute "href" Value "doesnotexist"

  Scenario: Displays Test - Confirm value of a field by Attribute and Value
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I type "test1234" into Attribute "id" Value "Input_Email"
    Then field value equals "test1234" by Attribute "id" Value "Input_Email"