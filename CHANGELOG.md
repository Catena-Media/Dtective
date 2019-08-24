# Changelog

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [1.4.0] - 2019-08-01
### Updated
- Redesigned the internal in-memory data store using dependency injection
- Introduced LOCAL, GLOBAL, CONFIGURATION data contexts

## [1.3.10] - 2019-08-01
### Updated
- POM structure, content and AppliTools integration POM
- Added new step to confirm a text value within an HTTP response

## [1.3.8] - 2019-07-05
### Fixed
- Character set handling to the HTTP response body.

## [1.3.7] - 2019-07-05
### Fixed
- Updated logging library due to warnings in console.

## [1.3.6] - 2019-06-19
### Fixed
- Prettified payloads in report.
 
## [1.3.5] - 2019-06-19
### Fixed
- Fixed scenario where test would crash when response is empty. Better handling in exception and response logging. 

## [1.3.4] - 2019-06-19
### New steps
- Added steps and tests for create / read / update / delete data within both local and session storage

## [1.3.3] - 2019-06-17
### Fixed
- Fixed params order in step @then("^value of data-store \"(.*)\" is equal to \"(.*)\"$")

## [1.3.2] - 2019-06-12
### Updated
- Centralized file-to-string reading methods within TestEnvironmentManager
- Removed constraints for JDK 11 allowing backwards compatibility

## [1.3.1] - 2019-06-12
### Updated 
- Fix for PUT request using Form Data

## [1.3] - 2019-04-30
### Added
- Added a new step to load a local HTML file in the browser
- Added first step for HTTP Patch method
- Added the missing capture of screenshots in the 'I navigate to' and 'text displays' steps
- Added a new step which allows user to pass a JSON Array as part of the JSON Body
- Added a step to send a POST with body from data-store
- Added a step to check when response does not contain a specific key
- Added a step to send a PUT with JSON request body
- Added a step to change the value of a key within the JSON request body

### Updated
- Updated API Steps definitions and the implementations of http functions
- Internal code cleanup and refactoring
- Updated httpClient maven dependency version

## [1.2.6] - 2019-03-29
### Fixed
- Fixed input order in step "I paste the value of param \"([^\"]*)\" to element by Attribute \"([^\"]*)\" Value \"([^\"]*)\"$"

## [1.2.5] - 2019-03-25
### Fixed
- HttpManager removing all existing headers on the first API request
### Added
- Added support for clearing HTTP headers before every scenario via configuration

## [1.2.4] - 2019-03-18
### Added
- HttpManager can now be set to a custom extension of class

### Removed
- API tests no longer need "I have http manager running" step. This steps was removed.

## [1.2.3] - 2019-03-13
### Added
- API Request and Response logging and attachment to Allure report. Configuration flag added : APILoggingEnabled=true

### Updated
- API Steps definitions and functions in general
- Removed all references to local params, proper name is data-store

## [1.2.2] - 2019-03-07 
### Updated
- Updated step from:
    - When I remove JSON key value pair with key "keyTest" from JSON "jsonObject"
     
    to
    
    -  When I remove JSON key value pair with key "keyTest" from JSON in local param "jsonObject"
### Refactored
- Removed unnecessary screenshots being taken in same step.

## [1.2.1] - 2019-02-21
### Added
- Iphone device and simulator support
- Added description to .env and environment.properties files
- Added support to dynamically disable tests on specific browser types by adding tag e.g.: "@disbled-chrome" 
### Updated
- Updated tests to satisfy iphone and firefox browsers (no structural changes)
- Disabled non-supported functions for Android, IPhone and Firefox

## [1.2] - 2019-02-18
### Added

- JsonPath (JsonPath) implementation for all the steps which interact with JSON Object keys and values. Example:

    - Then I assert that value from key "sum" stored in local param is greater than "30"

        - In this case, the 'sum' key is expected to be at the root level of the JSON Object, same as value would be set to "$.sum"

- Added new steps: 
    - Then value of local param "param" is equal to "something"
    - And I add key "id" and value "123" in JSON path "$" for JSON from data store "JSONbody" and store it in data store "newJSON"
    - And I get value of key "$.newKey" from JSON in local param "jsonObject" and store it in local param "value1"

### Updated

-  Reworded some of the steps to be more understandable.

## [1.1.5] - 2019-02-12
### Updated

- Updated and tested Android emulator and real device compatibility

## [1.1.4] - 2019-02-12
### Updated

