package poo.poo_ejercicios.orden_compra.models;

import java.util.Date;

public class OrdenCompra {
    private int id;
    private String descripcion;
    private Date fecha;
    private Cliente cliente;
    private Producto[] productos;
    private static int ultimoId;
    private static int index;

    public OrdenCompra(String descripcion) {
        this.descripcion = descripcion;
        this.id = ++ultimoId;
        productos = new Producto[4];
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void addProductos(Producto obj) {
        if (!(obj instanceof Producto)) {
            return;
        }
        if (index <= 3) {
            productos[index++] = obj;
        } else {
            System.out.println("Carrito de la compra lleno");
        }
    }

    public double getTotal() {
        double total = 0;
        for (Producto producto : productos) {
            if (producto != null) {
                total += producto.getPrecio();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "OrdenCompra(id = " + id + ", descripcion = " + descripcion + ", fecha = " + fecha + ", cliente = " + cliente.toString() + ", productos = " + productos + ")";
    }
}
