---
title: BDD, Cucumber & Gherkin
tags: [test]
---
### Enter BDD

BDD, which stands for Behaviour Driven Development, is one of the most popular approaches to test automation. This technique
has derived from TDD (Test Driven Development) which defines that before any functionality is implemented, tests should be created first.
TDD has focused more on the unit test side of things, useful for short term iterations when you need to keep your functionality safe from regression for a single unit that is under development.

When it comes to integration and end to end testing, we don't focus on a single unit but the application (or bigger parts of the application) as a whole, and therefore our tests increase in complexity and time spent designing them. The modeling of the behaviour is an effective approach to business requirements. BDD was created to involve business stakeholders, product owners, QAs and developers in a way where all parts can identify and align the flows of a user interacting with a feature.

Sounds great? It is great, and the folks at **Cucumber** implemented this technique, explaining the behaviour of the application in a simple English text using Gherkin language.

### Cucumber

Cucumber makes it easy to read and to understand the application flow. This is Cucumber in action:

```java
Feature:   User authorization and authorization
Scenario:  The user can log into the system
Given  The user is registered in the system
When  The user enters a login
And  enters a password
Then  the user is logged in
```

* `Feature`  — describes which part of the functionality scenarios are being created for.
* `Scenario`  — is used to describe the test case title.
* `Given`  — describes pre-conditions required to complete the scenario.
* `When`  — is used to describe the scenario's steps.
* `Then`  — describes the expected result of the scenario.
* `And`  — can be used for Given, When and Then keywords to describe additional steps.

The user can understand the application code as it uses Gherkin language which is in plain text and which makes anyone in the organization able understand the behavior of the software and collaborate in designing the scenarios.


#### Cucumber and Selenium

Selenium is an extremely popular browser automation testing tool which is capable of interacting with the HTML elements of a web application to simulate user activity. It is the implementation layer behind the Cucumber steps. You give Cucumber a set of step definitions, which map the business-readable language of each step into code to carry out whatever action is being described by the step.

 🔍 In Dtective, we build many many steps so you don't have to implement them - they are ready to go! Checkout the [cheatsheet](https://catena-media.github.io/Dtective/docs/cheatsheet/) to get started.

 It is possible to add your own custom step definitions as well - take a look at [Extending steps](https://catena-media.github.io/Dtective/docs/steps/).

 We recommend to explore cucumber.io [documentation](https://cucumber.io/docs/cucumber/) for some deeper understanding on how Gherkin steps are connected to code.

### Gherkin

Gherkin uses, special keywords to give structure and meaning to executable specifications, like we have seen above in the Cucumber section. Each keyword is translated to many spoken languages but in Dtective we are using English.

#### Gherkin and Cucumber

Translating Gherkin scenarios to code uses a technology called Regular Expressions, or as it’s commonly referred to, regex. Cucumber uses Regex to scan the scenarios that we define for Gherkin’s keywords (rembember `Scenario`, `Given`, `When`, `Then`, and `And`?) and the phrases that after them.

When Cucumber finds a phrase that it recognises in one of our scenarios using regex, it translates that phrase into code by using **step definitions** .

Please take a look of the [Gherkin Syntax Reference](https://cucumber.io/docs/gherkin/reference/) for tips on using `Background` and `Data Tables`.

We encourage you to check the set of tests we use for Dtective framework itself to gather some inspiration.
