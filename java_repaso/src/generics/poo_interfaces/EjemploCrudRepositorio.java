package generics.poo_interfaces;

import poo.poo_interfaces.modelos.Cliente;
import poo.poo_interfaces.modelos.Direccion;
import poo.poo_interfaces.services.CrudRepositorioServices;

public class EjemploCrudRepositorio {
        public static void main(String[] args) {
                CrudRepositorioServices crudRepositorioServices = new CrudRepositorioServices();

                crudRepositorioServices.guardar(new Cliente("Ana", "Torres", "30111222",
                                "ana.torres@correo.com", "351-4001122"));
                crudRepositorioServices.guardar(new Cliente("Bruno", "Gimenez", "28999444",
                                "bruno.gimenez@correo.com", "351-4002233"));
                crudRepositorioServices.guardar(new Cliente("Carla", "Medina", "35222888",
                                "carla.medina@correo.com", "351-4003344"));
                crudRepositorioServices.guardar(new Cliente("Diego", "Rojas", "33444555",
                                "diego.rojas@correo.com", "351-4004455"));
                crudRepositorioServices.guardar(new Cliente("Elena", "Vargas", "31777666",
                                "elena.vargas@correo.com", "351-4005566"));
                crudRepositorioServices.guardar(new Cliente("Federico", "Quiroga", "29555333",
                                "federico.quiroga@correo.com", "351-4006677"));

                crudRepositorioServices.listar("nombre", Direccion.DESC);

                crudRepositorioServices.listar().forEach(System.out::println);
        }
}
