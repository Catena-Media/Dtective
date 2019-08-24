@HttpManagerTest
@InternalTest
@UnitTest
Feature: HTTP Messaging

  Scenario: Functional Test - Save Response to data-store
    When I send a GET to URL "{unitTestSite}" and store response in data-store "myresponse"
    Then I can use the locally set data "myresponse"

  Scenario: Functional Test - unique id is created
    When I generate a new objectID and store it in data-store "newID"
    Then data-store "newID" is not empty

  Scenario: Functional Test - Key is found within a JSON Object
    Given I add the following data-store "filePath" "/src/test/resources/quality/bddtests/http"
    When I store contents of file "{filePath}/jsonSample.json" in data-store "jsonSample"
    Then JSON within data-store "jsonSample" contains key "$.data.items[0].country.title"

  Scenario: Functional Test - Key and value are found within a JSON Object
    Given I add the following data-store "filePath" "/src/test/resources/quality/bddtests/http"
    When I store contents of file "{filePath}/jsonSample.json" in data-store "jsonSample"
    Then data-store "jsonSample" contains key "$.data.items[0].country.title" with value "malta"

  Scenario: Functional Test - Parse data-store with localParamParser
    Given I add the following data-store "filePath" "/src/test/resources/quality/bddtests/http"
    And I clear the data-store "jsonFile"
    When I store contents of file "{filePath}/jsonSample.json" in data-store "jsonFile"
    Then data-store "jsonFile" is not empty

  Scenario: Functional Test -Assert String from header value against a regex pattern
    When I send a GET to URL "{TestApi}/JSON/Status"
    And I store value of header "Server" into the data store "headerValue"
    Then value of header "Server" matches the regex pattern "[A-z]*"
    And value of data-store "headerValue" matches the regex pattern "[A-z]*"

  Scenario: Functional Test - Header assertion equals
    When I send a GET to URL "{TestApi}/JSON/Status"
    Then response code is "200"
    And header "Content-Type" is equal to the value of "application/json; charset=utf-8"

  Scenario: Functional Test - JSON value set to true
    Given I send a GET to URL "{TestApi}" with route "/JSON/Status" and store response in data-store "statusJSON"
    And response code is "200"
    And response contains key "status" with value "alive and kicking"
    And data-store "statusJSON" contains key "status" with value "alive and kicking"
    When I change value of key "status" in JSON from data store "statusJSON" to true and store it in data store "statusJSON"
    Then data-store "statusJSON" contains key "status" with value "true"

  Scenario: Functional Test - JSON value set to false
    Given I send a GET to URL "{TestApi}" with route "/JSON/Status" and store response in data-store "statusJSON"
    And response code is "200"
    And response contains key "status" with value "alive and kicking"
    And data-store "statusJSON" contains key "status" with value "alive and kicking"
    When I change value of key "status" in JSON from data-store "statusJSON" to false and store it in data-store "statusJSON"
    Then data-store "statusJSON" contains key "status" with value "false"

  Scenario: Functional Test - JSON value set to a value from data-store
    Given I add the following data-store "newValue" "young and free"
    And I send a GET to URL "{TestApi}" with route "/JSON/Status" and store response in data-store "statusJSON"
    And response code is "200"
    And response contains key "status" with value "alive and kicking"
    And data-store "statusJSON" contains key "status" with value "alive and kicking"
    When I change value of key "status" in JSON from data-store "statusJSON" with value from data-store "newValue" and store it in data-store "statusJSON"
    Then data-store "statusJSON" contains key "status" with value "young and free"

  Scenario: Functional Test - Part of URL from value of a data-store
    Given I add the following data-store "endpoint" "Status"
    When I send a GET to URL "{TestApi}" with route "/JSON/{endpoint}" and store response in data-store "statusJSON"
    Then response code is "200"
    And response contains key "status"
    And response contains key "status" with value "alive and kicking"
    And data-store "statusJSON" contains key "status" with value "alive and kicking"

  Scenario: Functional Test - Part of URL from value of a data-store and from testEnvironment JSON file
    Given I add the following data-store "endpoint" "Status"
    When I send a GET to URL "{TestApi}" with route "/JSON/{endpoint}" and store response in data-store "statusJSON"
    Then response code is "200"
    And response contains key "status" with value "alive and kicking"
    And data-store "statusJSON" contains key "status" with value "alive and kicking"

  Scenario: Functional Test - URL contains 2 different values of data-stores
    Given I add the following data-store "route" "JSON"
    And I add the following data-store "endpoint" "Status"
    When I send a GET to URL "{TestApi}" with route "/{route}/{endpoint}" and store response in data-store "statusJSON"
    Then response code is "200"
    And response contains key "status" with value "alive and kicking"
    And data-store "statusJSON" contains key "status" with value "alive and kicking"

  Scenario: Functional Test - URL is made up of a URL from testEnvironment JSON file and 2 different values of data-stores
    Given I add the following data-store "route" "JSON"
    And I add the following data-store "endpoint" "Status"
    When I send a GET to URL "{TestApi}" with route "/{route}/{endpoint}" and store response in data-store "statusJSON"
    Then response code is "200"
    And response contains key "status" with value "alive and kicking"
    And response body contains "alive and kicking"
    And data-store "statusJSON" contains key "status" with value "alive and kicking"

  Scenario: Functional Test - Use Bearer Token Authentication Header
    When I set Bearer Token Authentication Header using token from auth_token environment variable
    Then data-store "headers" is not empty

  Scenario: Functional Test - Clearing the HTTP Headers within a scenario
    Given I add HTTP Header "Test" value "12345"
    When I clear all HTTP Headers
    Then value of data-store "headers" is null

  Scenario: Functional Test - Getting an empty Response
    When I send a GET to URL "{TestApi}/unused"
    Then response body is equal to ""
    And response code is "404"