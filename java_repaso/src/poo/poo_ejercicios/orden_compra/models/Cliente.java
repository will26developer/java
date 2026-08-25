package poo.poo_ejercicios.orden_compra.models;

public class Cliente {
    private String nombre;
    private String apellido;

    public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }


    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Cliente(nombre = " + nombre + ", apellido = " + apellido + ")";
    }
}
