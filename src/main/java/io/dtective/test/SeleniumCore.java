package io.dtective.test;

import io.dtective.configuration.ParameterMap;
import io.dtective.selenium.DriverBuilder;
import io.dtective.selenium.Extensions.QAWebDriver;
import io.dtective.selenium.Extensions.SharedWebDriver;

public class SeleniumCore {

    public static QAWebDriver getWebDriver() {

        QAWebDriver driver;

        if (ParameterMap.getParamIsSingleInstance()) {
            driver = SharedWebDriver.getInstance();
        } else {
            driver = ((QAWebDriver) TestDataCore.getConfigStore("driver"));
        }

        if (driver == null) {
            DriverBuilder driverBuilder = new DriverBuilder();
            if (ParameterMap.getParamIsSingleInstance()) {
                driverBuilder.getWebDriver();
                return SharedWebDriver.getInstance();
            } else {
                TestDataCore.addToConfigStore("driver", driverBuilder.getWebDriver());
                return getWebDriver();
            }
        }

        return driver;
    }

    public static void dispose() {

        if (!ParameterMap.getParamIsSingleInstance()) {
            try {
                if (TestDataCore.existsInConfigStore("driver"))
                    getWebDriver().close();
                TestDataCore.removeFromConfigStore("driver");
            } catch (UnsupportedOperationException ex) {
                // Single Instance Protection
            }
        }

    }
}
