package io.dtective.selenium.Extensions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.Augmentable;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.HttpClient;

import java.net.URL;

/**
 * Wrapper for the android webdriver class.
 */
@Augmentable
public class QAAndroidDriver extends AndroidDriver {
    public QAAndroidDriver(HttpCommandExecutor executor, Capabilities capabilities) {
        super(executor, capabilities);
    }

    public QAAndroidDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
    }

    public QAAndroidDriver(URL remoteAddress, HttpClient.Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(remoteAddress, httpClientFactory, desiredCapabilities);
    }

    public QAAndroidDriver(AppiumDriverLocalService service, Capabilities desiredCapabilities) {
        super(service, desiredCapabilities);
    }

    public QAAndroidDriver(AppiumDriverLocalService service, HttpClient.Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(service, httpClientFactory, desiredCapabilities);
    }

    public QAAndroidDriver(AppiumServiceBuilder builder, Capabilities desiredCapabilities) {
        super(builder, desiredCapabilities);
    }

    public QAAndroidDriver(AppiumServiceBuilder builder, HttpClient.Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(builder, httpClientFactory, desiredCapabilities);
    }

    public QAAndroidDriver(HttpClient.Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(httpClientFactory, desiredCapabilities);
    }

    public QAAndroidDriver(Capabilities desiredCapabilities) {
        super(desiredCapabilities);
    }
}
