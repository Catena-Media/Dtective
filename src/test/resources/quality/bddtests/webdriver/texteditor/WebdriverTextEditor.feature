@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Text Editor

  Background: Data Setup
    Given I add to data-store "Fields.Login" containing key "id" and value "Input_Email"
    And I add to data-store "Fields.Password" containing key "id" and value "Input_Password"
    And I Opened "{unitTestSite}/Identity/Account/Login"


  Scenario: Using Replace text to
    When I click by XPATH "//*[@id='Input_Email']"
    And I type "HelloWorld"
    And I replaceText "HelloWorld" to "test123" in XPATH "//*[@id='Input_Email']"
    And I replaceText "test123" to "123123" in Attribute "id" and Value "Input_Email"
    And I click by XPATH "//*[@id='Input_Email']"
    And I replaceText "123123" to "Home"
    Then element with id "Input_Email" contains value of "Home"


  Scenario: Using copy text of element by XPATH
    When I copy text of element by XPATH "//*[@for='Input_Email']" to param "title"
    And I paste the value of param "title" to element by XPATH "//*[@id='Input_Email']"
    Then element with id "Input_Email" contains value of "Email"