package io.dtective.web;

import io.dtective.environment.TestEnvironmentManager;
import io.dtective.placeholders.BDDPlaceholders;
import io.dtective.quality.framework.http.HttpStepsCore;
import io.dtective.test.ApiCore;
import io.dtective.test.TestDataCore;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import javax.net.ssl.SSLHandshakeException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static io.dtective.quality.framework.http.HttpStepsCore.iAddHTTPHeaderValue;

/**
 * Facilitate HTTP interactions.
 */
public class HttpManager {

    /**
     * HTTP OK Response code.
     */
    public static final int HTTP_STATUSOK = 200;

    /**
     * HTTP CREATED Response code.
     */
    public static final int HTTP_STATUSCREATED = 201;

    /**
     * HTTP NOT FOUND Response code.
     */
    public static final int HTTP_STATUSNOTFOUND = 404;

    /**
     * HTTPS port number.
     */
    private static final int HTTPS_PORT = 443;

    /**
     * Current class logger.
     */
    private static Logger logger =
            LogManager.getLogger(HttpManager.class);

    /**
     * HTTP Client for communication.
     */
    private static CloseableHttpClient httpClient;

    /**
     * Create default http client.
     */
    public HttpManager() {
        System.setProperty("https.protocols", "TLSv1.1");

        httpClient = createHttpClient();
    }

    private static void addHeadersIfNotEmpty(HttpRequest httpMethod) {
        Map<String, Object> headers = (Map<String, Object>) TestDataCore.getDataStore("headers");

        if (headers != null) {
            (headers).forEach((k, v) -> {
                logger.debug(String.format("Adding HTTP Header [Key] %s, [Value] %s", k, v));
                httpMethod.addHeader(k, v.toString());
            });
        }
    }

    /**
     * Build HTTP Post.
     *
     * @param url  - Target Url
     * @param body - Post Body
     * @return - Created HTTP Post
     */
    public static HttpPost buildPost(String url, String body) {

        url = BDDPlaceholders.replace(url);

        logger.debug("Building HTTP Post");
        logger.debug("URL : " + url);

        HttpPost post = new HttpPost(url);
        post.setEntity(new ByteArrayEntity(body.getBytes()));

        addHeadersIfNotEmpty(post);

        return post;
    }

    /**
     * Build HTTP get.
     *
     * @param url - Target Url
     * @return - Created HTTP get
     */
    public static HttpGet buildGet(String url) {

        url = BDDPlaceholders.replace(url);

        logger.debug("Building HTTP get");
        logger.debug("URL : " + url);

        HttpGet get = new HttpGet(url);

        addHeadersIfNotEmpty(get);

        return get;
    }

    /**
     * Build HTTP Put
     *
     * @param url  - Target Url
     * @param body - Request Body
     * @return - Created HTTP Put
     */
    public static HttpPut buildPut(String url, String body) {

        url = BDDPlaceholders.replace(url);

        logger.debug("Building HTTP Put");
        logger.debug("URL : " + url);

        HttpPut put = new HttpPut(url);
        put.setEntity(new ByteArrayEntity(body.getBytes()));

        addHeadersIfNotEmpty(put);

        return put;

    }

    /**
     * Build HTTP Delete
     *
     * @param url - Target Url
     * @return - Created HTTP Delete
     */
    public static HttpDelete buildDelete(String url) {

        url = BDDPlaceholders.replace(url);

        logger.debug("Building HTTP DELETE");
        logger.debug("URL : " + url);

        HttpDelete delete = new HttpDelete(url);

        addHeadersIfNotEmpty(delete);

        return delete;
    }

    /**
     * Build HTTP Patch for the JSON Patch format.
     *
     * @param url  - Target Url
     * @param body - Request Body
     * @return - Created HTTP Patch
     */
    public static HttpPatch buildJsonPatch(String url, String body) {

        url = BDDPlaceholders.replace(url);

        logger.trace("Building HTTP Patch");
        logger.trace("URL : " + url);

        HttpPatch patch = new HttpPatch(url);
        patch.setEntity(new ByteArrayEntity(body.getBytes()));

        addHeadersIfNotEmpty(patch);

        return patch;
    }

    public static void sendHTTPMethod(String method, String url, String route, String[] body) throws IOException {
        sendHTTPMethod(method, url, route, body, true);
    }

