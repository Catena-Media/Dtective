@localStorageTests
Feature: Data Setup BDD Test Integration

  Background: Example of modelling a page with BDD - https://github.com/login

  # ----------------------------------------------------------------------------------------------- #
  # Test Data Setup
  # ----------------------------------------------------------------------------------------------- #

  Scenario: Deleting cookies
    Given I delete all cookies

  Scenario Outline:  Simple key-value test example
    Given I add the following data-store "<DataKey>" "<DataValue>"

    Examples:
      | DataKey     | DataValue      |
      | TestName    | GlobalDataTest |
      | BrowserSize | 1920x1080      |
      | Platform    | Linux          |

  Scenario Outline: Unit test website - Named key-value test example
    Given I add to data-store "<Name>" containing key "<Key>" and value "<Value>"

    Examples:
      | Name                  | Key   | Value                                            |
      | Site-Logo             | class | header-logo                                      |
      | Button-SignIn         | name  | commit                                           |
      | Button-ForgotPassword | href  | /password_reset                                  |
      | RegisterAccount       | xpath | //*[@id='login']/p/a[@href='/join?source=login'] |
  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios
  # ----------------------------------------------------------------------------------------------- #

  Scenario:  Navigate to the website
    Given I Opened "https://github.com/login"

  Scenario Outline:  Verify page elements
    Then element displays by Reference "<Name>"

    Examples:
      | Name          |
      | Site-Logo     |
      | Button-SignIn |

  Scenario Outline:  Verify Buttons
    Given I Opened "https://github.com/login"
    When I click element with Attribute and Value using data-store name "<Button>"
    Then url contains "<URL>"

    Examples:
      | Button                | URL                               |
      | Button-ForgotPassword | https://github.com/password_reset |

  Scenario: Using XPATH with local parameter
    Given I open website "https://github.com/login"
    When I click element with XPATH using data-store name "RegisterAccount"
    Then text displays "Create your personal account"


  # ----------------------------------------------------------------------------------------------- #
  # Test Teardown
  # ----------------------------------------------------------------------------------------------- #

