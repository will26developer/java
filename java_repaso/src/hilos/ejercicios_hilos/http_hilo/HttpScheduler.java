package hilos.ejercicios_hilos.http_hilo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import hilos.ejercicios_hilos.http_hilo.models.Response;

public class HttpScheduler {
    private final BlockingQueue<Response<String>> queue;
    private final HttpRequestThread httpRequestThread;

    public HttpScheduler(String uri) {
        this.queue = new ArrayBlockingQueue<>(10);
        this.httpRequestThread = new HttpRequestThread(queue, uri);
    }

    public void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                httpRequestThread.setResponse();
            }
        }, 5000, 10000);
        Thread consumer = new Thread(this::getHttpResponse);
        consumer.start();
    }

    public void getHttpResponse() {
        Response<String> response = null;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                response = httpRequestThread.getResponse();
                System.out.println("Response = " + response.getBody());
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
