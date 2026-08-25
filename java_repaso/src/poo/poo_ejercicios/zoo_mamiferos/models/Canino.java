package poo.poo_ejercicios.zoo_mamiferos.models;

public class Canino extends Mamifero {
    private String color;
    private Float tamanoColmillos;

    public Canino(String habitat, Float altura, Float largo, Float peso, String nombreCientifico, String color, Float tamanoColmillos) {
        super(habitat, altura, largo, peso, nombreCientifico);
        this.color = color;
        this.tamanoColmillos = tamanoColmillos;


    }

    public String getColor() {
        return color;
    }

    public Float getTamanoColmillos() {
        return tamanoColmillos;
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
        return super.toString() + " , color = " + color + ", tamanoColmillos = " + tamanoColmillos;
    }
}
