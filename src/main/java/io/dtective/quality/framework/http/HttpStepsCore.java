package io.dtective.quality.framework.http;

import io.dtective.configuration.ParameterMap;
import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.test.TestDataCore;
import io.dtective.user.QAUserProfile;
import io.dtective.web.HttpManager;
import io.dtective.web.HttpResponseWrapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class which contains the steps to interact with APIs.
 *
 * @since 1.0
 */
public class HttpStepsCore {

    /**
     * Class Logger.
     */
    private static Logger classLogger =
            LogManager.getLogger(HttpStepsCore.class);

    /**
     * Adds an HTTP Header to the data-store named 'headers'.
     *
     * @param key   the key of the header to add.
     * @param value the value of the header to add.
     * @since 1.0
     */
    @And("^I add HTTP Header \"([^\"]*)\" value \"([^\"]*)\"$")
    @SuppressWarnings("unchecked")
    public static void iAddHTTPHeaderValue(String key, String value) {

        if (TestDataCore.getDataStore("headers") == null) {
            TestDataCore.addToDataStore("headers", new HashMap<String, Object>());
        }

        ((Map<String, Object>) TestDataCore.getDataStore("headers")).put(BDDPlaceholders.replace(key), BDDPlaceholders.replace(value));
    }

    /**
     * Generates a new ObjectID.
     *
     * @return String containing ObjectID
     * @since 1.0
     */
    private static String newObjectId() {
        return new ObjectId().toString();
    }

    /**
     * Verifies that the concatenation of url and route has the necessary forward slash in between.
     *
     * @param url   The url to check.
     * @param route The route to concatenate with url.
     * @return The modified route if necessary.
     * @since 1.0
     */
    public static String returnCorrectURI(String url, String route) {
        String uri;

        if ((url.endsWith(("/")) && (route.startsWith("/")))) {
            uri = url + route.substring(1);
        } else {
            if ((!url.endsWith(("/")) && (!route.startsWith("/")))) {
                if (route.length() > 0) {
                    uri = url + "/" + route;
                } else {
                    uri = url;
                }
            } else {
                uri = url + route;
            }
        }

        uri = uri.replaceAll("(?<!:)//", "/");

        return uri;
    }

    /**
     * Return the response body from the last executed HTTP request as a JSON
     *
     * @return JsonObject containing response body
     */
    public static JsonObject getResponseBodyAsJson() {
        HttpResponseWrapper response = (HttpResponseWrapper) TestDataCore.getDataStore("response");
        String responseBody = response.getHttpResponseBody();

        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(responseBody).getAsJsonObject();
    }

    public static void logResponse(HttpResponseWrapper response) {
        if (ParameterMap.getParamAPILoggingEnabled().equals("true")) {

            if (response == null || response.getHttpResponseBody().equals("Empty Response")) {
                appendEmptyResponseDataToScenario();
            } else {
                appendResponseDataToScenario(response);
            }
        }
    }

    private static void appendResponseDataToScenario(HttpResponseWrapper response) {
        final int jsonIndentation = 4;


        QAUserProfile.getCurrent().appendTextLogsToScenario(
                "API Response Status:", response.getResponse().getStatusLine().toString());
        QAUserProfile.getCurrent().appendTextLogsToScenario(
                "API Response Headers:", Arrays.toString(response.getResponse().getAllHeaders()));

        try {
            new JSONObject(response.getHttpResponseBody());
            QAUserProfile.getCurrent().appendTextLogsToScenario(
                    "API Response Payload:", new JSONObject(response.getHttpResponseBody()).toString(jsonIndentation)
            );
        } catch (Exception e) {
            QAUserProfile.getCurrent().appendTextLogsToScenario(
                    "API Response Payload:", response.getHttpResponseBody()
            );
        }
    }

    private static void appendEmptyResponseDataToScenario() {
        QAUserProfile.getCurrent().appendTextLogsToScenario(
                "API Response Status:", "No Response"
        );
        QAUserProfile.getCurrent().appendTextLogsToScenario(
                "API Response Headers:", "No Response"
        );
        QAUserProfile.getCurrent().appendTextLogsToScenario(
                "API Response Payload:", "No Response"
        );
    }