- Added Safari browser capability.

## [1.1.3] - 2019-02-12
### Updated

- AppliTools integration was moved to a sub-module within the framework repository. Steps relying on the AppliTools integration now require an additional artifact built from the AppliTools maven submodule.
    
## [1.1.2] - 2019-02-07
### Updated

- Added missing "exists" keyword in expected conditions bindings
    - @Then("^I assert that the element exists by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the element exists by XPATH \"([^\"]*)\" within (\\d+) seconds$")## [1.1.2] - 2019-02-07
### Updated

- Added missing "exists" keyword in expected conditions bindings
    - @Then("^I assert that the element exists by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the element exists by XPATH \"([^\"]*)\" within (\\d+) seconds$")

## [1.1.1] - 2019-02-07
### Updated

- Browsermob integration getHar() to static

## [1.1] - 2019-02-07
### Fixed

- @currenttime placeholder test fix
- wrapping for webdrivers in non-single-instance mode fix for local driver screenshot error

### Updated

- Test runner engine to Cucumber3
- Dockerfile to use JDK11 Maven base image
- .gitignore file
- Javadocs for step binding definitions
- Allire CLI no longer needs to be installed. Moved to maven integration using Allure 2.9
- Test hooks now required glue "classpath:com.catena.qa.framework"

### Refactored

- Project directories
- Moved the steps definitions from the folder test to folder main
- Disabled file based logs in log4j2.xml and removed excessive logging

### Added

- Integration for Jenkins pipelines
- Added steps and tests to implement expected condition “within x seconds"
- Javadocs for framework code

- New Expected Condition Steps
    - @Then("^I assert that the element is clickable by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the element is clickable by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the element is visible by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the element is visible by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the element is selected by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the element is selected by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the browser alert is displays within (\\d+) seconds$")
    - @Then("^I assert that the number of elements is (\\d+) by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the number of elements is (\\d+) by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the text \"([^\"]*)\" displays by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the text \"([^\"]*)\" displays by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the element exists by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    - @Then("^I assert that the element exists by XPATH \"([^\"]*)\" within (\\d+) seconds$")

## [1.0.2] - 2018-11-06
### Added
- Added Change Log file (CHANGELOG.md)
- New POST and PUT Steps 
    - @And("^I send a POST to URL \"([^\"]*)\" with JSON request body from file \"([^\"]*)\"$")
    - @And("^I send a PUT to URL \"([^\"]*)\" and route from value of local param \"([^\"]*)\" with JSON request body from file \"([^\"]*)\"$")
    - @And("^I send a PUT to URL \"([^\"]*)\" and route from value of local param \"([^\"]*)\" with form data$")
    - @And("^I send a PUT to URL \"([^\"]*)\" and route from value of local param \"([^\"]*)\" with JSON request body$")
- New types can be added to JSON body 
    - @And("^I add to the JSON request body a key \"([^\"]*)\" with Dict value (.*)$")
    - @And("^I add to the JSON request body a key \"([^\"]*)\" with boolean value (.*)$")
    - Fixed Failing Unit Tests 
    - Fixed deprecated Libraries

## [1.0.3] - 2018-11-20
### Added
- Bug fix: IsSingleInstance runs with correct browser height and width independently of boolean value

## [1.0.4] - 2018-11-30
### Added
- Bug fix: Browsermob proxy runs on remote instances

## [1.0.5] - 2018-01-11
### Added
- Added JAVADOCS to step bindings
- Moved step bindings from test source to main source
### Sub-Repositories Required Changes
- Update test hook files with additional glue `classpath:com.catena.qa.framework.quality`

## [1.1] - 2018-01-30
### Fixed
- @CurrentTime placeholder test failure
- wrapping for webdrivers in non-single-instance mode

### Updated
- Test runner engine to Cucumber3
- Dockerfile to use JDK11 Maven base image
- .gitignore file
- Javadocs for step definitions
- Allure shell based manually installation to  maven integration using Allure 2.8.1
- Test hooks now required glue "classpath:com.catena.qa.framework"


### Refactored
- Project directories
- Moved the steps definitions from the folder test to folder main
- Disabled file based logs in log4j2.xml

### Added
- Helper classes MemoryHelper.java, ClipboardHelper.java, NetworkAnalyticsHelper.java, Stop.java, WebElementHelper.java
- Integration for Jenkins pipelines
- Added steps and tests to implement expected condition “within x seconds"
- Javadocs for framework code
