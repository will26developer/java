package com.william.concurrenthttp;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.william.concurrenthttp.concurrency.HttpConcurrencyScheduler;

public class ConcurrentHttpMainApp {
    public static void main(String[] args) {
        URI uri = URI.create("https://api.restcountries.com/countries/v5");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer rc_live_97a07b6faaf348018231c385ab6ef4e5");
        HttpConcurrencyScheduler concurrencyScheduler = new HttpConcurrencyScheduler(uri, headers);
        concurrencyScheduler.start();
    }
}