    public static void logRequest(String requestMethod, String requestUrl, String requestHeaders, String
            requestBody) {
        if (ParameterMap.getParamAPILoggingEnabled().equals("true")) {
            appendRequestDataToScenario(requestMethod, requestUrl, requestHeaders, requestBody);
        }
    }

    private static void appendRequestDataToScenario(String requestMethod, String requestUrl, String requestHeaders, String requestBody) {
        final int jsonIndentation = 4;

        QAUserProfile.getCurrent().appendTextLogsToScenario(
                "API Request URL :", requestMethod.toUpperCase() + " " + requestUrl
        );
        QAUserProfile.getCurrent().appendTextLogsToScenario(
                "API Request Headers:", requestHeaders
        );

        try {
            new JSONObject(requestBody);
            QAUserProfile.getCurrent().appendTextLogsToScenario(
                    "API Request Payload:", new JSONObject(requestBody).toString(jsonIndentation)
            );
        } catch (Exception e) {
            QAUserProfile.getCurrent().appendTextLogsToScenario(
                    "API Request Payload:", requestBody

            );
        }
    }

    /**
     * Gets the response status code from the data-store named as 'response'.
     *
     * @param responseCode the expected response code.
     * @since 1.0
     */
    @Then("^response code is \"([^\"]*)\"$")
    public void iGetBackResponseCode(String responseCode) {

        HttpResponseWrapper response = (HttpResponseWrapper) TestDataCore.getDataStore("response");

        HttpManager.assertHTTPResponse(response, Integer.parseInt(BDDPlaceholders.replace(responseCode)));
    }

    /**
     * Adds an HTTP form-data to the data-store named 'headers'.
     *
     * @param key   The key of the form-data to add.
     * @param value The value of the form-data to add.
     * @since 1.0
     */
    @And("I add HTTP FORM data with key \"([^\"]*)\" and value \"([^\"]*)\"$")
    public void iSetFormData(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        if (TestDataCore.getDataStore("form") == null) {
            TestDataCore.addToDataStore("form", new HashMap<String, Object>());
        }

        ((Map<String, Object>) TestDataCore.getDataStore("form")).put(key, value);

    }

    /**
     * Adds an HTTP Form from file.
     *
     * @param path the path of the file.
     * @param key  the key to add
     * @since 1.0
     */
    @And("^I add HTTP Form file with path \"([^\"]*)\" and key \"([^\"]*)\"$")
    @SuppressWarnings("unchecked")
    public void iAddHTTPFormFile(String path, String key) {
        path = BDDPlaceholders.replace(path);
        key = BDDPlaceholders.replace(key);

        File file = new File(path);

        if (TestDataCore.getDataStore("form") == null) {
            TestDataCore.addToDataStore("form", new HashMap<String, Object>());
        }

        ((Map<String, Object>) TestDataCore.getDataStore("form")).put(key, file);
    }

    /**
     * Adds an HTTP Form from a file within a folder.
     *
     * @param folder the location of the folder.
     * @param key    the key to add
     * @since 1.0
     */
    @And("^I add HTTP Form file folder \"([^\"]*)\" and key \"([^\"]*)\"$")
    public void iAddHTTPFormFileFolder(String folder, String key) {
        folder = BDDPlaceholders.replace(folder);
        key = BDDPlaceholders.replace(key);

        File targetFolder = new File(folder);

        if (!targetFolder.isDirectory()) {
            throw new InvalidParameterException("Target is not a folder : " + folder);
        }

        for (File file : Objects.requireNonNull(targetFolder.listFiles())
        ) {
            iAddHTTPFormFile(file.getPath(), key);
        }
    }

    /**
     * Stores the URL redirections from the data-store named 'context' to a given data-store.
     *
     * @param localParamName the data-store name where to save the URL redirections.
     * @since 1.0
     */
    @And("^I store URL redirections in param \"([^\"]*)\"$")
    public void iStoreUrlRedirectionsInParam(String localParamName) {
        localParamName = BDDPlaceholders.replace(localParamName);

        HttpClientContext context = (HttpClientContext) TestDataCore.getDataStore("context");

        Assert.assertNotNull("Response cannot be null", context);
        List<URI> locList = context.getRedirectLocations();

        TestDataCore.addToDataStore(localParamName, locList);
    }

