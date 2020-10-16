package io.dtective.test;

import io.dtective.configuration.ParameterMap;
import io.dtective.exceptions.RegisterFailure;
import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.selenium.Extensions.QAWebDriver;
import io.dtective.user.QAUserProfile;
import cucumber.api.Scenario;
import io.dtective.webdrivers.CloudWebDriverCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AssumptionViolatedException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Base for any test step implementation
 */
public class TestStepsCore {

    private Logger logger = LogManager.getLogger(TestStepsCore.class);

    public static Scenario getScenario() {
        return (Scenario) TestDataCore.getConfigStore("scenario");
    }

    public static QAUserProfile getProfile() {
        return (QAUserProfile) TestDataCore.getConfigStore("profile");
    }

    protected QAWebDriver driver() {
        return SeleniumCore.getWebDriver();
    }

    protected String placeholders(String text) {
        return BDDPlaceholders.replace(text);
    }

    /**
     * Enforce test preparation implementation.
     *
     * @param scenario - BDD Scenario hook
     */
    public void before(Scenario scenario) {

        logger.trace("Initializing Test-Framework for : " + scenario.getName());

        if (scenario.getSourceTagNames().contains("@disabled-" + ParameterMap.getParamBrowserType())) {
            throw new AssumptionViolatedException(String.format("The feature %s is disabled on browser "
                    + "type : %s", scenario.getName(), ParameterMap.getParamBrowserType()));
        }
        if (scenario.getSourceTagNames().contains("@disabled")) {
            throw new AssumptionViolatedException(
                    String.format("The feature %s is disabled %s", scenario.getName(),
                            ParameterMap.getParamBrowserType()));
        }

        TestDataCore.addToConfigStore("scenario", scenario);
        TestDataCore.addToConfigStore("profile", new QAUserProfile(scenario.getName()));

        if (ParameterMap.getParamAPIClearHeadersBeforeScenario()) {
            TestDataCore.removeFromDataStore("headers");
        }
    }

    /**
     * Enforce test cleanup.
     */
    public void after() {

        if (TestStepsCore.getScenario() != null) {
            if (!ParameterMap.getParamCloudProvider().isEmpty()) {
                try {
                    if (TestStepsCore.getScenario().isFailed()) {
                        RegisterFailure.call();
                        CloudWebDriverCapabilities.markTestResult("failed");
                    } else {
                        CloudWebDriverCapabilities.markTestResult("passed");
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                logger.trace("Stopping Test-Framework for : " + getScenario().getName());

                if (TestStepsCore.getScenario().isFailed()) {
                    RegisterFailure.call();
                }
            }

            SeleniumCore.dispose();
        }
    }
}
