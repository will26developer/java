package linea_comandos;

/**
 * FORMATO DE SALIDA EN CONSOLA
 * ==============================
 * Técnicas para mostrar información de forma clara y visual
 * en la terminal: printf, alineación, colores ANSI y tablas.
 *
 * NOTA COLORES ANSI:
 *   Funcionan en Linux, Mac y Windows Terminal moderno.
 *   En CMD de Windows antiguo puede que no funcionen.
 */
public class FormatoConsola {

    // ================================================================
    // CÓDIGOS DE COLOR ANSI
    // ================================================================
    static final String RESET   = "\033[0m";
    static final String NEGRITA = "\033[1m";
    static final String CURSIVA = "\033[3m";
    static final String SUBRAY  = "\033[4m";

    // Colores de texto
    static final String NEGRO   = "\033[30m";
    static final String ROJO    = "\033[31m";
    static final String VERDE   = "\033[32m";
    static final String AMARILLO= "\033[33m";
    static final String AZUL    = "\033[34m";
    static final String MAGENTA = "\033[35m";
    static final String CYAN    = "\033[36m";
    static final String BLANCO  = "\033[37m";

    // Colores brillantes
    static final String ROJO_B  = "\033[91m";
    static final String VERDE_B = "\033[92m";
    static final String AMAR_B  = "\033[93m";
    static final String AZUL_B  = "\033[94m";

    // Fondos
    static final String FONDO_ROJO    = "\033[41m";
    static final String FONDO_VERDE   = "\033[42m";
    static final String FONDO_AMARILLO= "\033[43m";
    static final String FONDO_AZUL    = "\033[44m";

