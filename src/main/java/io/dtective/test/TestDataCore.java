package io.dtective.test;

import io.dtective.data.DataProvider;
import io.dtective.environment.TestEnvironmentManager;
import io.dtective.placeholders.BDDPlaceholders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.json.JSONObject;

import java.io.File;

/**
 * Steps implementation base.
 */
public class TestDataCore {

    /**
     * Used for thread safe initialization
     */
    private static final Object MUTEX = new Object();

    /**
     * Class logger
     */
    private static Logger logger = LogManager.getLogger(TestDataCore.class);

    /**
     * Data Provider reference variable
     */
    private static DataProvider dataProvider = null;

    /**
     * Static constructor to map the data provider
     */
    static {
        synchronized (MUTEX) {
            if (dataProvider == null) {
                ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                        "Beans.xml"
                );
                dataProvider = applicationContext.getBean("DataProvider", DataProvider.class);
            }
        }

    }

    /**
     * Add value to TestCase local data context
     *
     * @param key - Key of the value in the dictionary
     * @param o   - Object to be stored
     */
    public static void addToDataStore(String key, Object o) {
        dataProvider.getLocalDataService().put(key, o);
        logger.trace(String.format("Local storage added <%s/%s>", key, o));
    }

    /**
     * Add value to TestCase global data context
     *
     * @param key - Key of the value in the dictionary
     * @param o   - Object to be stored
     */
    public static void addToGlobalStore(String key, Object o) {
        dataProvider.getGlobalDataService().put(key, o);
        logger.trace(String.format("Global storage added <%s/%s>", key, o));
    }

    /**
     * Add value to TestCase configuration data context
     *
     * @param key - Key of the value in the dictionary
     * @param o   - Object to be stored
     */
    public static void addToConfigStore(String key, Object o) {
        dataProvider.getGlobalDataService().put(key, o);
        logger.trace(String.format("Configuration storage added <%s/%s>", key, o));
    }

    /**
     * Get value from Global TestCase data context
     *
     * @param key - Key of the value in the dictionary
     * @return - Object from dictionary
     */
    public static Object getGlobalStore(String key) {
        logger.trace(String.format("Get Global Data : %s", key));
        return dataProvider.getGlobalDataService().get(key);
    }

    /**
     * Get value from Configuration store
     *
     * @param key Key of the value in the dictionary
     * @return Object from dictionary
     */
    public static Object getConfigStore(String key) {
        logger.trace(String.format("Get Global Data : %s", key));
        return dataProvider.getConfigurationDataService().get(key);
    }

    /**
     * Get value from TestCase data context
     *
     * @param key - Key of the value in the dictionary
     * @return Object from dictionary
     */
    public static Object getDataStore(String key) {
        logger.trace(String.format("Getting Local Data : %s", key));
        Object data = dataProvider.getLocalDataService().get(key);

        if (data == null)
            logger.trace(String.format("Local Data : %s was not found", key));

        return data;
    }

    /**
     * Removes object from the TestCase data context
     *
     * @param key key to be removed
     */
    public static void removeFromDataStore(String key) {
        dataProvider.getLocalDataService().remove(key);
    }

    /**
     * Removes object from the global data context
     *
     * @param key key to be removed
     */
    public static void removeFromGlobalStore(String key) {
        dataProvider.getGlobalDataService().remove(key);
    }

    /**
     * Removes object from the configuration data context
     *
     * @param key key to be removed
     */
    public static void removeFromConfigStore(String key) {
        dataProvider.getConfigurationDataService().remove(key);
    }


    /**
     * True if the key exists in the TestCase data context
     *
     * @param key key to be checked
     * @return true if key is found
     */
    public static boolean existsInDataStore(String key) {
        logger.trace(String.format("Exists Local Data : %s", key));
        return dataProvider.getLocalDataService().containsKey(key);
    }

    /**
     * True if the key exists in the Global TestCase data context
     *
     * @param key key to be checked
     * @return true if key is found
     */
    public static boolean existsInGlobalStore(String key) {
        logger.trace(String.format("Exists Global Data : %s", key));
        return dataProvider.getGlobalDataService().containsKey(key);
    }

    /**
     * True if the key exists in the configuration data context
     *
     * @param key key to be checked
     * @return true if key is found
     */
    public static boolean existsInConfigStore(String key) {
        logger.trace(String.format("Exists Global Data : %s", key));
        return dataProvider.getGlobalDataService().containsKey(key);
    }

    /**
     * Method to return a JSON object either from a file or a data-store.
     *
     * @param storage The String containing either a data-store name or a file location
     * @return JSON Object
     */
    public static String returnStringFromFileOrDataStore(String storage) {
        String fullPath = System.getProperty("user.dir") + BDDPlaceholders.replace(storage);

        if (new File(fullPath).exists()) {
            return TestEnvironmentManager.readFile(fullPath);
        } else
            return getDataStore(storage).toString();
    }

    public static JSONObject returnJSONFromFileOrParam(String location) {
        if (location.toLowerCase().contains(".json")) {
            location = System.getProperty("user.dir") + BDDPlaceholders.replace(location);
            return new JSONObject(TestEnvironmentManager.readFile(location));
        } else {
            return new JSONObject(getDataStore(location).toString());
        }
    }

}
