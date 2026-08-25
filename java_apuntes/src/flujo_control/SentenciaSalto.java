package flujo_control;

/**
 * SENTENCIAS DE SALTO: break / continue / return / etiquetas
 * ============================================================
 *
 *  break     → sale del bucle o switch actual
 *  continue  → salta a la siguiente iteración del bucle
 *  return    → sale del método (devolviendo o no un valor)
 *
 *  etiqueta: (label)
 *  break etiqueta    → sale del bucle etiquetado (exterior)
 *  continue etiqueta → salta a la siguiente iteración del bucle etiquetado
 */
public class SentenciaSalto {

    public static void main(String[] args) {

        // ================================================================
        // A. break
        //    - Dentro de switch: evita el fall-through
        //    - Dentro de bucle: termina el bucle inmediatamente
        // ================================================================
        System.out.println("========== A. break ==========");

        // break en for — buscar el primer número par
        int[] numeros = {3, 7, 4, 9, 2, 8};
        for (int n : numeros) {
            if (n % 2 == 0) {
                System.out.println("Primer par encontrado: " + n); // 4
                break; // sale del for
            }
        }

        // break en while — detener cuando se supera un umbral
        int acumulado = 0;
        int i = 1;
        while (i <= 100) {
            acumulado += i;
            if (acumulado > 50) {
                System.out.println("Acumulado supera 50 en i=" + i + " (acum=" + acumulado + ")");
                break;
            }
            i++;
        }

        // break en bucle infinito
        System.out.println("\n--- Bucle infinito con break ---");
        int x = 0;
        while (true) {
            if (x == 5) break;
            System.out.print(x + " ");
            x++;
        }
        System.out.println("← salió con break");

        // ================================================================
        // B. continue
        //    Salta el resto del cuerpo del bucle y va a la siguiente iteración.
        // ================================================================
        System.out.println("\n========== B. continue ==========");

        // Imprimir solo impares
        System.out.print("Impares del 1 al 10: ");
        for (int n = 1; n <= 10; n++) {
            if (n % 2 == 0) continue; // salta los pares
            System.out.print(n + " ");
        }
        System.out.println();

        // Saltar valores nulos o vacíos
        String[] nombres = {"Ana", null, "Carlos", "", "David", null};
        System.out.println("\nNombres válidos:");
        for (String nombre : nombres) {
            if (nombre == null || nombre.isBlank()) continue;
            System.out.println("  · " + nombre);
        }

        // continue en while
        System.out.print("\nDel 1 al 20, saltando múltiplos de 3: ");
        int k = 0;
        while (k < 20) {
            k++;
            if (k % 3 == 0) continue;
            System.out.print(k + " ");
        }
        System.out.println();

        // ================================================================
        // C. ETIQUETAS (Labels)
        //    Permiten que break y continue actúen sobre un bucle exterior.
        //    Sintaxis: nombreEtiqueta: bucle { break/continue nombreEtiqueta; }
        // ================================================================
        System.out.println("\n========== C. Etiquetas (Labels) ==========");

        // Sin etiqueta: break solo sale del for INTERIOR
        System.out.println("--- Sin etiqueta: break sale del for interior ---");
        for (int fila = 1; fila <= 3; fila++) {
            for (int col = 1; col <= 3; col++) {
                if (col == 2) break; // solo sale del for de col
                System.out.print("(" + fila + "," + col + ") ");
            }
        }
        System.out.println();

        // CON etiqueta: break sale del for EXTERIOR
        System.out.println("--- Con etiqueta: break sale del for exterior ---");
        externo:
        for (int fila = 1; fila <= 3; fila++) {
            for (int col = 1; col <= 3; col++) {
                if (fila == 2 && col == 2) break externo; // sale del for externo
                System.out.print("(" + fila + "," + col + ") ");
            }
        }
        System.out.println("← salió con break externo");

        // CON etiqueta: continue en el bucle exterior
        System.out.println("\n--- continue con etiqueta ---");
        externo2:
        for (int fila = 1; fila <= 3; fila++) {
            for (int col = 1; col <= 3; col++) {
                if (col == 2) continue externo2; // salta a siguiente iteración del for exterior
                System.out.print("(" + fila + "," + col + ") ");
            }
        }
        System.out.println();

        // Caso práctico: buscar un valor en matriz 2D
        System.out.println("\n--- Buscar valor en matriz 2D ---");
        int[][] matriz = {
            {1,  2,  3,  4},
            {5,  6,  7,  8},
            {9, 10, 11, 12}
        };
        int buscar = 7;
        int filaEncontrada = -1, colEncontrada = -1;

        busqueda:
        for (int f = 0; f < matriz.length; f++) {
            for (int c = 0; c < matriz[f].length; c++) {
                if (matriz[f][c] == buscar) {
                    filaEncontrada = f;
                    colEncontrada  = c;
                    break busqueda; // sale de ambos bucles
                }
            }
        }
        System.out.println("Valor " + buscar + " encontrado en fila=" +
                filaEncontrada + ", col=" + colEncontrada);

        // ================================================================
        // D. return
        //    - En un método void: termina el método sin devolver nada.
        //    - En un método con tipo: devuelve el valor y termina.
        // ================================================================
        System.out.println("\n========== D. return ==========");

        System.out.println("¿5 es primo? → " + esPrimo(5));
        System.out.println("¿9 es primo? → " + esPrimo(9));
        System.out.println("¿13 es primo? → " + esPrimo(13));

        System.out.println("\nFibonacci hasta 100:");
        imprimirFibonacci(100);

        // return anticipado para evitar anidamiento profundo (early return)
        System.out.println("\n--- Early return ---");
        System.out.println(clasificarEdad(-1));
        System.out.println(clasificarEdad(8));
        System.out.println(clasificarEdad(17));
        System.out.println(clasificarEdad(25));
        System.out.println(clasificarEdad(70));

        // ================================================================
        // E. COMPARATIVA Y BUENAS PRÁCTICAS
        // ================================================================
        System.out.println("\n========== E. Buenas prácticas ==========");
        System.out.println("break    → Usa para salir limpiamente de búsquedas o bucles infinitos.");
        System.out.println("continue → Usa para saltarte casos inválidos al inicio del bucle (guard clause).");
        System.out.println("Etiquetas→ Evítalas si puedes; extrae el bucle en un método con return.");
        System.out.println("return   → Early return reduce el anidamiento y mejora la legibilidad.");
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    /** Devuelve true si n es número primo. */
    static boolean esPrimo(int n) {
        if (n < 2) return false;          // early return: casos triviales
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false; // sale en cuanto encuentra divisor
        }
        return true;
    }

    /** Imprime la serie de Fibonacci hasta maxValor. */
    static void imprimirFibonacci(int maxValor) {
        int a = 0, b = 1;
        while (a <= maxValor) {
            System.out.print(a + " ");
            int temp = b;
            b = a + b;
            a = temp;
        }
        System.out.println();
    }

    /** Clasifica una edad con early return (evita else anidados). */
    static String clasificarEdad(int edad) {
        if (edad < 0)  return "Edad inválida";     // guard clause
        if (edad < 13) return "Niño";
        if (edad < 18) return "Adolescente";
        if (edad < 65) return "Adulto";
        return "Adulto mayor";                     // else implícito al final
    }
}
