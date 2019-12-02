package io.dtective.quality.bddtests.webdriver.sendkeys;

import io.dtective.configuration.ParameterMap;
import io.dtective.test.TestStepsCore;
import io.dtective.xpath.XpathHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Class which contains all the steps related to dropdown web component.
 *
 * @since 1.0
 */
public class WebdriverSendkeysSteps extends TestStepsCore {

    /**
     * Sends the specified text to a target field identified by a memory key
     * which stores an XPATH expression as the locator.
     *
     * @param text        text to be send to the target.
     * @param designField memory identifier referencing an XPATH expression.
     * @since 1.0
     */
    @And("^I type \"([^\"]*)\" into Reference \"([^\"]*)\"$")
    public void sendKeysByXpathFromMemory(String text, String designField) {

        By by = XpathHelper.findByPropAndValue(placeholders(designField));

        driver().findElement(by).sendKeys(placeholders(text));
    }

    /**
     * Sends the specified text to the element currently in focus within the browser.
     *
     * @param text text to be sent to the target.
     * @since 1.0
     */
    @When("^I type \"([^\"]*)\"$")
    public void sendKeys(String text) {
        getProfile().sendKeysToCurrentFocus(placeholders(text));
    }

    /**
     * Removes all editable text from the currently focused element within the browser.
     *
     * @since 1.0
     */
    @And("^I clear the field$")
    public void clearField() {
        getProfile().getCurrentFocus().clear();
    }

