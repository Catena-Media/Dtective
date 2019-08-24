@HttpManagerTest
@InternalTest
@UnitTest
Feature: HTTP DELETE

  Scenario: Functional  Test - HTTP Delete to an URL
    And I start to build a JSON request body
    And I add new JSON request body key "personName" with value "Tester"
    And I add new JSON request body key "phoneNumber" with value "1234567890"
    And I add new JSON request body key "isProfilePublic" with value "false"
    And I add new JSON request body key "isFreeAccount" with value "true"
    And I send a POST to URL "{TestApi}/AddressbookModelsAPI" with the JSON request body
    And response code is "201"
    And I store response body key "id" as "AddressbookID"
    When I send a DELETE to URL "{TestApi}/AddressbookModelsAPI/{AddressbookID}"
    Then response code is "200"
    And I send a GET to URL "{TestApi}/AddressbookModelsAPI/{AddressbookID}"
    And response code is "404"