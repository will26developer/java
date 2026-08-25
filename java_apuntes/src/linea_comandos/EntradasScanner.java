package linea_comandos;

/**
 * SCANNER — LEER ENTRADA DEL USUARIO
 * =====================================
 * java.util.Scanner es la forma estándar de leer datos desde:
 *   - Consola (System.in)
 *   - String
 *   - Archivo
 *
 * COMPILAR Y EJECUTAR:
 *   javac EntradaScanner.java
 *   java EntradaScanner
 *
 * MÉTODOS PRINCIPALES:
 *   nextLine()    → lee una línea completa (String)
 *   next()        → lee la siguiente palabra (String)
 *   nextInt()     → lee un int
 *   nextDouble()  → lee un double
 *   nextBoolean() → lee un boolean
 *   nextLong()    → lee un long
 *   hasNext()     → comprueba si hay más tokens
 *   hasNextInt()  → comprueba si el siguiente token es int
 *   close()       → cierra el scanner (libera recursos)
 */
import java.util.Scanner;

public class EntradasScanner {

    public static void main(String[] args) {

        // ================================================================
        // A. CREAR UN SCANNER
        // ================================================================
        System.out.println("========== A. Crear Scanner ==========");

        // Desde consola (System.in)
        Scanner sc = new Scanner(System.in);

        // Desde un String (muy útil para pruebas sin consola)
        Scanner scTexto = new Scanner("Hola 42 3.14 true");

        // Desde un archivo (ver sección F)
        // Scanner scArchivo = new Scanner(new File("datos.txt"));

        // ================================================================
        // B. LEER DESDE STRING (demostración sin necesidad de consola)
        // ================================================================
        System.out.println("\n========== B. Leer desde String ==========");
        Scanner demo = new Scanner("William 25 1.80 true");

        String nombre  = demo.next();           // lee palabra
        int    edad    = demo.nextInt();         // lee int
        double altura  = demo.nextDouble();      // lee double
        boolean activo = demo.nextBoolean();     // lee boolean

        System.out.println("nombre  : " + nombre);
        System.out.println("edad    : " + edad);
        System.out.println("altura  : " + altura);
        System.out.println("activo  : " + activo);
        demo.close();

        // ================================================================
        // C. DIFERENCIA next() vs nextLine()
        // ================================================================
        System.out.println("\n========== C. next() vs nextLine() ==========");

        Scanner demo2 = new Scanner("Hola Mundo\nSegunda línea completa\n42");

        System.out.println("next()     : " + demo2.next());     // "Hola"
        System.out.println("next()     : " + demo2.next());     // "Mundo"
        demo2.nextLine();   // consume el \n que queda al final de "Mundo\n"
        System.out.println("nextLine() : " + demo2.nextLine()); // "Segunda línea completa"
        System.out.println("nextInt()  : " + demo2.nextInt());  // 42
        demo2.close();

        // TRAMPA COMÚN: mezclar nextInt() con nextLine()
        System.out.println("\n--- Trampa: nextInt() + nextLine() ---");
        Scanner demo3 = new Scanner("25\nWilliam");
        int num = demo3.nextInt();
        // nextInt() lee "25" pero NO consume el \n
        // La siguiente nextLine() leerá "" (la línea vacía que quedó)
        demo3.nextLine(); // ← consumir el \n sobrante ANTES de nextLine()
        String texto = demo3.nextLine(); // ahora sí lee "William"
        System.out.println("num    : " + num);
        System.out.println("texto  : " + texto);
        demo3.close();

        // ================================================================
        // D. hasNext() — comprobar antes de leer
        // ================================================================
        System.out.println("\n========== D. hasNext() ==========");
        Scanner demo4 = new Scanner("1 dos 3 cuatro 5");

        while (demo4.hasNext()) {
            if (demo4.hasNextInt()) {
                System.out.println("Número  : " + demo4.nextInt());
            } else {
                System.out.println("Texto   : " + demo4.next());
            }
        }
        demo4.close();

        // ================================================================
        // E. CAMBIAR DELIMITADOR
        //    Por defecto el delimitador es cualquier espacio en blanco.
        //    Se puede cambiar con useDelimiter(String regex)
        // ================================================================
        System.out.println("\n========== E. Delimitadores ==========");

        // Leer CSV separado por comas
        Scanner csv = new Scanner("Java,Python,TypeScript,Go");
        csv.useDelimiter(",");
        System.out.print("CSV: ");
        while (csv.hasNext()) {
            System.out.print("[" + csv.next() + "] ");
        }
        System.out.println();
        csv.close();

        // Leer separado por punto y coma y espacios
        Scanner semicolon = new Scanner("10; 20; 30; 40");
        semicolon.useDelimiter("\\s*;\\s*"); // ; con espacios opcionales
        System.out.print("Semcol: ");
        while (semicolon.hasNextInt()) {
            System.out.print(semicolon.nextInt() + " ");
        }
        System.out.println();
        semicolon.close();

        // ================================================================
        // F. PATRÓN: MENÚ INTERACTIVO
        //    (comentado para no bloquear la ejecución)
        // ================================================================
        System.out.println("\n========== F. Patrón menú interactivo ==========");
        System.out.println("Ejemplo de menú interactivo (código comentado):");
        System.out.println("""
            Scanner sc = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("1. Nueva tarea");
                System.out.println("2. Ver tareas");
                System.out.println("0. Salir");
                System.out.print("Elige: ");
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                switch (opcion) {
                    case 1 -> { System.out.print("Nombre: "); String t = sc.nextLine(); }
                    case 2 -> System.out.println("Lista...");
                    case 0 -> System.out.println("Hasta luego");
                    default -> System.out.println("Opción inválida");
                }
            } while (opcion != 0);
            sc.close();
            """);

        // ================================================================
        // G. VALIDACIÓN DE ENTRADA EN CONSOLA
        //    (patrón para pedir un int hasta que el usuario lo dé bien)
        // ================================================================
        System.out.println("========== G. Patrón validación de entrada ==========");
        System.out.println("Ejemplo de validación (código comentado):");
        System.out.println("""
            Scanner sc = new Scanner(System.in);
            int edad = -1;
            while (edad < 0 || edad > 120) {
                System.out.print("Introduce tu edad (0-120): ");
                if (sc.hasNextInt()) {
                    edad = sc.nextInt();
                    if (edad < 0 || edad > 120) {
                        System.out.println("Edad fuera de rango.");
                    }
                } else {
                    System.out.println("Eso no es un número.");
                    sc.next(); // descartar token inválido
                }
            }
            System.out.println("Edad válida: " + edad);
            """);

        // ================================================================
        // H. LEER LÍNEAS MÚLTIPLES HASTA FIN (EOF)
        // ================================================================
        System.out.println("========== H. Leer hasta EOF ==========");
        Scanner demo5 = new Scanner("línea 1\nlínea 2\nlínea 3");
        int lineaNum = 1;
        while (demo5.hasNextLine()) {
            System.out.println("  " + lineaNum++ + ": " + demo5.nextLine());
        }
        demo5.close();

        // ================================================================
        // I. LOCALE — leer decimales con coma o punto
        // ================================================================
        System.out.println("\n========== I. Locale en Scanner ==========");

        // En algunos sistemas el decimal es coma (Europa), en otros punto (US)
        // Scanner usa el Locale por defecto del sistema

        // Forzar punto decimal (Locale.US)
        Scanner puntoDecimal = new Scanner("3.14 2.71").useLocale(java.util.Locale.US);
        System.out.println("Con Locale.US (punto): " + puntoDecimal.nextDouble());
        System.out.println("Con Locale.US (punto): " + puntoDecimal.nextDouble());
        puntoDecimal.close();

        // Forzar coma decimal (Locale español)
        Scanner comaDecimal = new Scanner("3,14 2,71")
                .useLocale(new java.util.Locale("es", "ES"));
        System.out.println("Con Locale.ES (coma) : " + comaDecimal.nextDouble());
        System.out.println("Con Locale.ES (coma) : " + comaDecimal.nextDouble());
        comaDecimal.close();

        // ================================================================
        // J. BUENAS PRÁCTICAS
        // ================================================================
        System.out.println("\n========== J. Buenas prácticas ==========");
        System.out.println("1. Cierra siempre el Scanner con close() o usa try-with-resources.");
        System.out.println("2. NO cierres Scanner(System.in) si vas a seguir leyendo.");
        System.out.println("3. Consume el \\n sobrante con nextLine() después de nextInt().");
        System.out.println("4. Usa hasNextInt() antes de nextInt() para no lanzar excepción.");
        System.out.println("5. Para producción considera BufferedReader, que es más rápido.");

        // Try-with-resources (cierre automático)
        System.out.println("\n--- Try-with-resources ---");
        try (Scanner autoclose = new Scanner("uno dos tres")) {
            while (autoclose.hasNext()) {
                System.out.print(autoclose.next() + " ");
            }
        } // se cierra automáticamente al salir del bloque
        System.out.println("\nScanner cerrado automáticamente.");

        // Cerrar el Scanner principal
        sc.close();
    }
}