@TestDataCoreTest
Feature: Framework Unit-Test TestDataCore Tests

  Background: During test execution, I want to be able to use a local and a global data context to communicate between steps / test cases

  Scenario Outline:  simple key-value test
    Given I add the following data-store "<DataKey>" "<DataValue>"

    Examples:
      | DataKey     | DataValue      |
      | TestName    | GlobalDataTest |
      | BrowserSize | 1920x1080      |
      | BrowserType | Chrome         |
      | Platform    | Linux          |

  Scenario Outline:  Named key-value test
    Given I add the following data-store "<Key>" "<Value>"
    Given I add to data-store "<Name>" containing key "<Key>" and value "<Value>"
    Then value of data-store "unknownParam" is null

    Examples:
      | Name        | Key            | Value     |
      | TestName    | GlobalDataTest | DataValue |
      | BrowserSize | 1920x1080      | DataValue |
      | BrowserType | Chrome         | DataValue |
      | Platform    | Linux          | DataValue |

  Scenario: Setting and accessing boolean values in data store
    Given I add the following data-store "booleans" "{"trueKey":true,"falseKey":false}"
    When I change value of key "falseKey" in JSON from data store "booleans" to true and store it in data store "booleans"
    And I change value of key "trueKey" in JSON from data-store "booleans" to false and store it in data-store "booleans"
    Then value of data-store "booleans" is equal to "{"trueKey":false,"falseKey":true}"

