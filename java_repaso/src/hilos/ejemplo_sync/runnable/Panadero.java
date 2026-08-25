package hilos.ejemplo_sync.runnable;

import java.util.concurrent.ThreadLocalRandom;

import hilos.ejemplo_sync.Panaderia;

public class Panadero implements Runnable {
    private Panaderia panaderia;

    public Panadero(Panaderia panaderia) {
        this.panaderia = panaderia;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                panaderia.hornear("Pan n: " + i);
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }
    }

}
