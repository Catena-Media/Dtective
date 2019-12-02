package io.dtective.quality.bddtests.webdriver.scrollTo;

import io.dtective.test.TestStepsCore;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

/**
 * Class which contains all the steps related to scroll actions.
 *
 * @since 1.0
 */
public class WebdriverScrollToSteps extends TestStepsCore {

    /**
     * Scrolls to the element in the current page.
     *
     * @param xpath the XPATH of the element to scroll to.
     * @since 1.0
     */
    @When("^I scroll to element by XPATH \"([^\"]*)\"$")
    public void iScrollToElementByXPATH(String xpath) {
        getProfile().scrollTo(By.xpath(placeholders(xpath)));
    }

}
