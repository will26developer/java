package generics.poo_interfaces.repositorio;

import java.util.List;

import poo.poo_interfaces.modelos.Cliente;

public interface CrudRepositorio {
    List<Cliente> listar();

    Cliente buscarPorId(int id);

    void guardar(Cliente cliente);

    void actualizar(int id, Cliente cliente);

    void eliminar(int id);
}
