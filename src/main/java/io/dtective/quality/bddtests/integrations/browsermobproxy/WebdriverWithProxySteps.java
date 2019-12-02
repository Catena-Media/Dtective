package io.dtective.quality.bddtests.integrations.browsermobproxy;

import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.test.TestStepsCore;
import io.dtective.utils.NetworkAnalyticsHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.File;

/**
 * Class which contains all steps to use Browsermob Proxy.A free utility to help web developers watch and
 * manipulate network traffic from their AJAX applications. More in: https://github.com/lightbody/browsermob-proxy
 *
 * @since 1.0
 */
public class WebdriverWithProxySteps extends TestStepsCore {

    /**
     * Signals the proxy to start listening to the network traffic and write its contents.
     *
     * @since 1.0
     */
    @When("I start recording the browser interaction")
    public void iRecordBrowserInteraction() {
        NetworkAnalyticsHelper.getProxy().newHar("Har File");
    }

    /**
     * Saves the HAR file of the latest recording on the given filename.
     *
     * @param harFilename filename to store the HAR file.
     * @since 1.0
     */
    @And("I save the HAR file in \"([^\"]*)\"$")
    public void iSaveTheHarFile(String harFilename) {
        NetworkAnalyticsHelper.saveHar(BDDPlaceholders.replace(harFilename));
    }

    /**
     * Stops the proxy from listening to the network traffic.
     *
     * @since 1.0
     */
    @And("I stop recording the browser interaction")
    public void iStopRecordingBrowserInteraction() {
        NetworkAnalyticsHelper.getProxy().stop();
    }

    /**
     * Asserts if the file exists given a file name.
     *
     * @param filename the name of the file to be asserted.
     * @since 1.0
     */
    @Then("^the file \"([^\"]*)\" exists$")
    public void theFileExists(String filename) {

        File file = new File(BDDPlaceholders.replace(filename));
        if (!file.exists()) {
            Assert.fail("The file " + BDDPlaceholders.replace(filename) + " does not exist");
        }
    }
}
