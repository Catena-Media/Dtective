---
title: Contributing
tags: [contribute]
---

# Contributing to Dtective

We are glad you are here. We would like you to  take a moment to review this guide so the process is easy and effective for everyone involved. The contributions to Dtective are governed by our [Code of Conduct](https://github.com/Catena-Media/Dtective/blob/gh-pages/CODE_OF_CONDUCT.md).

## Using our Issue Tracker
We have prepared templates for [bug reports](https://github.com/Catena-Media/Dtective/issues/new?template=BUG.md) and [feature requests](https://github.com/Catena-Media/Dtective/issues/new?template=FEATURE.md) to help you formulate the issue at hand.

### Creating a Pull Request

Good pull requests are always welcomed! But keep in mind that they should remain focused in scope and avoid containing unrelated commits, as well include a test that exercises the added functionality. Before submitting one also consider to look at our Roadmap.

### Commit Format

Commit messages matter a lot to us. Before the following instructions, know that sometimes a single line is fine for simple changes.
The **type** and **subject** are mandatory.

```bash
 <type>: <subject>
 <body>
 <footer>
 ```

 **type** :
 * feat - new feature
 * fix - bug fix
 * docs - changes to documentation
 * style - formatting, no code change
 * refactor - refactoring code currently in production
 * test - add missing tests, refactoring tests
 * chore - infrastructure related file updates, bumping versions

 **subject** :
 * Use the imperative
 * Limit to 50 characters

 **body** :
 * Limit to 72 characters
 * Can include motivation for the change (what and why)

 **footer** :
 * For Closed/Resolved issues specifically (the number is from GitHub's Issue Tracker)
 * Example: Resolved #444



#### First timer?

We care to mark some few first issues that can be easier to tackle, make sure you take a look at them too.
Here are some steps we would like you to follow when creating a pull request:

1. [Fork](http://help.github.com/fork-a-repo/) the project, clone your fork,
   and configure the remotes:

   ```bash
   # Clone your fork of the repo into the current directory
   git clone https://github.com/<your-username>/<repo-name>
   # Navigate to the newly cloned directory
   cd <repo-name>
   # Assign the original repo to a remote called "upstream"
   git remote add upstream https://github.com/Catena-Media/Dtective/<repo-name>
   ```

2. If you cloned a while ago, get the latest changes from upstream:

   ```bash
   git checkout master
   git pull upstream master
   ```

3. Create a new topic branch (off the main project development branch) to
   contain your feature, change, or fix:

   ```bash
   git checkout -b <topic-branch-name>
   ```

4. Make sure to update, or add to the tests when appropriate.
Features will not be accepted without tests.

5. We also like to keep documentation clean and standard.
Please check the Javadocs section below for some guidance.


6. Push your topic branch up to your fork:

   ```bash
   git push origin <topic-branch-name>
   ```

8. Open a Pull Request with a clear title and description.

#### Bumping the version

The core maintainers will review your pull request and if merged we will bump the version of the framework ourselves
 including the newest changes in the Changelog. It is important to submit pull requests with clear description to make
 our Changelog readers life better.

#### Javadocs

We are using [Javadocs](https://www.tutorialspoint.com/java/java_documentation.htm) to generate user-facing documentation.
 This is only mandatory when introducing new features in `src > main > java > com.dtective.framework > quality`

The current standards are:

* for methods:

```java
/**
brief explanation of the method.

@param - what argument is expected
.. (list all @param if more than one)
@return - what the method returns
@since [the version which this method will be introduced in]
*/
```

* for classes:

```java
/**
* Brief explanation of the class

@since [the version which this class will be introduced in]
*/
```

## Setting Up the Code for Local Development

Please refer to the [installation](https://github.com/Catena-Media/Dtective/docs/installation) and
[editor configuration](https://github.com/Catena-Media/Dtective/docs/installation/ide) section in this site.


### Structure

There is plenty of material out there that explains thoroughly the concept of Behavior Driven Development.
In a nutshell, the goal of BDD is a business readable and domain-specific language that allows you to describe easily
how a system is supposed to be behaving without explaining how that behavior is implemented.

The implementation which supports the BDD layer (so called Step Definitions) can be found in
`src > java > com.dtective.framework`. We attempt to keep functionality grouped in a logical way.

Under `test > resources > quality` we have a set of sample tests that cover the functionality implemented which support the BDD steps. It is **highly** recommended that if you plan adding/modifying functionality you add/modify those tests to maintain the quality of the framework.

This is also a good starting point to understand how and what steps are available currently.

The magic hooks for JUnit live in `test > java > quality`.

### Testing

It is fundamental that fixes and new features pass our existing set of tests. This keeps the quality standards of the
 framework. To run the tests keep in mind the following:

 * In `build > environment.properties` there are several important parameters that can affect the test run. The
 `SeleniumHubUrl` should be pointing to the local Selenium Hub instance in combination with `IsRemoteInstance` set to
  `true`
 * To run all the tests run the Maven command `mvn test`

 **Note:** If experiencing difficulties, check documentation on [environment properties](https://github.com/Catena-Media/Dtective/docs/environmentproperties/)
  and [test environment](https://github.com/Catena-Media/Dtective/docs/testenvironment/) files.
