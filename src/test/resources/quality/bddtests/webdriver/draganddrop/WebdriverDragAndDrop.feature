@UnitTest
@InternalSeleniumTest
@DragAndDropUnitTest

@disabled-firefox
@disabled-iPhone
@disabled-androidchrome @disabled-androidfirefox
Feature: Web-Driver Drag and Drop

  Scenario: Drag and Drop Test
    Given I open website "{unitTestSite}/Webdriver/DragAndDrop"
    When I drag from element with property "id" and value "drag1" and drop into element with property "id" and value "div1"
    Then text displays "DROPPED"