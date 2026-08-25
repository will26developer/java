package flujo_control;

/**
 * FLUJOS DE CONTROL — PATRONES Y CASOS DE USO AVANZADOS
 * =======================================================
 * Ejemplos prácticos que combinan condicionales, bucles y
 * sentencias de salto para resolver problemas reales.
 */
import java.util.Arrays;

public class FlujosControlAvanzado {

    public static void main(String[] args) {

        // ================================================================
        // 1. GUARDIA CLÁUSULA (Guard Clause / Early Return)
        //    Devuelve o lanza excepción anticipadamente para evitar
        //    pirámides de ifs anidados.
        // ================================================================
        System.out.println("========== 1. Guard Clause ==========");

        // MAL: anidamiento profundo
        System.out.println("--- Mal (anidado) ---");
        System.out.println(procesarMal("William", 25, true));

        // BIEN: early return aplana el código
        System.out.println("--- Bien (early return) ---");
        System.out.println(procesarBien("William", 25, true));
        System.out.println(procesarBien(null, 25, true));
        System.out.println(procesarBien("William", -1, true));
        System.out.println(procesarBien("William", 25, false));

        // ================================================================
        // 2. ITERACIÓN CON ÍNDICE Y SIN ÍNDICE
        // ================================================================
        System.out.println("\n========== 2. Recorrer arrays ==========");
        String[] lenguajes = {"Java", "Python", "TypeScript", "Go", "Rust"};

        // for-each cuando no necesitas el índice
        System.out.print("for-each : ");
        for (String l : lenguajes) System.out.print(l + " ");
        System.out.println();

        // for clásico cuando necesitas el índice
        System.out.println("for con índice:");
        for (int i = 0; i < lenguajes.length; i++) {
            System.out.printf("  [%d] %s%n", i, lenguajes[i]);
        }

        // Recorrer en paralelo dos arrays
        int[] notas    = {8, 6, 9, 7, 10};
        String[] alumnos = {"Ana", "Ben", "Carla", "David", "Eva"};
        System.out.println("Arrays en paralelo:");
        for (int i = 0; i < alumnos.length; i++) {
            System.out.printf("  %-6s → %d%n", alumnos[i], notas[i]);
        }

        // ================================================================
        // 3. PATRONES COMUNES DE BUCLES
        // ================================================================
        System.out.println("\n========== 3. Patrones comunes ==========");

        int[] datos = {4, 7, 2, 9, 1, 5, 3, 8, 6};

        // Acumulador
        int suma = 0;
        for (int d : datos) suma += d;
        System.out.println("Suma       : " + suma);

        // Máximo y mínimo
        int max = datos[0], min = datos[0];
        for (int d : datos) {
            if (d > max) max = d;
            if (d < min) min = d;
        }
        System.out.println("Max        : " + max);
        System.out.println("Min        : " + min);

        // Contar elementos que cumplen condición
        int mayoresDe5 = 0;
        for (int d : datos) if (d > 5) mayoresDe5++;
        System.out.println("Mayores>5  : " + mayoresDe5);

        // Búsqueda lineal
        int buscar = 9;
        int posicion = -1;
        for (int i = 0; i < datos.length; i++) {
            if (datos[i] == buscar) { posicion = i; break; }
        }
        System.out.println("Posición " + buscar + " : " + posicion);

        // Todos / alguno cumplen condición
        boolean todosPares   = true;
        boolean algunoPar    = false;
        for (int d : datos) {
            if (d % 2 != 0) todosPares = false;
            if (d % 2 == 0) algunoPar  = true;
        }
        System.out.println("Todos pares: " + todosPares);
        System.out.println("Algún par  : " + algunoPar);

        // ================================================================
        // 4. ALGORITMOS CLÁSICOS CON BUCLES
        // ================================================================
        System.out.println("\n========== 4. Algoritmos clásicos ==========");

        // Burbuja (Bubble Sort)
        int[] arr = {64, 25, 12, 22, 11};
        burbuja(arr);
        System.out.println("Burbuja    : " + Arrays.toString(arr));

        // Búsqueda binaria (requiere array ordenado)
        int[] ordenado = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("BúsBinaria(7): pos " + busquedaBinaria(ordenado, 7));
        System.out.println("BúsBinaria(6): pos " + busquedaBinaria(ordenado, 6));

        // Número de dígitos de un número
        System.out.println("Dígitos de 123456: " + contarDigitos(123456));

        // Invertir número
        System.out.println("Invertir 1234: " + invertirNumero(1234));

        // ================================================================
        // 5. BUCLES Y STRINGS
        // ================================================================
        System.out.println("\n========== 5. Bucles con Strings ==========");

        // Contar vocales
        String texto = "Aprendiendo Java con dedicación";
        int vocales = 0;
        for (char c : texto.toLowerCase().toCharArray()) {
            if ("aeiouáéíóú".indexOf(c) >= 0) vocales++;
        }
        System.out.println("Vocales en \"" + texto + "\": " + vocales);

        // Construir triángulo
        System.out.println("\n--- Triángulo de asteriscos ---");
        for (int fila = 1; fila <= 5; fila++) {
            for (int col = 1; col <= fila; col++) System.out.print("* ");
            System.out.println();
        }

        // Pirámide centrada
        System.out.println("--- Pirámide ---");
        int altura = 5;
        for (int f = 1; f <= altura; f++) {
            // Espacios
            for (int s = 1; s <= altura - f; s++) System.out.print(" ");
            // Asteriscos
            for (int a2 = 1; a2 <= 2 * f - 1; a2++) System.out.print("*");
            System.out.println();
        }

        // ================================================================
        // 6. SWITCH EXPRESSION EN FLUJOS COMPLEJOS
        // ================================================================
        System.out.println("\n========== 6. Switch en flujos complejos ==========");

        // Calculadora básica
        char[] operaciones = {'+', '-', '*', '/', '%'};
        double x = 10, y = 3;
        for (char op : operaciones) {
            double resultado = switch (op) {
                case '+' -> x + y;
                case '-' -> x - y;
                case '*' -> x * y;
                case '/' -> y != 0 ? x / y : Double.NaN;
                case '%' -> x % y;
                default  -> throw new IllegalArgumentException("Op desconocida: " + op);
            };
            System.out.printf("%.1f %c %.1f = %.4f%n", x, op, y, resultado);
        }

        // ================================================================
        // 7. ITERACIÓN FUNCIONAL (Java 8+) vs IMPERATIVA
        // ================================================================
        System.out.println("\n========== 7. Imperativo vs Funcional ==========");
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Imperativo (bucle for)
        int sumaParesImp = 0;
        for (int n : nums) {
            if (n % 2 == 0) sumaParesImp += n;
        }
        System.out.println("Suma pares (imperativo)  : " + sumaParesImp);

        // Funcional (Stream API — Java 8+)
        int sumaParesFunc = Arrays.stream(nums)
                .filter(n -> n % 2 == 0)
                .sum();
        System.out.println("Suma pares (funcional)   : " + sumaParesFunc);
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static String procesarMal(String nombre, int edad, boolean activo) {
        String resultado;
        if (nombre != null) {
            if (edad >= 0) {
                if (activo) {
                    resultado = "OK: " + nombre + ", " + edad;
                } else {
                    resultado = "Error: inactivo";
                }
            } else {
                resultado = "Error: edad negativa";
            }
        } else {
            resultado = "Error: nombre nulo";
        }
        return resultado;
    }

    static String procesarBien(String nombre, int edad, boolean activo) {
        if (nombre == null)  return "Error: nombre nulo";    // guard
        if (edad < 0)        return "Error: edad negativa";  // guard
        if (!activo)         return "Error: inactivo";       // guard
        return "OK: " + nombre + ", " + edad;                // lógica principal
    }

    static void burbuja(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j]   = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static int busquedaBinaria(int[] arr, int objetivo) {
        int izq = 0, der = arr.length - 1;
        while (izq <= der) {
            int mid = izq + (der - izq) / 2;
            if      (arr[mid] == objetivo) return mid;
            else if (arr[mid] < objetivo)  izq = mid + 1;
            else                           der = mid - 1;
        }
        return -1;
    }

    static int contarDigitos(int n) {
        if (n == 0) return 1;
        int count = 0;
        n = Math.abs(n);
        while (n > 0) { count++; n /= 10; }
        return count;
    }

    static int invertirNumero(int n) {
        int invertido = 0;
        while (n != 0) {
            invertido = invertido * 10 + n % 10;
            n /= 10;
        }
        return invertido;
    }
}
