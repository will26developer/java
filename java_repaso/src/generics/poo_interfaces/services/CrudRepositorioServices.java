package generics.poo_interfaces.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import poo.poo_interfaces.modelos.Cliente;
import poo.poo_interfaces.modelos.Direccion;
import poo.poo_interfaces.repositorio.CrudRepositorio;
import poo.poo_interfaces.repositorio.OrdenableRepositorio;
import poo.poo_interfaces.repositorio.PaginableRepositorio;

public class CrudRepositorioServices implements CrudRepositorio, OrdenableRepositorio, PaginableRepositorio {
    List<Cliente> clientes;

    public CrudRepositorioServices() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public List<Cliente> listar() {
        return clientes;
    }

    @Override
    public Cliente buscarPorId(int id) {
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getId() == id).findFirst();
        if (cliente.isPresent()) {
            return cliente.get();
        }
        return null;
    }

    @Override
    public void guardar(Cliente cliente) {
        Cliente clienteExistente = buscarPorId(cliente.getId());
        if (clienteExistente == null) {
            clientes.add(cliente);
        }
    }

    @Override
    public void actualizar(int id, Cliente cliente) {
        Cliente clienteExistente = buscarPorId(id);
        if (clienteExistente != null) {
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setDni(cliente.getDni());
            clienteExistente.setEmail(cliente.getEmail());
            clienteExistente.setTelefono(cliente.getTelefono());
        }
    }

    @Override
    public void eliminar(int id) {
        Cliente clienteExistente = buscarPorId(id);
        if (clienteExistente != null) {
            clientes.remove(clienteExistente);
        }
    }

    @Override
    public List<Cliente> listar(String campo, Direccion direccion) {
        clientes.sort(new Comparator<Cliente>() {
            @Override
            public int compare(Cliente o1, Cliente o2) {
                int resultado = 0;
                if (direccion == Direccion.ASC) {
                    switch (campo) {
                        case "id" -> resultado = o1.getId() - o2.getId();
                        case "nombre" -> resultado = o1.getNombre().compareTo(o2.getNombre());
                        case "apellido" -> resultado = o1.getApellido().compareTo(o2.getApellido());
                        case "dni" -> resultado = o1.getDni().compareTo(o2.getDni());
                        case "email" -> resultado = o1.getEmail().compareTo(o2.getEmail());
                        case "telefono" -> resultado = o1.getTelefono().compareTo(o2.getTelefono());
                    }
                } else if (direccion == Direccion.DESC) {
                    switch (campo) {
                        case "id" -> resultado = o2.getId() - o1.getId();
                        case "nombre" -> resultado = o2.getNombre().compareTo(o1.getNombre());
                        case "apellido" -> resultado = o2.getApellido().compareTo(o1.getApellido());
                        case "dni" -> resultado = o2.getDni().compareTo(o1.getDni());
                        case "email" -> resultado = o2.getEmail().compareTo(o1.getEmail());
                        case "telefono" -> resultado = o2.getTelefono().compareTo(o1.getTelefono());
                    }
                }
                return resultado;
            }
        });
        return clientes;
    }

    @Override
    public List<Cliente> listar(int desde, int hasta) {
        return clientes.subList(desde, hasta);
    }
}
