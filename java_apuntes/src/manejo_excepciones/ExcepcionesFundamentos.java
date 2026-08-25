package manejo_excepciones;
/**
 * MANEJO DE EXCEPCIONES — FUNDAMENTOS
 * ======================================
 * Una excepción es un evento que interrumpe el flujo normal del programa.
 * Java usa un sistema de excepciones basado en objetos.
 *
 * JERARQUÍA:
 *   Throwable
 *   ├── Error          → problemas graves de la JVM (no manejar)
 *   │   ├── OutOfMemoryError
 *   │   ├── StackOverflowError
 *   │   └── ...
 *   └── Exception      → problemas recuperables
 *       ├── RuntimeException   → UNCHECKED (no obligatorio capturar)
 *       │   ├── NullPointerException
 *       │   ├── ArrayIndexOutOfBoundsException
 *       │   ├── ClassCastException
 *       │   ├── ArithmeticException
 *       │   ├── NumberFormatException
 *       │   └── IllegalArgumentException
 *       └── (otras)            → CHECKED (obligatorio capturar o declarar)
 *           ├── IOException
 *           ├── SQLException
 *           └── ParseException
 *
 * CHECKED vs UNCHECKED:
 *   Checked   → el compilador obliga a manejarlas (try-catch o throws)
 *   Unchecked → son RuntimeException, opcionales de capturar
 */
public class ExcepcionesFundamentos {

    public static void main(String[] args) {

        // ================================================================
        // A. SIN MANEJO — el programa termina abruptamente
        // ================================================================
        System.out.println("========== A. Excepciones comunes ==========");

        // NullPointerException
        try {
            String s = null;
            s.length(); // NPE
        } catch (NullPointerException e) {
            System.out.println("NPE: " + e.getMessage());
        }

        // ArrayIndexOutOfBoundsException
        try {
            int[] arr = {1, 2, 3};
            int x = arr[10];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("AIOOBE: " + e.getMessage());
        }

        // ArithmeticException
        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("AE: " + e.getMessage()); // / by zero
        }

        // NumberFormatException
        try {
            int n = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("NFE: " + e.getMessage());
        }

        // ClassCastException
        try {
            Object obj = "Hola";
            Integer i = (Integer) obj;
        } catch (ClassCastException e) {
            System.out.println("CCE: " + e.getMessage());
        }

        // StackOverflowError
        try {
            recursionInfinita();
        } catch (StackOverflowError e) {
            System.out.println("StackOverflow capturado");
        }

        // ================================================================
        // B. ESTRUCTURA TRY-CATCH
        // ================================================================
        System.out.println("\n========== B. try-catch ==========");

        // Estructura básica
        try {
            System.out.println("Antes del error");
            int resultado = dividir(10, 0);
            System.out.println("Nunca llega aquí: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Capturado: " + e.getMessage());
        }
        System.out.println("Ejecución continúa");

        // ================================================================
        // C. MÚLTIPLES CATCH
        // ================================================================
        System.out.println("\n========== C. Múltiples catch ==========");

        String[] inputs = {"42", "abc", null, "100"};
        for (String input : inputs) {
            try {
                int valor = Integer.parseInt(input); // NFE si no es número
                int result = 100 / valor;             // AE si es cero
                System.out.println("Resultado: " + result);
            } catch (NumberFormatException e) {
                System.out.println("No es un número: " + input);
            } catch (ArithmeticException e) {
                System.out.println("División por cero");
            } catch (NullPointerException e) {
                System.out.println("Valor nulo");
            }
        }

        // ORDEN IMPORTA: capturar subclases ANTES que superclases
        try {
            int[] arr = new int[5];
            arr[10] = 1;
        } catch (ArrayIndexOutOfBoundsException e) { // subclase primero
            System.out.println("AIOOBE específico: " + e.getMessage());
        } catch (RuntimeException e) {               // superclase después
            System.out.println("RuntimeException genérico");
        }

        // ================================================================
        // D. MULTI-CATCH (Java 7+) — un catch para varias excepciones
        // ================================================================
        System.out.println("\n========== D. Multi-catch ==========");

        String[] tests = {"abc", null, "42"};
        for (String t : tests) {
            try {
                int n = Integer.parseInt(t.trim()); // NFE o NPE
                System.out.println("Parseo OK: " + n);
            } catch (NumberFormatException | NullPointerException e) {
                // Un solo bloque para ambas
                System.out.println("Error con '" + t + "': " + e.getClass().getSimpleName());
            }
        }

