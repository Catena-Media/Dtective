@InternalCommonsTest
@InternalZIPTest
Feature: Zip

  Scenario: EnZIP one file

    Given I have a file to be zipped
    When I compress the file to a ZIP file
    Then the file is created
    And I can extract the contents of the created ZIP file
    And the extracted content matches the original one

  Scenario: EnZIP a folder containing files

    Given I have a folder which only contains files
    When I compress the folder to a ZIP file
    Then the file is created
    And I can extract the contents of the created ZIP file
    And the extracted content matches the original one

  Scenario: EnZIP a folder containing files and folders

    Given I have a folder which contains files and folders
    When I compress the multi-folder to a ZIP file
    Then the file is created
    And I can extract the contents of the created ZIP file
    And the extracted content matches the original one

