package poo.poo_ejercicios.empresa_personas;

import poo.poo_ejercicios.empresa_personas.models.Gerente;

public class EjemploEmpresaPersona {
    public static void main(String[] args) {
        Gerente gerente = new Gerente("William", "Martinez", "123456789", "Calle 123", 100000.0, 1, 1000000.0);
        System.out.println("gerente = " + gerente);
    }
}
