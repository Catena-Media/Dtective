package com.dtective.framework.quality.bddtests.webdriver.tabs;


import com.dtective.framework.test.TestStepsCore;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Class which contains all the steps related to browser tabs.
 *
 * @since 1.0.4
 */
public class WebdriverTabsSteps extends TestStepsCore {

    /**
     * Allows the current tab to be closed.
     *
     * @since 1.0.4
     */
    @And("^I close the current Tab$")
    public void iCloseTabs() {
        getProfile().closeTab();
    }

    /**
     * Opens a link in a new browser tab.
     *
     * @param xpath XPATH of the link to be opened on a new tab.
     * @since 1.0.4
     */
    @And("^I open link by XPATH \"([^\"]*)\" in a new Tab$")
    public void iOpenTabs(String xpath) {
        getProfile().openTab(By.xpath(xpath));
    }

    /**
     * Switches between the current tab and the next inactive tab in the browser.
     *
     * @since 1.0.4
     */
    @When("^I switch to the newly opened tab$")
    public void iSwitchToNewTab() {
        getProfile().switchToTab();
    }

}
