package cadenas;
/**
 * 
 * STRING EN JAVA — FUNDAMENTOS
 * =============================
 * String es una clase del paquete java.lang (no necesita import).
 * Representa una cadena de caracteres Unicode INMUTABLE.
 *
 * INMUTABILIDAD: una vez creado un String, su contenido no puede
 * modificarse. Cualquier operación que "modifique" un String en
 * realidad crea un nuevo objeto String.
 */
public class StringFundamentos {

    public static void main(String[] args) {

        // ----------------------------------------------------------------
        // 1. FORMAS DE CREAR UN STRING
        // ----------------------------------------------------------------
        System.out.println("=== Creación de Strings ===");

        // Literal (forma más común): se almacena en el String Pool
        String s1 = "Hola";

        // Con new: siempre crea un nuevo objeto en el heap (evitar)
        String s2 = new String("Hola");

        // A partir de un array de chars
        char[] letras = {'J', 'a', 'v', 'a'};
        String s3 = new String(letras);

        // A partir de bytes (útil al leer archivos/red)
        byte[] bytes = {72, 111, 108, 97}; // 'H','o','l','a' en ASCII
        String s4 = new String(bytes);

        // String vacío
        String vacio = "";
        String vacio2 = new String();

        System.out.println("Literal       : " + s1);
        System.out.println("Con new       : " + s2);
        System.out.println("De char[]     : " + s3);
        System.out.println("De byte[]     : " + s4);
        System.out.println("Vacío         : \"" + vacio + "\"");

        // ----------------------------------------------------------------
        // 2. STRING POOL E INMUTABILIDAD
        //    Los literales se reutilizan del pool; new crea siempre objeto nuevo.
        // ----------------------------------------------------------------
        System.out.println("\n=== String Pool ===");
        String pool1 = "Java";
        String pool2 = "Java";       // apunta al mismo objeto del pool
        String heap1 = new String("Java"); // objeto nuevo en el heap

        System.out.println("pool1 == pool2       : " + (pool1 == pool2));   // true
        System.out.println("pool1 == heap1       : " + (pool1 == heap1));   // false
        System.out.println("pool1.equals(heap1)  : " + pool1.equals(heap1)); // true

        // intern() fuerza a usar la referencia del pool
        String interned = heap1.intern();
        System.out.println("pool1 == heap1.intern(): " + (pool1 == interned)); // true

        // ----------------------------------------------------------------
        // 3. INMUTABILIDAD EN ACCIÓN
        // ----------------------------------------------------------------
        System.out.println("\n=== Inmutabilidad ===");
        String original = "Hola";
        String modificado = original.concat(" Mundo"); // crea un NUEVO String
        System.out.println("original    : " + original);    // "Hola" sin cambios
        System.out.println("modificado  : " + modificado);  // "Hola Mundo"
        System.out.println("¿Son el mismo objeto? " + (original == modificado)); // false

        // ----------------------------------------------------------------
        // 4. STRING MULTILÍNEA (Text Block — Java 13+, estable en Java 15)
        // ----------------------------------------------------------------
        System.out.println("\n=== Text Block (Java 15+) ===");
        String json = """
                {
                    "nombre": "William",
                    "lenguaje": "Java"
                }
                """;
        System.out.println(json);

        // ----------------------------------------------------------------
        // 5. CONCATENACIÓN
        // ----------------------------------------------------------------
        System.out.println("=== Concatenación ===");
        String nombre = "William";
        int    edad   = 25;

        // Con + (crea nuevos objetos en bucles → usar StringBuilder)
        String concat1 = "Hola, " + nombre + ". Tienes " + edad + " años.";

        // Con String.format (estilo printf)
        String concat2 = String.format("Hola, %s. Tienes %d años.", nombre, edad);

        // Con formatted() — Java 15+
        String concat3 = "Hola, %s. Tienes %d años.".formatted(nombre, edad);

        System.out.println(concat1);
        System.out.println(concat2);
        System.out.println(concat3);

        // ----------------------------------------------------------------
        // 6. CARACTERES DE ESCAPE
        // ----------------------------------------------------------------
        System.out.println("\n=== Caracteres de escape ===");
        System.out.println("Tabulador    : \tColumna");
        System.out.println("Nueva línea  : línea1\nlínea2");
        System.out.println("Comilla doble: \"texto entre comillas\"");
        System.out.println("Comilla simple: \'A\'");
        System.out.println("Barra inversa: C:\\Users\\William");
        System.out.println("Unicode      : \u00A1Hola!");
    }
}