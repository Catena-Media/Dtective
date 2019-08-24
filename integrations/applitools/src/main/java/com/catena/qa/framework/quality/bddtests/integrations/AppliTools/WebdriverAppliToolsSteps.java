package com.catena.qa.framework.quality.bddtests.integrations.AppliTools;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesException;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import com.catena.qa.framework.configuration.ParameterMap;
import com.catena.qa.framework.test.TestStepsCore;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class WebdriverAppliToolsSteps extends TestStepsCore {

    private Eyes eyes;

    /**
     * Solely called by the method: i_close_applitools(), is used to
     * handle all results given back by AppliTools
     *
     * @param result TestResults passed from the i_close_applitools() method
     * @since 1.0.4
     */
    static private void handleResult(TestResults result) {
        String resultStr;
        String url;
        if (result == null) {
            resultStr = "Test aborted";
            url = "undefined";
        } else {
            url = result.getUrl();
            int totalSteps = result.getSteps();
            if (result.isNew()) {
                resultStr = "New Baseline Created: " + totalSteps + " steps";
            } else if (result.isPassed()) {
                resultStr = "All steps passed:     " + totalSteps + " steps";
            } else {
                resultStr = "Test Failed     :     " + totalSteps + " steps";
                resultStr += " matches=" + result.getMatches();      /*  matched the baseline */
                resultStr += " missing=" + result.getMissing();       /* missing in the com.catena.qa.framework.test*/
                resultStr += " mismatches=" + result.getMismatches(); /* did not match the baseline */
            }
        }
        resultStr += "\n" + "results at " + url;
        System.out.println(resultStr);
    }

    /**
     * This step should be used in the beginning of any AppliTools test.
     * Here we are mainly initializing the connections with the application driver and the Eyes Server.
     * The browser width and height are taken from the environment.json or maven arguments.
     *
     * @since 1.0.4
     */
    @Given("^I open AppliTools$")
    public void i_open_applitools() {
        String appName = driver().getCurrentUrl();
        String testName = getScenario().getName();

        RectangleSize viewportSize = new RectangleSize(ParameterMap.getParamBrowserWidth(), ParameterMap.getParamBrowserHeight());

        eyes = new Eyes();
        setup(eyes);

        try {
            eyes.open(driver().getDriver(), appName, testName, viewportSize);
        } catch (EyesException e) {
            if (!ParameterMap.getParamIsSingleInstance())
                Assert.fail("" + e);
            else
                Assert.fail(e + " IsSingleInstance is set to true, please change to false");
        }


    }

    /**
     * This step takes a screen shot of the current page using the appliTools driver
     *
     * @param tagName the tagName used by AppliTools as descriptive name that identifies the checkpoint.
     * @since 1.0.4
     */
    @When("^I check screenshot with applitools with tagname \"([^\"]*)\"$")
    public void i_check_screenshot_with_applitools(String tagName) {

        eyes.checkWindow(tagName);
    }

    /**
     * This step is used at the end of every appliTools scenario.
     * The appliTools driver is closed, and the results are processed and sent to AppliTools.
     *
     * @since 1.0.4
     */
    @Then("^I close AppliTools and handle results$")
    public void i_close_applitools() {
        try {
            TestResults result = eyes.close(ParameterMap.getParamAppliToolsFailingResult());
            handleResult(result);
        } finally {
            eyes.abortIfNotClosed();
        }
    }

    /**
     * This method is solely called by the i_open_applitools()
     * This method sets everything for the test to begin, including a check that the AppliTools API key has been entered
     * in the environment.json or as a maven argument
     *
     * @param eyes driver eyes passed from i_open_applitools()
     * @since 1.0.4
     */
    private void setup(Eyes eyes) {
        Assert.assertNotEquals("Please enter Appli Tools API key", "", ParameterMap.getParamAppliToolsAPIKey());
        eyes.setApiKey(ParameterMap.getParamAppliToolsAPIKey());

        BatchInfo batchInfo = new BatchInfo(ParameterMap.getParamAppliToolsBatchInfo());
        eyes.setBatch(batchInfo);

        eyes.setIgnoreCaret(true);
    }


}
