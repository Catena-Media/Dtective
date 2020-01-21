package io.dtective.quality.bddtests.webdriver.css;

import io.dtective.test.TestStepsCore;
import io.dtective.xpath.XpathHelper;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

/**
 * Class which contains all the steps which
 * contain different assertions related to CSS attributes and CSS values
 * such as background colour, Font Size, Font Colour
 * taking different input formats: hex and rgba for colour and px for size.
 *
 * @since 1.0
 */
public class WebdriverCSSSteps extends TestStepsCore {

    /**
     * Accepts an XPATH of an element
     * and the expected background colour. The step will then generate the background
     * colour of the given XPATH and compare it to the expected background colour value.
     * The step will fail if there is a difference between the two values.
     *
     * @param xpath         the XPATH of the element which will return a background colour.
     * @param expectedColor the expected background colour of the element, can be in hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with xpath \"([^\"]*)\" has background colour #\"([^\"]*)\"$")
    public void iAssertBackgroundColourXpath(String xpath, String expectedColor) {
        getProfile().compareCSSValue(By.xpath(placeholders(xpath)), "background-color", placeholders(expectedColor));
    }

    /**
     * Accepts an XPATH of an element
     * and the expected font size. The step will then generate the font
     * size of the given XPATH and compare it to the expected font size value.
     * The step will fail if there is a difference between the two values.
     *
     * @param xpath the XPATH of the element which will return a font size.
     * @param size  the expected font size of the element in px format.
     * @since 1.0
     */
    @Then("^I assert that the element with xpath \"([^\"]*)\" has Font Size \"([^\"]*)\" px$")
    public void iAssertFontSizeXpath(String xpath, String size) {
        getProfile().compareCSSValue(By.xpath(placeholders(xpath)), "font-size", placeholders(size));
    }

    /**
     * Accepts an XPATH of an element
     * and the expected font colour. The step will then generate the font
     * colour of the given XPATH and compare it to the expected font colour value.
     * The step will fail if there is a difference between the two values.
     *
     * @param xpath         the XPATH of the element which will return a font colour.
     * @param expectedColor the expected font colour of the element, can be in hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with xpath \"([^\"]*)\" has Font Colour #\"([^\"]*)\"$")
    public void iAssertFontColourXpath(String xpath, String expectedColor) {
        getProfile().compareCSSValue(By.xpath(placeholders(xpath)), "color", placeholders(expectedColor));
    }

    /**
     * Accepts an XPATH of an element,
     * the expected css attribute and expected css value. The step will then generate the css attribute and
     * css value of the given XPATH and compare it to the expected css attribute and css value.
     * The step will fail if there is a difference between the two values.
     * This step is basically a generic step, for the following:
     * iAssertBackgroundColourXpath, iAssertFontSizeXpath, iAssertFontSizeXpath
     *
     * @param xpath        the XPATH of the element which will return a css value.
     * @param cssAttribute the css attribute of the required css value, such as: background-color, color, font-size.
     * @param cssValue     the expected css value, can be in px, hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with xpath \"([^\"]*)\" has CSS Attribute \"([^\"]*)\" with CSS Value \"([^\"]*)\"$")
    public void iAssertCSSattributeHasCSSvalueXpath(String xpath, String cssAttribute, String cssValue) {
        getProfile().compareCSSValue(By.xpath(placeholders(xpath)), placeholders(cssAttribute), placeholders(cssValue));
    }

    /**
     * Accepts a data-store
     * and expected background colour. The step will then generate the background colour
     * of the given data-store value and compare it to the expected background colour.
     * The step will fail if there is a difference between the two values.
     *
     * @param param the data-store name which contains the html attribute and html value of the element.
     * @param color the expected background colour of the element, can be in hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with Attribute and Value using data-store name \"([^\"]*)\" has background colour #\"([^\"]*)\"$")
    public void iAssertAttributeAndValueUsingLocalParamBackgroundColour(String param, String color) {
        getProfile().compareCSSValue(XpathHelper.findByPropAndValue(placeholders(param)), "background-color", placeholders(color));
    }

    /**
     * Accepts a data-store
     * and expected font size. The step will then generate the font size
     * of the given data-store value and compare it to the expected Font Size.
     * The step will fail if there is a difference between the two values.
     *
     * @param param the data-store name which contains the Attribute and value of the element.
     * @param size  the expected font size of the element in px format.
     * @since 1.0
     */
    @Then("^I assert that the element with Attribute and Value using data-store name \"([^\"]*)\" has Font Size \"([^\"]*)\" px$")
    public void iAssertAttributeAndValueUsingLocalParamFontSizePx(String param, String size) {
        getProfile().compareCSSValue(XpathHelper.findByPropAndValue(placeholders(param)), "font-size", placeholders(size));
    }

