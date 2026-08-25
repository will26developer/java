package manejo_excepciones;
/**
 * EXCEPCIONES PERSONALIZADAS, CHECKED/UNCHECKED Y TRY-WITH-RESOURCES
 * =====================================================================
 *
 * EXCEPCIONES PERSONALIZADAS:
 *   Extienden Exception (checked) o RuntimeException (unchecked)
 *   Permiten mensajes de error específicos del dominio
 *
 * THROWS vs THROW:
 *   throws → declara en la firma del método que puede lanzar
 *   throw  → lanza la excepción en el código
 *
 * TRY-WITH-RESOURCES (Java 7+):
 *   Cierra automáticamente recursos que implementan AutoCloseable
 *   Equivale a un finally que llama a close()
 */
public class ExcepcionesPersonalizadas {

    public static void main(String[] args) {

        // ================================================================
        // A. EXCEPCIÓN CHECKED PERSONALIZADA
        // ================================================================
        System.out.println("========== A. Excepción checked personalizada ==========");

        // El compilador OBLIGA a manejar excepciones checked
        try {
            transferir("ES001", "ES002", 500.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Saldo actual  : " + e.getSaldoActual());
            System.out.println("Cantidad pedida: " + e.getCantidadSolicitada());
        }

        try {
            transferir("ES001", "ES002", 100.0);
            System.out.println("Transferencia OK");
        } catch (SaldoInsuficienteException e) {
            System.out.println("No debería llegar aquí");
        }

        // ================================================================
        // B. EXCEPCIÓN UNCHECKED PERSONALIZADA
        // ================================================================
        System.out.println("\n========== B. Excepción unchecked personalizada ==========");

        // NO obliga al compilador, pero conviene capturar igualmente
        try {
            validarEdad(-5);
        } catch (EdadInvalidaException e) {
            System.out.println("Error validación: " + e.getMessage());
        }

        try {
            validarEdad(200);
        } catch (EdadInvalidaException e) {
            System.out.println("Error validación: " + e.getMessage());
        }

        try {
            int resultado = validarEdad(25);
            System.out.println("Edad válida: " + resultado);
        } catch (EdadInvalidaException e) {
            System.out.println("No debería llegar aquí");
        }

        // ================================================================
        // C. JERARQUÍA DE EXCEPCIONES PROPIAS
        // ================================================================
        System.out.println("\n========== C. Jerarquía de excepciones ==========");

        String[] usuarios = {"user123", "", null, "ad", "usuario_muy_largo_que_supera_limite"};
        for (String u : usuarios) {
            try {
                registrarUsuario(u);
                System.out.println("Registrado: " + u);
            } catch (UsuarioVacioException e) {
                System.out.println("Vacío: " + e.getMessage());
            } catch (UsuarioNuloException e) {
                System.out.println("Nulo: " + e.getMessage());
            } catch (UsuarioDemasiadoCortoException e) {
                System.out.println("Corto: " + e.getMessage() + " (mín: " + e.getMinimo() + ")");
            } catch (UsuarioDemasiadoLargoException e) {
                System.out.println("Largo: " + e.getMessage() + " (máx: " + e.getMaximo() + ")");
            } catch (UsuarioException e) {
                // Captura cualquier otra UsuarioException
                System.out.println("Error usuario: " + e.getMessage());
            }
        }

        // ================================================================
        // D. throws EN LA FIRMA DEL MÉTODO
        // ================================================================
        System.out.println("\n========== D. throws ==========");

        // Checked: el compilador obliga a declarar o capturar
        try {
            cargarConfiguracion("config.properties");
        } catch (ConfiguracionException e) {
            System.out.println("Config error: " + e.getMessage());
        }

        // Propagar hacia arriba sin capturar (delegar al llamador)
        try {
            procesarPedido(null);
        } catch (Exception e) {
            System.out.println("Pedido error: " + e.getMessage());
        }

        // ================================================================
        // E. TRY-WITH-RESOURCES
        // ================================================================
        System.out.println("\n========== E. try-with-resources ==========");

        // Los recursos se cierran automáticamente al salir del try
        // (incluso si hay excepción)
        try (RecursoSimulado recurso = new RecursoSimulado("BD")) {
            System.out.println("Usando: " + recurso.leer());
            recurso.escribir("datos importantes");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        // recurso.close() se llama automáticamente aquí

        // Múltiples recursos — se cierran en orden INVERSO al de apertura
        System.out.println("\n--- Múltiples recursos ---");
        try (
            RecursoSimulado origen  = new RecursoSimulado("Origen");
            RecursoSimulado destino = new RecursoSimulado("Destino")
        ) {
            String datos = origen.leer();
            destino.escribir(datos);
            System.out.println("Copia completada");
        } catch (Exception e) {
            System.out.println("Error copiando: " + e.getMessage());
        }
        // destino.close() → origen.close() (orden inverso)

        // Recurso que falla al cerrar — suppressed exceptions
        System.out.println("\n--- Suppressed exceptions ---");
        try (RecursoQueFalla recursoFallo = new RecursoQueFalla()) {
            System.out.println("Usando recurso que falla al cerrar");
        } catch (Exception e) {
            System.out.println("Excepción principal: " + e.getMessage());
            // Las excepciones suprimidas (del close()) están en:
            for (Throwable suprimida : e.getSuppressed()) {
                System.out.println("  Suprimida: " + suprimida.getMessage());
            }
        }

        // ================================================================
        // F. EFECTIVAMENTE FINAL EN TRY-WITH-RESOURCES (Java 9+)
        // ================================================================
        System.out.println("\n========== F. Recursos efectivamente final (Java 9+) ==========");

        RecursoSimulado recursoExterno = new RecursoSimulado("Externo");
        // Java 9+: se puede usar una variable efectivamente final directamente
        try (recursoExterno) {
            System.out.println(recursoExterno.leer());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ================================================================
        // G. BUENAS PRÁCTICAS
        // ================================================================
        System.out.println("\n========== G. Buenas prácticas ==========");
        System.out.println("1. Usa excepciones checked para errores recuperables esperados.");
        System.out.println("2. Usa excepciones unchecked para errores de programación.");
        System.out.println("3. Nunca captures Exception/Throwable genérico sin relanzar.");
        System.out.println("4. Siempre usa try-with-resources para recursos (ficheros, BD...)");
        System.out.println("5. Añade contexto al mensaje: QUIÉN falló, POR QUÉ, CON QUÉ valor.");
        System.out.println("6. No uses excepciones para control de flujo normal.");
        System.out.println("7. Crea jerarquías de excepciones para tu dominio.");
        System.out.println("8. Nunca swallows silenciosamente: catch (Exception e) {}");
    }

    // ----------------------------------------------------------------
    // MÉTODOS
    // ----------------------------------------------------------------

    static void transferir(String origen, String destino, double cantidad)
            throws SaldoInsuficienteException {
        double saldoActual = 200.0; // simulado
        if (cantidad > saldoActual) {
            throw new SaldoInsuficienteException(saldoActual, cantidad);
        }
        System.out.println("Transferido " + cantidad + " de " + origen + " a " + destino);
    }

    static int validarEdad(int edad) {
        if (edad < 0)   throw new EdadInvalidaException("La edad no puede ser negativa: " + edad);
        if (edad > 150) throw new EdadInvalidaException("Edad imposible: " + edad);
        return edad;
    }

    static void registrarUsuario(String usuario) throws UsuarioException {
        if (usuario == null)        throw new UsuarioNuloException();
        if (usuario.isEmpty())      throw new UsuarioVacioException();
        if (usuario.length() < 3)   throw new UsuarioDemasiadoCortoException(usuario, 3);
        if (usuario.length() > 20)  throw new UsuarioDemasiadoLargoException(usuario, 20);
    }

    static void cargarConfiguracion(String archivo) throws ConfiguracionException {
        throw new ConfiguracionException("Archivo no encontrado: " + archivo);
    }

    static void procesarPedido(String pedidoId) throws IllegalArgumentException {
        if (pedidoId == null) throw new IllegalArgumentException("ID de pedido nulo");
    }
}

// ====================================================================
// EXCEPCIONES PERSONALIZADAS
// ====================================================================

// CHECKED — extiende Exception
class SaldoInsuficienteException extends Exception {
    private final double saldoActual;
    private final double cantidadSolicitada;

    public SaldoInsuficienteException(double saldoActual, double cantidadSolicitada) {
        super(String.format("Saldo insuficiente. Disponible: %.2f, Solicitado: %.2f",
                saldoActual, cantidadSolicitada));
        this.saldoActual        = saldoActual;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public double getSaldoActual()        { return saldoActual; }
    public double getCantidadSolicitada() { return cantidadSolicitada; }
}

// UNCHECKED — extiende RuntimeException
class EdadInvalidaException extends RuntimeException {
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
    public EdadInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

// JERARQUÍA de excepciones de dominio

// Raíz de la jerarquía (checked)
class UsuarioException extends Exception {
    public UsuarioException(String mensaje) { super(mensaje); }
}

class UsuarioNuloException extends UsuarioException {
    public UsuarioNuloException() { super("El nombre de usuario no puede ser nulo"); }
}

class UsuarioVacioException extends UsuarioException {
    public UsuarioVacioException() { super("El nombre de usuario no puede estar vacío"); }
}

class UsuarioDemasiadoCortoException extends UsuarioException {
    private final int minimo;
    public UsuarioDemasiadoCortoException(String usuario, int minimo) {
        super(String.format("'%s' es demasiado corto (%d caracteres)", usuario, usuario.length()));
        this.minimo = minimo;
    }
    public int getMinimo() { return minimo; }
}

class UsuarioDemasiadoLargoException extends UsuarioException {
    private final int maximo;
    public UsuarioDemasiadoLargoException(String usuario, int maximo) {
        super(String.format("'%s' es demasiado largo (%d caracteres)", usuario, usuario.length()));
        this.maximo = maximo;
    }
    public int getMaximo() { return maximo; }
}

class ConfiguracionException extends Exception {
    public ConfiguracionException(String mensaje) { super(mensaje); }
    public ConfiguracionException(String mensaje, Throwable causa) { super(mensaje, causa); }
}

// ====================================================================
// RECURSOS AutoCloseable PARA TRY-WITH-RESOURCES
// ====================================================================

class RecursoSimulado implements AutoCloseable {
    private final String nombre;
    private boolean abierto = true;

    public RecursoSimulado(String nombre) {
        this.nombre = nombre;
        System.out.println("  [" + nombre + "] Abriendo recurso");
    }

    public String leer() {
        if (!abierto) throw new IllegalStateException("Recurso cerrado");
        return "datos de " + nombre;
    }

    public void escribir(String datos) {
        if (!abierto) throw new IllegalStateException("Recurso cerrado");
        System.out.println("  [" + nombre + "] Escribiendo: " + datos);
    }

    @Override
    public void close() {
        abierto = false;
        System.out.println("  [" + nombre + "] Cerrando recurso (automático)");
    }
}

class RecursoQueFalla implements AutoCloseable {
    public RecursoQueFalla() {
        System.out.println("  [RecursoQueFalla] Abriendo");
    }

    @Override
    public void close() throws Exception {
        System.out.println("  [RecursoQueFalla] close() lanza excepción");
        throw new Exception("Error al cerrar el recurso");
    }
}
