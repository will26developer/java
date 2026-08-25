package poo.poo_ejercicios.empresa_personas.models;

public class Empleado extends Persona {
    private Double remuneracion;
    private int empleadoId;

    public Empleado(String nombre, String apellido, String numeroFiscal, String direccion, Double remuneracion, int empleadoId) {
        super(nombre, apellido, numeroFiscal, direccion);
        this.remuneracion = remuneracion;
        this.empleadoId = empleadoId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public Double getRemuneracion() {
        return remuneracion;
    }

    public void aumentarRemuneracion(int porcentaje) {
        this.remuneracion = remuneracion + (this.remuneracion * porcentaje) / 100;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "remuneracion=" + remuneracion +
                ", empleadoId=" + empleadoId + " ," + super.toString() ;
    }
}
