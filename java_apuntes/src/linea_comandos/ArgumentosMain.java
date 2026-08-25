package linea_comandos;

/**
 * LÍNEA DE COMANDOS — args[] EN JAVA
 * =====================================
 * Cuando ejecutas un programa Java desde la terminal, puedes pasarle
 * argumentos que el método main recibe como String[].
 *
 * COMPILAR Y EJECUTAR:
 *   javac ArgumentosMain.java
 *   java ArgumentosMain hola 42 3.14 true
 *
 * El array args[] contiene:
 *   args[0] = "hola"
 *   args[1] = "42"
 *   args[2] = "3.14"
 *   args[3] = "true"
 *
 * IMPORTANTE:
 *   - Todos los argumentos llegan como String, hay que convertirlos.
 *   - Si no se pasan argumentos, args.length == 0 (no es null).
 *   - Los argumentos con espacios deben ir entre comillas: "hola mundo"
 */
public class ArgumentosMain {

    public static void main(String[] args) {

        // ================================================================
        // A. INFORMACIÓN BÁSICA DEL ARRAY args
        // ================================================================
        System.out.println("========== A. Información básica ==========");
        System.out.println("Número de argumentos: " + args.length);

        if (args.length == 0) {
            System.out.println("No se pasaron argumentos.");
            System.out.println("Prueba: java ArgumentosMain Juan 25 1.75 true");
        } else {
            System.out.println("Argumentos recibidos:");
            for (int i = 0; i < args.length; i++) {
                System.out.printf("  args[%d] = \"%s\"%n", i, args[i]);
            }
        }

        // ================================================================
        // B. SIMULAR ARGUMENTOS PARA LOS EJEMPLOS
        // ================================================================
        // Como este fichero se puede ejecutar sin argumentos, simulamos
        // un array para demostrar todas las operaciones.
        String[] simulados = {"William", "25", "1.75", "true", "Madrid"};
        System.out.println("\n========== B. Trabajando con argumentos ==========");
        System.out.println("(Usando argumentos simulados: William 25 1.75 true Madrid)");

        // ================================================================
        // C. CONVERTIR ARGUMENTOS AL TIPO CORRECTO
        //    Todos llegan como String — hay que parsearlos
        // ================================================================
        System.out.println("\n========== C. Conversión de tipos ==========");

        String nombre  =         simulados[0];           // String directo
        int    edad    = Integer.parseInt(simulados[1]);  // String → int
        double altura  = Double.parseDouble(simulados[2]);// String → double
        boolean activo = Boolean.parseBoolean(simulados[3]); // String → boolean
        String ciudad  =         simulados[4];

        System.out.println("nombre  (String)  : " + nombre);
        System.out.println("edad    (int)     : " + edad);
        System.out.println("altura  (double)  : " + altura);
        System.out.println("activo  (boolean) : " + activo);
        System.out.println("ciudad  (String)  : " + ciudad);

        // ================================================================
        // D. VALIDACIÓN ROBUSTA DE ARGUMENTOS
        // ================================================================
        System.out.println("\n========== D. Validación ==========");

        // Número mínimo de argumentos
        int MIN_ARGS = 2;
        String[] test1 = {"Juan"};            // insuficientes
        String[] test2 = {"María", "30"};     // suficientes
        String[] test3 = {"Carlos", "abc"};   // tipo incorrecto

        System.out.println("test1 (1 arg)     : " + validarArgs(test1, MIN_ARGS));
        System.out.println("test2 (2 args)    : " + validarArgs(test2, MIN_ARGS));

        // Parseo seguro con try-catch
        System.out.println("\n--- Parseo seguro ---");
        System.out.println("Parsear \"30\"   → " + parsearEnteroSeguro("30",  -1));
        System.out.println("Parsear \"abc\"  → " + parsearEnteroSeguro("abc", -1));
        System.out.println("Parsear \"\"     → " + parsearEnteroSeguro("",    -1));
        System.out.println("Parsear null    → " + parsearEnteroSeguro(null,   -1));

        // ================================================================
        // E. PATRÓN: ARGUMENTOS CON NOMBRE (--clave=valor o --clave valor)
        // ================================================================
        System.out.println("\n========== E. Argumentos con nombre ==========");

        // Simular: java App --nombre=William --edad=25 --verbose
        String[] namedArgs = {"--nombre=William", "--edad=25", "--ciudad=Madrid", "--verbose"};
        java.util.Map<String, String> params = parsearArgumentosNombrados(namedArgs);

        System.out.println("Mapa de argumentos:");
        params.forEach((k, v) -> System.out.println("  " + k + " = " + v));

        String nombreParam = params.getOrDefault("nombre", "Anónimo");
        int    edadParam   = parsearEnteroSeguro(params.get("edad"), 0);
        boolean verbose    = params.containsKey("verbose");

        System.out.println("\nnombre  : " + nombreParam);
        System.out.println("edad    : " + edadParam);
        System.out.println("verbose : " + verbose);
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static String validarArgs(String[] args, int minRequeridos) {
        if (args.length < minRequeridos) {
            return "ERROR: se necesitan al menos " + minRequeridos
                    + " argumentos, recibidos " + args.length;
        }
        return "OK";
    }

    static int parsearEnteroSeguro(String valor, int defecto) {
        if (valor == null || valor.isBlank()) return defecto;
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            return defecto;
        }
    }

    static java.util.Map<String, String> parsearArgumentosNombrados(String[] args) {
        java.util.Map<String, String> mapa = new java.util.LinkedHashMap<>();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                String sinPrefijo = arg.substring(2);
                if (sinPrefijo.contains("=")) {
                    String[] partes = sinPrefijo.split("=", 2);
                    mapa.put(partes[0], partes[1]);
                } else {
                    mapa.put(sinPrefijo, "true"); // flag sin valor
                }
            }
        }
        return mapa;
    }
}