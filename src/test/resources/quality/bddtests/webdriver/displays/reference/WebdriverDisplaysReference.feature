@UnitTest
@InternalSeleniumTest
@WebdriverDisplays
Feature: Web-Driver Displays

  Scenario Outline: Displays Test - Setting up data for the scenario
    Given I add to data-store "<Name>" containing key "<Key>" and value "<Value>"

    Examples:
      | Name          | Key  | Value                      |
      | signUp-button | href | /Identity/Account/Register |
      | notabutton    | href | asdasdasdasd               |


  Scenario: Displays Test - Find an Element with Attribute and Value using parameter
    Given I open website "{unitTestSite}"
    Then element is displayed by Attribute and Value using parameter "signUp-button"

  Scenario: Displays Test - Confirm Element does not display with Attribute and Value using parameter
    Given I open website "{unitTestSite}"
    Then element is not displayed by Attribute and Value using parameter "notabutton"

  Scenario Outline: Displays Test - Setting up data for the scenario
    Given I add the following data-store "<Name>" "<Value>"

    Examples:
      | Name          | Value                                   |
      | testButton    | //a[@href='/Identity/Account/Register'] |
      | nottestbutton | //a[@href='/asdasdasdas']               |

  Scenario: Displays Test - Find an Element with xpath using parameter
    Given I open website "{unitTestSite}"
    Then element is displayed by xpath using parameter "testButton"

  Scenario: Displays Test - Confirm Element does not display using xpath using parameter
    Given I open website "{unitTestSite}"
    Then element is not displayed by xpath using parameter "nottestbutton"

  Scenario Outline: Displays Test - Setting up data for the scenario
    Given I add the following data-store "<Name>" "<Value>"

    Examples:
      | Name    | Value                      |
      | text    | Welcome to QA Test Website |
      | nottext | aasdasdasdasdasd           |

  Scenario: Displays Test - Find an Element with text using parameter
    Given I open website "{unitTestSite}"
    Then element is displayed by text using parameter "text"

  Scenario: Displays Test - Confirm Element does not display using text using parameter
    Given I open website "{unitTestSite}"
    Then element is not displayed by text using parameter "nottext"