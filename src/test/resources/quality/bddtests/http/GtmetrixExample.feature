Feature: GTmetrix integration example

  Scenario Outline: Testing websites using Gtmetrix API
    Given I set basic Authentication for API request with username "<username>" and password "<password>"
    And I add HTTP FORM data with key "url" and value "<url>"
    And I add HTTP FORM data with key "browser" and value "<browser>"
    And I add HTTP FORM data with key "location" and value "<location>"
    When I send a POST to URL "https://gtmetrix.com/api/0.1/test" with form data
    Then response code is "200"
    And I wait "60000" ms
    And I get value of key "test_id" from JSON in data-store "resbody" and store it in data-store "route"
    Then I send a GET to URL "https://gtmetrix.com/api/0.1/test/" with route from data-store "route"
    And response code is "200"
    And I assert that value of key "$.results.pagespeed_score" from the response body is greater than "30"
    And I assert that value of key "$.results.fully_loaded_time" from the response body is smaller than "30000"

    Examples:
      | url                | browser | location | username            | password            |
      | https://google.com | 1       | 2        | enter-username-here | enter-password-here |