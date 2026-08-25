/**
 * SINGLETON
 *
 * Objetivo:
 * Garantizar que una clase tenga una única instancia y proporcionar un punto global de acceso.
 *
 * Cuándo usarlo:
 * - Configuración global de la aplicación.
 * - Logger compartido.
 * - Gestores de conexión o caché, con cuidado.
 *
 * Ventajas:
 * - Controla la creación de instancias.
 * - Evita objetos duplicados innecesarios.
 *
 * Desventajas:
 * - Puede ocultar dependencias globales.
 * - Puede dificultar testing si se abusa.
 */
public class SingletonEjemplo {

    public static void main(String[] args) {
        Configuracion c1 = Configuracion.getInstancia();
        Configuracion c2 = Configuracion.getInstancia();

        System.out.println(c1 == c2); // true
        c1.mostrarConfiguracion();
    }
}

class Configuracion {
    private static Configuracion instancia;

    private Configuracion() {}

    public static Configuracion getInstancia() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public void mostrarConfiguracion() {
        System.out.println("Configuración global cargada");
    }
}
