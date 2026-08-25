package cadenas;
/**
 * MÉTODOS DE STRING — REFERENCIA COMPLETA
 * ========================================
 * Todos los métodos más importantes de la clase java.lang.String,
 * organizados por categoría con ejemplos ejecutables.
 */
public class StringMetodos {

    public static void main(String[] args) {

        // ================================================================
        // A. INFORMACIÓN / CONSULTA
        // ================================================================
        System.out.println("========== A. INFORMACIÓN ==========");
        String texto = "  Hola, Java World!  ";

        // length() — número de caracteres (incluye espacios)
        System.out.println("length()            : " + texto.length()); // 21

        // isEmpty() — true si length() == 0
        System.out.println("isEmpty()           : " + texto.isEmpty());       // false
        System.out.println("\"\".isEmpty()        : " + "".isEmpty());         // true

        // isBlank() — true si está vacío o solo espacios/tabs (Java 11+)
        System.out.println("isBlank()           : " + texto.isBlank());       // false
        System.out.println("\"   \".isBlank()     : " + "   ".isBlank());      // true

        // charAt(index) — devuelve el char en la posición indicada
        System.out.println("charAt(2)           : " + texto.charAt(2));       // 'o'

        // indexOf(str) — primera aparición (-1 si no existe)
        System.out.println("indexOf('a')        : " + texto.indexOf('a'));    // 4
        System.out.println("indexOf(\"Java\")     : " + texto.indexOf("Java")); // 7
        System.out.println("indexOf('a', 5)     : " + texto.indexOf('a', 5)); // desde pos 5

        // lastIndexOf(str) — última aparición
        System.out.println("lastIndexOf('a')    : " + texto.lastIndexOf('a')); // 11

        // contains(CharSequence) — comprueba si contiene una subcadena
        System.out.println("contains(\"Java\")    : " + texto.contains("Java")); // true

        // startsWith / endsWith
        String ruta = "archivo.java";
        System.out.println("startsWith(\"arch\")  : " + ruta.startsWith("arch")); // true
        System.out.println("endsWith(\".java\")   : " + ruta.endsWith(".java"));  // true
        System.out.println("startsWith(\"va\",2)  : " + ruta.startsWith("va", 2)); // con offset

        // ================================================================
        // B. COMPARACIÓN
        // ================================================================
        System.out.println("\n========== B. COMPARACIÓN ==========");
        String a = "java";
        String b = "JAVA";
        String c = "java";

        // equals() — compara contenido (case-sensitive)
        System.out.println("a.equals(c)              : " + a.equals(c));           // true
        System.out.println("a.equals(b)              : " + a.equals(b));           // false

        // equalsIgnoreCase() — compara ignorando mayúsculas/minúsculas
        System.out.println("equalsIgnoreCase(b)      : " + a.equalsIgnoreCase(b)); // true

        // compareTo() — orden lexicográfico; 0=igual, <0=menor, >0=mayor
        System.out.println("\"abc\".compareTo(\"abd\")   : " + "abc".compareTo("abd")); // -1
        System.out.println("\"abc\".compareTo(\"abc\")   : " + "abc".compareTo("abc")); //  0
        System.out.println("\"abd\".compareTo(\"abc\")   : " + "abd".compareTo("abc")); //  1

        // compareToIgnoreCase()
        System.out.println("compareToIgnoreCase      : " + a.compareToIgnoreCase(b)); // 0

        // matches(regex) — comprueba expresión regular
        String email = "usuario@email.com";
        System.out.println("matches email regex      : " +
                email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")); // true

        // ================================================================
        // C. EXTRACCIÓN / SUBCADENAS
        // ================================================================
        System.out.println("\n========== C. EXTRACCIÓN ==========");
        String frase = "Aprendiendo Java con dedicación";

        // substring(inicio) — desde índice hasta el final
        System.out.println("substring(12)         : " + frase.substring(12)); // "Java con dedicación"

        // substring(inicio, fin) — [inicio, fin) — fin NO incluido
        System.out.println("substring(12, 16)     : " + frase.substring(12, 16)); // "Java"

        // charAt ya visto arriba

        // toCharArray() — convierte a array de chars
        char[] chars = "Hola".toCharArray();
        System.out.print("toCharArray()         : ");
        for (char ch : chars) System.out.print(ch + " ");
        System.out.println();

        // getBytes() — convierte a array de bytes (UTF-8 por defecto en Java 17+)
        byte[] bytesArr = "Hi".getBytes();
        System.out.print("getBytes()            : ");
        for (byte by : bytesArr) System.out.print(by + " ");
        System.out.println();

        // ================================================================
        // D. TRANSFORMACIÓN (devuelven un nuevo String)
        // ================================================================
        System.out.println("\n========== D. TRANSFORMACIÓN ==========");
        String mixto = "  HoLa MuNDo  ";

        // toUpperCase / toLowerCase
        System.out.println("toUpperCase()         : " + mixto.toUpperCase());
        System.out.println("toLowerCase()         : " + mixto.toLowerCase());

        // trim() — elimina espacios al inicio y al final (chars ≤ '\u0020')
        System.out.println("trim()                : \"" + mixto.trim() + "\"");

        // strip() — igual que trim pero Unicode-aware (Java 11+)
        System.out.println("strip()               : \"" + mixto.strip() + "\"");
        System.out.println("stripLeading()        : \"" + mixto.stripLeading() + "\"");
        System.out.println("stripTrailing()       : \"" + mixto.stripTrailing() + "\"");

        // replace(old, new) — reemplaza todas las ocurrencias de un char/String
        String rep1 = "banana".replace('a', 'o');
        System.out.println("replace('a','o')      : " + rep1); // "bonono"

        String rep2 = "Hola Mundo Mundo".replace("Mundo", "Java");
        System.out.println("replace(str,str)      : " + rep2); // "Hola Java Java"

        // replaceFirst(regex, str) — reemplaza solo la primera coincidencia
        System.out.println("replaceFirst          : " +
                "a1b2c3".replaceFirst("\\d", "X")); // "aXb2c3"

        // replaceAll(regex, str) — reemplaza todas las coincidencias del regex
        System.out.println("replaceAll(\\\\d,X)     : " +
                "a1b2c3".replaceAll("\\d", "X")); // "aXbXcX"

        // concat(str) — equivale a + pero solo con String
        System.out.println("concat()              : " + "Hola".concat(" Mundo"));

        // repeat(n) — repite el String n veces (Java 11+)
        System.out.println("repeat(3)             : " + "ha".repeat(3)); // "hahaha"

        // indent(n) — añade n espacios al inicio de cada línea (Java 12+)
        System.out.println("indent(4):\n" + "línea1\nlínea2".indent(4));

        // ================================================================
        // E. DIVISIÓN Y UNIÓN
        // ================================================================
        System.out.println("========== E. DIVISIÓN Y UNIÓN ==========");

        // split(regex) — divide en array según separador
        String csv = "Java,Python,JavaScript,TypeScript";
        String[] partes = csv.split(",");
        System.out.print("split(\",\")            : ");
        for (String p : partes) System.out.print("[" + p + "] ");
        System.out.println();

        // split(regex, limit) — limita el número de fragmentos
        String[] limitado = csv.split(",", 2);
        System.out.print("split(\",\", 2)         : ");
        for (String p : limitado) System.out.print("[" + p + "] ");
        System.out.println();

        // join(delimitador, elementos) — une strings con separador (Java 8+)
        String unido = String.join(" | ", "Java", "Python", "Go");
        System.out.println("String.join()         : " + unido);

        // join con array
        String unido2 = String.join(", ", partes);
        System.out.println("join con array        : " + unido2);

        // ================================================================
        // F. BÚSQUEDA / VERIFICACIÓN AVANZADA
        // ================================================================
        System.out.println("\n========== F. BÚSQUEDA AVANZADA ==========");
        String codigo = "ERROR: archivo no encontrado en /home/user";

        System.out.println("regionMatches : " +
                "Hello World".regionMatches(6, "World Cup", 0, 5)); // true

        System.out.println("regionMatches (ignore case): " +
                "Hello World".regionMatches(true, 6, "WORLD Cup", 0, 5)); // true

        // ================================================================
        // G. CONVERSIÓN DESDE/HACIA OTROS TIPOS
        // ================================================================
        System.out.println("\n========== G. CONVERSIÓN ==========");

        // valueOf() — convierte cualquier tipo a String
        System.out.println("valueOf(42)           : " + String.valueOf(42));
        System.out.println("valueOf(3.14)         : " + String.valueOf(3.14));
        System.out.println("valueOf(true)         : " + String.valueOf(true));
        System.out.println("valueOf('A')          : " + String.valueOf('A'));

        // toString() — todos los objetos lo heredan de Object
        Integer num = 100;
        System.out.println("Integer.toString()    : " + num.toString());

        // Integer.parseInt / Double.parseDouble — de String a primitivo
        int    parsed1 = Integer.parseInt("123");
        double parsed2 = Double.parseDouble("3.14");
        System.out.println("parseInt(\"123\")       : " + parsed1);
        System.out.println("parseDouble(\"3.14\")   : " + parsed2);

        // ================================================================
        // H. FORMATTED (Java 15+) y FORMAT
        // ================================================================
        System.out.println("\n========== H. FORMATO ==========");
        String formateado = String.format("%-10s | %5d | %.2f", "Java", 2024, 9.99);
        System.out.println(formateado); // "Java       |  2024 | 9.99"

        System.out.println("formatted()           : " +
                "Hola %s, versión %d".formatted("Java", 21));

        // ================================================================
        // I. STREAMS DE LÍNEAS (Java 11+)
        // ================================================================
        System.out.println("\n========== I. lines() — Java 11+ ==========");
        String multilinea = "línea 1\nlínea 2\nlínea 3";
        multilinea.lines()
                  .map(l -> "  -> " + l)
                  .forEach(System.out::println);

        // ================================================================
        // J. CHARS Y CODEPOINTS
        // ================================================================
        System.out.println("\n========== J. chars() y codePoints() ==========");
        "ABC".chars()
            .forEach(i -> System.out.print((char) i + "=" + i + " ")); // A=65 B=66 C=67
        System.out.println();
    }
}