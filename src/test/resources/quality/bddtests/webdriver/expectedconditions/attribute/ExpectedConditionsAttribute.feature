@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Expected Conditions

  Background: Setup for the test
    Given I open website "{unitTestSite}/Identity/Account/Login"

  Scenario: Expected Conditions - Clickable (Attribute-Value)
    Then I assert that the element is clickable by Attribute "type" and Value "submit" within 1 seconds

  Scenario: Expected Conditions - Visible (Attribute-Value)
    Then I assert that the element is visible by Attribute "type" and Value "submit" within 1 seconds

  Scenario: Expected Conditions - Number of elements (Attribute-Value)
    Then I assert that the number of elements is 1 by Attribute "type" and Value "submit" within 1 seconds

  Scenario: Expected Conditions - Text matching (Attribute-Value)
    Then I assert that the text "[A-Z].*" displays by Attribute "type" and Value "submit" within 1 seconds

  Scenario: Expected Conditions - Existing (Attribute-Value)
    Then I assert that the element exists by Attribute "type" and Value "submit" within 1 seconds

  Scenario: Expected Conditions - Selectable (Attribute-Value)
    Given I open website "{unitTestSite}/AddressbookModels/Create"
    And I click element with Attribute "class" and Value "checkbox"
    Then I assert that the element is selected by Attribute "name" and Value "IsProfilePublic" within 1 seconds