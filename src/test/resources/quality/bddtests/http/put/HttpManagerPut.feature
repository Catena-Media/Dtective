@HttpManagerTest
@InternalTest
@UnitTest
Feature: HTTP PUT

  Scenario: Functional Test - Http PUT to an URL
    And I start to build a JSON request body
    And I add new JSON request body key "PersonName" with value "Tester"
    And I add new JSON request body key "PhoneNumber" with value "1234567890"
    And I add new JSON request body key "isProfilePublic" with value "false"
    And I add new JSON request body key "isFreeAccount" with value "true"
    And I add new JSON request body key "age" with value "0"
    And I send a POST to URL "{TestApi}/AddressbookModelsAPI" with the JSON request body
    And response code is "201"
    And I store response body key "id" as "newAddressbook"
    And I add new JSON request body key "id" with value from data-store "newAddressbook"
    And I change value of key "PersonName" to "AwesomeTester" in JSON request body
    When I send a PUT to URL "{TestApi}" and route "/AddressbookModelsAPI/{newAddressbook}" with the JSON request body
    Then response code is "204"
    When I send a DELETE to URL "{TestApi}/AddressbookModelsAPI" and route "{newAddressbook}"
    Then response code is "200"

  Scenario: Functional Test - Http PUT to a URL using JSON File
    When I send a POST to URL "{TestApi}/AddressbookModelsAPI" with request body from file "/src/test/resources/quality/bddtests/http/jsonAPIexample.json"
    Then response code is "201"
    And I store response body key "id" as "secondAddressbook"
    And I store contents of file "/src/test/resources/quality/bddtests/http/jsonAPIexample.json" in data-store "bodyForPUT"
    And I add key "id" and value "{secondAddressbook}" in JSON path "$" for JSON from data store "bodyForPUT" and store it in data store "bodyForPUT"
    When I send a PUT to URL "{TestApi}/AddressbookModelsAPI" and route "{secondAddressbook}" with request body from data-store "bodyForPUT"
    Then response code is "204"
    When I send a DELETE to URL "{TestApi}/AddressbookModelsAPI" and route "{secondAddressbook}"
    Then response code is "200"
    And I send a DELETE to URL "{TestApi}/AddressbookModelsAPI/99999"