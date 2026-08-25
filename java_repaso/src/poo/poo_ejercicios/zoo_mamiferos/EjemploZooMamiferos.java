package poo.poo_ejercicios.zoo_mamiferos;

import poo.poo_ejercicios.zoo_mamiferos.models.Guepardo;
import poo.poo_ejercicios.zoo_mamiferos.models.Leon;
import poo.poo_ejercicios.zoo_mamiferos.models.Lobo;
import poo.poo_ejercicios.zoo_mamiferos.models.Mamifero;
import poo.poo_ejercicios.zoo_mamiferos.models.Perro;
import poo.poo_ejercicios.zoo_mamiferos.models.Tigre;

public class EjemploZooMamiferos {
    public static void main(String[] args) {

        Guepardo guepardo = new Guepardo("Sabana africana", 0.9f, 1.4f, 55f,
                "Acinonyx jubatus", 4.5f, 110);

        Leon leon = new Leon("Sabana africana", 1.2f, 2.0f, 190f,
                "Panthera leo", 7.5f, 80, 12, 114f);

        Tigre tigre = new Tigre("Selva asiatica", 1.0f, 2.8f, 250f,
                "Panthera tigris", 9.0f, 65, "Tigre de Bengala");

        Perro perro = new Perro("Domestico", 0.6f, 1.1f, 30f,
                "Canis lupus familiaris", "Marron", 2.5f);

        Lobo lobo = new Lobo("Bosque templado", 0.8f, 1.5f, 45f,
                "Canis lupus", "Gris", 5.0f, 6, "Lobo iberico");

        Mamifero[] mamiferos = {guepardo, leon, tigre, perro, lobo};

        for (Mamifero mamifero : mamiferos) {
            System.out.println(mamifero.comer());
            System.out.println(mamifero.dormir());
            System.out.println(mamifero.correr());
            System.out.println(mamifero.comunicarse());
            System.out.println();
        }
    }
}
