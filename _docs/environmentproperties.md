---
title: Environment properties
subtitle: Most relevant environment.properties explained
tags: [features]
author: john
---

The `environment.properties` file found in `build > environment` is the configuration starter point.
You can specify in this file the desired setup to run your automated test suite.

* `SeleniumHubUrl` points to the URL where the Selenium Grid is running on
* `WebDriverTimeout`
* `WebDriverDelayMS`
* `IsSingleInstance` when set to `true` runs without a browser teardown in between the test scenarios of a feature file
* `IsRemoteInstance` when set to `false` runs the tests on the local machine; setting it to `true` with attempt to run in a Selenium Grid specified on the `SeleniumHubUrl`
* `BrowserType` can be set to the browsers we currently support (confirm the support)
* `BrowserWidth` is the width of the browser window
* `BrowserHeight` is the height of the browser window
* `RecordSeleniumBrowserLogs`
* `RecordSeleniumDriverLogs`
* `RecordSeleniumServerLogs`
* `RecordSeleniumPerformanceLogs`
* `RecordSeleniumClientLogs`
* `RecordScreenshots`
* `BrowsermobProxy` when set to `true` captures the traffic to be analysed using using [Browsermob](https://github.com/lightbody/browsermob-proxy)
* `TestEnvironment` points to the key-value from the `testEnvironment.json` file

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
TestEnvironment=env-ci                          
```
