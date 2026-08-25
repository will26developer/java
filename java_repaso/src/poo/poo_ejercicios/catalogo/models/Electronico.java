package poo.poo_ejercicios.catalogo.models;

public abstract class Electronico extends Producto implements IElectronico {
    private String fabricante;

    public Electronico(int precio, String fabricante) {
        super(precio);
        this.fabricante = fabricante;
    }

    public String getFabricante() {
        return fabricante;
    }
}
