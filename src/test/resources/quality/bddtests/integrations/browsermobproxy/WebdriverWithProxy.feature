@BrowserMobProxyTest
@InternalSeleniumTest
Feature: Framework Browsermob Proxy

  Scenario: Record a browser interaction with Browsermob proxy
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I start recording the browser interaction
    And I click by XPATH "//*[@id='Input_Email']"
    And I save the HAR file in "browsermobtestfile"
    And I stop recording the browser interaction
    Then the file "browsermobtestfile" exists
