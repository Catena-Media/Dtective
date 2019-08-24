package com.dtective.framework.selenium.Extensions;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.Logs;

import java.util.Set;

/**
 * Encapsulation of the Manage object
 */
public class QAManage implements WebDriver.Options {

    /**
     * Internal variable to store the wrapped webdriver options
     */
    private WebDriver.Options options;

    /**
     * Default constructor
     *
     * @param options Options to be wrapped
     */
    public QAManage(WebDriver.Options options) {
        this.options = options;
    }

    /**
     * Adds the target cookie to the current browser session
     *
     * @param cookie Cookie to be added
     */
    @Override
    public void addCookie(Cookie cookie) {
        options.addCookie(cookie);
    }

    /**
     * Deletes the target cookie
     *
     * @param s Cookie to be deleted
     */
    @Override
    public void deleteCookieNamed(String s) {
        options.deleteCookieNamed(s);
    }

    /**
     * Deletes the target cookie
     *
     * @param cookie Cookie to be deleted
     */
    @Override
    public void deleteCookie(Cookie cookie) {
        options.deleteCookie(cookie);
    }

    /**
     * Deletes all cookies
     */
    @Override
    public void deleteAllCookies() {
        options.deleteAllCookies();
    }

    /**
     * Retrieves all cookies
     *
     * @return Collection of cookies from the browser
     */
    @Override
    public Set<Cookie> getCookies() {
        return options.getCookies();
    }

    /**
     * Retrieves the target cookie by name
     *
     * @param s Name of the cookie
     * @return Cookie from the browser
     */
    @Override
    public Cookie getCookieNamed(String s) {
        return options.getCookieNamed(s);
    }

    /**
     * Retrieves the webdriver timeouts
     *
     * @return Webdriver.Timeouts
     */
    @Override
    public WebDriver.Timeouts timeouts() {
        return options.timeouts();
    }

    /**
     * Retrieves the IME handler
     *
     * @return Webdriver.ImeHandler
     */
    @Override
    public WebDriver.ImeHandler ime() {
        return options.ime();
    }

    /**
     * Retrieves the window handler
     *
     * @return webdriver windows
     */
    @Override
    public WebDriver.Window window() {
        return options.window();
    }

    /**
     * Retrieves webdriver logs
     *
     * @return webdriver logs
     */
    @Override
    public Logs logs() {
        return options.logs();
    }
}
