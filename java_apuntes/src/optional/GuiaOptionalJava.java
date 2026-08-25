package optional;

import java.util.*;
import java.util.stream.*;

/**
 * ============================================================
 * GUÍA COMPLETA DE OPTIONAL EN JAVA
 * ============================================================
 *
 * Este archivo contiene TODO sobre Optional:
 *
 * 1. ¿Qué es Optional?
 * 2. Cómo crear Optional
 * 3. isPresent() y get()
 * 4. ifPresent()
 * 5. orElse()
 * 6. orElseGet()
 * 7. orElseThrow()
 * 8. map()
 * 9. flatMap()
 * 10. filter()
 * 11. Optional con Streams
 * 12. Optional en métodos
 * 13. Encadenamiento funcional
 * 14. Errores comunes
 * 15. Buenas prácticas
 * 16. Ejemplos avanzados
 *
 * Compatible con Java 8+
 * ============================================================
 */
public class GuiaOptionalJava {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("GUÍA COMPLETA OPTIONAL EN JAVA");
        System.out.println("====================================\n");

        queEsOptional();
        crearOptional();
        isPresentYGet();
        ifPresentEjemplo();
        orElseEjemplo();
        orElseGetEjemplo();
        orElseThrowEjemplo();
        mapEjemplo();
        flatMapEjemplo();
        filterEjemplo();
        optionalConStreams();
        optionalEnMetodos();
        encadenamientoFuncional();
        erroresComunes();
        buenasPracticas();
        ejemplosAvanzados();
    }

    // ============================================================
    // 1. ¿QUÉ ES OPTIONAL?
    // ============================================================
    public static void queEsOptional() {

        System.out.println("\n1. ¿QUÉ ES OPTIONAL?");
        System.out.println("----------------------");

        System.out.println("Optional es un contenedor que puede");
        System.out.println("tener un valor o estar vacío.");
        System.out.println("Ayuda a evitar NullPointerException.");

        // Antes de Optional
        String nombre = null;

        if (nombre != null) {
            System.out.println(nombre.length());
        }

        // Con Optional
        Optional<String> optional = Optional.ofNullable(nombre);

        optional.ifPresent(valor ->
                System.out.println(valor.length()));
    }

    // ============================================================
    // 2. CREAR OPTIONAL
    // ============================================================
    public static void crearOptional() {

        System.out.println("\n2. CREAR OPTIONAL");
        System.out.println("----------------------");

        // Optional.of()
        Optional<String> nombre1 = Optional.of("William");

        // Optional.ofNullable()
        Optional<String> nombre2 = Optional.ofNullable(null);

        // Optional.empty()
        Optional<String> nombre3 = Optional.empty();

        System.out.println("nombre1: " + nombre1);
        System.out.println("nombre2: " + nombre2);
        System.out.println("nombre3: " + nombre3);

        // CUIDADO:
        // Optional.of(null); -> lanza NullPointerException
    }

    // ============================================================
    // 3. ISPRESENT() Y GET()
    // ============================================================
    public static void isPresentYGet() {

        System.out.println("\n3. ISPRESENT() Y GET()");
        System.out.println("----------------------");

        Optional<String> nombre = Optional.of("Java");

        if (nombre.isPresent()) {
            System.out.println(nombre.get());
        }

        // Java 11+
        nombre.ifPresentOrElse(
                valor -> System.out.println("Valor: " + valor),
                () -> System.out.println("Vacío")
        );

        // Mala práctica:
        // usar mucho isPresent() + get()
    }

    // ============================================================
    // 4. IFPRESENT()
    // ============================================================
    public static void ifPresentEjemplo() {

        System.out.println("\n4. IFPRESENT()");
        System.out.println("----------------------");

        Optional<String> nombre = Optional.of("Lambda");

        nombre.ifPresent(valor ->
                System.out.println("Hola " + valor));

        Optional<String> vacio = Optional.empty();

        vacio.ifPresent(valor ->
                System.out.println("Nunca se ejecuta"));
    }

    // ============================================================
    // 5. ORELSE()
    // ============================================================
    public static void orElseEjemplo() {

        System.out.println("\n5. ORELSE()");
        System.out.println("----------------------");

        Optional<String> nombre1 = Optional.of("Carlos");
        Optional<String> nombre2 = Optional.empty();

        String resultado1 = nombre1.orElse("Invitado");
        String resultado2 = nombre2.orElse("Invitado");

        System.out.println(resultado1);
        System.out.println(resultado2);

        // IMPORTANTE:
        // orElse() evalúa SIEMPRE el valor por defecto
    }

    // ============================================================
    // 6. ORELSEGET()
    // ============================================================
    public static void orElseGetEjemplo() {

        System.out.println("\n6. ORELSEGET()");
        System.out.println("----------------------");

        Optional<String> nombre = Optional.empty();

        String resultado = nombre.orElseGet(() -> {
            System.out.println("Generando valor...");
            return "Valor generado";
        });

        System.out.println(resultado);

        // Diferencia:
        // orElseGet() ejecuta SOLO si Optional está vacío
    }

    // ============================================================
    // 7. ORELSETHROW()
    // ============================================================
    public static void orElseThrowEjemplo() {

        System.out.println("\n7. ORELSETHROW()");
        System.out.println("----------------------");

        Optional<String> nombre = Optional.of("Pedro");

        String valor = nombre.orElseThrow(() ->
                new RuntimeException("No existe valor"));

        System.out.println(valor);

        // Java 10+
        // nombre.orElseThrow();
    }

    // ============================================================
    // 8. MAP()
    // ============================================================
    public static void mapEjemplo() {

        System.out.println("\n8. MAP()");
        System.out.println("----------------------");

        Optional<String> nombre = Optional.of("william");

        Optional<String> mayusculas = nombre.map(String::toUpperCase);

        System.out.println(mayusculas.get());

        Optional<Integer> longitud = nombre.map(String::length);

        System.out.println("Longitud: " + longitud.get());

        // map transforma el valor dentro del Optional
    }

    // ============================================================
    // 9. FLATMAP()
    // ============================================================
    public static void flatMapEjemplo() {

        System.out.println("\n9. FLATMAP()");
        System.out.println("----------------------");

        Usuario usuario = new Usuario("William",
                Optional.of(new Direccion("Madrid")));

        Optional<String> ciudad = Optional.of(usuario)
                .flatMap(Usuario::getDireccion)
                .map(Direccion::getCiudad);

        ciudad.ifPresent(System.out::println);

        // flatMap evita Optional<Optional<T>>
    }

    // ============================================================
    // 10. FILTER()
    // ============================================================
    public static void filterEjemplo() {

        System.out.println("\n10. FILTER()");
        System.out.println("----------------------");

        Optional<Integer> numero = Optional.of(20);

        Optional<Integer> resultado = numero
                .filter(n -> n > 10);

        System.out.println(resultado);

        Optional<Integer> resultado2 = numero
                .filter(n -> n > 100);

        System.out.println(resultado2);
    }

    // ============================================================
    // 11. OPTIONAL CON STREAMS
    // ============================================================
    public static void optionalConStreams() {

        System.out.println("\n11. OPTIONAL CON STREAMS");
        System.out.println("----------------------");

        List<String> nombres = Arrays.asList(
                "Ana",
                "Pedro",
                "Carlos",
                "Lucía"
        );

        Optional<String> resultado = nombres.stream()
                .filter(n -> n.startsWith("P"))
                .findFirst();

        resultado.ifPresent(System.out::println);

        Optional<String> max = nombres.stream()
                .max(String::compareTo);

        System.out.println("Máximo: " + max.orElse("Nada"));
    }

    // ============================================================
    // 12. OPTIONAL EN MÉTODOS
    // ============================================================
    public static void optionalEnMetodos() {

        System.out.println("\n12. OPTIONAL EN MÉTODOS");
        System.out.println("----------------------");

        Optional<Usuario> usuario = buscarUsuario("William");

        usuario.ifPresent(u ->
                System.out.println("Usuario encontrado: " + u.getNombre()));
    }

    public static Optional<Usuario> buscarUsuario(String nombre) {

        if (nombre.equals("William")) {
            return Optional.of(new Usuario(nombre));
        }

        return Optional.empty();
    }

    // ============================================================
    // 13. ENCADENAMIENTO FUNCIONAL
    // ============================================================
    public static void encadenamientoFuncional() {

        System.out.println("\n13. ENCADENAMIENTO FUNCIONAL");
        System.out.println("----------------------");

        Optional<String> resultado = Optional.of("java")
                .map(String::toUpperCase)
                .filter(s -> s.length() > 3)
                .map(s -> s + " 17");

        resultado.ifPresent(System.out::println);
    }

    // ============================================================
    // 14. ERRORES COMUNES
    // ============================================================
    public static void erroresComunes() {

        System.out.println("\n14. ERRORES COMUNES");
        System.out.println("----------------------");

        // ERROR 1: usar get() sin verificar
        Optional<String> vacio = Optional.empty();

        // vacio.get(); -> NoSuchElementException

        // ERROR 2: usar Optional en campos innecesarios

        // MAL:
        // class Persona {
        //     Optional<String> nombre;
        // }

        // ERROR 3: usar Optional como parámetro

        // MAL:
        // metodo(Optional<String> nombre)

        // ERROR 4: abusar de isPresent()

        if (vacio.isPresent()) {
            System.out.println(vacio.get());
        } else {
            System.out.println("Vacío");
        }

        // MEJOR:
        System.out.println(vacio.orElse("Vacío"));
    }

    // ============================================================
    // 15. BUENAS PRÁCTICAS
    // ============================================================
    public static void buenasPracticas() {

        System.out.println("\n15. BUENAS PRÁCTICAS");
        System.out.println("----------------------");

        System.out.println("1. Usa Optional como retorno de métodos");
        System.out.println("2. Evita get() sin validar");
        System.out.println("3. Prefiere orElse(), map() e ifPresent()");
        System.out.println("4. No uses Optional en atributos");
        System.out.println("5. No uses Optional en parámetros");
        System.out.println("6. Usa flatMap para evitar Optional anidados");
        System.out.println("7. Usa Optional para expresar ausencia de valor");
    }

    // ============================================================
    // 16. EJEMPLOS AVANZADOS
    // ============================================================
    public static void ejemplosAvanzados() {

        System.out.println("\n16. EJEMPLOS AVANZADOS");
        System.out.println("----------------------");

        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Ana"),
                new Usuario("Carlos"),
                new Usuario("Pedro")
        );

        Optional<Usuario> resultado = usuarios.stream()
                .filter(u -> u.getNombre().startsWith("C"))
                .findFirst();

        resultado
                .map(Usuario::getNombre)
                .map(String::toUpperCase)
                .ifPresent(System.out::println);

        // OptionalInt
        OptionalInt max = IntStream.of(1,2,3,4,5)
                .max();

        System.out.println("Máximo: " + max.orElse(0));

        // OptionalDouble
        OptionalDouble promedio = IntStream.of(10,20,30)
                .average();

        System.out.println("Promedio: " + promedio.orElse(0));
    }

    // ============================================================
    // CLASES AUXILIARES
    // ============================================================

    static class Usuario {

        private String nombre;
        private Optional<Direccion> direccion;

        public Usuario(String nombre) {
            this.nombre = nombre;
            this.direccion = Optional.empty();
        }

        public Usuario(String nombre, Optional<Direccion> direccion) {
            this.nombre = nombre;
            this.direccion = direccion;
        }

        public String getNombre() {
            return nombre;
        }

        public Optional<Direccion> getDireccion() {
            return direccion;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    static class Direccion {

        private String ciudad;

        public Direccion(String ciudad) {
            this.ciudad = ciudad;
        }

        public String getCiudad() {
            return ciudad;
        }
    }
}
