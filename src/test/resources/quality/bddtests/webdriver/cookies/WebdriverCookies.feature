@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Cookies

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Cookies
  #
  # Cookies can be observed in the browser using Developer Tools / Application tab and Cookies option.
  # ----------------------------------------------------------------------------------------------- #

  Scenario: Deleting cookies
    Given I Opened "{unitTestSite}/Identity/Account/Login"
    Given I delete all cookies

  Scenario: Testing cookies CRUD
    Given I Opened "{unitTestSite}/Identity/Account/Login"
    When I add cookie "test" with value "yes"
    Then I assert that cookie with name "test" exists
    And I assert that cookie with name "test" has value "yes"
    And I delete cookie "test"

  @disabled-firefox
  Scenario: Deleting local storage
    Given I Opened "{unitTestSite}/Identity/Account/Login"
    Given I delete all web local storage

  @disabled-firefox
  Scenario: Testing local storage CRUD
    Given I Opened "{unitTestSite}/Identity/Account/Login"
    When I add to web local storage "test" with value "yes"
    Then I assert that web local storage key with name "test" exists
    And I assert that web local storage key with name "test" has value "yes"
    And I delete web local storage key "test"

  @disabled-firefox
  Scenario: Deleting session storage
    Given I Opened "{unitTestSite}/Identity/Account/Login"
    Given I delete all web session storage

  @disabled-firefox
  Scenario: Testing session storage CRUD
    Given I Opened "{unitTestSite}/Identity/Account/Login"
    When I add to web session storage "test" with value "yes"
    Then I assert that web session storage key with name "test" exists
    And I assert that web session storage key with name "test" has value "yes"
    And I delete web session storage key "test"

