package poo.poo_ejercicios.catalogo.models;

public class IPhone extends Electronico {
    private String modelo;
    private String color;

    public IPhone(int precio, String fabricante, String modelo, String color) {
        super(precio, fabricante);
        this.modelo = modelo;
        this.color = color;
    }

    @Override
    public double getPrecioVenta() {
        return super.getPrecio() + (super.getPrecio() * 0.21);
    }
}
