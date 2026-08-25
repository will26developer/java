package poo.poo_ejercicios.zoo_mamiferos.models;

public class Leon extends Felino {
    private Integer numManada;
    private Float potenciaRugidoDecibel;

    public Leon(String habitat, Float altura, Float largo, Float peso, String nombreCientifico, Float tamanoGarras, Integer velocidad, Integer numManada, Float potenciaRugidoDecibel) {
        super(habitat, altura, largo, peso, nombreCientifico, tamanoGarras, velocidad);
        this.numManada = numManada;
        this.potenciaRugidoDecibel = potenciaRugidoDecibel;
    }

    public Integer getNumManada() {
        return numManada;
    }

    public Float getPotenciaRugidoDecibel() {
        return potenciaRugidoDecibel;
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
        return super.toString() + " , numManada = " + numManada + ", potenciaRugidoDecibel = " + potenciaRugidoDecibel;
    }
}
