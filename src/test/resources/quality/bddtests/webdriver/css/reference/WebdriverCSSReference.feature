@UnitTest
@UnitTestCSS
@InternalSeleniumTest
@WebdriverDisplays

@disabled-iPhone
Feature: Web-Driver CSS Assertions

  # ----------------------------------------------------------------------------------------------- #
  # Test Data Setup
  # ----------------------------------------------------------------------------------------------- #

  Background: Setup
    Given I Opened "{unitTestSite}"

  Scenario Outline: Setting up data for the scenario
    Given I add to data-store "<Name>" containing key "<Key>" and value "<Value>"

    Examples:
      | Name          | Key  | Value                      |
      | signUp-button | href | /Identity/Account/Register |

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Propery and Value
  # ----------------------------------------------------------------------------------------------- #

  Scenario: CSS Test - Get Background Colour - Reference
    Then I assert that the element with Attribute and Value using data-store name "signUp-button" has background colour #"26a69a"

  Scenario: CSS Test - Get Font Size - Reference
    Then I assert that the element with Attribute and Value using data-store name "signUp-button" has Font Size "14" px

  Scenario: CSS Test - Get Font Colour - Reference
    Then I assert that the element with Attribute and Value using data-store name "signUp-button" has Font Colour #"fff"

  Scenario Outline: CSS Test - Generic CSS Attribute Step - Reference
    Then I assert that the element with Attribute and Value using data-store name "<param>" has CSS Attribute "<cssAttribute>" with CSS Value "<cssValue>"
    Examples:
      | param         | cssAttribute     | cssValue              |
      | signUp-button | color            | #fff                  |
      | signUp-button | font-size        | 14px                  |
      | signUp-button | background-color | #26a69a               |
      | signUp-button | color            | rgb(255, 255, 255)    |
      | signUp-button | background-color | rgb(38, 166, 154)     |
      | signUp-button | background-color | rgba(38, 166, 154, 1) |

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Xpath
  # ----------------------------------------------------------------------------------------------- #

  Scenario Outline: Setting up data for the scenario
    Given I add the following data-store "<Name>" "<Value>"

    Examples:
      | Name       | Value                                   |
      | testButton | //a[@href='/Identity/Account/Register'] |

  Scenario: Get Background Colour
    Then I assert that the element with xpath using data-store name "testButton" has background colour #"26a69a"

  Scenario: Get Font Size
    Then I assert that the element with xpath using data-store name "testButton" has Font Size "14" px

  Scenario: Get Font Colour
    Then I assert that the element with xpath using data-store name "testButton" has Font Colour #"fff"

  Scenario Outline: Generic CSS Attribute Step
    Then I assert that the element with xpath using data-store name "<param>" has CSS Attribute "<cssAttribute>" with CSS Value "<cssValue>"
    Examples:
      | param      | cssAttribute     | cssValue              |
      | testButton | color            | #fff                  |
      | testButton | font-size        | 14px                  |
      | testButton | background-color | #26a69a               |
      | testButton | color            | rgb(255, 255, 255)    |
      | testButton | background-color | rgb(38, 166, 154)     |
      | testButton | background-color | rgba(38, 166, 154, 1) |
