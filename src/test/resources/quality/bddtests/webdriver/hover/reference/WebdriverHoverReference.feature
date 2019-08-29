@UnitTest
@InternalSeleniumTest
@HoverTest

@disabled-firefox
@disabled-iPhone
@disabled-androidchrome @disabled-androidfirefox
Feature: Web-Driver Hover

  Scenario Outline: Using data-stores for hover
    Given I add the following data-store "<Name>" "<Value>"

    Examples:
      | Name             | Value                                |
      | button-xpath     | //*[@href='/Identity/Account/Login'] |
      | button-attr      | href                                 |
      | button-attrvalue | /Identity/Account/Login              |

  Scenario: Hover Test - Using hover with XPATH using local parameter
    Given I open website "{unitTestSite}"
    Then I assert that the element with Attribute "href" Value "/Identity/Account/Login" has background colour #"26a69a"
    When I hover over by XPATH "{button-xpath}"
    And I wait "500" ms
    Then I assert that the element with Attribute "href" Value "/Identity/Account/Login" has background colour #"2bbbad"

  Scenario: Hover Test - Using hover with Attribute and Value using local parameter
    Given I open website "{unitTestSite}"
    Then I assert that the element with Attribute "href" Value "/Identity/Account/Login" has background colour #"26a69a"
    When I hover over element with Property "{button-attr}" and Value "{button-attrvalue}"
    And I wait "500" ms
    Then I assert that the element with Attribute "href" Value "/Identity/Account/Login" has background colour #"2bbbad"
