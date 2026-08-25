package poo.poo_ejercicios.orden_compra;

import poo.poo_ejercicios.orden_compra.models.Cliente;
import poo.poo_ejercicios.orden_compra.models.OrdenCompra;
import poo.poo_ejercicios.orden_compra.models.Producto;

import java.util.Date;

public class EjemploOrdenCompra {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("William", "Martinez");
        OrdenCompra ordenCompra = new OrdenCompra("Compra de productos");
        ordenCompra.setCliente(cliente);
        ordenCompra.setFecha(new Date());

        Producto producto1 = new Producto("Fabricante 1", "Producto 1", 100.0);
        Producto producto2 = new Producto("Fabricante 2", "Producto 2", 200.0);
        Producto producto3 = new Producto("Fabricante 3", "Producto 3", 300.0);
        Producto producto4 = new Producto("Fabricante 4", "Producto 4", 400.0);

        ordenCompra.addProductos(producto1);
        ordenCompra.addProductos(producto2);
        ordenCompra.addProductos(producto3);
        ordenCompra.addProductos(producto4);

        System.out.println(ordenCompra.toString());
        System.out.println("Total: " + ordenCompra.getTotal());
    }
}
