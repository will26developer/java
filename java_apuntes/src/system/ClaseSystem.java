package system;

/**
 * CLASE SYSTEM — REFERENCIA COMPLETA
 * =====================================
 * java.lang.System es una clase final con solo miembros estáticos.
 * No se puede instanciar ni extender.
 *
 * CAMPOS ESTÁTICOS:
 *   System.in   → InputStream  (entrada estándar, normalmente teclado)
 *   System.out  → PrintStream  (salida estándar, normalmente consola)
 *   System.err  → PrintStream  (salida de error estándar)
 *
 * MÉTODOS PRINCIPALES:
 *   currentTimeMillis() / nanoTime()    → tiempo
 *   arraycopy()                         → copiar arrays
 *   getenv() / getProperty()            → entorno y propiedades
 *   exit()                              → terminar JVM
 *   gc()                                → sugerir garbage collection
 *   identityHashCode()                  → hash de referencia
 *   lineSeparator()                     → separador de línea del SO
 */
public class ClaseSystem {

    public static void main(String[] args) {

        // ================================================================
        // A. System.out — salida estándar (PrintStream)
        // ================================================================
        System.out.println("========== A. System.out ==========");

        // println() — imprime y añade salto de línea
        System.out.println("println: con salto de línea");

        // print() — imprime SIN salto de línea
        System.out.print("print: sin salto  ");
        System.out.print("→ continúa en la misma línea");
        System.out.println(); // salto manual

        // printf() — formato estilo C
        System.out.printf("printf: nombre=%-10s edad=%3d precio=%.2f%n", "William", 25, 9.99);

        // format() — idéntico a printf
        System.out.format("format: %d + %d = %d%n", 3, 4, 3 + 4);

        // Imprimir distintos tipos
        System.out.println(42);
        System.out.println(3.14);
        System.out.println(true);
        System.out.println('A');
        System.out.println(new int[]{1, 2, 3}); // imprime referencia, no contenido
        System.out.println(java.util.Arrays.toString(new int[]{1, 2, 3})); // contenido

        // ================================================================
        // B. System.err — salida de error estándar (PrintStream)
        // ================================================================
        System.out.println("\n========== B. System.err ==========");

        // Funciona igual que System.out pero va al canal de error
        // En consola suele verse en rojo en algunos IDEs
        System.err.println("Este es un mensaje de ERROR");
        System.err.printf("Error en línea %d: %s%n", 42, "valor nulo");

        // Redirigir err a out (para sincronizar el orden en consola)
        System.setErr(System.out); // ahora err escribe en out
        System.err.println("err redirigido a out");

        // ================================================================
        // C. System.in — entrada estándar (InputStream)
        // ================================================================
        System.out.println("\n========== C. System.in ==========");
        // System.in es un InputStream de bajo nivel
        // Normalmente se envuelve en Scanner o BufferedReader para leer

        // Ejemplo con Scanner (no se ejecuta interactivamente aquí)
        System.out.println("Uso habitual de System.in:");
        System.out.println("  Scanner sc = new Scanner(System.in);");
        System.out.println("  String linea = sc.nextLine();");
        System.out.println("  int numero   = sc.nextInt();");

        // Comprobar bytes disponibles sin bloquear
        try {
            System.out.println("Bytes disponibles en System.in: " + System.in.available());
        } catch (Exception e) {
            System.out.println("No hay entrada disponible");
        }

        // ================================================================
        // D. currentTimeMillis() y nanoTime() — medición de tiempo
        // ================================================================
        System.out.println("\n========== D. Tiempo ==========");

        // currentTimeMillis() → ms desde el epoch (1 ene 1970 UTC)
        // Útil para timestamps y fechas
        long ms = System.currentTimeMillis();
        System.out.println("currentTimeMillis() : " + ms + " ms");
        System.out.println("En segundos         : " + ms / 1000);
        System.out.println("Aprox. año          : " + (1970 + ms / 1000 / 60 / 60 / 24 / 365));

        // nanoTime() → nanosegundos desde un origen arbitrario
        // Solo sirve para medir INTERVALOS, no para fechas absolutas
        long inicio = System.nanoTime();
        long suma = 0;
        for (int i = 0; i < 1_000_000; i++) suma += i;
        long fin = System.nanoTime();

        System.out.println("\nnanoTime() para medir rendimiento:");
        System.out.println("Suma 1M iteraciones : " + suma);
        System.out.println("Tiempo transcurrido : " + (fin - inicio) + " ns");
        System.out.println("En milisegundos     : " + (fin - inicio) / 1_000_000.0 + " ms");

        // Diferencia: currentTimeMillis vs nanoTime
        System.out.println("\nCurrentTimeMillis → para timestamps/fechas (resolución ~10ms)");
        System.out.println("nanoTime          → para benchmarks (alta resolución, no es fecha)");

        // ================================================================
        // E. arraycopy() — copiar arrays eficientemente
        // ================================================================
        System.out.println("\n========== E. arraycopy() ==========");
        // System.arraycopy(origen, posOrigen, destino, posDestino, longitud)
        // Es la forma más rápida de copiar arrays (nativo JVM)

        int[] origen  = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] destino = new int[10];

