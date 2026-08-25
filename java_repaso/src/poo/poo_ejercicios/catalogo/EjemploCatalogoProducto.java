package poo.poo_ejercicios.catalogo;

import poo.poo_ejercicios.catalogo.models.IPhone;
import poo.poo_ejercicios.catalogo.models.Libro;
import poo.poo_ejercicios.catalogo.models.TvLcd;

import java.util.Date;

public class EjemploCatalogoProducto {
    public static void main(String[] args) {
        IPhone iphone = new IPhone(1000, "Apple", "iPhone 14", "Azul");
        System.out.println("iphone = " + iphone);
        System.out.println("precio = " + iphone.getPrecioVenta());

        TvLcd tvLcd = new TvLcd(2000, "Samsung", 55);
        System.out.println("tvLcd = " + tvLcd);
        System.out.println("precio = " + tvLcd.getPrecioVenta());

        Libro libro = new Libro(15,new Date(),"Stephen King","La Casa del Libro","Nisery");
    }
}