    /**
     * Removes all editable text from the element located by the HTML Attribute name-value pair
     * parameters.
     *
     * @param attr  HTML Attribute name for locating.
     * @param value HTML Attribute value for locating.
     * @since 1.0
     */
    @And("^I clear the field by Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void clearField(String attr, String value) {
        getProfile().clearField(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)));
    }

    /**
     * Removes all editable text from the element located by the com.catena.qa.framework.xpath expression
     *
     * @param xpath HTML Attribute name for locating.
     * @since 1.0
     */
    @And("^I clear the field by XPath \"([^\"]*)\"$")
    public void clearField(String xpath) {
        getProfile().clearField(By.xpath(placeholders(xpath)));
    }

    /**
     * Sends the specified text to the target element located
     * by the specified HTML Attribute name-value.
     *
     * @param text  text to be sent to the target.
     * @param attr  HTML Attribute name for locating.
     * @param value HTML Attribute value for locating.
     * @since 1.0
     */
    @When("^I type \"([^\"]*)\" into Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void sendKeys(String text, String attr, String value) {
        getProfile().sendKeys(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)), placeholders(text));
    }

    /**
     * Sends the specified text to the target element located
     * by the specified XPATH expression.
     *
     * @param text  text to be sent to the target.
     * @param xpath XPATH expression for locating.
     * @since 1.0
     */
    @When("^I type \"([^\"]*)\" into xpath \"([^\"]*)\"$")
    public void sendKeys(String text, String xpath) {
        getProfile().sendKeys(By.xpath(placeholders(xpath)), placeholders(text));
    }

    /**
     * Sends the specified text to the target element located by the
     * HTML Attribute name-value pair stored in memory referenced by the identifier parameter.
     *
     * @param text  text to be sent to the target.
     * @param param memory reference identifier storing HTML Attribute name-value pair.
     * @since 1.0
     */
    @When("^I type \"([^\"]*)\" into field with Attribute and Value using data-store \"([^\"]*)\"$")
    public void sendKeysReferenceAttributeValue(String text, String param) {
        getProfile().sendKeys(XpathHelper.findByPropAndValue(placeholders(param)), placeholders(text));
    }

    /**
     * Sends the specified text to the target element located by the
     * XPATH expression stored in memory referenced by the identifier parameter.
     *
     * @param text  text to be sent to the target.
     * @param param memory reference identifier storing an XPATH expression.
     * @since 1.0
     */
    @When("^I type \"([^\"]*)\" into field with xpath using data-store \"([^\"]*)\"$")
    public void iTypeIntoFieldWithXpathUsingLocalParameter(String text, String param) {
        getProfile().sendKeys(XpathHelper.findByXpathValue(placeholders(param)), placeholders(text));
    }

    /**
     * Sends the text configured as FRAMEWORK-USER to the target element
     * located by the specified HTML Attribute name-value.
     * <p>
     * The Framework user can be set (in order of priority)
     * 1. Commandline                   : -DWebAppUser=admin@com.catena.qa.framework.test.com
     * 2. Environmental variable        : WebAppUser=admin@com.catena.qa.framework.test.com
     * 3. environment.properties file   : WebAppUser=admin@com.catena.qa.framework.test.com
     *
     * @param attr  HTML Attribute name to locate.
     * @param value HTML Attribute value to locate.
     * @since 1.0
     */
    @When("^I type FrameworkUser into Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void sendKeysFrameworkUser(String attr, String value) {
        getProfile().sendKeys(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)), ParameterMap.getParamWebAppUser());
    }

    /**
     * Sends the text configured as FRAMEWORK-PASSWORD to the target element
     * located by the specified HTML Attribute name-value.
     * <p>
     * The Framework password can be set (in order of priority)
     * 1. Commandline                   : -DWebAppPass=serenity
     * 2. Environmental variable        : WebAppPass=serenity
     * 3. environment.properties file   : WebAppPass=serenity
     *
     * @param attr  HTML Attribute name to locate.
     * @param value HTML Attribute value to locate.
     * @since 1.0
     */
    @When("^I type FrameworkUserPass into Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void sendKeysFrameworkPassword(String attr, String value) {
        getProfile().sendKeys(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)), ParameterMap.getParamWebAppPass());
    }

    /**
     * Sends the text configured as FRAMEWORK-USER to the target element
     * located by the specified XPATH expression.
     * <p>
     * The Framework user can be set (in order of priority)
     * 1. Commandline                   : -DWebAppUser=admin@com.catena.qa.framework.test.com
     * 2. Environmental variable        : WebAppUser=admin@com.catena.qa.framework.test.com
     * 3. environment.properties file   : WebAppUser=admin@com.catena.qa.framework.test.com
     *
     * @param xpath XPATH expression to locate.
     * @since 1.0
     */
    @When("^I type FrameworkUser into xpath \"([^\"]*)\"$")
    public void sendKeysFrameworkUser(String xpath) {
        getProfile().sendKeys(By.xpath(placeholders(xpath)), ParameterMap.getParamWebAppUser());
    }

    /**
     * Sends the text configured as FRAMEWORK-PASSWORD to the target element
     * located by the specified XPATH expression.
     * <p>
     * The Framework password can be set (in order of priority)
     * 1. Commandline                   : -DWebAppPass=serenity
     * 2. Environmental variable        : WebAppPass=serenity
     * 3. environment.properties file   : WebAppPass=serenity
     *
     * @param xpath HTML Attribute name to locate.
     * @since 1.0
     */
    @When("^I type FrameworkUserPass into xpath \"([^\"]*)\"$")
    public void sendKeysFrameworkPassword(String xpath) {
        getProfile().sendKeys(By.xpath(placeholders(xpath)), ParameterMap.getParamWebAppPass());
    }

    /**
     * Sends the text configured as FRAMEWORK-USER to the target element
     * located by the HTML Attribute name-value pair stored in memory referenced by the identifier parameter
     *
     * @param param memory reference to the stored HTML Attribute name-value pair.
     * @since 1.0
     */
    @When("^I type FrameworkUser into field with Attribute and Value using data-store \"([^\"]*)\"$")
    public void sendKeysFrameworkUserReferenceAttributeValue(String param) {
        getProfile().sendKeys(XpathHelper.findByPropAndValue(placeholders(param)), ParameterMap.getParamWebAppUser());
    }

    /**
     * Sends the text configured as FRAMEWORK-PASSWORD to the target element
     * located by the HTML Attribute name-value pair stored in memory referenced by the identifier parameter.
     *
     * @param param memory reference to the stored HTML Attribute name-value pair.
     * @since 1.0
     */
    @When("^I type FrameworkUserPass into field with Attribute and Value using data-store \"([^\"]*)\"$")
    public void sendKeysFrameworkPasswordReferenceAttributeValue(String param) {
        getProfile().sendKeys(XpathHelper.findByPropAndValue(placeholders(param)), ParameterMap.getParamWebAppPass());
    }

    /**
     * Sends the text configured as FRAMEWORK-USER to the target element
     * located by the XPATH expression stored in memory, referenced by the identifier parameter.
     *
     * @param param memory reference to the stored HTML Attribute name-value pair.
     * @since 1.0
     */
    @When("^I type FrameworkUser into field with xpath using data-store \"([^\"]*)\"$")
    public void sendKeysFrameworkUserReferenceXpath(String param) {
        getProfile().sendKeys(XpathHelper.findByXpathValue(placeholders(param)), ParameterMap.getParamWebAppUser());
    }

    /**
     * Sends the text configured as FRAMEWORK-PASSWORD to the target element
     * located by the XPATH expression stored in memory, referenced by the identifier parameter.
     *
     * @param param memory reference to the stored HTML Attribute name-value pair.
     * @since 1.0
     */
    @When("^I type FrameworkUserPass into field with xpath using data-store \"([^\"]*)\"$")
    public void sendKeysFrameworkPasswordReferenceXpath(String param) {
        getProfile().sendKeys(XpathHelper.findByXpathValue(placeholders(param)), ParameterMap.getParamWebAppPass());
    }

    /**
     * Sends the text to the element identified by the specified ID.
     *
     * @param text text to be typed.
     * @param id   HTML ID of the target element.
     * @since 1.0
     */
    @When("^I type \"([^\"]*)\" into field with ID \"([^\"]*)\"$")
    public void iTypeIntoFieldWithID(String text, String id) {
        getProfile().sendKeys(By.id(placeholders(id)), placeholders(text));
    }
}
