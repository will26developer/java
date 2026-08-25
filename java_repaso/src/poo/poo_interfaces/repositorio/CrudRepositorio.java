package poo.poo_interfaces.repositorio;

import poo.poo_interfaces.modelos.Cliente;

import java.util.List;

public interface CrudRepositorio {
    List<Cliente> listar();
    Cliente buscarPorId(int id);
    void guardar(Cliente cliente);
    void actualizar(int id, Cliente cliente);
    void eliminar(int id);
}
