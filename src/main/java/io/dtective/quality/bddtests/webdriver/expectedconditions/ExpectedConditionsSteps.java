package io.dtective.quality.bddtests.webdriver.expectedconditions;

import io.dtective.test.TestStepsCore;
import io.dtective.xpath.XpathHelper;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

import java.util.regex.Pattern;

/**
 * Class which contains all the steps related to expected conditions
 *
 * @since 1.0
 */
public class ExpectedConditionsSteps extends TestStepsCore {

    /**
     * Confirms that the target element is clickable within the given timeframe.
     *
     * @param attribute HTML Attribute
     * @param value     Value of the HTML Attribute
     * @param seconds   Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the element is clickable by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    public void elementClickable(String attribute, String value, int seconds) {
        getProfile().isClickable(XpathHelper.findByPropAndValue(placeholders(attribute), placeholders(value)), seconds);
    }

    /**
     * Confirms that the target element is clickable within the given timeframe.
     *
     * @param xpath   Xpath expression to locate element
     * @param seconds Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the element is clickable by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    public void elementClickable(String xpath, int seconds) {
        getProfile().isClickable(By.xpath(placeholders(xpath)), seconds);
    }

    /**
     * Confirms that the target element is visible within the given timeframe.
     *
     * @param attribute HTML Attribute
     * @param value     Value of the HTML Attribute
     * @param seconds   Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the element is visible by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    public void elementVisible(String attribute, String value, int seconds) {
        getProfile().isVisible(XpathHelper.findByPropAndValue(placeholders(attribute), placeholders(value)), seconds);
    }

    /**
     * Confirms that the target element is visible within the given timeframe.
     *
     * @param xpath   Xpath expression to locate element
     * @param seconds Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the element is visible by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    public void elementVisible(String xpath, int seconds) {
        getProfile().isVisible(By.xpath(placeholders(xpath)), seconds);
    }

    /**
     * Confirms that the target element is selected (checkbox checked) within the given timeframe.
     *
     * @param attribute HTML Attribute
     * @param value     Value of the HTML Attribute
     * @param seconds   Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the element is selected by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    public void elementSelectable(String attribute, String value, int seconds) {
        getProfile().isSelected(XpathHelper.findByPropAndValue(placeholders(attribute), placeholders(value)), seconds);
    }

    /**
     * Confirms that the target element is selected (checkbox checked) within the given timeframe.
     *
     * @param xpath   Xpath expression to locate element
     * @param seconds Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the element is selected by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    public void elementSelectable(String xpath, int seconds) {
        getProfile().isSelected(By.xpath(placeholders(xpath)), seconds);
    }

    /**
     * Confirms that the target a browser alert is present within the given timeframe
     *
     * @param seconds Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the browser alert is displays within (\\d+) seconds$")
    public void browserAlertPresent(int seconds) {
        getProfile().alertPresent(seconds);
    }

    /**
     * Confirms that using the selector, the number of displayed elements are equal to the parameter within the given timeframe
     *
     * @param numOf     Number of expected elements
     * @param attribute HTML Attribute
     * @param value     Value of the HTML Attribute
     * @param seconds   Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the number of elements is (\\d+) by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    public void numberOfElementsEqual(int numOf, String attribute, String value, int seconds) {
        getProfile().numberOfElements(XpathHelper.findByPropAndValue(placeholders(attribute), placeholders(value)), numOf, seconds);
    }

    /**
     * Confirms that using the selector, the number of displayed elements are equal to the parameter within the given timeframe
     *
     * @param numOf   Number of expected elements
     * @param xpath   Xpath expression to locate element
     * @param seconds Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the number of elements is (\\d+) by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    public void numberOfElementsEqual(int numOf, String xpath, int seconds) {
        getProfile().numberOfElements(By.xpath(placeholders(xpath)), numOf, seconds);
    }

    /**
     * Confirms that the target element's text matches the regex pattern within the given timeframe.
     *
     * @param text      Regex pattern
     * @param attribute HTML Attribute
     * @param value     Value of the HTML Attribute
     * @param seconds   Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the text \"([^\"]*)\" displays by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    public void textMatches(String text, String attribute, String value, int seconds) {
        getProfile().textMatches(XpathHelper.findByPropAndValue(placeholders(attribute),
                placeholders(value)), Pattern.compile(placeholders(text)), seconds);
    }

    /**
     * Confirms that the target element's text matches the regex pattern within the given timeframe.
     *
     * @param text    Regex pattern
     * @param xpath   Xpath expression to locate element
     * @param seconds Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the text \"([^\"]*)\" displays by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    public void textMatches(String text, String xpath, int seconds) {
        getProfile().textMatches(By.xpath(placeholders(xpath)), Pattern.compile(placeholders(text)), seconds);
    }

    /**
     * Confirms that the target element exists within the given timeframe.
     *
     * @param attribute HTML Attribute
     * @param value     Value of the HTML Attribute
     * @param seconds   Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the element exists by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within (\\d+) seconds$")
    public void elementExists(String attribute, String value, int seconds) {
        getProfile().isExisting(XpathHelper.findByPropAndValue(placeholders(attribute), placeholders(value)), seconds);
    }

    /**
     * Confirms that the target element exists within the given timeframe.
     *
     * @param xpath   Xpath expression to locate element
     * @param seconds Number of seconds to wait
     * @since 1.0
     */
    @Then("^I assert that the element exists by XPATH \"([^\"]*)\" within (\\d+) seconds$")
    public void elementExists(String xpath, int seconds) {
        getProfile().isExisting(By.xpath(placeholders(xpath)), seconds);
    }

}
