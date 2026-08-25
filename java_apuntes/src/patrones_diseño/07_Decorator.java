/**
 * DECORATOR
 *
 * Objetivo:
 * Añadir comportamiento a un objeto sin modificar su clase original.
 *
 * Cuándo usarlo:
 * - Añadir funcionalidades combinables.
 * - Evitar herencias enormes.
 *
 * Ventajas:
 * - Flexible y extensible.
 * - Usa composición.
 *
 * Desventajas:
 * - Puede crear muchas clases pequeñas.
 */
public class DecoratorEjemplo {

    public static void main(String[] args) {
        Cafe cafe = new ChocolateDecorator(new LecheDecorator(new CafeSimple()));
        System.out.println(cafe.descripcion());
        System.out.println(cafe.precio());
    }
}

interface Cafe {
    String descripcion();
    double precio();
}

class CafeSimple implements Cafe {
    public String descripcion() { return "Café"; }
    public double precio() { return 1.50; }
}

class LecheDecorator implements Cafe {
    private Cafe cafe;
    LecheDecorator(Cafe cafe) { this.cafe = cafe; }
    public String descripcion() { return cafe.descripcion() + " + leche"; }
    public double precio() { return cafe.precio() + 0.50; }
}

class ChocolateDecorator implements Cafe {
    private Cafe cafe;
    ChocolateDecorator(Cafe cafe) { this.cafe = cafe; }
    public String descripcion() { return cafe.descripcion() + " + chocolate"; }
    public double precio() { return cafe.precio() + 0.70; }
}
