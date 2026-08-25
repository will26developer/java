/**
 * STATE
 *
 * Objetivo:
 * Cambiar el comportamiento de un objeto cuando cambia su estado interno.
 *
 * Cuándo usarlo:
 * - Estados de pedido: nuevo, pagado, enviado.
 * - Máquinas de estado.
 * - Flujos con reglas diferentes por estado.
 *
 * Ventajas:
 * - Evita if/switch enormes por estado.
 *
 * Desventajas:
 * - Añade clases por cada estado.
 */
public class StateEjemplo {

    public static void main(String[] args) {
        Pedido pedido = new Pedido();
        pedido.avanzar();
        pedido.avanzar();
    }
}

interface EstadoPedido {
    void avanzar(Pedido pedido);
}

class EstadoNuevo implements EstadoPedido {
    public void avanzar(Pedido pedido) {
        System.out.println("Pedido pagado");
        pedido.setEstado(new EstadoPagado());
    }
}

class EstadoPagado implements EstadoPedido {
    public void avanzar(Pedido pedido) {
        System.out.println("Pedido enviado");
    }
}

class Pedido {
    private EstadoPedido estado = new EstadoNuevo();
    void setEstado(EstadoPedido estado) { this.estado = estado; }
    void avanzar() { estado.avanzar(this); }
}
