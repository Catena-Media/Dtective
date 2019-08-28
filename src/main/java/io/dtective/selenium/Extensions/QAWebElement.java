package io.dtective.selenium.Extensions;


import io.dtective.configuration.ParameterMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;

public class QAWebElement implements WebElement, Locatable {

    private static Logger logger = LogManager.getLogger(QAWebElement.class);

    private WebElement element;
    private QAWebDriver driver;

    public QAWebElement(WebElement element, QAWebDriver driver) {

        this.element = element;
        this.driver = driver;
    }

    public WebElement getElement() {
        return element;
    }

    @Override
    public void click() {

        driver.pageLoadTimeHook();
        driver.waitForPage();

        if (element.getCssValue("display").equalsIgnoreCase("none")) {
            throw new Error("The target element has <display:none> attribute set");

        } else if (!ParameterMap.getParamBrowserType().equals("android") && element.getAttribute("disabled") != null
                && (element.getAttribute("disabled").equalsIgnoreCase("true")
                || element.getAttribute("disabled").equalsIgnoreCase("disabled"))
        ) {
            throw new Error("The target element is disabled");
        }

        try {
            highlightElement(element);
            element.click();
        } catch (WebDriverException webex) {
            webex.printStackTrace();
            if (webex.getMessage().contains("Other element would receive the click:")) {
                throw new Error(webex.getMessage().substring(0,
                        webex.getMessage().indexOf("(Session info:"))
                        .replace("unknown error:", ""));
            } else {
                throw new Error(webex.getMessage());
            }
        }

        driver.takeScreenshot();
        driver.pageLoadTimeHook();
    }

    public void doubleClick() {

        driver.waitForPage();
        driver.createActions().doubleClick(this).perform();
        driver.takeScreenshot();
        driver.pageLoadTimeHook();

    }

    public void contextClick() {

        driver.waitForPage();
        highlightElement(element);
        driver.createActions().contextClick(this).perform();
        driver.takeScreenshot();
    }

    public void hoverOver() {

        driver.waitForPage();
        highlightElement(element);
        driver.createActions().moveToElement(this).perform();
        driver.takeScreenshot();
    }

    public void dragAndDropInto(WebElement target) {

        driver.waitForPage();
        highlightElement(element);
        driver.createActions().dragAndDrop(this, target).perform();
        driver.takeScreenshot();
    }


    @Override
    public void submit() {


        driver.waitForPage();
        element.submit();
        driver.pageLoadTimeHook();
        driver.takeScreenshot();

    }

    @Override
    public void sendKeys(CharSequence... charSequences) {

        driver.waitForPage();

        if (charSequences.length != 0 && charSequences[0] instanceof Keys)
            logger.debug("Sending Keys(key) - " + ((Keys) charSequences[0]).name() + "-- To Element "
                    + element.toString());
        else {
            logger.debug("Sending Keys(txt) - " + charSequences[0] + " -- To Element" + element.toString());
        }

        highlightElement(element);
        element.sendKeys(charSequences);

        driver.pageLoadTimeHook();
        driver.takeScreenshot();

    }

    @Override
    public void clear() {

        driver.waitForPage();
        highlightElement(element);
        driver.takeScreenshot();
        element.clear();
        driver.takeScreenshot();

    }

    @Override
    public String getTagName() {

        driver.waitForPage();
        return element.getTagName();
    }

    @Override
    public String getAttribute(String s) {

        driver.waitForPage();
        return element.getAttribute(s);
    }

    @Override
    public boolean isSelected() {

        driver.waitForPage();
        highlightElement(element);

        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {

        driver.takeScreenshot();
        driver.waitForPage();
        highlightElement(element);

        return element.isEnabled();
    }

    @Override
    public String getText() {

        driver.waitForPage();
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {

        driver.waitForPage();
        driver.takeScreenshot();
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {

        driver.waitForPage();
        driver.takeScreenshot();
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {

        driver.waitForPage();
        driver.takeScreenshot();
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {

        driver.waitForPage();
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {

        driver.waitForPage();
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {

        driver.waitForPage();
        return element.getRect();
    }

    @Override
    public String getCssValue(String s) {

        driver.takeScreenshot();
        driver.waitForPage();
        return element.getCssValue(s);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {

        return element.getScreenshotAs(outputType);
    }

    /**
     * Highlights element being interacted with so that in shows in screenshots
     *
     * @param element           element to be highlighted
     * @param durationInSeconds duration of highlight
     */
    public void highlightElement(WebElement element, int durationInSeconds) {

        if (ParameterMap.getParamHighlightElements()) {
            logger.debug("Highlighting - " + element.toString());

            String originalStyle = element.getAttribute("style");

            driver.executeScript(
                    "arguments[0].setAttribute(arguments[1], arguments[2])",
                    element,
                    "style",
                    "border: 4px solid red; border-style: dotted solid;");

            if (durationInSeconds > 0) {
                try {
                    final int sleepTime = 1000;
                    Thread.sleep(durationInSeconds * sleepTime);

                    driver.executeScript(
                            "arguments[0].setAttribute(arguments[1], arguments[2])",
                            element,
                            "style",
                            originalStyle);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void highlightElement(WebElement element) {

        if (ParameterMap.getParamHighlightElements()) {

            if (element instanceof QAWebElement) {
                highlightElement(((QAWebElement) element).getElement(), 1);
            } else {
                highlightElement(element, 1);
            }

        }
    }


    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
