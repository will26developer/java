/**
 * BUENAS PRÁCTICAS EN MANEJO DE ARCHIVOS JAVA
 *
 * 1. Usa try-with-resources
 *    Cierra automáticamente archivos, streams y readers.
 *
 * 2. Prefiere java.nio.file.Files y Path
 *    Es más moderno, claro y potente que File.
 *
 * 3. Maneja IOException
 *    Las operaciones con archivos pueden fallar por permisos,
 *    rutas incorrectas, archivos inexistentes, etc.
 *
 * 4. No hardcodees rutas absolutas
 *    Evita rutas como C:\usuarios\...
 *    Usa rutas relativas o configuración externa.
 *
 * 5. Valida si el archivo existe
 *    Files.exists(path)
 *
 * 6. Cuidado con sobrescribir archivos
 *    Usa StandardCopyOption.REPLACE_EXISTING solo cuando sea intencional.
 *
 * 7. No cargues archivos enormes completos en memoria
 *    Para archivos grandes, lee línea por línea o usa streams.
 *
 * 8. Separa responsabilidades
 *    No mezcles lógica de negocio con lectura/escritura.
 *
 * 9. Usa logging en aplicaciones reales
 *    En vez de solo System.out.println.
 *
 * 10. Usa librerías para formatos complejos
 *    CSV complejo, JSON, XML, Excel, PDF, etc.
 */
public class BuenasPracticasArchivos {

    public static void main(String[] args) {

        System.out.println("Buenas prácticas principales:");

        System.out.println("1. try-with-resources");
        System.out.println("2. java.nio.file.Path y Files");
        System.out.println("3. Manejo correcto de IOException");
        System.out.println("4. Evitar rutas absolutas hardcodeadas");
        System.out.println("5. Leer archivos grandes de forma eficiente");
    }
}
