package api_colecciones.sets;

import java.util.HashSet;
import java.util.Set;

public class EjemploHashSetBuscarDuplicados2 {
    public static void main(String[] args) {
        String[] peces = { "Corvina", "Lenguado", "Corvina", "Pejerrey", "Robalo", "Atun", "Lenguado" };
        Set<String> unicos = new HashSet<>();
        Set<String> duplicados = new HashSet<>();

        for (String pez : peces) {
            if (!unicos.add(pez)) {
                duplicados.add(pez);
            }
        }
        unicos.removeAll(duplicados);
        System.out.println("Unicos: " + unicos);
        System.out.println("Duplicados: " + duplicados);
    }
}
