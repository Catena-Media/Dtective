package io.dtective.selenium.Extensions;

import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.test.SeleniumCore;
import org.openqa.selenium.WebDriver;

import java.net.URL;

/**
 * Class to wrap the selenium Webdriver.Navigate class.
 */
public class QANavigate implements WebDriver.Navigation {

    /**
     * Internal variable to store the wrapped navigation
     */
    private WebDriver.Navigation navigation = null;

    /**
     * Default constructor
     *
     * @param navigation Navigation to be wrapped
     */
    public QANavigate(WebDriver.Navigation navigation) {
        this.navigation = navigation;
    }

    /**
     * Back browser navigation.
     */
    @Override
    public void back() {

        navigation.back();
    }

    /**
     * Forward browser navigation.
     */
    @Override
    public void forward() {
        navigation.forward();
        SeleniumCore.getWebDriver().waitForPage();
        SeleniumCore.getWebDriver().pageLoadTimeHook();
    }

    /**
     * Navigation to a specific url
     *
     * @param s Target text based URL
     */
    @Override
    public void to(String s) {
        s = BDDPlaceholders.replace(s);
        try {
            navigation.to(s);
        } catch (Exception ex) {
            throw new Error(String.format("Invalid navigation attempt to : %s. Exception - " + ex.getMessage(), s));
        }
        SeleniumCore.getWebDriver().waitForPage();
        SeleniumCore.getWebDriver().pageLoadTimeHook();
    }

    /**
     * Navigation to a specific url
     *
     * @param url Java URL class based URL
     */
    @Override
    public void to(URL url) {
        navigation.to(url);
        SeleniumCore.getWebDriver().waitForPage();
        SeleniumCore.getWebDriver().pageLoadTimeHook();
    }

    /**
     * Refresh the browser
     */
    @Override
    public void refresh() {
        navigation.refresh();
        SeleniumCore.getWebDriver().waitForPage();
    }
}