        // Copia completa
        System.arraycopy(origen, 0, destino, 0, origen.length);
        System.out.println("Copia completa     : " + java.util.Arrays.toString(destino));

        // Copia parcial — elementos 2,3,4 (índices 2..4) → destino desde índice 1
        int[] parcial = new int[5];
        System.arraycopy(origen, 2, parcial, 1, 3);
        System.out.println("Copia parcial      : " + java.util.Arrays.toString(parcial));
        // [0, 3, 4, 5, 0]

        // Desplazar elementos dentro del mismo array (shift left)
        int[] arr = {1, 2, 3, 4, 5};
        System.arraycopy(arr, 1, arr, 0, arr.length - 1);
        arr[arr.length - 1] = 0; // limpiar último
        System.out.println("Shift left         : " + java.util.Arrays.toString(arr));
        // [2, 3, 4, 5, 0]

        // Comparativa: arraycopy vs bucle for
        int tamaño = 1_000_000;
        int[] grande = new int[tamaño];
        int[] copiaFor = new int[tamaño];
        int[] copiaSystem = new int[tamaño];
        java.util.Arrays.fill(grande, 1);

        long t1 = System.nanoTime();
        for (int i = 0; i < tamaño; i++) copiaFor[i] = grande[i];
        long t2 = System.nanoTime();
        System.arraycopy(grande, 0, copiaSystem, 0, tamaño);
        long t3 = System.nanoTime();

        System.out.println("\nCopiar 1M elementos:");
        System.out.println("for loop           : " + (t2 - t1) / 1_000_000.0 + " ms");
        System.out.println("arraycopy          : " + (t3 - t2) / 1_000_000.0 + " ms");

        // ================================================================
        // F. Propiedades del sistema — getProperty()
        // ================================================================
        System.out.println("\n========== F. Propiedades del sistema ==========");

        // Propiedades estándar de la JVM
        System.out.println("java.version       : " + System.getProperty("java.version"));
        System.out.println("java.vendor        : " + System.getProperty("java.vendor"));
        System.out.println("java.home          : " + System.getProperty("java.home"));
        System.out.println("os.name            : " + System.getProperty("os.name"));
        System.out.println("os.arch            : " + System.getProperty("os.arch"));
        System.out.println("os.version         : " + System.getProperty("os.version"));
        System.out.println("user.name          : " + System.getProperty("user.name"));
        System.out.println("user.home          : " + System.getProperty("user.home"));
        System.out.println("user.dir           : " + System.getProperty("user.dir"));
        System.out.println("file.separator     : " + System.getProperty("file.separator"));
        System.out.println("path.separator     : " + System.getProperty("path.separator"));
        System.out.println("line.separator len : " + System.getProperty("line.separator").length());

        // Valor por defecto si la propiedad no existe
        String miProp = System.getProperty("mi.propiedad.custom", "valor_por_defecto");
        System.out.println("mi.propiedad       : " + miProp);

        // Establecer una propiedad en tiempo de ejecución
        System.setProperty("app.nombre", "MiApp");
        System.setProperty("app.version", "1.0.0");
        System.out.println("app.nombre         : " + System.getProperty("app.nombre"));
        System.out.println("app.version        : " + System.getProperty("app.version"));

        // Obtener TODAS las propiedades
        System.out.println("\nTotal propiedades  : " + System.getProperties().size());

        // ================================================================
        // G. Variables de entorno — getenv()
        // ================================================================
        System.out.println("\n========== G. Variables de entorno ==========");

        // Leer variable de entorno del sistema operativo
        String pathEnv  = System.getenv("PATH");
        String homeEnv  = System.getenv("HOME");   // Linux/Mac
        String userEnv  = System.getenv("USERNAME"); // Windows

        System.out.println("PATH (primeros 60) : " + (pathEnv != null ? pathEnv.substring(0, Math.min(60, pathEnv.length())) + "..." : "null"));
        System.out.println("HOME               : " + homeEnv);
        System.out.println("USERNAME           : " + userEnv);

        // Variable inexistente devuelve null
        String miVar = System.getenv("MI_VARIABLE_CUSTOM");
        System.out.println("MI_VARIABLE_CUSTOM : " + miVar); // null

        // Total de variables de entorno disponibles
        System.out.println("Total env vars     : " + System.getenv().size());

        // ================================================================
        // H. lineSeparator() — separador de línea del SO
        // ================================================================
        System.out.println("\n========== H. lineSeparator() ==========");
        String sep = System.lineSeparator();
        System.out.println("lineSeparator length : " + sep.length());
        // Windows: "\r\n" (2 chars), Linux/Mac: "\n" (1 char)
        System.out.println("Es Windows (\\r\\n)    : " + sep.equals("\r\n"));
        System.out.println("Es Unix (\\n)         : " + sep.equals("\n"));

