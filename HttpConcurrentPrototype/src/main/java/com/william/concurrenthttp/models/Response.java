package com.william.concurrenthttp.models;

import java.util.List;
import java.util.Map;

public class Response<T> {
    private int statusCode;
    private T body;
    private Map<String, List<String>> headers;

    public Response() {

    }

    public Response(int statusCode, T body, Map<String, List<String>> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getBody() {
        return body;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

}
