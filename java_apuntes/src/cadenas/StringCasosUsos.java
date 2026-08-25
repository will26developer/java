package cadenas;
/**
 * STRING — CASOS DE USO PRÁCTICOS
 * =================================
 * Ejemplos reales que combinan los métodos de String para resolver
 * problemas comunes de programación.
 */
import java.util.Arrays;
import java.util.StringJoiner;

public class StringCasosUsos {

    public static void main(String[] args) {

        // ================================================================
        // 1. VALIDACIÓN DE ENTRADA
        // ================================================================
        System.out.println("========== 1. Validación ==========");

        String[] nombres = {"William", "", "  ", null, "Ana"};
        for (String n : nombres) {
            System.out.println("\"" + n + "\" → válido: " + esNombreValido(n));
        }

        // Validar email con regex
        String[] emails = {"user@mail.com", "invalido", "otro@dominio.es", "@sin-usuario.com"};
        for (String e : emails) {
            System.out.println(e + " → " + (esEmailValido(e) ? "válido" : "inválido"));
        }

        // ================================================================
        // 2. FORMATEO Y PRESENTACIÓN
        // ================================================================
        System.out.println("\n========== 2. Formateo ==========");

        // Tabla formateada
        System.out.println(generarTabla());

        // Capitalizar primera letra de cada palabra
        String titulo = "aprendiendo java desde cero";
        System.out.println("Capitalizado : " + capitalizarPalabras(titulo));

        // Truncar texto largo con elipsis
        String descripcion = "Java es un lenguaje de programación orientado a objetos muy popular";
        System.out.println("Truncado     : " + truncar(descripcion, 30));

        // ================================================================
        // 3. PARSING / EXTRACCIÓN
        // ================================================================
        System.out.println("\n========== 3. Parsing ==========");

        // Parsear una fecha simple "dd/MM/yyyy"
        String fecha = "19/05/2024";
        String[] partesFecha = fecha.split("/");
        System.out.println("Día   : " + partesFecha[0]);
        System.out.println("Mes   : " + partesFecha[1]);
        System.out.println("Año   : " + partesFecha[2]);

        // Extraer dominio de un email
        String correo = "william@ejemplo.com";
        String dominio = correo.substring(correo.indexOf('@') + 1);
        System.out.println("Dominio del correo: " + dominio);

        // Contar ocurrencias de una subcadena
        String texto = "Java es Java y siempre será Java";
        System.out.println("Ocurrencias de 'Java': " + contarOcurrencias(texto, "Java"));

        // ================================================================
        // 4. TRANSFORMACIÓN DE STRINGS
        // ================================================================
        System.out.println("\n========== 4. Transformación ==========");

        // Invertir un String
        String original = "Hola Mundo";
        String invertido = new StringBuilder(original).reverse().toString();
        System.out.println("Invertido    : " + invertido);

        // Comprobar si es palíndromo
        String[] palabras = {"radar", "java", "oso", "nivel", "mundo"};
        for (String p : palabras) {
            System.out.println(p + " → palíndromo: " + esPalindromo(p));
        }

        // Eliminar duplicados de caracteres
        System.out.println("Sin duplicados de 'aabbccdd': " + eliminarDuplicados("aabbccdd"));

        // Contar vocales y consonantes
        contarVocalesConsonantes("Aprendiendo Java");

        // ================================================================
        // 5. STRINGJOINER (Java 8+)
        //    Alternativa limpia a construir listas separadas por comas
        // ================================================================
        System.out.println("\n========== 5. StringJoiner ==========");

        StringJoiner sj1 = new StringJoiner(", ");
        sj1.add("Java").add("Python").add("TypeScript");
        System.out.println("Sin delimitadores : " + sj1);

        // Con prefijo y sufijo
        StringJoiner sj2 = new StringJoiner(", ", "[", "]");
        sj2.add("uno").add("dos").add("tres");
        System.out.println("Con [ ]           : " + sj2);

        // Valor por defecto si está vacío
        StringJoiner sj3 = new StringJoiner(", ", "(", ")");
        sj3.setEmptyValue("vacío");
        System.out.println("Vacío             : " + sj3);

        // ================================================================
        // 6. STREAMS CON STRINGS (Java 8+)
        // ================================================================
        System.out.println("\n========== 6. Strings con Streams ==========");

        String oracion = "java es un lenguaje orientado a objetos";

        // Contar palabras únicas
        long palabrasUnicas = Arrays.stream(oracion.split(" "))
                .distinct()
                .count();
        System.out.println("Palabras únicas   : " + palabrasUnicas);

        // Palabra más larga
        String masLarga = Arrays.stream(oracion.split(" "))
                .max((a, b) -> Integer.compare(a.length(), b.length()))
                .orElse("");
        System.out.println("Palabra más larga : " + masLarga);

        // Unir con Stream.collect y joining
        String resultado = Arrays.stream(oracion.split(" "))
                .filter(p -> p.length() > 3)
                .map(String::toUpperCase)
                .collect(java.util.stream.Collectors.joining(" | "));
        System.out.println("Filtrado+join     : " + resultado);
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static boolean esNombreValido(String nombre) {
        return nombre != null && !nombre.isBlank();
    }

    static boolean esEmailValido(String email) {
        if (email == null) return false;
        return email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    }

    static String generarTabla() {
        StringBuilder tabla = new StringBuilder();
        String[][] datos = {
            {"Nombre",   "Lenguaje",    "Años exp"},
            {"William",  "Java",        "2"},
            {"Ana",      "Python",      "5"},
            {"Carlos",   "JavaScript",  "3"}
        };
        for (String[] fila : datos) {
            tabla.append(String.format("%-12s %-14s %-10s%n", fila[0], fila[1], fila[2]));
        }
        return tabla.toString();
    }

    static String capitalizarPalabras(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        String[] palabras = texto.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String p : palabras) {
            if (!p.isEmpty()) {
                sb.append(Character.toUpperCase(p.charAt(0)))
                  .append(p.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    static String truncar(String texto, int maxLen) {
        if (texto == null || texto.length() <= maxLen) return texto;
        return texto.substring(0, maxLen - 3) + "...";
    }

    static int contarOcurrencias(String texto, String sub) {
        int count = 0, idx = 0;
        while ((idx = texto.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    static boolean esPalindromo(String s) {
        String limpio = s.toLowerCase().replaceAll("[^a-záéíóúñ]", "");
        return limpio.equals(new StringBuilder(limpio).reverse().toString());
    }

    static String eliminarDuplicados(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static void contarVocalesConsonantes(String texto) {
        int vocales = 0, consonantes = 0;
        String vocalesStr = "aeiouáéíóúAEIOUÁÉÍÓÚ";
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                if (vocalesStr.indexOf(c) >= 0) vocales++;
                else consonantes++;
            }
        }
        System.out.println("\"" + texto + "\" → vocales: " + vocales + ", consonantes: " + consonantes);
    }
}