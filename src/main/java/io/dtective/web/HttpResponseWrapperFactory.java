package io.dtective.web;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class HttpResponseWrapperFactory extends HttpResponseWrapper {

    public HttpResponseWrapperFactory(CloseableHttpResponse response) throws IOException {
        super(response);
    }

    private static HttpResponseWrapper createInstance(HttpResponse response) throws IOException {
        return new HttpResponseWrapper(response);
    }

    public static HttpResponseWrapper execute(CloseableHttpClient httpClient, HttpRequestBase request) throws IOException {
        return createInstance(httpClient.execute(request));
    }

    public static HttpResponseWrapper execute(CloseableHttpClient httpClient, HttpHost host, HttpRequestBase request) throws IOException {
        return createInstance(httpClient.execute(host, request));
    }

    public static HttpResponseWrapper execute(CloseableHttpClient httpClient, HttpHost host, HttpRequestBase request,
                                              HttpContext localContext) throws IOException {
        return createInstance(httpClient.execute(host, request, localContext));
    }
}
