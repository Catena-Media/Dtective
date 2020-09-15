@cloud-provider-fixed
# Settings:
# - CloudProvider=<cloudProviderName>
# - CloudProviderFixed=true
# - TunnelID=
# - CloudBrowserType=

Feature: Cloud Provider Integration - Generic Run on fixed Cloud Provider

  Scenario: Run a simple test on Chrome browser for fixed Cloud Provider
    Given I set cloud browser to "chrome"
    When I Opened "http://google.com"
    Then text displays "google"

  Scenario: Run a simple test on Safari browser for fixed Cloud Provider
    Given I set cloud browser to "safari"
    When I Opened "http://google.com"
    Then text displays "google"

  Scenario: Run a simple test on Firefox browser for fixed Cloud Provider
    Given I set cloud browser to "firefox"
    When I Opened "http://google.com"
    Then text displays "google"