package io.dtective.quality.bdd;

import cucumber.api.java.en.Given;

/**
 * A simple class which contains a BDD test step.
 *
 * @since 1.0
 */
public class SimpleBDDSteps {

    /**
     * Prints Hello World to the output.
     *
     * @since 1.0
     */
    @Given("^I am executing a simple script$")
    public void iAmExecutingASimpleScript() {
        System.out.println("\n<--------------------------------->");
        System.out.println("\t\t\tHello World");
        System.out.println("<--------------------------------->");
    }
}
