package thread_concurrencia;
/**
 * SINCRONIZACIÓN Y PROBLEMAS DE CONCURRENCIA
 * ============================================
 * Cuando varios hilos acceden a datos compartidos sin sincronización
 * pueden ocurrir problemas de concurrencia.
 *
 * PROBLEMAS PRINCIPALES:
 *   Race condition  → resultado depende del orden de ejecución de hilos
 *   Deadlock        → dos hilos esperan mutuamente el lock del otro
 *   Livelock        → hilos reaccionan constantemente entre sí sin avanzar
 *   Starvation      → un hilo nunca obtiene acceso al recurso compartido
 *
 * MECANISMOS DE SINCRONIZACIÓN:
 *   synchronized    → bloqueo intrínseco (monitor)
 *   volatile        → visibilidad entre hilos
 *   wait/notify     → comunicación entre hilos
 *   Atomic*         → operaciones atómicas sin bloqueo
 */
import java.util.concurrent.atomic.*;

public class Sincronizacion {

    public static void main(String[] args) throws InterruptedException {

        // ================================================================
        // A. PROBLEMA: RACE CONDITION
        // ================================================================
        System.out.println("========== A. Race condition ==========");

        ContadorInseguro ci = new ContadorInseguro();
        Thread[] hilosInseguros = new Thread[10];
        for (int i = 0; i < 10; i++) {
            hilosInseguros[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) ci.incrementar();
            });
        }
        for (Thread h : hilosInseguros) h.start();
        for (Thread h : hilosInseguros) h.join();
        System.out.println("Contador inseguro (esperado 10000): " + ci.getValor());
        // Resultado impredecible: puede ser < 10000

        // ================================================================
        // B. SOLUCIÓN: synchronized
        // ================================================================
        System.out.println("\n========== B. synchronized ==========");

        ContadorSeguro cs = new ContadorSeguro();
        Thread[] hilosSeguras = new Thread[10];
        for (int i = 0; i < 10; i++) {
            hilosSeguras[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) cs.incrementar();
            });
        }
        for (Thread h : hilosSeguras) h.start();
        for (Thread h : hilosSeguras) h.join();
        System.out.println("Contador seguro  (esperado 10000): " + cs.getValor());
        // Siempre 10000

        // ================================================================
        // C. BLOQUE synchronized
        // ================================================================
        System.out.println("\n========== C. Bloque synchronized ==========");

        Object lock = new Object(); // objeto como candado
        int[] contador = {0};

        Thread hA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) { // solo un hilo a la vez
                    contador[0]++;
                    System.out.println("Hilo A → " + contador[0]);
                }
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        }, "Hilo-A");

        Thread hB = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) { // mismo lock → se bloquean mutuamente
                    contador[0]++;
                    System.out.println("Hilo B → " + contador[0]);
                }
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        }, "Hilo-B");

        hA.start(); hB.start();
        hA.join();  hB.join();
        System.out.println("Final: " + contador[0]);

        // ================================================================
        // D. volatile — visibilidad entre hilos
        // ================================================================
        System.out.println("\n========== D. volatile ==========");
        // volatile garantiza que los cambios son visibles para todos los hilos
        // pero NO garantiza atomicidad (no reemplaza synchronized para i++)

        HiloConVolatile hv = new HiloConVolatile();
        Thread hiloVolatile = new Thread(hv, "Volatile-Hilo");
        hiloVolatile.start();
        Thread.sleep(500);
        hv.detener(); // visible inmediatamente gracias a volatile
        hiloVolatile.join();
        System.out.println("Hilo volatile detenido");

        // ================================================================
        // E. ATOMIC — operaciones atómicas sin locks
        // ================================================================
        System.out.println("\n========== E. Atomic ==========");

        AtomicInteger atomicCounter = new AtomicInteger(0);
        Thread[] atomicHilos = new Thread[10];
        for (int i = 0; i < 10; i++) {
            atomicHilos[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicCounter.incrementAndGet(); // atómica
                }
            });
        }
        for (Thread h : atomicHilos) h.start();
        for (Thread h : atomicHilos) h.join();
        System.out.println("AtomicInteger (esperado 10000): " + atomicCounter.get());

        // Otros métodos de AtomicInteger
        AtomicInteger ai = new AtomicInteger(10);
        System.out.println("get()              : " + ai.get());
        System.out.println("getAndIncrement()  : " + ai.getAndIncrement()); // 10, luego 11
        System.out.println("incrementAndGet()  : " + ai.incrementAndGet()); // 12
        System.out.println("getAndAdd(5)       : " + ai.getAndAdd(5));      // 12, luego 17
        System.out.println("addAndGet(3)       : " + ai.addAndGet(3));      // 20
        System.out.println("getAndSet(0)       : " + ai.getAndSet(0));      // 20, luego 0

        // compareAndSet (CAS) — clave en algoritmos lock-free
        boolean exito = ai.compareAndSet(0, 100); // si es 0, poner 100
        System.out.println("CAS(0→100) éxito   : " + exito + ", valor: " + ai.get());
        boolean fallo = ai.compareAndSet(0, 999); // si es 0 (no lo es → 100)
        System.out.println("CAS(0→999) éxito   : " + fallo + ", valor: " + ai.get());

        // AtomicLong, AtomicBoolean, AtomicReference
        AtomicLong    al  = new AtomicLong(100L);
        AtomicBoolean ab  = new AtomicBoolean(false);
        AtomicReference<String> ar = new AtomicReference<>("inicial");

        ab.set(true);
        ar.compareAndSet("inicial", "actualizado");
        System.out.println("AtomicLong    : " + al.incrementAndGet());
        System.out.println("AtomicBoolean : " + ab.get());
        System.out.println("AtomicRef     : " + ar.get());

        // ================================================================
        // F. wait() y notify() — comunicación entre hilos
        // ================================================================
        System.out.println("\n========== F. wait() y notify() ==========");

        BuzonMensajes buzon = new BuzonMensajes();

        Thread productor = new Thread(() -> {
            try {
                Thread.sleep(500); // simula trabajo
                buzon.enviar("¡Hola desde el productor!");
                Thread.sleep(300);
                buzon.enviar("Segundo mensaje");
                Thread.sleep(300);
                buzon.enviar(null); // señal de fin
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Productor");

        Thread consumidor = new Thread(() -> {
            while (true) {
                String msg = buzon.recibir();
                if (msg == null) break;
                System.out.println("Consumidor recibió: " + msg);
            }
        }, "Consumidor");

        consumidor.start();
        productor.start();
        productor.join();
        consumidor.join();

        // ================================================================
        // G. DEADLOCK — cómo ocurre y cómo evitarlo
        // ================================================================
        System.out.println("\n========== G. Deadlock (ejemplo y prevención) ==========");

        // DEADLOCK ocurre cuando:
        // Hilo 1 tiene lock A, espera lock B
        // Hilo 2 tiene lock B, espera lock A → bloqueo mutuo

        // PREVENCIÓN: adquirir locks siempre en el mismo orden
        Object lockA = new Object();
        Object lockB = new Object();

        // Ejemplo de deadlock POTENCIAL (comentado para no bloquear el programa)
        System.out.println("Deadlock ocurre cuando dos hilos esperan locks del otro");
        System.out.println("Prevención: ordenar siempre los locks de la misma manera");

        // Forma segura: hilo 1 y hilo 2 adquieren A antes que B
        Thread seguro1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Seguro1 tiene A");
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (lockB) {
                    System.out.println("Seguro1 tiene A y B");
                }
            }
        }, "Seguro1");

        Thread seguro2 = new Thread(() -> {
            synchronized (lockA) { // mismo orden: A antes que B
                System.out.println("Seguro2 tiene A");
                synchronized (lockB) {
                    System.out.println("Seguro2 tiene A y B");
                }
            }
        }, "Seguro2");

        seguro1.start(); seguro2.start();
        seguro1.join();  seguro2.join();
        System.out.println("Sin deadlock gracias al orden de locks");

        // ================================================================
        // H. MÉTODOS synchronized vs BLOQUES — cuándo usar cada uno
        // ================================================================
        System.out.println("\n========== H. Resumen sincronización ==========");
        System.out.println("synchronized método → lock en 'this' o en la clase (static)");
        System.out.println("synchronized bloque → lock en objeto explícito (más flexible)");
        System.out.println("volatile            → solo visibilidad, NO atomicidad");
        System.out.println("Atomic*             → operaciones atómicas sin lock (más rápido)");
        System.out.println("wait/notify         → comunicación entre hilos (siempre en sync)");
    }
}

