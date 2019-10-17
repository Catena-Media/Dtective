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
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Web Driver Capabilities.
 */
public class CloudWebDriverCapabilities {

    private static LoggingPreferences getLoggingPreferences() {
        LoggingPreferences logPrefs = new LoggingPreferences();

        if (ParameterMap.getParamRecordSeleniumBrowserLogs()) {
            logPrefs.enable(LogType.BROWSER, Level.INFO);
        }

        if (ParameterMap.getParamRecordSeleniumServerLogs()) {
            logPrefs.enable(LogType.SERVER, Level.INFO);
        }

        if (ParameterMap.getParamRecordSeleniumClientLogs()) {
            logPrefs.enable(LogType.CLIENT, Level.INFO);
        }

        if (ParameterMap.getParamRecordSeleniumPerformanceLogs()) {
            logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        }

        if (ParameterMap.getParamRecordSeleniumDriverLogs()) {
            logPrefs.enable(LogType.DRIVER, Level.INFO);
        }

        return logPrefs;

    }

    /**
     * Returns the URL for the requested cloud provider.
     *
     * @return String URL
     */

    public static final int MAX_DURATION = 60;
    public static final int COMMAND_TIMEOUT = 5;
    public static final int IDLE_TIMEOUT = 3;

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
     * @return - MutableCapabilities
     */
    public static MutableCapabilities getCloudCapabilities() {
        MutableCapabilities capabilities = new MutableCapabilities();

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

                /** Set a second MutableCapabilities object to pass Sauce Options and Browser Type **/
                sauceOpts.setCapability("seleniumVersion", "3.141.59");
                capabilities.setCapability("sauce:options", sauceOpts);
                capabilities.setCapability("w3c", true);
                capabilities.setCapability("browserName", ParameterMap.getParamCloudBrowserType());
                capabilities.setCapability("platformName", "macOS 10.14");
                capabilities.setCapability("browserVersion", "latest");

            } else if (ParameterMap.getParamCloudProvider().equalsIgnoreCase("browserstack")) {

                MutableCapabilities browserstackOptions = new MutableCapabilities();
                browserstackOptions.setCapability("seleniumVersion", "3.5.2");
                browserstackOptions.setCapability("os", "OS X");
                browserstackOptions.setCapability("browserName", ParameterMap.getParamCloudBrowserType());
                browserstackOptions.setCapability("osVersion", "Mojave");
                browserstackOptions.setCapability("local", "false");
                browserstackOptions.setCapability("sessionName", TestStepsCore.getScenario().getName());
                capabilities.setCapability("bstack:options", browserstackOptions);

            }
        } catch (Exception e) {
            throw new Error("Unable to find capability for browser type - " + ParameterMap.getParamCloudBrowserType()
                    + " - " + ParameterMap.getParamCloudProvider());
        }
        return capabilities;
    }



    /**
     * Marks the test result for the specific cloud provider.
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

            // Note BrowserStack
            // The driver.quit statement is required, otherwise the test continues to execute, leading to a timeout.
            // SeleniumCore.getWebDriver().getDriver().quit();

        } else if (ParameterMap.getParamCloudProvider().contains("saucelabs")) {
            ((JavascriptExecutor) SeleniumCore.getWebDriver().getDriver())
                    .executeScript("sauce:job-result=" + (result));
        }
        ParameterMap.setParamCloudProvider("");
        ParameterMap.setParamCloudProviderMobile(false);
    }
}
