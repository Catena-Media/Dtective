package io.dtective.selenium.Extensions;


import io.dtective.configuration.ParameterMap;
import io.dtective.test.SeleniumCore;
import io.dtective.user.QAUserProfile;
import io.dtective.test.TestStepsCore;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmentable;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Wrapper for the selenium webdriver class.
 */
@Augmentable
public class QAWebDriver implements WebDriver, JavascriptExecutor, TakesScreenshot {

    /**
     * Class logger.
     */
    private static Logger logger = LogManager.getLogger(QAWebDriver.class);

    /**
     * Internal variable to store the wrapped webdriver
     */
    private WebDriver driver;

    /**
     * Currently active url for the webdriver. Necessary to detect navigation changes
     */
    private String activeUrl = null;

    /**
     * Default class constructor
     *
     * @param driver Webdriver to be wrapped
     */
    public QAWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Gets the targetted url
     *
     * @param s target url
     */
    @Override
    public void get(String s) {
        driver.get(s);
    }

    /**
     * Gets the current url of the browser
     *
     * @return Browser url
     */
    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Gets the title of the browser
     *
     * @return Browser title
     */
    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Locates a collection of elements based on the expression
     * and wraps them in the QAWebElement class
     *
     * @param by Selector expression
     * @return List of QAWebElements matching the expression on the page
     */
    @Override
    public List<WebElement> findElements(By by) {
        List<WebElement> elements = driver.findElements(by);
        List<WebElement> results = new ArrayList<>();

        elements.forEach(webElement -> {
            results.add(new QAWebElement(webElement, this));
        });

        return results;
    }

    /**
     * Locates the target element based on the expression
     *
     * @param by Selector expression
     * @return QAWebelement matching the expression
     */
    @Override
    public QAWebElement findElement(By by) {

        QAWebElement result = null;
        try {
            result = new QAWebElement(driver.findElement(by), this);
        } catch (NoSuchElementException ex) {
            logger.trace(ex);
            try {
                TestStepsCore.getScenario().write(ex.getMessage());
            } catch (NullPointerException nullEx) {
                logger.trace(nullEx);
            }

            return null;
        }
        return result;
    }

    /**
     * Returns the source code of the current website
     *
     * @return Source code of the current website
     */
    @Override
    public String getPageSource() {

        return driver.getPageSource();
    }

    /**
     * Closes the webdriver session
     */
    @Override
    public void close() {
        logger.debug("Closing WebDriver");

        if (Boolean.parseBoolean(ParameterMap.getParamIsRemoteInstance())) {
            quit();
        } else
            driver.close();
    }

    /**
     * Quits the webdriver session (remote usage)
     */
    @Override
    public void quit() {
        logger.debug("Quitting Driver");
        driver.quit();
    }

    /**
     * Gets the webdriver windowhandles
     *
     * @return Webdriver Windowhandles
     */
    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    /**
     * Gets the webdriver windowhandle
     *
     * @return Webdriver Windowhandle
     */
    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * @return TargetLocator
     */
    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    /**
     * @return Webdriver's navigate object
     */
    @Override
    public Navigation navigate() {
        return new QANavigate(driver.navigate());
    }

    /**
     * @return Webdriver's manage object
     */
    @Override
    public Options manage() {
        return new QAManage(driver.manage());
    }

    /**
     * @return Original non-wrapped webdriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Update the wrapped driver
     *
     * @param driver native webdriver class
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Execute javascript - script should start with RETURN
     *
     * @param script the script to be executed
     * @param args   optional arguments
     * @return Object containing value from executed script
     */
    @Override
    public Object executeScript(String script, Object... args) {
        script = script.trim();
        if (!script.toLowerCase().startsWith("return"))
            script = "return " + script;

        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    /**
     * Execute async javascript - script should start with RETURN
     *
     * @param script the script to be executed
     * @param args   optional arguments
     * @return Object containing value from executed async script
     */
    @Override
    public Object executeAsyncScript(String script, Object... args) {
        script = script.trim();
        if (!script.toLowerCase().startsWith("return"))
            script = "return " + script;

        return ((JavascriptExecutor) driver).executeAsyncScript(script, args);
    }

    /**
     * Method to register page load times and attach them to the test report
     */
    public void pageLoadTimeHook() {
        if (activeUrl == null || !activeUrl.equals(SeleniumCore.getWebDriver().getCurrentUrl())) {
            java.util.Date time = new java.util.Date();
            time.setTime(getPageLoadTime());
            SimpleDateFormat formatter = new SimpleDateFormat("mm:ss.SSS");

            String loadtime = formatter.format(time);

            String logEntry = "Page load time was : " + loadtime + " ms on - " + getCurrentUrl();

            Allure.getLifecycle().addAttachment("PageLoadLog",
                    "text/plain", ".txt", logEntry.getBytes());


            activeUrl = getCurrentUrl();
        }
    }


    /**
     * Method to evaluate the websites's loading speed
     *
     * @return amount of time passed between navigation start to load event end
     */
    public Long getPageLoadTime() {
        Long loadtime = (Long) executeScript(
                "return performance.timing.loadEventEnd - performance.timing.navigationStart;");
        logger.debug(String.format("Page load time = %d on %s", loadtime, getCurrentUrl()));

        return loadtime;
    }

    /**
     * Method to await the website before interacting with it. Respects javascript based technologies.
     */
    public void waitForPage() {
        new WebDriverWait(this, ParameterMap.getParamWebDriverTimeout()).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals(
                        "complete"));

        try {
            Thread.sleep(ParameterMap.getParamWebDriverDelayMS());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Attaches a screenshot to the test report
     */
    public void takeScreenshot() {
        QAUserProfile.getCurrent().appendScreenshotToScenario(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    /**
     * @return Webdriver Action object for complex interactions
     */
    public Actions createActions() {
        return new Actions(driver);
    }

    /**
     * @param outputType Output type of the screenshot
     * @param <X>        Class type
     * @return Screenshot
     * @throws WebDriverException In any event the screenshot cannot be taken
     */
    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return ((TakesScreenshot) driver).getScreenshotAs(outputType);
    }
}
