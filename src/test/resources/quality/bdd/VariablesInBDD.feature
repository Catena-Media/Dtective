Feature: Data Setup BDD Test Integration

  Background: Example of modelling a page with BDD

  # ----------------------------------------------------------------------------------------------- #
  # Example of using system variables - io.dtective.bdd
  # ----------------------------------------------------------------------------------------------- #


  Scenario:  Simple key-value test example
    Given I Opened "{unitTestSite}/Identity/Account/Login"
    When I click on element with ID "Input_Email"
    And I type "@BrowserType @CurrentDate @CurrentTime"
    And I press enter
    Then text displays "The Email field is not a valid e-mail address."
