package generics;

import generics.generics_class.Animal;
import generics.generics_class.Automovil;
import generics.generics_class.Camion;
import generics.generics_class.Maquinaria;

public class EjemploGenericosClass {
    public static void main(String[] args) {
        Camion<Animal> transporteCaballos = new Camion<>(5);
        transporteCaballos.add(new Animal("Peregrino", "Caballo"));
        transporteCaballos.add(new Animal("Grillo", "Caballo"));
        transporteCaballos.add(new Animal("Alberto", "Caballo"));
        transporteCaballos.add(new Animal("Pedro", "Caballo"));

        for (Animal animal : transporteCaballos) {
            System.out.println(animal.toString());
        }

        Camion<Maquinaria> transporteMaquinaria = new Camion<>(3);
        transporteMaquinaria.add(new Maquinaria("Lavadora"));
        transporteMaquinaria.add(new Maquinaria("Cocina"));
        transporteMaquinaria.add(new Maquinaria("Frigorifico"));

        for (Maquinaria maquina : transporteMaquinaria) {
            System.out.println(maquina.toString());
        }

        Camion<Automovil> transporteAutomovil = new Camion<>(2);
        transporteAutomovil.add(new Automovil("Subaru"));
        transporteAutomovil.add(new Automovil("Mazda"));

        for (Automovil auto : transporteAutomovil) {
            System.out.println(auto.toString());
        }
    }
}
