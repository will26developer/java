/**
 * FACADE
 *
 * Objetivo:
 * Proporcionar una interfaz simple para un subsistema complejo.
 *
 * Cuándo usarlo:
 * - Simplificar APIs internas complejas.
 * - Ocultar muchos pasos detrás de un método claro.
 *
 * Ventajas:
 * - Código cliente más simple.
 * - Menos acoplamiento.
 *
 * Desventajas:
 * - Puede convertirse en una clase demasiado grande si se abusa.
 */
public class FacadeEjemplo {

    public static void main(String[] args) {
        HomeCinemaFacade homeCinema = new HomeCinemaFacade();
        homeCinema.verPelicula();
    }
}

class Proyector { void encender() { System.out.println("Proyector encendido"); } }
class Sonido { void activar() { System.out.println("Sonido activado"); } }
class Luces { void apagar() { System.out.println("Luces apagadas"); } }

class HomeCinemaFacade {
    private Proyector proyector = new Proyector();
    private Sonido sonido = new Sonido();
    private Luces luces = new Luces();

    void verPelicula() {
        luces.apagar();
        proyector.encender();
        sonido.activar();
        System.out.println("Película lista");
    }
}