// ====================================================================
// CLASES DE EJEMPLO
// ====================================================================

class ContadorInseguro {
    private int valor = 0;
    public void incrementar() { valor++; } // NO thread-safe: leer-modificar-escribir
    public int getValor()     { return valor; }
}

class ContadorSeguro {
    private int valor = 0;
    public synchronized void incrementar() { valor++; } // thread-safe
    public synchronized int getValor()     { return valor; }
}

class HiloConVolatile implements Runnable {
    private volatile boolean ejecutando = true; // visible para todos los hilos

    public void detener() { ejecutando = false; }

    @Override
    public void run() {
        int i = 0;
        while (ejecutando) { // lee el valor actualizado gracias a volatile
            i++;
        }
        System.out.println("Volatile: completé " + i + " iteraciones antes de detenerme");
    }
}

class BuzonMensajes {
    private String mensaje = null;
    private boolean hayMensaje = false;

    public synchronized void enviar(String msg) throws InterruptedException {
        while (hayMensaje) {
            wait(); // espera a que el consumidor recoja el mensaje anterior
        }
        this.mensaje   = msg;
        hayMensaje     = true;
        System.out.println("Productor envió: " + msg);
        notify(); // despierta al consumidor
    }

    public synchronized String recibir() {
        while (!hayMensaje) {
            try { wait(); } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        String msg = mensaje;
        hayMensaje = false;
        notify(); // despierta al productor
        return msg;
    }
}
