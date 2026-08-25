/**
 * MVC - MODEL VIEW CONTROLLER
 *
 * Objetivo:
 * Separar datos, interfaz y lógica de control.
 *
 * Cuándo usarlo:
 * - Aplicaciones web.
 * - Aplicaciones con UI.
 * - Separar responsabilidades.
 *
 * Partes:
 * - Model: datos y reglas.
 * - View: presentación.
 * - Controller: coordina modelo y vista.
 */
public class MVCEjemplo {

    public static void main(String[] args) {
        UsuarioModel model = new UsuarioModel("William");
        UsuarioView view = new UsuarioView();
        UsuarioController controller = new UsuarioController(model, view);

        controller.mostrarUsuario();
    }
}

class UsuarioModel {
    private String nombre;
    UsuarioModel(String nombre) { this.nombre = nombre; }
    String getNombre() { return nombre; }
}

class UsuarioView {
    void render(String nombre) {
        System.out.println("Usuario: " + nombre);
    }
}

class UsuarioController {
    private UsuarioModel model;
    private UsuarioView view;

    UsuarioController(UsuarioModel model, UsuarioView view) {
        this.model = model;
        this.view = view;
    }

    void mostrarUsuario() {
        view.render(model.getNombre());
    }
}
