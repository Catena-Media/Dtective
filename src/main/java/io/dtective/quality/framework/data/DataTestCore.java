package io.dtective.quality.framework.data;

import io.dtective.data.DataProvider;
import io.dtective.placeholders.BDDPlaceholders;
import com.google.gson.*;
import com.jayway.jsonpath.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Class which contains the steps to store and manage stored data for tests.
 *
 * @since 1.0
 */
public class DataTestCore {

    @Autowired
    private DataProvider dataProvider;

    public DataTestCore() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("./Beans.xml");
        dataProvider = applicationContext.getBean("DataProvider", DataProvider.class);
    }

    //CREATE OPERATION
    public static String addKeyValueToJSON(String json, String jsonPathKey, Object value) {
        String jsonPath;

        JSONObject jsonObject = new JSONObject(json);

        if (jsonPathKey.contains("$.")) {
            jsonPath = jsonPathKey.substring(0, jsonPathKey.lastIndexOf("."));
            jsonPathKey = jsonPathKey.substring(jsonPathKey.lastIndexOf(".") + 1);
        } else {
            jsonPath = "$";
        }

        DocumentContext doc;
        Configuration conf =
                Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL).addOptions(Option.SUPPRESS_EXCEPTIONS);

        if (jsonPath.equals("$")) {

            doc = JsonPath.using(conf).parse(jsonObject.toString())
                    .set(JsonPath.compile(jsonPathKey), value);

        } else
            doc = JsonPath.using(conf).parse(jsonObject.toString())
                    .put(jsonPath, jsonPathKey, value);

        return new GsonBuilder().create().toJsonTree(doc.json()).getAsJsonObject().toString();

    }

    //READ OPERATION
    public static String returnValueFromKeyInJSON(String json, String key) {
        return String.valueOf((Object) JsonPath.read(json, key));
    }

    //CHANGE OPERATION
    public static String changeValueofKeyInJSON(String json, String key, String newValue) {
        DocumentContext doc = JsonPath.parse(json).
                set(key, newValue);
        return String.valueOf(new GsonBuilder().create().toJsonTree(doc.json()).getAsJsonObject().toString());
    }

    //DELETE OPERATION
    public static String deleteKeyValueFromJSON(String json, String key) {
        DocumentContext doc = JsonPath.parse(json).delete(key);
        return new GsonBuilder().create().toJsonTree(doc.json()).getAsJsonObject().toString();
    }

    /**
     * Adds the given key-value pair to the data store.
     *
     * @param key   the key to be stored.
     * @param value the value to be stored with the given key.
     * @since 1.0
     */
    @Given("^I add the following data-store \"([^\"]*)\" \"(.*)\"$")
    public void iAddTheFollowingLocalParameters(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);
        dataProvider.getLocalDataService().put(key, value);
    }

    /**
     * Adds the key-value pair under the same given name reference to the data store.
     *
     * @param name  the name which key-value pair are stored with.
     * @param key   the key to be stored.
     * @param value the value to be stored with the given key.
     * @since 1.0
     */
    @Given("^I add to data-store \"([^\"]*)\" containing key \"([^\"]*)\" and value \""
            + "([^\"]*)\"$")
    public void iAddTheFollowingLocalParameters(String name, String key, String value) {
        name = BDDPlaceholders.replace(name);
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        Map<Object, Object> result = new HashMap<>();
        result.put(key, value);

        dataProvider.getLocalDataService().put(name, result);
    }

    /**
     * Asserts if the key exists in the data store.
     *
     * @param key the key to be asserted.
     * @since 1.0
     */
    @Then("^I can use the locally set data \"([^\"]*)\"$")
    public void iCanUseDataSetInDataStore(String key) {
        Assert.assertNotNull(dataProvider.getLocalDataService().get(BDDPlaceholders.replace(key)));
    }

    /**
     * Asserts the value of stored parameter against a given regex pattern.
     *
     * @param param the parameter to be asserted.
     * @param regex the regex pattern.
     * @since 1.0
     */
    @Then("^value of data-store \"([^\"]*)\" matches the regex pattern \"(.*)\"$")
    public void valueOfStoredParamMatchesTheRegexPattern(String param, String regex) {
        param = BDDPlaceholders.replace(param);
        regex = BDDPlaceholders.replace(regex);

        String query = dataProvider.getLocalDataService().get(param).toString();

        Pattern p = Pattern.compile("^(" + regex + ")$");

        Assert.assertTrue("Pattern was not found in query. Value of param was: " + query, p.matcher(query).matches());
    }

    /**
     * Asserts if the stored parameter is null.
     *
     * @param param the stored parameter to be asserted.
     * @since 1.0
     */
    @Then("^value of data-store \"([^\"]*)\" is null$")
    public void valueOfStoredParamIsNull(String param) {
        param = BDDPlaceholders.replace(param);

        Assert.assertNull("data-store " + param + " was not null", dataProvider.getLocalDataService().get(param));
    }

    /**
     * Verifies that a given stored parameter is not empty.
     *
     * @param param the stored parameter to be asserted.
     * @since 1.0
     */
    @Then("^data-store \"([^\"]*)\" is not empty$")
    public void storedParamIsNotEmpty(String param) {
        param = BDDPlaceholders.replace(param);

        Assert.assertNotNull("Param: " + param + " found empty", dataProvider.getLocalDataService().get(param));
    }

    /**
     * Clears a given stored parameter.
     *
     * @param param the stored parameter to be set as null.
     * @since 1.0
     */
    @And("^I clear the data-store \"([^\"]*)\"$")
    public void iClearTheStoredParam(String param) {
        dataProvider.getLocalDataService().replace(BDDPlaceholders.replace(param), null);
    }

    /**
     * Asserts that the value from a specified paramater is equal to a specified value.
     *
     * @param dataStore The parameter name containing the value to be asserted.
     * @param expectedValue The expected value.
     * @since 1.0
     */
    @Then("^value of data-store \"(.*)\" is equal to \"(.*)\"$")
    public void valueOfLocalParamIsEqualTo(String param, Object value) {
        Assert.assertEquals("data-store " + param
                        + " was not equal to " + value
                        + ". Actual value was " + dataProvider.getLocalDataService().get(param),
                value, dataProvider.getLocalDataService().get(param));
    }

    /**
     * Creates a stored parameter named 'requestBody' containing a new JSONObject.
     *
     * @since 1.0
     */
    @And("^I start to build a JSON request body$")
    public void iStartToBuildAJSONRequestBody() {

        dataProvider.getLocalDataService().put("requestBody", new JSONObject());
    }

    /**
     * Adds a key-value pair to a previously created stored param named 'requestBody'.
     *
     * @param jsonPathKey the key to be added, specified in JSON Path notation
     * @param value       the value to be added.
     * @since 1.0
     */
    @And("^I add new JSON request body key \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void iAddToTheJSONRequestBodyAKeyWithValue(String jsonPathKey, String value) {
        jsonPathKey = BDDPlaceholders.replace(jsonPathKey);
        value = BDDPlaceholders.replace(value);

        String json = addKeyValueToJSON(dataProvider.getLocalDataService().get("requestBody").toString(), jsonPathKey, value);
        dataProvider.getLocalDataService().put("requestBody", json);
    }

    //ToDo A unit test is needed for the step however we first need an endpoint in our UnitTestAPI which accepts it as
    // part of the payload

    /**
     * Adds a key-value pair of type JSON Object to the already created stored parameter named 'requestBody'.
     *
     * @param key   the key to add.
     * @param value the JSON Object to store.
     * @since 1.0
     */
    @And("^I add new JSON request body key \"([^\"]*)\" with Dict value \"(.*)\"$")
    public void iAddToTheJSONRequestBodyAKeyWithDictValue(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        JsonObject jsonWithDict = new Gson().fromJson(value, JsonObject.class);
        String json = addKeyValueToJSON(dataProvider.getLocalDataService().get("requestBody").toString(), key, jsonWithDict);
        dataProvider.getLocalDataService().put("requestBody", json);
    }

    //ToDo A unit test is needed for the step however we first need an endpoint in our UnitTestAPI which accepts it as
    // part of the payload

    /**
     * Adds a key-value pair of type JSON Object to the already created stored parameter named 'requestBody'.
     *
     * @param key   the key to add.
     * @param value the JSON Array to store.
     * @since 1.0.0
     */
    @And("^I add new JSON request body key \"([^\"]*)\" with Array value \"(.*)\"$")
    public void iAddToTheJSONRequestBodyAKeyWithArrayValue(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        JsonParser parser = new JsonParser();
        JsonElement arrayElement = parser.parse(value);
        JsonArray arrayValue = arrayElement.getAsJsonArray();
        String json = addKeyValueToJSON(dataProvider.getLocalDataService().get("requestBody").toString(), key, arrayValue);
        dataProvider.getLocalDataService().put("requestBody", json);
    }

    /**
     * Adds a key-value pair of type JSON Object to the already created stored parameter named 'requestBody'
     *
     * @param key   the key to add.
     * @param value the boolean value to store.
     */
    @And("^I add a new JSON request body a key \"([^\"]*)\" with boolean value \"(.*)\"$")
    public void iAddToTheJSONRequestBodyAKeyWithBooleanValue(String key, String value) {
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        boolean booleanValue = Boolean.parseBoolean(value);
        JSONObject json = new JSONObject(dataProvider.getLocalDataService().get("requestBody").toString());
        json.put(key, booleanValue);
        dataProvider.getLocalDataService().put("requestBody", json);
    }

    /**
     * Add a JSON key and value pair in a location specified in JSON Path notation,  within a JSON from a data store.
     * The resulting JSON is stored in a specified data store.
     *
     * @param key      The key name to add.
     * @param value    The value to add.
     * @param path     The JSON path to use to add the key value pair.
     * @param param    The data store containing the JSON object.
     * @param newParam The new data store to store the modified JSON object.
     * @since 1.0
     */
    @When("^I add key \"([^\"]*)\" and value \"([^\"]*)\" in JSON path \"([^\"]*)\" for JSON from data store \""
            + "([^\"]*)\" and store "
            + "it in data store \"([^\"]*)\"$")
    public void iAddKeyAndValueInJSONPathForJSONFromLocalParamAndStoreItInLocalParam(String key, String value,
                                                                                     String path, String param,
                                                                                     String newParam) {
        String json = dataProvider.getLocalDataService().get(param).toString();
        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);
        path = BDDPlaceholders.replace(path);
        newParam = BDDPlaceholders.replace(newParam);

        DocumentContext doc = JsonPath.parse(json)
                .put(path, key, value);
        String newJson = new GsonBuilder().create().toJsonTree(doc.json()).getAsJsonObject().toString();
        dataProvider.getLocalDataService().put(newParam, newJson);
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
    @And("^I change value of key \"([^\"]*)\" to \"([^\"]*)\" in JSON from data-store \"([^\"]*)\" and store it as \"([^\"]*)\"$")
    public void iChangeValueOfKeyInJSONFromStoredParamWithValueAndSaveItInStoredParam(String key, String value, String bodyParam,
                                                                                      String newParam) {

        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);
        bodyParam = BDDPlaceholders.replace(bodyParam);
        newParam = BDDPlaceholders.replace(newParam);

        String json = dataProvider.getLocalDataService().get(bodyParam).toString();
        String newJson = changeValueofKeyInJSON(json, key, value);
        dataProvider.getLocalDataService().put(newParam, newJson);
    }

    @And("^I change value of key \"([^\"]*)\" to \"([^\"]*)\" in JSON request body$")
    public void iChangeValueOfKeyToInJSONRequestBody(String key, String value) {

        key = BDDPlaceholders.replace(key);
        value = BDDPlaceholders.replace(value);

        String json = dataProvider.getLocalDataService().get("requestBody").toString();
        String newJson = changeValueofKeyInJSON(json, key, value);
        dataProvider.getLocalDataService().put("requestBody", newJson);
    }

    /**
     * Asserts if the JSON Object found within a given stored parameter contains a specific key.
     *
     * @param param stored parameter containing the JSON Object.
     * @param key   the key to search for.
     * @since 1.0
     */
    @Then("^JSON within data-store \"([^\"]*)\" contains key \"([^\"]*)\"$")
    public void jsonWithinStoredParamContainsKey(String param, String key) {

        param = BDDPlaceholders.replace(param);
        key = BDDPlaceholders.replace(key);

        JsonParser jsonParser = new JsonParser();
        JsonObject parsedBody = jsonParser.parse(dataProvider.getLocalDataService().get(param).toString()).getAsJsonObject();

        try {
            returnValueFromKeyInJSON(dataProvider.getLocalDataService().get(param).toString(), key);
        } catch (PathNotFoundException e) {
            Assert.fail("Key '" + key + "' was not found within:\n" + parsedBody);
        }

    }

    /**
     * Asserts that the JSON stored in a specified param does not contain a specified key.
     *
     * @param param The param containing the JSON.
     * @param key   The key to be used for the assertion
     * @since 1.0
     */
    @Then("^JSON within data-store \"([^\"]*)\" does not contain key \"([^\"]*)\"$")
    public void jsonWithinLocalParamDoesNotContainKey(String param, String key) {

        param = BDDPlaceholders.replace(param);
        key = BDDPlaceholders.replace(key);

        try {
            returnValueFromKeyInJSON(dataProvider.getLocalDataService().get(param).toString(), key);
            Assert.fail("Key '" + key + "' was found in data-store.");
        } catch (PathNotFoundException e) {
            //No implementation needed in catch block sine assertion is done in try block.
        }
    }

    /**
     * Removes a given key-value pair from a JSON Object. The modified JSON Object is stored within the same stored
     * parameter.
     *
     * @param key   the key of the key value-pair to be removed.
     * @param param the stored parameter containing the JSON Object to be modified.
     * @since 1.0
     */
    @And("^I remove JSON key value pair with key \"([^\"]*)\" from JSON in data-store \"([^\"]*)\"$")
    public void iRemoveJSONKeyValuePairWithKeyFromJSON(String key, String param) {
        key = BDDPlaceholders.replace(key);
        param = BDDPlaceholders.replace(param);

        String newJson = deleteKeyValueFromJSON(
                dataProvider.getLocalDataService().get(param).toString(), key);
        dataProvider.getLocalDataService().put(param, newJson);
    }

    /**
     * Gets the value of a key found using Json Path and store it in a data-store
     *
     * @param key      the key to search for the value
     * @param param    the param containing the Json to search
     * @param newParam the new param to store the value
     * @since 1.0
     */
    @And("^I get value of key \"([^\"]*)\" from JSON in data-store \"([^\"]*)\" and store it in data-store \""
            + "([^\"]*)\"$")
    public void iGetValueOfKeyFromJSONInLocalParamAndStoreItInLocalParam(String key, String param, String newParam) {
        key = BDDPlaceholders.replace(key);
        param = BDDPlaceholders.replace(param);
        newParam = BDDPlaceholders.replace(newParam);

        dataProvider.getLocalDataService().put(
                newParam, returnValueFromKeyInJSON(
                        dataProvider.getLocalDataService().get(param).toString(), key));
    }
}
