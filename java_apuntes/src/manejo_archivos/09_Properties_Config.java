import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ARCHIVOS .properties
 *
 * ¿Para qué sirven?
 * - Guardar configuración simple.
 * - Clave = valor.
 *
 * Ejemplo:
 * servidor=localhost
 * puerto=8080
 */
public class PropertiesConfig {

    public static void main(String[] args) {

        Properties config = new Properties();

        config.setProperty("servidor", "localhost");
        config.setProperty("puerto", "8080");
        config.setProperty("modo", "desarrollo");

        try (FileOutputStream salida = new FileOutputStream("app.properties")) {

            config.store(salida, "Configuración de aplicación");
            System.out.println("Archivo properties creado.");

        } catch (IOException e) {
            System.out.println("Error guardando properties: " + e.getMessage());
        }

        Properties cargado = new Properties();

        try (FileInputStream entrada = new FileInputStream("app.properties")) {

            cargado.load(entrada);

            System.out.println("Servidor: " + cargado.getProperty("servidor"));
            System.out.println("Puerto: " + cargado.getProperty("puerto"));
            System.out.println("Modo: " + cargado.getProperty("modo"));

        } catch (IOException e) {
            System.out.println("Error leyendo properties: " + e.getMessage());
        }
    }
}
