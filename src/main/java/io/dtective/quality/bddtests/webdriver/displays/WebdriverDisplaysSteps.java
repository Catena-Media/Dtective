package io.dtective.quality.bddtests.webdriver.displays;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.dtective.test.TestDataCore;
import io.dtective.test.TestStepsCore;
import io.dtective.xpath.XpathHelper;
import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * Contains all the steps to assert that a given element is displayed on the current
 * webpage and that a field displays a given value.
 *
 * @since 1.0
 */
public class WebdriverDisplaysSteps extends TestStepsCore {

    /**
     * Asserts if the text is displayed in the current page.
     *
     * @param text the text value to be asserted.
     * @since 1.0
     */
    @Then("^text displays \"([^\"]*)\"$")
    public void textDisplays(String text) {
        text = placeholders(text);
        getProfile().textDisplays(text);
    }

    /**
     * Asserts if the given text is being displayed in the given XPATH.
     *
     * @param text  the text to verify if it is displayed.
     * @param xpath the XPATH of the element where the text should be displayed on.
     * @since 1.0
     */
    @Then("^text \"([^\"]*)\" is displayed in XPATH \"([^\"]*)\"$")
    public void textIsDisplayedInXPATH(String text, String xpath) {
        getProfile().isDisplayed(XpathHelper.findByText(placeholders(text), placeholders(xpath)));
    }

    /**
     * Asserts if the element is displayed given an HTML property and value.
     *
     * @param property the property name of the HTML element to assert on.
     * @param value    the value name of the HTML element to assert on.
     * @since 1.0
     */
    @Then("^text displays by Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void textDisplays(String property, String value) {
        getProfile().isDisplayed(XpathHelper.findByPropAndValue(placeholders(property), placeholders(value)));
    }

    /**
     * Asserts if the text is not displayed in the current page.
     *
     * @param text the text value to be asserted.
     * @since 1.0
     */
    @Then("^text does not display \"([^\"]*)\"$")
    public void textDoesNotDisplay(String text) {
        text = placeholders(text);

        getProfile().appendScreenshotToScenario(new Object() {
        }.getClass().getEnclosingMethod().getName());
        Assert.assertNull("Error, the text displays on the site - "
                + text, driver().findElement(XpathHelper.findByText(text)));
    }

    /**
     * Asserts if the element, located by XPATH, is displayed.
     *
     * @param xpath the XPATH of the element to verify it is displayed.
     * @since 1.0
     */
    @Then("^element is displayed by xpath \"([^\"]*)\"$")
    public void elementDisplayedByXpath(String xpath) {
        getProfile().isDisplayed(By.xpath(placeholders(xpath)));
    }

    /**
     * Asserts if the previously saved by reference element is displayed.
     *
     * @param ref the reference name previously saved.
     * @since 1.0
     */
    @Then("^element displays by Reference \"([^\"]*)\"$")
    public void elementDisplays(String ref) {
        getProfile().isDisplayed(XpathHelper.findByPropAndValue(placeholders(ref)));
    }

    /**
     * Asserts if the element, located by id, has the given text value displayed.
     *
     * @param id   the id of the element which contains the text value to be asserted.
     * @param text the text value to be asserted.
     * @since 1.0
     */
    @Then("^element with id \"([^\"]*)\" contains value of \"([^\"]*)\"$")
    public void elementWithIdContainsValueOf(String id, String text) {
        getProfile().assertAttributeValueContains(By.id(placeholders(id)), "value", placeholders(text));
    }

    /**
     * Asserts if the element, which has a given property and value, is displayed.
     *
     * @param property the property of the element to be asserted.
     * @param value    the value of the element to be asserted.
     * @since 1.0
     */
    @And("^element displays \"([^\"]*)\" that contains \"([^\"]*)\"$")
    public void elementDisplaysThatContains(String property, String value) {
        getProfile().isDisplayed(XpathHelper.findByPropertyValueContains(placeholders(property), placeholders(value)));
    }

    /**
     * Asserts if the element is displayed in a given XPATH, which has a given property and value.
     *
     * @param xpath    the XPATH of the element which contains the given property and value.
     * @param property the property of the element to be asserted.
     * @param value    the value of the element to be asserted.
     * @since 1.0
     */
    @Then("^element displays inside Xpath \"([^\"]*)\" property \"([^\"]*)\" that contains \"([^\"]*)\"$")
    public void elementDisplaysInsideXpathPropertyThatContains(String xpath, String property, String value) {
        getProfile().isDisplayed(XpathHelper.findByPropertyValueContainsWithinXpath(placeholders(xpath),
                placeholders(property), placeholders(value)));
    }

