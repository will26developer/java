package api_colecciones.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api_colecciones.modelos.Alumno;

public class EjemploListComparator {
    public static void main(String[] args) {
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Pato", 5));
        alumnos.add(new Alumno("Paco", 7));
        alumnos.add(new Alumno("Alberto", 10));
        alumnos.add(new Alumno("Jano", 9));
        alumnos.add(new Alumno("Pedro", 1));
        alumnos.add(new Alumno("Zeus", 9));
        alumnos.add(new Alumno("Jano", 9));
        alumnos.add(new Alumno("Pedro", 1));
        alumnos.add(new Alumno("Zeus", 9));
        Collections.sort(alumnos);
        System.out.println("alumnos = " + alumnos);
    }
}
