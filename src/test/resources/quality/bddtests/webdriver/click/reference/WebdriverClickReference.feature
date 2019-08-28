@UnitTest
@UnitTestClick
@InternalSeleniumTest
Feature: Web-Driver Click

  Background: This test is designed to demonstrate how BDD steps can be used with parametere references

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Click
  # ----------------------------------------------------------------------------------------------- #

  Scenario Outline: Click Test (with data setup using attribute-value)

    Given I add to data-store "signUp-button" containing key "href" and value "/Identity/Account/Register"
    And I am viewing "<TargetURL>"
    When I click element with Attribute and Value using data-store name "signUp-button"
    Then url contains "<EndUrl>"

    Examples:
      | TargetURL      | EndUrl                                   |
      | {unitTestSite} | {unitTestSite}/Identity/Account/Register |

  Scenario Outline: Simple Click Test (with data setup using xpath )
    Given I add to data-store "testButton" containing key "xpath" and value "//a[@href='/Identity/Account/Register']"
    Given I am viewing "<TargetURL>"
    When I click element with XPATH using data-store name "<Button>"
    Then url contains "<EndUrl>"

    Examples:
      | TargetURL      | Button     | EndUrl                                   |
      | {unitTestSite} | testButton | {unitTestSite}/Identity/Account/Register |

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Double-Click
  # ----------------------------------------------------------------------------------------------- #

  # Test disabled due to Mozilla browser issue : https://github.com/mozilla/geckodriver/issues/1383

  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Double Click Test (with data setup using attribute-value)

    Given I add to data-store "doubleclick-button" containing key "ondblclick" and value "myFunction()"
    And I open website "{unitTestSite}/webdriver/clicks"
    When I double click element with Attribute and Value using data-store name "doubleclick-button"
    Then text displays "Hello World"

  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Double Click Test (with data setup using xpath)

    Given I add the following data-store "doubleclick-button-xpath" "//*[@ondblclick='myFunction()']"
    And I open website "{unitTestSite}/webdriver/clicks"
    When I double click element with xpath using data-store name "doubleclick-button-xpath"
    Then text displays "Hello World"

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Context-Click
  # ----------------------------------------------------------------------------------------------- #

  # Tests disabled due to Mozilla browser issue : https://github.com/mozilla/geckodriver/issues/1383

  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Context Click Test (with data setup using attribute-value)

    Given I add to data-store "contextclick-button" containing key "class" and value "container body-content"
    And I open website "{unitTestSite}/webdriver/clicks"
    When I context click element with Attribute and Value using data-store name "contextclick-button"
    Then text displays "Share On Facebook"

  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Context Click Test (with data setup using xpath)

    Given I add the following data-store "contextclick-button-xpath" "//*[@class='container body-content']"
    And  I open website "{unitTestSite}/webdriver/clicks"
    When I context click element with xpath using data-store name "contextclick-button-xpath"
    Then text displays "Share On Facebook"