package generics.bolsa_supermercado.services;

import java.util.ArrayList;
import java.util.List;

import generics.bolsa_supermercado.exceptions.BolsaSupermercadoLlenaException;
import generics.bolsa_supermercado.models.Producto;

public class BolsaSupermercadoService<T extends Producto> {
    private List<T> bolsaCompra;
    private int capacity;

    public BolsaSupermercadoService(int capacity) {
        this.capacity = capacity;
        this.bolsaCompra = new ArrayList<>();
    }

    public void addProductos(T obj) throws BolsaSupermercadoLlenaException {
        if (obj instanceof Producto && bolsaCompra.size() <= capacity) {
            this.bolsaCompra.add(obj);
        } else {
            throw new BolsaSupermercadoLlenaException("Bolsa llena, coja otra bolsa");
        }
    }

    public List<T> getProductos() {
        return bolsaCompra;
    }

    public void mostrarProductos() {
        for (Producto producto : bolsaCompra) {
            System.out.println(producto.toString());
        }
    }
}
