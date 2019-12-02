package io.dtective.quality.bddtests.http.post;

import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.test.TestDataCore;
import io.dtective.web.HttpManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class contains steps related to the usage of HTTP POST methods.
 *
 * @since 1.0
 */
public class HttpManagerPostSteps {

    /**
     * Sends an HTTP POST to a URL with an empty body.
     * This is used in cases where query string parameters are used.
     *
     * @param url The URL of the API to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @When("^I send a POST to URL \"([^\"]*)\"$")
    public void iSendAPOSTToUrl(String url) throws IOException {
        HttpManager.sendHTTPMethod("post", url, "", null);
    }

    /**
     * Sends an HTTP POST to a URL and route with an empty body.
     * This is used in cases where query string parameters are used.
     *
     * @param url   The URL of the API to call.
     * @param route The endpoint to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @When("^I send a POST to URL \"([^\"]*)\" and route \"([^\"]*)\"$")
    public void iSendAPOSTToURLAndRoute(String url, String route) throws IOException {
        HttpManager.sendHTTPMethod("post", url, route, null);
    }

    /**
     * Sends a HTTP POST to a URL with request body containing the value of the data-store named
     * 'requestBody'. The HTTP Header Content-Type is set as application/json.
     *
     * @param url The URL of the API to call.
     * @throws IOException Exception thrown for file interaction errors.           .
     * @since 1.0
     */
    @When("^I send a POST to URL \"([^\"]*)\" with the JSON request body$")
    public void iSendAPOSTToURLWithTheJSONRequestBody(String url) throws IOException {
        HttpManager.sendHTTPMethod("post", url, "", new String[]{"dataStore", "requestBody"});
    }

    /**
     * Sends a HTTP POST to a URL and route with request body containing the value of the data-store named
     * 'requestBody'. The HTTP Header Content-Type is set as application/json.
     *
     * @param url   The URL of the API to call.
     * @param route The endpoint to call.
     * @throws IOException Exception thrown for file interaction errors.           .
     * @since 1.0
     */
    @When("^I send a POST to URL \"([^\"]*)\" and route \"([^\"]*)\" with the JSON request body$")
    public void iSendAPOSTToURLAndRouteWithTheJSONRequestBody(String url, String route) throws IOException {
        HttpManager.sendHTTPMethod("post", BDDPlaceholders.replace(url), BDDPlaceholders.replace(route),
                new String[]{"dataStore", "requestBody"});
    }


    /**
     * Sends an HTTP POST to a URL with request body containing key value pairs using Form data.
     * As a request body it sends the contents of the data-store named 'form'.
     *
     * @param url The URL of the API to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @When("^I send a POST to URL \"([^\"]*)\" with form data$")
    public void iSendAPOSTFormWithKeyAndValueToUrl(String url) throws IOException {
        HashMap formData = (HashMap) TestDataCore.getDataStore("form");
        TestDataCore.addToDataStore("postFormData", formData);

        HttpManager.sendHTTPMethod("post", BDDPlaceholders.replace(url), "", new String[]{"dataStore", "postFormData"});
    }

    /**
     * Sends an HTTP POST to a URL with request body containing key value pairs using Form data.
     * As a request body it sends the contents of the data-store named 'form'.
     *
     * @param url   The URL of the API to call.
     * @param route The endpoint to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @When("^I send a POST to URL \"([^\"]*)\" and route \"([^\"]*)\" with form data$")
    public void iSendAPOSTFormWithKeyAndValueToURLAndRoute(String url, String route) throws IOException {
        HashMap formData = (HashMap) TestDataCore.getDataStore("form");
        TestDataCore.addToDataStore("postFormData", formData);

        HttpManager.sendHTTPMethod("post", BDDPlaceholders.replace(url), BDDPlaceholders.replace(route),
                new String[]{"dataStore", "postFormData"});
    }

    /**
     * Sends an HTTP POST to URL with the request JSON body retrieved from the specified local file.
     * The file path needs to be relative to the project directory.
     *
     * @param url  The URL of the API to call.
     * @param file The file path and name to file containing JSON object.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @And("^I send a POST to URL \"([^\"]*)\" with request body from file \"([^\"]*)\"$")
    public void iSendAPOSTToURLWithJSONRequestBodyFromFile(String url, String file) throws IOException {
        HttpManager.sendHTTPMethod("post", BDDPlaceholders.replace(url), "", new String[]{"file", BDDPlaceholders.replace(file)});
    }

    /**
     * Sends an HTTP POST to URL with the request JSON body retrieved from the specified local file.
     * The file path needs to be relative to the project directory.
     *
     * @param url   The URL of the API to call.
     * @param route The endpoint to call.
     * @param file  The file path and name to file containing JSON object.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @And("^I send a POST to URL \"([^\"]*)\" and route \"([^\"]*)\" with request body from file \"([^\"]*)\"$")
    public void iSendAPOSTToURLAndRouteWithJSONRequestBodyFromFile(String url, String route, String file) throws IOException {
        HttpManager.sendHTTPMethod("post", BDDPlaceholders.replace(url), BDDPlaceholders.replace(route),
                new String[]{"file", BDDPlaceholders.replace(file)});
    }

    /**
     * Sends an HTTP POST to a URL and route with the request body containing a JSON object retrieved from
     * a data-store.
     * The HTTP Header Content-Type is set as application/json.
     *
     * @param url       The URL of the API.
     * @param dataStore The name of the data-store containing the body to send with the call.
     * @throws IOException Exception thrown for file interaction errors..
     * @since 1.0
     */
    @And("^I send a POST to URL \"([^\"]*)\" with request body from data-store \"([^\"]*)\"$")
    public void iSendAPOSTToURLWithBodyFromDataStore(String url, String dataStore) throws
            IOException {
        HttpManager.sendHTTPMethod("post", BDDPlaceholders.replace(url), "", new String[]{"dataStore",
                BDDPlaceholders.replace(dataStore)});
    }

    /**
     * Sends an HTTP POST to a URL and route with the request body containing a JSON object retrieved from
     * a data-store.
     * The HTTP Header Content-Type is set as application/json.
     *
     * @param url       The URL of the API.
     * @param route     The endpoint to call.
     * @param dataStore The name of the data-store containing the body to send with the call.
     * @throws IOException Exception thrown for file interaction errors..
     * @since 1.0
     */
    @And("^I send a POST to URL \"([^\"]*)\" and route \"([^\"]*)\" with request body from data-store \"([^\"]*)\"$")
    public void iSendAPOSTToURLWithBodyFromDataStore(String url, String route, String dataStore) throws
            IOException {
        HttpManager.sendHTTPMethod("post", BDDPlaceholders.replace(url), BDDPlaceholders.replace(route),
                new String[]{"dataStore", BDDPlaceholders.replace(dataStore)});
    }
}
