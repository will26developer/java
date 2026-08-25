package thread_concurrencia;
/**
 * LOCKS AVANZADOS, COLECCIONES CONCURRENTES Y PATRONES
 * ======================================================
 *
 * LOCKS DEL PAQUETE java.util.concurrent.locks:
 *   ReentrantLock       → como synchronized pero más flexible
 *   ReentrantReadWriteLock → múltiples lectores O un escritor
 *   StampedLock         → lock optimista (Java 8+)
 *
 * SEMÁFOROS Y BARRERAS:
 *   Semaphore           → limitar acceso concurrente a N hilos
 *   CountDownLatch      → esperar a que N hilos completen
 *   CyclicBarrier       → N hilos se esperan mutuamente
 *   Phaser              → barrera flexible y reutilizable
 *
 * COLECCIONES CONCURRENTES:
 *   ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue...
 */
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.*;

public class ConcurrenciaAvanzada {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        // ================================================================
        // A. REENTRANTLOCK — alternativa a synchronized
        // ================================================================
        System.out.println("========== A. ReentrantLock ==========");

        ContadorConLock ccl = new ContadorConLock();
        ExecutorService exec = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            exec.submit(() -> {
                for (int j = 0; j < 2500; j++) ccl.incrementar();
            });
        }
        exec.shutdown();
        exec.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("ReentrantLock (esperado 10000): " + ccl.getValor());

        // Ventajas de ReentrantLock sobre synchronized:
        // - tryLock() — intenta adquirir sin bloquear
        // - tryLock(tiempo, unidad) — espera un tiempo máximo
        // - lockInterruptibly() — puede ser interrumpido
        // - fairness — puede ser justo (orden FIFO)

        ReentrantLock lock = new ReentrantLock(true); // fair=true

        // tryLock sin espera
        if (lock.tryLock()) {
            try {
                System.out.println("tryLock() adquirido");
            } finally {
                lock.unlock();
            }
        }

        // tryLock con timeout
        boolean adquirido = lock.tryLock(100, TimeUnit.MILLISECONDS);
        if (adquirido) {
            try {
                System.out.println("tryLock(100ms) adquirido");
            } finally {
                lock.unlock();
            }
        }

        // isLocked / getHoldCount
        lock.lock();
        lock.lock(); // ReentrantLock permite reentrancia
        System.out.println("HoldCount (reentrante): " + lock.getHoldCount()); // 2
        lock.unlock();
        lock.unlock();

        // ================================================================
        // B. READWRITELOCK — múltiples lectores, un escritor
        // ================================================================
        System.out.println("\n========== B. ReadWriteLock ==========");

        Cache2 cache = new Cache2();
        ExecutorService rwExec = Executors.newFixedThreadPool(6);

        // 4 lectores
        for (int i = 0; i < 4; i++) {
            final int id = i;
            rwExec.submit(() -> {
                String val = cache.leer("clave");
                System.out.println("Lector " + id + " leyó: " + val);
            });
        }

        // 2 escritores
        for (int i = 0; i < 2; i++) {
            final int id = i;
            rwExec.submit(() -> {
                cache.escribir("clave", "valor-" + id);
                System.out.println("Escritor " + id + " escribió");
            });
        }

        rwExec.shutdown();
        rwExec.awaitTermination(3, TimeUnit.SECONDS);

        // ================================================================
        // C. SEMAPHORE — limitar acceso concurrente
        // ================================================================
        System.out.println("\n========== C. Semaphore ==========");

        // Solo 3 hilos pueden acceder simultáneamente al recurso
        Semaphore semaforo = new Semaphore(3);
        ExecutorService semExec = Executors.newFixedThreadPool(8);

        for (int i = 1; i <= 8; i++) {
            final int id = i;
            semExec.submit(() -> {
                try {
                    semaforo.acquire(); // decrementa el semáforo (bloquea si == 0)
                    System.out.println("Hilo " + id + " accedió. Disponibles: "
                            + semaforo.availablePermits());
                    Thread.sleep(200); // simula uso del recurso
                    System.out.println("Hilo " + id + " libera");
                    semaforo.release(); // incrementa el semáforo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        semExec.shutdown();
        semExec.awaitTermination(5, TimeUnit.SECONDS);

        // tryAcquire — sin bloqueo
        System.out.println("tryAcquire: " + semaforo.tryAcquire()); // true (hay permisos)

        // ================================================================
        // D. COUNTDOWNLATCH — esperar a que N hilos completen
        // ================================================================
        System.out.println("\n========== D. CountDownLatch ==========");

        int N = 5;
        CountDownLatch latch = new CountDownLatch(N);
        ExecutorService latchExec = Executors.newFixedThreadPool(N);

        long inicio = System.currentTimeMillis();
        for (int i = 1; i <= N; i++) {
            final int id = i;
            latchExec.submit(() -> {
                try {
                    long trabajo = (long)(Math.random() * 300 + 100);
                    Thread.sleep(trabajo);
                    System.out.println("  Tarea " + id + " completada (" + trabajo + "ms)");
                    latch.countDown(); // decrementa el contador
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        latch.await(); // bloquea hasta que el contador llegue a 0
        System.out.println("Todas las tareas completadas en "
                + (System.currentTimeMillis() - inicio) + "ms");
        latchExec.shutdown();

        // ================================================================
        // E. CYCLICBARRIER — N hilos se esperan mutuamente
        // ================================================================
        System.out.println("\n========== E. CyclicBarrier ==========");

        int participantes = 3;
        CyclicBarrier barrera = new CyclicBarrier(participantes,
            () -> System.out.println("--- Todos en el punto de encuentro, ¡adelante! ---"));

        ExecutorService barrExec = Executors.newFixedThreadPool(participantes);
        for (int i = 1; i <= participantes; i++) {
            final int id = i;
            barrExec.submit(() -> {
                try {
                    long tiempo = (long)(Math.random() * 300 + 100);
                    System.out.println("Participante " + id + " preparándose...");
                    Thread.sleep(tiempo);
                    System.out.println("Participante " + id + " listo, esperando en barrera");
                    barrera.await(); // espera a que todos lleguen
                    System.out.println("Participante " + id + " continúa");
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        barrExec.shutdown();
        barrExec.awaitTermination(5, TimeUnit.SECONDS);

        // ================================================================
        // F. BLOCKINGQUEUE — productor-consumidor
        // ================================================================
        System.out.println("\n========== F. BlockingQueue (Productor-Consumidor) ==========");

        BlockingQueue<String> cola = new LinkedBlockingQueue<>(5); // capacidad 5
        AtomicBoolean produciendo = new AtomicBoolean(true);

        Thread productor = new Thread(() -> {
            try {
                for (int i = 1; i <= 8; i++) {
                    String item = "Item-" + i;
                    cola.put(item); // bloquea si la cola está llena
                    System.out.println("Producido: " + item + " (cola: " + cola.size() + ")");
                    Thread.sleep(100);
                }
                produciendo.set(false);
                cola.put("FIN"); // señal de terminación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Productor");

        Thread consumidor = new Thread(() -> {
            try {
                while (true) {
                    String item = cola.take(); // bloquea si la cola está vacía
                    if ("FIN".equals(item)) break;
                    System.out.println("  Consumido: " + item);
                    Thread.sleep(180); // consume más lento que produce
                }
                System.out.println("  Consumidor terminó");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumidor");

        productor.start();
        consumidor.start();
        productor.join();
        consumidor.join();

        // Otros tipos de BlockingQueue
        System.out.println("\nTipos de BlockingQueue:");
        System.out.println("  LinkedBlockingQueue  → ilimitada/limitada, enlazada");
        System.out.println("  ArrayBlockingQueue   → tamaño fijo, basada en array");
        System.out.println("  PriorityBlockingQueue→ con prioridad");
        System.out.println("  SynchronousQueue     → capacidad 0 (entrega directa)");
        System.out.println("  DelayQueue           → elementos con tiempo de espera");

        // ================================================================
        // G. CONCURRENTHASHMAP — mapa thread-safe eficiente
        // ================================================================
        System.out.println("\n========== G. ConcurrentHashMap ==========");

        ConcurrentHashMap<String, AtomicInteger> frecuencias = new ConcurrentHashMap<>();
        String[] palabras = {"java", "python", "java", "go", "python", "java", "rust"};

        ExecutorService mapExec = Executors.newFixedThreadPool(4);
        for (String p : palabras) {
            mapExec.submit(() -> {
                frecuencias.computeIfAbsent(p, k -> new AtomicInteger(0))
                           .incrementAndGet();
            });
        }
        mapExec.shutdown();
        mapExec.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("Frecuencias: " + frecuencias);

        // merge — thread-safe con ConcurrentHashMap
        ConcurrentHashMap<String, Integer> mapa = new ConcurrentHashMap<>();
        String[] colores = {"rojo", "azul", "rojo", "verde", "azul", "rojo"};
        for (String c : colores) {
            mapa.merge(c, 1, Integer::sum); // thread-safe
        }
        System.out.println("Merge colores: " + mapa);

        // ================================================================
        // H. PATRÓN THREAD LOCAL
        // ================================================================
        System.out.println("\n========== H. ThreadLocal ==========");

        // ThreadLocal: cada hilo tiene su propia copia de la variable
        ThreadLocal<String> nombreHilo = ThreadLocal.withInitial(() -> "sin-nombre");

        Thread tl1 = new Thread(() -> {
            nombreHilo.set("Hilo-TL-1");
            System.out.println(Thread.currentThread().getName()
                    + " tiene: " + nombreHilo.get());
            nombreHilo.remove(); // limpiar para evitar memory leaks
        }, "TL1");

        Thread tl2 = new Thread(() -> {
            nombreHilo.set("Hilo-TL-2");
            System.out.println(Thread.currentThread().getName()
                    + " tiene: " + nombreHilo.get());
            nombreHilo.remove();
        }, "TL2");

        tl1.start(); tl2.start();
        tl1.join();  tl2.join();

        System.out.println("Main tiene: " + nombreHilo.get()); // "sin-nombre" (valor inicial)

        // Uso típico de ThreadLocal: SimpleDateFormat (no thread-safe)
        ThreadLocal<java.text.SimpleDateFormat> sdfLocal =
            ThreadLocal.withInitial(() -> new java.text.SimpleDateFormat("dd/MM/yyyy"));
        // Cada hilo usa su propio SDF, sin sincronización necesaria

        System.out.println("\nMain termina");
    }
}

// ====================================================================
// CLASES DE EJEMPLO
// ====================================================================

class ContadorConLock {
    private int valor = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void incrementar() {
        lock.lock();
        try {
            valor++;
        } finally {
            lock.unlock(); // SIEMPRE en finally para garantizar liberación
        }
    }

    public int getValor() {
        lock.lock();
        try {
            return valor;
        } finally {
            lock.unlock();
        }
    }
}

class Cache2 {
    private final Map<String, String> datos = new HashMap<>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock  = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public String leer(String clave) {
        readLock.lock(); // múltiples hilos pueden leer simultáneamente
        try {
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            return datos.getOrDefault(clave, "no-encontrado");
        } finally {
            readLock.unlock();
        }
    }

    public void escribir(String clave, String valor) {
        writeLock.lock(); // solo un hilo puede escribir
        try {
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            datos.put(clave, valor);
        } finally {
            writeLock.unlock();
        }
    }
}
