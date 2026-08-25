package generics.generics_ejercicio.repositorio;

import generics.generics_ejercicio.models.Producto;

import java.util.List;

public interface BolsaRepositorio<T extends Producto> {
    void addProductos(T obj);
     List<T> getProductos();
}
