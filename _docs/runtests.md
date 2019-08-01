---
title: Run tests
subtitle: Let those tests run!
tags: [customize]
author: evan
---

You put great effort developing your set of automated tests and now is the moment
to let them work their magic. A few couple of things:

1. Make sure the drivers for Chrome and Firefox are on the root folder of the project
2. If not using Maven integrated with IntelliJ, please install Maven first

We **highly recommend** you to read the following guide on [tags](https://cucumber.io/docs/cucumber/api/#tags)
 for organising and running a specific subset of scenarios.

To run the tests simply execute at the project root `mvn test`. This will run all
the tests available. It is possible to run the tests with more control over the options.

Some examples:

* `mvn clean test "-Dcucumber.options=--tags @DEV" -DBrowserType=chrome -DIsRemoteInstance=false`
 **cleans** previous results and runs only the **test** scenarios annotated with the `@DEV` tag on a `chrome` browser, locally.

* `mvn test "-Dcucumber.options=--tags @smoke @perf" -DBrowserType=firefox -DTestEnvironment=my-env`
runs the **test** scenarios annotated with `@smoke` and `@perf` on a `firefox` browser using the `my-env` parameters that
 are set on the `testEnvironment.json` file

* `mvn test "-Dcucumber.options=--tags @Regression" -DSeleniumHubUrl=http://localhost:4444/wd/hub -DIsRemoteInstance=true`
run the **tests** scenarios annotated with `@Regression` on a **remote** Selenium Grid that is running on `localhost:4444`

The parameters `-D<Parameter>=` being passed on the command are overriding the existing configuration set
on the `environment.properties` file.

####For IntelliJ users

Simply with the right click to run your feature files or when right clicking over a specific scenario to just run that particular one:

[create GIF and paste here]
