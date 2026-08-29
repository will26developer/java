package generics.poo_interfaces.repositorio;

import java.util.List;

import poo.poo_interfaces.modelos.Cliente;
import poo.poo_interfaces.modelos.Direccion;

public interface OrdenableRepositorio {
    List<Cliente> listar(String campo, Direccion direccion);
}