    /**
     * Asserts if the response URL is stored in a specified parameter against a specified URL.
     *
     * @param param       the parameter to get the response URL.
     * @param expectedURL the expected URL to assert.
     * @since 1.0
     */
    @Then("^response URL in param \"([^\"]*)\" is \"([^\"]*)\"$")
    public void responseURLInParamIs(String param, String expectedURL) {
        param = BDDPlaceholders.replace(param);
        expectedURL = BDDPlaceholders.replace(expectedURL);

        List<URI> urls = (List<URI>) TestDataCore.getDataStore(param);
        Assert.assertEquals(expectedURL, urls.get(urls.size() - 1).toString());
    }

    /**
     * Asserts if the header value from the data-store named 'response'
     * contains a given value.
     *
     * @param header        the header key to retrieve value.
     * @param expectedValue the expected header value.
     * @throws NullPointerException exception thrown if header is not found.
     * @since 1.0
     */
    @Then("^header \"([^\"]*)\" contains value of \"([^\"]*)\"$")
    public void headerContainsValueOf(String header, String expectedValue) throws NullPointerException {
        header = BDDPlaceholders.replace(header);
        expectedValue = BDDPlaceholders.replace(expectedValue);

        String actualHeaderValue = getHeaderValueFromResponse(header);
        if (actualHeaderValue != null) {
            Assert.assertTrue("Header value did not contain the expected value. Header value was: " + actualHeaderValue,
                    actualHeaderValue.contains(expectedValue));
        } else {
            Assert.fail("Header did not contain a value");
        }
    }

    /**
     * This method gets the value of a specific header from the 'headers' data store.
     *
     * @param header The header for which to get the value.
     * @return The value of the header.
     */
    private String getHeaderValueFromResponse(String header) {
        header = BDDPlaceholders.replace(header);

        HttpResponseWrapper response = (HttpResponseWrapper) TestDataCore.getDataStore("response");

        try {
            return response.getResponse().getFirstHeader(header).getValue();
        } catch (NullPointerException e) {
            Assert.fail("Header " + header + " does not exist.");
        }
        return null;
    }

    /**
     * Asserts if the header value from data-store named 'response'
     * is equal to a given value.
     *
     * @param header        the header key to retrieve value.
     * @param expectedValue the expected header value.
     * @since 1.0
     */
    @Then("^header \"([^\"]*)\" is equal to the value of \"([^\"]*)\"$")
    public void headerIsEqualToTheValueOf(String header, String expectedValue) {
        header = BDDPlaceholders.replace(header);
        expectedValue = BDDPlaceholders.replace(expectedValue);

        Assert.assertEquals(expectedValue, Objects.requireNonNull(getHeaderValueFromResponse(header)));
    }

    /**
     * Changes the value of a key in a JSON Object to a null and stores it in a parameter.
     *
     * @param key       the key to modify its value.
     * @param bodyParam stored parameter which contains the JSON.
     * @param newParam  the name of the stored parameter to save the modified JSON Object.
     * @since 1.0
     */
    @And("^I change value of key \"([^\"]*)\" in JSON from data-store \"([^\"]*)\" to null and store it in data-store"
            + " \"([^\"]*)\"$")
    public void iChangeValueOfKeyInJSONFromStoredParamToNullAndSaveItInStoredParam(String key, String bodyParam,
                                                                                   String newParam) {
        key = BDDPlaceholders.replace(key);
        bodyParam = BDDPlaceholders.replace(bodyParam);
        newParam = BDDPlaceholders.replace(newParam);

        JSONObject jsonBody = new JSONObject(TestDataCore.getDataStore(bodyParam).toString());

        String[] keys = key.split(",");

        for (int i = 0; i <= keys.length - 1; i++) {
            jsonBody.put(keys[i], JSONObject.NULL);
        }

        String body = jsonBody.toString();
        TestDataCore.addToDataStore(newParam, body);
    }

