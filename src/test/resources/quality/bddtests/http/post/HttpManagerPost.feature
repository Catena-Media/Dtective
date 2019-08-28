@HttpManagerTest
@HttpPostTest
@InternalTest
@UnitTest
Feature: HTTP POST

  Scenario Outline: Functional Test - Http POST to an URL
    When I send a POST to URL "<URL>"
    Then response code is "<ResponseCode>"

    Examples:
      | URL                   | ResponseCode |
      | {TestApi}/JSON/Status | 404          |

  Scenario: Functional Test - Http POST to an URL with headers
    And I add HTTP Header "Authorization" value "Bearer TEST123"
    When I send a POST to URL "{TestApi}/DepartmentsAPI"
    Then response code is "400"

  Scenario: Functional Test - Http POST to a URL using JSON File
    When I send a POST to URL "{TestApi}/AddressbookModelsAPI" with request body from file "/src/test/resources/quality/bddtests/http/jsonAPIexample.json"
    Then response code is "201"
    And I store response body key "id" as "Addressbook"
    When I send a DELETE to URL "{TestApi}/AddressbookModelsAPI" and route "{Addressbook}"
    Then response code is "200"
