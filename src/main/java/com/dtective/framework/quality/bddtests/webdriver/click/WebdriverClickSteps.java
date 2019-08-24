package com.dtective.framework.quality.bddtests.webdriver.click;

import com.dtective.framework.test.TestDataCore;
import com.dtective.framework.test.TestStepsCore;
import com.dtective.framework.xpath.XpathHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Class which provides steps related to the mouse click interactions with elements.
 *
 * @since 1.0.4
 */
public class WebdriverClickSteps extends TestStepsCore {

    /**
     * Allows the passing of a string to find an element by text and clicking on it.
     *
     * @param text The text to be used for locating the element.
     * @since 1.0.4
     */
    @When("^I click on text \"([^\"]*)\"$")
    public void iClickOnText(String text) {
        text = placeholders(text);
        getProfile().click(XpathHelper.findByText(text));
    }

    /**
     * Allows the passing of an HTML property and value to find an element and double click on it.
     *
     * @param property The HTML property to be used to locate the element.
     * @param value    The value of the HTML property to locate the element.
     * @since 1.0.4
     */
    @When("^I double click element with Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void iDoubleClick(String property, String value) {
        property = placeholders(property);
        value = placeholders(value);
        getProfile().doubleClick(XpathHelper.findByPropAndValue(property, value));
    }

    /**
     * Allows the passing of a data-store name containing the XPATH string to define the
     * element to double click.
     *
     * @param xpath The data-store containing the XPATH string to locate the element.
     * @since 1.0.4
     */
    @When("^I double click by XPATH \"([^\"]*)\"$")
    public void iDoubleClickByXPATH(String xpath) {
        xpath = placeholders(xpath);
        getProfile().doubleClick(By.xpath(xpath));
    }

    /**
     * Allows the passing of an HTML attribute and value to define an element and perform a context click.
     *
     * @param property The HTML property to be used to locate the element.
     * @param value    The value of the HTML property to locate the element.
     * @since 1.0.4
     */
    @When("^I context click element with Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void iContextClick(String property, String value) {
        property = placeholders(property);
        value = placeholders(value);
        getProfile().contextClick(XpathHelper.findByPropAndValue(property, value));
    }

    /**
     * Allows the passing of a data-store name to define an element and perform a context click.
     *
     * @param name The name of the data-store containing the HTML property and value to locate an element.
     * @since 1.0.4
     */
    @When("^I context click \"([^\"]*)\"$")
    public void iContextClick(String name) {
        name = placeholders(name);
        getProfile().contextClick(XpathHelper.findByPropAndValue(name));
    }

    /**
     * Allows the passing of a string containing the XPATH to locate the element to perform a
     * context click.
     *
     * @param xpath The XPATH string to locate the element.
     * @since 1.0.4
     */
    @When("^I context click by XPATH \"([^\"]*)\"$")
    public void iContextClickByXPATH(String xpath) {
        xpath = placeholders(xpath);
        getProfile().contextClick(By.xpath(xpath));
    }

    /**
     * Allows the passing of a data-store name which contains the HTML property and value
     * to locate an element and perform a standard left click.
     *
     * @param localParamName The localParam containing the XPATH string to locate the element.
     * @since 1.0.4
     */
    @And("^I click element with XPATH using data-store name \"([^\"]*)\"$")
    public void iClickLocalParamByXPATH(String localParamName) {
        localParamName = placeholders(localParamName);
        getProfile().click(XpathHelper.findByXpathValueFromDatastore(localParamName));
    }

    /**
     * Allows the passing of a string containing the XPATH to locate the element to perform a click.
     *
     * @param xpath The XPATH string to locate the element.
     * @since 1.0.4
     */
    @And("^I click by XPATH \"([^\"]*)\"$")
    public void iClickByXPATH(String xpath) {
        xpath = placeholders(xpath);
        getProfile().click(By.xpath(xpath));
    }

    /**
     * Allows the passing of an HTML property and two values to find an element with a partial
     * match on both values and click on it.
     *
     * @param property The HTML property to be used to locate the element.
     * @param value    The first value of the HTML property to locate the element.
     * @param value2   The second value of the HTML property to locate the element.
     * @since 1.0.4
     */
    @When("^I click element with Attribute \"([^\"]*)\" and Value that contains \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iClickThatContainsAnd(String property, String value, String value2) {
        property = placeholders(property);
        value = placeholders(value);
        value2 = placeholders(value2);
        getProfile().click(XpathHelper.findByPropertyValueContains(property, value, value2));
    }

    /**
     * Performs a click on the first element located of type submit.
     *
     * @since 1.0.4
     */
    @And("^I click submit$")
    public void iClickSubmit() {
        getProfile().click(XpathHelper.anySubmit());
    }

    /**
     * Allows the passing of a data-store name containing the XPATH
     * string to define the element to click.
     *
     * @param name The name of the data-store containing the HTML property and value to locate an element.
     * @since 1.0.4
     */
    @When("^I click element with Attribute and Value using data-store name \"([^\"]*)\"$")
    public void iClickWithAttributeAndValueUsingLocalParam(String name) {
        name = placeholders(name);
        if (TestDataCore.existsInDataStore(name)) {
            getProfile().click(XpathHelper.findByPropAndValue(name));
        } else {
            throw new Error("Button Definition not found - " + name);
        }
    }

