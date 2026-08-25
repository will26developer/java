package poo;
/**
* ENCAPSULACIÓN Y MODIFICADORES DE ACCESO
 * =========================================
 * La encapsulación oculta los detalles internos de un objeto y expone
 * solo lo necesario a través de una interfaz pública controlada.
 *
 * MODIFICADORES DE ACCESO (de más a menos restrictivo):
 *
 *  private    → solo la propia clase
 *  (default)  → clases del mismo paquete (sin modificador)
 *  protected  → mismo paquete + subclases
 *  public     → cualquier clase
 *
 *  ┌─────────────┬───────┬─────────┬───────────┬────────┐
 *  │             │ Clase │ Paquete │ Subclase  │ Mundo  │
 *  ├─────────────┼───────┼─────────┼───────────┼────────┤
 *  │ private     │  ✓   │   ✗     │    ✗      │   ✗    │
 *  │ (default)   │  ✓   │   ✓     │    ✗      │   ✗    │
 *  │ protected   │  ✓   │   ✓     │    ✓      │   ✗    │
 *  │ public      │  ✓   │   ✓     │    ✓      │   ✓    │
 *  └─────────────┴───────┴─────────┴───────────┴────────┘
 */
public class Encapsulacion {

    public static void main(String[] args) {

        // ================================================================
        // A. ENCAPSULACIÓN EN ACCIÓN
        // ================================================================
        System.out.println("========== A. Encapsulación ==========");

        CuentaBancaria cuenta = new CuentaBancaria("ES1234567890", 1000.0);
        System.out.println(cuenta);

        // Los campos son privados → no se puede acceder directamente
        // cuenta.saldo = 9999; // ERROR de compilación

        // Solo a través de métodos controlados
        cuenta.depositar(500.0);
        cuenta.retirar(200.0);
        System.out.println("Saldo: " + cuenta.getSaldo());

        try {
            cuenta.retirar(9999.0); // intento de retirar más de lo disponible
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ================================================================
        // B. CAMPOS final — INMUTABLES UNA VEZ ASIGNADOS
        // ================================================================
        System.out.println("\n========== B. Campos final ==========");

        Circulo c = new Circulo(5.0);
        System.out.println("Radio  : " + c.getRadio());
        System.out.printf("Área   : %.2f%n", c.calcularArea());
        System.out.printf("Perímetro: %.2f%n", c.calcularPerimetro());
        // c.radio = 10; // ERROR: campo final (el radio no puede cambiar)

        // ================================================================
        // C. CAMPOS static final — CONSTANTES
        // ================================================================
        System.out.println("\n========== C. Constantes static final ==========");
        System.out.println("IVA        : " + Impuesto.IVA);
        System.out.println("IRPF       : " + Impuesto.IRPF);
        System.out.println("BASE_COTIZ : " + Impuesto.BASE_MINIMA_COTIZACION);

        double bruto = 2000.0;
        double neto  = Impuesto.calcularNeto(bruto);
        System.out.printf("Bruto %.2f → Neto %.2f%n", bruto, neto);

        // ================================================================
        // D. GETTERS Y SETTERS CON VALIDACIÓN
        // ================================================================
        System.out.println("\n========== D. Getters/Setters con validación ==========");

        Empleado emp = new Empleado("William", 1500.0);
        System.out.println(emp);

        // Setters validan los datos
        emp.setSalario(-100); // inválido, no cambia
        System.out.println("Salario tras set negativo: " + emp.getSalario()); // 1500 sin cambio

        emp.setSalario(2000);
        System.out.println("Salario tras set válido  : " + emp.getSalario()); // 2000

        emp.setNombre("  "); // blanco → inválido
        System.out.println("Nombre tras set blanco   : " + emp.getNombre()); // sin cambio

        // ================================================================
        // E. CLASE INMUTABLE
        //    - Todos los campos son private final
        //    - No hay setters
        //    - Los métodos no modifican el estado
        //    - Thread-safe por diseño
        // ================================================================
        System.out.println("\n========== E. Clase inmutable ==========");

        Punto p1 = new Punto(3, 4);
        System.out.println("p1: " + p1);

        // Las operaciones devuelven NUEVOS objetos (no modifican el original)
        Punto p2 = p1.trasladar(1, 2);
        System.out.println("p1 tras trasladar: " + p1); // sin cambio
        System.out.println("p2 (nuevo):        " + p2);

        System.out.printf("Distancia al origen: %.2f%n", p1.distanciaAlOrigen());
        System.out.printf("Distancia p1→p2:     %.2f%n", p1.distanciaA(p2));

        // ================================================================
        // F. RECORD (Java 16+) — clase inmutable compacta
        // ================================================================
        System.out.println("\n========== F. Record (Java 16+) ==========");

        // Record genera automáticamente: constructor, getters, equals,
        // hashCode y toString
        Coordenada coord1 = new Coordenada(40.4168, -3.7038); // Madrid
        Coordenada coord2 = new Coordenada(41.3851, 2.1734);  // Barcelona

        System.out.println("Madrid    : " + coord1);
        System.out.println("Barcelona : " + coord2);
        System.out.println("Lat Madrid: " + coord1.latitud());  // getter automático
        System.out.println("¿Iguales? : " + coord1.equals(coord2));

        // Los records son inmutables: no se pueden modificar sus campos
        // coord1.latitud = 0; // ERROR
    }
}

// ====================================================================
// CLASES DE EJEMPLO
// ====================================================================

class CuentaBancaria {
    private final String numeroCuenta;  // final: no cambia tras construcción
    private double saldo;
    private int    numTransacciones;

