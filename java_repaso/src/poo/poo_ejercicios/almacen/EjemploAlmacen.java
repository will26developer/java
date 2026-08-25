package poo.poo_ejercicios.almacen;

import poo.poo_ejercicios.almacen.models.*;

public class EjemploAlmacen {
    public static void main(String[] args) {
        Lacteo lacteo = new Lacteo("Leche", 10.0, 100, 100);
        Limpieza limpieza = new Limpieza("Limpieza", 20.0, "Agua, Luz, Aire", 10.0);
        Fruta fruta = new Fruta("Manzana", 5.0, 100.0, "Naranja");
        NoPerecible noPerecible = new NoPerecible("macarrones",10.0,300,300);

        Producto[] productos = {lacteo,limpieza,fruta,noPerecible};

        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
            System.out.println(producto.toString());
        }

        System.out.println("Total: " + total);
    }
}