    /**
     * Changes the value of a key in a JSON Object to true and saves it in a stored parameter.
     *
     * @param key       the key to modify its value.
     * @param bodyParam the name of the stored parameter containing the JSON Object.
     * @param newParam  the name of the stored parameter to save the modified JSON Object.
     * @since 1.0
     */
    @And("^I change value of key \"([^\"]*)\" in JSON from data store \"([^\"]*)\" to true and store it in data "
            + "store \"([^\"]*)\"$")
    public void iChangeValueOfKeyInJSONFromStoredParamToTrueAndSaveItInStoredParam(String key, String bodyParam,
                                                                                   String newParam) {
        key = BDDPlaceholders.replace(key);
        bodyParam = BDDPlaceholders.replace(bodyParam);
        newParam = BDDPlaceholders.replace(newParam);

        JSONObject jsonBody = new JSONObject(TestDataCore.getDataStore(bodyParam).toString());

        String[] keys = key.split(",");

        for (int i = 0; i <= keys.length - 1; i++) {
            jsonBody.put(keys[i], true);
        }

        String body = jsonBody.toString();
        TestDataCore.addToDataStore(newParam, body);
    }

    /**
     * Changes the value of a key in a JSON Object to false and saves it in a stored parameter.
     *
     * @param key       the key to modify its value.
     * @param bodyParam the name of the data-store containing the JSON Object.
     * @param newParam  the name of the data-store to save the modified JSON Object.
     * @since 1.0
     */
    @And("^I change value of key \"([^\"]*)\" in JSON from data-store \"([^\"]*)\" to false and store it in "
            + "data-store \"([^\"]*)\"$")
    public void iChangeValueOfKeyInJSONFromStoredParamToFalseAndSaveItInStoredParam(String key, String bodyParam,
                                                                                    String newParam) {
        key = BDDPlaceholders.replace(key);
        bodyParam = BDDPlaceholders.replace(bodyParam);
        newParam = BDDPlaceholders.replace(newParam);

        JSONObject jsonBody = new JSONObject(TestDataCore.getDataStore(bodyParam).toString());

        String[] keys = key.split(",");

        for (int i = 0; i <= keys.length - 1; i++) {
            jsonBody.put(keys[i], false);
        }

        String body = jsonBody.toString();
        TestDataCore.addToDataStore(newParam, body);
    }

    /**
     * Changes the value of a key in a JSON Object to a value from a stored parameter
     * and saves it in another given stored parameter.
     *
     * @param key        the key to modify its value.
     * @param bodyParam  the name of the stored parameter containing the JSON Object.
     * @param valueParam the parameter with the value to be set.
     * @param newParam   the name of the stored parameter to save the modified JSON Object.
     * @since 1.0
     */
    @And("^I change value of key \"([^\"]*)\" in JSON from data-store \"([^\"]*)\" with value from data-store \""
            + "([^\"]*)\" and store it in data-store \"([^\"]*)\"$")
    public void iChangeValueOfKeyInJSONFromStoredParamWithValueFromStoredParamAndSaveItInStoredParam(String key,
                                                                                                     String bodyParam,
                                                                                                     String valueParam,
                                                                                                     String newParam) {
        key = BDDPlaceholders.replace(key);
        bodyParam = BDDPlaceholders.replace(bodyParam);
        valueParam = BDDPlaceholders.replace(valueParam);
        newParam = BDDPlaceholders.replace(newParam);

        JSONObject jsonBody = new JSONObject(TestDataCore.getDataStore(bodyParam).toString());

        String[] keys = key.split(",");

        for (int i = 0; i <= keys.length - 1; i++) {
            jsonBody.put(keys[i], TestDataCore.getDataStore(valueParam));
        }

        String body = jsonBody.toString();
        TestDataCore.addToDataStore(newParam, body);
    }

