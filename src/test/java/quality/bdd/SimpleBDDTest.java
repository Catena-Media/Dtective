package quality.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Hook for JUnit.
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = {"quality.framework.http", "quality.bdd"})

public class SimpleBDDTest {
}
