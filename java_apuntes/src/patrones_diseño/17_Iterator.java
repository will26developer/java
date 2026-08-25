import java.util.*;

/**
 * ITERATOR
 *
 * Objetivo:
 * Recorrer una colección sin exponer su estructura interna.
 *
 * Cuándo usarlo:
 * - Recorrer listas, árboles o colecciones personalizadas.
 *
 * Ventajas:
 * - El cliente no necesita conocer cómo está guardada la colección.
 *
 * Desventajas:
 * - En Java muchas veces ya viene resuelto con Iterator y foreach.
 */
public class IteratorEjemplo {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.agregar("Clean Code");
        biblioteca.agregar("Effective Java");

        Iterator<String> it = biblioteca.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

class Biblioteca implements Iterable<String> {
    private List<String> libros = new ArrayList<>();

    void agregar(String libro) { libros.add(libro); }

    public Iterator<String> iterator() {
        return libros.iterator();
    }
}
