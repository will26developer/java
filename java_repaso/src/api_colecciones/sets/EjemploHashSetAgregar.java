package api_colecciones.sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EjemploHashSetAgregar {
    public static void main(String[] args) {
        Set<String> hs = new HashSet<>();
        System.out.println(hs.add("uno"));
        System.out.println(hs.add("dos"));
        System.out.println(hs.add("tres"));
        System.out.println(hs.add("cuatro"));
        System.out.println(hs.add("cinco"));

        List<String> lista = new ArrayList<>(hs);
        Collections.sort(lista);

        boolean b = hs.add("tres");
        System.out.println("permite elementos duplicados = " + b);
        System.out.println(hs);
    }
}
