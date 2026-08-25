package generics;

import generics.modelos.Cliente;
import generics.modelos.ClientePremium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EjemploGenericos {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList();
        clientes.add(new Cliente("Ana", "Torres", "30111222", "", "351-4001122"));

        Cliente cliente =  clientes.iterator().next();
        System.out.println(cliente.getNombre());

        Cliente[] clientesArray = {
          new Cliente("Ana", "Torres", "30111222", "email@example.com", "351-4001122"),
          new Cliente("Bruno", "Gimenez", "28999444", "email@example.com", "351-4002233"),
          new Cliente("Bruno", "Gimenez", "28999444", "email@example.com", "351-4002233"),
          new Cliente("Carla", "Medina", "35222888", "email@example.com", "351-4003344"),
          new Cliente("Diego", "Rojas", "33444555", "email@example.com", "351-4004455"),
        };
        Integer[] numeros = {1,2,3,4,5};

        List<Integer> numerosList = fromArrayToList(numeros);
        numerosList.forEach(System.out::println);
        List<Cliente> clientesList = fromArrayToList(clientesArray);
        clientesList.forEach(System.out::println);

        List<Cliente> clientesList2 = fromArrayToList(clientesArray, numeros);
        clientesList2.forEach(System.out::println);

        List<String> nombres = fromArrayToList(new String[]{"Ana", "Bruno", "Carla", "Diego"},new Integer[]{1,2,3,4});

        ClientePremium[] clientesPremiums = new ClientePremium[3];
        clientesPremiums[0] = new ClientePremium("William","Martinez","X9323314B","will.mart.tria.0304@gmail.com","603110439");
        clientesPremiums[1] = new ClientePremium("Alberto","Maldonado","74913085S","alberto.maldonado@gmail.com","6112722209");
        clientesPremiums[2] = new ClientePremium("Francisco","Morente","344534343L","francisco.morente@gmail.com","611272209");
        List<ClientePremium> clientePremiums = fromArrayToList(clientesPremiums);
        imprimirClientes(clientePremiums);

        System.out.println("el mayor entre 1, 9, 5 = " + greatest(1,9,5));
        System.out.println("el mayor entre zanahoria, melocoton, almendra = " + greatest("zanahoria","melocoton","almendra"));

    }

    public static <T> List<T> fromArrayToList(T[] c){
        return Arrays.asList(c);
    }

    public static <T extends Number> List<T> fromArrayToList(T[] c){
        return Arrays.asList(c);
    }

    public static <T extends Cliente> List<T> fromArrayToList(T[] c){
        return Arrays.asList(c);
    }

    public static <T, G> List<T> fromArrayToList(T[] c, G[] g){
        for (G elemento: g) {
            System.out.println(elemento);
        }
        return Arrays.asList(c);
    }

    public static void imprimirClientes(List<? extends Cliente> clientes) {
        clientes.forEach(System.out::println);
    }

    public static <T extends  Comparable<T>> T greatest(T a, T b, T c) {
        T max = a;
        if (b.compareTo(max) > 0) {
            max = b;
        }
        if (c.compareTo(max) > 0) {
            max = c;
        }
        return max;
    }
}
