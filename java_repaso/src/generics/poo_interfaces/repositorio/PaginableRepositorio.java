package generics.poo_interfaces.repositorio;

import java.util.List;

import poo.poo_interfaces.modelos.Cliente;

public interface PaginableRepositorio {
    List<Cliente> listar(int desde, int hasta);
}
