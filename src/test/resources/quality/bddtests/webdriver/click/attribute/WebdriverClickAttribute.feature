@UnitTest
@UnitTestClick
@InternalSeleniumTest
Feature: Web-Driver Click

#  Background: This test is designed to demonstrate how BDD steps can be used with HTML Attribute-Value

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Click - Attribute-Value - Equals
  #
  # Example : Login button
  #
  # HTML = <a href="/Identity/Account/Login"> Login </a>
  # BDD = I click element with Attribute "href" and Value "/Identity/Account/Login"
  # ----------------------------------------------------------------------------------------------- #

  Scenario Outline: Click Test - Attribute-Value

    Given I am viewing "<TargetURL>"
    When I click element with Attribute "<HTMLAttributeKey>" and Value "<Value>"
    Then url contains "<EndUrl>"

    Examples:
      | TargetURL      | HTMLAttributeKey | Value                      | EndUrl                                   |
      | {unitTestSite} | href             | /Identity/Account/Register | {unitTestSite}/Identity/Account/Register |

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Click - Attribute-Value - Contains
  #
  # Example : Login button
  #
  # HTML = <a href="/Identity/Account/Login"> Login </a>
  # BDD = I click element with Attribute "href" and Value that contains "Login"
  # ----------------------------------------------------------------------------------------------- #

  Scenario Outline: Click Test - Attribute-Value - Contains

    Given I am viewing "<TargetURL>"
    When I click element with Attribute "<HTMLAttributeKey>" and Value that contains "<Value>"
    Then url contains "<EndUrl>"

    Examples:
      | TargetURL      | HTMLAttributeKey | Value                      | EndUrl                                   |
      | {unitTestSite} | href             | /Identity/Account/Register | {unitTestSite}/Identity/Account/Register |

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Click - Attribute-Value - Contains Multiple
  #
  # Example : Login button
  #
  # HTML = <a href="/Identity/Account/Login"> Login </a>
  # BDD = I click element with Attribute "href" and Value that contains "Account" and "Login"
  # ----------------------------------------------------------------------------------------------- #

  Scenario Outline: Click Test - Attribute-Value - Contains Two Values
    Given I am viewing "<TargetURL>"
    When I click element with Attribute "<HTMLAttributeKey>" and Value that contains "<TextValue>" and "<TextValue2>"
    Then url contains "<EndUrl>"

    Examples:
      | TargetURL      | HTMLAttributeKey | TextValue | TextValue2 | EndUrl                                   |
      | {unitTestSite} | href             | Account   | Register   | {unitTestSite}/Identity/Account/Register |

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Double-Click
  # ----------------------------------------------------------------------------------------------- #


  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Double Click Test - Attribute-Value - Equals

    Given I open website "{unitTestSite}/webdriver/clicks"
    When I double click element with Attribute "ondblclick" Value "myFunction()"
    Then text displays "Hello World"


  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Double Click Test - Contains

    Given I open website "{unitTestSite}/webdriver/clicks"
    When I double click element with Attribute "ondblclick" and Value that contains "myFunction()"
    Then text displays "Hello World"


  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Double Click Test - Contains-Multiple

    Given I open website "{unitTestSite}/webdriver/clicks"
    When I double click element with Attribute "ondblclick" and Value that contains "my" and "Function()"
    Then text displays "Hello World"

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Context-Click
  # ----------------------------------------------------------------------------------------------- #

  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Context Click Test - Equals

    Given I open website "{unitTestSite}/webdriver/clicks"
    When I context click element with Attribute "class" Value "container body-content"
    Then text displays "Share On Facebook"

  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Context Click Test - Contains

    Given I open website "{unitTestSite}/webdriver/clicks"
    When I context click element with Attribute "class" and Value that contains "container body-content"
    Then text displays "Share On Facebook"

  @disabled-firefox
  @disabled-iPhone
  @disabled-androidchrome @disabled-androidfirefox
  Scenario: Context Click Test - Contains-Multiple

    Given I open website "{unitTestSite}/webdriver/clicks"
    When I context click element with Attribute "class" and Value that contains "container body-content" and ads""
    Then text displays "Share On Facebook"

  # ----------------------------------------------------------------------------------------------- #
  # Test Scenarios - Submit Button Click with Attribute and Value
  #
  # Example : Login button
  #
  # HTML = <button type="submit" class="btn btn-default">Log in</button>
  # BDD = I click submit with Attribute "class" and Value "btn btn-default"
  # ----------------------------------------------------------------------------------------------- #

  Scenario Outline: Click Test - Submit Button - Attribute-Value
    Given I am viewing "<TargetURL>"
    And I click submit with Attribute "class" and Value "btn btn-default"
    Then text displays "The Email field is required."

    Examples:
      | TargetURL                             |
      | {unitTestSite}/Identity/Account/Login |