package hilos.ejemplo_sync;

import hilos.ejemplo_sync.runnable.Consumidor;
import hilos.ejemplo_sync.runnable.Panadero;

public class EjemploProductoConsumidor {
    public static void main(String[] args) {
        Panaderia panaderia = new Panaderia();
        new Thread(new Panadero(panaderia)).start();
        new Thread(new Consumidor(panaderia)).start();
    }
}
