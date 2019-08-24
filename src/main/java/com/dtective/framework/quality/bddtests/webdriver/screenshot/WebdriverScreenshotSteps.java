package com.dtective.framework.quality.bddtests.webdriver.screenshot;

import com.dtective.framework.test.TestDataCore;
import com.dtective.framework.test.TestStepsCore;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

/**
 * Class which provides steps related to screenshots.
 *
 * @since 1.0.4
 */
public class WebdriverScreenshotSteps extends TestStepsCore {

    /**
     * Performs the saving of the screenshot and increment the count stored in data-store named
     * as screenshot-file.
     *
     * @since 1.0.4
     */
    @When("^I take a screenshot$")
    public void iTakeAScreenshot() {

        int count = 0;

        try {
            count = (int) TestDataCore.getDataStore("screenshot-count");
            TestDataCore.removeFromDataStore("screenshot-count");
            TestDataCore.addToDataStore("screenshot-count", count + 1);

        } catch (Exception ex) {
            TestDataCore.addToDataStore("screenshot-count", 1);
        }

        File screenshot = getProfile().getScreenshot("Test-Screenshot_" + count);

        try {
            TestStepsCore.getScenario().embed(FileUtils.readFileToByteArray(screenshot),
                    URLConnection.guessContentTypeFromName(screenshot.getName()));
            TestDataCore.addToDataStore("screenshot-file", screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if screenshot with file name saved in data-store named as screenshot-file
     * actually exists.
     *
     * @since 1.0.4
     */
    @Then("^screenshot is saved$")
    public void screenshotIsSaved() {
        Assert.assertTrue("Screenshot was not recorded", TestDataCore.existsInDataStore("screenshot-count"));
        Assert.assertTrue("Screenshot file was not found",
                ((File) TestDataCore.getDataStore("screenshot-file")).exists());
    }


}
