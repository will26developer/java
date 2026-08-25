package thread_concurrencia;
/**
 * EXECUTOR SERVICE — GESTIÓN AVANZADA DE HILOS
 * ==============================================
 * ExecutorService gestiona un pool de hilos reutilizables.
 * Evita crear/destruir hilos manualmente (costoso).
 *
 * IMPLEMENTACIONES:
 *   FixedThreadPool     → número fijo de hilos
 *   CachedThreadPool    → crea hilos según demanda, reutiliza ociosos
 *   SingleThreadExecutor→ un solo hilo, tareas en cola
 *   ScheduledThreadPool → tareas periódicas o con retraso
 *   WorkStealingPool    → basado en ForkJoinPool (Java 8+)
 *
 * CALLABLE vs RUNNABLE:
 *   Runnable → void run()      → sin valor de retorno, sin checked exceptions
 *   Callable → V call()        → con valor de retorno, puede lanzar Exception
 *
 * FUTURE:
 *   Representa el resultado futuro de una tarea asíncrona
 *   future.get() → bloquea hasta obtener el resultado
 */
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class ExecutorServiceHilos {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // ================================================================
        // A. FIXED THREAD POOL
        // ================================================================
        System.out.println("========== A. FixedThreadPool ==========");

        ExecutorService pool = Executors.newFixedThreadPool(3);

        // submit(Runnable) — sin valor de retorno
        for (int i = 1; i <= 6; i++) {
            final int tarea = i;
            pool.submit(() -> {
                System.out.println("Tarea " + tarea + " en " + Thread.currentThread().getName());
                try { Thread.sleep(200); } catch (InterruptedException e) {}
                System.out.println("Tarea " + tarea + " completada");
            });
        }

        pool.shutdown(); // no acepta más tareas
        pool.awaitTermination(5, TimeUnit.SECONDS); // espera hasta 5s
        System.out.println("Pool terminado");

        // ================================================================
        // B. CALLABLE Y FUTURE
        // ================================================================
        System.out.println("\n========== B. Callable y Future ==========");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // submit(Callable) → devuelve Future<T>
        Future<Integer> future1 = executor.submit(() -> {
            Thread.sleep(300);
            return calcularFactorial(10);
        });

        Future<String> future2 = executor.submit(() -> {
            Thread.sleep(200);
            return "Resultado de tarea 2: " + (int)(Math.random() * 100);
        });

        Future<Double> future3 = executor.submit(() -> {
            Thread.sleep(100);
            return Math.PI * Math.pow(5, 2);
        });

        System.out.println("Tareas lanzadas, haciendo otras cosas...");
        Thread.sleep(50); // simula trabajo mientras esperamos

        // get() bloquea hasta que el resultado está disponible
        System.out.println("Factorial(10) = " + future1.get());
        System.out.println(future2.get());
        System.out.printf("Área círculo r=5: %.4f%n", future3.get());

        // isDone() — comprueba sin bloquear
        System.out.println("future1 done: " + future1.isDone());

        // get con timeout — evita bloqueo infinito
        Future<String> futureTimeout = executor.submit(() -> {
            Thread.sleep(5000); // tarda mucho
            return "Nunca llega";
        });

        try {
            String res = futureTimeout.get(500, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("Timeout: la tarea tardó demasiado");
            futureTimeout.cancel(true); // cancelar la tarea
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);

        // ================================================================
        // C. INVOKEALL — lanzar múltiples Callable y esperar todos
        // ================================================================
        System.out.println("\n========== C. invokeAll ==========");

        ExecutorService exec = Executors.newFixedThreadPool(4);

        List<Callable<Integer>> tareas = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            final int n = i;
            tareas.add(() -> {
                Thread.sleep((long)(Math.random() * 300));
                int resultado = n * n;
                System.out.println("  n=" + n + " → " + resultado);
                return resultado;
            });
        }

        // invokeAll espera a que TODAS las tareas terminen
        List<Future<Integer>> resultados = exec.invokeAll(tareas);

        int sumaTotal = 0;
        for (Future<Integer> f : resultados) {
            sumaTotal += f.get(); // ya están completos
        }
        System.out.println("Suma de cuadrados: " + sumaTotal);

        exec.shutdown();

        // ================================================================
        // D. INVOKEANY — la primera que termine
        // ================================================================
        System.out.println("\n========== D. invokeAny ==========");

        ExecutorService exec2 = Executors.newFixedThreadPool(3);

        List<Callable<String>> servidores = List.of(
            () -> { Thread.sleep(300); return "Servidor A respondió"; },
            () -> { Thread.sleep(100); return "Servidor B respondió"; }, // el más rápido
            () -> { Thread.sleep(500); return "Servidor C respondió"; }
        );

        // invokeAny devuelve el resultado de la PRIMERA que complete
        String primeraRespuesta = exec2.invokeAny(servidores);
        System.out.println("Primera respuesta: " + primeraRespuesta);
        exec2.shutdown();

        // ================================================================
        // E. SCHEDULED EXECUTOR — tareas periódicas
        // ================================================================
        System.out.println("\n========== E. ScheduledExecutorService ==========");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // schedule — ejecutar una vez con retraso
        ScheduledFuture<String> scheduledFuture = scheduler.schedule(
            () -> "Tarea retrasada ejecutada",
            500, TimeUnit.MILLISECONDS
        );
        System.out.println("Esperando tarea retrasada...");
        System.out.println(scheduledFuture.get());

        // scheduleAtFixedRate — ejecutar periódicamente (tasa fija)
        AtomicInteger contador = new AtomicInteger(0);
        ScheduledFuture<?> periodic = scheduler.scheduleAtFixedRate(
            () -> System.out.println("  Periódico #" + contador.incrementAndGet()
                    + " en " + Thread.currentThread().getName()),
            0,    // retraso inicial
            200,  // período
            TimeUnit.MILLISECONDS
        );

        Thread.sleep(700); // dejar que se ejecute varias veces
        periodic.cancel(false); // cancelar sin interrumpir si está ejecutándose

        // scheduleWithFixedDelay — espera entre fin de una y comienzo de siguiente
        AtomicInteger contador2 = new AtomicInteger(0);
        ScheduledFuture<?> conDelay = scheduler.scheduleWithFixedDelay(
            () -> {
                System.out.println("  WithDelay #" + contador2.incrementAndGet());
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            },
            0, 150, TimeUnit.MILLISECONDS
        );

        Thread.sleep(600);
        conDelay.cancel(false);

        scheduler.shutdown();
        scheduler.awaitTermination(1, TimeUnit.SECONDS);

        // ================================================================
        // F. COMPLETABLEFUTURE (Java 8+) — composición asíncrona
        // ================================================================
        System.out.println("\n========== F. CompletableFuture ==========");

        // runAsync — sin valor de retorno
        CompletableFuture<Void> async1 = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync en: " + Thread.currentThread().getName());
        });

        // supplyAsync — con valor de retorno
        CompletableFuture<Integer> async2 = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(200); } catch (InterruptedException e) {}
            return 42;
        });

        // thenApply — transformar el resultado (como map en Stream)
        CompletableFuture<String> async3 = async2
                .thenApply(n -> n * 2)
                .thenApply(n -> "Resultado: " + n);

        System.out.println(async3.get()); // "Resultado: 84"

        // thenCombine — combinar dos CompletableFutures
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> { 
            try { Thread.sleep(100); } catch (InterruptedException e) {} return 10; 
        });
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(150); } catch (InterruptedException e) {} return 20;
        });
        CompletableFuture<Integer> suma = a.thenCombine(b, Integer::sum);
        System.out.println("thenCombine(10+20): " + suma.get());

        // allOf — esperar que todos terminen
        CompletableFuture<Void> todos = CompletableFuture.allOf(
            CompletableFuture.supplyAsync(() -> "tarea1"),
            CompletableFuture.supplyAsync(() -> "tarea2"),
            CompletableFuture.supplyAsync(() -> "tarea3")
        );
        todos.join();
        System.out.println("Todos completados");

        // anyOf — el primero que termine
        CompletableFuture<Object> primero = CompletableFuture.anyOf(
            CompletableFuture.supplyAsync(() -> { try{Thread.sleep(300);}catch(Exception e){} return "lento"; }),
            CompletableFuture.supplyAsync(() -> { try{Thread.sleep(100);}catch(Exception e){} return "rápido"; }),
            CompletableFuture.supplyAsync(() -> { try{Thread.sleep(200);}catch(Exception e){} return "medio"; })
        );
        System.out.println("anyOf: " + primero.get());

        // Manejo de errores en CompletableFuture
        CompletableFuture<Integer> conError = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("¡Error asíncrono!");
        });

        String resultadoFinal = conError
                .thenApply(n -> "OK: " + n)
                .exceptionally(ex -> "Error capturado: " + ex.getMessage())
                .get();
        System.out.println("exceptionally: " + resultadoFinal);

        // handle — manejar éxito y error en un solo bloque
        CompletableFuture<String> conHandle = CompletableFuture
                .supplyAsync(() -> { throw new RuntimeException("fallo"); })
                .handle((resultado, error) -> {
                    if (error != null) return "Handle error: " + error.getMessage();
                    return "Handle ok: " + resultado;
                });
        System.out.println(conHandle.get());

        async1.join();
        System.out.println("\nMain termina");
    }

    static int calcularFactorial(int n) throws InterruptedException {
        Thread.sleep(100); // simula cálculo costoso
        int resultado = 1;
        for (int i = 2; i <= n; i++) resultado *= i;
        return resultado;
    }
}
