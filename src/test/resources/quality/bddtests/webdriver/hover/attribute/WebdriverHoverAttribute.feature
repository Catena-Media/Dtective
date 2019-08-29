@UnitTest
@InternalSeleniumTest
@HoverTest

@disabled-firefox
@disabled-iPhone
@disabled-androidchrome @disabled-androidfirefox
Feature: Web-Driver Hover

  Scenario : Hover Test - Using hover with Attribute and Value
    Given I open website "{unitTestSite}"
    Then I assert that the element with Attribute "href" Value "/Identity/Account/Login" has background colour #"26a69a"
    When I hover over element with Property "href" and Value "/Identity/Account/Login"
    And I wait "500" ms
    Then I assert that the element with Attribute "href" Value "/Identity/Account/Login" has background colour #"2bbbad"