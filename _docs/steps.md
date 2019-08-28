---
title: Extending steps
tags: [test]
---
We encourage the extension of existing capabilities! To create a BDD step that fits
your particular business needs is easy.

On the `test > java` folder create a file named at your convenience, for example `MySteps.java`.
Create a class just like the example below:

```java
public class Steps {

}
```

In IntelliJ is possible to generate a step template automatically with few key strokes:
 - On the feature file write the sentence that will represent the step, for example `And I create an entry named "Jane Doe"`.
 - The sentence will be highlighted as the step is not implemented
 - On the keyboard do `option + enter` with the cursor on the undefined sentence
 - Select `create step definition` on the dropdown
 - Select the above file created `MySteps.java` and a template of the step will be automatically generated in that file

To implement the step template in another editor is simply to add to the class created above

```java
public class Steps {

  @And("I create an entry named {string}")
  public void iCreateAnEntryNamed(String arg0) {
      // implementation logic here
  }

}
```

Each steps starts with `Given`, `When`, `Then`, `And` or `But`.

 `"I create an entry named {string}"` is the plain text sentence to be used
when describing the behaviour on the feature file.

The `iCreateAnEntryNamed` is the method associated that implements the desired logic and `{string}` is a placeholder
for an argument that is passed to the method.

We **highly recommend** you to read the [Step definitions](https://cucumber.io/docs/cucumber/step-definitions/)
article from cucumber.io itself.
