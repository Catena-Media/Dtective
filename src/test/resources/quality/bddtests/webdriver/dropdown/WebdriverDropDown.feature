@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Dropdown

  @disabled-iPhone
  Scenario: Dropdown Test - Example of manual drop-down test
    Given I am viewing "{unitTestSite}/Webdriver/Dropdown"
    When I click by XPATH "//input"
    And I click on text "Second"
    Then text "Second" is displayed in XPATH "//span[@id='selected']"
