package io.dtective.quality.framework.webdriver;

import io.dtective.configuration.ParameterMap;
import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.selenium.Extensions.SharedWebDriver;
import io.dtective.test.SeleniumCore;
import io.dtective.test.TestDataCore;
import io.dtective.test.TestStepsCore;
import io.dtective.user.QAUserProfile;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


/**
 * Class which contains the general steps available to the webdriver.
 *
 * @since 1.0
 */
public class WebdriverStepsCore extends TestStepsCore {

    @Override
    @Before
    @BeforeClass
    public void before(Scenario scenario) {
        super.before(scenario);
    }

    @Override
    @After
    @AfterClass
    public void after() {
        super.after();
    }

    /**
     * Resets the current webdriver.
     *
     * @since 1.0
     */
    @And("^I reset webdriver$")
    public void iResetWebdriver() {
        if (ParameterMap.getParamIsSingleInstance()) {
            SeleniumCore.getWebDriver().getDriver().close();
            SharedWebDriver.setInstance(null);
        } else {
            SeleniumCore.getWebDriver().close();
        }
        TestDataCore.addToDataStore("driver", null);
    }

    @When("^I switch context to iFrame \"([^\"]*)\"$")
    public void iSwitchToIframe(String name) {
        name = BDDPlaceholders.replace(name);

        driver().switchTo().defaultContent();
        driver().switchTo().frame(name);
    }

    @When("^I switch context to original$")
    public void iSwitchToOriginal() {
        driver().switchTo().defaultContent();
    }

    /**
     * The webdriver stops executing for a given time.
     *
     * @param time waiting time in milliseconds.
     * @since 1.0
     */
    @And("^I wait \"([^\"]*)\" ms$")
    public void iWaitMs(String time) {
        try {
            Thread.sleep(Integer.parseInt(time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getProfile().appendScreenshotToScenario(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    /**
     * Asserts if all buttons that match a given XPATH are clickable.
     *
     * @param xpath the key to modify its value.
     * @since 1.0
     */
    @When("^I check that all buttons using XPATH: \"([^\"]*)\" are clickable$")
    public void iCheckAllButtons(String xpath) {

        xpath = BDDPlaceholders.replace(xpath);

        List<WebElement> elementList = driver().getDriver().findElements(By.xpath(xpath));
        Assert.assertNotEquals("Element not found: ", elementList.size(), 0);

        List<WebElement> buttonList = new ArrayList<>(elementList);

        for (WebElement button : buttonList) {
            Assert.assertTrue("Button is not displayed, therefore not clickable", button.isDisplayed());
            Assert.assertTrue("Button is not enabled, therefore not clickable", button.isEnabled());
        }
    }

    /**
     * Asserts if the requested URL does not return any console log severe or warning error messages.
     *
     * @since 1.0
     */
    @Then("^I assert that there are no severe and warning errors in the browser console$")
    public void iAssertThatThereAreNoErrorsInTheBrowserConsole() {

        String logs = QAUserProfile.getCurrent().getLogsByType("browser");

        if (!logs.equals("There are no captured logs") && (logs.contains("[WARNING]") || logs.contains("[SEVERE]")))
            Assert.fail("The following error logs were found: " + logs);
    }

}