    /**
     * This method executes the requested Http Method which is defined by the first parameter with the name of the
     * method, followed by the url, route and body. The last parameter is only needed for get calls to determine if call
     * should follow any redirection.
     *
     * @param method            - the string containing the Http Method to use ex: get, post, put, patch
     * @param url               - the url to send the request
     * @param route             - the route to send the request
     * @param body              - the body to send with the request (set to null if get)
     * @param followRedirection - boolean to follow redirection or stop on first HTTP Code 301/302
     * @throws IOException Exception thrown for HTTP errors.
     */
    public static void sendHTTPMethod(String method, String url, String route, String[] body,
                                      boolean followRedirection) throws IOException {

        url = BDDPlaceholders.replace(url);
        route = BDDPlaceholders.replace(route);
        String uri = HttpStepsCore.returnCorrectURI(url, route);

        String httpBody, jsonBody = null;
        Map<String, Object> formBody = null;

        if (body != null) {
            httpBody = BDDPlaceholders.replace(body[1]);

            switch (body[0]) {
                case ("dataStore"):

                    switch (TestDataCore.getDataStore(httpBody).getClass().getName()) {
                        case "java.util.HashMap": {

                            formBody = (Map<String, Object>) TestDataCore.getDataStore(httpBody);

                            break;
                        }

                        case "java.lang.String": {
                            jsonBody = new JSONObject(TestDataCore.getDataStore(httpBody).toString()).toString();
                            break;
                        }

                        case "org.json.JSONObject": {
                            jsonBody = TestDataCore.getDataStore(httpBody).toString();
                            break;

                        }
                        default:
                            break;
                    }

                    break;


                case ("file"):
                    jsonBody = fileContentsToString(System.getProperty("user.dir") + httpBody);
                    break;


                case ("text"):
                    jsonBody = TestDataCore.getDataStore(httpBody).toString();
                    break;

                default:
                    break;
            }


        } else
            jsonBody = "";

        HttpManager manager = ApiCore.getHttpManager();
        if (formBody == null) {
            iAddHTTPHeaderValue(HttpHeaders.CONTENT_TYPE, "application/json");
        }
        HttpResponseWrapper response = null;

        switch (method) {
            case ("get"):
                logger.debug("Sending GET to URL - " + uri);

                HttpGet getRequest = buildGet(uri);
                response = manager.sendGet(getRequest, followRedirection);
                break;
            case ("patch"):
                logger.debug("Sending PATCH to URL - " + uri);

                HttpPatch patchRequest = buildJsonPatch(uri, Objects.requireNonNull(jsonBody));
                response = manager.sendJsonPatch(patchRequest);
                break;
            case ("post"):
                logger.debug("Sending POST to URL - " + uri);

                if (formBody != null) {
                    HttpPost postRequest = buildPost(url, "");
                    response = manager.sendPostHeadersForms(postRequest, formBody);
                } else {
                    HttpPost postRequest = buildPost(uri, Objects.requireNonNull(jsonBody));
                    response = manager.sendPost(postRequest);
                }
                break;
            case ("put"):
                logger.debug("Sending PUT to URL - " + uri);
                if (formBody != null) {
                    HttpPut putRequest = buildPut(uri, "");
                    response = manager.sendPutHeadersForms(putRequest, formBody);
                } else {
                    HttpPut putRequest = buildPut(uri, Objects.requireNonNull(jsonBody));
                    response = manager.sendPut(putRequest);
                }
                break;
            case ("delete"):
                logger.debug("Sending DELETE to URL - " + uri);

                HttpDelete deleteRequest = buildDelete(uri);
                response = manager.sendDelete(deleteRequest);
                break;
            default:
                break;

        }

        if (response != null) {
            TestDataCore.addToDataStore("response", response);
            TestDataCore.addToDataStore("resbody", response.getHttpResponseBody());
        } else {
            Assert.fail("Response is null");
        }
    }

    /**
     * Verify HTTP Message response.
     *
     * @param response     - Received HTTP Response
     * @param expectedCode - Expected HTTP status code
     */
    public static void assertHTTPResponse(HttpResponseWrapper response, int expectedCode) {

        Assert.assertNotNull("Assert not null - HTTP response", response);

        try {

            String responseBody = response.getHttpResponseBody();
            int actualResponseCode = response.getStatusCode();

            Assert.assertEquals(expectedCode, actualResponseCode);

        } catch (NullPointerException e) {
            Assert.fail("Response Code was empty");
        }
    }

