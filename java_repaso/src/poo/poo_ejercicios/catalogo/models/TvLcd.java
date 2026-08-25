package poo.poo_ejercicios.catalogo.models;

public class TvLcd extends Electronico {
    private int pulgada;

    public TvLcd(int precio, String fabricante, int pulgada) {
        super(precio, fabricante);
        this.pulgada = pulgada;
    }

    @Override
    public double getPrecioVenta() {
        return super.getPrecio() + (super.getPrecio() * 0.21);
    }
}
