@UnitTest
@UnitTestClick
@InternalSeleniumTest
Feature: Web-Driver Click

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Click - Submit button
  #
  # Example : Login button
  #
  # HTML = <button type="submit"> Log in </button>
  # BDD = I click submit
  # ----------------------------------------------------------------------------------------------- #

  Scenario Outline: Click Test - Submit button
    Given I am viewing "<TargetURL>"
    And I click submit
    Then text displays "The Email field is required."

    Examples:
      | TargetURL                             |
      | {unitTestSite}/Identity/Account/Login |

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Checking all buttons on the page for DISPLAYED / ENABLED
  # ----------------------------------------------------------------------------------------------- #

  Scenario: Click Test - Assertion - All buttons are clickable
    Given I am viewing "{unitTestSite}/Identity/Account/Login"
    Then I check that all buttons using XPATH: "//body//*[@type='submit']" are clickable