    /**
     * Asserts if the element located by attribute and value is displayed.
     *
     * @param attr  attribute of the element to be located.
     * @param value value of the element to be located.
     * @since 1.0
     */
    @Then("^element is displayed by Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void elementIsDisplayedByAttributeValue(String attr, String value) {
        getProfile().isDisplayed(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)));
    }

    /**
     * Asserts if the element is not displayed, given an attribute and value.
     *
     * @param attr  attribute of the element to be located.
     * @param value value of the element to be located.
     * @since 1.0
     */
    @Then("^element is not displayed by Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void elementIsNotDisplayedByAttributeValue(String attr, String value) {
        getProfile().elementDoesNotDisplay(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)));
    }

    /**
     * Asserts if the element, located by XPATH, is not displayed.
     *
     * @param xpath the XPATH of the element to verify it is not displayed.
     * @since 1.0
     */
    @Then("^element is not displayed by xpath \"([^\"]*)\"$")
    public void elementIsNotDisplayedByXpath(String xpath) {
        getProfile().elementDoesNotDisplay(By.xpath(placeholders(xpath)));
    }

    /**
     * Asserts if an element located by a previously stored parameter is displayed.
     *
     * @param param name of the parameter to verify it is displayed.
     * @since 1.0
     */
    @Then("^element is displayed by Attribute and Value using parameter \"([^\"]*)\"$")
    public void elementIsDisplayedByAttributeAndValueUsingParameter(String param) {
        getProfile().isDisplayed(XpathHelper.findByPropAndValue(placeholders(param)));
    }

    /**
     * Asserts if an element located by a previously stored attribute and value parameter is not displayed.
     *
     * @param param name of the attribute and value parameter to verify it is not displayed.
     * @since 1.0
     */
    @Then("^element is not displayed by Attribute and Value using parameter \"([^\"]*)\"$")
    public void elementIsNotDisplayedByAttributeAndValueUsingParameter(String param) {
        getProfile().elementDoesNotDisplay(XpathHelper.findByPropAndValue(placeholders(param)));
    }

    /**
     * Asserts if an element located by a previously stored XPATH parameter is displayed.
     *
     * @param param name of the XPATH parameter to verify it is displayed.
     * @since 1.0
     */
    @Then("^element is displayed by xpath using parameter \"([^\"]*)\"$")
    public void elementIsDisplayedByXpathUsingParameter(String param) {
        getProfile().isDisplayed(XpathHelper.findByXpathValue(placeholders(param)));
    }

    /**
     * Asserts if an element located by a previously stored XPATH parameter is not displayed.
     *
     * @param param name of the XPATH parameter to verify it is not displayed.
     * @since 1.0
     */
    @Then("^element is not displayed by xpath using parameter \"([^\"]*)\"$")
    public void elementIsNotDisplayedByXpathUsingParameter(String param) {
        getProfile().elementDoesNotDisplay(XpathHelper.findByXpathValue(placeholders(param)));
    }

    /**
     * Asserts if an element located by a previously stored text parameter is displayed.
     *
     * @param param name of the text parameter to verify it is displayed.
     * @since 1.0
     */
    @Then("^element is displayed by text using parameter \"([^\"]*)\"$")
    public void elementIsDisplayedByTextUsingParameter(String param) {
        getProfile().isDisplayed(XpathHelper.findByText((String) TestDataCore.getDataStore(placeholders(param))));
    }

    /**
     * Asserts if an element located by a previously stored text parameter is not displayed.
     *
     * @param param name of the text parameter to verify it is not displayed.
     * @since 1.0
     */
    @Then("^element is not displayed by text using parameter \"([^\"]*)\"$")
    public void elementIsNotDisplayedByTextUsingParameter(String param) {
        getProfile().elementDoesNotDisplay(XpathHelper.findByText((String) TestDataCore.getDataStore(placeholders(param))));
    }

    /**
     * Asserts if the text value is the equal to the value displayed in a given field, located by attribute
     * and value in the HTML.
     *
     * @param text  text value to be asserted.
     * @param attr  html attribute of the field element.
     * @param value html value of the field element.
     * @since 1.0
     */
    @Then("^field value equals \"([^\"]*)\" by Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void fieldValueEqualsByAttributeValue(String text, String attr, String value) {
        getProfile().compareFieldValue(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)), placeholders(text));
    }

    /**
     * Asserts if the text value is the equal to the value displayed in a
     * given field, located by xpath.
     *
     * @param text  text value to be asserted.
     * @param xpath XPATH of the field element.
     * @since 1.0
     */
    @Then("^field value equals \"([^\"]*)\" by XPATH \"([^\"]*)\"$")
    public void fieldValueEqualsByXPATH(String text, String xpath) {
        getProfile().compareFieldValue(By.xpath(placeholders(xpath)), placeholders(text));
    }

}
