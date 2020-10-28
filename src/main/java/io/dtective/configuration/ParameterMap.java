package io.dtective.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Framework Parameter Mapping Class.
 */
public class ParameterMap {

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     * <p class="warning">
     * Warning - Used in conjunction with
     * <strong>ISREMOTEBROWSER</strong> parameter
     * </p>
     * <p>Remote execution requires the
     * remote hub URL to be initialized</p>„
     * <p>
     * Remote Chrome WebDriver instance URL
     * E.g.: http://localhost:8050/wd/hub</p>
     * <ul>
     * <li>Must be a fully qualified URL</li>
     * <li>Default : N/A</li>
     * </ul>
     * -
     * <p>Exception is thrown when :</p>
     * <ul>
     * <li>IsRemoteBrowserInstance = true</li>
     * <li>SeleniumHubUrl = null</li>
     * </ul>
     * -
     * <p>Expected value</p>
     * <ul>
     * <li>HTTP Url</li>
     * </ul>
     */

    private static String paramSeleniumHubUrl = "http://hub:4444/wd/hub";
    private static String paramWebdriverTimeoutEnabled = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * Number of seconds to wait for a webpage to load
     * --
     * Default : 6
     * --
     * Expected value (int): whole positive number
     */

    private static String paramWebDriverTimeout = "10";
    /**
     * Maven Parameter binding reference.
     * --
     * Number of seconds to wait for a webpage to load
     * --
     * Default : 6
     * --
     * Expected value (int): whole positive number
     */

    private static String paramWebDriverDelayMS = "0";
    /**
     * Maven Parameter binding reference.
     * --
     * Tested web app url. E.g.: http://test.com
     * Must not include the '/' character at the end
     * --
     * Default : N/A
     * --
     * Expected value : email address
     */

    private static String paramWebAppUrl = "http://qa-server.catena.media";

    /**
     * Maven Parameter binding reference.
     * --
     * HTTPManager class name
     * --
     * Default : com.catena.qa.framework.web.HttpManager
     */
    private static String paramHTTPManagerClassName = "io.dtective.web.HttpManager";

    /**
     * Method to get the class name for the base
     *
     * @return
     */
    public static String getHTTPManagerClassName() {
        return paramHTTPManagerClassName;
    }

    /**
     * Maven Parameter binding reference.
     * --
     * Tested web application user email
     * --
     * Default : N/A
     * --
     * Expected value (string): email address
     */
    private static String paramWebAppUser = "N/A";

    /**
     * Maven Parameter binding reference.
     * --
     * Tested web application user password ENCODED - BASE64
     * --
     * Default : N/A
     * --
     * Expected value (string): Base64 encoded text
     */

    private static String paramWebAppPass = "N/A";
    /**
     * Maven Parameter binding reference.
     * --
     * Controls whether all tests are executed in a single browser
     * --
     * Default : 'true'
     * --
     * Expected values (boolean): true / false
     */
    private static String paramIsSingleInstance = "true";

    /**
     * Maven Parameter binding reference.
     * --
     * Controls to use local driver executable or remote com.catena.qa.framework.web
     * --
     * Default : 'true'
     * --
     * Expected values (boolean): true / false
     */

    private static String paramIsRemoteInstance = "true";
    /**
     * Maven Parameter binding reference.
     * --
     * Controls to width of webdrivers
     * --
     * Default : 'true'
     * --
     * Expected values 1024, 1920
     */

    private static String paramBrowserWidth = "1920";
    /**
     * Maven Parameter binding reference.
     * --
     * Controls to height of webdrivers
     * --
     * Default : 'true'
     * --
     * Expected values 768, 1080
     */

