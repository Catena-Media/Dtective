package io.dtective.quality.bddtests.configuration;

import io.dtective.configuration.ParameterMap;
import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.test.TestStepsCore;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.BrowserType;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Contains all the steps related to Configuration of parameters for the test run. These configurations can be
 * set (in order of priority):
 * * 1. Commandline                   : -DExampleParameter=true
 * * 2. Environmental variable        : ExampleParameter=true
 * * 3. environment.properties file   : ExampleParameter=true
 * These steps allow the modification and assertion of the configuration values during the test. It is necessary to
 * reset the webdriver so the configuration takes effect. Please refer to Configuration.feature for
 * examples of its usage.
 *
 * @since 1.0
 */
public class ConfigurationSteps extends TestStepsCore {

    private List<Field> fieldList;

    public ConfigurationSteps() {
        fieldList = Arrays.asList(
                ParameterMap.class.getDeclaredFields());
    }

    /**
     * Sets the given configuration field to the given configuration value. For the available configuration parameters,
     * refer to the environment.properties file.
     *
     * @param field the configuration parameter to be modified.
     * @param value the value to modify the configuration parameter.
     * @since 1.0
     */
    @When("^I set configuration \"([^\"]*)\" to value \"([^\"]*)\"$")
    public void iSetConfigurationToValue(String field, String value) {

        fieldList.stream()
                .filter(x -> x.getName().equals("param" + field))
                .forEach(paramField -> {
                    try {
                        paramField.setAccessible(true);
                        paramField.set(null, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * Asserts that the configuration field has the expected value. For the available configuration parameters,
     * refer to the environment.properties file.
     *
     * @param field the configuration parameter to be asserted.
     * @param value the value to assert on the configuration parameter.
     * @since 1.0
     */
    @Then("^configuration \"([^\"]*)\" value is \"([^\"]*)\"$")
    public void configurationValueIs(String field, String value) {
        fieldList.stream()
                // Include prefix
                .filter(x -> x.getName().equals(field))
                .forEach(paramField -> {
                    try {
                        if (!paramField.get(null).equals(value))
                            Assert.fail(String.format("Value of %s was expected to be %s but was %s",
                                    field, value, paramField.get(ParameterMap.class)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

    }

    /**
     * Sets the configuration field of Selenium-Hub URL with the given value. The configuration for the Selenium-Hub URL
     * can be found in environment.properties.
     *
     * @param url the URL where the Selenium-Hub is running.
     * @since 1.0
     */
    @When("^I set Selenium-Hub url to \"([^\"]*)\"$")
    public void iSetSeleniumHubUrlTo(String url) {
        ParameterMap.setParamSeleniumHubUrl(url);
    }

    /**
     * Asserts if the Selenium-Hub URL configuration value is set to the given URL.
     *
     * @param url the URL where the Selenium-Hub should be running.
     * @since 1.0
     */
    @Then("^Selenium-Hub url is set to \"([^\"]*)\"$")
    public void seleniumHubUrlIsSetTo(String url) {
        assert ParameterMap.getParamSeleniumHubUrl().equals(url);
    }

    /**
     * Sets the configuration field of WebDriver Timeout with the given value. The configuration for the WebDriver
     * Timeout can be found in environment.properties.
     *
     * @param timeout the value for the timeout in seconds.
     * @since 1.0
     */
    @When("^I set Webdriver Timeout to \"([^\"]*)\"$")
    public void iSetWebdriverTimeoutTo(String timeout) {
        ParameterMap.setParamWebDriverTimeout(timeout);
    }

    /**
     * Asserts if the WebDriver Timeout configuration value is set to the given timeout value.
     *
     * @param timeout the value for the timeout in seconds.
     * @since 1.0
     */
    @Then("^Webdriver Timeout is set to \"([^\"]*)\"$")
    public void webdriverTimeoutIsSetTo(String timeout) {
        assert ParameterMap.getParamWebDriverTimeout() == Integer.parseInt(timeout);
    }

    /**
     * Sets the configuration field of WebDriver Delay with the given value. The configuration for the WebDriver Delay
     * can be found in environment.properties.
     *
     * @param delay the delay value in milliseconds.
     * @since 1.0
     */
    @When("^I set Webdriver Delay MS to \"([^\"]*)\"$")
    public void iSetWebdriverDelayMSTo(String delay) {
        ParameterMap.setParamWebDriverDelayMS(delay);
    }

    /**
     * Asserts if the WebDriver Delay configuration value is set to the given delay.
     *
     * @param delay the delay value in milliseconds.
     * @since 1.0
     */
    @Then("^Webdriver Delay MS is set to \"([^\"]*)\"$")
    public void webdriverDelayMSIsSetTo(String delay) {
        assert ParameterMap.getParamWebDriverDelayMS() == Integer.parseInt(delay);
    }

    /**
     * Sets the configuration field of Browser Type with the given value. The configuration for the Browser Type
     * can be found in environment.properties.
     *
     * @param browser the browser values supported are chrome, firefox and android.
     * @since 1.0
     */
    @When("^I set Browser Type to \"([^\"]*)\"$")
    public void iSetBrowserTypeTo(String browser) {
        ParameterMap.setParamBrowserType(browser);
    }

    /**
     * Asserts if the Browser Type configuration value is set to the given browser.
     *
     * @param browser the browser values supported are chrome, firefox and android.
     * @since 1.0
     */
    @Then("^Browser Type is set to \"([^\"]*)\"$")
    public void browserTypeIsSetTo(String browser) {
        assert ParameterMap.getParamBrowserType().equals(browser);
    }

    /**
     * Sets the configuration field of Browser Width and Browser Height with the given values. The configuration for
     * the Browser Width and Browser Height can be found in environment.properties.
     *
     * @param width  browser width in pixels.
     * @param height browser height in pixels.
     * @since 1.0
     */
    @When("^I set Browser Size to width \"([^\"]*)\" and heigth \"([^\"]*)\"$")
    public void iSetBrowserSizeToWidthAndHeigth(String width, String height) {
        ParameterMap.setParamBrowserWidth(width);
        ParameterMap.setParamBrowserHeight(height);
    }

    /**
     * Asserts if the Browser Width and Browser Height configuration fields are set with the given values.
     *
     * @param width  browser width in pixels.
     * @param height browser height in pixels.
     * @since 1.0
     */
    @Then("^Browser Size is set to width \"([^\"]*)\" and heigth \"([^\"]*)\"$")
    public void browserSizeIsSetToWidthAndHeigth(String width, String height) {
        assert ParameterMap.getParamBrowserWidth() == Integer.parseInt(width);
        assert ParameterMap.getParamBrowserHeight() == Integer.parseInt(height);
    }

    /**
     * Sets the configuration field of the current Browser Width and Browser Height with the given values.
     *
     * @param pwidth  browser width in pixels.
     * @param pheight browser height in pixels.
     * @since 1.0
     */
    @When("^I set current Browser Size to width \"([^\"]*)\" and height \"([^\"]*)\"$")
    public void iSetCurrentBrowserSizeToWidthAndHeight(String pwidth, String pheight) {
        int width, height;
        width = Integer.parseInt(pwidth);
        height = Integer.parseInt(pheight);

        //set window size
        if (!ParameterMap.getParamBrowserType().equals(BrowserType.ANDROID)) {
            driver().manage().window().setSize(new Dimension(width, height));
        }
    }

    /**
     * Asserts if the current Browser size is set to the given Width and Height.
     *
     * @param width  browser width in pixels.
     * @param height browser width in pixels.
     * @since 1.0
     */
    @Then("^current Browser Size is set to width \"([^\"]*)\" and height \"([^\"]*)\"$")
    public void currentBrowserSizeIsSetToWidthAndHeight(String width, String height) {
        assert driver().manage().window().getSize().width == Integer.parseInt(width);
        assert driver().manage().window().getSize().height == Integer.parseInt(height);
    }

    /**
     * Sets the configuration field of WebApp URL with the given value. The configuration for
     * the WebApp URL can be found in environment.properties.
     *
     * @param url fully qualified url for the WebApp.
     * @since 1.0
     */
    @When("^I set Target WebApp url to \"([^\"]*)\"$")
    public void iSetTargetWebAppUrlTo(String url) {
        ParameterMap.setParamWebAppUrl(BDDPlaceholders.replace(url));
    }

    /**
     * Asserts if the WebApp URL configuration value is set to the given URL.
     *
     * @param url fully qualified url for the WebApp.
     * @since 1.0
     */
    @Then("^Target WebApp url is set to \"([^\"]*)\"$")
    public void targetWebAppUrlIsSetTo(String url) {
        assert ParameterMap.getParamWebAppUrl().equals(url);
    }

    /**
     * Sets the configuration field of WebApp Username with the given value. The configuration for
     * the WebApp Username can be found in environment.properties.
     *
     * @param username credential for the WebApp.
     * @since 1.0
     */
    @When("^I set WebApp User Name to \"([^\"]*)\"$")
    public void iSetWebAppUserNameTo(String username) {
        ParameterMap.setParamWebAppUser(username);
    }

    /**
     * Asserts if the WebApp Username configuration value is set to the given username.
     *
     * @param username credential for the WebApp.
     * @since 1.0
     */
    @Then("^WebApp User Name is set to \"([^\"]*)\"$")
    public void webappUserNameIsSetTo(String username) {
        assert ParameterMap.getParamWebAppUser().equals(username);
    }

    /**
     * Sets the configuration field of WebApp Password with the given value. The configuration for
     * the WebApp Password can be found in environment.properties.
     *
     * @param password credential for the WebApp.
     * @since 1.0
     */
    @When("^I set WebApp User Password to \"([^\"]*)\"$")
    public void iSetWebAppUserPasswordTo(String password) {
        ParameterMap.setParamWebAppPass(password);
    }

    /**
     * Asserts if the WebApp Password configuration value is set to the given password.
     *
     * @param password credential for the WebApp.
     * @since 1.0
     */
    @Then("^WebApp User Password is set to \"([^\"]*)\"$")
    public void webappUserPasswordIsSetTo(String password) {
        assert ParameterMap.getParamWebAppPass().equals(password);
    }

    /**
     * Sets the configuration field of TestEnvironment with the given value. This value needs to be defined
     * in testEnvironment.json. The current configuration for TestEnvironment can be found in environment.properties.
     *
     * @param environment key value from testEnvironment.json
     * @since 1.0
     */
    @When("^I set Test Environment JSON to \"([^\"]*)\"$")
    public void iSetTestEnvironmentJSONTo(String environment) {
        ParameterMap.setParamTestEnvironment(environment);
    }

    /**
     * Asserts if the TestEnvironment configuration value is set to the given environment key.
     *
     * @param environment key value from testEnvironment.json
     * @since 1.0
     */
    @Then("^Test Environment JSON is set to \"([^\"]*)\"$")
    public void testEnvironmentJSONIsSetTo(String environment) {
        assert ParameterMap.getParamTestEnvironment().equals(environment);
    }

    /**
     * Sets the configuration field of Record Screenshots with the given value. The configuration for
     * the Record Screenshots can be found in environment.properties.
     *
     * @param bool true or false if screenshot recording capability is enabled or disabled, respectively.
     * @since 1.0
     */
    @When("^I set Record Screenshots \"([^\"]*)\"$")
    public void iSetRecordScreenshots(String bool) {
        ParameterMap.setParamRecordScreenshots(bool);
    }

    /**
     * Asserts if the Record Field configuration value is set to the given value.
     *
     * @param bool true or false if screenshot recording capability is enabled or disabled, respectively.
     * @since 1.0
     */
    @Then("^Record Screenshots is set to \"([^\"]*)\"$")
    public void recordScreenshotsIsSetTo(String bool) {
        assert ParameterMap.getParamRecordScreenshots() == Boolean.parseBoolean(bool);
    }

    /**
     * Sets the configuration field of Highlight Elements with the given value. The configuration for
     * the Highlight Elements can be found in environment.properties.
     *
     * @param bool true or false if element highlighting capability is enabled or disabled, respectively.
     * @since 1.0
     */
    @When("^I set Highlight Elements \"([^\"]*)\"$")
    public void iSetHighlightElements(String bool) {
        ParameterMap.setParamHighlightElements(bool);
    }

    /**
     * Asserts if the Highlight Elements configuration value is set to the given value.
     *
     * @param bool true or false if element highlighting capability is enabled or disabled, respectively.
     * @since 1.0
     */
    @Then("^Highlight Elements is set to \"([^\"]*)\"$")
    public void highlightElementsIsSetTo(String bool) {
        assert ParameterMap.getParamHighlightElements() == Boolean.parseBoolean(bool);
    }

    /**
     * Sets the configuration field of Browsermob Proxy with the given value. The configuration for
     * the Browsermob Proxy can be found in environment.properties.
     *
     * @param bool true or false if Browsermob proxy capability is enabled or disabled, respectively.
     * @since 1.0
     */
    @When("^I set Browser Mob Proxy \"([^\"]*)\"$")
    public void iSetBrowserMobProxy(String bool) {
        ParameterMap.setParamBrowsermobProxy(bool);
    }

    /**
     * Asserts if the Browsermob Proxy configuration value is set to the given value.
     *
     * @param bool true or false if Browsermob proxy capability is enabled or disabled, respectively.
     * @since 1.0
     */
    @Then("^Browser Mob Proxy is set to \"([^\"]*)\"$")
    public void browserMobProxyIsSetTo(String bool) {
        assert ParameterMap.getParamBrowsermobProxy() == Boolean.parseBoolean(bool);
    }

    /**
     * Sets the configuration fields of SauceLabs and Single Instance to run on cloud provider.
     * The default value for these fields can be found in environment.properties.
     * This step overwrites default values.
     */
    @When("^I want to run tests on SauceLabs$")
    public void iWantToRunTestsOnSauceLabs() {
        ParameterMap.setParamCloudProvider("saucelabs");
        ParameterMap.setParamIsSingleInstance("false");
    }

    @When("^I want to run tests on mobile device on SauceLabs$")
    public void iWantToRunTestsOnMobileDeviceSauceLabs() {
        ParameterMap.setParamCloudProvider("saucelabs");
        ParameterMap.setParamIsSingleInstance("false");
    }

    /**
     * Sets the configuration fields of BrowserStack and Single Instance to run on cloud provider.
     * The default value for these fields can be found in environment.properties.
     * This step overwrites default values.
     */
    @When("^I want to run tests on BrowserStack")
    public void iWantToRunTestsOnBrowserStack() {
        ParameterMap.setParamCloudProvider("browserstack");
        ParameterMap.setParamIsSingleInstance("false");
    }

    /**
     * Sets the configuration field of SauceLabs Browser Type to use on cloud provider.
     * The default value can be found in environment.properties.
     * This step overwrites it.
     */
    @When("^I set cloud browser to \"([^\"]*)\"$")
    public void iSetCloudBrowserTo(String browser) {
        ParameterMap.setParamCloudBrowserType(browser);
    }
}
