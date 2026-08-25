package generics.generics_ejercicio;

import generics.generics_ejercicio.models.Fruta;
import generics.generics_ejercicio.models.Lacteo;
import generics.generics_ejercicio.models.Limpieza;
import generics.generics_ejercicio.models.NoPerecible;
import generics.generics_ejercicio.servicio.BolsaSupermercadoServicio;

public class EjemploBolsaSupermercado {
    public static void main(String[] args) {
        Lacteo lacteo = new Lacteo("Leche", 10.0, 100, 100);
        Fruta fruta = new Fruta("Manzana", 5.0, 100.0, "Naranja");
        NoPerecible noPerecible = new NoPerecible("macarrones",10.0,300,300);
        Limpieza limpieza = new Limpieza("Limpieza", 20.0, "Agua, Luz, Aire", 10.0);

        BolsaSupermercadoServicio bolsaSupermercadoServicio = new BolsaSupermercadoServicio(5);
        bolsaSupermercadoServicio.addProductos(lacteo);
        bolsaSupermercadoServicio.addProductos(fruta);
        bolsaSupermercadoServicio.addProductos(limpieza);
        bolsaSupermercadoServicio.addProductos(noPerecible);

        System.out.println(bolsaSupermercadoServicio.getProductos());
        System.out.println("bolsaSupermercadoServicio = " + bolsaSupermercadoServicio);

    }
}
