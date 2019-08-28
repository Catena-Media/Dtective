package io.dtective.test;

import io.dtective.configuration.ParameterMap;
import io.dtective.quality.framework.http.HttpStepsCore;
import io.dtective.web.HttpManager;
import org.junit.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static io.dtective.quality.framework.http.HttpStepsCore.iAddHTTPHeaderValue;

/**
 * Class handling the creation of an Http Manager when needed.
 */
public final class ApiCore {

    static {
        getHttpManager();
    }

    /**
     * Default Constructor.
     */
    private ApiCore() {
    }

    /**
     * Method creates a new HttpManager unless already existing within data-store.
     *
     * @return HttpManager
     */
    public static HttpManager getHttpManager() {

        HttpManager manager;

        manager = ((HttpManager) TestDataCore.getDataStore("manager"));

        if (manager == null) {
            HttpManager newmanager = null;

            try {
                newmanager = (HttpManager) Class.forName(ParameterMap.getHTTPManagerClassName()).getDeclaredConstructor().newInstance();
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }

            if (newmanager == null) {
                throw new Error("Unable to create new instance of the http manager. Class used : "
                        + ParameterMap.getHTTPManagerClassName());
            }

            if (TestDataCore.getDataStore("headers") == null) {
                TestDataCore.addToDataStore("headers", new HashMap<String, Object>());
            }

            HttpStepsCore coresteps = new HttpStepsCore();

            if (!ParameterMap.getParamDefaultAuth().equals("")) {
                switch (ParameterMap.getParamDefaultAuth()) {
                    case ("bearer token"):
                        iAddHTTPHeaderValue("Authorization", ParameterMap.getParamAuthToken());
                        break;
                    case ("basic auth"):
                        coresteps.iSetBasicAuth(ParameterMap.getParamBasicAuthUsername(), ParameterMap.getParamBasicAuthPassword());
                        break;
                    default:
                        Assert.fail("Incorrect Authentication mode defined in environment property DefaultAuth");
                        break;
                }
            }

            TestDataCore.addToDataStore("manager", newmanager);

            return getHttpManager();
        }

        return manager;
    }
}
