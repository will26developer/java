package generics;

import generics.generics_class.Animal;
import generics.generics_class.Automovil;
import generics.generics_class.Camion;
import generics.generics_class.Maquinaria;

public class EjemploGenericClass {
    public static <T> void imprimirCamion(Camion<T> camion) {
        for (T object: camion) {
            if (object instanceof Animal) {
                System.out.println(((Animal) object).getNombre() + " " + ((Animal) object).getTipo());
            } else if (object instanceof Automovil) {
                System.out.println(((Automovil) object).getMarca());
            } else if (object instanceof Maquinaria) {
                System.out.println(((Maquinaria) object).getTipo());
            }
        }
    }

    public static void main(String[] args) {
        Camion<Animal> camion = new Camion<Animal>(5);
        Animal animal = new Animal("Perro", "Mamifero");
        Animal animal1 = new Animal("Gato", "Mamifero");
        Animal animal2 = new Animal("Pez", "Ave");
        Animal animal3 = new Animal("Tortuga", "Ave");
        Animal animal4 = new Animal("Caballo", "Mamifero");
        Animal animal5 = new Animal("Caballo", "Mamifero");

        camion.addObjectos(animal);
        camion.addObjectos(animal1);
        camion.addObjectos(animal2);
        camion.addObjectos(animal3);
        camion.addObjectos(animal4);
        camion.addObjectos(animal5);

        for (Animal object: camion) {
            System.out.println("nombre = " + object.getNombre() + ", tipo = " + object.getTipo());
        }

        Camion<Maquinaria> camionTransporteMaquinaria = new Camion<Maquinaria>(3);
        camionTransporteMaquinaria.addObjectos(new Maquinaria("Cocina"));
        camionTransporteMaquinaria.addObjectos(new Maquinaria("Lavadora"));
        camionTransporteMaquinaria.addObjectos(new Maquinaria("Frigorifico"));

        for (Maquinaria object: camionTransporteMaquinaria) {
            System.out.println(object.getTipo());
        }

        Camion<Automovil> camionTransAutomovil = new Camion<>(3);
        camionTransAutomovil.addObjectos(new Automovil("Mazda"));
        camionTransAutomovil.addObjectos(new Automovil("Toyota"));
        camionTransAutomovil.addObjectos(new Automovil("Ford"));

        for (Automovil object: camionTransAutomovil) {
            System.out.println(object.getMarca());
        }
    }
}
