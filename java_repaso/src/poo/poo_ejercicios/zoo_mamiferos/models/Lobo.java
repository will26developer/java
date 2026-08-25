package poo.poo_ejercicios.zoo_mamiferos.models;

public class Lobo extends Canino {
    private Integer numCamada;
    private String especieLobo;

    public Lobo(String habitat, Float altura, Float largo, Float peso, String nombreCientifico, String color, Float tamanoColmillos, Integer numCamada, String especieLobo) {
        super(habitat, altura, largo, peso, nombreCientifico, color, tamanoColmillos);
        this.numCamada = numCamada;
        this.especieLobo = especieLobo;
    }

    public Integer getNumCamada() {
        return numCamada;
    }

    public String getEspecieLobo() {
        return especieLobo;
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
        return super.toString() + " , numCamada = " + numCamada + ", especieLobo = " + especieLobo;
    }
}
