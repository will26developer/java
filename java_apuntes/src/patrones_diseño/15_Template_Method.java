/**
 * TEMPLATE METHOD
 *
 * Objetivo:
 * Definir el esqueleto de un algoritmo en una clase base y permitir que subclases personalicen pasos.
 *
 * Cuándo usarlo:
 * - Procesos con pasos fijos y algunos variables.
 * - Importación/exportación de datos.
 *
 * Ventajas:
 * - Reutiliza estructura común.
 *
 * Desventajas:
 * - Usa herencia; puede ser menos flexible que composición.
 */
public class TemplateMethodEjemplo {

    public static void main(String[] args) {
        Bebida cafe = new Cafe();
        cafe.preparar();
    }
}

abstract class Bebida {
    final void preparar() {
        hervirAgua();
        agregarIngrediente();
        servir();
    }

    void hervirAgua() { System.out.println("Hervir agua"); }
    abstract void agregarIngrediente();
    void servir() { System.out.println("Servir bebida"); }
}

class Cafe extends Bebida {
    void agregarIngrediente() {
        System.out.println("Agregar café");
    }
}
