package api_colecciones.sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import api_colecciones.modelos.Alumno;

public class EjemploHashSetIteraciones {
    public static void main(String[] args) {

        Set<Alumno> alumnos = new HashSet<>();
        alumnos.add(new Alumno("Pato", 5));
        alumnos.add(new Alumno("Paco", 7));
        alumnos.add(new Alumno("Alberto", 10));
        alumnos.add(new Alumno("Jano", 9));
        alumnos.add(new Alumno("Pedro", 1));
        alumnos.add(new Alumno("Zeus", 9));
        alumnos.add(new Alumno("Jano", 9));
        alumnos.add(new Alumno("Pedro", 1));
        alumnos.add(new Alumno("Zeus", 9));
        System.out.println("alumnos = " + alumnos);

        System.out.println("Iterando usando un for-each");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno.toString());
        }

        System.out.println("Iterando usando un while");
        Iterator<Alumno> alumnosIterator = alumnos.iterator();

        while (alumnosIterator.hasNext()) {
            Alumno alumno = alumnosIterator.next();
            System.out.println(alumno.toString());
        }

        System.out.println("Iterando utilizando un stream forEach");
        alumnos.forEach(System.out::println);
    }
}
