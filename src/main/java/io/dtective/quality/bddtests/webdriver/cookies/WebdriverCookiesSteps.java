package io.dtective.quality.bddtests.webdriver.cookies;

import io.dtective.configuration.ParameterMap;
import io.dtective.test.TestStepsCore;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;

import java.util.Set;

/**
 * Class which contains all the steps related to browser cookies.
 *
 * @since 1.0
 */
public class WebdriverCookiesSteps extends TestStepsCore {

    /**
     * Deletes all cookies in the current browser.
     *
     * @since 1.0
     */
    @Given("^I delete all cookies$")
    public void deleteAllCookies() {
        driver().manage().deleteAllCookies();
    }


    /**
     * Asserts that the requested cookie exists in the current browser session.
     *
     * @param cookieName name of the cookie.
     * @since 1.0
     */

    @Then("^I assert that cookie with name \"([^\"]*)\" exists$")
    public void assertCookieExists(String cookieName) {
        String listOfCookies = (driver().manage().getCookies()).toString();
        Assert.assertTrue("Cookie not found: " + placeholders(cookieName), listOfCookies.contains(placeholders(cookieName)));
    }

    /**
     * Asserts that the requested key exists in the local storage within the current browser session.
     *
     * @param key name of the key.
     * @since 1.0
     */

    @Then("^I assert that web local storage key with name \"([^\"]*)\" exists$")
    public void assertLocalStorageExists(String key) {
        Set listOfCookies = getStorageDriver().getLocalStorage().keySet();
        Assert.assertTrue("Local storage key not found: " + placeholders(key), listOfCookies.contains(placeholders(key)));
    }

    /**
     * Asserts that the requested key exists in the session storage within the current browser session.
     *
     * @param key name of the key.
     * @since 1.0
     */

    @Then("^I assert that web session storage key with name \"([^\"]*)\" exists$")
    public void assertSessionStorageExists(String key) {
        Set listOfCookies = getStorageDriver().getSessionStorage().keySet();
        Assert.assertTrue("Session storage key not found: " + placeholders(key), listOfCookies.contains(placeholders(key)));
    }


    /**
     * Asserts that the cookie exists in the current browser session and
     * compares the value to the expected parameter one.
     *
     * @param cookieName name of the cookie.
     * @param value      expected value for the identified cookie.
     * @since 1.0
     */
    @And("^I assert that cookie with name \"([^\"]*)\" has value \"([^\"]*)\"$")
    public void assertCookieExistsWithValue(String cookieName, String value) {

        cookieName = placeholders(cookieName);
        value = placeholders(value);

        assertCookieExists(cookieName);

        String cookievalue = driver().manage().getCookieNamed(cookieName).getValue();

        Assert.assertEquals(value, cookievalue);
    }

    /**
     * Asserts that the web local storage key exists in the current browser session and
     * compares the value to the expected parameter one.
     *
     * @param key   name of the key.
     * @param value expected value for the identified key.
     * @since 1.0
     */
    @And("^I assert that web local storage key with name \"([^\"]*)\" has value \"([^\"]*)\"$")
    public void assertLocalStorageExistsWithValue(String key, String value) {

        key = placeholders(key);
        value = placeholders(value);

        assertLocalStorageExists(key);

        String actualValue = getStorageDriver().getLocalStorage().getItem(key);

        Assert.assertEquals(value, actualValue);
    }

    /**
     * Asserts that the web session storage key exists in the current browser session and
     * compares the value to the expected parameter one.
     *
     * @param key   name of the key.
     * @param value expected value for the identified key.
     * @since 1.0
     */
    @And("^I assert that web session storage key with name \"([^\"]*)\" has value \"([^\"]*)\"$")
    public void assertSessionStorageExistsWithValue(String key, String value) {

        key = placeholders(key);
        value = placeholders(value);

        assertSessionStorageExists(key);

        String actualValue = getStorageDriver().getSessionStorage().getItem(key);

        Assert.assertEquals(value, actualValue);
    }

    /**
     * Adds the specified cookie key-value to the cookies in the current browser session
     *
     * @param cookiename name of the cookie to be added.
     * @param value      value of the cookie to be added.
     * @since 1.0
     */
    @Given("^I add cookie \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void addCookieWithValue(String cookiename, String value) {
        cookiename = placeholders(cookiename);
        value = placeholders(value);

        driver().manage().addCookie(new Cookie(cookiename, value));
    }

    /**
     * Deletes all local storage in the current browser.
     *
     * @since 1.0
     */
    @Given("I delete all web local storage")
    public void iDeleteAllWebLocalStorage() {
        getStorageDriver().getLocalStorage().clear();
    }

    /**
     * Deletes all session storage in the current browser.
     *
     * @since 1.0
     */
    @Given("I delete all web session storage")
    public void iDeleteAllWebSessionStorage() {
        getStorageDriver().getSessionStorage().clear();
    }

    /**
     * Adds the specified key-value to the local storage in the current browser session
     *
     * @param key   name of the local storage to be added.
     * @param value value of the local storage to be added.
     * @since 1.0
     */
    @When("I add to web local storage \"([^\"]*)\" with value \"([^\"]*)\"")
    public void iAddToWebLocalStorageWithValue(String key, String value) {
        getStorageDriver().getLocalStorage().setItem(placeholders(key), placeholders(value));
    }

    /**
     * Adds the specified key-value to the session storage in the current browser session
     *
     * @param key   name of the session storage to be added.
     * @param value value of the session storage to be added.
     * @since 1.0
     */
    @When("I add to web session storage \"([^\"]*)\" with value \"([^\"]*)\"")
    public void iAddToWebSessionStorageWithValue(String key, String value) {
        getStorageDriver().getSessionStorage().setItem(placeholders(key), placeholders(value));
    }

    /**
     * Helper method due to inconsistency in Selenium local / remote webdriver functionality
     *
     * @return webdriver capable of interacting with local and session storage
     */
    private WebStorage getStorageDriver() {

        WebDriver actualDriver = driver().getDriver();

        if (ParameterMap.getParamIsRemoteInstance().equalsIgnoreCase("true")) {
            return (WebStorage) new Augmenter().augment(actualDriver);
        }

        return (WebStorage) actualDriver;
    }

    /**
     * Removes one specific cookie from the browser session
     *
     * @param key name of the cookie to be removed
     * @since 1.0
     */
    @And("I delete cookie \"([^\"]*)\"")
    public void iDeleteCookie(String key) {

        Cookie cookie = driver().manage().getCookieNamed(placeholders(key));
        if (cookie != null) {
            driver().manage().deleteCookie(cookie);
        }

    }

    /**
     * Removes the specified key from the local storage in the current browser session
     *
     * @param key name of the local storage key to be removed
     * @since 1.0
     */
    @And("I delete web local storage key \"([^\"]*)\"")
    public void iDeleteWebLocalStorageKey(String key) {
        getStorageDriver().getLocalStorage().removeItem(placeholders(key));
    }

    /**
     * Removes the specified key from the session storage in the current browser session
     *
     * @param key name of the session storage key to be removed
     * @since 1.0
     */
    @And("I delete web session storage key \"([^\"]*)\"")
    public void iDeleteWebSessionStorageKey(String key) {
        getStorageDriver().getSessionStorage().removeItem(placeholders(key));
    }
}
