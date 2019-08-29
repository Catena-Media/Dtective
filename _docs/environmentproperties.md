---
title: Environment properties
tags: [setup]
---

The `environment.properties` file found in `build > environment` is the configuration starter point. If the file is named `environment.properties.example` please rename it to `environment.properties` otherwise there may be some problems when running the tests.
You can specify in this file the desired setup to run your automated test suite.

* `SeleniumHubUrl` points to the URL where the Selenium Grid is running on
* `WebDriverTimeout`
* `WebDriverDelayMS`
* `IsSingleInstance` when set to `true` runs without a browser teardown in between the test scenarios of a feature file
* `IsRemoteInstance` when set to `false` runs the tests on the local machine; setting it to `true` will attempt to run in a Selenium Grid specified on the `SeleniumHubUrl`
* `BrowserType` can be set to the browsers we currently support
* `BrowserWidth` is the width of the browser window
* `BrowserHeight` is the height of the browser window
* `RecordSeleniumBrowserLogs`
* `RecordSeleniumDriverLogs`
* `RecordSeleniumServerLogs`
* `RecordSeleniumPerformanceLogs`
* `RecordSeleniumClientLogs`
* `RecordScreenshots`
* `BrowsermobProxy` when set to `true` captures the traffic to be analysed using using [Browsermob](https://github.com/lightbody/browsermob-proxy)
* `TestEnvironment` points to the key-value structure specified on the `testEnvironment.json` file. This is useful when running against different test environments such as staging or in a CI server.

Please leave as is the properties for `WebAppUrl`, `TestServer`, `TestProject`, `UseTestServer`, `WebAppUser` and `WebAppPass`. We will deprecate such properties from the open source project soon.

### An example:

```java
SeleniumHubUrl=http://localhost:4444/wd/hub     
WebDriverTimeout=3                              
WebDriverDelayMS=500                            
IsSingleInstance=true                           
IsRemoteInstance=false                          
BrowserType=chrome                              
BrowserWidth=1920                               
BrowserHeight=1080                              
BrowsermobProxy=false                           
TestEnvironment=env-local                          
```

With the above example, the tests will be running locally, since the property `IsRemoteInstance` is set to `false`. The browser where tests will be running would be `chrome` with no teardown of browser sessions in between the tests, with `IsSingleInstance` set to `true`.
The context of variables for the test run will be using the `env-local` structure stated in `testEnvironment.json` file.
