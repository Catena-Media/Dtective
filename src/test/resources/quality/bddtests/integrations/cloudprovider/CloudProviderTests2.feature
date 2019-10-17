@cloud-provider-parallel
Feature: Cloud Provider Integration - Parallel

  @SauceLabs
  Scenario: Run a simple test on Safari browser Sauce - Parallel
    Given I want to run tests on SauceLabs
    And I set cloud browser to "safari"
    When I Opened "http://google.com"
    Then text displays "google"

  @SauceLabs
  Scenario: Run a simple test on Chrome browser Sauce - Parallel
    Given I want to run tests on SauceLabs
    When I set cloud browser to "chrome"
    When I Opened "http://google.com"
    Then text displays "google"

  @Browserstack
  Scenario: Run a simple test on Chrome browser on BrowserStack - Parallel
    Given I want to run tests on BrowserStack
    And I set cloud browser to "chrome"
    When I Opened "http://google.com"
    Then text displays "google"

  @SauceLabs
  Scenario: Run a simple test on Firefox browser Sauce - Parallel
    Given I want to run tests on SauceLabs
    And I set cloud browser to "firefox"
    When I Opened "http://google.com"
    Then text displays "google"

  @SauceLabs @Browserstack
  Scenario: Run a simple test locally - Parallel
    When I Opened "http://google.com"
    Then text displays "google"

  @SauceLabs @Browserstack
  Scenario: Run a simple test locally - Parallel
    When I Opened "https://saucelabs.com"
    Then text displays "saucelabs"