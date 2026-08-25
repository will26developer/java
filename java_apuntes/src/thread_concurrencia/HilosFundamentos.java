package thread_concurrencia;
/**
 * HILOS Y CONCURRENCIA — FUNDAMENTOS
 * =====================================
 * Un hilo (Thread) es la unidad mínima de ejecución dentro de un proceso.
 * Java permite ejecutar múltiples hilos simultáneamente (concurrencia).
 *
 * CICLO DE VIDA DE UN HILO:
 *   NEW        → creado pero no iniciado
 *   RUNNABLE   → ejecutándose o listo para ejecutarse
 *   BLOCKED    → esperando un monitor lock
 *   WAITING    → esperando indefinidamente (wait, join)
 *   TIMED_WAITING → esperando con tiempo límite (sleep, join(ms))
 *   TERMINATED → ha terminado su ejecución
 *
 * FORMAS DE CREAR HILOS:
 *   1. Extender Thread
 *   2. Implementar Runnable
 *   3. Implementar Callable (con valor de retorno)
 *   4. Lambda (Runnable es @FunctionalInterface)
 */
public class HilosFundamentos {

    public static void main(String[] args) throws InterruptedException {

        // ================================================================
        // A. EXTENDER THREAD
        // ================================================================
        System.out.println("========== A. Extender Thread ==========");

        MiHilo h1 = new MiHilo("Hilo-A");
        MiHilo h2 = new MiHilo("Hilo-B");

        h1.start(); // start() → crea el hilo y llama a run() en él
        h2.start();

        // start() ≠ run()
        // run() ejecuta el código EN EL HILO ACTUAL (no crea nuevo hilo)
        // start() crea un NUEVO hilo y ejecuta run() en él

        h1.join(); // espera a que h1 termine
        h2.join();
        System.out.println("Ambos hilos terminaron");

        // ================================================================
        // B. IMPLEMENTAR RUNNABLE
        // ================================================================
        System.out.println("\n========== B. Implementar Runnable ==========");

        // Runnable es @FunctionalInterface → se puede usar lambda
        Runnable tarea = new MiRunnable("Runnable-1");
        Thread t1 = new Thread(tarea, "Hilo-Runnable-1");
        Thread t2 = new Thread(new MiRunnable("Runnable-2"), "Hilo-Runnable-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // ================================================================
        // C. LAMBDA COMO RUNNABLE
        // ================================================================
        System.out.println("\n========== C. Lambda ==========");

        Thread lambdaHilo = new Thread(() -> {
            System.out.println("Lambda hilo: " + Thread.currentThread().getName());
            for (int i = 1; i <= 3; i++) {
                System.out.println("  Lambda " + i);
            }
        }, "Lambda-Hilo");

        lambdaHilo.start();
        lambdaHilo.join();

        // ================================================================
        // D. PROPIEDADES DE UN HILO
        // ================================================================
        System.out.println("\n========== D. Propiedades del hilo ==========");

        Thread actual = Thread.currentThread();
        System.out.println("Nombre       : " + actual.getName());
        System.out.println("ID           : " + actual.getId());
        System.out.println("Prioridad    : " + actual.getPriority());
        System.out.println("Es daemon    : " + actual.isDaemon());
        System.out.println("Estado       : " + actual.getState());
        System.out.println("Está vivo    : " + actual.isAlive());
        System.out.println("Grupo        : " + actual.getThreadGroup().getName());

        // Prioridades (1=MIN, 5=NORM, 10=MAX) — sugerencia a la JVM, no garantía
        Thread alta    = new Thread(() -> {}, "Alta");
        Thread normal  = new Thread(() -> {}, "Normal");
        Thread baja    = new Thread(() -> {}, "Baja");

        alta.setPriority(Thread.MAX_PRIORITY);    // 10
        normal.setPriority(Thread.NORM_PRIORITY); // 5
        baja.setPriority(Thread.MIN_PRIORITY);    // 1

        System.out.println("\nPrioridades:");
        System.out.println("  MAX : " + alta.getPriority());
        System.out.println("  NORM: " + normal.getPriority());
        System.out.println("  MIN : " + baja.getPriority());

        // ================================================================
        // E. HILO DAEMON
        //    Los hilos daemon mueren cuando todos los hilos no-daemon terminan
        //    Útiles para tareas de fondo (GC, monitoreo...)
        // ================================================================
        System.out.println("\n========== E. Hilos Daemon ==========");

        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("Daemon trabajando...");
                try { Thread.sleep(500); } catch (InterruptedException e) { break; }
            }
        }, "Daemon");

        daemon.setDaemon(true); // DEBE llamarse ANTES de start()
        daemon.start();

        Thread.sleep(1200); // dejar que el daemon trabaje un poco
        System.out.println("Main termina → el daemon morirá con él");

        // ================================================================
        // F. SLEEP, YIELD e INTERRUPT
        // ================================================================
        System.out.println("\n========== F. sleep, yield, interrupt ==========");

        // sleep — pausa el hilo actual (no libera locks)
        Thread dormido = new Thread(() -> {
            System.out.println("Durmiendo 500ms...");
            try {
                Thread.sleep(500);
                System.out.println("Desperté");
            } catch (InterruptedException e) {
                System.out.println("Interrumpido mientras dormía");
                Thread.currentThread().interrupt(); // restaurar flag
            }
        }, "Dormido");
        dormido.start();
        dormido.join();

        // interrupt — señal de interrupción al hilo
        Thread interruptible = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Hilo detectó interrupción en i=" + i);
                    return;
                }
                System.out.println("  Trabajando i=" + i);
            }
        }, "Interruptible");
        interruptible.start();
        Thread.sleep(1); // dejar que arranque
        interruptible.interrupt(); // enviar señal de interrupción
        interruptible.join();

        // yield — sugiere al scheduler ceder el turno (raramente necesario)
        Thread yielder = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Yielder " + i);
                Thread.yield(); // cede el turno si hay otro hilo listo
            }
        }, "Yielder");
        yielder.start();
        yielder.join();

        // ================================================================
        // G. ESTADO DE LOS HILOS
        // ================================================================
        System.out.println("\n========== G. Estados del hilo ==========");

        Thread estadoHilo = new Thread(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }, "Estado-Hilo");

        System.out.println("Antes start  : " + estadoHilo.getState()); // NEW
        estadoHilo.start();
        Thread.sleep(50);
        System.out.println("Tras start   : " + estadoHilo.getState()); // TIMED_WAITING (sleep)
        estadoHilo.interrupt();
        estadoHilo.join();
        System.out.println("Tras join    : " + estadoHilo.getState()); // TERMINATED

        // ================================================================
        // H. HILO PRINCIPAL Y GRUPO DE HILOS
        // ================================================================
        System.out.println("\n========== H. Hilos activos ==========");

        // Todos los hilos activos del grupo actual
        ThreadGroup grupo = Thread.currentThread().getThreadGroup();
        Thread[] hilosActivos = new Thread[grupo.activeCount()];
        grupo.enumerate(hilosActivos);
        System.out.println("Hilos activos en grupo '" + grupo.getName() + "':");
        for (Thread h : hilosActivos) {
            if (h != null) System.out.println("  " + h.getName() + " → " + h.getState());
        }

        System.out.println("\nMain termina");
    }
}

// ====================================================================
// CLASES DE EJEMPLO
// ====================================================================

class MiHilo extends Thread {
    private String nombre;

    public MiHilo(String nombre) {
        super(nombre); // nombre del hilo
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println(nombre + " iniciado en hilo: " + Thread.currentThread().getName());
        for (int i = 1; i <= 3; i++) {
            System.out.println("  " + nombre + " → " + i);
            try {
                Thread.sleep(100); // simula trabajo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println(nombre + " terminado");
    }
}

class MiRunnable implements Runnable {
    private String nombre;

    public MiRunnable(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println(nombre + " ejecutando en: " + Thread.currentThread().getName());
        for (int i = 1; i <= 3; i++) {
            System.out.println("  " + nombre + " → " + i);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
