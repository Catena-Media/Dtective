package io.dtective.quality.bddtests.webdriver.hover;

import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.test.TestStepsCore;
import io.dtective.xpath.XpathHelper;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Class which provides steps for hovering mouse focus to specific elements.
 * The different methods provide multiple ways of defining the elements to switch mouse focus to.
 *
 * @since 1.0
 */
public class WebdriverHoverSteps extends TestStepsCore {

    /**
     * Allows the passing of a property and a value to define the element to switch mouse focus to.
     *
     * @param property the HTML property to be used to locate the element.
     * @param value    the value of the HTML property to locate the element.
     * @since 1.0
     */
    @When("^I hover over element with Property \"([^\"]*)\" and Value \"([^\"]*)\"$")
    public void iHoverOverElementWithPropertyAndValue(String property, String value) {
        property = BDDPlaceholders.replace(property);
        value = BDDPlaceholders.replace(value);
        getProfile().hoverOver(XpathHelper.findByPropAndValue(property, value));
    }

    /**
     * Allows the passing of an XPATH to define the element to switch mouse focus to.
     *
     * @param xpath the XPATH string to locate the element.
     * @since 1.0
     */
    @When("^I hover over by XPATH \"([^\"]*)\"$")
    public void iHoverOverByXPATH(String xpath) {
        xpath = BDDPlaceholders.replace(xpath);
        getProfile().hoverOver(By.xpath(xpath));
    }

}
