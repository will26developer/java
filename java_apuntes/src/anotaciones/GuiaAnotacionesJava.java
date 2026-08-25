package anotaciones;


import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * ============================================================
 * GUÍA COMPLETA DE ANOTACIONES EN JAVA
 * ============================================================
 *
 * Este archivo contiene TODO sobre Annotations:
 *
 * 1. ¿Qué son las anotaciones?
 * 2. Anotaciones integradas de Java
 * 3. @Override
 * 4. @Deprecated
 * 5. @SuppressWarnings
 * 6. @FunctionalInterface
 * 7. Crear anotaciones personalizadas
 * 8. @Target
 * 9. @Retention
 * 10. @Documented
 * 11. @Inherited
 * 12. Reflection con anotaciones
 * 13. Valores por defecto
 * 14. Múltiples valores
 * 15. Repetible annotations
 * 16. Procesamiento en runtime
 * 17. Casos reales
 * 18. Buenas prácticas
 *
 * Compatible con Java 8+
 * ============================================================
 */
public class GuiaAnotacionesJava {

    public static void main(String[] args) throws Exception {

        System.out.println("====================================");
        System.out.println("GUÍA COMPLETA ANOTACIONES JAVA");
        System.out.println("====================================\n");

        queSonAnotaciones();
        anotacionesIntegradas();
        overrideEjemplo();
        deprecatedEjemplo();
        suppressWarningsEjemplo();
        functionalInterfaceEjemplo();
        crearAnotacionPersonalizada();
        targetEjemplo();
        retentionEjemplo();
        documentedEjemplo();
        inheritedEjemplo();
        reflectionEjemplo();
        valoresPorDefecto();
        multiplesValores();
        repeatableAnnotations();
        procesamientoRuntime();
        casosReales();
        buenasPracticas();
    }

    // ============================================================
    // 1. ¿QUÉ SON LAS ANOTACIONES?
    // ============================================================
    public static void queSonAnotaciones() {

        System.out.println("\n1. ¿QUÉ SON LAS ANOTACIONES?");
        System.out.println("----------------------");

        System.out.println("Las anotaciones son metadatos");
        System.out.println("que proporcionan información");
        System.out.println("adicional sobre el código.");

        System.out.println("\nEjemplos:");
        System.out.println("@Override");
        System.out.println("@Deprecated");
        System.out.println("@FunctionalInterface");
    }

    // ============================================================
    // 2. ANOTACIONES INTEGRADAS
    // ============================================================
    public static void anotacionesIntegradas() {

        System.out.println("\n2. ANOTACIONES INTEGRADAS");
        System.out.println("----------------------");

        System.out.println("Principales anotaciones:");
        System.out.println("1. @Override");
        System.out.println("2. @Deprecated");
        System.out.println("3. @SuppressWarnings");
        System.out.println("4. @FunctionalInterface");
        System.out.println("5. @SafeVarargs");
    }

    // ============================================================
    // 3. @OVERRIDE
    // ============================================================
    public static void overrideEjemplo() {

        System.out.println("\n3. @OVERRIDE");
        System.out.println("----------------------");

        Animal animal = new Perro();
        animal.hacerSonido();

        System.out.println("@Override verifica que");
        System.out.println("realmente sobrescribes un método.");
    }

    static class Animal {
        public void hacerSonido() {
            System.out.println("Sonido genérico");
        }
    }

    static class Perro extends Animal {

        @Override
        public void hacerSonido() {
            System.out.println("Guau Guau");
        }
    }

    // ============================================================
    // 4. @DEPRECATED
    // ============================================================
    public static void deprecatedEjemplo() {

        System.out.println("\n4. @DEPRECATED");
        System.out.println("----------------------");

        SistemaAntiguo sistema = new SistemaAntiguo();

        sistema.metodoAntiguo();
        sistema.metodoNuevo();

        System.out.println("@Deprecated marca código obsoleto.");
    }

    static class SistemaAntiguo {

        @Deprecated
        public void metodoAntiguo() {
            System.out.println("Método antiguo");
        }

        public void metodoNuevo() {
            System.out.println("Método nuevo");
        }
    }

