package poo;
/**
 * POO AVANZADA — CLASES INTERNAS, ENUMS, GENÉRICOS Y PATRONES
 * =============================================================
 *
 * CLASES INTERNAS (Inner Classes):
 *   - Clase interna de instancia
 *   - Clase interna estática (static nested)
 *   - Clase anónima
 *   - Clase local
 *
 * ENUM: tipo especial que representa un conjunto fijo de constantes
 *
 * GENÉRICOS (Generics): código que funciona con cualquier tipo
 *
 * PATRONES DE DISEÑO: Singleton, Builder, Factory
 */
import java.util.ArrayList;
import java.util.List;

public class PooAvanzada {

    public static void main(String[] args) {

        // ================================================================
        // A. ENUM BÁSICO
        // ================================================================
        System.out.println("========== A. Enum básico ==========");

        DiaSemana dia = DiaSemana.LUNES;
        System.out.println("Día        : " + dia);
        System.out.println("name()     : " + dia.name());
        System.out.println("ordinal()  : " + dia.ordinal()); // posición (0-based)

        // Switch con enum
        String tipo = switch (dia) {
            case LUNES, MARTES, MIERCOLES, JUEVES, VIERNES -> "Laborable";
            case SABADO, DOMINGO -> "Fin de semana";
        };
        System.out.println("Tipo       : " + tipo);

        // values() — todos los valores
        System.out.print("Todos: ");
        for (DiaSemana d : DiaSemana.values()) System.out.print(d + " ");
        System.out.println();

        // valueOf() — obtener por nombre
        DiaSemana viernes = DiaSemana.valueOf("VIERNES");
        System.out.println("valueOf    : " + viernes);

        // ================================================================
        // B. ENUM CON CAMPOS Y MÉTODOS
        // ================================================================
        System.out.println("\n========== B. Enum con campos y métodos ==========");

        for (Planeta planeta : Planeta.values()) {
            System.out.printf("%-10s masa=%.2e kg, radio=%,d km%n",
                    planeta, planeta.getMasa(), planeta.getRadioKm());
        }

        System.out.println("\nPeso en Marte (70kg terrestre): " +
                String.format("%.2f", Planeta.MARTE.calcularPeso(70)) + " kg");

        // ================================================================
        // C. CLASE INTERNA DE INSTANCIA
        // ================================================================
        System.out.println("\n========== C. Clase interna de instancia ==========");

        Ordenador pc = new Ordenador("Dell XPS", 16);
        Ordenador.CPU cpu = pc.new CPU("Intel i9", 3.5); // necesita instancia del padre
        cpu.mostrarInfo();

        // ================================================================
        // D. CLASE INTERNA ESTÁTICA (static nested)
        // ================================================================
        System.out.println("\n========== D. Clase estática anidada ==========");

        // No necesita instancia de la clase externa
        Ordenador.Memoria ram = new Ordenador.Memoria(32, "DDR5");
        ram.mostrarInfo();

        // ================================================================
        // E. CLASE ANÓNIMA
        // ================================================================
        System.out.println("\n========== E. Clase anónima ==========");

        // Implementar una interfaz al vuelo (sin nombre de clase)
        Saludo saludoFormal = new Saludo() {
            @Override
            public void saludar(String nombre) {
                System.out.println("Buenos días, estimado " + nombre + ".");
            }
        };

        Saludo saludoInformal = new Saludo() {
            @Override
            public void saludar(String nombre) {
                System.out.println("¡Hola, " + nombre + "! ¿Qué tal?");
            }
        };

        saludoFormal.saludar("Sr. García");
        saludoInformal.saludar("William");

        // Con lambda (alternativa moderna a clases anónimas para interfaces funcionales)
        Saludo saludoLambda = nombre -> System.out.println("Hey, " + nombre + "!");
        saludoLambda.saludar("mundo");

        // ================================================================
        // F. GENÉRICOS (Generics)
        // ================================================================
        System.out.println("\n========== F. Genéricos ==========");

        // Clase genérica con cualquier tipo
        Caja<Integer> cajaInt = new Caja<>(42);
        Caja<String>  cajaStr = new Caja<>("Hola");
        Caja<Double>  cajaDbl = new Caja<>(3.14);

        System.out.println("cajaInt: " + cajaInt.getContenido() + " [" + cajaInt.getTipo() + "]");
        System.out.println("cajaStr: " + cajaStr.getContenido() + " [" + cajaStr.getTipo() + "]");
        System.out.println("cajaDbl: " + cajaDbl.getContenido() + " [" + cajaDbl.getTipo() + "]");

        // Método genérico
        System.out.println("Mayor (3,7): " + mayor(3, 7));
        System.out.println("Mayor (a,z): " + mayor('a', 'z'));
        System.out.println("Mayor (str): " + mayor("Java", "Python"));

        // Pila genérica
        Pila<String> pila = new Pila<>();
        pila.push("primero");
        pila.push("segundo");
        pila.push("tercero");
        System.out.println("peek: " + pila.peek());
        System.out.println("pop : " + pila.pop());
        System.out.println("pop : " + pila.pop());
        System.out.println("tamaño: " + pila.tamaño());

        // ================================================================
        // G. PATRÓN SINGLETON
        //    Solo puede existir UNA instancia de la clase
        // ================================================================
        System.out.println("\n========== G. Singleton ==========");

        Configuracion conf1 = Configuracion.getInstance();
        Configuracion conf2 = Configuracion.getInstance();

        conf1.set("tema", "oscuro");
        System.out.println("conf2.get(tema): " + conf2.get("tema")); // "oscuro" (misma instancia)
        System.out.println("conf1 == conf2 : " + (conf1 == conf2)); // true

        // ================================================================
        // H. PATRÓN BUILDER
        //    Construir objetos complejos paso a paso
        // ================================================================
        System.out.println("\n========== H. Builder ==========");

        Pizza pizza = new Pizza.Builder("Grande")
                .masa("Fina")
                .salsa("Tomate")
                .queso("Mozzarella")
                .addIngrediente("Jamón")
                .addIngrediente("Champiñones")
                .addIngrediente("Aceitunas")
                .build();

        System.out.println(pizza);

        // ================================================================
        // I. PATRÓN FACTORY
        //    Delegar la creación de objetos a un método/clase
        // ================================================================
        System.out.println("\n========== I. Factory ==========");

        Notificacion email = NotificacionFactory.crear("EMAIL", "Nuevo mensaje");
        Notificacion sms   = NotificacionFactory.crear("SMS", "Código: 1234");
        Notificacion push  = NotificacionFactory.crear("PUSH", "Actualización disponible");

        email.enviar("usuario@email.com");
        sms.enviar("+34600123456");
        push.enviar("dispositivo-abc");
    }

