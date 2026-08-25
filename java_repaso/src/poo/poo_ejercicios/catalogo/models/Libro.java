package poo.poo_ejercicios.catalogo.models;

import java.util.Date;

public class Libro extends Producto implements ILibro {
    private Date fechaPublicacion;
    private String autor;
    private String editorial;
    private String titulo;

    public Libro(int precio, Date fechaPublicacion, String autor, String editorial, String titulo) {
        super(precio);
        this.fechaPublicacion = fechaPublicacion;
        this.autor = autor;
        this.editorial = editorial;
        this.titulo = titulo;
    }

    @Override
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    @Override
    public String getEditorial() {
        return editorial;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public double getPrecioVenta() {
        return super.getPrecio() + (super.getPrecio() * 0.21);
    }
}