    /**
     * Allows the passing of a string used to locate an element with the HTML text attribute and
     * double click on it.
     *
     * @param text The text to be used for locating the element.
     * @since 1.0.4
     */
    @When("^I double click on text \"([^\"]*)\"$")
    public void iDoubleClickOnText(String text) {
        text = placeholders(text);
        getProfile().doubleClick(XpathHelper.findByText(text));
    }

    /**
     * Allows the passing of a string used to locate an element with the HTML text attribute and
     * context click on it.
     *
     * @param text The text to be used for locating the element.
     * @since 1.0.4
     */
    @When("^I context click on text \"([^\"]*)\"$")
    public void iContextClickOnText(String text) {
        text = placeholders(text);
        getProfile().contextClick(XpathHelper.findByText(text));
    }

    /**
     * Allows the passing of a data-store name
     * containing the HTML property and value to locate an element and perform a double click.
     *
     * @param name The name of the data-store containing the HTML property and value to locate an element.
     * @since 1.0.4
     */
    @When("^I double click element with Attribute and Value using data-store name \"([^\"]*)\"$")
    public void iDoubleClickElementWithAttributeAndValueUsingLocalParam(String name) {
        name = placeholders(name);
        if (TestDataCore.existsInDataStore(name)) {
            getProfile().doubleClick(XpathHelper.findByPropAndValue(name));
        } else {
            throw new Error("Button Definition not found - " + name);
        }
    }

    /**
     * Allows the passing of a data-store name containing an XPATH
     * string to define the element to perform a double click.
     *
     * @param name The name of the data-store containing the XPATH.
     * @since 1.0.4
     */
    @When("^I double click element with xpath using data-store name \"([^\"]*)\"$")
    public void iDoubleClickElementWithXpathUsingLocalParam(String name) {
        name = placeholders(name);
        if (TestDataCore.existsInDataStore(name)) {
            getProfile().doubleClick(By.xpath((String) TestDataCore.getDataStore(name)));
        } else {
            throw new Error("Button Definition not found - " + name);
        }
    }

    /**
     * Allows the passing of a data-store name containing
     * the HTML property and value to locate an element and perform a context click.
     *
     * @param name The name of the data-store containing the HTML property and value to locate an element.
     * @since 1.0.4
     */
    @When("^I context click element with Attribute and Value using data-store name \"([^\"]*)\"$")
    public void iContextClickElementWithAttributeAndValueUsingLocalParam(String name) {
        name = placeholders(name);
        if (TestDataCore.existsInDataStore(name)) {
            getProfile().contextClick(XpathHelper.findByPropAndValue(name));
        } else {
            throw new Error("Button Definition not found - " + name);
        }
    }

    /**
     * Allows the passing of a data-store name containing an XPATH
     * string to define the element to perform a context click.
     *
     * @param name The name of the data-store containing the XPATH.
     * @since 1.0.4
     */
    @When("^I context click element with xpath using data-store name \"([^\"]*)\"$")
    public void iContextClickElementWithXpathUsingLocalParam(String name) {
        name = placeholders(name);
        if (TestDataCore.existsInDataStore(name)) {
            getProfile().contextClick(By.xpath((String) TestDataCore.getDataStore(name)));
        } else {
            throw new Error("Button Definition not found - " + name);
        }
    }

    /**
     * Allows the passing of an HTML attribute and a value to define an element
     * and perform a click on it.
     *
     * @param attribute The HTML attribute to be used to locate the element.
     * @param value     The value of the HTML attribute to locate the element.
     * @since 1.0.4
     */
    @When("^I click element with Attribute \"([^\"]*)\" and Value \"([^\"]*)\"$")
    public void iClickElementWithAttributeAndValue(String attribute, String value) {
        attribute = placeholders(attribute);
        value = placeholders(value);
        getProfile().click(XpathHelper.findByPropAndValue(attribute, value));
    }

    /**
     * Performs a click on the currently focused element.
     *
     * @since 1.0.4
     */
    @And("^I click$")
    public void iClick() {
        getProfile().getCurrentFocus().click();
    }

    /**
     * Allows the passing of an HTML property and value to find an element
     * with a partial match on the value and click on it.
     *
     * @param attr  The HTML property to be used to locate the element.
     * @param value The value of the HTML property to locate the element.
     * @since 1.0.4
     */
    @When("^I click element with Attribute \"([^\"]*)\" and Value that contains \"([^\"]*)\"$")
    public void iClickElementWithAttributeThatContains(String attr, String value) {
        attr = placeholders(attr);
        value = placeholders(value);
        getProfile().click(XpathHelper.findByPropertyValueContains(attr, value));
    }

