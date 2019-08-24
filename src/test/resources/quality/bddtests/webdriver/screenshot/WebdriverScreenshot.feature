@UnitTest
@InternalSeleniumTest
@ScreenShotUnitTest
Feature: Web-Driver Screenshot

  Scenario Outline: Web-Driver Screenshot Test

    Given I open website "<URL>"
    And I take a screenshot
    Then screenshot is saved

    Examples:
      | URL            |
      | {unitTestSite} |