    // Método genérico con bound (T debe ser Comparable)
    static <T extends Comparable<T>> T mayor(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }
}

// ====================================================================
// ENUM BÁSICO
// ====================================================================
enum DiaSemana { LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO }

// ====================================================================
// ENUM CON CAMPOS Y MÉTODOS
// ====================================================================
enum Planeta {
    MERCURIO(3.303e+23, 2439700),
    VENUS   (4.869e+24, 6051800),
    TIERRA  (5.976e+24, 6371000),
    MARTE   (6.421e+23, 3389500);

    private final double masa;    // kg
    private final int    radio;   // metros

    Planeta(double masa, int radio) {
        this.masa  = masa;
        this.radio = radio;
    }

    private static final double G = 6.67300E-11;

    public double getMasa()    { return masa; }
    public int    getRadioKm() { return radio / 1000; }

    public double gravedad() { return G * masa / (radio * radio); }
    public double calcularPeso(double masaTerrestre) {
        return masaTerrestre * gravedad() / Planeta.TIERRA.gravedad();
    }
}

// ====================================================================
// CLASES INTERNAS
// ====================================================================
class Ordenador {
    private String modelo;
    private int    ramGB;

    public Ordenador(String modelo, int ramGB) {
        this.modelo = modelo;
        this.ramGB  = ramGB;
    }

    // Clase interna de instancia (accede a campos del padre)
    class CPU {
        private String nombre;
        private double ghz;

        public CPU(String nombre, double ghz) {
            this.nombre = nombre;
            this.ghz    = ghz;
        }

        public void mostrarInfo() {
            // Accede al campo 'modelo' de la clase externa
            System.out.println("CPU " + nombre + " @" + ghz + "GHz en " + modelo);
        }
    }

    // Clase estática anidada (NO accede a campos de instancia del padre)
    static class Memoria {
        private int    gb;
        private String tipo;

