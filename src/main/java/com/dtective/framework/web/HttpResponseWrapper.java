package com.dtective.framework.web;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpResponseWrapper {

    private HttpResponse responseCache;
    private String responseBodyEntityCache;

    public HttpResponseWrapper(HttpResponse response) throws IOException {
        responseCache = response;
        responseBodyEntityCache = cacheHttpResponseBody(responseCache);
    }

    public HttpResponseWrapper(HttpResponse response, String responseBody) {
        responseCache = response;
        responseBodyEntityCache = responseBody;
    }

    public HttpResponse getResponse() {
        return responseCache;
    }

    public String getHttpResponseBody() {
        return responseBodyEntityCache;
    }

    private String cacheHttpResponseBody(HttpResponse response) throws IOException {
        String responseBody;
        HttpEntity entity = response.getEntity();
        ContentType contentType = ContentType.getOrDefault(entity);

        if (responseBodyEntityCache == null) {
            try {
                responseBody = EntityUtils.toString(entity, contentType.getCharset() == null ? "UTF-8"
                        : contentType.getCharset().toString());
            } catch (IllegalArgumentException e) {
                responseBody = "";
            }

        } else
            responseBody = responseBodyEntityCache;

        return responseBody;
    }


    public int getStatusCode() {
        return responseCache.getStatusLine().getStatusCode();
    }

}
