package poo.poo_ejercicios.zoo_mamiferos.models;

public class Guepardo extends Felino {
    public Guepardo(String habitat, Float altura, Float largo, Float peso, String nombreCientifico, Float tamanoGarras, Integer velocidad) {
        super(habitat, altura, largo, peso, nombreCientifico, tamanoGarras, velocidad);
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
        return super.toString();
    }
}
