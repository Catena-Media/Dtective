---
title: Run tests
tags: [test]
---

You have put great effort developing your set of automated tests and now is the moment
to let them work their magic.

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

The parameters `-D<Parameter>=` being passed on the command line are overriding the existing configuration set
on the `environment.properties` file.

#### For IntelliJ users

Simply with the right click to run your feature files or right clicking over a scenario to just run that specific one.
This runs the tests with the setup described in `environment.properties`.
