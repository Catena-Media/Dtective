package io.dtective.quality.bddtests.webdriver.keys;

import io.dtective.test.TestStepsCore;
import io.dtective.xpath.XpathHelper;
import cucumber.api.java.en.And;
import org.openqa.selenium.Keys;

/**
 * Class which contains all the steps related specific key pressings.
 *
 * @since 1.0
 */
public class WebdriverKeysSteps extends TestStepsCore {

    /**
     * Sends an ENTER key to the current focus element.
     *
     * @since 1.0
     */
    @And("^I press enter$")
    public void iPressEnter() {
        getProfile().sendKeysToCurrentFocus(Keys.ENTER);
    }

    /**
     * Sends an ENTER key to a previously stored field.
     *
     * @param designField a previously stored field name.
     * @since 1.0
     */
    @And("^I press enter into \"([^\"]*)\"$")
    public void iPressEnterInto(String designField) {

        getProfile().sendKeys(XpathHelper.findByPropAndValue(placeholders(designField)), Keys.ENTER);
    }

    /**
     * Sends an ENTER key to an element located by its property and value.
     *
     * @param property property of the element to be located.
     * @param value    value of the element to be located.
     * @since 1.0
     */
    @And("^I press enter into \"([^\"]*)\" \"([^\"]*)\"$")
    public void iPressEnterInto(String property, String value) {
        getProfile().sendKeys(XpathHelper.findByPropAndValue(placeholders(property), placeholders(value)), Keys.ENTER);
    }

    /**
     * Sends a space character to the current focus element.
     *
     * @since 1.0
     */
    @And("^I press space$")
    public void iPressSpace() {
        getProfile().sendKeysToCurrentFocus(" ");
    }

    /**
     * Sends a TAB key to the current focus element.
     *
     * @since 1.0
     */
    @And("^I press tab$")
    public void iPressTab() {
        getProfile().sendKeysToCurrentFocus(Keys.TAB);
    }

    /**
     * Sends an ESC key to the current focus element.
     *
     * @since 1.0
     */
    @And("^I press esc$")
    public void iPressEsc() {
        getProfile().sendKeysToCurrentFocus(Keys.ESCAPE);
    }
}
