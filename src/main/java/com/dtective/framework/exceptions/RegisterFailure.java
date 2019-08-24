package com.dtective.framework.exceptions;

import com.dtective.framework.test.TestDataCore;
import com.dtective.framework.test.TestStepsCore;

/**
 * Class to handle the event of test failures
 *
 * @since 1.0
 */
public class RegisterFailure {

    public static void call() {

        if (TestStepsCore.getProfile() != null
                && TestDataCore.existsInDataStore("driver")) {

            TestStepsCore.getProfile().appendScreenshotToScenario(new Object() {
            }.getClass().getEnclosingMethod().getName());

        }
    }
}
