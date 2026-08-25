/**
 * PROXY
 *
 * Objetivo:
 * Controlar el acceso a otro objeto.
 *
 * Cuándo usarlo:
 * - Seguridad.
 * - Carga perezosa.
 * - Logging, caché o control remoto.
 *
 * Ventajas:
 * - Añade control sin cambiar el objeto real.
 *
 * Desventajas:
 * - Puede ocultar complejidad.
 */
public class ProxyEjemplo {

    public static void main(String[] args) {
        Imagen imagen = new ImagenProxy("foto.png");
        imagen.mostrar();
        imagen.mostrar();
    }
}

interface Imagen { void mostrar(); }

class ImagenReal implements Imagen {
    private String archivo;

    ImagenReal(String archivo) {
        this.archivo = archivo;
        cargarDesdeDisco();
    }

    private void cargarDesdeDisco() {
        System.out.println("Cargando " + archivo);
    }

    public void mostrar() {
        System.out.println("Mostrando " + archivo);
    }
}

class ImagenProxy implements Imagen {
    private ImagenReal imagenReal;
    private String archivo;

    ImagenProxy(String archivo) { this.archivo = archivo; }

    public void mostrar() {
        if (imagenReal == null) {
            imagenReal = new ImagenReal(archivo);
        }
        imagenReal.mostrar();
    }
}
