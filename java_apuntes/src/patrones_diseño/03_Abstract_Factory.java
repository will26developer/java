/**
 * ABSTRACT FACTORY
 *
 * Objetivo:
 * Crear familias de objetos relacionados sin especificar sus clases concretas.
 *
 * Cuándo usarlo:
 * - Interfaces gráficas multiplataforma.
 * - Familias de productos: Windows/Mac, Claro/Oscuro, SQL/NoSQL.
 *
 * Ventajas:
 * - Mantiene coherencia entre objetos relacionados.
 * - Facilita cambiar familias completas.
 *
 * Desventajas:
 * - Estructura más compleja que Factory Method.
 */
public class AbstractFactoryEjemplo {

    public static void main(String[] args) {
        UIFactory factory = new WindowsFactory();
        Boton boton = factory.crearBoton();
        Checkbox checkbox = factory.crearCheckbox();

        boton.render();
        checkbox.render();
    }
}

interface Boton { void render(); }
interface Checkbox { void render(); }

class BotonWindows implements Boton {
    public void render() { System.out.println("Botón estilo Windows"); }
}

class CheckboxWindows implements Checkbox {
    public void render() { System.out.println("Checkbox estilo Windows"); }
}

interface UIFactory {
    Boton crearBoton();
    Checkbox crearCheckbox();
}

class WindowsFactory implements UIFactory {
    public Boton crearBoton() { return new BotonWindows(); }
    public Checkbox crearCheckbox() { return new CheckboxWindows(); }
}