    /**
     * Changes the value of a key in a JSON Object saved in a stored parameter, which has a unique Mongo ObjectID,
     * and saves the modified JSON in a stored parameter.
     *
     * @param key       the key for which value will be set with the ObjectID value.
     * @param bodyParam the stored parameter containing the unmodified JSON Object.
     * @param param     the stored param which will save the modified JSON Object.
     * @since 1.0
     */
    @And("^I change value of key \"([^\"]*)\" in JSON from data-store \"([^\"]*)\" with a unique ID and store "
            + "modified JSON in data-store \"([^\"]*)\"$")
    public void iChangeValueOfKeyInJSONFromStoredParamWithAUniqueIDAndSaveModifiedJSONInStoredParm(String key,
                                                                                                   String bodyParam,
                                                                                                   String param) {
        key = BDDPlaceholders.replace(key);
        bodyParam = BDDPlaceholders.replace(bodyParam);
        param = BDDPlaceholders.replace(param);

        JSONObject jsonBody = new JSONObject(TestDataCore.getDataStore(bodyParam).toString());

        String[] keys = key.split(",");

        for (int i = 0; i <= keys.length - 1; i++) {
            jsonBody.put(keys[i], newObjectId());
        }

        String body = jsonBody.toString();
        TestDataCore.addToDataStore(param, body);
    }

    /**
     * Changes the value of a key in a JSON Object to a given value and stores it in a parameter.
     *
     * @param key       the key to modify its value.
     * @param bodyParam the name of the stored parameter containing the JSON Object.
     * @param value     the value to be set.
     * @param newParam  the name of the stored parameter to save the modified JSON Object.
     * @since 1.0
     */
    @And("^I change value of key \"([^\"]*)\" in JSON from data-store \"([^\"]*)\" with value \"([^\"]*)\" and store"
            + " it in data-store \"([^\"]*)\"$")
    public void iChangeValueOfKeyInJSONFromStoredParamWithValueAndSaveItInStoredParam(String key, String bodyParam,
                                                                                      String value,
                                                                                      String newParam) {
        key = BDDPlaceholders.replace(key);
        bodyParam = BDDPlaceholders.replace(bodyParam);
        value = BDDPlaceholders.replace(value);
        newParam = BDDPlaceholders.replace(newParam);

        JSONObject jsonBody = new JSONObject(TestDataCore.getDataStore(bodyParam).toString());

        String[] keys = key.split(",");

        for (int i = 0; i <= keys.length - 1; i++) {
            jsonBody.put(keys[i], value);
        }

        String body = jsonBody.toString();
        TestDataCore.addToDataStore(newParam, body);
    }

    /**
     * Adds a key to the stored parameter named 'requestBody' with the value
     * from the contents of another given stored parameter.
     *
     * @param key   the key to add the JSON in the stored parameter named 'requestBody'.
     * @param param the parameter containing the value.
     * @since 1.0
     */
    @And("^I add new JSON request body key \"([^\"]*)\" with value from data-store \"([^\"]*)\"$")
    public void iAddToTheJSONRequestBodyAKeyWithValueFromStoredParam(String key, String param) {
        key = BDDPlaceholders.replace(key);
        param = BDDPlaceholders.replace(param);

        JSONObject json = new JSONObject(TestDataCore.getDataStore("requestBody").toString());
        String value = TestDataCore.getDataStore(param).toString();
        json.put(key, value);
        TestDataCore.addToDataStore("requestBody", json);
    }

    /**
     * Saves the JSON Object from a given file to a specified stored data-store.
     *
     * @param file  the file containing the JSON Object.
     * @param param the stored parameter name to save the JSON Object.
     * @since 1.0
     */
    @And("^I store contents of file \"([^\"]*)\" in data-store \"([^\"]*)\"$")
    public void iSaveContentsOfFileInStoredParam(String file, String param) {
        file = BDDPlaceholders.replace(file);
        param = BDDPlaceholders.replace(param);

        JSONObject jsonBody = new JSONObject(TestDataCore.returnStringFromFileOrDataStore(file));
        TestDataCore.addToDataStore(param, jsonBody);
    }

    /**
     * Asserts if the response found within the stored parameter named 'response' contains a specific key.
     *
     * @param key the key to be searched.
     * @since 1.0
     */
    @Then("^response contains key \"([^\"]*)\"$")
    public void responseContainsKey(String key) {
        key = BDDPlaceholders.replace(key);

        JsonObject parsedBody = getResponseBodyAsJson();
        try {
            Assert.assertNotNull(JsonPath.read(parsedBody.toString(), key));
        } catch (PathNotFoundException e) {
            Assert.fail("Key '" + key + "' was not found in response.");
        }
    }

