@UnitTest
@InternalSeleniumTest
@EscapeUnitTest
Feature: Web-Driver Keys

  Background: Data Setup
    Given I add to data-store "Fields.Login" containing key "id" and value "Input_Email"
    Given I add to data-store "Fields.Password" containing key "id" and value "Input_Password"
    And I Opened "{unitTestSite}/Identity/Account/Login"

  @disabled-iPhone
  Scenario: Keys Test - Using the tab key
    Given I type "test@test.eu" into Attribute "id" Value "Input_Email"
    And I press tab
    And I type "test123456"
    And I press enter
    Then text displays "Invalid login attempt."

  @disabled-firefox
  @disabled-iPhone
  Scenario Outline: Keys Test - Type space between text search

    Given I click element with Attribute and Value using data-store name "Fields.Login"
    And I clear the field
    When I type "<Text1>"
    And I press space
    And I type "<Text2>"
    Then element with id "Input_Email" contains value of "<Text1> <Text2>"
    And field value equals "<Text1> <Text2>" by Attribute "id" Value "Input_Email"

    Examples:
      | Text1 | Text2 |
      | Hello | World |