    private static String paramBrowserHeight = "1080";
    /**
     * Maven Parameter binding reference.
     * --
     * Controls which browser types are used for testing
     * --
     * Default : 'chrome'
     * --
     * Expected values (String):
     * - CHROME = "chrome";
     * - FIREFOX = "firefox";
     * - ALL = "all"
     */
    private static String paramBrowserType = "chrome";
    /**
     * Maven Parameter binding reference.
     * --
     * URL of the test automation server
     * --
     * Default : 'none'
     * --
     * Expected value - fully qualified URL of the test automation server
     */
    private static String paramTestServer = "";
    /**
     * Maven Parameter binding reference.
     * --
     * URL of the test automation server
     * --
     * Default : 'none'
     * --
     * Expected value - fully qualified URL of the test automation server
     */
    private static String paramUseTestServer = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * URL of the test automation server
     * --
     * Default : 'none'
     * --
     * Expected value - fully qualified URL of the test automation server
     */
    private static String paramTestProject = "";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls the addition of logs during execution
     * --
     * Default : 'false'
     * --
     * Expected value - fully qualified URL of the test automation server
     */
    private static String paramRecordSeleniumBrowserLogs = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls the addition of logs during execution
     * --
     * Default : 'false'
     * --
     * Expected value - fully qualified URL of the test automation server
     */
    private static String paramRecordSeleniumServerLogs = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls the addition of logs during execution
     * --
     * Default : 'false'
     * --
     * Expected value - fully qualified URL of the test automation server
     */
    private static String paramRecordSeleniumClientLogs = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls the addition of logs during execution
     * --
     * Default : 'false'
     * --
     * Expected value - fully qualified URL of the test automation server
     */
    private static String paramRecordSeleniumDriverLogs = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * String - key in environment JSON file corresponding to the test environment
     * --
     * Default : 'N/A'
     * --
     * Expected value - key for the test environment
     */
    private static String paramTestEnvironment = "N/A";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls the addition of logs during execution
     * --
     * Default : 'false'
     * --
     * Expected value - fully qualified URL of the test automation server
     */
    private static String paramRecordSeleniumPerformanceLogs = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls the addition of logs during execution
     * --
     * Default : 'false'
     * --
     * Expected value - (boolean) true/false
     */
    private static String paramRecordScreenshots = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls highlighting of element for screenshot reporting purpose
     * --
     * Default : 'false'
     * --
     * Expected value - (boolean) true/false
     */
    private static String paramHighlightElements = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls Chrome browser headless mode
     * --
     * Default : 'false'
     * --
     * Expected value - (boolean) true/false
     */
    private static String paramChromeHeadlessMode = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls Firefox browser headless mode
     * --
     * Default : 'false'
     * --
     * Expected value - (boolean) true/false
     */
    private static String paramFirefoxHeadlessMode = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. Controls the enabling of Browsermob Proxy
     * --
     * Default : 'false'
     * --
     * Expected value - (boolean) true/false
     */
    private static String paramBrowsermobProxy = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * If you have a dedicated cloud, then set serverURL to the URL of your server.
     * If you don't have a dedicated cloud, then don't pass anything to the Eyes constructor,
     * and the test will run on the Applitools public cloud.
     * --
     * Default : 'https://eyesapi.applitools.com'
     * --
     * Expected value - (String) url of server
     */
    private static String paramAppliToolsServer = "https://eyesapi.applitools.com";
    /**
     * Maven Parameter binding reference.
     * --
     * If you have a dedicated cloud, then set serverURL to the URL of your server.
     * If you don't have a dedicated cloud, then don't pass anything to the Eyes constructor,
     * and the test will run on the Applitools public cloud.
     * --
     * Default : ''
     * --
     * Expected value - (String) BatchInfoName
     */
    private static String paramAppliToolsBatchInfo = "";
    /**
     * Maven Parameter binding reference.
     * --
     * batches are used to group tests that are run as part of a particular test suite or a build.
     * The simplest way to group multiple tests into a batch is to create a BatchInfo object and
     * then associating it with an eyes instance using the setBatch( ) method. After that,
     * all tests that are opened on that eyes instance will part of that batch.
     * --
     * Default : ''
     * --
     * Expected value - (String) applitools API key
     */
    private static String paramAppliToolsAPIKey = "";
    /**
     * Maven Parameter binding reference.
     * --
     * true/false - boolean parsed value. False means don't throw exception for failed tests
     * --
     * Default : 'false'
     * --
     * Expected value - (boolean) true/false
     */
    private static String paramAppliToolsFailingResult = "false";
    /**
     * Maven Parameter binding reference.
     * --
     * Auth token for Bearer Token Authentication
     * --
     * Default : ''
     * --
     * Expected value - (boolean) true/false
     */
    private static String paramAuthToken = "";
    /**
     * Maven Parameter binding reference.
     * --
     * Default Authentication mode to be set to all requests. Does no effect if set to an empty string
     * --
     * Default : ''
     * --
     * Expected value - (string) Authentication mode (ex: bearer token, basic auth)
     */
    private static String paramDefaultAuth = "";
    /**
     * Maven Parameter binding reference.
     * --
     * Default Basic Authentication Username
     * --
     * Default : ''
     * --
     * Expected value - (string) Basic Authentication Username
     */
    private static String paramBasicAuthUsername = "";
    /**
     * Maven Parameter binding reference.
     * --
     * Default Basic Authentication Password
     * --
     * Default : ''
     * --
     * Expected value - (string) Basic Authentication Password
     */
    private static String paramBasicAuthPassword = "";

