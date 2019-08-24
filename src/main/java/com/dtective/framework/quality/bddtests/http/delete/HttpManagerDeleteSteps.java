package com.dtective.framework.quality.bddtests.http.delete;

import com.dtective.framework.web.HttpManager;
import cucumber.api.java.en.When;

import java.io.IOException;

/**
 * This class contains steps related to the usage of HTTP POST methods.
 *
 * @since 1.0.4
 */
public class HttpManagerDeleteSteps {

    /**
     * Sends an HTTP POST to a URL with an empty body.
     * This is used in cases where query string parameters are used.
     *
     * @param url The URL of the API to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0.4
     */
    @When("^I send a DELETE to URL \"([^\"]*)\"$")
    public void iSendADELETEToUrl(String url) throws IOException {
        HttpManager.sendHTTPMethod("delete", url, "", null);
    }

    /**
     * Sends an HTTP DELETE to a URL and route with an empty body.
     * This is used in cases where query string parameters are used.
     *
     * @param url   The URL of the API to call.
     * @param route The endpoint to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0.4
     */
    @When("^I send a DELETE to URL \"([^\"]*)\" and route \"([^\"]*)\"$")
    public void iSendADELETEToURLAndRoute(String url, String route) throws IOException {
        HttpManager.sendHTTPMethod("delete", url, route, null);
    }
}
