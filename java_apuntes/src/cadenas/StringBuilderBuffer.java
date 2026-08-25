package cadenas;
/**
 * STRINGBUILDER Y STRINGBUFFER
 * ==============================
 * Cuando necesitas construir o modificar cadenas de forma intensiva
 * (por ejemplo en bucles), usar String + String crea muchos objetos
 * temporales y es ineficiente.
 *
 * StringBuilder  → mutable, NO thread-safe, más rápido (uso general)
 * StringBuffer   → mutable, thread-safe (sincronizado), algo más lento
 *
 * Regla práctica:
 *   - Usa StringBuilder en código de un solo hilo (la gran mayoría).
 *   - Usa StringBuffer solo si varios hilos acceden al mismo objeto.
 */
public class StringBuilderBuffer {

    public static void main(String[] args) {

        // ================================================================
        // 1. CREACIÓN DE STRINGBUILDER
        // ================================================================
        System.out.println("========== Creación ==========");

        StringBuilder sb1 = new StringBuilder();             // capacidad inicial 16
        StringBuilder sb2 = new StringBuilder(64);           // capacidad inicial 64
        StringBuilder sb3 = new StringBuilder("Hola");       // inicializado con texto
        StringBuilder sb4 = new StringBuilder(sb3);          // copia de otro SB

        System.out.println("sb3               : " + sb3);
        System.out.println("capacidad inicial : " + sb1.capacity()); // 16
        System.out.println("longitud sb3      : " + sb3.length());   // 4

        // ================================================================
        // 2. MÉTODOS DE MODIFICACIÓN
        // ================================================================
        System.out.println("\n========== Modificación ==========");
        StringBuilder sb = new StringBuilder("Java");

        // append() — añade al final, acepta cualquier tipo
        sb.append(" es");
        sb.append(' ');
        sb.append("genial");
        sb.append('!');
        sb.append(2024);
        System.out.println("append()          : " + sb); // "Java es genial!2024"

        // insert(índice, valor) — inserta en la posición indicada
        sb.insert(8, "muy ");
        System.out.println("insert(8, \"muy \") : " + sb); // "Java es muy genial!2024"

        // delete(inicio, fin) — elimina [inicio, fin)
        sb.delete(sb.length() - 4, sb.length()); // quita "2024"
        System.out.println("delete()          : " + sb); // "Java es muy genial!"

        // deleteCharAt(índice) — elimina el carácter en esa posición
        sb.deleteCharAt(sb.length() - 1); // quita '!'
        System.out.println("deleteCharAt()    : " + sb);

        // replace(inicio, fin, str) — reemplaza [inicio, fin) con str
        sb.replace(0, 4, "Python");
        System.out.println("replace()         : " + sb); // "Python es muy genial"

        // setCharAt(índice, char) — cambia el carácter en esa posición
        sb.setCharAt(0, 'p');
        System.out.println("setCharAt(0,'p')  : " + sb);

        // reverse() — invierte el contenido
        StringBuilder rev = new StringBuilder("abcde");
        System.out.println("reverse()         : " + rev.reverse()); // "edcba"

        // ================================================================
        // 3. MÉTODOS DE CONSULTA (igual que String)
        // ================================================================
        System.out.println("\n========== Consulta ==========");
        StringBuilder info = new StringBuilder("Aprendiendo Java");

        System.out.println("length()          : " + info.length());
        System.out.println("charAt(3)         : " + info.charAt(3));
        System.out.println("indexOf(\"Java\")   : " + info.indexOf("Java"));
        System.out.println("lastIndexOf(\"a\")  : " + info.lastIndexOf("a"));
        System.out.println("substring(12)     : " + info.substring(12));
        System.out.println("substring(0,11)   : " + info.substring(0, 11));

        // ================================================================
        // 4. CONVERSIÓN A STRING
        // ================================================================
        System.out.println("\n========== Conversión ==========");
        StringBuilder construido = new StringBuilder();
        construido.append("William").append(" - ").append("Desarrollador");
        String resultado = construido.toString(); // SIEMPRE llamar toString() al final
        System.out.println("toString()        : " + resultado);

        // ================================================================
        // 5. ENCADENAMIENTO DE LLAMADAS (method chaining)
        //    Todos los métodos que modifican devuelven el mismo StringBuilder.
        // ================================================================
        System.out.println("\n========== Method Chaining ==========");
        String cadena = new StringBuilder()
                .append("Hola")
                .append(", ")
                .append("mundo")
                .append("!")
                .insert(0, ">> ")
                .toString();
        System.out.println("chaining          : " + cadena); // ">> Hola, mundo!"

        // ================================================================
        // 6. RENDIMIENTO: String vs StringBuilder en bucles
        // ================================================================
        System.out.println("\n========== Rendimiento ==========");
        int iteraciones = 100_000;

        // MAL: concatenación con String en bucle (crea muchos objetos)
        long inicio1 = System.currentTimeMillis();
        String malo = "";
        for (int i = 0; i < iteraciones; i++) {
            malo += "x"; // cada iteración crea un nuevo String
        }
        long fin1 = System.currentTimeMillis();
        System.out.println("String   (100k iter): " + (fin1 - inicio1) + " ms");

        // BIEN: StringBuilder (modifica el mismo objeto)
        long inicio2 = System.currentTimeMillis();
        StringBuilder bueno = new StringBuilder();
        for (int i = 0; i < iteraciones; i++) {
            bueno.append("x");
        }
        String resultadoBueno = bueno.toString();
        long fin2 = System.currentTimeMillis();
        System.out.println("StringBuilder (100k): " + (fin2 - inicio2) + " ms");

        // ================================================================
        // 7. STRINGBUFFER — idéntica API pero sincronizada
        // ================================================================
        System.out.println("\n========== StringBuffer (thread-safe) ==========");
        StringBuffer buffer = new StringBuffer("Hola");
        buffer.append(" Mundo");
        buffer.insert(4, ",");
        System.out.println("StringBuffer      : " + buffer);
        System.out.println("Es StringBuffer   : " + (buffer instanceof StringBuffer));

        // ================================================================
        // 8. COMPARATIVA RÁPIDA
        // ================================================================
        System.out.println("\n========== Comparativa String / StringBuilder / StringBuffer ==========");
        System.out.printf("%-15s %-12s %-12s %-20s%n", "Clase", "Mutable", "Thread-safe", "Uso recomendado");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-15s %-12s %-12s %-20s%n", "String",        "No",  "Sí (implíc.)", "Valores fijos");
        System.out.printf("%-15s %-12s %-12s %-20s%n", "StringBuilder", "Sí",  "No",           "Un solo hilo");
        System.out.printf("%-15s %-12s %-12s %-20s%n", "StringBuffer",  "Sí",  "Sí",           "Múltiples hilos");
    }
}