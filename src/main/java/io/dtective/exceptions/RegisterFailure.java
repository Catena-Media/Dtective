package io.dtective.exceptions;

import io.dtective.test.TestDataCore;
import io.dtective.test.TestStepsCore;

/**
 * Class to handle the event of test failures
 *
 * @since 1.0
 */
public class RegisterFailure {

    public static void call() {
        if (TestStepsCore.getProfile() != null
                && TestDataCore.existsInConfigStore("driver")) {

            TestStepsCore.getProfile().appendScreenshotToScenario(new Object() {
                }.getClass().getEnclosingMethod().getName());
        }
    }
}
