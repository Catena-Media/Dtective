@UnitTest
@UnitTestClick
@InternalSeleniumTest
Feature: Web-Driver Click

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Click - Xpath
  #
  # Example : Login button
  #
  # HTML = <a href="/Identity/Account/Login"> Login </a>
  # BDD = I click by XPATH "//a[@href='/Identity/Account/Login']"
  # ----------------------------------------------------------------------------------------------- #

  Scenario Outline: Click Test - XPATH
    Given I am viewing "<TargetURL>"
    When I click by XPATH "<XPATH>"
    Then url contains "<EndUrl>"

    Examples:
      | TargetURL      | XPATH                                   | EndUrl                                   |
      | {unitTestSite} | //a[@href='/Identity/Account/Register'] | {unitTestSite}/Identity/Account/Register |

  Scenario Outline: Click Test - Attribute-Value within XPATH
    Given I am viewing "{unitTestSite}"
    When I click by Attribute "href" and Value "/Identity/Account/Register" within XPATH "<XPATH>"
    Then url contains "{unitTestSite}/Identity/Account/Register"

    Examples:
      | XPATH  |
      | //body |



