import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * LEER ARCHIVOS CSV
 *
 * CSV significa Comma-Separated Values.
 *
 * Ejemplo:
 * nombre,edad,ciudad
 * Ana,25,Madrid
 *
 * Nota:
 * Este ejemplo es básico. Para CSV complejos con comillas,
 * saltos de línea o separadores especiales, conviene usar una librería.
 */
public class LeerCSV {

    public static void main(String[] args) {

        crearCSVDeEjemplo();

        try (BufferedReader reader = new BufferedReader(new FileReader("personas.csv"))) {

            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {

                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] partes = linea.split(",");

                String nombre = partes[0];
                int edad = Integer.parseInt(partes[1]);
                String ciudad = partes[2];

                System.out.println(nombre + " tiene " + edad + " años y vive en " + ciudad);
            }

        } catch (IOException e) {
            System.out.println("Error leyendo CSV: " + e.getMessage());
        }
    }

    private static void crearCSVDeEjemplo() {
        try (FileWriter writer = new FileWriter("personas.csv")) {
            writer.write("nombre,edad,ciudad\n");
            writer.write("Ana,25,Madrid\n");
            writer.write("Pedro,31,Barcelona\n");
            writer.write("Lucia,28,Valencia\n");
        } catch (IOException e) {
            System.out.println("Error creando CSV: " + e.getMessage());
        }
    }
}
