package poo.poo_ejercicios.zoo_mamiferos.models;

public class Tigre extends Felino {
    private String especieTigre;

    public Tigre(String habitat, Float altura, Float largo, Float peso, String nombreCientifico, Float tamanoGarras, Integer velocidad, String especieTigre) {
        super(habitat, altura, largo, peso, nombreCientifico, tamanoGarras, velocidad);
        this.especieTigre = especieTigre;
    }

    public String getEspecieTigre() {
        return especieTigre;
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
        return super.toString() + " , especieTigre = " + especieTigre;
    }
}
