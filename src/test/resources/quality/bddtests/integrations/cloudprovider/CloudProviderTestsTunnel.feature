@cloud-provider-tunnel
# Settings:
# - CloudProviderFixed=true
# - CloudProvider=<provider>
# - CloudBrowserType=<browser>
# - TunnelID=<tunnnelName>

Feature: Cloud Provider Integration - Tunnel running

  Scenario: Run a simple test on Provider using Tunnel - Test 1 (P)
    When I Opened "http://google.com"
    Then text displays "google"

  Scenario: Run a simple test on Provider using Tunnel - Test 2 (F)
    When I Opened "http://google.com"
    Then text does not display "google"

  Scenario: Run a simple test on Provider using Tunnel - Test 3 (P)
    When I Opened "https://saucelabs.com"
    Then text displays "saucelabs"