    /**
     * Generates an ObjectID value and store in a stored parameter.
     *
     * @param param the stored parameter to save the value.
     * @since 1.0
     */
    @When("^I generate a new objectID and store it in data-store \"([^\"]*)\"$")
    public void iGenerateANewObjectIDAndSaveItInStoredParam(String param) {
        param = BDDPlaceholders.replace(param);

        TestDataCore.addToDataStore(param, newObjectId());
    }

    /**
     * Asserts if the stored parameter named 'response' has a given key which contains a given value.
     *
     * @param key   the key of the response to be asserted.
     * @param value the value of the key to be asserted.
     * @since 1.0
     */
    @Then("^response contains key \"([^\"]*)\" with value \"(.*)\"$")
    public void responseContainsKeyWithValue(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        assertKeyAndValueInJson(key, value, getResponseBodyAsJson());
    }

    /**
     * Asserts if the stored parameter named 'response' has a given key which contains a given value.
     *
     * @param key   the key of the response to be asserted.
     * @param value the value of the key to be asserted.
     * @since 1.4.0
     */
    @Then("^response contains key \"([^\"]*)\" with anything other than value \"(.*)\"$")
    public void responseContainsKeyWithAnythingButValue(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        assertKeyAndExcludeValueInJson(key, value, getResponseBodyAsJson());
    }

    /**
     * Asserts if the give stored parameter has a specified key which contains a specified value.
     *
     * @param param the stored parameter that contains the key-value pair to be asserted.
     * @param key   the key of the response to be asserted.
     * @param value the value of the key to be asserted.
     * @since 1.0
     */
    @Then("^data-store \"([^\"]*)\" contains key \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void storedParamContainsKeyWithValue(String param, String key, String value) {
        param = BDDPlaceholders.replace(param);
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        JsonObject parsedBody = new JsonParser().parse(TestDataCore.getDataStore(param).toString()).getAsJsonObject();
        assertKeyAndValueInJson(key, value, parsedBody);
    }

    /**
     * Asserts if the give stored parameter has a specified key which contains a specified value.
     *
     * @param param the stored parameter that contains the key-value pair to be asserted.
     * @param key   the key of the response to be asserted.
     * @param value the value of the key to be asserted.
     * @since 1.4.0
     */
    @Then("^data-store \"([^\"]*)\" contains key \"([^\"]*)\" with anything other than value \"([^\"]*)\"$")
    public void storedParamContainsKeyAndExcludeValue(String param, String key, String value) {
        param = BDDPlaceholders.replace(param);
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        JsonObject parsedBody = new JsonParser().parse(TestDataCore.getDataStore(param).toString()).getAsJsonObject();
        assertKeyAndExcludeValueInJson(key, value, parsedBody);
    }


    /**
     * Asserts that the specified key and value in the specifed JSON are found,
     *
     * @param key        The key to be searched within the JSON
     * @param value      The value to be compared with the actual value of the specified key
     * @param jsonObject The JSON object in which to search for the specified key and value
     */
    private void assertKeyAndValueInJson(String key, String value, JsonObject jsonObject) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        String actualValue = extractValueFromJson(key, jsonObject);

