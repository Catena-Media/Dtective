@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Expected Conditions

  Background: Setup for the test
    Given I open website "{unitTestSite}/Identity/Account/Login"

  Scenario: Expected Conditions - Clickable (XPATH)
    Then I assert that the element is clickable by XPATH "//*[@type='submit']" within 1 seconds

  Scenario: Expected Conditions - Visible (XPATH)
    Then I assert that the element is visible by XPATH "//*[@type='submit']" within 1 seconds

  Scenario: Expected Conditions - Number of elements (XPATH)
    Then I assert that the number of elements is 1 by XPATH "//*[@type='submit']" within 1 seconds

  Scenario: Expected Conditions - Text matching (XPATH)
    Then I assert that the text "[A-Z].*" displays by XPATH "//*[@type='submit']" within 1 seconds

  Scenario: Expected Conditions - Existing (XPATH)
    Then I assert that the element exists by XPATH "//*[@type='submit']" within 1 seconds

  Scenario: Expected Conditions - Selectable (XPATH)
    Given I open website "{unitTestSite}/AddressbookModels/Create"
    And I click element with Attribute "class" and Value "checkbox"
    Then I assert that the element is selected by XPATH "//*[@name='IsProfilePublic']" within 1 seconds