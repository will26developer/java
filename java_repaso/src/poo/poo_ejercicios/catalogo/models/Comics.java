package poo.poo_ejercicios.catalogo.models;

import java.util.Date;

public class Comics extends Libro {
    private String personaje;

    public Comics(int precio, Date fechaPublicacion, String autor, String editorial, String titulo, String personaje) {
        super(precio, fechaPublicacion, autor, editorial, titulo);
        this.personaje = personaje;
    }

    @Override
    public double getPrecioVenta() {
        return super.getPrecioVenta();
    }
}
