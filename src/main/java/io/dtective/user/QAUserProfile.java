package io.dtective.user;

import io.dtective.configuration.ParameterMap;
import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.selenium.Extensions.QAWebDriver;
import io.dtective.selenium.Extensions.QAWebElement;
import io.dtective.selenium.Extensions.SharedWebDriver;
import io.dtective.test.SeleniumCore;
import io.dtective.test.TestDataCore;
import io.dtective.test.TestStepsCore;
import io.dtective.utils.ClipboardHelper;
import io.dtective.utils.MemoryHelper;
import io.dtective.utils.WebElementHelper;
import io.dtective.xpath.XpathHelper;
import cucumber.api.Scenario;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static io.dtective.test.TestStepsCore.getScenario;


/**
 * User Profile class for screen-play testing
 */
public class QAUserProfile {

    private Logger logger = LogManager.getLogger(QAUserProfile.class);
    private String name;


    /**
     * User Profile class, to be used during screen play interaction of the application
     *
     * @param name - name of the test user, should be unique
     */
    public QAUserProfile(String name) {
        this.name = name;
        logger.trace("Created new User Profile : " + name);
    }

    /**
     * Retrieves the current user profile from the memory
     *
     * @return current user profile
     */
    public static QAUserProfile getCurrent() {
        return TestStepsCore.getProfile();
    }

    /**
     * Uses the currently active rpg-driver to navigate to the target URL.
     *
     * @param url - Target
     */
    public void navigateTo(String url) {
        getWebDriver().navigate().to(url);
        getWebDriver().takeScreenshot();
    }

    /**
     * Retrieves the current webdriver
     *
     * @return current webdriver
     */
    private QAWebDriver getWebDriver() {
        return SeleniumCore.getWebDriver();
    }

    /**
     * Uses the currently active driver to click.
     *
     * @param by - Target selector
     */
    public void hoverOver(By by) {
        logger.debug(String.format("%s is hovering over : %s", name, by));

        QAWebElement element = (QAWebElement) WebElementHelper.findElement(by);

        element.hoverOver();
    }

    /**
     * Uses the currently active driver to drag-and-drop.
     */
    public void dragAndDrop(By source, By target) {
        logger.debug(String.format("%s is dragging : %s into %s", name, source, target));

        QAWebElement sourceElement = (QAWebElement) WebElementHelper.findElement(source);

        QAWebElement targetElement = getWebDriver().findElement(target);

        sourceElement.dragAndDropInto(targetElement);
    }

    /**
     * Shortcut to taking a screenshot
     */
    private void appendScreenshot() {
        SeleniumCore.getWebDriver().takeScreenshot();
    }

    /**
     * Uses the currently active rpg-driver to click.
     *
     * @param by - Target
     */
    public void click(By by) {
        logger.debug(String.format("%s is clicking : %s", name, by));
        WebElement element = WebElementHelper.findElement(by);
        element.click();
    }

    /**
     * Uses the currently active rpg-driver to click.
     *
     * @param by - Target
     */
    public void doubleClick(By by) {
        logger.debug(String.format("%s is double-clicking : %s", name, by));
        QAWebElement element = (QAWebElement) WebElementHelper.findElement(by);
        element.doubleClick();

    }

    /**
     * Uses the currently active driver to context-click.
     *
     * @param by - Target
     */
    public void contextClick(By by) {
        logger.debug(String.format("%s is context-clicking : %s", name, by));
        QAWebElement element = (QAWebElement) WebElementHelper.findElement(by);

        element.contextClick();

    }


    /**
     * Uses the currently active driver to check if an elemnt displays
     *
     * @param by - Target.
     */
    public void isDisplayed(By by) {
        logger.debug(String.format("%s is checking for displayed : %s", name, by));

        WebElement element = null;


        try {
            element = WebElementHelper.findElement(by);
        } catch (NotFoundException ex) {
            Assert.fail("Element does not display " + by);
        }

        Assert.assertTrue(String.format("Element <%s> has displayed set to false", by.toString()), element.isDisplayed());
    }

    /**
     * @return the current url of the webdriver
     */
    public String getCurrentUrl() {
        return getWebDriver().getCurrentUrl();
    }


