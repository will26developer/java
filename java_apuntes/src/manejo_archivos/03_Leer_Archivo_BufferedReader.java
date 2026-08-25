import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * LEER ARCHIVOS CON BufferedReader
 *
 * ¿Cuándo usarlo?
 * - Para leer archivos de texto línea por línea.
 * - Es más eficiente que leer carácter por carácter.
 *
 * Ventaja:
 * BufferedReader usa un buffer interno para mejorar rendimiento.
 */
public class LeerArchivoBufferedReader {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader("salida.txt"))) {

            String linea;

            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }
    }
}
