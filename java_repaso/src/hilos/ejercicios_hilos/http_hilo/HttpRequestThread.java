package hilos.ejercicios_hilos.http_hilo;

import java.util.concurrent.BlockingQueue;

import hilos.ejercicios_hilos.http_hilo.models.Response;

public class HttpRequestThread {

    private final BlockingQueue<Response<String>> queue;
    private String uri;

    public HttpRequestThread(BlockingQueue<Response<String>> queue, String uri) {
        this.queue = queue;
        this.uri = uri;
    }

    public void setResponse() {
        ApiRequest apiRequest = new ApiRequest();
        Response<String> response = apiRequest.makeRequest(uri);
        try {
            queue.put(response);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Response<String> getResponse() throws InterruptedException {
        return queue.take();
    }
}
