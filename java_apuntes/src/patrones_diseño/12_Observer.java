import java.util.*;

/**
 * OBSERVER
 *
 * Objetivo:
 * Notificar automáticamente a varios objetos cuando cambia el estado de otro.
 *
 * Cuándo usarlo:
 * - Sistemas de eventos.
 * - Notificaciones.
 * - Interfaces gráficas.
 *
 * Ventajas:
 * - Bajo acoplamiento entre emisor y receptores.
 *
 * Desventajas:
 * - Puede ser difícil seguir el flujo si hay muchos observadores.
 */
public class ObserverEjemplo {

    public static void main(String[] args) {
        Canal canal = new Canal();
        canal.suscribir(new Usuario("Ana"));
        canal.suscribir(new Usuario("Pedro"));

        canal.publicar("Nuevo vídeo de Java");
    }
}

interface Observador {
    void actualizar(String mensaje);
}

class Usuario implements Observador {
    private String nombre;
    Usuario(String nombre) { this.nombre = nombre; }
    public void actualizar(String mensaje) {
        System.out.println(nombre + " recibió: " + mensaje);
    }
}

class Canal {
    private List<Observador> observadores = new ArrayList<>();

    void suscribir(Observador observador) { observadores.add(observador); }

    void publicar(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(mensaje);
        }
    }
}
