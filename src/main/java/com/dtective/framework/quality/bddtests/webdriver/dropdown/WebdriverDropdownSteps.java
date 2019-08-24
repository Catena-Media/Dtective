package com.dtective.framework.quality.bddtests.webdriver.dropdown;

import com.dtective.framework.test.TestStepsCore;
import com.dtective.framework.xpath.XpathHelper;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Class which contains all the steps related to dropdown web component.
 *
 * @since 1.0.4
 */
public class WebdriverDropdownSteps extends TestStepsCore {

    /**
     * selects the specified target value from the dropdown component identified by the
     * HTML Attribute name-value parameters.
     *
     * @param selectedvalue text value to be selected from the dropdown.
     * @param attr          HTML Attribute name for locating the target dropdown.
     * @param value         HTML Attribute value for locating the target dropdown.
     * @since 1.0.4
     */
    @When("^I select \"([^\"]*)\" from dropdown with Attribute \"([^\"]*)\" Value \"([^\"]*)\"$")
    public void selectDropdownValue(String selectedvalue, String attr, String value) {
        getProfile().dropdownSelect(XpathHelper.findByPropAndValue(attr, value), selectedvalue);
    }

    /**
     * Selects the specified target value from the dropdown component identified by the
     * XPATH expression.
     *
     * @param selectedValue text value to be selected from the dropdown.
     * @param xpath         XPATH expression for locating the target dropdown.
     * @since 1.0.4
     */
    @When("^I select \"([^\"]*)\" from dropdown with xpath \"([^\"]*)\"$")
    public void selectDropdownValue(String selectedValue, String xpath) {
        getProfile().dropdownSelect(By.xpath(xpath), selectedValue);
    }

    /**
     * Selects the specified target value from the dropdown component identified by
     * an XPath expression stored in memory using the specified memory identifier.
     *
     * @param selectedValue text value to be selected from the dropdown.
     * @param param         memory identifier for the stored XPATH expression.
     * @since 1.0.4
     */
    @When("^I select \"([^\"]*)\" from dropdown with xpath using data-store \"([^\"]*)\"$")
    public void selectDropdownValueByXpathFromMemory(String selectedValue, String param) {
        getProfile().dropdownSelect(XpathHelper.findByXpathValue(param), selectedValue);
    }

    /**
     * Selects the specified target value from the dropdown component identified by
     * an HTML Attribute NAME-VALUE pair stored in memory using the specified memory identifier.
     *
     * @param selectedValue text value to be selected from the dropdown.
     * @param param         memory identifier for the stored HTML Attribute name-value pair.
     * @since 1.0.4
     */
    @When("^I select \"([^\"]*)\" from dropdown with Attribute and Value using data-store \"([^\"]*)\"$")
    public void selectDropdownValueByAttributeValueFromMemory(String selectedValue, String param) {
        getProfile().dropdownSelect(XpathHelper.findByPropAndValue(param), selectedValue);
    }
}
