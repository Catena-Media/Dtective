package quality.framework;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Hook for JUnit.
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = {"quality", "classpath:io.dtective"})

public class ExampleSiteSetupTest {
}
