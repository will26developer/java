import java.util.*;

/**
 * COMPOSITE
 *
 * Objetivo:
 * Tratar objetos individuales y grupos de objetos de la misma forma.
 *
 * Cuándo usarlo:
 * - Árboles de carpetas.
 * - Menús jerárquicos.
 * - Estructuras padre-hijo.
 *
 * Ventajas:
 * - Simplifica estructuras jerárquicas.
 *
 * Desventajas:
 * - Puede hacer difícil restringir ciertos tipos de hijos.
 */
public class CompositeEjemplo {

    public static void main(String[] args) {
        Carpeta raiz = new Carpeta("root");
        raiz.agregar(new Archivo("foto.jpg"));

        Carpeta docs = new Carpeta("docs");
        docs.agregar(new Archivo("cv.pdf"));

        raiz.agregar(docs);
        raiz.mostrar();
    }
}

interface SistemaArchivo { void mostrar(); }

class Archivo implements SistemaArchivo {
    private String nombre;
    Archivo(String nombre) { this.nombre = nombre; }
    public void mostrar() { System.out.println("Archivo: " + nombre); }
}

class Carpeta implements SistemaArchivo {
    private String nombre;
    private List<SistemaArchivo> elementos = new ArrayList<>();

    Carpeta(String nombre) { this.nombre = nombre; }
    void agregar(SistemaArchivo elemento) { elementos.add(elemento); }

    public void mostrar() {
        System.out.println("Carpeta: " + nombre);
        for (SistemaArchivo elemento : elementos) {
            elemento.mostrar();
        }
    }
}
