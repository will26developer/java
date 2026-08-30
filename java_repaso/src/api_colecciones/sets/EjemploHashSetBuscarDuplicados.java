package api_colecciones.sets;

import java.util.HashSet;
import java.util.Set;

public class EjemploHashSetBuscarDuplicados {
    public static void main(String[] args) {
        String[] peces = { "Corvina", "Lenguado", "Corvina", "Pejerrey", "Robalo", "Atun", "Lenguado" };
        Set<String> unicos = new HashSet<>();

        for (String pez : peces) {
            if (!unicos.add(pez)) {
                System.out.println("Elemento duplicado = " + pez);
            }
        }
        System.out.println(unicos.size() + " elementos no duplicados " + unicos);
    }
}