    // ============================================================
    // 5. @SUPPRESSWARNINGS
    // ============================================================
    @SuppressWarnings("unchecked")
    public static void suppressWarningsEjemplo() {

        System.out.println("\n5. @SUPPRESSWARNINGS");
        System.out.println("----------------------");

        List lista = new ArrayList();
        lista.add("Java");

        System.out.println(lista);

        System.out.println("Suprime warnings del compilador.");
    }

    // ============================================================
    // 6. @FUNCTIONALINTERFACE
    // ============================================================
    public static void functionalInterfaceEjemplo() {

        System.out.println("\n6. @FUNCTIONALINTERFACE");
        System.out.println("----------------------");

        Calculadora suma = (a, b) -> a + b;

        System.out.println(suma.operar(10, 5));

        System.out.println("Garantiza un único método abstracto.");
    }

    @FunctionalInterface
    interface Calculadora {
        int operar(int a, int b);
    }

    // ============================================================
    // 7. CREAR ANOTACIÓN PERSONALIZADA
    // ============================================================
    public static void crearAnotacionPersonalizada() {

        System.out.println("\n7. CREAR ANOTACIÓN PERSONALIZADA");
        System.out.println("----------------------");

        System.out.println("Se crean usando @interface");
    }

    @interface MiAnotacion {
        String valor();
    }

    @MiAnotacion(valor = "Ejemplo")
    static class ClaseEjemplo {
    }

    // ============================================================
    // 8. @TARGET
    // ============================================================
    public static void targetEjemplo() {

        System.out.println("\n8. @TARGET");
        System.out.println("----------------------");

        System.out.println("Define dónde puede usarse");
        System.out.println("una anotación.");

        System.out.println("Ejemplos:");
        System.out.println("TYPE");
        System.out.println("METHOD");
        System.out.println("FIELD");
        System.out.println("PARAMETER");
    }

    @Target(ElementType.METHOD)
    @interface SoloMetodo {
    }

    static class DemoTarget {

        @SoloMetodo
        public void ejecutar() {
            System.out.println("Método ejecutado");
        }
    }

