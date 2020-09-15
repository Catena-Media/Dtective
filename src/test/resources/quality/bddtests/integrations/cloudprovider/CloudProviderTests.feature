@cloud-provider-specific
# Settings:
# - CloudProvider=
# - CloudProviderFixed=false
# - TunnelID=
# - CloudBrowserType=

Feature: Cloud Provider Integration - Run test on Specified Provider

  Scenario: Run a simple test on Safari browser Sauce
    Given I want to run tests on SauceLabs
    And I set cloud browser to "safari"
    When I Opened "http://google.com"
    Then text displays "google"

  Scenario: Run a simple test on Chrome browser Sauce
    Given I want to run tests on SauceLabs
    And I set cloud browser to "chrome"
    When I Opened "http://google.com"
    Then text displays "google"

  Scenario: Run a simple test on Chrome browser on BrowserStack - failing
    Given I want to run tests on BrowserStack
    And I set cloud browser to "chrome"
    When I Opened "http://google.com"
    Then text does not display "google"

  Scenario: Run a simple test on Chrome browser on BrowserStack
    Given I want to run tests on BrowserStack
    And I set cloud browser to "chrome"
    When I Opened "http://google.com"
    Then text displays "google"

  Scenario: Run a simple test on Firefox browser on BrowserStack
    Given I want to run tests on BrowserStack
    And I set cloud browser to "firefox"
    When I Opened "http://google.com"
    Then text displays "google"

  Scenario: Run a simple test on Firefox browser Sauce- failing
    Given I want to run tests on SauceLabs
    And I set cloud browser to "firefox"
    When I Opened "http://google.com"
    Then text does not display "google"

  Scenario: Run a simple test locally
    When I Opened "http://google.com"
    Then text displays "google"

  Scenario: Run a simple test locally
    When I Opened "https://saucelabs.com"
    Then text displays "saucelabs"

  Scenario: Run a simple test locally - failing
    When I Opened "http://google.com"
    Then text does not display "google"