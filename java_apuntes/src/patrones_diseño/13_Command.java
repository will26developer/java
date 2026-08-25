/**
 * COMMAND
 *
 * Objetivo:
 * Encapsular una acción como un objeto.
 *
 * Cuándo usarlo:
 * - Botones de UI.
 * - Cola de tareas.
 * - Deshacer/rehacer operaciones.
 *
 * Ventajas:
 * - Desacopla quien pide la acción de quien la ejecuta.
 *
 * Desventajas:
 * - Puede generar muchas clases de comandos.
 */
public class CommandEjemplo {

    public static void main(String[] args) {
        Luz luz = new Luz();
        Command encender = new EncenderLuzCommand(luz);

        ControlRemoto control = new ControlRemoto();
        control.setCommand(encender);
        control.presionarBoton();
    }
}

interface Command { void ejecutar(); }

class Luz {
    void encender() { System.out.println("Luz encendida"); }
}

class EncenderLuzCommand implements Command {
    private Luz luz;
    EncenderLuzCommand(Luz luz) { this.luz = luz; }
    public void ejecutar() { luz.encender(); }
}

class ControlRemoto {
    private Command command;
    void setCommand(Command command) { this.command = command; }
    void presionarBoton() { command.ejecutar(); }
}
