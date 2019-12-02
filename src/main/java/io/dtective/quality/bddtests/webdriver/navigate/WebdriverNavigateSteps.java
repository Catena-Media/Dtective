package io.dtective.quality.bddtests.webdriver.navigate;

import io.dtective.configuration.ParameterMap;
import io.dtective.test.TestStepsCore;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class which contains all the steps to navigate to webpages and assert URL routes.
 *
 * @since 1.0
 */
public class WebdriverNavigateSteps extends TestStepsCore {

    private static boolean initialized = false;

    /**
     * Allows the user to navigate to a given URL.
     *
     * @param url the URL to navigate to.
     * @since 1.0
     */
    @When("^I open website \"([^\"]*)\"$")
    public void iOpenWebsite(String url) {
        getProfile().navigateTo(url);
    }

    @Given("^I Opened \"([^\"]*)\"$")
    public void iOpened(String url) {
        getProfile().navigateTo(url);
    }

    /**
     * Allows the user to navigate to the URL that is composed of the
     * WebAppUrl parameter in environment.properties and the route given.
     *
     * @param url the route to be appended to the WebAppUrl.
     * @since 1.0
     */
    @Given("^I am viewing route \"([^\"]*)\"")
    public void iAmViewingRoute(String url) {
        getProfile().navigateTo(ParameterMap.getParamWebAppUrl() + url);
    }

    /**
     * Navigates to the target url
     *
     * @param url target url
     * @since 1.0.0
     */
    @Then("^I am viewing \"([^\"]*)\"")
    public void iAmViewing(String url) {
        getProfile().navigateTo(placeholders(url));
    }

    /**
     * Navigates to the WebApp URL set in environment.properties.
     *
     * @since 1.0
     */
    @Given("^I Opened target application$")
    public void iOpenedTargetApplication() {
        getProfile().navigateTo(ParameterMap.getParamWebAppUrl());
    }

    /**
     * Asserts if the URL contains a given substring
     *
     * @param url substring of the URL to be asserted.
     * @since 1.0
     */
    @Then("^url contains \"([^\"]*)\"$")
    public void urlContains(String url) {
        url = placeholders(url);
        if (!getProfile().getCurrentUrl().contains(url)) {
            Assert.fail("Url did not contain the following - " + url + "\n"
                    + "Actual url was: " + getProfile().getCurrentUrl());
        }
    }

    /**
     * Asserts if the URL contains the WebApp URL set in environment.properties.
     *
     * @since 1.0
     */
    @Then("^url contains Target Url$")
    public void urlContainsTargetUrl() {
        if (!getProfile().getCurrentUrl().contains(ParameterMap.getParamWebAppUrl())) {
            Assert.fail("Url did not contain the following - " + ParameterMap.getParamWebAppUrl());
        }
    }

    /**
     * Navigates the user to the previous page.
     *
     * @since 1.0
     */
    @And("^I press browser back$")
    public void iPressBrowserBack() {
        driver().navigate().back();
    }

    /**
     * Opens a local HTML file in browser. The file has to be relative to the repository location.
     *
     * @param file the path and file to open in browser
     */
    @When("I open local HTML file {string}")
    public void iOpenLocalHTMLFile(String file) {
        file = placeholders(file);
        File htmlFile = new File(System.getProperty("user.dir") + file);

        try {
            if (!htmlFile.exists())
                throw new FileNotFoundException();

            getProfile().navigateTo("file://" + htmlFile.getPath());
        } catch (FileNotFoundException e) {
            Assert.fail("Opening HTML failed! Make sure that the file '" + htmlFile.getPath() + "' exists.");
        }
    }
}
