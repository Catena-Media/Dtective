package io.dtective.xpath;

import io.dtective.test.TestDataCore;
import org.openqa.selenium.By;

import java.util.HashMap;

public class XpathHelper {

    public static By findByID(String id) {

        return By.xpath("//*[@id='" + id + "']");
    }

    public static By findByClass(String classValue) {

        return By.xpath("//*[@class='" + classValue + "']");

    }

    public static By findByPropAndValue(String designField) {

        HashMap data = (HashMap) TestDataCore.getDataStore(designField);

        String property = (String) data.keySet().toArray()[0];
        String value = (String) data.get(property);

        return By.xpath("//*[@" + property + "='" + value + "']");

    }

    public static By findByXpathValueFromDatastore(String designField) {

        HashMap data = (HashMap) TestDataCore.getDataStore(designField);

        String property = (String) data.keySet().toArray()[0];
        String value = (String) data.get(property);

        return By.xpath(value);

    }

    public static By findByXpathValue(String designField) {

        return By.xpath((String) TestDataCore.getDataStore(designField));

    }

    public static By findByPropAndValue(String property, String value) {

        return By.xpath("//*[@" + property + "='" + value + "']");

    }

    public static By findByText(String text) {

        return By.xpath(String.format("//*[contains(text(),\"%s\")]", text));

    }

    public static By findByText(String text, String xpathPrefix) {

        return By.xpath(String.format(xpathPrefix + "[contains(text(),\"%s\")]", text));

    }

    public static By inputSubmit() {

        return By.xpath("//input[@type='submit']");

    }

    public static By anySubmit() {

        return By.xpath("//*[@type='submit']");

    }

    public static By submitByProperyAndValue(String attribute, String value) {

        return By.xpath(String.format("//*[@type='submit' and @%s='%s']", attribute, value));

    }

    public static By getText(String text) {

        return By.xpath(String.format("//*[normalize-space()='%s']", text));

    }

    public static By findByPropertyValueContains(String property, String text) {

        return By.xpath(String.format("//*[contains(@%s,'%s')]", property, text));

    }

    public static By findByPropertyValueContainsWithinXpath(String xpath, String property, String text) {

        return By.xpath(String.format(xpath + "//*[contains(@%s,'%s')]", property, text));

    }

    public static By findByPropertyValueContains(String property, String text, String text2) {

        return By.xpath(String.format("//*[contains(@%s,'%s') and contains(@%s,'%s')]", property, text, property, text2));

    }
}
