import java.io.File;
import java.io.IOException;

/**
 * MANEJO BÁSICO DE ARCHIVOS CON java.io.File
 *
 * ¿Qué aprenderás?
 * - Crear objetos File
 * - Comprobar si existe un archivo
 * - Crear archivos y carpetas
 * - Obtener información básica
 *
 * IMPORTANTE:
 * File representa rutas de archivos o carpetas.
 * No sirve directamente para leer o escribir contenido.
 */
public class FileBasico {

    public static void main(String[] args) {

        File archivo = new File("ejemplo.txt");
        File carpeta = new File("mi_carpeta");

        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }

            if (carpeta.mkdir()) {
                System.out.println("Carpeta creada: " + carpeta.getName());
            } else {
                System.out.println("La carpeta ya existe o no pudo crearse.");
            }

            System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());
            System.out.println("Existe: " + archivo.exists());
            System.out.println("Es archivo: " + archivo.isFile());
            System.out.println("Es carpeta: " + archivo.isDirectory());
            System.out.println("Tamaño en bytes: " + archivo.length());

        } catch (IOException e) {
            System.out.println("Error creando archivo: " + e.getMessage());
        }
    }
}
