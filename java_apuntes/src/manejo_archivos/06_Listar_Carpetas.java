import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * LISTAR ARCHIVOS DE UNA CARPETA
 *
 * Files.list(ruta):
 * - Lista solo el primer nivel.
 *
 * Files.walk(ruta):
 * - Recorre carpetas de forma recursiva.
 */
public class ListarCarpetas {

    public static void main(String[] args) {

        Path carpeta = Path.of(".");

        System.out.println("Archivos en la carpeta actual:");

        try (Stream<Path> archivos = Files.list(carpeta)) {

            archivos.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Error listando carpeta: " + e.getMessage());
        }

        System.out.println("\nRecorrido recursivo:");

        try (Stream<Path> archivos = Files.walk(carpeta, 2)) {

            archivos.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Error recorriendo carpeta: " + e.getMessage());
        }
    }
}
