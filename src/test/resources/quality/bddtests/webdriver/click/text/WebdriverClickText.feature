@UnitTest
@UnitTestClick
@InternalSeleniumTest
Feature: Web-Driver Click

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Click - Text
  #
  # Example : Login button
  #
  # HTML = <a href="/Identity/Account/Login"> Login </a>
  # BDD = I click on text "Login"
  # ----------------------------------------------------------------------------------------------- #

  Scenario Outline: Click Test - Contains Text
    Given I am viewing "<TargetURL>"
    When I click on text "<TextValue>"
    Then url contains "<EndUrl>"

    Examples:
      | TargetURL      | TextValue | EndUrl                                |
      | {unitTestSite} | Login     | {unitTestSite}/Identity/Account/Login |

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Double-Click
  # ----------------------------------------------------------------------------------------------- #

  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Double Click Test - Contains Text

    Given I open website "{unitTestSite}/webdriver/clicks"
    When I double click on text "Double-click this paragraph to trigger a function."
    Then text displays "Hello World"

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Context-Click
  # ----------------------------------------------------------------------------------------------- #

  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Context Click Test - Contains Text

    Given I open website "{unitTestSite}/webdriver/clicks"
    When I context click on text "Context Click Test"
    Then text displays "Share On Facebook"