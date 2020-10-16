package io.dtective.webdrivers;

import io.dtective.configuration.ParameterMap;
import io.dtective.test.SeleniumCore;
import io.dtective.test.TestStepsCore;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Web Driver Capabilities.
 */
public class CloudWebDriverCapabilities {

    /**
     * MAX_DURATION, COMMAND_TIMEOUT, IDLE_TIMEOUT are in seconds.
     */

    private static final int MAX_DURATION = 120;
    private static final int COMMAND_TIMEOUT = 5;
    private static final int IDLE_TIMEOUT = 3;

    /**
     * Returns the URL for the requested cloud provider.
     *
     * @return String URL of Cloud Provider
     */
    public static String getCloudURL() {
        String cloudURL = "";
        if (ParameterMap.getParamCloudProvider().equalsIgnoreCase("saucelabs")) {
            cloudURL = ParameterMap.getParamSauceLabsServer();
        } else if (ParameterMap.getParamCloudProvider().equalsIgnoreCase("browserstack")) {
            cloudURL = "https://" + ParameterMap.getParamBrowserStackUserName() + ":"
                    + ParameterMap.getParamBrowserStackAccessKey() + ParameterMap.getParamBrowserStackServer();
        }
        return cloudURL;
    }

    /**
     * Returns the webdriver capabilities for the requested cloud provider.
     *
     * @return  MutableCapabilities from the cloud provider
     */
    public static MutableCapabilities getCloudCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        try {
            if (ParameterMap.getParamCloudProvider().equalsIgnoreCase("saucelabs")) {
                 MutableCapabilities sauceOpts = new MutableCapabilities();

                 sauceOpts.setCapability("username", ParameterMap.getParamSauceUserName());
                 sauceOpts.setCapability("accessKey", ParameterMap.getParamSauceAccessKey());
                 sauceOpts.setCapability("name", TestStepsCore.getScenario().getName());
                 sauceOpts.setCapability("tags", TestStepsCore.getScenario().getSourceTagNames());
                 sauceOpts.setCapability("maxDuration", MAX_DURATION);
                 sauceOpts.setCapability("commandTimeout", COMMAND_TIMEOUT);
                 sauceOpts.setCapability("idleTimeout", IDLE_TIMEOUT);

                if (!ParameterMap.getParamTunnelID().isEmpty()) {
                    sauceOpts.setCapability("tunnelIdentifier", ParameterMap.getParamTunnelID());
                }

                 capabilities.setCapability("browserName", ParameterMap.getParamCloudBrowserType());
                 capabilities.setCapability("platformName", ParameterMap.getParamCloudPlatformNameSL());
                 capabilities.setCapability("browserVersion", ParameterMap.getParamCloudBrowserVersionSL());
                 capabilities.setCapability("sauce:options", sauceOpts);

            } else if (ParameterMap.getParamCloudProvider().equalsIgnoreCase("browserstack")) {

                MutableCapabilities browserstackOptions = new MutableCapabilities();
                browserstackOptions.setCapability("os", ParameterMap.getParamCloudOSBS());
                browserstackOptions.setCapability("osVersion", ParameterMap.getParamCloudOSVersionBS());
                browserstackOptions.setCapability("browserName", ParameterMap.getParamCloudBrowserType());
                browserstackOptions.setCapability("sessionName", TestStepsCore.getScenario().getName());
                capabilities.setCapability("bstack:options", browserstackOptions);

                if (!ParameterMap.getParamTunnelID().isEmpty()) {
                    capabilities.setCapability("browserstack.local", "true");
                    capabilities.setCapability("browserstack.localIdentifier", ParameterMap.getParamTunnelID());
                }
            }
        } catch (Exception e) {
            throw new Error("Unable to find capability for browser type - " + ParameterMap.getParamCloudBrowserType()
                    + " - " + ParameterMap.getParamCloudProvider());
        }
        return capabilities;
    }



    /**
     * Marks the test result as failed or passed for the specific cloud provider.
     */
    public static void markTestResult(String result) throws URISyntaxException, IOException {
        if (ParameterMap.getParamCloudProvider().contains("browserstack")) {

            String sessionId = ((RemoteWebDriver) SeleniumCore.getWebDriver().getDriver()).getSessionId().toString();

            URI uri = new URI("https://" + ParameterMap.getParamBrowserStackUserName() + ":"
                    + ParameterMap.getParamBrowserStackAccessKey() + "@api.browserstack.com/automate/sessions/"
                    + sessionId + ".json");

            HttpPut putRequest = new HttpPut(uri);
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add((new BasicNameValuePair("status", result)));
            putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpClientBuilder.create().build().execute(putRequest);

        } else if (ParameterMap.getParamCloudProvider().contains("saucelabs")) {
            ((JavascriptExecutor) SeleniumCore.getWebDriver().getDriver())
                    .executeScript("sauce:job-result=" + (result));
             }

        if (!Boolean.parseBoolean(ParameterMap.getParamCloudProviderFixed())) {
            ParameterMap.setParamCloudProvider("");
        }

    }
}
