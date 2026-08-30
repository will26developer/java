package generics.bolsa_supermercado;

import generics.bolsa_supermercado.exceptions.BolsaSupermercadoLlenaException;
import generics.bolsa_supermercado.models.Lacteo;
import generics.bolsa_supermercado.services.BolsaSupermercadoService;

public class MainBolsaSupermercado {

    public static void main(String[] args) throws BolsaSupermercadoLlenaException {
        BolsaSupermercadoService<Lacteo> bolsaLacteos = new BolsaSupermercadoService<>(5);
        bolsaLacteos.addProductos(new Lacteo("Yogur", 1.89, 1, 650));
        bolsaLacteos.addProductos(new Lacteo("Yogur", 1.89, 1, 650));
        bolsaLacteos.addProductos(new Lacteo("Yogur", 1.89, 1, 650));
        bolsaLacteos.addProductos(new Lacteo("Yogur", 1.89, 1, 650));

        bolsaLacteos.mostrarProductos();
    }
}
