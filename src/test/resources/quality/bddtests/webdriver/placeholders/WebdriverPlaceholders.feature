@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Placeholders

  Background: Setting up the current time test
    Given I add the following data-store "currentTime" "@CurrentTime"

  Scenario Outline:  Using placeholders during input

    Given I add to data-store "Fields.Login" containing key "id" and value "Input_Email"
    And I add to data-store "Fields.Password" containing key "id" and value "Input_Password"
    And I Opened "{unitTestSite}/Identity/Account/Login"
    When I type "<Text>" into Reference "Fields.Login"
    And I press enter
    Then element with id "Input_Email" contains value of "<Text>"

    Examples:
      | Text          |
      | @CurrentDate  |
      | {currentTime} |
      | @CurrentMonth |
      | @CurrentYear  |
      | @CurrentDate  |
      | @CurrentDate  |