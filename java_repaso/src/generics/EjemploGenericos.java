package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import generics.modelos.Cliente;
import generics.modelos.ClientePremium;

public class EjemploGenericos {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(
                new Cliente("William", "Martinez Triana"));
        Cliente cliente = clientes.iterator().next();

        Cliente[] clientesArreglo = {
                new Cliente("Luci", "Martinez"),
                new Cliente("Andres", "Guzman")
        };
        Integer[] numeros = { 1, 2, 3, 4, 5 };

        List<Cliente> clientes2 = fromArrayToList(clientesArreglo);
        List<Integer> numeros2 = fromArrayToList(numeros);

        clientes2.forEach(System.out::println);
        numeros2.forEach(System.out::println);

        List<String> nombres = fromArrayToList(new String[] {
                "William", "Veronica", "Angel", "Jorge"
        }, new Integer[] { 2, 4, 6, 8, 10 });
        System.out.println(nombres);

        ClientePremium[] clientePremiums = {
                new ClientePremium("William", "Martinez"),
                new ClientePremium("Luci", "Gutierrez"),
                new ClientePremium("Alberto", "Fernandez")
        };

        List<ClientePremium> clientesPremiumsList = fromArrayToList(clientePremiums);
        clientesPremiumsList.forEach(System.out::println);

        imprimirClientes(clientes2);
        imprimirClientes(clientesPremiumsList);

        System.out.println("Maximo entre 1, 9, 5  = " + greatest("1", "9", "5"));
        System.out.println("Maximo entre 3.17, 11.8, 9.5 = " + greatest(3.17, 11.8, 9.5));
        System.out.println("Maximo entre naranja, melocoton, uva = " + greatest("naranja", "melocoton", "uva"));

    }

    public static <T extends Cliente & Comparable<T>> List<T> fromArrayToList(T[] obj) {
        return Arrays.asList(obj);
    }

    public static <T> List<T> fromArrayToList(T[] obj) {
        return Arrays.asList(obj);
    }

    public static <T extends Number> List<T> fromArrayToList(T[] obj) {
        return Arrays.asList(obj);
    }

    public static <T, G> List<T> fromArrayToList(T[] c, G[] g) {
        for (G elemento : g) {
            System.out.println("G = " + elemento);
        }
        return Arrays.asList(c);
    }

    public static void imprimirClientes(List<? extends Cliente> lista) {
        lista.forEach(System.out::println);
    }

    public static <T extends Comparable<T>> T greatest(T a, T b, T c) {
        T max = a;

        if (b.compareTo(max) > 0) {
            max = b;
        } else if (c.compareTo(max) > 0) {
            max = c;
        }

        return max;
    }
}
