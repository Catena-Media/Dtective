@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Dropdown

  Scenario: Dropdown Test - Attribute-Value
    Given I am viewing "{unitTestSite}/Webdriver/DropdownBasic"
    When I select "Second" from dropdown with Attribute "id" Value "drop"
    Then text "Second" is displayed in XPATH "//span[@id='selected']"
