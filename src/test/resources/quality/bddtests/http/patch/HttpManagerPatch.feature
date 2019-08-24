#@HttpManagerTest
#@InternalTest
#@UnitTest
#Feature: HTTP PATCH
#
#  Scenario Outline: Functional Test - Http PATCH to a URL
#    When I send a PATCH to URL "<URL>" body file-path: "<file>"
#    Then response code is "<ResponseCode>"
#
#    Examples:
#      | URL                   | ResponseCode |
#      | {TestApi}/JSON/Status | 200          |