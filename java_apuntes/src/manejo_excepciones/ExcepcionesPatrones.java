package manejo_excepciones;
/**
 * EXCEPCIONES — PATRONES AVANZADOS Y CASOS DE USO REALES
 * =========================================================
 * Patrones profesionales para manejar excepciones en aplicaciones reales:
 *   - Patrón Try (Result type)
 *   - Global exception handler
 *   - Retry con excepciones
 *   - Validación acumulativa
 *   - Logging de excepciones
 *   - Excepciones en lambdas y streams
 */
import java.util.*;
import java.util.function.*;

public class ExcepcionesPatrones {

    public static void main(String[] args) {

        // ================================================================
        // A. PATRÓN OPTIONAL PARA EVITAR NPE
        // ================================================================
        System.out.println("========== A. Optional para evitar NPE ==========");

        String[] nombres = {"William", null, "", "Ana"};
        for (String n : nombres) {
            String resultado = Optional.ofNullable(n)
                    .filter(s -> !s.isBlank())
                    .map(String::toUpperCase)
                    .orElse("ANÓNIMO");
            System.out.println("'" + n + "' → " + resultado);
        }

        // Encadenar operaciones evitando NPE
        Optional<String> email = Optional.ofNullable(buscarUsuario(1))
                .map(u -> u.get("email"))
                .filter(e -> e.contains("@"));

        System.out.println("Email encontrado: " + email.orElse("sin email"));

        // orElseThrow — lanzar excepción si vacío
        try {
            String email2 = Optional.ofNullable(buscarUsuario(99))
                    .map(u -> u.get("email"))
                    .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        } catch (NoSuchElementException e) {
            System.out.println("Exception Optional: " + e.getMessage());
        }

        // ================================================================
        // B. VALIDACIÓN ACUMULATIVA (recoger todos los errores de una vez)
        // ================================================================
        System.out.println("\n========== B. Validación acumulativa ==========");

        // En lugar de lanzar la primera excepción, acumula todos los errores
        ValidadorUsuario validador = new ValidadorUsuario();

        List<String> errores1 = validador.validar("", -5, "correomal");
        System.out.println("Errores (todos juntos):");
        errores1.forEach(e -> System.out.println("  - " + e));

        List<String> errores2 = validador.validar("William", 25, "william@email.com");
        System.out.println("Errores (datos válidos): " + (errores2.isEmpty() ? "ninguno" : errores2));

        // ================================================================
        // C. RETRY — reintentar operaciones que pueden fallar
        // ================================================================
        System.out.println("\n========== C. Patrón Retry ==========");

        try {
            String resultado = reintentar(() -> operacionInestable(), 3, 100);
            System.out.println("Resultado tras retry: " + resultado);
        } catch (Exception e) {
            System.out.println("Falló tras todos los intentos: " + e.getMessage());
        }

        // ================================================================
        // D. EXCEPCIONES EN LAMBDAS — el problema
        // ================================================================
        System.out.println("\n========== D. Excepciones en lambdas ==========");

        List<String> numeros = List.of("1", "2", "abc", "4", "xyz");

        // PROBLEMA: las lambdas de Stream no admiten checked exceptions
        // numeros.stream().map(Integer::parseInt) // NFE es unchecked → OK
        // numeros.stream().map(s -> { throw new IOException(...); }) // ERROR

        // SOLUCIÓN 1: capturar dentro de la lambda
        List<Integer> parseados = new ArrayList<>();
        numeros.forEach(s -> {
            try {
                parseados.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                System.out.println("  Ignorando valor inválido: " + s);
            }
        });
        System.out.println("Parseados: " + parseados);

        // SOLUCIÓN 2: wrapper que convierte checked en unchecked
        List<Integer> parseados2 = new ArrayList<>();
        numeros.stream()
                .map(s -> parsearSeguro(s))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(parseados2::add);
        System.out.println("Con Optional: " + parseados2);

        // SOLUCIÓN 3: método envolvente genérico
        List<Integer> parseados3 = new ArrayList<>();
        numeros.forEach(envolver(s -> {
            parseados3.add(Integer.parseInt(s));
        }));
        System.out.println("Con envolver: " + parseados3);

        // ================================================================
        // E. GLOBAL EXCEPTION HANDLER — manejar excepciones no capturadas
        // ================================================================
        System.out.println("\n========== E. UncaughtExceptionHandler ==========");

        // Configurar handler para excepciones no capturadas en el hilo actual
        Thread.currentThread().setUncaughtExceptionHandler((hilo, excepcion) -> {
            System.out.println("¡Excepción no capturada en hilo " + hilo.getName() + "!");
            System.out.println("Tipo   : " + excepcion.getClass().getSimpleName());
            System.out.println("Mensaje: " + excepcion.getMessage());
            // Aquí se podría loguear, notificar, etc.
        });

        // Handler global para todos los hilos
        Thread.setDefaultUncaughtExceptionHandler((hilo, excepcion) -> {
            System.out.println("[GLOBAL] Excepción en hilo " + hilo.getName() +
                    ": " + excepcion.getMessage());
        });

        System.out.println("Handler configurado (no se dispara aquí al estar en try-catch)");

        // ================================================================
        // F. EXCEPCIONES EN CONSTRUCTORES
        // ================================================================
        System.out.println("\n========== F. Excepciones en constructores ==========");

        try {
            Email email3 = new Email(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Email nulo: " + e.getMessage());
        }

        try {
            Email email4 = new Email("no-es-un-email");
        } catch (IllegalArgumentException e) {
            System.out.println("Email inválido: " + e.getMessage());
        }

        try {
            Email email5 = new Email("usuario@dominio.com");
            System.out.println("Email válido: " + email5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error inesperado");
        }

        // ================================================================
        // G. ASSERT — para invariantes en desarrollo
        // ================================================================
        System.out.println("\n========== G. assert ==========");
        System.out.println("assert condición : se activa con -ea (enableassertions)");
        System.out.println("assert condición : 'mensaje'");
        System.out.println("Solo para pruebas/desarrollo, NO para validación en producción.");
        System.out.println("Ejemplo:");
        System.out.println("  assert saldo >= 0 : \"Saldo negativo: \" + saldo;");

        // ================================================================
        // H. RESUMEN DE ANTI-PATRONES
        // ================================================================
        System.out.println("\n========== H. Anti-patrones a evitar ==========");
        System.out.println("❌ catch (Exception e) {}             → swallow silencioso");
        System.out.println("❌ catch (Exception e) { e.printStackTrace(); } → solo en dev");
        System.out.println("❌ return null en vez de lanzar excepción → confuso");
        System.out.println("❌ Usar excepciones para control de flujo → muy costoso");
        System.out.println("❌ Capturar Throwable/Error             → se captura OOM, etc.");
        System.out.println("❌ Jerarquía plana de excepciones       → imposible captura selectiva");
        System.out.println("❌ Mensaje sin contexto: throw new Exception(\"Error\")");
        System.out.println("✅ Añade contexto: throw new Exception(\"Fallo al leer usuario id=\" + id)");
        System.out.println("✅ Usa excepciones checked para errores de negocio esperados");
        System.out.println("✅ Usa finally o try-with-resources para liberar recursos");
        System.out.println("✅ Encadena excepciones para no perder la causa raíz");
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static Map<String, String> buscarUsuario(int id) {
        if (id == 1) {
            Map<String, String> u = new HashMap<>();
            u.put("nombre", "William");
            u.put("email", "william@email.com");
            return u;
        }
        return null;
    }

    static int intentos = 0;
    static String operacionInestable() throws Exception {
        intentos++;
        System.out.println("  Intento " + intentos);
        if (intentos < 3) throw new Exception("Servicio no disponible (intento " + intentos + ")");
        return "¡Éxito en intento " + intentos + "!";
    }

    static <T> T reintentar(Callable<T> operacion, int maxIntentos, long esperaMs)
            throws Exception {
        Exception ultimaExcepcion = null;
        for (int i = 0; i < maxIntentos; i++) {
            try {
                return operacion.call();
            } catch (Exception e) {
                ultimaExcepcion = e;
                System.out.println("  Reintentando en " + esperaMs + "ms...");
                if (i < maxIntentos - 1) {
                    try { Thread.sleep(esperaMs); } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        throw ultimaExcepcion;
    }

    static Optional<Integer> parsearSeguro(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // Envuelve una lambda que puede lanzar checked exception
    @FunctionalInterface
    interface ConsumerConExcepcion<T> {
        void accept(T t) throws Exception;
    }

    static <T> Consumer<T> envolver(ConsumerConExcepcion<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (NumberFormatException e) {
                // Ignorar conversiones inválidas silenciosamente
            } catch (Exception e) {
                throw new RuntimeException(e); // convertir a unchecked
            }
        };
    }
}

// ====================================================================
// VALIDADOR ACUMULATIVO
// ====================================================================
class ValidadorUsuario {
    public List<String> validar(String nombre, int edad, String email) {
        List<String> errores = new ArrayList<>();

        if (nombre == null || nombre.isBlank()) {
            errores.add("El nombre no puede estar vacío");
        } else if (nombre.length() < 3) {
            errores.add("El nombre debe tener al menos 3 caracteres");
        }

        if (edad < 0) {
            errores.add("La edad no puede ser negativa: " + edad);
        } else if (edad > 120) {
            errores.add("La edad no puede superar 120: " + edad);
        }

        if (email == null || !email.matches("^[\\w.+\\-]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
            errores.add("Email inválido: " + email);
        }

        return errores;
    }
}

// ====================================================================
// VALUE OBJECT CON VALIDACIÓN EN CONSTRUCTOR
// ====================================================================
final class Email {
    private final String valor;

    public Email(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El email no puede ser nulo");
        }
        String trimado = valor.trim();
        if (!trimado.matches("^[\\w.+\\-]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Formato de email inválido: " + trimado);
        }
        this.valor = trimado;
    }

    public String getValor() { return valor; }

    @Override public String toString() { return "Email[" + valor + "]"; }
}

// ====================================================================
// CALLABLE (para el patrón Retry)
// ====================================================================
@FunctionalInterface
interface Callable<T> {
    T call() throws Exception;
}
