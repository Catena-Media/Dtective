@UnitTest
@UnitTestCSS
@InternalSeleniumTest
@WebdriverDisplays

@disabled-iPhone
Feature: Web-Driver CSS Assertions

  Background: Setup
    Given I Opened "{unitTestSite}"

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - CSS Assertions
  #
  # Computed CSS style attribute assertions
  # 1. Check the color of the button by default
  # 2. Check the color of the button when the mouse is hovering
  # ----------------------------------------------------------------------------------------------- #

  Scenario: CSS Test - Get Font Size - Attribute-Value
    Then I assert that the element with Attribute "href" Value "/Identity/Account/Register" has Font Size "14" px

  Scenario: CSS Test - Get Font Colour - Attribute-Value
    Then I assert that the element with Attribute "href" Value "/Identity/Account/Register" has Font Colour #"fff"

  Scenario Outline: CSS Test - Generic CSS Attribute Step - Attribute-Value
    Then I assert that the element with Attribute "href" Value "/Identity/Account/Register" has CSS Attribute "<cssAttribute>" with CSS Value "<cssValue>"
    Examples:
      | cssAttribute     | cssValue              |
      | color            | #fff                  |
      | font-size        | 14px                  |
      | background-color | #26a69a               |
      | color            | rgb(255, 255, 255)    |
      | background-color | rgb(38, 166, 154)     |
      | background-color | rgba(38, 166, 154, 1) |
