package quality.bddtests.integrations.cloudprovider;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Hook for JUnit.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/quality/bddtests/integrations/cloudprovider/CloudProviderTests.feature"},
        glue = {"quality", "classpath:io.dtective"},
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "json:target/cucumber-report/report.json"})

public class CloudProviderTestsTest {
}
