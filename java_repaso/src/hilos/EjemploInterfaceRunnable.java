package hilos;

import hilos.runnable.ViajeTarea;

public class EjemploInterfaceRunnable {
    public static void main(String[] args) {
        new Thread(new ViajeTarea("Isla de pascua")).start();
        new Thread(new ViajeTarea("Nueva Zelanda")).start();
        new Thread(new ViajeTarea("Alemania")).start();
        new Thread(new ViajeTarea("Cuba")).start();

    }
}
