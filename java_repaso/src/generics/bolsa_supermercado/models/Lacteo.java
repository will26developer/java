package generics.bolsa_supermercado.models;

public class Lacteo extends Producto {
    private int cantidad;
    private int proteina;

    public Lacteo(String nombre, Double precio, int cantidad, int proteina) {
        super(nombre, precio);
        this.cantidad = cantidad;
        this.proteina = proteina;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getProteina() {
        return proteina;
    }

    @Override
    public String toString() {
        return "Lacteo(" + super.toString() + ", cantidad = " + cantidad + ", proteina = " + proteina + ")";
    }

}
