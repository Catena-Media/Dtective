Feature: Setup of the example site

  @testsite-setup
  Scenario: Migrate database
    Given I open website "{unitTestSite}/Departments"
    When I click element with Attribute "id" and Value "applyMigrations"
    And I wait "1000" ms
    And I refreshed
    Then text displays "Index"