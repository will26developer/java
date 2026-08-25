import java.io.*;

/**
 * SERIALIZACIÓN DE OBJETOS
 *
 * ¿Qué es serializar?
 * Convertir un objeto Java en bytes para guardarlo en un archivo.
 *
 * Requisitos:
 * - La clase debe implementar Serializable.
 *
 * Uso típico:
 * - Guardar estados
 * - Cache simple
 * - Transferencia de objetos
 */
public class SerializacionObjetos {

    public static void main(String[] args) {

        Persona persona = new Persona("William", 30);

        try (ObjectOutputStream salida =
                     new ObjectOutputStream(new FileOutputStream("persona.dat"))) {

            salida.writeObject(persona);
            System.out.println("Objeto serializado.");

        } catch (IOException e) {
            System.out.println("Error serializando: " + e.getMessage());
        }

        try (ObjectInputStream entrada =
                     new ObjectInputStream(new FileInputStream("persona.dat"))) {

            Persona recuperada = (Persona) entrada.readObject();
            System.out.println("Objeto recuperado: " + recuperada);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializando: " + e.getMessage());
        }
    }

    static class Persona implements Serializable {

        private static final long serialVersionUID = 1L;

        private String nombre;
        private int edad;

        public Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        @Override
        public String toString() {
            return nombre + " - " + edad;
        }
    }
}
