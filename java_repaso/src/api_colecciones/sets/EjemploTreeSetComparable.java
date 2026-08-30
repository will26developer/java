package api_colecciones.sets;

import java.util.Set;
import java.util.TreeSet;

import api_colecciones.modelos.Alumno;

public class EjemploTreeSetComparable {
    public static void main(String[] args) {
        Set<Alumno> alumnos = new TreeSet<>((a, b) -> b.getNota().compareTo(a.getNota()));

        alumnos.add(new Alumno("Pato", 5));
        alumnos.add(new Alumno("Paco", 7));
        alumnos.add(new Alumno("Alberto", 10));
        alumnos.add(new Alumno("Jano", 9));
        alumnos.add(new Alumno("Pedro", 1));
        alumnos.add(new Alumno("Zeus", 9));

        System.out.println("alumnos = " + alumnos);
    }

}
