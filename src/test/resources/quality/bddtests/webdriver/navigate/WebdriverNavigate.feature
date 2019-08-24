Feature: Web-Driver Navigate

  @UnitTest
  @UnitTestNavigate
  @InternalSeleniumTest
  Scenario Outline: Navigate Test - Simple Navigate

    Given I Opened "<TargetURL>"
    Then text displays "<Text>"

    Examples:
      | TargetURL      | Text                       |
      | {unitTestSite} | Welcome to QA Test Website |

  @UnitTest
  @UnitTestNavigate
  @InternalSeleniumTest
  Scenario: Navigate Test - Simple Navigate 2

    Given I open website "{unitTestSite}"
    Then text displays "Welcome to QA Test Website"

  @UnitTest
  @UnitTestNavigate
  @InternalSeleniumTest
  Scenario: Navigate Test - Simple target application Navigate
    Given I set Target WebApp url to "{unitTestSite}"
    Given I Opened target application
    Then url contains Target Url

  @UnitTest
  @UnitTestNavigate
  @InternalSeleniumTest
  Scenario:  Navigate Test - Open URL using a placeholder
    When  I am viewing "{unitTestSite}/Identity/Account/Login"
    Then url contains "{unitTestSite}/Identity/Account/Login"

  Scenario: Navigate Test - Open a local HTML file
    When I open local HTML file "/src/test/resources/quality/bddtests/webdriver/navigate/localHTML.html"
    Then text displays "This is a very simple HTML document"
