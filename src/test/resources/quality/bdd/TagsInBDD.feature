  # ----------------------------------------------------------------------------------------------- #
  # Execution How-To
  # ----------------------------------------------------------------------------------------------- #
  # Reference - https://github.com/cucumber/cucumber/wiki/Tags
  #
  # Example: Running scenarios which match @important OR @billing
  #
  #     cucumber --tags @Scenario1,@Scenario2
  #
  #
  # Example: Running scenarios which match @important AND @billing
  #
  #     cucumber --tags @Scenario1 --tags @Scenario2
  #
  #
  # Example: Running scenarios which match: (@billing OR @WIP) AND @important
  #
  #     cucumber --tags @Scenario1,@Scenario2 --tags @Scenario3
  #
  #
  # Example: Skipping both @Scenario1 and @Scenario2 tags
  #
  #     cucumber --tags ~@Scenario1 --tags ~@Scenario2


  @ExampleTestCategory1
  Feature: Tags in BDD test execution guideline

    @Scenario1
    Scenario: 1
      Given I am executing a simple script

    @Scenario2
    Scenario: 2
      Given I am executing a simple script

    @Scenario3
    Scenario: 3
      Given I am executing a simple script


