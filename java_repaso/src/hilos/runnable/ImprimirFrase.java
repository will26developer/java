package hilos.runnable;

import hilos.EjemploSincronizacionThread;

public class ImprimirFrase implements Runnable {
    private String frase1, frase2;

    public ImprimirFrase(String frase1, String frase2) {
        this.frase1 = frase1;
        this.frase2 = frase2;
    }

    @Override
    public void run() {
        EjemploSincronizacionThread.imprimirFrases(frase1, frase2);
    }

}
