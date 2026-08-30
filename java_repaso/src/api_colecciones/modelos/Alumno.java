package api_colecciones.modelos;

public class Alumno implements Comparable<Alumno> {
    private String nombre;
    private Integer nota;

    public Alumno() {

    }

    public Alumno(String nombre, Integer nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    @Override
    public int compareTo(Alumno a) {
        /*
         * if (this.nombre == null) {
         * return 0;
         * }
         * return this.nombre.compareTo(a.getNombre());
         */
        /*
         * if (this.nota == a.nota) {
         * return 0;
         * } else if (this.nota > a.nota) {
         * return 1;
         * } else {
         * return -1;
         * }
         */
        if (this.nota == null) {
            return 0;
        }
        return this.nota.compareTo(a.nota);
    }

    @Override
    public String toString() {
        return "Alumno(nombre = " + nombre + ", nota = " + nota + ")";
    }
}
