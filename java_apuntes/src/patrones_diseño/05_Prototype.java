/**
 * PROTOTYPE
 *
 * Objetivo:
 * Crear nuevos objetos copiando una instancia existente.
 *
 * Cuándo usarlo:
 * - Crear objetos costosos de construir.
 * - Necesitas duplicar objetos con estado inicial parecido.
 *
 * Ventajas:
 * - Evita repetir inicialización compleja.
 *
 * Desventajas:
 * - Las copias profundas pueden ser delicadas.
 */
public class PrototypeEjemplo {

    public static void main(String[] args) {
        Documento original = new Documento("Contrato", "Contenido legal");
        Documento copia = original.clonar();

        System.out.println(copia);
    }
}

class Documento {
    private String titulo;
    private String contenido;

    Documento(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    Documento clonar() {
        return new Documento(this.titulo, this.contenido);
    }

    public String toString() {
        return titulo + ": " + contenido;
    }
}
