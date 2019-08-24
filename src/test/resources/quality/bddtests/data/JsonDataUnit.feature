@UnitTest
@JsonDataTest

Feature: Framework Unit-Test JSON Tests

  Background: During test execution, I want to be able to use and modify JSON objects using the JSON Path notation
    Given I add the following data-store "jsonObject" "{"keyTest": "valueTest","inner": {"something":"something"}}"

    # ADD OPERATIONS

  Scenario: Add a new key value pair in root level and inner level
    When I add key "newKey" and value "AddedValue" in JSON path "$" for JSON from data store "jsonObject" and store it in data store "jsonObject"
    And I add key "innerKey" and value "AnotherValue" in JSON path "$.inner" for JSON from data store "jsonObject" and store it in data store "jsonObject"
    And I get value of key "$.newKey" from JSON in data-store "jsonObject" and store it in data-store "value1"
    And I get value of key "$.inner.innerKey" from JSON in data-store "jsonObject" and store it in data-store "value2"
    Then value of data-store "value1" is equal to "AddedValue"
    And value of data-store "value2" is equal to "AnotherValue"

    # READ OPERATIONS

  Scenario: Get a value for a specific key in root level and inner level
    When I get value of key "$.keyTest" from JSON in data-store "jsonObject" and store it in data-store "value1"
    And I get value of key "$.inner.something" from JSON in data-store "jsonObject" and store it in data-store "value2"
    Then value of data-store "value1" is equal to "valueTest"
    And value of data-store "value2" is equal to "something"

    # EDIT OPERATIONS

  Scenario: Change a value for a specific key
    When I change value of key "$.inner.something" to "something new" in JSON from data-store "jsonObject" and store it as "jsonObject"
    And I get value of key "$.inner.something" from JSON in data-store "jsonObject" and store it in data-store "value"
    Then value of data-store "value" is equal to "something new"

    # DELETE OPERATIONS

  Scenario: Remove a key value pair
    When I remove JSON key value pair with key "keyTest" from JSON in data-store "jsonObject"
    Then JSON within data-store "jsonObject" does not contain key "$.keyTest"
