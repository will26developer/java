package poo.poo_interfaces.repositorio;

import poo.poo_interfaces.modelos.Cliente;
import poo.poo_interfaces.modelos.Direccion;

import java.util.List;

public interface OrdenableRepositorio {
    List<Cliente> listar(String campo, Direccion direccion);
}
