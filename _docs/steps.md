---
title: Steps implementation
subtitle: Want to extend existing functionality and create more steps? No problem!
tags: [customize]
author: evan
---
We encourage the extension of existing capabilities! To create a BDD step that fits
your particular business needs it is easy.

On the `test > java` folder create a file named at your convenience, for example `MySteps.java`.
Create a class just like the example below:

```java
public class Steps {

}
```

In IntelliJ is possible to generate a step template automatically with few key strokes:

[ create GIF and put it here]

To implement the step template in another editor is simply to add to the class created above

```java
public class Steps {

  @And("I create an entry named {string}")
  public void iCreateAnEntryNamed(String arg0) {
      // implementation logic here
  }

}
```

Where the `"I create an entry named {string}"` is the plain text sentence to be used
when describing the behaviour (on the feature file) and the `iCreateAnEntryNamed` is
the method associated that implements the desired logic. The `{string}` is a placeholder
for an argument that is passed to the method.

We **highly recommend** you to read the following from cucumber.io:
* [Step definitions](https://cucumber.io/docs/cucumber/step-definitions/)
* [Cucumber Reference](https://cucumber.io/docs/cucumber/api/)
