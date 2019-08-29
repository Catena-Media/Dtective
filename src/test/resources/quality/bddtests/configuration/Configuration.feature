@ConfigurationTests
Feature: Configuration

#  Generic Implementation

  Scenario Outline: Configuration Map Test
    When I set configuration "<field>" to value "<value>"
    Then configuration "<field>" value is "<value>"
    Examples:
      | field            | value                        |
      | SeleniumHubUrl   | http://localhost:4444/wd/hub |
      | WebDriverTimeout | 10                           |
      | WebDriverDelayMS | 500                          |
      | WebAppUrl        | http://localhost:8088        |
      | WebAppUser       | test@test.eu                |
      | WebAppPass       | 123456                       |
      | BrowserWidth     | 1920                         |
      | BrowserHeight    | 1080                         |
      | BrowserType      | chrome                       |
      | TestEnvironment  | env-CI                       |
      | BrowsermobProxy  | false                        |

#  Specific Implementations

  Scenario: Change Selenium-Hub Url
    When I set Selenium-Hub url to "http://test:4444/wd/hub"
    And I reset webdriver
    Then Selenium-Hub url is set to "http://test:4444/wd/hub"

  Scenario: Change Webdriver Timeout
    When I set Webdriver Timeout to "10"
    And I reset webdriver
    Then Webdriver Timeout is set to "10"

  Scenario: Change Webdriver Delay
    When I set Webdriver Delay MS to "500"
    And I reset webdriver
    Then Webdriver Delay MS is set to "500"

  Scenario: Change Browser Type
    When I set Browser Type to "chrome"
    And I reset webdriver
    Then Browser Type is set to "chrome"

  Scenario: Change Browser Size (general)
    When I set Browser Size to width "1920" and heigth "1080"
    And I reset webdriver
    Then Browser Size is set to width "1920" and heigth "1080"

  Scenario: Change Browser Size (current only)
    Given I open website "{unitTestSite}"
    When I set current Browser Size to width "1920" and height "1080"
    Then current Browser Size is set to width "1920" and height "1080"

  Scenario: Change Target WebApp Url
    When I set Target WebApp url to "https://www.google.com/"
    And I reset webdriver
    Then Target WebApp url is set to "https://www.google.com/"

  Scenario: Change WebApp User Name
    When I set WebApp User Name to "user@test.com"
    And I reset webdriver
    Then WebApp User Name is set to "user@test.com"

  Scenario: Change WebApp User Password
    When I set WebApp User Password to "123456"
    And I reset webdriver
    Then WebApp User Password is set to "123456"

  Scenario: Change Test Environment JSON
    When I set Test Environment JSON to "env-CI"
    And I reset webdriver
    Then Test Environment JSON is set to "env-CI"

  Scenario: Change Record Screenshots
    When I set Record Screenshots "true"
    And I reset webdriver
    Then Record Screenshots is set to "true"

  Scenario: Change Highlight Elements
    When I set Highlight Elements "true"
    And I reset webdriver
    Then Highlight Elements is set to "true"

  Scenario: Change Browser Mob Proxy
    When I set Browser Mob Proxy "true"
    And I reset webdriver
    Then Browser Mob Proxy is set to "true"