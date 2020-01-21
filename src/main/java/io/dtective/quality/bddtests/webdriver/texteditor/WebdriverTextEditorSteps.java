package io.dtective.quality.bddtests.webdriver.texteditor;

import io.dtective.selenium.Extensions.QAWebElement;
import io.dtective.test.TestStepsCore;
import io.dtective.utils.WebElementHelper;
import io.dtective.xpath.XpathHelper;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Class which contains all the steps related text editing.
 *
 * @since 1.0
 */
public class WebdriverTextEditorSteps extends TestStepsCore {


    /**
     * Replaces currently focused text from an element with the given text.
     *
     * @param from text from the current element in focus to be replaced.
     * @param to   text which replaces the previous text in the element in focus.
     * @since 1.0
     */
    @And("^I replaceText \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iReplaceTextTo(String from, String to) {
        from = placeholders(from);
        to = placeholders(to);

        QAWebElement focusedElement = getProfile().getCurrentFocus();
        String text = focusedElement.getText();

        if (text.isEmpty()) {
            text = focusedElement.getAttribute("value");
        }

        text = text.replaceAll(from, to);

        focusedElement.clear();
        focusedElement.sendKeys(placeholders(text));
    }

    /**
     * Replaces target(xpath) elements' text with the given text.
     *
     * @param from  text from the current element in focus to be replaced.
     * @param to    text which replaces the previous text in the element in focus.
     * @param xpath target element
     * @since 1.0
     */
    @And("^I replaceText \"([^\"]*)\" to \"([^\"]*)\" in XPATH \"([^\"]*)\"$")
    public void iReplaceTextToXPATH(String from, String to, String xpath) {

        from = placeholders(from);
        to = placeholders(to);
        xpath = placeholders(xpath);

        WebElement focusedElement = WebElementHelper.findElement(By.xpath(xpath));
        String text = focusedElement.getText();

        if (text.isEmpty()) {
            text = focusedElement.getAttribute("value");
        }

        text = text.replaceAll(from, to);

        focusedElement.clear();
        focusedElement.sendKeys(placeholders(text));
    }

    /**
     * Replaces target(html attribute / value) element's text with the given text.
     *
     * @param from      text from the current element in focus to be replaced.
     * @param to        text which replaces the previous text in the element in focus.
     * @param attribute HTML Attribute of the target
     * @param value     HTML Attribute value of the target
     * @since 1.0
     */
    @And("^I replaceText \"([^\"]*)\" to \"([^\"]*)\" in Attribute \"([^\"]*)\" and Value \"([^\"]*)\"$")
    public void iReplaceTextToInAttributeAndValue(String from, String to, String attribute, String value) {

        from = placeholders(from);
        to = placeholders(to);
        value = placeholders(value);

        WebElement focusedElement = WebElementHelper.findElement(
                XpathHelper.findByPropAndValue(attribute, value));
        String text = focusedElement.getText();

        if (text.isEmpty()) {
            text = focusedElement.getAttribute("value");
        }

        text = text.replaceAll(from, to);

        focusedElement.clear();
        focusedElement.sendKeys(placeholders(text));
    }
}
