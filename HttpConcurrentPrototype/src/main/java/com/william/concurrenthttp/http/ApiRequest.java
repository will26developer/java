package com.william.concurrenthttp.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.william.concurrenthttp.models.Response;

public class ApiRequest {
    private final URI uri;
    private final Map<String, String> headers;

    public ApiRequest(URI uri, Map<String, String> headers) {
        this.uri = uri;
        this.headers = headers;
    }

    public Response<String> executeRequest() {
        HttpResponse<String> response = null;
        Response<String> responseEntity = new Response<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET();
            if (headers != null) {
                headers.forEach(builder::header);
            }
            HttpRequest request = builder.build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseEntity.setStatusCode(response.statusCode());
            responseEntity.setBody(response.body());
            responseEntity.setHeaders(response.headers().map());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ae) {
            Thread.currentThread().interrupt();
        }

        return responseEntity;
    }
}
