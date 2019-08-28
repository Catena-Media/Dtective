@UnitTestPasteFromClipboard
Feature: Web-Driver Copy-Paste-Clipboard

  Scenario: Pasting a value which is saved in clipboard
    Given I put "test1234" in the clipboard
    Given I open website "{unitTestSite}/Identity/Account/Login"
    When I paste the value from clipboard to element by Attribute "id" Value "Input_Email"
    Then field value equals "test1234" by Attribute "id" Value "Input_Email"

  Scenario: Copying value of element and pasting using Reference
    Given I open website "{unitTestSite}/Identity/Account/Login"
    Given I type "test1111" into Attribute "id" Value "Input_Email"
    When I copy text of element by XPATH "//*[@id='Input_Email']" to param "testCopyXpath"
    And I clear the field by Attribute "id" Value "Input_Email"
    And I paste the value of param "testCopyAttribute" to element by XPATH "//*[@id='Input_Email']"
    Then field value equals "test1111" by Attribute "id" Value "Input_Email"

  Scenario: Copying value of element and pasting using Clipboard
    Given I open website "{unitTestSite}/Identity/Account/Login"
    And I type "test4321" into Attribute "id" Value "Input_Email"
    When I copy text of element by Attribute "id" Value "Input_Email" to clipboard
    When I paste the value from clipboard to element by XPATH "//*[@id='Input_Password']"
    Then field value equals "test4321" by Attribute "id" Value "Input_Email"