@HttpManagerTest
@InternalTest
@UnitTest
Feature: HTTP GET

  Scenario Outline: Functional Test - Http GET to an URL
    When I send a GET to URL "<URL>"
    Then response code is "<ResponseCode>"

    Examples:
      | URL                   | ResponseCode |
      | {TestApi}/JSON/Status | 200          |

  Scenario Outline: Functional Test - Http GET to an URL with headers
    And I add HTTP Header "Authorization" value "Bearer TEST123"
    When I send a GET to URL "<URL>"
    And response code is "<ResponseCode>"
    And response body is equal to "{"status":"alive and kicking"}"
    And response does not contain key "status" with value "something"
    And response does not contain key "$.another.status"

    Examples:
      | URL                   | ResponseCode |
      | {TestApi}/JSON/Status | 200          |