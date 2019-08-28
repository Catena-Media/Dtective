@ExampleAPITest

Feature: Example Project - Feature - Backend Testing

  Background: Setting up required http manager
    Given I add the following data-store "ExampleAPI" "http://localhost:8088/api/"


    #GET

  Scenario Outline: Using the GET HTTP Method to get data from a service
    When I send a GET to URL "<url>"
    Then response code is "<status code>"

    Examples:
      | url                                            | status code |
      | {ExampleAPI}StatusCodesAPI/CustomCode?code=200 | 200         |


    #POST

  Scenario Outline: Using the POST HTTP Method to send data to a service
    When I start to build a JSON request body
    And I add new JSON request body key "<key>" with value "<value>"
    And I send a POST to URL "{ExampleAPI}StatusCodesAPI/CheckStatus" with the JSON request body
    Then response code is "200"
    And response does not contain key "status" with value "fail"
    And response does not contain key "status" with value "null"
    And response contains key "status" with value "Status was OK"
    And response contains key "status" with anything other than value "Status was NOT OK"

    Examples:
      | key      | value |
      | $.status | ok    |


    #CREATE

  Scenario: Create a new Addressbook Entry
    When I start to build a JSON request body
    And I add new JSON request body key "personName" with value "Francis"
    And I add new JSON request body key "phoneNumber" with value "+35699999999"
    And I add new JSON request body key "isProfilePublic" with value "false"
    And I add new JSON request body key "isFreeAccount" with value "true"
    And I send a POST to URL "{ExampleAPI}AddressbookModelsAPI" with the JSON request body

    #READ

    And I store response body key "id" as "AddressID"
    And I send a GET to URL "{ExampleAPI}AddressbookModelsAPI" with route from data-store "AddressID"
    Then response code is "200"
    And response contains key "phoneNumber" with value "+35699999999"


  #Update scenario
  Scenario: Update an existing field in Addressbook entry
    #Creating a record to make sure there is an entry
    Given I start to build a JSON request body
    And I add new JSON request body key "personName" with value "Francis"
    And I add new JSON request body key "phoneNumber" with value "+35699999999"
    And I add new JSON request body key "isProfilePublic" with value "false"
    And I add new JSON request body key "isFreeAccount" with value "true"
    And I send a POST to URL "{ExampleAPI}AddressbookModelsAPI" with the JSON request body
    And I store response body key "$.id" as "idAdd"
    #end of creating entry
    When I send a GET to URL "{ExampleAPI}AddressbookModelsAPI/{idAdd}" and store response in data-store "AddressbookEntry1"
    And I change value of key "$.personName" in JSON from data-store "AddressbookEntry1" with value "Francis" and store it in data-store "AddressbookEntryModified"
    And I change value of key "$.isFreeAccount" in JSON from data store "AddressbookEntryModified" to true and store it in data store "AddressbookEntryModified"
    And I send a PUT to URL "{ExampleAPI}" and route "AddressbookModelsAPI/{idAdd}" with request body from data-store "AddressbookEntryModified"
    And response code is "204"
    Then I send a GET to URL "{ExampleAPI}AddressbookModelsAPI/{idAdd}"
    And response code is "200"
    And response contains key "personName" with value "Francis"


  #Update scenario
  Scenario: Add a new field in an existing Addressbook entry
    #Creating a record to use in scenario
    Given I start to build a JSON request body
    And I add new JSON request body key "personName" with value "Francis"
    And I add new JSON request body key "phoneNumber" with value "+35699999999"
    And I add new JSON request body key "isProfilePublic" with value "false"
    And I add new JSON request body key "isFreeAccount" with value "true"
    And I send a POST to URL "{ExampleAPI}AddressbookModelsAPI" with the JSON request body
    And I store response body key "$.id" as "idAdd"

    When I send a GET to URL "{ExampleAPI}AddressbookModelsAPI/{idAdd}" and store response in data-store "AddressbookEntry1"
    And I change value of key "$.personName" in JSON from data-store "AddressbookEntry1" with value "Francis" and store it in data-store "AddressbookEntryModified"
    And I send a PUT to URL "{ExampleAPI}" and route "AddressbookModelsAPI/{idAdd}" with request body from data-store "AddressbookEntryModified"
    And response code is "204"
    Then I send a GET to URL "{ExampleAPI}AddressbookModelsAPI/{idAdd}"
    And response code is "200"
    And response contains key "personName" with value "Francis"
  

  #Delete scenario
  Scenario: Delete an existing Addressbook entry
    Given I start to build a JSON request body
    And I add new JSON request body key "personName" with value "NewName"
    And I add new JSON request body key "phoneNumber" with value "+35699999999"
    And I add new JSON request body key "isProfilePublic" with value "true"
    And I add new JSON request body key "isFreeAccount" with value "true"
    And I send a POST to URL "{ExampleAPI}AddressbookModelsAPI" with the JSON request body
    And I store response body key "id" as "newID"
    When I send a GET to URL "{ExampleAPI}AddressbookModelsAPI" with route from data-store "newID"
    And response code is "200"
    And I send a DELETE to URL "{ExampleAPI}AddressbookModelsAPI" and route "newID"
    And response code is "200"
    And I send a GET to URL "{ExampleAPI}AddressbookModelsAPI" with route from data-store "newID"
    Then response code is "404"