        public Memoria(int gb, String tipo) {
            this.gb   = gb;
            this.tipo = tipo;
        }

        public void mostrarInfo() {
            System.out.println("RAM " + gb + "GB " + tipo);
        }
    }
}

// ====================================================================
// INTERFAZ FUNCIONAL (para clase anónima y lambda)
// ====================================================================
@FunctionalInterface
interface Saludo { void saludar(String nombre); }

// ====================================================================
// GENÉRICOS
// ====================================================================
class Caja<T> {
    private T contenido;

    public Caja(T contenido) { this.contenido = contenido; }
    public T      getContenido() { return contenido; }
    public String getTipo()      { return contenido.getClass().getSimpleName(); }

    @Override
    public String toString() { return "Caja[" + contenido + "]"; }
}

class Pila<T> {
    private List<T> elementos = new ArrayList<>();

    public void push(T elemento) { elementos.add(elemento); }

    public T pop() {
        if (elementos.isEmpty()) throw new java.util.EmptyStackException();
        return elementos.remove(elementos.size() - 1);
    }

    public T peek() {
        if (elementos.isEmpty()) throw new java.util.EmptyStackException();
        return elementos.get(elementos.size() - 1);
    }

    public int     tamaño()  { return elementos.size(); }
    public boolean estaVacia(){ return elementos.isEmpty(); }
}

// ====================================================================
// SINGLETON
// ====================================================================
class Configuracion {
    private static Configuracion instancia; // única instancia
    private java.util.Map<String, String> propiedades = new java.util.HashMap<>();

    private Configuracion() {} // constructor privado

    public static Configuracion getInstance() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public void   set(String clave, String valor) { propiedades.put(clave, valor); }
    public String get(String clave)               { return propiedades.get(clave); }
}

// ====================================================================
// BUILDER
// ====================================================================
class Pizza {
    private final String tamaño;
    private final String masa;
    private final String salsa;
    private final String queso;
    private final List<String> ingredientes;

    private Pizza(Builder b) {
        this.tamaño       = b.tamaño;
        this.masa         = b.masa;
        this.salsa        = b.salsa;
        this.queso        = b.queso;
        this.ingredientes = b.ingredientes;
    }

    @Override
    public String toString() {
        return "Pizza " + tamaño + " [masa=" + masa + ", salsa=" + salsa +
               ", queso=" + queso + ", extras=" + ingredientes + "]";
    }

    static class Builder {
        private final String tamaño;
        private String masa  = "Normal";
        private String salsa = "Tomate";
        private String queso = "Mozzarella";
        private List<String> ingredientes = new ArrayList<>();

        public Builder(String tamaño)        { this.tamaño = tamaño; }
        public Builder masa(String m)        { this.masa  = m; return this; }
        public Builder salsa(String s)       { this.salsa = s; return this; }
        public Builder queso(String q)       { this.queso = q; return this; }
        public Builder addIngrediente(String i){ ingredientes.add(i); return this; }
        public Pizza   build()               { return new Pizza(this); }
    }
}

// ====================================================================
// FACTORY
// ====================================================================
interface Notificacion {
    void enviar(String destino);
}

class NotificacionEmail implements Notificacion {
    private String mensaje;
    public NotificacionEmail(String mensaje) { this.mensaje = mensaje; }
    @Override public void enviar(String destino) {
        System.out.println("📧 Email → " + destino + ": " + mensaje);
    }
}

class NotificacionSMS implements Notificacion {
    private String mensaje;
    public NotificacionSMS(String mensaje) { this.mensaje = mensaje; }
    @Override public void enviar(String destino) {
        System.out.println("📱 SMS → " + destino + ": " + mensaje);
    }
}

class NotificacionPush implements Notificacion {
    private String mensaje;
    public NotificacionPush(String mensaje) { this.mensaje = mensaje; }
    @Override public void enviar(String destino) {
        System.out.println("🔔 Push → " + destino + ": " + mensaje);
    }
}

class NotificacionFactory {
    public static Notificacion crear(String tipo, String mensaje) {
        return switch (tipo.toUpperCase()) {
            case "EMAIL" -> new NotificacionEmail(mensaje);
            case "SMS"   -> new NotificacionSMS(mensaje);
            case "PUSH"  -> new NotificacionPush(mensaje);
            default      -> throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        };
    }
}
