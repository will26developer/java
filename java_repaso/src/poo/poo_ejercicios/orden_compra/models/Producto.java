package poo.poo_ejercicios.orden_compra.models;

public class Producto {
    private String fabricante;
    private String nombre;
    private Double precio;

    public Producto(String fabricante, String nombre, Double precio) {
        this.fabricante = fabricante;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto(fabricante = " + fabricante + ", nombre = " + nombre + ", precio = " + precio + ")";
    }
}
