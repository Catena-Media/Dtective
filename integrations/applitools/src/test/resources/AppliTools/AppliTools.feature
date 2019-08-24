Feature: Framework Unit-Test Web-Driver AppliTools

  Scenario: Web-Driver AppliTools Test

    Given I open website "https://www.google.com/"
    When I open AppliTools
    And I check screenshot with applitools with tagname "Google homepage"
    Then I close AppliTools and handle results
    And I close the current Tab
