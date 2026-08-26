package hilos.ejercicios_hilos.http_hilo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import hilos.ejercicios_hilos.http_hilo.models.Response;

public class ApiRequest {

    public Response<String> makeRequest(String uri) {
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri))
                .header("Authorization", "Bearer rc_live_97a07b6faaf348018231c385ab6ef4e5")
                .GET().build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return new Response<String>(response.statusCode(), response.body());

    }

}
