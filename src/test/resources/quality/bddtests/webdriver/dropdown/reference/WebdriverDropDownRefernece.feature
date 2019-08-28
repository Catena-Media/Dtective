@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Dropdown

  Scenario Outline: Setting up data for the scenario
    Given I add the following data-store "<Name>" "<Value>"

    Examples:
      | Name         | Value                |
      | select-xpath | //select[@id='drop'] |

  Scenario: Dropdown Test - Parameter Reference
    Given I am viewing "{unitTestSite}/Webdriver/DropdownBasic"
    When I select "Second" from dropdown with xpath using data-store "select-xpath"
    Then text "Second" is displayed in XPATH "//span[@id='selected']"

  Scenario Outline: Setting up data for the scenario
    Given I add to data-store "<Name>" containing key "<Key>" and value "<Value>"

    Examples:
      | Name             | Key | Value |
      | select-attribute | id  | drop  |

  Scenario: Dropdown Test - Parameter Reference 2
    Given I am viewing "{unitTestSite}/Webdriver/DropdownBasic"
    When I select "Third" from dropdown with Attribute and Value using data-store "select-attribute"
    Then text "Third" is displayed in XPATH "//span[@id='selected']"