    /**
     * Uses the currently active driver to type keys.
     *
     * @param charSequences - Text or Key object
     * @param by            - Target
     */
    public void sendKeys(By by, CharSequence... charSequences) {
        WebElement element = WebElementHelper.findElement(by);
        sendKeys(element, charSequences);


    }

    /**
     * Helper method to log out correctly between keys and text
     *
     * @param element
     * @param charSequences
     */
    private void sendKeys(WebElement element, CharSequence... charSequences) {

        if (charSequences[0] instanceof Keys) {
            logger.debug(String.format("%s is sending keys : %s", name, ((Keys) charSequences[0]).name()));
        } else {
            logger.debug(String.format("%s is sending keys : %s", name, charSequences[0]));
        }

        element.sendKeys(charSequences);
    }

    public QAWebElement getCurrentFocus() {
        return new QAWebElement(getWebDriver().switchTo().activeElement(), getWebDriver());
    }

    /**
     * Uses the currently active rpg-driver to type keys.
     *
     * @param charSequences text
     */
    public void sendKeysToCurrentFocus(CharSequence... charSequences) {

        if (charSequences[0] instanceof Keys) {
            logger.debug(String.format("%s is sending keys : %s", name, ((Keys) charSequences[0]).name()));
        } else {
            logger.debug(String.format("%s is sending keys : %s", name, charSequences[0]));
        }

        WebElement element = getWebDriver().switchTo().activeElement();

        if (element == null) {
            Assert.fail("Unable to locate element : current-focus");
        }

        if (element == null) {
            logger.error("Skipping SENDKEYS on : " + getCurrentFocus());
            Assert.fail("Could not find target element");
        } else {
            element.sendKeys(charSequences);
        }


    }


    /**
     * Uses the currently active rpg-driver to get a screenshot
     *
     * @param name - Target
     * @return Screenshot temporary file
     */
    public File getScreenshot(String name) {

//        logger.debug(String.format("%s is taking screenshot : %s", name, name));

        boolean isRemote = Boolean.parseBoolean(ParameterMap.getParamIsRemoteInstance());

        if (isRemote) {

            WebDriver driver = getWebDriver().getDriver();

            if (driver instanceof SharedWebDriver) {
                driver = ((SharedWebDriver) driver).getDriver();
            }

            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.simple())
                    .takeScreenshot(driver);

            File scrFile = new File(String.format("target/site/screenshots/%s.png", name));

            try {
                scrFile.getParentFile().mkdirs();
                ImageIO.write(screenshot.getImage(), "PNG", scrFile);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return scrFile;
        } else {

            File scrFile = null;

            scrFile = ((TakesScreenshot) getWebDriver().getDriver()).getScreenshotAs(OutputType.FILE);
            name = name.replace(" ", "_");

            File target = new File(String.format("target/site/screenshots/%s.png", name));
            try {

                FileUtils.copyFile(scrFile, target);
            } catch (IOException e) {
                throw new Error("Path : " + target.getPath() + " Exception : " + e.getMessage());
            }

            return scrFile;
        }

    }

    /**
     * Checks if text is displayed within the whole DOM
     *
     * @param text String containing the text to be found
     */
    public void textDisplays(String text) {
        getWebDriver().takeScreenshot();
        By by = XpathHelper.findByText(text);

        Assert.assertNotNull("Expected text did not display : " + text, WebElementHelper.findElement(by));

    }

    private String getStringToSetScreenshotName(Scenario scenario, String stepMethodName) {
        String rawFeatureName = scenario.getId().split(";")[0];
        String featureName = rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);
        if (featureName.contains("feature:")) {
            featureName = featureName.substring(featureName.lastIndexOf("/") + 1, featureName.lastIndexOf(":"));
        }

        String screenshotName = featureName + "/" + ParameterMap.getParamBrowserType() + "_" + scenario.getName() + "_" + stepMethodName;

