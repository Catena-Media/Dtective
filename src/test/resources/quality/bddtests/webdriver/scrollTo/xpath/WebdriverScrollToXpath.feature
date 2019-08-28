@UnitTest
@InternalSeleniumTest

@disabled-firefox
#@disabled-chrome
@disabled-iPhone
Feature: Web-Driver ScrollTo

  Scenario: ScrollTo Test - Simple Scroll To Test
    Given I add the following data-store "element-id" "operations-StatusCodesAPI-ApiStatusCodesAPICheckStatusPost"
    And I open website "{unitTestSite}/swagger/"
    When I scroll to element by XPATH "//*[@id='operations-StatusCodesAPI-ApiStatusCodesAPICheckStatusPost']"
    And I click by XPATH "//*[@id='{element-id}']"
    And I click by XPATH "//*[@id='{element-id}']//*[contains(text(),'Try it out')]"
    And I click by XPATH "//*[@id='{element-id}']//*[contains(text(),'Execute')]"
    Then text displays "I don't understand the status"
    