    public CuentaBancaria(String numeroCuenta, double saldoInicial) {
        if (saldoInicial < 0) throw new IllegalArgumentException("Saldo inicial no puede ser negativo");
        this.numeroCuenta    = numeroCuenta;
        this.saldo           = saldoInicial;
        this.numTransacciones = 0;
    }

    public void depositar(double cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser positiva");
        saldo += cantidad;
        numTransacciones++;
        System.out.println("Depósito: +" + cantidad + " → Saldo: " + saldo);
    }

    public void retirar(double cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser positiva");
        if (cantidad > saldo) throw new IllegalArgumentException("Saldo insuficiente");
        saldo -= cantidad;
        numTransacciones++;
        System.out.println("Retiro: -" + cantidad + " → Saldo: " + saldo);
    }

    public double getSaldo()           { return saldo; }
    public String getNumeroCuenta()    { return numeroCuenta; }
    public int    getNumTransacciones(){ return numTransacciones; }

    @Override
    public String toString() {
        return String.format("Cuenta[%s, saldo=%.2f, transacciones=%d]",
                numeroCuenta, saldo, numTransacciones);
    }
}

class Circulo {
    private final double radio; // final: el radio no cambia

    public Circulo(double radio) {
        if (radio <= 0) throw new IllegalArgumentException("Radio debe ser positivo");
        this.radio = radio;
    }

    public double getRadio()            { return radio; }
    public double calcularArea()        { return Math.PI * radio * radio; }
    public double calcularPerimetro()   { return 2 * Math.PI * radio; }
}

class Impuesto {
    // Constantes de clase
    public static final double IVA                    = 0.21;
    public static final double IRPF                   = 0.15;
    public static final double BASE_MINIMA_COTIZACION = 1080.0;

    // Constructor privado: no se puede instanciar (solo constantes y métodos)
    private Impuesto() {}

    public static double calcularNeto(double bruto) {
        return bruto * (1 - IRPF);
    }
}

class Empleado {
    private String nombre;
    private double salario;

    public Empleado(String nombre, double salario) {
        setNombre(nombre);   // reutilizar validación del setter
        setSalario(salario);
    }

    public String getNombre()  { return nombre; }
    public double getSalario() { return salario; }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre.trim();
        }
    }

    public void setSalario(double salario) {
        if (salario >= 0) this.salario = salario;
    }

    @Override
    public String toString() {
        return String.format("Empleado{nombre='%s', salario=%.2f}", nombre, salario);
    }
}

// Clase inmutable
final class Punto {
    private final int x;
    private final int y;

    public Punto(int x, int y) { this.x = x; this.y = y; }

    public int getX() { return x; }
    public int getY() { return y; }

    // Devuelve un NUEVO Punto (no modifica this)
    public Punto trasladar(int dx, int dy) { return new Punto(x + dx, y + dy); }

    public double distanciaAlOrigen() { return Math.hypot(x, y); }
    public double distanciaA(Punto otro) { return Math.hypot(otro.x - x, otro.y - y); }

    @Override public String toString()  { return "(" + x + ", " + y + ")"; }
    @Override public boolean equals(Object o) {
        if (!(o instanceof Punto p)) return false;
        return x == p.x && y == p.y;
    }
    @Override public int hashCode() { return java.util.Objects.hash(x, y); }
}

// Record: clase inmutable con sintaxis compacta (Java 16+)
record Coordenada(double latitud, double longitud) {}
