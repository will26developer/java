package poo.poo_interfaces.repositorio;

import poo.poo_interfaces.modelos.Cliente;

import java.util.List;

public interface PaginableRepositorio {
    List<Cliente> listar(int desde, int hasta);
}