    /**
     * Maven Parameter binding reference.
     * --
     * Enables logging and attaching of requests and responses to Allure reports
     * --
     * Default : 'true'
     * --
     * Expected value - (boolean) true or false
     */
    private static String paramAPILoggingEnabled = "true";

    /**
     * Maven Parameter binding reference.
     * --
     * When enabled, this clears HTTP Headers in the included before scenario hook
     * --
     * Default : 'true'
     * --
     * Expected value - (boolean) true or false
     */
    private static String paramAPIClearHeadersBeforeScenario = "true";

    /**
     * Maven Parameter binding reference.
     * --
     * Cloud Provider
     * --
     * Default : ''
     * --
     * Expected value - (string) Cloud Provider name
     */
    private static String paramCloudProvider = "";

    /**
     * Maven Parameter binding reference.
     * --
     * Used to set the complete run on cloud provider (I set it in environment.properties)
     * --
     * Default : false
     * --
     * Expected value - (booelan) Cloud Provider Fixed
     */
    private static String paramCloudProviderFixed = "false";

    /**
     * Maven Parameter binding reference.
     * --
     * Default Cloud platformName
     * --
     * Default : 'macOS 10.14'
     * --
     * Expected value - (string) Cloud Browser Type
     */
    private static String paramCloudPlatformNameSL = "macOS 10.14";

    /**
     * Maven Parameter binding reference.
     * --
     * Default Cloud browser Version
     * --
     * Default : 'latest'
     * --
     * Expected value - (string) Cloud Browser Version
     */
    private static String paramCloudBrowserVersionSL = "latest";


    /**
     * Maven Parameter binding reference.
     * --
     * Default Cloud OS Version
     * --
     * Default : 'Mojave'
     * --
     * Expected value - (string) Cloud OS Version
     */
    private static String paramCloudOSVersionBS = "Mojave";

    /**
     * Maven Parameter binding reference.
     * --
     * Default Cloud OS
     * --
     * Default : 'OS X'
     * --
     * Expected value - (string) Cloud OS
     */
    private static String paramCloudOSBS = "OS X";

    /**
     * Maven Parameter binding reference.
     * --
     * Default Cloud Browser Type
     * --
     * Default : 'chrome'
     * --
     * Expected value - (string) Cloud Browser Type
     */
    private static String paramCloudBrowserType = "chrome";

    /**
     * Maven Parameter binding reference.
     * --
     * SauceLabs Username to run tests on cloud
     * --
     * Default : ''
     * --
     * Expected value - (string) Saucelabs username
     */
    private static String paramSauceUserName = "";

    /**
     * Maven Parameter binding reference.
     * --
     * SauceLabs Access Key to run tests on cloud
     * --
     * Default : ''
     * --
     * Expected value - (string) Saucelabs access key
     */
    private static String paramSauceAccessKey = "";

    /**
     * Maven Parameter binding reference.
     * --
     * SauceLabs Server Address to run tests on cloud
     * --
     * Default : ''
     * --
     * Expected value - (string) Saucelabs Server Address
     */
    private static String paramSauceLabsServer = "";

    /**
     * Maven Parameter binding reference.
     * --
     * Tunnel ID to run tests on cloud
     * --
     * Default : ''
     * --
     * Expected value - (string) Tunnel ID
     */
    private static String paramTunnelID = "";

    /**
     * Maven Parameter binding reference.
     * --
     * BrowserStack Username to run tests on cloud
     * --
     * Default : ''
     * --
     * Expected value - (string) BrowserStack username
     */
    private static String paramBrowserStackUserName = "";

    /**
     * Maven Parameter binding reference.
     * --
     * BrowserStack Access Key to run tests on cloud
     * --
     * Default : ''
     * --
     * Expected value - (string) BrowserStack access key
     */
    private static String paramBrowserStackAccessKey = "";

