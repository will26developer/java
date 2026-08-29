package generics.generics_class;

public class Automovil {
    private String marca;

    public Automovil(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Automovil [marca=" + marca + "]";
    }

}
