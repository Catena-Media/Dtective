@UnitTest
@PlaceholderTest

Feature: Framework Unit-Test Placeholder Tests

  Background: Viewing unit test site
    Given I am viewing "{unitTestSite}"

  Scenario: Click steps are working with placeholder replace
    Given I add the following data-store "Key" "href"
    And I add the following data-store "Value" "/Identity/Account/Register"
    And I add the following data-store "rootXPATH" "."
    When I click element with Attribute "{Key}" and Value "{Value}"
    Then url contains "{unitTestSite}/Identity/Account/Register"
    When I am viewing "{unitTestSite}"
    And I click by Attribute "{Key}" and Value "{Value}" within XPATH "{rootXPATH}"
    Then url contains "{unitTestSite}/Identity/Account/Register"

