@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Dropdown

  Scenario: Dropdown Test - XPATH
    Given I am viewing "{unitTestSite}/Webdriver/DropdownBasic"
    When I select "Second" from dropdown with xpath "//select[@id='drop']"
    Then text "Second" is displayed in XPATH "//span[@id='selected']"
