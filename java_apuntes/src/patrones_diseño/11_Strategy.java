/**
 * STRATEGY
 *
 * Objetivo:
 * Definir una familia de algoritmos y hacerlos intercambiables.
 *
 * Cuándo usarlo:
 * - Diferentes formas de pago.
 * - Diferentes algoritmos de ordenación, descuento o validación.
 *
 * Ventajas:
 * - Evita muchos if/switch.
 * - Facilita añadir nuevos comportamientos.
 *
 * Desventajas:
 * - El cliente debe elegir la estrategia adecuada.
 */
public class StrategyEjemplo {

    public static void main(String[] args) {
        Carrito carrito = new Carrito(new PagoTarjeta());
        carrito.pagar(100);

        carrito.setMetodoPago(new PagoPaypal());
        carrito.pagar(200);
    }
}

interface MetodoPago {
    void pagar(double cantidad);
}

class PagoTarjeta implements MetodoPago {
    public void pagar(double cantidad) {
        System.out.println("Pagando con tarjeta: " + cantidad);
    }
}

class PagoPaypal implements MetodoPago {
    public void pagar(double cantidad) {
        System.out.println("Pagando con PayPal: " + cantidad);
    }
}

class Carrito {
    private MetodoPago metodoPago;

    Carrito(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
    void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
    void pagar(double cantidad) { metodoPago.pagar(cantidad); }
}
