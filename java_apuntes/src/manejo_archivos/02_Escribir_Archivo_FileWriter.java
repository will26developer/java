import java.io.FileWriter;
import java.io.IOException;

/**
 * ESCRIBIR ARCHIVOS CON FileWriter
 *
 * ¿Cuándo usarlo?
 * - Para escribir texto simple en archivos.
 *
 * Modos:
 * - new FileWriter("archivo.txt") sobrescribe el archivo.
 * - new FileWriter("archivo.txt", true) añade contenido al final.
 */
public class EscribirArchivoFileWriter {

    public static void main(String[] args) {

        try (FileWriter writer = new FileWriter("salida.txt")) {

            writer.write("Primera línea\n");
            writer.write("Segunda línea\n");
            writer.write("Java permite escribir archivos fácilmente.\n");

            System.out.println("Archivo escrito correctamente.");

        } catch (IOException e) {
            System.out.println("Error escribiendo archivo: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("salida.txt", true)) {

            writer.write("Línea añadida al final.\n");

            System.out.println("Contenido añadido correctamente.");

        } catch (IOException e) {
            System.out.println("Error añadiendo contenido: " + e.getMessage());
        }
    }
}
