import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * COPIAR, MOVER Y BORRAR ARCHIVOS
 *
 * Métodos importantes:
 * - Files.copy()
 * - Files.move()
 * - Files.delete()
 * - Files.deleteIfExists()
 *
 * StandardCopyOption.REPLACE_EXISTING permite sobrescribir.
 */
public class CopiarMoverBorrar {

    public static void main(String[] args) {

        Path original = Path.of("archivo_original.txt");
        Path copia = Path.of("archivo_copia.txt");
        Path movido = Path.of("archivo_movido.txt");

        try {
            Files.writeString(original, "Contenido original");

            Files.copy(original, copia, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado.");

            Files.move(copia, movido, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo movido.");

            Files.deleteIfExists(original);
            Files.deleteIfExists(movido);
            System.out.println("Archivos borrados.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
