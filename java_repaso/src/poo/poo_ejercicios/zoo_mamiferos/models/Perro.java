package poo.poo_ejercicios.zoo_mamiferos.models;

public class Perro extends Canino {
    private String color;
    private Float tamanoColmillos;

    public Perro(String habitat, Float altura, Float largo, Float peso, String nombreCientifico, String color, Float tamanoColmillos) {
        super(habitat, altura, largo, peso, nombreCientifico, color, tamanoColmillos);
        this.color = color;
        this.tamanoColmillos = tamanoColmillos;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public Float getTamanoColmillos() {
        return tamanoColmillos;
    }

    @Override
    public String comer() {
        return this + " esta comiendo";
    }

    @Override
    public String dormir() {
        return this + " esta durmiendo";
    }

    @Override
    public String correr() {
        return this + " esta corriendo";
    }

    @Override
    public String comunicarse() {
        return this + " se esta comunicando";
    }

    @Override
    public String toString() {
        return super.toString() + " , color = " + color + ", tamanoColmillos = " + tamanoColmillos;
    }
}
