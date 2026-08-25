package hilos;

import hilos.runnable.ImprimirFrase;

public class EjemploSincronizacionThread {
    public static void main(String[] args) throws InterruptedException {
        Thread h1 = new Thread(new ImprimirFrase("Hola", " que tal?"));
        h1.start();
        Thread h2 = new Thread(new ImprimirFrase("Quien", " eres tu?"));
        h2.start();
        Thread h3 = new Thread(new ImprimirFrase("Gracias", " por todo amigo!"));
        h3.start();
        Thread.sleep(1000);
        System.out.println(h3.getState());
    }

    public synchronized static void imprimirFrases(String frase1, String frase2) {
        System.out.print(frase1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(frase2);
    }
}