        Assert.assertNotNull("Value of searched key was null or not a valid type: " + actualValue, actualValue);
        Assert.assertEquals("Key '" + key + "' and value '" + value + "' was not found.", value, actualValue);

    }

    /**
     * Asserts that the specified key has a different value
     *
     * @param key        The key to be searched within the JSON
     * @param value      The value to be compared with the actual value of the specified key
     * @param jsonObject The JSON object in which to search for the specified key and value
     */
    private void assertKeyAndExcludeValueInJson(String key, String value, JsonObject jsonObject) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        String actualValue = extractValueFromJson(key, jsonObject);

        Assert.assertNotNull("Value of searched key was null or not a valid type: " + actualValue, actualValue);
        Assert.assertNotEquals("Key '" + key + "' and value '" + value + "' was found.", value, actualValue);

    }

    private String extractValueFromJson(String key, JsonObject jsonObject) {
        final int jsonIndentation = 4;
        key = BDDPlaceholders.replace(key);
        String actualValue = null;

        try {
            actualValue = JsonPath.read(jsonObject.toString(), key).toString();
        } catch (PathNotFoundException e) {
            Assert.fail("Key '" + key + "' was not found within " + new JSONObject(jsonObject).toString(jsonIndentation));
        }

        return actualValue;
    }

    /**
     * Handles basic HTTP authentication.
     *
     * @param username username to authenticate.
     * @param password password to authenticate.
     * @since 1.0
     */
    @When("I set basic Authentication for API request with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iSetBasicAuth(String username, String password) {
        username = BDDPlaceholders.replace(username);
        password = BDDPlaceholders.replace(password);

        Base64 b = new Base64();
        String encodedAuth = b.encodeAsString((username + ":" + password).getBytes());

        if (TestDataCore.getDataStore("headers") == null) {
            TestDataCore.addToDataStore("headers", new HashMap<String, Object>());
        }

        ((Map<String, Object>) TestDataCore.getDataStore("headers")).put("Authorization", "Basic " + encodedAuth);

    }

    /**
     * Asserts if a value from a given key in latest HTTP response is greater than a certain value.
     *
     * @param key   the key of the value to be asserted.
     * @param value the value to be asserted.
     * @since 1.0
     */
    @Then("^I assert that value of key \"([^\"]*)\" from the response body is greater than \"([^\"]*)\"$")
    public void iAssertGreaterThan(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        JsonObject jsonObject = getResponseBodyAsJson();

        try {
            int scenarioIntValue = Integer.parseInt(String.valueOf(value));
            int paramIntValue = Integer.parseInt(JsonPath.read(jsonObject.toString(), key).toString());

            if (paramIntValue < scenarioIntValue) {
                Assert.fail("Score is too low: " + paramIntValue);
            } else {
                classLogger.debug("Score is: " + paramIntValue);
            }
        } catch (Exception e) {
            Assert.fail("Key '" + key + "' not found or invalid type.");
        }
    }

    /**
     * Asserts if a value from a given key in latest HTTP response is smaller than a certain value.
     *
     * @param key   the key of the value to be asserted.
     * @param value the value to be asserted.
     */
    @Then("^I assert that value of key \"([^\"]*)\" from the response body is smaller than \"([^\"]*)\"$")
    public void iAssertSmallerThan(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        JsonObject jsonObject = getResponseBodyAsJson();

        try {
            int scenarioIntValue = Integer.parseInt(String.valueOf(value));
            int paramIntValue = Integer.parseInt(JsonPath.read(jsonObject.toString(), key).toString());

            if (paramIntValue > scenarioIntValue) {
                Assert.fail("Score is too high: " + paramIntValue);
            } else {
                classLogger.debug("Score is: " + paramIntValue);
            }
        } catch (Exception e) {
            Assert.fail("Key '" + key + "' not found or invalid type.");
        }

    }

    /**
     * Checks the value of a response header against a given regex pattern.
     *
     * @param header the response header with the value to be asserted.
     * @param regex  the regex pattern.
     * @since 1.0
     */
    @Then("^value of header \"([^\"]*)\" matches the regex pattern \"([^\"]*)\"$")
    public void valueOfHeaderMatchesTheRegexPattern(String header, String regex) {
        header = BDDPlaceholders.replace(header);
        regex = BDDPlaceholders.replace(regex);
        String headerValue = getHeaderValueFromResponse(header);

        Pattern p = Pattern.compile("^(" + regex + ")$");

        Assert.assertTrue("Pattern was not found in query. Value of header was: " + headerValue,
                p.matcher(Objects.requireNonNull(headerValue)).matches());
    }

    /**
     * Asserts that the body of the latest response is equal to a specified value.
     *
     * @param expectedValue the expected value of the response.
     * @since 1.0
     */
    @And("^response body is equal to \"(.*)\"$")
    public void responseBodyIsEqualTo(String expectedValue) {
        expectedValue = BDDPlaceholders.replace(expectedValue);

        HttpResponseWrapper response = (HttpResponseWrapper) TestDataCore.getDataStore("response");
        String responseBody = response.getHttpResponseBody();

        Assert.assertEquals("Response body did not match the expected value.", expectedValue,
                responseBody.replaceAll("^\"|\"$", ""));
    }

    /**
     * Gets the value of a given key from the JSON body of the latest response.
     *
     * @param key   key to get the value from.
     * @param param stored parameter to save the value.
     * @since 1.0
     */
    @And("^I store response body key \"([^\"]*)\" as \"([^\"]*)\"$")
    public void iGetValueOfKeyFromTheResponseBodyAndSaveItInStoredParam(String key, String param) {
        key = BDDPlaceholders.replace(key);
        param = BDDPlaceholders.replace(param);

        try {
            String value = JsonPath.read(getResponseBodyAsJson().toString(), key).toString();
            TestDataCore.addToDataStore(param, value);
        } catch (PathNotFoundException e) {
            Assert.fail("Key not found. Response contained:\n" + getResponseBodyAsJson());
        }
    }

    /**
     * Handles Bearer Token authentication, using the auth_token given in environment.properties.
     * It sets an HTTP Header to
     *
     * @since 1.0
     */
    @And("^I set Bearer Token Authentication Header using token from auth_token environment variable$")
    public void iSetBearerTokenAuthenticationHeaderUsingTokenFromAuthTokenEnvironmentVariable() {
        String token = ParameterMap.getParamAuthToken();

        if (!token.toLowerCase().startsWith("bearer")) {
            token = "Bearer " + token.trim();
        }
        iAddHTTPHeaderValue("Authorization", token);
    }

    /**
     * Asserts that a specific key in the latest response does not contain a specific value.
     *
     * @param key   key to assert its value.
     * @param value value to be asserted.
     * @since 1.0
     */
    @And("^response does not contain key \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void responseDoesNotContainKeyWithValue(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        HttpResponseWrapper response = (HttpResponseWrapper) TestDataCore.getDataStore("response");
        String responseBody = response.getHttpResponseBody();
        String actualValue = null;
        try {
            actualValue = JsonPath.read(responseBody, key).toString();
        } catch (NullPointerException e) {
            actualValue = "null";
        } catch (PathNotFoundException e) {
            Assert.fail("JSON Path " + key + " was not found in response body.");
        }
        Assert.assertNotEquals(value, actualValue, "Key " + key + " contained the value " + actualValue + "\n "
                + "Response was: \n" + responseBody);
    }

    /**
     * Asserts that a the latest response does not contain a specific key in JSON.
     *
     * @param key key to verify that does not exist in response.
     * @since 1.0
     */
    @And("^response does not contain key \"([^\"]*)\"$")
    public void responseDoesNotContainKey(String key) {
        key = BDDPlaceholders.replace(key);

        HttpResponseWrapper response = (HttpResponseWrapper) TestDataCore.getDataStore("response");
        String responseBody = response.getHttpResponseBody();
        try {
            JsonPath.read(responseBody, key);
            Assert.fail("JSON Path '" + key + "' was found in response body.");
        } catch (PathNotFoundException e) {
            classLogger.debug(e);
        }
    }

    @And("^I store value of header \"([^\"]*)\" into the data store \"([^\"]*)\"$")
    public void iStoreValueOfHeaderIntoTheDataStore(String header, String param) {
        header = BDDPlaceholders.replace(header);
        param = BDDPlaceholders.replace(param);

        String headerValue = getHeaderValueFromResponse(header);
        TestDataCore.addToDataStore(param, headerValue);
    }

    @When("I clear all HTTP Headers")
    public void iClearAllHTTPHeaders() {
        TestDataCore.removeFromDataStore("headers");
    }

    /**
     * Asserts if a text exists within the response from an HTTP request
     *
     * @param text value to be check within the response
     */
    @And("response body contains \"([^\"]*)\"")
    public void responseBodyContains(String text) {
        text = BDDPlaceholders.replace(text);

        HttpResponseWrapper response = (HttpResponseWrapper) TestDataCore.getDataStore("response");
        String responseBody;

        responseBody = response.getHttpResponseBody();

        if (!responseBody.contains(text)) {
            Assert.fail(String.format("Expected string [%s] was not found in response [%s]", text, responseBody));
        }
    }
}