        // Uso práctico: construir texto multiplataforma
        String texto = "Línea 1" + System.lineSeparator()
                + "Línea 2" + System.lineSeparator()
                + "Línea 3";
        System.out.println("Texto multiplataforma:\n" + texto);

        // ================================================================
        // I. identityHashCode() — hash de referencia del objeto
        // ================================================================
        System.out.println("\n========== I. identityHashCode() ==========");

        // Devuelve el hashCode original de Object, aunque el objeto
        // haya sobreescrito hashCode()
        String s1 = new String("Java");
        String s2 = new String("Java");

        System.out.println("s1.hashCode()              : " + s1.hashCode());    // igual (contenido)
        System.out.println("s2.hashCode()              : " + s2.hashCode());    // igual (contenido)
        System.out.println("identityHashCode(s1)       : " + System.identityHashCode(s1)); // diferente
        System.out.println("identityHashCode(s2)       : " + System.identityHashCode(s2)); // diferente
        System.out.println("¿Mismo hashCode()?         : " + (s1.hashCode() == s2.hashCode())); // true
        System.out.println("¿Mismo identityHashCode()? : " + (System.identityHashCode(s1) == System.identityHashCode(s2))); // casi siempre false

        // Útil para detectar si dos variables apuntan al mismo objeto
        String s3 = s1; // misma referencia
        System.out.println("\ns1 e s3 mismo objeto: " +
                (System.identityHashCode(s1) == System.identityHashCode(s3))); // true

        // ================================================================
        // J. gc() — sugerir Garbage Collection
        // ================================================================
        System.out.println("\n========== J. gc() ==========");
        System.out.println("System.gc() sugiere al GC que ejecute una recolección.");
        System.out.println("NO garantiza que se ejecute inmediatamente.");
        System.out.println("En producción raramente se usa; la JVM gestiona el GC mejor sola.");

        Runtime runtime = Runtime.getRuntime();
        long antesGC  = runtime.freeMemory();
        System.gc(); // sugerencia
        long despuesGC = runtime.freeMemory();
        System.out.println("Memoria libre antes gc  : " + antesGC / 1024 + " KB");
        System.out.println("Memoria libre después gc : " + despuesGC / 1024 + " KB");

        // ================================================================
        // K. Runtime — información de la JVM (relacionado con System)
        // ================================================================
        System.out.println("\n========== K. Runtime (relacionado) ==========");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Procesadores disponibles : " + rt.availableProcessors());
        System.out.println("Memoria total JVM        : " + rt.totalMemory() / 1024 / 1024 + " MB");
        System.out.println("Memoria libre JVM        : " + rt.freeMemory()  / 1024 / 1024 + " MB");
        System.out.println("Memoria máxima JVM       : " + rt.maxMemory()   / 1024 / 1024 + " MB");

        // ================================================================
        // L. System.exit() — terminar la JVM
        // ================================================================
        System.out.println("\n========== L. System.exit() ==========");
        System.out.println("System.exit(0)  → termina normalmente (código 0 = éxito)");
        System.out.println("System.exit(1)  → termina con error (código ≠ 0 = fallo)");
        System.out.println("Llama a los shutdown hooks antes de terminar.");
        System.out.println("Úsalo con cuidado: no libera recursos correctamente en entornos administrados.");
        System.out.println("(No se llama aquí para que el programa continúe)");

        // Ejemplo de cuándo usarlo:
        // if (archivoEsencial == null) {
        //     System.err.println("Error crítico: archivo no encontrado");
        //     System.exit(1);
        // }

        // ================================================================
        // RESUMEN
        // ================================================================
        System.out.println("\n========== RESUMEN System ==========");
        System.out.printf("%-30s %s%n", "System.out.println/print/printf", "Salida estándar");
        System.out.printf("%-30s %s%n", "System.err.println",              "Salida de error");
        System.out.printf("%-30s %s%n", "System.in",                       "Entrada estándar");
        System.out.printf("%-30s %s%n", "System.currentTimeMillis()",      "Timestamp en ms");
        System.out.printf("%-30s %s%n", "System.nanoTime()",               "Benchmark de precisión");
        System.out.printf("%-30s %s%n", "System.arraycopy()",              "Copia de arrays rápida");
        System.out.printf("%-30s %s%n", "System.getProperty()",            "Propiedades JVM");
        System.out.printf("%-30s %s%n", "System.setProperty()",            "Establecer propiedad");
        System.out.printf("%-30s %s%n", "System.getenv()",                 "Variables de entorno SO");
        System.out.printf("%-30s %s%n", "System.lineSeparator()",          "Salto de línea del SO");
        System.out.printf("%-30s %s%n", "System.identityHashCode()",       "Hash de referencia");
        System.out.printf("%-30s %s%n", "System.gc()",                     "Sugerir GC");
        System.out.printf("%-30s %s%n", "System.exit(código)",             "Terminar JVM");
    }
}