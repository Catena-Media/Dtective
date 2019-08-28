package io.dtective.selenium;

import io.dtective.configuration.ParameterMap;
import io.dtective.test.TestDataCore;
import io.dtective.web.HttpManager;
import io.dtective.web.HttpResponseWrapper;
import com.savoirtech.logging.slf4j.json.LoggerFactory;

import java.io.IOException;

/**
 * Interactions with Selenium Hub
 */
public class SeleniumHubManager {

    private static com.savoirtech.logging.slf4j.json.logger.Logger
            logger = LoggerFactory.getLogger("JSONLogger");

    /**
     * Pings the remote com.catena.qa.framework.selenium hub or node for a quick response
     */
    public static void isHubRunning() throws IOException {
        String url;

        url = ParameterMap.getParamSeleniumHubUrl();
        url = url.replace("/wd/hub", "");

        logger.trace().message("Verifying SeleniumHub on " + url).log();

        HttpManager.sendHTTPMethod("get", url, "", null, true);

        HttpResponseWrapper response = (HttpResponseWrapper) TestDataCore.getDataStore("response");

        if (response.getStatusCode() != HttpManager.HTTP_STATUSOK) {
            logger.error().message("Selenium server health-check failure")
                    .field("Address", url)
                    .field("Response", response.getStatusCode());

            throw new Error(
                    String.format("Selenium server health-check failure on %s, the response was %s",
                            url, response.getStatusCode())
            );
        }


    }


}
