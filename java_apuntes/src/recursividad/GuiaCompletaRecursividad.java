package recursividad;

import java.util.*;

/**
 * ============================================================
 * GUÍA COMPLETA DE RECURSIVIDAD EN JAVA
 * ============================================================
 *
 * Este archivo contiene TODO sobre Recursividad:
 *
 * 1. ¿Qué es la recursividad?
 * 2. Caso base
 * 3. Llamadas recursivas
 * 4. Recursividad simple
 * 5. Recursividad múltiple
 * 6. Factorial
 * 7. Fibonacci
 * 8. Potencias
 * 9. Suma de arrays
 * 10. Búsqueda recursiva
 * 11. Palíndromos
 * 12. Torres de Hanoi
 * 13. Recursividad con Strings
 * 14. Recursividad indirecta
 * 15. Backtracking
 * 16. Tail Recursion
 * 17. Problemas comunes
 * 18. Complejidad temporal
 * 19. Buenas prácticas
 * 20. Ejemplos avanzados
 *
 * Compatible con Java 8+
 * ============================================================
 */
public class GuiaCompletaRecursividad {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("GUÍA COMPLETA RECURSIVIDAD JAVA");
        System.out.println("====================================\n");

        queEsRecursividad();
        casoBase();
        recursividadSimple();
        factorialEjemplo();
        fibonacciEjemplo();
        potenciasEjemplo();
        sumaArrayEjemplo();
        busquedaRecursiva();
        palindromosEjemplo();
        hanoiEjemplo();
        stringsRecursivos();
        recursividadIndirecta();
        backtrackingEjemplo();
        tailRecursionEjemplo();
        problemasComunes();
        complejidadTemporal();
        buenasPracticas();
        ejemplosAvanzados();
    }

    // ============================================================
    // 1. ¿QUÉ ES LA RECURSIVIDAD?
    // ============================================================
    public static void queEsRecursividad() {

        System.out.println("\n1. ¿QUÉ ES LA RECURSIVIDAD?");
        System.out.println("----------------------");

        System.out.println("La recursividad ocurre cuando");
        System.out.println("un método se llama a sí mismo.");

        System.out.println("\nToda recursividad necesita:");
        System.out.println("1. Caso base");
        System.out.println("2. Llamada recursiva");
    }

    // ============================================================
    // 2. CASO BASE
    // ============================================================
    public static void casoBase() {

        System.out.println("\n2. CASO BASE");
        System.out.println("----------------------");

        System.out.println("El caso base detiene la recursión.");

        contarRegresivo(5);
    }

    public static void contarRegresivo(int n) {

        // CASO BASE
        if (n == 0) {
            System.out.println("Fin");
            return;
        }

        System.out.println(n);

        // LLAMADA RECURSIVA
        contarRegresivo(n - 1);
    }

    // ============================================================
    // 3. RECURSIVIDAD SIMPLE
    // ============================================================
    public static void recursividadSimple() {

        System.out.println("\n3. RECURSIVIDAD SIMPLE");
        System.out.println("----------------------");

        imprimir(1);
    }

    public static void imprimir(int n) {

        if (n > 5) {
            return;
        }

        System.out.println("Número: " + n);

        imprimir(n + 1);
    }

    // ============================================================
    // 4. FACTORIAL
    // ============================================================
    public static void factorialEjemplo() {

        System.out.println("\n4. FACTORIAL");
        System.out.println("----------------------");

        int numero = 5;

        int resultado = factorial(numero);

        System.out.println("Factorial de " + numero +
                " = " + resultado);
    }

    public static int factorial(int n) {

        // Caso base
        if (n == 0 || n == 1) {
            return 1;
        }

        // Recursión
        return n * factorial(n - 1);
    }

    // ============================================================
    // 5. FIBONACCI
    // ============================================================
    public static void fibonacciEjemplo() {

        System.out.println("\n5. FIBONACCI");
        System.out.println("----------------------");

        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }

        System.out.println();
    }

    public static int fibonacci(int n) {

        // Casos base
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        // Recursividad múltiple
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // ============================================================
    // 6. POTENCIAS
    // ============================================================
    public static void potenciasEjemplo() {

        System.out.println("\n6. POTENCIAS");
        System.out.println("----------------------");

        int base = 2;
        int exponente = 5;

        int resultado = potencia(base, exponente);

        System.out.println(base + "^" + exponente +
                " = " + resultado);
    }

    public static int potencia(int base, int exponente) {

        if (exponente == 0) {
            return 1;
        }

        return base * potencia(base, exponente - 1);
    }

    // ============================================================
    // 7. SUMA DE ARRAY
    // ============================================================
    public static void sumaArrayEjemplo() {

        System.out.println("\n7. SUMA DE ARRAY");
        System.out.println("----------------------");

        int[] numeros = {1,2,3,4,5};

        int suma = sumarArray(numeros, 0);

        System.out.println("Suma = " + suma);
    }

    public static int sumarArray(int[] array, int indice) {

        if (indice == array.length) {
            return 0;
        }

        return array[indice] +
                sumarArray(array, indice + 1);
    }

    // ============================================================
    // 8. BÚSQUEDA RECURSIVA
    // ============================================================
    public static void busquedaRecursiva() {

        System.out.println("\n8. BÚSQUEDA RECURSIVA");
        System.out.println("----------------------");

        int[] numeros = {10,20,30,40,50};

        boolean encontrado = buscar(numeros, 30, 0);

        System.out.println("Encontrado: " + encontrado);
    }

    public static boolean buscar(int[] array,
                                 int valor,
                                 int indice) {

        if (indice >= array.length) {
            return false;
        }

        if (array[indice] == valor) {
            return true;
        }

        return buscar(array, valor, indice + 1);
    }

    // ============================================================
    // 9. PALÍNDROMOS
    // ============================================================
    public static void palindromosEjemplo() {

        System.out.println("\n9. PALÍNDROMOS");
        System.out.println("----------------------");

        String palabra = "reconocer";

        boolean esPalindromo =
                esPalindromo(palabra, 0,
                        palabra.length() - 1);

        System.out.println("¿Es palíndromo?: " + esPalindromo);
    }

    public static boolean esPalindromo(String texto,
                                       int inicio,
                                       int fin) {

        if (inicio >= fin) {
            return true;
        }

        if (texto.charAt(inicio) != texto.charAt(fin)) {
            return false;
        }

        return esPalindromo(texto,
                inicio + 1,
                fin - 1);
    }

    // ============================================================
    // 10. TORRES DE HANOI
    // ============================================================
    public static void hanoiEjemplo() {

        System.out.println("\n10. TORRES DE HANOI");
        System.out.println("----------------------");

        torresHanoi(3, 'A', 'C', 'B');
    }

    public static void torresHanoi(int discos,
                                   char origen,
                                   char destino,
                                   char auxiliar) {

        if (discos == 1) {
            System.out.println("Mover disco de " +
                    origen + " a " + destino);
            return;
        }

        torresHanoi(discos - 1,
                origen,
                auxiliar,
                destino);

        System.out.println("Mover disco de " +
                origen + " a " + destino);

        torresHanoi(discos - 1,
                auxiliar,
                destino,
                origen);
    }

    // ============================================================
    // 11. STRINGS RECURSIVOS
    // ============================================================
    public static void stringsRecursivos() {

        System.out.println("\n11. STRINGS RECURSIVOS");
        System.out.println("----------------------");

        String texto = "Java";

        String invertido = invertir(texto);

        System.out.println("Invertido: " + invertido);
    }

    public static String invertir(String texto) {

        if (texto.isEmpty()) {
            return texto;
        }

        return invertir(texto.substring(1)) +
                texto.charAt(0);
    }

    // ============================================================
    // 12. RECURSIVIDAD INDIRECTA
    // ============================================================
    public static void recursividadIndirecta() {

        System.out.println("\n12. RECURSIVIDAD INDIRECTA");
        System.out.println("----------------------");

        esPar(4);
    }

    public static boolean esPar(int n) {

        if (n == 0) {
            System.out.println("Es par");
            return true;
        }

        return esImpar(n - 1);
    }

    public static boolean esImpar(int n) {

        if (n == 0) {
            System.out.println("Es impar");
            return false;
        }

        return esPar(n - 1);
    }

    // ============================================================
    // 13. BACKTRACKING
    // ============================================================
    public static void backtrackingEjemplo() {

        System.out.println("\n13. BACKTRACKING");
        System.out.println("----------------------");

        generarBinarios("", 3);
    }

    public static void generarBinarios(String actual,
                                       int longitud) {

        if (actual.length() == longitud) {
            System.out.println(actual);
            return;
        }

        generarBinarios(actual + "0", longitud);
        generarBinarios(actual + "1", longitud);
    }

    // ============================================================
    // 14. TAIL RECURSION
    // ============================================================
    public static void tailRecursionEjemplo() {

        System.out.println("\n14. TAIL RECURSION");
        System.out.println("----------------------");

        int resultado = factorialTail(5, 1);

        System.out.println("Factorial Tail = " + resultado);

        System.out.println("Java NO optimiza tail recursion.");
    }

    public static int factorialTail(int n, int acumulador) {

        if (n <= 1) {
            return acumulador;
        }

        return factorialTail(n - 1,
                acumulador * n);
    }

    // ============================================================
    // 15. PROBLEMAS COMUNES
    // ============================================================
    public static void problemasComunes() {

        System.out.println("\n15. PROBLEMAS COMUNES");
        System.out.println("----------------------");

        System.out.println("1. Olvidar el caso base");
        System.out.println("2. StackOverflowError");
        System.out.println("3. Recursión infinita");
        System.out.println("4. Mala complejidad");
        System.out.println("5. Duplicación de cálculos");

        System.out.println("\nEjemplo peligroso:");

        // infinito(1);

        System.out.println("Método comentado para evitar error.");
    }

    public static void infinito(int n) {

        System.out.println(n);

        infinito(n + 1);
    }

    // ============================================================
    // 16. COMPLEJIDAD TEMPORAL
    // ============================================================
    public static void complejidadTemporal() {

        System.out.println("\n16. COMPLEJIDAD TEMPORAL");
        System.out.println("----------------------");

        System.out.println("Factorial -> O(n)");
        System.out.println("Fibonacci recursivo -> O(2^n)");
        System.out.println("Búsqueda lineal -> O(n)");
        System.out.println("Torres de Hanoi -> O(2^n)");

        System.out.println("\nLa recursividad consume stack memory.");
    }

    // ============================================================
    // 17. BUENAS PRÁCTICAS
    // ============================================================
    public static void buenasPracticas() {

        System.out.println("\n17. BUENAS PRÁCTICAS");
        System.out.println("----------------------");

        System.out.println("1. Siempre define caso base");
        System.out.println("2. Usa nombres claros");
        System.out.println("3. Evita recursión innecesaria");
        System.out.println("4. Controla profundidad");
        System.out.println("5. Usa memoization si es necesario");
        System.out.println("6. Prefiere iteración si es más simple");
        System.out.println("7. Documenta el algoritmo");
    }

    // ============================================================
    // 18. EJEMPLOS AVANZADOS
    // ============================================================
    public static void ejemplosAvanzados() {

        System.out.println("\n18. EJEMPLOS AVANZADOS");
        System.out.println("----------------------");

        // Memoization Fibonacci
        Map<Integer, Long> memo = new HashMap<>();

        long resultado = fibonacciMemo(40, memo);

        System.out.println("Fibonacci memoized: " + resultado);

        // Permutaciones
        System.out.println("\nPermutaciones:");

        permutaciones("ABC", "");
    }

    public static long fibonacciMemo(int n,
                                     Map<Integer, Long> memo) {

        if (n <= 1) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        long valor = fibonacciMemo(n - 1, memo)
                + fibonacciMemo(n - 2, memo);

        memo.put(n, valor);

        return valor;
    }

    public static void permutaciones(String texto,
                                     String actual) {

        if (texto.isEmpty()) {
            System.out.println(actual);
            return;
        }

        for (int i = 0; i < texto.length(); i++) {

            char c = texto.charAt(i);

            String restante =
                    texto.substring(0, i)
                            + texto.substring(i + 1);

            permutaciones(restante,
                    actual + c);
        }
    }
}