        // ================================================================
        // E. BLOQUE finally
        //    Se ejecuta SIEMPRE: tanto si hay excepción como si no
        // ================================================================
        System.out.println("\n========== E. finally ==========");

        // Caso sin excepción
        System.out.println("--- Sin excepción ---");
        try {
            System.out.println("try: operación OK");
        } catch (Exception e) {
            System.out.println("catch: no se ejecuta");
        } finally {
            System.out.println("finally: SIEMPRE se ejecuta");
        }

        // Caso con excepción
        System.out.println("--- Con excepción ---");
        try {
            System.out.println("try: antes del error");
            int x = 1 / 0;
            System.out.println("try: nunca llega aquí");
        } catch (ArithmeticException e) {
            System.out.println("catch: capturado");
        } finally {
            System.out.println("finally: SIEMPRE se ejecuta");
        }

        // finally y return — finally se ejecuta ANTES del return
        System.out.println("Retorno con finally: " + metodoConReturn());

        // ================================================================
        // F. INFORMACIÓN DE LA EXCEPCIÓN
        // ================================================================
        System.out.println("\n========== F. Información de la excepción ==========");

        try {
            lanzarConInfo();
        } catch (Exception e) {
            System.out.println("getMessage()     : " + e.getMessage());
            System.out.println("getClass()       : " + e.getClass().getName());
            System.out.println("getClass simple  : " + e.getClass().getSimpleName());
            System.out.println("toString()       : " + e.toString());

            // Stack trace completo
            System.out.println("\nStack trace (primeras 3 líneas):");
            StackTraceElement[] stack = e.getStackTrace();
            for (int i = 0; i < Math.min(3, stack.length); i++) {
                System.out.println("  " + stack[i]);
            }

            // Causa raíz (chained exception)
            if (e.getCause() != null) {
                System.out.println("Causa raíz: " + e.getCause().getMessage());
            }
        }

        // ================================================================
        // G. EXCEPCIÓN ENCADENADA (Chained Exception)
        // ================================================================
        System.out.println("\n========== G. Excepciones encadenadas ==========");

        try {
            operacionDeNegocio();
        } catch (Exception e) {
            System.out.println("Excepción: " + e.getMessage());
            System.out.println("Causa    : " + e.getCause().getMessage());
            System.out.println("Causa raíz: " + e.getCause().getCause().getMessage());
        }

        // ================================================================
        // H. RELANZAR EXCEPCIONES (rethrow)
        // ================================================================
        System.out.println("\n========== H. Relanzar excepciones ==========");

        try {
            procesarArchivo("datos.txt");
        } catch (Exception e) {
            System.out.println("Capturado al relanzar: " + e.getClass().getSimpleName()
                    + " - " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static int dividir(int a, int b) {
        return a / b;
    }

    static void recursionInfinita() {
        recursionInfinita(); // StackOverflowError
    }

    static int metodoConReturn() {
        try {
            return 1;
        } finally {
            System.out.println("  finally antes del return");
            // return 2; // esto sobreescribiría el return del try (evitar)
        }
    }

    static void lanzarConInfo() throws Exception {
        try {
            throw new ArithmeticException("División inválida");
        } catch (ArithmeticException e) {
            throw new Exception("Error en cálculo", e); // encadenar
        }
    }

    static void operacionDeNegocio() throws Exception {
        try {
            accesoBaseDatos();
        } catch (Exception e) {
            throw new Exception("Error en operación de negocio", e);
        }
    }

    static void accesoBaseDatos() throws Exception {
        try {
            ejecutarSQL();
        } catch (Exception e) {
            throw new Exception("Error de acceso a BD", e);
        }
    }

    static void ejecutarSQL() throws Exception {
        throw new Exception("Conexión rechazada por el servidor");
    }

    // throws declara que puede lanzar una excepción checked
    static void procesarArchivo(String nombre) throws Exception {
        try {
            // Simula un error de IO
            if (nombre.endsWith(".txt")) {
                throw new java.io.IOException("Archivo no encontrado: " + nombre);
            }
        } catch (java.io.IOException e) {
            // Relanzar como excepción de mayor nivel
            throw new Exception("Error procesando archivo: " + e.getMessage(), e);
        }
    }
}