    private static HttpRequestBase formData(HttpEntityEnclosingRequestBase request, Map<String, Object> form) {

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        String boundary = "---------------" + UUID.randomUUID().toString();
        builder.setBoundary(boundary);

        if (form != null) {
            (form).forEach((k, v) -> {
                logger.debug(String.format("Adding HTTP Form [Key] %s, [Value] %s", k, v));

                ArrayList<String> obj = new ArrayList<>(
                        Collections.singletonList(".zip"));

                for (String str : obj) {
                    if ((v.toString()).contains(str)) {
                        builder.addBinaryBody(k, (File) v);
                    } else
                        builder.addTextBody(k, v.toString());
                }

            });
        }

        HttpEntity multipart = builder.build();
        request.setEntity(multipart);
        request.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.getMimeType() + ";boundary=" + boundary);

        return request;

    }

    private static HttpResponseWrapper executeHttpMethod(CloseableHttpClient httpClient, HttpHost host,
                                                         HttpRequestBase request) {
        HttpResponseWrapper response = null;

        try {
            response = HttpResponseWrapperFactory.execute(httpClient, host, request);
        } catch (Exception e) {
            logger.error("Failed to send request");
            logger.error(e);
        }
        HttpStepsCore.logResponse(response);
        return response;
    }

    private static HttpResponseWrapper executeHttpMethod(CloseableHttpClient httpClient, HttpRequestBase request) throws IOException {
        HttpResponseWrapper response;

        try {
            response = HttpResponseWrapperFactory.execute(httpClient, request);
        } catch (NoHttpResponseException e) {
            response = new HttpResponseWrapper(null, "Empty Response");
        } catch (HttpHostConnectException | SSLHandshakeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Failed to send request");
            logger.error(e);
            throw e;
        }
        HttpStepsCore.logResponse(response);
        return response;
    }

    private static HttpResponseWrapper executeHttpMethod(CloseableHttpClient httpClient, HttpHost host,
                                                         HttpRequestBase request,
                                                         HttpClientContext localContext) {
        HttpResponseWrapper response = null;

        try {
            response = HttpResponseWrapperFactory.execute(httpClient, host, request, localContext);
        } catch (Exception e) {
            logger.error("Failed to send request");
            logger.error(e);
        }
        HttpStepsCore.logResponse(response);
        return response;
    }

    private static String fileContentsToString(String filePath) {
        return TestEnvironmentManager.readFile(filePath);
    }

    /**
     * Sends a HTTP GET request to the target URL.
     * <p>
     * Usage :
     * HttpManager http = new HttpManager();
     * HttpResponse response = http.sendGET(url);
     *
     * @param get - HttpGet instance
     * @return - Null in case of exception
     */
    public HttpResponseWrapper sendGet(HttpGet get, boolean followRedirects) throws IOException {

        HttpStepsCore.logRequest("get", get.getURI().toString(), Arrays.toString(get.getAllHeaders()),
                "");

        if (!followRedirects) {
            CloseableHttpClient httpClientNoRedirect = HttpClientBuilder.create().disableRedirectHandling()
                    .build();
            return executeHttpMethod(httpClientNoRedirect, get);
        } else {
            return executeHttpMethod(httpClient, get);
        }

    }

    /**
     * Sends a HTTP DELETE request to the target URL.
     *
     * @param delete - Target for the GET request
     * @return - Null in case of exception
     */
    public HttpResponseWrapper sendDelete(HttpDelete delete) throws IOException {
        HttpStepsCore.logRequest("delete", delete.getURI().toString(), Arrays.toString(delete.getAllHeaders()),
                "");
        return executeHttpMethod(httpClient, delete);
    }

    /**
     * Sends a HTTP PUT request to the target URL.
     *
     * @param put - Target for the PUT request
     * @return - Null in case of exception
     */
    public HttpResponseWrapper sendPut(HttpPut put) throws IOException {
        HttpStepsCore.logRequest("put", put.getURI().toString(), Arrays.toString(put.getAllHeaders()),
                IOUtils.toString(put.getEntity().getContent(), "UTF-8"));
        return executeHttpMethod(httpClient, put);
    }

    /**
     * Sends a HTTP Post.
     *
     * @param files - Target files to send
     * @param post  - Target post to send
     * @return - HTTP response
     */
    public HttpResponseWrapper sendPostFiles(HttpPost post, File[] files) throws IOException {

        MultipartEntityBuilder entity = MultipartEntityBuilder.create();

        for (File obj : files
        ) {
            entity.addPart((obj).getName(), new FileBody(obj));

        }

        post.setEntity(entity.build());

        return executeHttpMethod(httpClient, post);
    }

    public HttpResponseWrapper sendPostHeadersForms(HttpPost post, Map<String, Object> form,
                                                    Map<String, Object> headers) throws IOException {
        formData(post, form);
        HttpStepsCore.logRequest("post", post.getURI().toString(), Arrays.toString(post.getAllHeaders()),
                IOUtils.toString(post.getEntity().getContent(), "UTF-8"));

        if (headers != null) {
            (headers).forEach((k, v) -> {
                logger.debug(String.format("Adding HTTP Header [Key] %s, [Value] %s", k, v));
                post.addHeader(k, v.toString());
            });
        }

        return executeHttpMethod(httpClient, post);
    }

    public HttpResponseWrapper sendPostHeadersForms(HttpPost post, Map<String, Object> form) throws IOException {

        post = (HttpPost) formData(post, form);
        HttpStepsCore.logRequest("post", post.getURI().toString(), Arrays.toString(post.getAllHeaders()),
                post.getEntity().toString());
        return executeHttpMethod(httpClient, post);
    }

    public HttpResponseWrapper sendPutHeadersForms(HttpPut put, Map<String, Object> form) throws IOException {

        put = (HttpPut) formData(put, form);
        HttpStepsCore.logRequest("put", put.getURI().toString(), Arrays.toString(put.getAllHeaders()),
                put.getEntity().toString());
        return executeHttpMethod(httpClient, put);
    }

    /**
     * Sends a HTTP Post.
     *
     * @param post - Target post to send
     * @return - HTTP response
     */
    public HttpResponseWrapper sendPost(HttpPost post) throws IOException {
        HttpStepsCore.logRequest("post", post.getURI().toString(), Arrays.toString(post.getAllHeaders()),
                IOUtils.toString(post.getEntity().getContent(), StandardCharsets.UTF_8));
        return executeHttpMethod(httpClient, post);
    }

    /**
     * Sends a HTTP Post to a HTTPS domain.
     *
     * @param post - Target post to send
     * @return - HTTP response
     */
    public HttpResponseWrapper sendHTTPSPost(HttpPost post) throws IOException {
        logger.debug("Sending POST to - " + post.getURI());
        HttpStepsCore.logRequest("post", post.getURI().toString(), Arrays.toString(post.getAllHeaders()),
                IOUtils.toString(post.getEntity().getContent(), StandardCharsets.UTF_8));
        return executeHttpMethod(httpClient, post);
    }

    /**
     * Sends a HTTP Patch in JSON Patch format
     *
     * @param patch - Target patch to send
     * @return - HTTP response
     */
    public HttpResponseWrapper sendJsonPatch(HttpPatch patch) throws IOException {
        HttpStepsCore.logRequest("patch", patch.getURI().toString(), Arrays.toString(patch.getAllHeaders()),
                IOUtils.toString(patch.getEntity().getContent(), "UTF-8"));
        return executeHttpMethod(httpClient, patch);
    }

    private CloseableHttpClient createHttpClient() {

        final int timeAfterInactivity = 300;
        final int defaultMaxPerRoute = 1000;
        final int maxTotal = 1000;
        final int tries = 3;

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        connectionManager.setValidateAfterInactivity(timeAfterInactivity);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        connectionManager.setMaxTotal(maxTotal);

        return HttpClients.custom().setConnectionManager(connectionManager).setRetryHandler((exception,
                                                                                             executionCount, context)
                -> {
            if (executionCount > tries) {
                logger.debug("Maximum tries reached for client http pool ");
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                logger.debug("No response from server on " + executionCount + " call");
                return true;
            }
            return false;
        }).build();
    }
}
