package quality.bddtests.webdriver.scrollTo.xpath;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Hook for JUnit.
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = {"quality", "classpath:com.dtective.framework.quality"},
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "json:target/cucumber-report/report.json"})

public class WebdriverScrollToXpathTest {
}
