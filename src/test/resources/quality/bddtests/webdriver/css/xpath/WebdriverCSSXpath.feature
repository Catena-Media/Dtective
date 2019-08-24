@UnitTest
@UnitTestCSS
@InternalSeleniumTest
@WebdriverDisplays

@disabled-iPhone
Feature: Web-Driver CSS Assertions

  Background: Setup
    Given I Opened "{unitTestSite}"

  Scenario: CSS Test - Get Background Colour - XPATH
    Then I assert that the element with xpath "//a[@href='/Identity/Account/Login']" has background colour #"26a69a"

  Scenario: CSS Test - Get Font Size - XPATH
    Then I assert that the element with xpath "//a[@href='/Identity/Account/Login']" has Font Size "14" px

  Scenario: CSS Test - Get Font Colour - XPATH
    Then I assert that the element with xpath "//a[@href='/Identity/Account/Login']" has Font Colour #"fff"

  Scenario Outline: CSS Test - Generic CSS Attribute Step - XPATH
    Then I assert that the element with xpath "<xpath>" has CSS Attribute "<cssAttribute>" with CSS Value "<cssValue>"
    Examples:
      | xpath                                | cssAttribute     | cssValue              |
      | //a[@href='/Identity/Account/Login'] | color            | #fff                  |
      | //a[@href='/Identity/Account/Login'] | font-size        | 14px                  |
      | //a[@href='/Identity/Account/Login'] | background-color | #26a69a               |
      | //a[@href='/Identity/Account/Login'] | color            | rgb(255, 255, 255)    |
      | //a[@href='/Identity/Account/Login'] | background-color | rgb(38, 166, 154)     |
      | //a[@href='/Identity/Account/Login'] | background-color | rgba(38, 166, 154, 1) |
