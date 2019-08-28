package io.dtective.quality.bddtests.webdriver.refresh;

import io.dtective.test.TestStepsCore;
import cucumber.api.java.en.Given;

/**
 * Class which contains all the steps which
 * have the ability to refresh the currently open browser.
 *
 * @since 1.0
 */
public class WebdriverRefreshSteps extends TestStepsCore {


    /**
     * Simple browser refresh
     * which refreshes the currently open browser.
     *
     * @since 1.0
     */
    @Given("^I refreshed$")
    public void iRefreshed() {
        getProfile().browserRefresh();
    }
}
