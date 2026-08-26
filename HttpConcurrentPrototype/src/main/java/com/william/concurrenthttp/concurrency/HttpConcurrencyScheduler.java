package com.william.concurrenthttp.concurrency;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.william.concurrenthttp.dtos.ApiResponseDto;
import com.william.concurrenthttp.dtos.CountryDto;
import com.william.concurrenthttp.dtos.DataDto;
import com.william.concurrenthttp.http.ApiRequest;
import com.william.concurrenthttp.models.Response;
import com.william.concurrenthttp.serialization.JsonDeserializer;

public class HttpConcurrencyScheduler {
    private final BlockingQueue<Response<String>> queue;
    private final ApiRequest apiRequest;
    private final URI uri;
    private Map<String, String> headers;

    public HttpConcurrencyScheduler(URI uri, Map<String, String> headers) {
        this.uri = uri;
        this.queue = new ArrayBlockingQueue<>(10);
        this.apiRequest = new ApiRequest(uri, headers);
    }

    public void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Response<String> response = apiRequest.executeRequest();
                try {
                    queue.put(response);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, 5000, 100000);
        Thread consumer = new Thread(this::getResponse);
        consumer.start();
    }

    public void getResponse() {
        Response<String> strResponse = null;
        ApiResponseDto apiResponseDto = null;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                strResponse = queue.take();
                JsonDeserializer deserializer = new JsonDeserializer();
                apiResponseDto = deserializer.deserialize(strResponse.getBody(), ApiResponseDto.class);
                DataDto dataDto = apiResponseDto.getData();
                List<CountryDto> countries = dataDto.getObjects();
                for (CountryDto country : countries) {
                    System.out.println("Country name: " + country.getNames().getCommon());
                    System.out.println("Population: " + country.getPopulation());
                    System.out.println("Region: " + country.getRegion());
                    country.getCapitals().forEach(capital -> System.out.println("Capital: " + capital.getName()));
                    System.out.println("========================================================");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
