package generics.generics_ejercicio.servicio;

import generics.generics_ejercicio.models.Producto;
import generics.generics_ejercicio.repositorio.BolsaRepositorio;

import java.util.ArrayList;
import java.util.List;

public class BolsaSupermercadoServicio implements BolsaRepositorio<Producto> {

    private int id;
    private List<Producto> productos;
    private int maxCapacidad;
    private static int ultimoId;

    public BolsaSupermercadoServicio() {
        this.id = ++ultimoId;
    }

    public BolsaSupermercadoServicio(int maxCapacidad) {
        this();
        this.productos = new ArrayList<>();
        this.maxCapacidad = maxCapacidad;
    }

    @Override
    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public void  addProductos(Producto obj) {
        if (productos.size() <= maxCapacidad ) {
            this.productos.add(obj);
        } else {
            throw new RuntimeException("Bolsa llena");
        }
    }

    @Override
    public String toString() {
        return "BolsaSupermercadoServicio(id = " + id + ", productos = " + productos + ", maxCapacidad = " + maxCapacidad + ")";
    }
}
