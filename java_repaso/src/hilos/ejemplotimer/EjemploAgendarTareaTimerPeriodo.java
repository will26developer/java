package hilos.ejemplotimer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class EjemploAgendarTareaTimerPeriodo {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Tarea periodica en: " + new Date().toString() + " - nombre de Thread: "
                        + Thread.currentThread().getName());
                System.out.println("Finaliza el tiempo");
                // timer.cancel();
            }
        }, 5000, 10000);

        System.out.println("Agendamos una tarea para 5 segundos mas....");
    }
}
