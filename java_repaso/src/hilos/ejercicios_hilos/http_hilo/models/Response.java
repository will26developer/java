package hilos.ejercicios_hilos.http_hilo.models;

public class Response<T> {
    private int statusCode;
    private T body;

    public Response(int statusCode, T body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getBody() {
        return body;
    }
}
