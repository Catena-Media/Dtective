@UnitTest
@InternalSeleniumTest
@WebdriverDisplays
Feature: Web-Driver Displays

  Scenario: Displays Test - Find an Element using xpath
    Given I open website "{unitTestSite}"
    Then element is displayed by xpath "//*[@href='/Identity/Account/Login']"

  Scenario: Displays Test - Confirm Element does not display
    Given I open website "{unitTestSite}"
    Then element is not displayed by xpath "//*[@href='asdasdasd']"

  Scenario: Displays Test - Confirm value of a field - Xpath
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I type "test1234" into Attribute "id" Value "Input_Email"
    Then field value equals "test1234" by XPATH "//*[@id='Input_Email']"