        return screenshotName;
    }

    public void appendScreenshotToScenario(String stepMethodName) {
        if (ParameterMap.getParamRecordScreenshots()) {
            try {
                File screenshot =
                        QAUserProfile.getCurrent().getScreenshot(getStringToSetScreenshotName(getScenario(), stepMethodName));


                byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshot);

                Allure.getLifecycle().addAttachment("Screenshot",
                        URLConnection.guessContentTypeFromName(screenshot.getName()), "png", screenshotBytes);

            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public void appendTextLogsToScenario(String title, String text) {
        Allure.getLifecycle().addAttachment(title,
                "text/plain", ".txt", text.getBytes());
    }

    public void appendWebdriverLogsToScenario(String logType) {
        Scenario scenario = getScenario();
        scenario.write(getLogsByType(logType));
    }

    public String getLogsByType(String logType) {
        QAWebDriver driver = getWebDriver();
        StringBuilder sb = new StringBuilder();

        try {
            driver.manage()
                    .logs().get(logType)
                    .getAll().forEach(logEntry -> {
                sb.append(logEntry.toString()).append("\n");
            });

            return sb.toString().isEmpty() ? "There are no captured logs" : sb.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public void dispose() {
        TestDataCore.removeFromConfigStore("profile");
    }


    protected Actions getAction() {
        WebDriver driver = getWebDriver().getDriver();

        if (driver instanceof SharedWebDriver) {
            driver = ((SharedWebDriver) driver).getDriver();
        }

        return new Actions(driver);
    }

    public void switchToTab() {
        if (ParameterMap.getParamBrowserType().matches("firefox")) {
            try {
                final int sleepTime = 1500;
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String originalTab = getWebDriver().getWindowHandle();
        ArrayList<String> tabHandles = new ArrayList<>(getWebDriver().getWindowHandles());

        for (String handle : tabHandles) {
            if (!handle.equals(originalTab)) {
                getWebDriver().switchTo().window(handle);
                break;
            }
        }
    }

    public void openTab(By by) {

        WebElement link = WebElementHelper.findElement(by);

        if (link == null) {
            Assert.fail("Unable to locate element : " + by.toString());
        }

        String storedUrl = link.getAttribute("href");

        getWebDriver().executeScript("window.open(\'" + storedUrl + "\');");

    }

    public void closeTab() {
        String currentPageHandle = getWebDriver().getWindowHandle();
        ArrayList<String> tabHandles = new ArrayList<String>(getWebDriver().getWindowHandles());

        for (String handle : tabHandles) {
            if (!handle.equals(currentPageHandle)) {

                try {
                    getWebDriver().close();
                } catch (UnsupportedOperationException ex) {
                }

                getWebDriver().switchTo().window(handle);
                break;
            }
        }
    }

    public void clearField(By by) {
        WebElementHelper.findElement(by).clear();
    }

    public void browserRefresh() {
        getWebDriver().navigate().refresh();
    }

    public void clipboardAdd(String text) {
        ClipboardHelper.add(text);
    }

    public void copyTextToMemory(By by, String id) {
        WebElement element = WebElementHelper.findElement(by);

        MemoryHelper.add(id, element.getText());
    }

    public void copyAttributeToMemory(By by, String attribute, String id) {
        String value = WebElementHelper.getValueOf(by, attribute);
        MemoryHelper.add(id, value);
    }

    public void pasteTextFromMemory(By by, String id) {
        String myData = String.valueOf(MemoryHelper.get(id));

        WebElement element = WebElementHelper.findElement(by);

        element.sendKeys(myData);
    }

    public void pasteFromClipboard(By by) {
        WebElement element = WebElementHelper.locateElement(by);
        String result = ClipboardHelper.get();

        element.sendKeys(result);
    }

    public void copyAttributeToClipboard(String attribute, By by) {

        clipboardAdd(WebElementHelper.getValueOf(by, attribute));
    }

    public void copyElementTextToClipboard(By by) {
        WebElement element = WebElementHelper.locateElement(by);

        clipboardAdd(element.getText());
    }

    public void dropdownSelect(By by, String value) {

        Select select = new Select(WebElementHelper.findElement(by));

        select.selectByVisibleText(value);
    }

    public void elementDoesNotDisplay(By by) {


        try {
            WebElement element = WebElementHelper.findElement(by);

            Assert.assertNull("Error - element was found, when expected to not display + " + by.toString(), element);
        } catch (NotFoundException ignored) {
        }
    }

    public void assertAttributeValueContains(By by, String attribute, String value) {

        WebElement target = WebElementHelper.findElement(by);

        if (target.getAttribute(attribute) == null || target.getAttribute(attribute).isEmpty()) {
            throw new AssertionError(
                    String.format("Target element %s did not contain attribute %s", by, attribute)
            );
        }

        Assert.assertTrue(String.format(
                "#Target/Actual-Value : <%s> / <%s>, #Attribute : %s, #Selector : %s",
                value, target.getAttribute(attribute), attribute, by
                ),
                target.getAttribute(attribute).contains(value));
    }

    public void assertAttributeValueEquals(By by, String attribute, String value) {
        WebElement element = WebElementHelper.findElement(by);

        Assert.assertNotNull(String.format("Attribute <%s> does not exist on %s", attribute, by.toString()),
                element.getAttribute(attribute));
        Assert.assertEquals(String.format("Attribute <%s> value <%s> does not equal to expected value <%s> on element <%s>",
                attribute, element.getAttribute(attribute), value, by.toString()), value, element.getAttribute(attribute));
    }

    public void compareFieldValue(By by, String value) {
        WebElement element = WebElementHelper.findElement(by);
        Assert.assertEquals(value, element.getAttribute("value"));
    }

    public void urlContains(String text) {

        appendScreenshot();
        text = BDDPlaceholders.replace(text);
        if (!getWebDriver().getCurrentUrl().contains(text)) {
            Assert.fail("Url did not contain the following - " + text + "\n"
                    + "Actual url was: " + getWebDriver().getCurrentUrl());
        }
    }

    public void compareCSSValue(By by, String attribute, String value) {
        appendScreenshot();
        WebElementHelper.compareCSSBy(by, attribute, value);

    }

    public void scrollTo(By by) {

        QAWebElement element = (QAWebElement) WebElementHelper.findElement(by);
        element.hoverOver();
    }

    public void isClickable(By by, int seconds) {
        WebElement element = WebElementHelper.findElement(by);
        try {
            getWebDriver().waitForPage();
            new WebDriverWait(
                    getWebDriver(), seconds).until(
                    ExpectedConditions.elementToBeClickable(element)
            );
        } catch (TimeoutException ex) {
            Assert.fail("element: " + element + " is not clickable");
        }
        getWebDriver().takeScreenshot();
    }

    public void isVisible(By by, int seconds) {
        WebElement element = WebElementHelper.findElement(by);
        try {
            getWebDriver().waitForPage();
            new WebDriverWait(getWebDriver(), seconds).until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ex) {
            Assert.fail("element: " + element + " is not visible");
        }
        getWebDriver().takeScreenshot();
    }

    public void isSelected(By by, int seconds) {
        WebElement element = WebElementHelper.findElement(by);
        try {
            getWebDriver().waitForPage();
            new WebDriverWait(getWebDriver(), seconds).until(
                    ExpectedConditions.elementToBeSelected(element));
        } catch (TimeoutException ex) {

            if (element.getAttribute("value").equals("true")) {

            } else {
                Assert.fail("element: " + element + " is not selectable");
            }

        }
        getWebDriver().takeScreenshot();
    }

    public void alertPresent(int seconds) {
        try {
            getWebDriver().waitForPage();
            new WebDriverWait(getWebDriver(), seconds).until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException ex) {
            Assert.fail("Alert is not present");
        }
        getWebDriver().takeScreenshot();
    }

    public void numberOfElements(By by, int number, int seconds) {
        try {
            getWebDriver().waitForPage();
            new WebDriverWait(getWebDriver(), seconds).until(ExpectedConditions.numberOfElementsToBe(by,
                    number));
        } catch (TimeoutException ex) {
            Assert.fail("Number of elements is incorrect");
        }
        getWebDriver().takeScreenshot();
    }

    public void textMatches(By by, Pattern pattern, int seconds) {
        WebElement element = WebElementHelper.findElement(by);
        try {
            getWebDriver().waitForPage();
            new WebDriverWait(getWebDriver(), seconds).until(
                    ExpectedConditions.textMatches(by, pattern));
        } catch (TimeoutException ex) {
            Assert.fail(String.format("Text [%s] for element [%s] does not match. Expected pattern [%s]",
                    element.getText(), by.toString(), pattern.toString()));
        }
        getWebDriver().takeScreenshot();
    }

    public void isExisting(By by, int seconds) {
        try {
            getWebDriver().waitForPage();
            new WebDriverWait(getWebDriver(), seconds).until(
                    ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException ex) {
            Assert.fail("Element does not exist");
        }
        getWebDriver().takeScreenshot();
    }
}