    /**
     * Maven Parameter binding reference.
     * --
     * BrowserStack Server Address to run tests on cloud
     * --
     * Default : ''
     * --
     * Expected value - (string) BrowserStack Server Address
     */
    private static String paramBrowserStackServer = "";

    public static boolean getParamAPIClearHeadersBeforeScenario() {
        return Boolean.parseBoolean(paramAPIClearHeadersBeforeScenario);
    }

    /**
     * Generated method
     *
     * @return value of APILoggingEnabled config
     */
    public static String getParamAPILoggingEnabled() {
        return paramAPILoggingEnabled;
    }

    /**
     * Class Logger.
     */
    private static Logger classLogger =
            LogManager.getLogger(ParameterMap.class);

    private static Properties prop;

    static {
        initialize();
    }

    /**
     * Static initialization of fields.
     */
    private static void initialize() {
        classLogger.info("Initializing - Parameter Map");
        List<Field> fieldList = Arrays.asList(
                ParameterMap.class.getDeclaredFields());

        prop = new Properties();
        InputStream input = null;

        try {
            classLogger.trace("Initializing - environment.properties file");

            File envProps = new File("./build/environment/environment.properties");
            if (!envProps.exists()) {
                envProps = new File("environment.properties");
            }

            input = new FileInputStream(envProps);

            // load a properties file
            prop.load(input);

            classLogger.trace(prop);

        } catch (IOException ex) {
            classLogger.trace("Failed to initialize");
            classLogger.error(ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        fieldList.stream()
                .filter(x -> x.getName().contains("param"))
                .forEach(field -> {
                    try {

                        String paramBinder = field.getName()
                                .replace("param", "");
                        String value = System.getProperty(
                                paramBinder);

                        String envVar = System.getenv(paramBinder);

                        if (value != null && !value.isEmpty()) {
                            classLogger.info(String.format(
                                    "Using CLI Parameter name -> %s = [%s]",
                                    paramBinder, value));

                            field.set(null, value);
                        } else if (envVar != null && !envVar.isEmpty()) {
                            classLogger.info(String.format(
                                    "Using Environment Variable Parameter name -> %s = [%s]",
                                    paramBinder, envVar));
                            field.set(null, envVar);
                        } else if (prop.get(paramBinder) != null) {
                            classLogger.info(String.format(
                                    "Using (environment.properties) Parameter name -> %s = [%s]",
                                    paramBinder, prop.get(paramBinder)));
                            field.set(null, prop.get(paramBinder));
                        } else {
                            classLogger.info(String.format(
                                    "Using Default Parameter name -> %s = [%s]",
                                    paramBinder, field.get(null)));
                        }

                    } catch (IllegalAccessException e) {
                        classLogger.error(
                                "Exception encountered "
                                        + "while looking for parameter: "
                                        + field.getName());
                        classLogger.error(e);
                    }
                });
    }

    /**
     * Default Authentication Mode to be set to all requests when RequestsAuth is true
     *
     * @return string containing Authentication mode (ex: bearer token, basic auth)
     */
    public static String getParamDefaultAuth() {
        return paramDefaultAuth;
    }

    public static void setParamDefaultAuth(String paramDefaultAuth) {
        ParameterMap.paramDefaultAuth = paramDefaultAuth;
    }

    /**
     * Default Basic Auth Username
     *
     * @return string containing username
     */
    public static String getParamBasicAuthUsername() {
        return paramBasicAuthUsername;
    }

    public static void setParamBasicAuthUsername(String paramBasicAuthUsername) {
        ParameterMap.paramBasicAuthUsername = paramBasicAuthUsername;
    }

    /**
     * Default Basic Auth Password
     *
     * @return string containing password
     */
    public static String getParamBasicAuthPassword() {
        return paramBasicAuthPassword;
    }

    public static void setParamBasicAuthPassword(String paramBasicAuthPassword) {
        ParameterMap.paramBasicAuthPassword = paramBasicAuthPassword;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     * <p class="warning">
     * Warning - Used in conjunction with
     * <strong>ISREMOTEBROWSER</strong> parameter
     * </p>
     * <p>Remote execution requires the
     * remote hub URL to be initialized</p>„
     *
     * <ul>
     * <li>Must be a fully qualified URL</li>
     * <li>Default : N/A</li>
     * </ul>
     * -
     * <p>Exception is thrown when :</p>
     * <ul>
     * <li>IsRemoteBrowserInstance = true</li>
     * <li>SeleniumHubUrl = null</li>
     * </ul>
     * -
     * <p>Expected value</p>
     * <ul>
     * <li>HTTP Url of the Selenium com.catena.qa.framework.web driver API</li>
     * <li>E.g.: http://localhost:8050/wd/hub</li>
     * </ul>
     */
    public static String getParamSeleniumHubUrl() {
        return paramSeleniumHubUrl;
    }

    public static void setParamSeleniumHubUrl(String paramSeleniumHubUrl) {
        ParameterMap.paramSeleniumHubUrl = paramSeleniumHubUrl;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     *
     * <p>Number of seconds to wait for a com.catena.qa.framework.web element to load</p>
     *
     * <ul>
     * <li>Must be a positive whole number</li>
     * <li>Default : 10 (seconds)</li>
     * </ul>
     * -
     * <p>Exception is thrown when :</p>
     * <ul>
     * <li>Number format is invalid</li>
     * </ul>
     * -
     *
     * @return Seconds of implicit timeout
     */
    public static int getParamWebDriverTimeout() {
        return Integer.parseInt(paramWebDriverTimeout);
    }

    public static void setParamWebDriverTimeout(String paramWebDriverTimeout) {
        ParameterMap.paramWebDriverTimeout = paramWebDriverTimeout;
    }

    public static int getParamWebDriverDelayMS() {
        return Integer.parseInt(paramWebDriverDelayMS);
    }

    public static void setParamWebDriverDelayMS(String paramWebDriverDelayMS) {
        ParameterMap.paramWebDriverDelayMS = paramWebDriverDelayMS;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     * <p>Pre-set target website to run tests on.</p>
     *
     * <ul>
     * <li>Must be a fully qualified URL</li>
     * <li>Default : N/A</li>
     * </ul>
     * -
     * <p>Exception is thrown when :</p>
     * <ul>
     * <li>IsRemoteBrowserInstance = true</li>
     * <li>SeleniumHubUrl = null</li>
     * </ul>
     * -
     * <p>Expected value</p>
     * <ul>
     * <li>HTTP Url of the Selenium com.catena.qa.framework.web driver API</li>
     * <li>E.g.: http://localhost:8050/wd/hub</li>
     * </ul>
     */
    public static String getParamWebAppUrl() {
        return paramWebAppUrl;
    }

    public static void setParamWebAppUrl(String paramWebAppUrl) {
        ParameterMap.paramWebAppUrl = paramWebAppUrl;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     * <p>Pre-set com.catena.qa.framework.user name or email for the target website.</p>
     *
     * <ul>
     * <li>Default : N/A</li>
     * </ul>
     */
    public static String getParamWebAppUser() {
        return paramWebAppUser;
    }

    public static void setParamWebAppUser(String paramWebAppUser) {
        ParameterMap.paramWebAppUser = paramWebAppUser;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     * <p>Pre-set com.catena.qa.framework.user password for the target website.</p>
     *
     * <ul>
     * <li>Default : N/A</li>
     * </ul>
     */
    public static String getParamWebAppPass() {
        return paramWebAppPass;
    }

    public static void setParamWebAppPass(String paramWebAppPass) {
        ParameterMap.paramWebAppPass = paramWebAppPass;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     * <p>Test parallelization control.</p>
     *
     * <ul>
     * <li>Default : N/A</li>
     * </ul>
     *
     * <strong>Effects</strong>
     * <ul>
     * <li>True : Automatic scaling and com.catena.qa.framework.test execution distribution.
     * A number of browsers are created when testing starts, one com.catena.qa.framework.test starts where the previous left off</li>
     * <li>False : Uncontrolled scaling, each tests gets a new browser,
     * browser is closed after com.catena.qa.framework.test is done</li>
     * </ul>
     */
    public static boolean getParamIsSingleInstance() {

        if (!paramIsSingleInstance.equalsIgnoreCase("true")
                && !paramIsSingleInstance.equalsIgnoreCase("false")) {
            throw new IllegalArgumentException(
                    String.format(
                            "Configuration value <%s> is not acceptable for IsSingleInstance. "
                                    + "Required is < true / false >",
                            paramIsSingleInstance)
            );
        }

        return Boolean.parseBoolean(paramIsSingleInstance);
    }

    public static void setParamIsSingleInstance(String paramIsSingleInstance) {
        ParameterMap.paramIsSingleInstance = paramIsSingleInstance;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     * <p>Usage of remote com.catena.qa.framework.selenium grid.</p>
     *
     * <ul>
     * <li>Default : true</li>
     * </ul>
     *
     * <strong>Effects</strong>
     * <ul>
     * <li>True : The SELENIUMHUBURL parameter will be used to request a browser from the target server</li>
     * <li>False : Local executables in the project root will be used to create browsers on the current host</li>
     * </ul>
     */
    public static String getParamIsRemoteInstance() {
        return paramIsRemoteInstance;
    }

    public static void setParamIsRemoteInstance(String paramIsRemoteInstance) {
        ParameterMap.paramIsRemoteInstance = paramIsRemoteInstance;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     * <p>Controls which browser types are used for testing.</p>
     *
     * <ul>
     * <li>Default : chrome</li>
     * </ul>
     *
     * <strong>Supported Browsers</strong>
     * <ul>
     * <li>chrome : Chrome Browser</li>
     * <li>firefox : Firefox Browser</li>
     * <li>android : Android Device Browser</li>
     * </ul>
     */
    public static String getParamBrowserType() {
        return paramBrowserType;
    }

    public static void setParamBrowserType(String paramBrowserType) {
        ParameterMap.paramBrowserType = paramBrowserType;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     *
     * <p>Browser size control - Width</p>
     *
     * <ul>
     * <li>Must be a positive whole number</li>
     * <li>Default : 1920 (seconds)</li>
     * </ul>
     * -
     * <p>Exception is thrown when :</p>
     * <ul>
     * <li>Number format is invalid</li>
     * </ul>
     * -
     *
     * @return Number in pixels of browser width
     */
    public static int getParamBrowserWidth() {
        return Integer.parseInt(paramBrowserWidth);
    }

    public static void setParamBrowserWidth(String paramBrowserWidth) {
        ParameterMap.paramBrowserWidth = paramBrowserWidth;
    }

    /**
     * Generated Method - paramBrowserHeight.
     *
     * <p>Browser size control - Width</p>
     *
     * <ul>
     * <li>Must be a positive whole number</li>
     * <li>Default : 1080 (seconds)</li>
     * </ul>
     * -
     * <p>Exception is thrown when :</p>
     * <ul>
     * <li>Number format is invalid</li>
     * </ul>
     * -
     *
     * @return Number in pixels of browser height
     */
    public static int getParamBrowserHeight() {
        return Integer.parseInt(paramBrowserHeight);
    }

    public static void setParamBrowserHeight(String paramBrowserHeight) {
        ParameterMap.paramBrowserHeight = paramBrowserHeight;
    }

    /**
     * Generated Method - getParamRecordSeleniumBrowserLogs.
     *
     * @return whether the selenium session is collecting browser logs.
     */
    public static boolean getParamRecordSeleniumBrowserLogs() {
        return Boolean.parseBoolean(paramRecordSeleniumBrowserLogs);
    }

    /**
     * Generated Method - setParamRecordSeleniumBrowserLogs.
     */
    public static void setParamRecordSeleniumBrowserLogs(String paramRecordSeleniumBrowserLogs) {
        ParameterMap.paramRecordSeleniumBrowserLogs = paramRecordSeleniumBrowserLogs;
    }

    /**
     * Generated Method - getParamRecordSeleniumServerLogs.
     *
     * @return whether the selenium session is collecting server logs.
     */
    public static boolean getParamRecordSeleniumServerLogs() {
        return Boolean.parseBoolean(paramRecordSeleniumServerLogs);
    }

    public static void setParamRecordSeleniumServerLogs(String paramRecordSeleniumServerLogs) {
        ParameterMap.paramRecordSeleniumServerLogs = paramRecordSeleniumServerLogs;
    }

    /**
     * Generated Method - getParamRecordSeleniumClientLogs.
     *
     * @return whether the selenium session is collecting client logs.
     */
    public static boolean getParamRecordSeleniumClientLogs() {
        return Boolean.parseBoolean(paramRecordSeleniumClientLogs);
    }

    public static void setParamRecordSeleniumClientLogs(String paramRecordSeleniumClientLogs) {
        ParameterMap.paramRecordSeleniumClientLogs = paramRecordSeleniumClientLogs;
    }

    /**
     * Generated Method - getParamRecordSeleniumPerformanceLogs.
     *
     * @return whether the selenium session is collecting performance logs.
     */
    public static boolean getParamRecordSeleniumPerformanceLogs() {
        return Boolean.parseBoolean(paramRecordSeleniumPerformanceLogs);
    }

    public static void setParamRecordSeleniumPerformanceLogs(String paramRecordSeleniumPerformanceLogs) {
        ParameterMap.paramRecordSeleniumPerformanceLogs = paramRecordSeleniumPerformanceLogs;
    }

    /**
     * Generated Method - getParamRecordSeleniumDriverLogs.
     *
     * @return whether the selenium session is collecting driver logs.
     */
    public static boolean getParamRecordSeleniumDriverLogs() {
        return Boolean.parseBoolean(paramRecordSeleniumDriverLogs);
    }

    public static void setParamRecordSeleniumDriverLogs(String paramRecordSeleniumDriverLogs) {
        ParameterMap.paramRecordSeleniumDriverLogs = paramRecordSeleniumDriverLogs;
    }

    /**
     * <strong>Maven Parameter binding reference.</strong>
     * <p>--</p>
     * <p>Screenshot recording and addition to the com.catena.qa.framework.test report</p>
     *
     * <ul>
     * <li>Default : true</li>
     * </ul>
     *
     * <strong>Effects</strong>
     * <ul>
     * <li>True : Every interactive step during text execution records a screenshot</li>
     * <li>False : Screenshots are only recorded on-demand</li>
     * </ul>
     */
    public static boolean getParamRecordScreenshots() {
        return Boolean.parseBoolean(paramRecordScreenshots);
    }

    /**
     * Updated selenium browser session to record screenshots during interactions
     *
     * @param paramRecordScreenshots
     */
    public static void setParamRecordScreenshots(String paramRecordScreenshots) {
        ParameterMap.paramRecordScreenshots = paramRecordScreenshots;
    }

    /**
     * HighlightElements
     *
     * @return true / false flag for HighlightElements
     */
    public static boolean getParamHighlightElements() {
        return Boolean.parseBoolean(paramHighlightElements);
    }

    /**
     * Updated the selenium browser session to highlight elements during interactions
     *
     * @param paramHighlightElements
     */
    public static void setParamHighlightElements(String paramHighlightElements) {
        ParameterMap.paramHighlightElements = paramHighlightElements;
    }

    /**
     * CHromeHeadlessMode
     *
     * @return true / false flag for ChromeHeadlessMode
     */
    public static boolean getParamChromeHeadlessMode() {
        return Boolean.parseBoolean(paramChromeHeadlessMode);
    }

    /**
     * Only applicable to newly created selenium sessions.
     *
     * @param paramChromeHeadlessMode true / false
     */
    public static void setParamChromeHeadlessMode(String paramChromeHeadlessMode) {
        ParameterMap.paramChromeHeadlessMode = paramChromeHeadlessMode;
    }

    /**
     * FirefoxHeadlessMode
     *
     * @return true / false flag for FirefoxHeadlessMode
     */
    public static boolean getParamFirefoxHeadlessMode() {
        return Boolean.parseBoolean(paramFirefoxHeadlessMode);
    }

    public static void setParamFirefoxHeadlessMode(String paramFirefoxHeadlessMode) {
        ParameterMap.paramFirefoxHeadlessMode = paramFirefoxHeadlessMode;
    }

    /**
     * Run with Browsermob Proxy capability
     *
     * @return true / false for Browsermob Proxy
     */
    public static boolean getParamBrowsermobProxy() {
        return Boolean.parseBoolean(paramBrowsermobProxy);
    }

    public static void setParamBrowsermobProxy(String paramBrowsermobProxy) {
        ParameterMap.paramBrowsermobProxy = paramBrowsermobProxy;
    }

    /**
     * Parameter value corresponding to the environment to use for the com.catena.qa.framework.test URLs
     *
     * @return String
     */
    public static String getParamTestEnvironment() {
        return paramTestEnvironment;
    }

    /**
     * Changes the key used to get the set of test environment variables from the
     * testEnvironment.json file.
     *
     * @param paramTestEnvironment environment key from the testEnvironment.json file
     */
    public static void setParamTestEnvironment(String paramTestEnvironment) {
        ParameterMap.paramTestEnvironment = paramTestEnvironment;
    }

    /**
     * AppliTools
     *
     * @return AppliTools unique API Key
     */
    public static String getParamAppliToolsAPIKey() {
        return paramAppliToolsAPIKey;
    }

    public static void setParamAppliToolsAPIKey(String paramAppliToolsAPIKey) {
        ParameterMap.paramAppliToolsAPIKey = paramAppliToolsAPIKey;
    }

    /**
     * AppliTools
     *
     * @return AppliTools unique BatchInfo name
     */
    public static String getParamAppliToolsBatchInfo() {
        return paramAppliToolsBatchInfo;
    }

    public static void setParamAppliToolsBatchInfo(String paramAppliToolsBatchInfo) {
        ParameterMap.paramAppliToolsBatchInfo = paramAppliToolsBatchInfo;
    }

    /**
     * AppliTools
     *
     * @return AppliTools failing result. i.e. if com.catena.qa.framework.test will
     * fail if mismatch is found
     */
    public static boolean getParamAppliToolsFailingResult() {
        return Boolean.parseBoolean(paramAppliToolsFailingResult);
    }

    public static void setParamAppliToolsFailingResult(String paramAppliToolsFailingResult) {
        ParameterMap.paramAppliToolsFailingResult = paramAppliToolsFailingResult;
    }

    /**
     * Authentication Bearer Token
     *
     * @return string containing token
     */
    public static String getParamAuthToken() {
        return paramAuthToken;
    }

    public static void setParamAuthToken(String paramAuthToken) {
        ParameterMap.paramAuthToken = paramAuthToken;
    }

    /**
     * Print out the current com.catena.qa.framework.configuration context
     *
     * @return text with all com.catena.qa.framework.configuration properties and values
     */
    public static String mapToString() {

        StringBuilder response = new StringBuilder();

        response.append(" Configuration Values - ").append(ParameterMap.class.getName()).append("/n");

        List<Field> fieldList = Arrays.asList(
                ParameterMap.class.getDeclaredFields());

        fieldList.stream()
                .filter(x -> x.getName().contains("param"))
                .forEach(field -> {
                    try {
                        response.append(String.format("Field <%s> - Value <%s> /n", field.getName(), field.get(null)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        return response.toString();
    }

    private static String paramAndroidUDID = "";

    public static String getParamAndroidUDID() {
        return paramAndroidUDID;
    }

    private static String paramAndroidDeviceName = "";

    public static String getAndroidDeviceName() {
        return paramAndroidDeviceName;
    }

    /**
     * Cloud Provider settings
     */
    public static void setParamCloudProvider(String paramCloudProvider) {
        ParameterMap.paramCloudProvider = paramCloudProvider;
    }

    public static void setParamCloudBrowserType(String paramCloudBrowserType) {
        ParameterMap.paramCloudBrowserType = paramCloudBrowserType;
    }

    public static String getParamCloudProvider() {
        return paramCloudProvider;
    }

    public static String getParamTunnelID() {
        return paramTunnelID;
    }

    public static String getParamCloudProviderFixed() {
        return paramCloudProviderFixed;
    }

    public static String getParamCloudBrowserType() {
        return paramCloudBrowserType;
    }

    public static String getParamCloudPlatformNameSL() {
        return paramCloudPlatformNameSL;
    }

    public static String getParamCloudBrowserVersionSL() {
        return paramCloudBrowserVersionSL;
    }

    public static String getParamCloudOSBS() {
        return paramCloudOSBS;
    }

    public static String getParamCloudOSVersionBS() {
        return paramCloudOSVersionBS;
    }

    public static String getParamSauceUserName() {
        return paramSauceUserName;
    }

    public static String getParamSauceAccessKey() {
        return paramSauceAccessKey;
    }

    public static String getParamSauceLabsServer() {
        return paramSauceLabsServer;
    }

    public static String getParamBrowserStackUserName() {
        return paramBrowserStackUserName;
    }

    public static String getParamBrowserStackAccessKey() {
        return paramBrowserStackAccessKey;
    }

    public static String getParamBrowserStackServer() {
        return paramBrowserStackServer;
    }

}
