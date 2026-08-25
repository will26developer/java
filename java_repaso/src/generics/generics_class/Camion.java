package generics.generics_class;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Camion<T> implements Iterable<T> {
    private List<T> objetos;
    private int maxCapacidad;

    public Camion(int maxCapacidad) {
        this.maxCapacidad = maxCapacidad;
        this.objetos = new ArrayList();
    }

    public void addObjectos(T obj) {
        if (this.objetos.size() <= this.maxCapacidad) {
            this.objetos.add(obj);
        } else {
            System.out.println("Camion lleno");
            throw new RuntimeException("Camion lleno");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.objetos.iterator();
    }
}
