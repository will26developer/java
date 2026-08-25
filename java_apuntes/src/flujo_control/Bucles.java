package flujo_control;

/**
 * BUCLES: for / while / do-while / for-each
 * ===========================================
 * Permiten repetir un bloque de código mientras se cumpla
 * una condición o para cada elemento de una colección.
 *
 *  for (init; condición; actualización) { ... }
 *  while (condición) { ... }
 *  do { ... } while (condición);
 *  for (tipo elemento : colección) { ... }
 */
import java.util.Arrays;
import java.util.List;

public class Bucles {

    public static void main(String[] args) {

        // ================================================================
        // A. BUCLE for CLÁSICO
        //    Ideal cuando se conoce el número de iteraciones de antemano.
        // ================================================================
        System.out.println("========== A. for clásico ==========");

        // Estructura: for (inicialización; condición; actualización)
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Contar hacia atrás
        for (int i = 10; i >= 0; i -= 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Tabla de multiplicar del 7
        System.out.println("\n--- Tabla del 7 ---");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("7 x %2d = %2d%n", i, 7 * i);
        }

        // For con múltiples variables
        System.out.println("\n--- For con múltiples variables ---");
        for (int i = 0, j = 10; i < j; i++, j--) {
            System.out.print("(" + i + "," + j + ") ");
        }
        System.out.println();

        // For anidado — tabla de multiplicar completa
        System.out.println("\n--- For anidado (tabla 1-5) ---");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.printf("%3d", i * j);
            }
            System.out.println();
        }

        // ================================================================
        // B. BUCLE while
        //    Ideal cuando NO se conoce el número de iteraciones.
        //    La condición se evalúa ANTES de cada iteración.
        // ================================================================
        System.out.println("\n========== B. while ==========");

        // Básico
        int n = 1;
        while (n <= 5) {
            System.out.print(n + " ");
            n++;
        }
        System.out.println();

        // Leer hasta encontrar un valor (simulado)
        System.out.println("\n--- Buscar primer número divisible por 7 > 50 ---");
        int num = 51;
        while (num % 7 != 0) {
            num++;
        }
        System.out.println("Encontrado: " + num);

        // While con condición compuesta
        System.out.println("\n--- Algoritmo de Euclides (MCD) ---");
        int a = 48, b = 18;
        System.out.print("MCD(" + a + "," + b + ") → ");
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        System.out.println(a);

        // While potencialmente infinito con break interno
        System.out.println("\n--- Potencias de 2 menores que 1000 ---");
        int pot = 1;
        while (true) {           // bucle infinito controlado con break
            if (pot >= 1000) break;
            System.out.print(pot + " ");
            pot *= 2;
        }
        System.out.println();

        // ================================================================
        // C. BUCLE do-while
        //    La condición se evalúa DESPUÉS de cada iteración.
        //    El cuerpo se ejecuta AL MENOS UNA VEZ.
        // ================================================================
        System.out.println("\n========== C. do-while ==========");

        // El bloque siempre se ejecuta al menos una vez
        int contador = 10;
        do {
            System.out.println("Ejecutado con contador=" + contador);
            contador++;
        } while (contador < 10); // condición falsa desde el inicio → igual ejecuta una vez

        // Uso clásico: menú de aplicación (simulado)
        System.out.println("\n--- Menú simulado ---");
        int opcion = 0;
        int[] opciones = {2, 1, 3}; // simula entradas del usuario
        int idx = 0;
        do {
            opcion = opciones[idx++];
            System.out.println("Opción elegida: " + opcion);
            switch (opcion) {
                case 1 -> System.out.println("  → Nuevo archivo");
                case 2 -> System.out.println("  → Abrir archivo");
                case 3 -> System.out.println("  → Salir");
            }
        } while (opcion != 3);

        // ================================================================
        // D. FOR-EACH (Enhanced for)
        //    Para recorrer arrays y colecciones sin índice.
        //    No permite modificar el array directamente.
        // ================================================================
        System.out.println("\n========== D. for-each ==========");

        // Array primitivo
        int[] numeros = {3, 7, 1, 9, 4, 6};
        int suma = 0;
        for (int numero : numeros) {
            suma += numero;
            System.out.print(numero + " ");
        }
        System.out.println("\nSuma: " + suma);

        // Array de Strings
        String[] frutas = {"Manzana", "Pera", "Naranja", "Kiwi"};
        System.out.println("\n--- Frutas ---");
        for (String fruta : frutas) {
            System.out.println("  · " + fruta + " (" + fruta.length() + " letras)");
        }

        // Lista (List)
        List<Integer> lista = Arrays.asList(10, 20, 30, 40, 50);
        System.out.println("\n--- Lista con for-each ---");
        for (int valor : lista) {
            System.out.print(valor + " ");
        }
        System.out.println();

        // Array 2D
        int[][] matriz = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("\n--- Matriz 3x3 con for-each anidado ---");
        for (int[] fila : matriz) {
            for (int celda : fila) {
                System.out.printf("%3d", celda);
            }
            System.out.println();
        }

        // ================================================================
        // E. FOR vs WHILE vs DO-WHILE — CUÁNDO USAR CADA UNO
        // ================================================================
        System.out.println("\n========== E. Comparativa ==========");
        System.out.println("for       → Número de iteraciones conocido de antemano");
        System.out.println("while     → Condición evaluada antes, puede no ejecutarse");
        System.out.println("do-while  → Siempre se ejecuta al menos una vez");
        System.out.println("for-each  → Recorrer arrays/colecciones sin índice");

        // ================================================================
        // F. BUCLES INFINITOS CONTROLADOS
        // ================================================================
        System.out.println("\n========== F. Bucles especiales ==========");

        // for sin cuerpo (trabajo en la cabecera)
        int suma2 = 0;
        int[] arr = {1, 2, 3, 4, 5};
        for (int i = 0, total = arr.length; i < total; suma2 += arr[i++]);
        System.out.println("Suma (for sin cuerpo): " + suma2);

        // Recorrer desde el final
        System.out.print("Invertido: ");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}