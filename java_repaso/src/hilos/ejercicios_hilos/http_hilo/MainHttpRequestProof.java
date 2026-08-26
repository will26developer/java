package hilos.ejercicios_hilos.http_hilo;

public class MainHttpRequestProof {
    public static void main(String[] args) {
        HttpScheduler httpScheduler = new HttpScheduler("https://api.restcountries.com/countries/v5?q=canada");
        httpScheduler.start();
    }
}
