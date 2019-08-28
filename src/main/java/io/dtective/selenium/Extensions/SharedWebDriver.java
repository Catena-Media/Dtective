package io.dtective.selenium.Extensions;

import io.dtective.configuration.ParameterMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.ConcurrentHashMap;

public class SharedWebDriver extends QAWebDriver implements WebDriver, JavascriptExecutor {

    /**
     * Returns the registry of currently active driver instances
     * @return  ConcurrentHashMap of the drivers
     */
    public static ConcurrentHashMap getConcurrentMap() {
        return concurrentMap;
    }

    /**
     * Static browser session to be shared between tests
     */

    private static ConcurrentHashMap concurrentMap = new ConcurrentHashMap();



    /**
     * Thread to be used on JVM shutdown to close the browser session.
     */
    private static final Thread CLOSE_THREAD = new Thread(()
            -> {
        for (Object thread : concurrentMap.keySet()) {
            ((QAWebDriver) concurrentMap.get(thread)).close();
        }
    });

    /**
     * Static constructor for attaching shutdown hook
     */
    static {
        if (ParameterMap.getParamIsSingleInstance()) {
            Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        }
    }

    public SharedWebDriver(WebDriver driver) {
        super(driver);
    }

    public static QAWebDriver getInstance() {
        if (concurrentMap.containsKey(Thread.currentThread().getName())) {
            return (QAWebDriver) concurrentMap.get(Thread.currentThread().getName());
        } else {
            return null;
        }
    }

    public static void setInstance(QAWebDriver webDriver) {
        if (webDriver == null){
            concurrentMap.remove(Thread.currentThread().getName());
        }else {
            concurrentMap.put(Thread.currentThread().getName(), webDriver);
        }
    }

    @Override
    public void close() {

        if (getDriver().getWindowHandles().size() > 1) {
            getDriver().close();
            getDriver().switchTo().window(getDriver().getWindowHandles().iterator().next());
        }


        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        } else {
            getDriver().close();

            if (Boolean.parseBoolean(ParameterMap.getParamIsRemoteInstance())) {
                quit();
            }
        }
    }


}
