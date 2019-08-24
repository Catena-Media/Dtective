package com.dtective.framework.environment;

import com.dtective.framework.configuration.ParameterMap;
import com.dtective.framework.test.TestDataCore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.*;

/**
 * Class to handle the loading of the test environment definition json file.
 *
 * @since 1.1
 */
public class TestEnvironmentManager {

    /**
     * Variable to store the loaded test environment json file.
     */
    private static final JSONObject TEST_ENVIRONMENT;

    private static final Object MUTEX = new Object();

    private static Logger logger = LogManager.getLogger(TestEnvironmentManager.class);

    /* Static constructor to initiate the parsing of the test environment definition file. */
    static {

        synchronized (MUTEX) {

            File testEnv = new File("./build/environment/testEnvironment.json");
            if (!testEnv.exists()) {
                testEnv = new File("testEnvironment.json");
            }

            TEST_ENVIRONMENT = new JSONObject(readFile(testEnv.getAbsolutePath()));

            String selectedEnv = ParameterMap.getParamTestEnvironment();

            if (TEST_ENVIRONMENT.getJSONObject(selectedEnv) == null) {
                throw new Error(
                        String.format("Test environment %s not found. Please add it to the testEnvironment.json "
                        + "file. | %s", selectedEnv, TEST_ENVIRONMENT.toString()));
            }

            JSONObject envData = TEST_ENVIRONMENT.getJSONObject(selectedEnv);

            for (String key
                    : envData.keySet()) {
                logger.trace(String.format(Thread.currentThread().getName() + " - Adding Test Environment Data : %s |", key));
                TestDataCore.addToGlobalStore(key, envData.get(key));
            }
        }

    }

    public static String readFile(String path) {

        InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Error("Unable to find file at :"
                    + path);
        }
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            line = buf.readLine();

            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Unable to read :" + path);
        }

        return sb.toString();

    }

    /**
     * Getter for the test environment definition json file
     *
     * @return Test environment definition JSONObject
     */
    public static JSONObject getTestEnvironment() {
        return TEST_ENVIRONMENT;
    }
}
