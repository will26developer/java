package hilos;

import hilos.runnable.ViajeTarea;

public class EjemploInterfaceRunnableJava8 {
    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();
        Runnable viaje = () -> {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i + " - " + Thread.currentThread().getName());
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Main = " + main.getState());
                System.out.println("Finalmente me voy de viaje a = " + Thread.currentThread().getName());
            };
        Thread v1 = new Thread(viaje, "Isla de pascua");
        Thread v2 = new Thread(viaje, "Nueva Zelanda");
        Thread v3 = new Thread(viaje,"Alemania");
        Thread v4 = new Thread(viaje, "Cuba");

        v1.start();
        v2.start();
        v3.start();
        v4.start();
        v1.join();
        v2.join();
        v3.join();
        v4.join();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Continuando con la ejecucion del metodo " + main.getName());

    }
}
