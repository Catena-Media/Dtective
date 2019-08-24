package com.dtective.framework.test;

import com.dtective.framework.configuration.ParameterMap;
import com.dtective.framework.selenium.DriverBuilder;
import com.dtective.framework.selenium.Extensions.QAWebDriver;
import com.dtective.framework.selenium.Extensions.SharedWebDriver;

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
}
