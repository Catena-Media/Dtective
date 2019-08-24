package com.dtective.framework.utils;

import com.dtective.framework.selenium.Extensions.QAWebElement;
import com.dtective.framework.test.SeleniumCore;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;


public class WebElementHelper {

    public static WebElement findElement(By by) {
        return locateElement(by);
    }

    public static String getValueOf(By by, String attribute) {
        return locateElement(by).getAttribute(attribute);
    }

    public static void checkNotNull(WebElement element, By by) {
        if (element == null || ((QAWebElement) element).getElement() == null) {
            throw new NotFoundException("Unable to locate element : " + by);
        }
    }

    public static WebElement locateElement(By by) {

        WebElement result = SeleniumCore.getWebDriver().findElement(by);

        checkNotNull(result, by);

        return result;
    }

    public static void compareCSSBy(By by, String attribute, String value) {
        final int rgbSize = 3;
        final int hexSize = 6;


        WebElement element = WebElementHelper.findElement(by);


        if (attribute.contains("size") && !value.contains("px")) {
            value = value + "px";
        }

        if (attribute.contains("color")) {

            String actualValue = element.getCssValue(attribute);

            if (value.length() == rgbSize || value.length() == hexSize) {
                String hex = Color.fromString(actualValue).asHex();

                hex = hex.replace("#", "");

                if (value.length() == rgbSize) {
                    String[] charArray = hex.split("");
                    String[] shortHand = new String[rgbSize];

                    for (int i = 0; i < shortHand.length; i++) {
                        shortHand[i] = charArray[i * 2];
                        hex = String.join("", shortHand);
                    }
                }

                actualValue = hex;

            } else {
                if (!actualValue.contains("rgba") && !actualValue.contains("px")) {
                    actualValue = Color.fromString(actualValue).asRgba();
                }

                if (!value.contains("rgba") && !value.contains("px")) {
                    value = Color.fromString(value).asRgba();
                }
            }

            Assert.assertEquals(String.format(
                    "CSS Attribute <%s> value <%s> was not equal to expected value <%s> on element <%s>",
                    attribute, actualValue, value, by.toString()), value, actualValue);
        } else {
            Assert.assertEquals(String.format(
                    "CSS Attribute <%s> value <%s> was not equal to expected value <%s> on element <%s>",
                    attribute, element.getCssValue(attribute), value, by.toString()), value, element.getCssValue(attribute));
        }
    }
}

