package poo.poo_ejercicios.zoo_mamiferos.models;

public class Felino extends Mamifero {
    private Float tamanoGarras;
    private Integer velocidad;

    public Felino(String habitat, Float altura, Float largo, Float peso, String nombreCientifico, Float tamanoGarras, Integer velocidad) {
        super(habitat, altura, largo, peso, nombreCientifico);
        this.tamanoGarras = tamanoGarras;
        this.velocidad = velocidad;
    }

    public Float getTamanoGarras() {
        return tamanoGarras;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    @Override
    public String comer() {
        return "";
    }

    @Override
    public String dormir() {
        return "";
    }

    @Override
    public String correr() {
        return "";
    }

    @Override
    public String comunicarse() {
        return "";
    }

    @Override
    public String toString() {
        return super.toString() + " , tamanoGarras = " + tamanoGarras + ", velocidad = " + velocidad;
    }
}
