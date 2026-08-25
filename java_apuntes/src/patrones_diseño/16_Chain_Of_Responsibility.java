/**
 * CHAIN OF RESPONSIBILITY
 *
 * Objetivo:
 * Pasar una petición por una cadena de manejadores hasta que uno la procese.
 *
 * Cuándo usarlo:
 * - Validaciones encadenadas.
 * - Filtros HTTP.
 * - Sistemas de soporte por nivel.
 *
 * Ventajas:
 * - Desacopla emisor y receptor.
 *
 * Desventajas:
 * - Puede ser difícil saber quién procesará la petición.
 */
public class ChainOfResponsibilityEjemplo {

    public static void main(String[] args) {
        Handler basico = new SoporteBasico();
        Handler avanzado = new SoporteAvanzado();
        basico.setSiguiente(avanzado);

        basico.manejar("AVANZADO");
    }
}

abstract class Handler {
    protected Handler siguiente;
    void setSiguiente(Handler siguiente) { this.siguiente = siguiente; }
    abstract void manejar(String tipo);
}

class SoporteBasico extends Handler {
    void manejar(String tipo) {
        if (tipo.equals("BASICO")) {
            System.out.println("Resuelto por soporte básico");
        } else if (siguiente != null) {
            siguiente.manejar(tipo);
        }
    }
}

class SoporteAvanzado extends Handler {
    void manejar(String tipo) {
        if (tipo.equals("AVANZADO")) {
            System.out.println("Resuelto por soporte avanzado");
        }
    }
}