    public static void main(String[] args) {

        // ================================================================
        // A. printf y format — especificadores de formato
        // ================================================================
        System.out.println("========== A. Especificadores de formato ==========");

        // %d → entero decimal
        System.out.printf("Entero         : %d%n", 42);
        System.out.printf("Con ancho      : %10d%n", 42);       // alineado derecha
        System.out.printf("Alineado izq   : %-10d|%n", 42);     // alineado izquierda
        System.out.printf("Con ceros      : %010d%n", 42);       // relleno con ceros
        System.out.printf("Con signo      : %+d%n", 42);         // fuerza signo +

        // %f → decimal
        System.out.printf("%nDecimal        : %f%n", 3.14159);
        System.out.printf("2 decimales    : %.2f%n", 3.14159);
        System.out.printf("8.2 formato    : %8.2f%n", 3.14159);  // ancho 8, 2 dec
        System.out.printf("Con coma miles : %,.2f%n", 1234567.89);

        // %s → String
        System.out.printf("%nString         : %s%n", "Java");
        System.out.printf("Ancho 15       : %15s|%n", "Java");      // derecha
        System.out.printf("Ancho -15      : %-15s|%n", "Java");     // izquierda
        System.out.printf("Mayúsculas     : %S%n", "java");         // %S = toUpperCase

        // %c → carácter
        System.out.printf("%nCarácter       : %c%n", 'A');

        // %b → boolean
        System.out.printf("Boolean        : %b%n", true);
        System.out.printf("Boolean        : %B%n", false);           // %B = mayúsculas

        // %x %o → hexadecimal y octal
        System.out.printf("%nHex de 255     : %x%n", 255);           // ff
        System.out.printf("Hex mayúscula  : %X%n", 255);            // FF
        System.out.printf("Hex con #      : %#x%n", 255);           // 0xff
        System.out.printf("Octal de 8     : %o%n", 8);              // 10

        // %e → notación científica
        System.out.printf("%nCientífico     : %e%n", 123456.789);
        System.out.printf("Científico .2  : %.2e%n", 123456.789);

        // %n → salto de línea (multiplataforma, mejor que \n en printf)
        System.out.printf("Con %%n al final%n");

        // %% → literal %
        System.out.printf("Porcentaje     : %.1f%%%n", 75.5);

        // ================================================================
        // B. String.format() — igual que printf pero devuelve String
        // ================================================================
        System.out.println("\n========== B. String.format() ==========");

        String linea1 = String.format("%-20s %5d %8.2f", "William", 25, 1.80);
        String linea2 = String.format("%-20s %5d %8.2f", "Ana García", 30, 1.65);
        System.out.println(linea1);
        System.out.println(linea2);

        // Construir mensajes reutilizables
        String err  = String.format("[ERROR] Línea %d: %s", 42, "variable no declarada");
        String info = String.format("[INFO]  %s iniciado correctamente", "Servidor");
        System.out.println(err);
        System.out.println(info);

        // ================================================================
        // C. TABLAS EN CONSOLA
        // ================================================================
        System.out.println("\n========== C. Tablas ==========");

        // Datos
        String[][] datos = {
                {"Nombre",    "Edad", "Lenguaje",    "Exp"},
                {"William",   "25",   "Java",         "2 años"},
                {"Ana",       "30",   "Python",       "5 años"},
                {"Carlos",    "28",   "TypeScript",   "3 años"},
                {"Eva",       "35",   "Go",           "7 años"},
        };

        // Calcular anchos máximos por columna
        int[] anchos = new int[datos[0].length];
        for (String[] fila : datos) {
            for (int i = 0; i < fila.length; i++) {
                anchos[i] = Math.max(anchos[i], fila[i].length());
            }
        }

        // Línea separadora
        StringBuilder separador = new StringBuilder("+");
        for (int a : anchos) separador.append("-".repeat(a + 2)).append("+");

        // Imprimir tabla
        System.out.println(separador);
        for (int f = 0; f < datos.length; f++) {
            System.out.print("|");
            for (int c = 0; c < datos[f].length; c++) {
                System.out.printf(" %-" + anchos[c] + "s |", datos[f][c]);
            }
            System.out.println();
            if (f == 0) System.out.println(separador); // separador tras cabecera
        }
        System.out.println(separador);

        // ================================================================
        // D. COLORES ANSI
        // ================================================================
        System.out.println("\n========== D. Colores ANSI ==========");

        System.out.println(ROJO    + "Texto rojo"    + RESET);
        System.out.println(VERDE   + "Texto verde"   + RESET);
        System.out.println(AMARILLO+ "Texto amarillo"+ RESET);
        System.out.println(AZUL    + "Texto azul"    + RESET);
        System.out.println(MAGENTA + "Texto magenta" + RESET);
        System.out.println(CYAN    + "Texto cyan"    + RESET);

        System.out.println(NEGRITA + "Texto en negrita" + RESET);
        System.out.println(CURSIVA + "Texto en cursiva" + RESET);
        System.out.println(SUBRAY  + "Texto subrayado"  + RESET);

        System.out.println(ROJO_B   + NEGRITA + "ERROR CRÍTICO"   + RESET);
        System.out.println(VERDE_B  + NEGRITA + "ÉXITO"           + RESET);
        System.out.println(AMAR_B   + NEGRITA + "ADVERTENCIA"     + RESET);
        System.out.println(AZUL_B   + NEGRITA + "INFORMACIÓN"     + RESET);

        // Texto con fondo
        System.out.println(FONDO_ROJO   + BLANCO + " ERROR   " + RESET);
        System.out.println(FONDO_VERDE  + NEGRO  + " SUCCESS " + RESET);
        System.out.println(FONDO_AMARILLO + NEGRO + " WARN    " + RESET);

        // ================================================================
        // E. LOGGER DE CONSOLA CON COLORES
        // ================================================================
        System.out.println("\n========== E. Logger con colores ==========");
        log("INFO",  "Aplicación iniciada correctamente");
        log("WARN",  "El archivo de configuración no existe, usando valores por defecto");
        log("ERROR", "No se pudo conectar a la base de datos");
        log("DEBUG", "Valor de x = 42");

        // ================================================================
        // F. BARRA DE PROGRESO
        // ================================================================
        System.out.println("\n========== F. Barra de progreso ==========");
        for (int i = 0; i <= 100; i += 10) {
            imprimirProgreso(i, 100, 30);
            try { Thread.sleep(80); } catch (InterruptedException e) { }
        }
        System.out.println(); // salto final

        // ================================================================
        // G. SPINNER / ANIMACIÓN
        // ================================================================
        System.out.println("\n========== G. Spinner ==========");
        char[] spinner = {'|', '/', '-', '\\'};
        for (int i = 0; i < 20; i++) {
            System.out.print("\r" + CYAN + "Procesando " + spinner[i % 4] + RESET);
            try { Thread.sleep(100); } catch (InterruptedException e) { }
        }
        System.out.println("\r" + VERDE_B + "✓ Completado         " + RESET);

        // ================================================================
        // H. LIMPIAR CONSOLA
        // ================================================================
        System.out.println("\n========== H. Limpiar consola ==========");
        System.out.println("Para limpiar la consola:");
        System.out.println("  System.out.print(\"\\033[H\\033[2J\");");
        System.out.println("  System.out.flush();");
        System.out.println("(No ejecutado aquí para no borrar la salida)");
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static void log(String nivel, String mensaje) {
        String color = switch (nivel) {
            case "ERROR" -> ROJO_B  + NEGRITA;
            case "WARN"  -> AMAR_B  + NEGRITA;
            case "INFO"  -> VERDE_B;
            case "DEBUG" -> CYAN;
            default      -> RESET;
        };
        String ahora = java.time.LocalTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("%s [%s%-5s%s] %s %s%n",
                ahora, color, nivel, RESET, mensaje, "");
    }

    static void imprimirProgreso(int actual, int total, int anchoBar) {
        int completado = (int)((double) actual / total * anchoBar);
        int restante   = anchoBar - completado;
        String barra   = VERDE_B + "█".repeat(completado) + RESET
                + "░".repeat(restante);
        int porcentaje = (int)((double) actual / total * 100);
        System.out.printf("\r[%s] %3d%%  %d/%d", barra, porcentaje, actual, total);
    }
}