/**
 * ADAPTER
 *
 * Objetivo:
 * Permitir que dos interfaces incompatibles trabajen juntas.
 *
 * Cuándo usarlo:
 * - Integrar código antiguo con una interfaz nueva.
 * - Adaptar librerías externas.
 *
 * Ventajas:
 * - Reutiliza clases existentes.
 * - Reduce cambios en código legacy.
 *
 * Desventajas:
 * - Añade una capa extra.
 */
public class AdapterEjemplo {

    public static void main(String[] args) {
        Reproductor reproductor = new AdaptadorMp3();
        reproductor.reproducir("cancion.mp3");
    }
}

interface Reproductor {
    void reproducir(String archivo);
}

class ReproductorAntiguoMp3 {
    void playMp3(String archivo) {
        System.out.println("Reproduciendo MP3 antiguo: " + archivo);
    }
}

class AdaptadorMp3 implements Reproductor {
    private ReproductorAntiguoMp3 antiguo = new ReproductorAntiguoMp3();

    public void reproducir(String archivo) {
        antiguo.playMp3(archivo);
    }
}
