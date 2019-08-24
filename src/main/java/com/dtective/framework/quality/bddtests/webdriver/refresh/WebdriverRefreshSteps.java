package com.dtective.framework.quality.bddtests.webdriver.refresh;

import com.dtective.framework.test.TestStepsCore;
import cucumber.api.java.en.Given;

/**
 * Class which contains all the steps which
 * have the ability to refresh the currently open browser.
 *
 * @since 1.0.4
 */
public class WebdriverRefreshSteps extends TestStepsCore {


    /**
     * Simple browser refresh
     * which refreshes the currently open browser.
     *
     * @since 1.0.4
     */
    @Given("^I refreshed$")
    public void iRefreshed() {
        getProfile().browserRefresh();
    }
}
