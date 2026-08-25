/**
 * BUILDER
 *
 * Objetivo:
 * Construir objetos complejos paso a paso de forma clara.
 *
 * Cuándo usarlo:
 * - Objetos con muchos parámetros opcionales.
 * - Evitar constructores enormes.
 *
 * Ventajas:
 * - Código legible.
 * - Evita errores por orden de parámetros.
 *
 * Desventajas:
 * - Añade más código que un constructor simple.
 */
public class BuilderEjemplo {

    public static void main(String[] args) {
        Usuario usuario = new Usuario.Builder()
                .nombre("William")
                .email("william@email.com")
                .edad(30)
                .build();

        System.out.println(usuario);
    }
}

class Usuario {
    private final String nombre;
    private final String email;
    private final int edad;

    private Usuario(Builder builder) {
        this.nombre = builder.nombre;
        this.email = builder.email;
        this.edad = builder.edad;
    }

    static class Builder {
        private String nombre;
        private String email;
        private int edad;

        Builder nombre(String nombre) { this.nombre = nombre; return this; }
        Builder email(String email) { this.email = email; return this; }
        Builder edad(int edad) { this.edad = edad; return this; }

        Usuario build() { return new Usuario(this); }
    }

    public String toString() {
        return nombre + " - " + email + " - " + edad;
    }
}
