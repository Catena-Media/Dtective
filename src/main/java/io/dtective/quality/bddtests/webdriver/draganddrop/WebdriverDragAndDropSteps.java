package io.dtective.quality.bddtests.webdriver.draganddrop;

import io.dtective.test.TestStepsCore;
import io.dtective.xpath.XpathHelper;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * WebdriverDragAndDropSteps is the class which provides steps for performing drag and drop actions from one element to
 * another.
 *
 * @since 1.0
 */
public class WebdriverDragAndDropSteps extends TestStepsCore {

    /**
     * Allows the passing of two sets of HTML property and value defining the element from which the
     * drag action is performed and the element to which the drop action is performed.
     *
     * @param propertyFrom the HTTP property to be used to locate the element from which to drag.
     * @param valueFrom    the value of the HTML property to locate the element from which to drag.
     * @param propertyTo   the HTTP property to be used to locate the element on which to drop.
     * @param valueTo      the value of the HTML property to locate the element on which to drop.
     * @since 1.0
     */
    @When("^I drag from element with property \"([^\"]*)\" and value \"([^\"]*)\" and drop into element with "
            + "property \"([^\"]*)\" and value \"([^\"]*)\"$")
    public void iDragInto(String propertyFrom, String valueFrom, String propertyTo, String valueTo) {

        propertyFrom = placeholders(propertyFrom);
        valueFrom = placeholders(valueFrom);
        propertyTo = placeholders(propertyTo);
        valueTo = placeholders(valueTo);

        By source = XpathHelper.findByPropAndValue(propertyFrom, valueFrom);
        By target = XpathHelper.findByPropAndValue(propertyTo, valueTo);

        getProfile().dragAndDrop(source, target);
    }

}
