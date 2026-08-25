package expresiones_lambda;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * ============================================================
 * GUÍA COMPLETA DE EXPRESIONES LAMBDA EN JAVA
 * ============================================================
 *
 * Este archivo contiene:
 *
 * 1. ¿Qué son las expresiones lambda?
 * 2. Sintaxis básica
 * 3. Interfaces funcionales
 * 4. Uso con colecciones
 * 5. Uso con Streams
 * 6. Predicate, Function, Consumer y Supplier
 * 7. Method References
 * 8. Variables efectivamente finales
 * 9. Lambdas con múltiples parámetros
 * 10. Manejo de excepciones
 * 11. Comparator con lambdas
 * 12. Buenas prácticas
 * 13. Ejemplos avanzados
 *
 * Compatible con Java 8+
 * ============================================================
 */
public class GuiaExpresionesLambda {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("EXPRESIONES LAMBDA EN JAVA");
        System.out.println("====================================\n");

        ejemploBasico();
        interfacesFuncionales();
        lambdaConParametros();
        usoConColecciones();
        usoConStreams();
        predicateEjemplo();
        functionEjemplo();
        consumerEjemplo();
        supplierEjemplo();
        methodReferences();
        comparatorsConLambda();
        variablesEfectivamenteFinales();
        lambdaConExcepciones();
        ejemplosAvanzados();
        buenasPracticas();
    }

    // ============================================================
    // 1. EJEMPLO BÁSICO
    // ============================================================
    public static void ejemploBasico() {

        System.out.println("\n1. EJEMPLO BÁSICO");
        System.out.println("----------------------");

        // Forma tradicional
        Runnable tradicional = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hola desde clase anónima");
            }
        };

        // Forma lambda
        Runnable lambda = () -> System.out.println("Hola desde lambda");

        tradicional.run();
        lambda.run();
    }

    // ============================================================
    // 2. INTERFACES FUNCIONALES
    // ============================================================
    public static void interfacesFuncionales() {

        System.out.println("\n2. INTERFACES FUNCIONALES");
        System.out.println("----------------------");

        // Una interfaz funcional tiene UN solo método abstracto

        Operacion suma = (a, b) -> a + b;
        Operacion resta = (a, b) -> a - b;
        Operacion multiplicacion = (a, b) -> a * b;

        System.out.println("Suma: " + suma.calcular(10, 5));
        System.out.println("Resta: " + resta.calcular(10, 5));
        System.out.println("Multiplicación: " + multiplicacion.calcular(10, 5));
    }

    @FunctionalInterface
    interface Operacion {
        int calcular(int a, int b);
    }

    // ============================================================
    // 3. LAMBDAS CON PARÁMETROS
    // ============================================================
    public static void lambdaConParametros() {

        System.out.println("\n3. LAMBDAS CON PARÁMETROS");
        System.out.println("----------------------");

        // Sin parámetros
        Runnable r1 = () -> System.out.println("Sin parámetros");

        // Un parámetro
        Consumer<String> r2 = nombre -> System.out.println("Hola " + nombre);

        // Varios parámetros
        BiFunction<Integer, Integer, Integer> r3 = (a, b) -> a + b;

        // Con bloque
        BiFunction<Integer, Integer, Integer> r4 = (a, b) -> {
            int resultado = a * b;
            return resultado;
        };

        r1.run();
        r2.accept("William");

        System.out.println("Suma: " + r3.apply(4, 6));
        System.out.println("Multiplicación: " + r4.apply(4, 6));
    }

    // ============================================================
    // 4. USO CON COLECCIONES
    // ============================================================
    public static void usoConColecciones() {

        System.out.println("\n4. USO CON COLECCIONES");
        System.out.println("----------------------");

        List<String> nombres = Arrays.asList(
                "Carlos",
                "Ana",
                "Pedro",
                "Lucía"
        );

        // forEach con lambda
        nombres.forEach(nombre -> System.out.println(nombre));

        // Ordenar
        nombres.sort((a, b) -> a.compareTo(b));

        System.out.println("Ordenados:");
        nombres.forEach(System.out::println);
    }

    // ============================================================
    // 5. STREAMS + LAMBDAS
    // ============================================================
    public static void usoConStreams() {

        System.out.println("\n5. STREAMS + LAMBDAS");
        System.out.println("----------------------");

        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        List<Integer> paresCuadrados = numeros.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println("Pares al cuadrado: " + paresCuadrados);

        int suma = numeros.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println("Suma total: " + suma);
    }

    // ============================================================
    // 6. PREDICATE
    // ============================================================
    public static void predicateEjemplo() {

        System.out.println("\n6. PREDICATE");
        System.out.println("----------------------");

        Predicate<Integer> esPar = n -> n % 2 == 0;
        Predicate<Integer> mayorQue10 = n -> n > 10;

        System.out.println("8 es par: " + esPar.test(8));
        System.out.println("15 > 10: " + mayorQue10.test(15));

        Predicate<Integer> combinado = esPar.and(mayorQue10);

        System.out.println("20 es par y >10: " + combinado.test(20));
    }

    // ============================================================
    // 7. FUNCTION
    // ============================================================
    public static void functionEjemplo() {

        System.out.println("\n7. FUNCTION");
        System.out.println("----------------------");

        Function<String, Integer> longitud = texto -> texto.length();

        System.out.println("Longitud de 'Java': " + longitud.apply("Java"));

        Function<Integer, Integer> cuadrado = x -> x * x;
        Function<Integer, Integer> doble = x -> x * 2;

        Function<Integer, Integer> composicion = cuadrado.andThen(doble);

        System.out.println("(5^2)*2 = " + composicion.apply(5));
    }

    // ============================================================
    // 8. CONSUMER
    // ============================================================
    public static void consumerEjemplo() {

        System.out.println("\n8. CONSUMER");
        System.out.println("----------------------");

        Consumer<String> imprimir = mensaje ->
                System.out.println("Mensaje: " + mensaje);

        imprimir.accept("Hola Lambda");
    }

    // ============================================================
    // 9. SUPPLIER
    // ============================================================
    public static void supplierEjemplo() {

        System.out.println("\n9. SUPPLIER");
        System.out.println("----------------------");

        Supplier<Double> random = () -> Math.random();

        System.out.println("Número aleatorio: " + random.get());
    }

    // ============================================================
    // 10. METHOD REFERENCES
    // ============================================================
    public static void methodReferences() {

        System.out.println("\n10. METHOD REFERENCES");
        System.out.println("----------------------");

        List<String> nombres = Arrays.asList("Ana", "Luis", "Pedro");

        // Lambda tradicional
        nombres.forEach(nombre -> System.out.println(nombre));

        // Method Reference
        nombres.forEach(System.out::println);

        // Tipos de Method References:
        // 1. staticMethod
        // 2. instanceMethod
        // 3. constructor

        Function<String, Integer> parseInt = Integer::parseInt;

        System.out.println(parseInt.apply("123"));
    }

    // ============================================================
    // 11. COMPARATORS CON LAMBDA
    // ============================================================
    public static void comparatorsConLambda() {

        System.out.println("\n11. COMPARATORS CON LAMBDA");
        System.out.println("----------------------");

        List<Persona> personas = Arrays.asList(
                new Persona("Carlos", 30),
                new Persona("Ana", 25),
                new Persona("Pedro", 40)
        );

        // Ordenar por edad
        personas.sort((p1, p2) -> Integer.compare(p1.edad, p2.edad));

        personas.forEach(System.out::println);

        // Comparator moderno
        personas.sort(Comparator.comparing(Persona::getNombre));

        System.out.println("Ordenados por nombre:");
        personas.forEach(System.out::println);
    }

    static class Persona {
        private String nombre;
        private int edad;

        public Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        public String getNombre() {
            return nombre;
        }

        public int getEdad() {
            return edad;
        }

        @Override
        public String toString() {
            return nombre + " - " + edad;
        }
    }

    // ============================================================
    // 12. VARIABLES EFECTIVAMENTE FINALES
    // ============================================================
    public static void variablesEfectivamenteFinales() {

        System.out.println("\n12. VARIABLES EFECTIVAMENTE FINALES");
        System.out.println("----------------------");

        int numero = 10;

        // La variable debe ser effectively final
        Consumer<Integer> multiplicar = x ->
                System.out.println(x * numero);

        multiplicar.accept(5);

        // Esto NO compilaría:
        // numero = 20;
    }

    // ============================================================
    // 13. MANEJO DE EXCEPCIONES
    // ============================================================
    public static void lambdaConExcepciones() {

        System.out.println("\n13. MANEJO DE EXCEPCIONES");
        System.out.println("----------------------");

        List<String> numeros = Arrays.asList("1", "2", "abc", "4");

        numeros.forEach(n -> {
            try {
                int valor = Integer.parseInt(n);
                System.out.println(valor);
            } catch (NumberFormatException e) {
                System.out.println("Error convirtiendo: " + n);
            }
        });
    }

    // ============================================================
    // 14. EJEMPLOS AVANZADOS
    // ============================================================
    public static void ejemplosAvanzados() {

        System.out.println("\n14. EJEMPLOS AVANZADOS");
        System.out.println("----------------------");

        // Stream complejo
        List<String> palabras = Arrays.asList(
                "java",
                "lambda",
                "stream",
                "functional",
                "programming"
        );

        palabras.stream()
                .filter(p -> p.length() > 5)
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        // Optional + Lambda
        Optional<String> nombre = Optional.of("William");

        nombre.ifPresent(n ->
                System.out.println("Nombre presente: " + n));

        // BiPredicate
        BiPredicate<String, Integer> validar =
                (texto, longitud) -> texto.length() > longitud;

        System.out.println(validar.test("Lambda", 3));
    }

    // ============================================================
    // 15. BUENAS PRÁCTICAS
    // ============================================================
    public static void buenasPracticas() {

        System.out.println("\n15. BUENAS PRÁCTICAS");
        System.out.println("----------------------");

        System.out.println("1. Usa lambdas cortas y legibles");
        System.out.println("2. Evita lógica compleja dentro de lambdas");
        System.out.println("3. Prefiere Method References cuando sea posible");
        System.out.println("4. Usa Streams para procesamiento funcional");
        System.out.println("5. Evita efectos secundarios innecesarios");
        System.out.println("6. Mantén el código expresivo");

        // MAL
        Function<Integer, Integer> malo = x -> {
            int y = x * 2;
            y += 5;
            y *= 3;
            return y;
        };

        // MEJOR
        Function<Integer, Integer> bueno = x -> (x * 2 + 5) * 3;

        System.out.println("Resultado malo: " + malo.apply(5));
        System.out.println("Resultado bueno: " + bueno.apply(5));
    }
}
