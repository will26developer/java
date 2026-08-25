package generics.generics_ejercicio.models;

public class Producto {
    private String nombre;
    private Double precio;

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public String toString() {
        return "Producto(nombre = " + nombre + ", precio = " + precio + ")";
    }
}
