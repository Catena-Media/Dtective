@UnitTest
@InternalSeleniumTest
@UnitTestTabs
Feature: Web-Driver Tabs

  @disabled-firefox
  @disabled-iPhone
  Scenario: Opening link in new tab and closing it
    Given I am viewing "{unitTestSite}/Webdriver/Links"
    When I open link by XPATH "//a[@id='internal-link']" in a new Tab
    And I switch to the newly opened tab
    Then url contains "Keytracker"
    And text displays "Press a key..."
    And I close the current Tab
    Then url contains "Links"
    And text displays "Internal link"

  @disabled-iPhone
  Scenario: Opening link that creates new tab and closing it
    Given I am viewing "{unitTestSite}/Webdriver/Links"
    When I click element with Attribute "id" and Value "newtab-link"
    And I switch to the newly opened tab
    Then url contains "w3schools"
    And I close the current Tab
    Then url contains "Links"
