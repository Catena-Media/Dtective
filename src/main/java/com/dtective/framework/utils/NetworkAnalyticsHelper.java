package com.dtective.framework.utils;

import com.dtective.framework.configuration.ParameterMap;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.net.NetworkUtils;

import java.io.File;
import java.io.IOException;

public class NetworkAnalyticsHelper {

    private static Har har;
    private static Logger logger = LogManager.getLogger(NetworkAnalyticsHelper.class);

    private static BrowserMobProxy proxy;
    private static String ipAddress = "N/A";

    public static BrowserMobProxy getProxy() {
        if (proxy == null) {
            throw new Error(
                    "BrowserMob proxy setting is turned off,"
                            + " please enable and restart the test");
        }

        return proxy;
    }

    public static void saveHar(String harFilename) {

        if (proxy == null) {
            throw new Error(
                    "BrowserMob proxy setting is turned off,"
                            + " please enable and restart the test");
        }

        har = proxy.getHar();
        String harFilePath = "./" + harFilename;
        File harFile = new File(harFilePath);
        try {
            har.writeTo(harFile);
        } catch (IOException ex) {
            logger.info(ex.toString());
            logger.error("Could not find file " + harFilePath);
        }
    }

    public static Har getHar() {
        return har;
    }

    public static Proxy initializeProxy() {

        logger.debug("Starting BrowserMob Proxy");
        proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.setHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.start();

        ipAddress = new NetworkUtils().getIp4NonLoopbackAddressOfThisMachine().getHostAddress();

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        if (seleniumProxy == null) {

            if (ParameterMap.getParamBrowsermobProxy()) {
                throw new Error(String.format(
                        "Unable to initiaze proxy. Proxy Address [%s:%d]",
                        ipAddress, proxy.getPort()
                ));
            }

        }

        seleniumProxy.setHttpProxy(ipAddress + ":" + proxy.getPort());
        logger.debug("Host IP address allocated -> " + ipAddress + ":" + proxy.getPort());

        return seleniumProxy;
    }

}