    /**
     * Allows the passing of an HTML attribute and value to find
     * an element with a partial match on value and performs a double click.
     *
     * @param attr  The HTML attribute to be used to locate the element.
     * @param value The value of the HTML attribute to locate the element.
     * @since 1.0.4
     */
    @When("^I double click element with Attribute \"([^\"]*)\" and Value that contains \"([^\"]*)\"$")
    public void iDoubleClickElementWithAttributeAndValueThatContains(String attr, String value) {
        attr = placeholders(attr);
        value = placeholders(value);
        getProfile().doubleClick(XpathHelper.findByPropertyValueContains(attr, value));
    }

    /**
     * Allows the passing of an HTML property and two values to
     * find an element with a partial match on both values and double click on it.
     *
     * @param attr   The HTML attribute to be used to locate the element.
     * @param value  The first value of the HTML attribute to locate the element.
     * @param value2 The second value of the HTML attribute to locate the element.
     * @since 1.0.4
     */
    @When("^I double click element with Attribute \"([^\"]*)\" and Value that contains \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iDoubleClickElementWithAttributeAndValueThatContains(String attr, String value, String value2) {
        attr = placeholders(attr);
        value = placeholders(value);
        value2 = placeholders(value2);
        getProfile().doubleClick(XpathHelper.findByPropertyValueContains(attr, value, value2));
    }

    /**
     * Allows the passing of an HTML attribute and value to find
     * an element with a partial match on value and performs a double click.
     *
     * @param attr  The HTML attribute to be used to locate the element.
     * @param value The value of the HTML attribute to locate the element.
     * @since 1.0.4
     */
    @When("^I context click element with Attribute \"([^\"]*)\" and Value that contains \"([^\"]*)\"$")
    public void iContextClickElementWithAttributeAndValueThatContains(String attr, String value) {
        attr = placeholders(attr);
        value = placeholders(value);
        getProfile().contextClick(XpathHelper.findByPropertyValueContains(attr, value));
    }

    /**
     * Allows the passing of an HTML property and two
     * values to find an element with a partial match on both values and performs a context click on it.
     *
     * @param attr   The HTML attribute to be used to locate the element.
     * @param value  The first value of the HTML attribute to locate the element.
     * @param value2 The second value of the HTML attribute to locate the element.
     * @since 1.0.4
     */
    @When("^I context click element with Attribute \"([^\"]*)\" and Value that contains \"([^\"]*)\" and ads\"([^\"]*)\"$")
    public void iContextClickElementWithAttributeAndValueThatContainsAndAds(String attr, String value, String value2) {
        attr = placeholders(attr);
        value = placeholders(value);
        value2 = placeholders(value2);
        getProfile().contextClick(XpathHelper.findByPropertyValueContains(attr, value, value2));
    }

    /**
     * Allows the passing of an HTML attribute and value and an XPATH string,
     * locating the com.catena.qa.framework.xpath with the HTML attribute and value
     * within the specified XPATH node and performing a standard left click on it.
     *
     * @param attr           The HTML attribute to be used to locate the element.
     * @param value          The first value of the HTML attribute to locate the element.
     * @param containerXpath The XPATH from which to locate the element.
     * @since 1.0.4
     */
    @When("^I click by Attribute \"([^\"]*)\" and Value \"([^\"]*)\" within XPATH \"([^\"]*)\"$")
    public void iClickByAttributeAndValueWithinXPATH(String attr, String value, String containerXpath) {
        attr = placeholders(attr);
        value = placeholders(value);
        containerXpath = placeholders(containerXpath);
        getProfile().click(XpathHelper.findByPropertyValueContainsWithinXpath(containerXpath, attr, value));
    }

    /**
     * Allows the passing of an HTML attribute and value to find an element which
     * is both of type submit and has the specified HTML attribute and value.
     *
     * @param attr  The HTML attribute to be used to locate the element.
     * @param value The value of the HTML attribute to locate the element.
     * @since 1.0.4
     */
    @And("^I click submit with Attribute \"([^\"]*)\" and Value \"([^\"]*)\"$")
    public void iClickSubmitWithAttributeAndValue(String attr, String value) {
        attr = placeholders(attr);
        value = placeholders(value);
        getProfile().click(XpathHelper.submitByProperyAndValue(attr, value));
    }

    /**
     * Clicks on the element identified with the specified ID.
     *
     * @param id ID of the target element.
     * @since 1.0.4
     */
    @Then("^I click on element with ID \"([^\"]*)\"$")
    public void click(String id) {
        id = placeholders(id);
        getProfile().click(By.id(id));
    }

    /**
     * Double clicks on the element identified with the specified ID.
     *
     * @param id ID of the target element.
     * @since 1.0.4
     */
    @Then("^I double click on element with ID \"([^\"]*)\"$")
    public void doubleClick(String id) {
        id = placeholders(id);
        getProfile().doubleClick(By.id(id));
    }

    /**
     * Context clicks (right click) on the element identified with the specified ID.
     *
     * @param id ID of the target element.
     * @since 1.0.4
     */
    @Then("^I context click on element with ID \"([^\"]*)\"$")
    public void contextClick(String id) {
        id = placeholders(id);
        getProfile().contextClick(By.id(id));
    }
}
