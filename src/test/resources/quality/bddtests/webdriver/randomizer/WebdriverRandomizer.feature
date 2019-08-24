@UnitTest
@InternalSeleniumTest
Feature: Web-Driver Text Randomizer

  Scenario Outline: Different types of usages with randomization
    Given I create a new category with name "Category <type> <newrandom> test"
    Then text displays "Category <type> <samerandom> test"
    And I deleted category "Category <type> <samerandom> test"

    Examples:
      | type                 | newrandom                                        | samerandom  |
      | simple               | @Randomize(5)                                    | @LastRandom |
      | long                 | @Randomize(50)                                   | @LastRandom |
      | number only          | @Randomize(10,charset:numbers)                   | @LastRandom |
      | text only            | @Randomize(10,charset:letters)                   | @LastRandom |
      | text only uppercase  | @Randomize(10,charset:letters-uppercase)         | @LastRandom |
      | text only lowercase  | @Randomize(10,charset:letters-lowercase)         | @LastRandom |
      | number and lowercase | @Randomize(10,charset:numbers-letters-lowercase) | @LastRandom |
      | number and uppercase | @Randomize(10,charset:numbers-letters-uppercase) | @LastRandom |
      # Country specific character sets
      | chinese              | @Randomize(10,charset:chinese)                   | @LastRandom |
      | runic                | @Randomize(10,charset:runic)                     | @LastRandom |
      | greek                | @Randomize(10,charset:greek)                     | @LastRandom |
      | russian              | @Randomize(10,charset:russian)                   | @LastRandom |
      | georgian             | @Randomize(10,charset:georgian)                  | @LastRandom |
      | brailles             | @Randomize(10,charset:brailles)                  | @LastRandom |
      | ALL-Languages        | @Randomize(10,charset:all-languages)             | @LastRandom |
      | ASCII                | @Randomize(30,charset:ASCII)                     | @LastRandom |