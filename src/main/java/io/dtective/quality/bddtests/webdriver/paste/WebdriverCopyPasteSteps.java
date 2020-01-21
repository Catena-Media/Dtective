package io.dtective.quality.bddtests.webdriver.paste;

import io.dtective.test.TestStepsCore;
import io.dtective.xpath.XpathHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Class which contains all the steps related to copying and pasting.
 *
 * @since 1.0
 */
public class WebdriverCopyPasteSteps extends TestStepsCore {

    /**
     * Adds the specified text value to the system clipboard.
     *
     * @param text text to be added to the clipboard.
     * @since 1.0
     */
    @Given("^I put \"([^\"]*)\" in the clipboard$")
    public void addToClipboard(String text) {
        getProfile().clipboardAdd(placeholders(text));
    }

    /**
     * Locates the target element based on the specified XPATH parameter and
     * copies the text within that element to memory with the specified key identifier.
     *
     * @param xpath com.catena.qa.framework.xpath expression to locate the element.
     * @param param memory identifier name for the stored text value.
     * @since 1.0
     */
    @When("^I copy text of element by XPATH \"([^\"]*)\" to param \"([^\"]*)\"$")
    public void copyToMemory(String xpath, String param) {
        getProfile().copyTextToMemory(By.xpath(placeholders(xpath)), placeholders(param));
    }

    /**
     * Locates the target element based on the specified HTML Attribute name-value parameter and
     * copies the text within that element to memory with the specified key identifier.
     *
     * @param attr  HTML attribute name to locate target element.
     * @param value HTML attribute value to locate target element.
     * @param param memory identifier name for the stored text value.
     * @since 1.0
     */
    @When("^I copy text of element by Attribute \"([^\"]*)\" Value \"([^\"]*)\" to param \"([^\"]*)\"$")
    public void copyToMemory(String attr, String value, String param) {
        getProfile().copyTextToMemory(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)), placeholders(param));
    }

    /**
     * Locates the target element based on the specified HTML Attribute name-value parameter and
     * copies the text within that element to clipboard.
     *
     * @param attr  HTML attribute name to locate target element.
     * @param value HTML attribute value to locate target element.
     * @since 1.0
     */
    @When("^I copy text of element by Attribute \"([^\"]*)\" Value \"([^\"]*)\" to clipboard$")
    public void copyToClipboard(String attr, String value) {
        getProfile().copyElementTextToClipboard(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)));
    }

    /**
     * Fetches a stored value from memory using the specified key and pastes it
     * into a target element identified by an XPATH expression parameter.
     *
     * @param param memory identifier name for the stored text value.
     * @param xpath XPATH expression to locate the target element.
     * @since 1.0
     */
    @And("^I paste the value of param \"([^\"]*)\" to element by XPATH \"([^\"]*)\"$")
    public void pasteValueFromMemory(String param, String xpath) {
        getProfile().pasteTextFromMemory(By.xpath(placeholders(xpath)), placeholders(param));
    }

    /**
     * Fetches a stored value from memory using the specified key and pastes it
     * into a target element identified by an HTML Attribute name-value parameter.
     *
     * @param attr  HTML attribute name to locate target element.
     * @param value HTML attribute value to locate target element.
     * @param param memory identifier name for the stored text value.
     * @since 1.0
     */
    @And("^I paste the value of param \"([^\"]*)\" to element by Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void pasteValueFromMemory(String param, String attr, String value) {
        getProfile().pasteTextFromMemory(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)), placeholders(param));
    }

    /**
     * Fetches the value stored in the clipboard and pastes it into the target element
     * identified by an XPATH expression parameter.
     *
     * @param xpath XPATH expression to locate the target element.
     * @since 1.0
     */
    @And("^I paste the value from clipboard to element by XPATH \"([^\"]*)\"$")
    public void pasteValueFromClipboard(String xpath) {
        getProfile().pasteFromClipboard(By.xpath(placeholders(xpath)));
    }

    /**
     * Fetches the value stored in the clipboard and pastes it into the target element
     * identified by an HTML Attribute name-value parameter.
     *
     * @param attr  HTML attribute name to locate target element.
     * @param value HTML attribute value to locate target element.
     * @since 1.0
     */
    @When("^I paste the value from clipboard to element by Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void pasteValueFromClipboard(String attr, String value) {
        getProfile().pasteFromClipboard(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)));
    }


}
