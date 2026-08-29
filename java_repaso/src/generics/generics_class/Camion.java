package generics.generics_class;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Camion<T> implements Iterable<T> {
    private List<T> objetos;
    private int max;

    public Camion(int max) {
        this.max = max;
        this.objetos = new ArrayList<>();
    }

    public void add(T object) {
        if (objetos.size() <= max) {
            this.add(object);
        } else {
            throw new RuntimeException("Se lleno el carro.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.objetos.iterator();
    }
}
