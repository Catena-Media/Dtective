@UnitTest
@InternalSeleniumTest
@WebdriverDisplays
Feature: Web-Driver Displays

  Scenario: Displays Test - Find text
    Given I open website "{unitTestSite}"
    Then text displays "Welcome to QA Test Website"

  Scenario: Displays Test - Confirm text does not display
    Given I open website "{unitTestSite}"
    Then text does not display "asdasdasdasd"