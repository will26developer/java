import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * MANEJO MODERNO DE ARCHIVOS CON java.nio.file.Files
 *
 * ¿Por qué usar NIO?
 * - Es más moderno que java.io.File.
 * - Tiene métodos simples para leer, escribir, copiar y borrar.
 * - Trabaja con Path.
 *
 * Recomendado para código moderno.
 */
public class FilesNIOJava {

    public static void main(String[] args) {

        Path ruta = Path.of("nio_ejemplo.txt");

        try {
            Files.writeString(ruta, "Hola desde NIO\nSegunda línea\n");

            String contenido = Files.readString(ruta);
            System.out.println("Contenido completo:");
            System.out.println(contenido);

            List<String> lineas = Files.readAllLines(ruta);
            System.out.println("Líneas:");
            lineas.forEach(System.out::println);

            System.out.println("Existe: " + Files.exists(ruta));
            System.out.println("Tamaño: " + Files.size(ruta) + " bytes");

        } catch (IOException e) {
            System.out.println("Error con Files NIO: " + e.getMessage());
        }
    }
}