    // ============================================================
    // 9. @RETENTION
    // ============================================================
    public static void retentionEjemplo() {

        System.out.println("\n9. @RETENTION");
        System.out.println("----------------------");

        System.out.println("Controla cuánto vive");
        System.out.println("la anotación.");

        System.out.println("SOURCE -> solo compilación");
        System.out.println("CLASS -> bytecode");
        System.out.println("RUNTIME -> disponible en runtime");
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface RuntimeAnnotation {
        String value();
    }

    // ============================================================
    // 10. @DOCUMENTED
    // ============================================================
    public static void documentedEjemplo() {

        System.out.println("\n10. @DOCUMENTED");
        System.out.println("----------------------");

        System.out.println("Hace que la anotación aparezca");
        System.out.println("en JavaDoc.");
    }

    @Documented
    @interface Documentada {
    }

    // ============================================================
    // 11. @INHERITED
    // ============================================================
    public static void inheritedEjemplo() {

        System.out.println("\n11. @INHERITED");
        System.out.println("----------------------");

        System.out.println("Permite heredar anotaciones.");
    }

    @Inherited
    @interface Heredable {
    }

    @Heredable
    static class Padre {
    }

    static class Hijo extends Padre {
    }

    // ============================================================
    // 12. REFLECTION CON ANOTACIONES
    // ============================================================
    public static void reflectionEjemplo() throws Exception {

        System.out.println("\n12. REFLECTION CON ANOTACIONES");
        System.out.println("----------------------");

        Class<Usuario> clase = Usuario.class;

        if (clase.isAnnotationPresent(Entidad.class)) {

            Entidad entidad = clase.getAnnotation(Entidad.class);

            System.out.println("Tabla: " + entidad.tabla());
        }

        for (Field campo : clase.getDeclaredFields()) {

            if (campo.isAnnotationPresent(Columna.class)) {

                Columna columna = campo.getAnnotation(Columna.class);

                System.out.println(campo.getName() +
                        " -> " + columna.nombre());
            }
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Entidad {
        String tabla();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Columna {
        String nombre();
    }

    @Entidad(tabla = "usuarios")
    static class Usuario {

        @Columna(nombre = "id_usuario")
        private int id;

        @Columna(nombre = "nombre_usuario")
        private String nombre;
    }

    // ============================================================
    // 13. VALORES POR DEFECTO
    // ============================================================
    public static void valoresPorDefecto() {

        System.out.println("\n13. VALORES POR DEFECTO");
        System.out.println("----------------------");

        System.out.println("Las anotaciones pueden tener");
        System.out.println("valores por defecto.");
    }

    @interface Configuracion {

        String servidor() default "localhost";

        int puerto() default 8080;
    }

    @Configuracion
    static class App1 {
    }

    @Configuracion(servidor = "production")
    static class App2 {
    }

    // ============================================================
    // 14. MÚLTIPLES VALORES
    // ============================================================
    public static void multiplesValores() {

        System.out.println("\n14. MÚLTIPLES VALORES");
        System.out.println("----------------------");

        System.out.println("Las anotaciones pueden recibir arrays.");
    }

    @interface Roles {
        String[] value();
    }

    @Roles({"ADMIN", "USER", "DEV"})
    static class Seguridad {
    }

    // ============================================================
    // 15. REPEATABLE ANNOTATIONS
    // ============================================================
    public static void repeatableAnnotations() {

        System.out.println("\n15. REPEATABLE ANNOTATIONS");
        System.out.println("----------------------");

        System.out.println("Permiten repetir anotaciones.");
    }

    @Repeatable(Tags.class)
    @interface Tag {
        String value();
    }

    @interface Tags {
        Tag[] value();
    }

    @Tag("java")
    @Tag("spring")
    @Tag("backend")
    static class Proyecto {
    }

    // ============================================================
    // 16. PROCESAMIENTO EN RUNTIME
    // ============================================================
    public static void procesamientoRuntime() throws Exception {

        System.out.println("\n16. PROCESAMIENTO EN RUNTIME");
        System.out.println("----------------------");

        Class<Servicio> clase = Servicio.class;

        for (Method metodo : clase.getDeclaredMethods()) {

            if (metodo.isAnnotationPresent(Ejecutable.class)) {

                Ejecutable anotacion =
                        metodo.getAnnotation(Ejecutable.class);

                if (anotacion.activo()) {

                    System.out.println("Ejecutando: " + metodo.getName());

                    metodo.invoke(new Servicio());
                }
            }
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Ejecutable {
        boolean activo() default true;
    }

    static class Servicio {

        @Ejecutable
        public void iniciar() {
            System.out.println("Servicio iniciado");
        }

        @Ejecutable(activo = false)
        public void detener() {
            System.out.println("Servicio detenido");
        }
    }

    // ============================================================
    // 17. CASOS REALES
    // ============================================================
    public static void casosReales() {

        System.out.println("\n17. CASOS REALES");
        System.out.println("----------------------");

        System.out.println("Frameworks usan anotaciones:");

        System.out.println("\nSpring:");
        System.out.println("@Component");
        System.out.println("@Service");
        System.out.println("@Autowired");

        System.out.println("\nJPA/Hibernate:");
        System.out.println("@Entity");
        System.out.println("@Table");
        System.out.println("@Column");

        System.out.println("\nJUnit:");
        System.out.println("@Test");
        System.out.println("@BeforeEach");
    }

    // ============================================================
    // 18. BUENAS PRÁCTICAS
    // ============================================================
    public static void buenasPracticas() {

        System.out.println("\n18. BUENAS PRÁCTICAS");
        System.out.println("----------------------");

        System.out.println("1. Usa nombres claros");
        System.out.println("2. Limita el uso de reflection");
        System.out.println("3. Usa @Retention apropiadamente");
        System.out.println("4. Evita lógica compleja en annotations");
        System.out.println("5. Documenta anotaciones personalizadas");
        System.out.println("6. Usa @Target específico");
        System.out.println("7. Usa annotations para metadata");
        System.out.println("8. Evita abusar de annotations");
    }
}