    /**
     * Accepts a data-store
     * and expected font colour. The step will then generate the font colour
     * of the given data-store value and compare it to the expected font colour colour.
     * The step will fail if there is a difference between the two values.
     *
     * @param param         the data-store name which contains the Attribute and value of the element.
     * @param expectedColor the expected font colour of the element in, rgba or hex format.
     * @since 1.0
     */
    @Then("^I assert that the element with Attribute and Value using data-store name \"([^\"]*)\" has Font Colour #\"([^\"]*)\"$")
    public void iAssertAttributeAndValueUsingLocalParamFontColour(String param, String expectedColor) {
        getProfile().compareCSSValue(XpathHelper.findByPropAndValue(placeholders(param)), "color", placeholders(expectedColor));
    }

    /**
     * Accepts a data-store,
     * expected css attribute and expected css value. The step will then generate the css attribute and css value
     * of the given data-store value and compare it to the expected css attribute and css value.
     * The step will fail if there is a difference between the two values.
     * This step is basically a generic step, for the following:
     * iAssertAttributeAndValueUsingLocalParamBackgroundColour, iAssertAttributeAndValueUsingLocalParamFontSizePx,
     * iAssertAttributeAndValueUsingLocalParamFontColour
     *
     * @param param        the data-store name which contains the html attribute and html value of the element.
     * @param cssAttribute the css attribute of the required css value, such as: background colour, color, font-size.
     * @param cssValue     the expected css value, can be in px, hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with Attribute and Value using data-store name \"([^\"]*)\" has CSS "
            + "Attribute \"([^\"]*)\" with CSS Value \"([^\"]*)\"$")
    public void iAssertAttributeAndValueUsingLocalParamHasCSSAttributeWithCSSValue(String param, String cssAttribute, String cssValue) {
        getProfile().compareCSSValue(XpathHelper.findByPropAndValue(placeholders(param)),
                placeholders(cssAttribute), placeholders(cssValue));
    }

    /**
     * Accepts a data-store
     * and expected background colour. The step will then generate the background colour
     * of the given data-store value and compare it to the expected background colour.
     * The step will fail if there is a difference between the two values.
     *
     * @param param the data-store name which contains the XPATH value of the element.
     * @param color the expected background colour in hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with xpath using data-store name \"([^\"]*)\" has background colour #\"([^\"]*)\"$")
    public void iAssertBackgroundColourUsingXpathFromLocalParam(String param, String color) {
        getProfile().compareCSSValue(XpathHelper.findByXpathValue(placeholders(param)), "background-color", placeholders(color));
    }

    /**
     * Accepts a data-store
     * and expected font size. The step will then generate the font colour
     * of the given data-store value and compare it to the expected background colour.
     * The step will fail if there is a difference between the two values.
     *
     * @param param the data-store name which contains the XPATH of the element.
     * @param size  the expected font size in px format.
     * @since 1.0
     */
    @Then("^I assert that the element with xpath using data-store name \"([^\"]*)\" has Font Size \"([^\"]*)\" px$")
    public void iAssertFontSizePxUsingXpathFromLocalParam(String param, String size) {
        getProfile().compareCSSValue(XpathHelper.findByXpathValue(placeholders(param)), "font-size", placeholders(size));
    }

