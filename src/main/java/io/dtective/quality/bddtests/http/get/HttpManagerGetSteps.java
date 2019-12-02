package io.dtective.quality.bddtests.http.get;

import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.test.TestDataCore;
import io.dtective.web.HttpManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import java.io.IOException;

/**
 * This class contains all steps which in perform an HTTP GET request.
 * Steps in this class include features such as: performing a GET request with
 * or without following redirects, storing the response in a data-store.
 *
 * @since 1.0
 */
public class HttpManagerGetSteps {

    /**
     * Sends an HTTP GET request to URL.
     * The step automatically follows redirects.
     *
     * @param url The URL of the API to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @And("^I send a GET to URL \"([^\"]*)\"$")
    public void sendGETtoUrl(String url) throws IOException {
        HttpManager.sendHTTPMethod("get", BDDPlaceholders.replace(url), "", null, true);
    }

    /**
     * Sends an HTTP GET to URL and route.
     * The step automatically follows redirects.
     *
     * @param url   The URL of the API to call.
     * @param route The endpoint to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @And("^I send a get to URL \"([^\"]*)\" with route \"([^\"]*)\"$")
    public void sendGETtoURLWithRoute(String url, String route) throws IOException {
        HttpManager.sendHTTPMethod("get", BDDPlaceholders.replace(url), BDDPlaceholders.replace(route), null, true);
    }

    /**
     * Sends an HTTP GET to a URL without following redirects.
     *
     * @param url The URL of the API to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @And("^I send a GET to URL \"([^\"]*)\" without following the redirects$")
    public void sendGETtoURLWithoutFollowingRedirects(String url) throws IOException {
        HttpManager.sendHTTPMethod("get", BDDPlaceholders.replace(url), "", null, false);
    }

    /**
     * Sends an HTTP GET to a URL and route without following the redirects.
     *
     * @param url   The URL of the API to call.
     * @param route The endpoint to call.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @And("^I send a GET to URL \"([^\"]*)\" with route \"([^\"]*)\" without following the redirects$")
    public void sendGETtoUrlWithRouteWithoutFollowingRedirects(String url, String route) throws IOException {
        HttpManager.sendHTTPMethod("get", BDDPlaceholders.replace(url), BDDPlaceholders.replace(route), null, false);
    }

    /**
     * Sends an HTTP GET to a URL and store the response in a specified data-store.
     *
     * @param url       The URL of the API to call.
     * @param dataStore The data-store name to save the response.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @When("^I send a GET to URL \"([^\"]*)\" and store response in data-store \"([^\"]*)\"$")
    public void sendGETtoURLSaveToDataStore(String url, String dataStore) throws IOException {
        HttpManager.sendHTTPMethod("get", BDDPlaceholders.replace(url), "", null, true);
        TestDataCore.addToDataStore(BDDPlaceholders.replace(dataStore), TestDataCore.getDataStore("resbody").toString());
    }

    /**
     * Sends an HTTP GET to a URL and route and save response in a specified data-store.
     *
     * @param url       The URL of the API to call.
     * @param route     The endpoint to call.
     * @param dataStore The data-store name to save the response.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @And("^I send a GET to URL \"([^\"]*)\" with route \"([^\"]*)\" and store response in data-store \"([^\"]*)\"$")
    public void sendGETtoURLWithRouteSaveToDataStore(String url, String route, String dataStore) throws IOException {
        HttpManager.sendHTTPMethod("get", BDDPlaceholders.replace(url), BDDPlaceholders.replace(route), null, true);
        TestDataCore.addToDataStore(BDDPlaceholders.replace(dataStore), TestDataCore.getDataStore("resbody").toString());
    }

    /**
     * Sends a GET to a URL and the route is obtained from the contents of the specified data-store.
     *
     * @param url       The URL of the API to call.
     * @param dataStore The data-store containing the value of the route to attach to a URL.
     * @throws IOException Exception thrown for file interaction errors.
     * @since 1.0
     */
    @When("^I send a GET to URL \"([^\"]*)\" with route from data-store \"([^\"]*)\"$")
    public void sendGETtoURLWithRouteFromDataStore(String url, String dataStore) throws IOException {
        String route = TestDataCore.getDataStore(BDDPlaceholders.replace(dataStore)).toString();
        HttpManager.sendHTTPMethod("get", BDDPlaceholders.replace(url), BDDPlaceholders.replace(route), null, true);
    }
}



