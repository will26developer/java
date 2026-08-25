/**
 * FACTORY METHOD
 *
 * Objetivo:
 * Delegar la creación de objetos a una fábrica, evitando usar new directamente en muchas partes.
 *
 * Cuándo usarlo:
 * - Cuando necesitas crear objetos según una condición.
 * - Cuando quieres desacoplar el código cliente de las clases concretas.
 *
 * Ventajas:
 * - Código más flexible.
 * - Centraliza la lógica de creación.
 *
 * Desventajas:
 * - Puede añadir clases extra si el caso es muy simple.
 */
public class FactoryMethodEjemplo {

    public static void main(String[] args) {
        Notificacion email = NotificacionFactory.crear("EMAIL");
        Notificacion sms = NotificacionFactory.crear("SMS");

        email.enviar("Hola por email");
        sms.enviar("Hola por SMS");
    }
}

interface Notificacion {
    void enviar(String mensaje);
}

class EmailNotificacion implements Notificacion {
    public void enviar(String mensaje) {
        System.out.println("Email: " + mensaje);
    }
}

class SmsNotificacion implements Notificacion {
    public void enviar(String mensaje) {
        System.out.println("SMS: " + mensaje);
    }
}

class NotificacionFactory {
    public static Notificacion crear(String tipo) {
        switch (tipo) {
            case "EMAIL": return new EmailNotificacion();
            case "SMS": return new SmsNotificacion();
            default: throw new IllegalArgumentException("Tipo no soportado");
        }
    }
}
