package io.dtective.webdrivers;

import io.dtective.configuration.ParameterMap;
import io.dtective.utils.NetworkAnalyticsHelper;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.logging.Level;

/**
 * Web Driver Capabilities.
 */
public class WebDriverCapabilities {

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
     * Returns the webdriver capabilities for the requested type.
     *
     * @param type - BrowserType, eg. chrome
     * @return - DesiredCapabilies
     */
    public static DesiredCapabilities getCapabilities(String type) {

        DesiredCapabilities capa = null;

        switch (type) {

            case BrowserType.CHROME: {

                ChromeOptions options = new ChromeOptions();

                if (ParameterMap.getParamChromeHeadlessMode())
                    options.addArguments("--headless");

                capa = DesiredCapabilities.chrome();
                capa.setBrowserName("chrome");

                capa.setCapability(CapabilityType.LOGGING_PREFS, getLoggingPreferences());

                capa.setJavascriptEnabled(true);
                capa.setCapability(ChromeOptions.CAPABILITY, options);

                capa.setCapability(CapabilityType
                        .ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                capa.setCapability(CapabilityType
                        .ForSeleniumServer.AVOIDING_PROXY, true);
                capa.setCapability("chrome.switches",
                        Collections.singletonList("--incognito"));
                break;

            }
            case BrowserType.FIREFOX: {

                FirefoxOptions options = new FirefoxOptions();
                if (ParameterMap.getParamFirefoxHeadlessMode())
                    options.setHeadless(true);

                capa = DesiredCapabilities.firefox();
                capa.setBrowserName(BrowserType.FIREFOX);
                capa.setCapability("marionette", true);
                capa.setPlatform(Platform.ANY);

                capa.setJavascriptEnabled(true);
                capa.setCapability(FirefoxOptions
                        .FIREFOX_OPTIONS, options);
                capa.setCapability(CapabilityType
                        .LOGGING_PREFS, getLoggingPreferences());
                capa.setCapability(CapabilityType
                        .ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                capa.setCapability(CapabilityType
                        .ForSeleniumServer.AVOIDING_PROXY, true);
                break;

            }
            case BrowserType.SAFARI: {
                capa = DesiredCapabilities.safari();

                break;

            }
            case BrowserType.IPHONE: {
                capa = DesiredCapabilities.iphone();
                capa.setCapability("deviceName", "iPhone XR");
                capa.setCapability("automationName", "XCUITest");
                capa.setCapability("browserName", "safari");
                capa.setCapability("platformName", "iOS");
                capa.setCapability("platformVersion", "12.1");

                break;

            }
            case BrowserType.ANDROID: {
                capa = DesiredCapabilities.android();

                capa.setCapability("udid", ParameterMap.getParamAndroidUDID());
                capa.setCapability("deviceName", "Android");
                capa.setCapability("device", ParameterMap.getAndroidDeviceName());

                //mandatory capabilities
                capa.setCapability("browserName", "chrome");
                capa.setCapability("forceMjsonwp", "true");

                break;

            }
            default: {
                throw new Error("Unable to find capability for browser type - " + type);
            }

        }

        if (ParameterMap.getParamBrowsermobProxy()) {
            capa.setCapability(CapabilityType.PROXY, NetworkAnalyticsHelper.initializeProxy());
        }

        return capa;
    }

}
