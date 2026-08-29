package generics.poo_interfaces.modelos;

public class ClientePremium extends Cliente implements Comparable<Cliente> {

    public ClientePremium() {
    }

    public ClientePremium(String nombre, String apellido) {
        super(nombre, apellido);
    }

    @Override
    public int compareTo(Cliente arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}