    /**
     * Accepts a data-store
     * and expected font colour. The step will then generate the font colour
     * of the given data-store value and compare it to the expected font colour.
     * The step will fail if there is a difference between the two values.
     *
     * @param param         the data-store name which contains the XPATH of the element.
     * @param expectedColor the expected font colour in hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with xpath using data-store name \"([^\"]*)\" has Font Colour #\"([^\"]*)\"$")
    public void iAssertFontColourUsingXpathFromLocalParam(String param, String expectedColor) {
        getProfile().compareCSSValue(XpathHelper.findByXpathValue(placeholders(param)), "color", placeholders(expectedColor));
    }

    /**
     * Accepts a data-store,
     * css attribute and css value. The step will then generate the expected css value
     * of the given data-store value and compare it to the expected font colour.
     * The step will fail if there is a difference between the two values.
     * This step is basically a generic step, for the following:
     * iAssertBackgroundColourUsingXpathFromLocalParam, iAssertFontSizePxUsingXpathFromLocalParam,
     * iAssertFontColourUsingXpathFromLocalParam
     *
     * @param param        the data-store name which contains the XPATH of an element.
     * @param cssAttribute the css attribute of the required css value, such as: background colour, color, font-size.
     * @param cssValue     the expected css value, can be in px, hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with xpath using data-store name \"([^\"]*)\" has CSS Attribute \"([^\"]*)\" with"
            + " CSS Value \"([^\"]*)\"$")
    public void iAssertCSSAttributeWithCSSValueUsingXpathFromLocalParam(String param, String cssAttribute, String cssValue) {
        getProfile().compareCSSValue(XpathHelper.findByXpathValue(placeholders(param)),
                placeholders(cssAttribute), placeholders(cssValue));
    }

    /**
     * Accepts a html attribute,
     * html value and expected background colour. The step will then generate the expected background colour
     * of the given html attribute and html value and compare it to the expected background colour.
     * The step will fail if there is a difference between the two values.
     *
     * @param attr  the html attribute of the required element.
     * @param value the html value of the required element.
     * @param color the expected background colour can be in hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with Attribute \"([^\"]*)\" Value \"([^\"]*)\" has background colour #\"([^\"]*)\"$")
    public void iAssertAttributeValueHasBackgroundColour(String attr, String value, String color) {
        getProfile().compareCSSValue(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)),
                "background-color", placeholders(color));
    }

    /**
     * Accepts a html attribute,
     * html value and expected font size. The step will then generate the expected font size
     * of the given html attribute and html value and compare it to the expected font size.
     * The step will fail if there is a difference between the two values.
     *
     * @param attr  the html attribute of the required element.
     * @param value the html value of the required element.
     * @param size  the expected font size in px format.
     * @since 1.0
     */
    @Then("^I assert that the element with Attribute \"([^\"]*)\" Value \"([^\"]*)\" has Font Size \"([^\"]*)\" px$")
    public void iAssertAttributeValueHasFontSizePx(String attr, String value, String size) {
        getProfile().compareCSSValue(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)),
                "font-size", placeholders(size));
    }

    /**
     * Accepts a html attribute,
     * html value and expected font size. The step will then generate the expected font size
     * of the given html attribute and html value and compare it to the expected font size.
     * The step will fail if there is a difference between the two values.
     *
     * @param attr          the html attribute of the required element.
     * @param value         the html value of the required element.
     * @param expectedColor the expected font colour in hex or rgba format.
     * @since 1.0
     */
    @Then("^I assert that the element with Attribute \"([^\"]*)\" Value \"([^\"]*)\" has Font Colour #\"([^\"]*)\"$")
    public void iAssertAttributeValueHasFontColour(String attr, String value, String expectedColor) {
        getProfile().compareCSSValue(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)),
                "color", placeholders(expectedColor));
    }

    /**
     * Accepts an html attribute,
     * html value, expected css attribute and expected css value. The step will then generate the
     * css attribute and css value of the given html attribute and html value and
     * compare. The step will fail if there is a difference between the two values.
     * This step is basically a generic step, for the following: iAssertAttributeValueHasBackgroundColour,
     * iAssertAttributeValueHasFontSizePx, iAssertAttributeValueHasFontColour
     *
     * @param attr         the html attribute of the required element.
     * @param value        the html value of the required element.
     * @param cssAttribute the expected css attribute, such as: background colour, color, font-size.
     * @param cssValue     the expected css value, the format can be in: px, rgba or hex.
     * @since 1.0
     */
    @Then("^I assert that the element with Attribute \"([^\"]*)\" Value \"([^\"]*)\" has CSS Attribute \"([^\"]*)\" with"
            + " CSS Value \"([^\"]*)\"$")
    public void iAssertAttributeValueHasCSSAttributeWithCSSValue(String attr, String value, String cssAttribute, String cssValue) {
        getProfile().compareCSSValue(XpathHelper.findByPropAndValue(placeholders(attr), placeholders(value)),
                placeholders(cssAttribute), placeholders(cssValue));
    }
}
