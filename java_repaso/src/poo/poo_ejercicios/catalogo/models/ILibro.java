package poo.poo_ejercicios.catalogo.models;

import java.util.Date;

public interface ILibro {

    Date getFechaPublicacion();
    String getEditorial();
    String getAutor();
    String getTitulo();
}
