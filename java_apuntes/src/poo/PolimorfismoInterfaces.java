package poo;
/**
 * POLIMORFISMO, CLASES ABSTRACTAS E INTERFACES
 * ==============================================
 *
 * POLIMORFISMO: mismo mensaje, distintos comportamientos según el tipo real.
 *   - Compilación (sobrecarga)  → mismo nombre, distintos parámetros
 *   - Ejecución (sobreescritura)→ método en subclase reemplaza al del padre
 *
 * CLASE ABSTRACTA:
 *   - No se puede instanciar directamente
 *   - Puede tener métodos abstractos (sin implementación) y concretos
 *   - Una subclase DEBE implementar todos los métodos abstractos
 *
 * INTERFAZ:
 *   - Contrato que las clases se comprometen a cumplir
 *   - Todos los métodos son públicos (abstractos por defecto)
 *   - Puede tener métodos default y static (Java 8+)
 *   - Una clase puede implementar MÚLTIPLES interfaces
 *   - Los campos son public static final (constantes)
 */
public class PolimorfismoInterfaces {

    public static void main(String[] args) {

        // ================================================================
        // A. SOBRECARGA (OVERLOADING) — polimorfismo en compilación
        // ================================================================
        System.out.println("========== A. Sobrecarga ==========");
        Calculadora calc = new Calculadora();

        System.out.println("sumar(3,4)       = " + calc.sumar(3, 4));
        System.out.println("sumar(3,4,5)     = " + calc.sumar(3, 4, 5));
        System.out.println("sumar(3.0,4.0)   = " + calc.sumar(3.0, 4.0));
        System.out.println("sumar(\"3\",\"4\")   = " + calc.sumar("3", "4"));

        // ================================================================
        // B. SOBREESCRITURA (OVERRIDING) — polimorfismo en ejecución
        // ================================================================
        System.out.println("\n========== B. Sobreescritura ==========");

        // La variable es de tipo Animal (padre), pero apunta a subclases
        Animal[] animales = {
            new Perro("Rex"),
            new Gato("Misi"),
            new Pajaro("Pío"),
            new Perro("Max")
        };

        // Se llama al método de la clase REAL del objeto (late binding)
        for (Animal a : animales) {
            a.hacerSonido();          // polimorfismo en acción
            System.out.println(a);    // toString sobreescrito
        }

        // ================================================================
        // C. CLASE ABSTRACTA
        // ================================================================
        System.out.println("\n========== C. Clase abstracta ==========");

        // Animal animal = new Animal("X"); // ERROR: no se puede instanciar abstracta

        Perro p = new Perro("Buddy");
        p.hacerSonido();
        p.dormir();      // método concreto heredado
        p.describir();   // método concreto con llamada a abstracto

        // ================================================================
        // D. INTERFACES
        // ================================================================
        System.out.println("\n========== D. Interfaces ==========");

        // Implementaciones de Volable
        Volable[] voladores = {
            new Pajaro("Cóndor"),
            new Avion("Boeing 737"),
            new Dron("DJI Mini")
        };

        for (Volable v : voladores) {
            v.despegar();
            v.volar();
            v.aterrizar();
            System.out.println("Altitud máx: " + v.getAltitudMaxima() + "m");
            System.out.println();
        }

        // ================================================================
        // E. MÚLTIPLES INTERFACES
        //    Java no permite herencia múltiple de clases,
        //    pero sí implementar múltiples interfaces
        // ================================================================
        System.out.println("========== E. Múltiples interfaces ==========");

        Anfibio anfibio = new Anfibio("Rana");
        anfibio.nadar();   // de Nadable
        anfibio.saltar();  // de Saltable
        System.out.println("Nombre: " + anfibio.getNombre());

        // Polimorfismo con interfaz
        Nadable nadador = anfibio;
        nadador.nadar();

        Saltable saltador = anfibio;
        saltador.saltar();

        // ================================================================
        // F. MÉTODOS default EN INTERFACES (Java 8+)
        // ================================================================
        System.out.println("\n========== F. Métodos default ==========");

        Figura2[] figuras = {
            new Cuadrado2(4),
            new Triangulo2(3, 4, 5)
        };

        for (Figura2 f : figuras) {
            System.out.println(f.getClass().getSimpleName());
            System.out.printf("  área       = %.2f%n", f.calcularArea());
            System.out.printf("  perímetro  = %.2f%n", f.calcularPerimetro());
            System.out.printf("  es grande  = %b%n", f.esGrande()); // método default
        }

        // ================================================================
        // G. INTERFAZ FUNCIONAL Y LAMBDA (Java 8+)
        // ================================================================
        System.out.println("\n========== G. Interfaz funcional ==========");

        // Una interfaz con UN solo método abstracto = interfaz funcional
        Transformador doblar     = n -> n * 2;
        Transformador cuadrado   = n -> n * n;
        Transformador incrementar= n -> n + 1;

        int[] nums = {1, 2, 3, 4, 5};
        System.out.print("Doble     : ");
        aplicar(nums, doblar);
        System.out.print("Cuadrado  : ");
        aplicar(nums, cuadrado);
        System.out.print("Incremento: ");
        aplicar(nums, incrementar);

        // ================================================================
        // H. INTERFACE vs CLASE ABSTRACTA — cuándo usar cada uno
        // ================================================================
        System.out.println("\n========== H. Interface vs Clase abstracta ==========");
        System.out.println("Clase abstracta → relación ES-UN, comportamiento parcial compartido");
        System.out.println("Interface       → capacidades (PUEDE-HACER), contrato sin estado");
        System.out.println("Ejemplo:");
        System.out.println("  Animal (abstracta) → Perro, Gato, Pajaro");
        System.out.println("  Volable (interfaz) → Pajaro, Avion, Dron");
        System.out.println("  Pajaro extiende Animal E implementa Volable");
    }

    static void aplicar(int[] arr, Transformador t) {
        for (int n : arr) System.out.print(t.transformar(n) + " ");
        System.out.println();
    }
}

// ====================================================================
// SOBRECARGA
// ====================================================================

class Calculadora {
    public int    sumar(int a, int b)          { return a + b; }
    public int    sumar(int a, int b, int c)   { return a + b + c; }
    public double sumar(double a, double b)    { return a + b; }
    public String sumar(String a, String b)    { return a + b; }   // concatenación
}

// ====================================================================
// CLASE ABSTRACTA + SOBREESCRITURA
// ====================================================================

abstract class Animal {
    protected String nombre;

    public Animal(String nombre) { this.nombre = nombre; }

    // Método abstracto: OBLIGATORIO sobreescribir en subclases
    public abstract void hacerSonido();

    // Método concreto: compartido por todos los hijos
    public void dormir() {
        System.out.println(nombre + " está durmiendo...");
    }

    // Método que usa el abstracto (Template Method pattern)
    public void describir() {
        System.out.print(nombre + " dice: ");
        hacerSonido();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + nombre + "]";
    }
}

class Perro extends Animal {
    public Perro(String nombre) { super(nombre); }

    @Override
    public void hacerSonido() { System.out.println(nombre + ": ¡Guau guau!"); }
}

class Gato extends Animal {
    public Gato(String nombre) { super(nombre); }

    @Override
    public void hacerSonido() { System.out.println(nombre + ": ¡Miau!"); }
}

// ====================================================================
// INTERFACES
// ====================================================================

interface Volable {
    // Constante de interfaz (public static final implícito)
    int ALTITUD_MINIMA = 10;

    // Métodos abstractos (public abstract implícito)
    void despegar();
    void volar();
    void aterrizar();
    int  getAltitudMaxima();

    // Método default (Java 8+) — implementación por defecto
    default String getDescripcion() {
        return "Objeto capaz de volar hasta " + getAltitudMaxima() + "m";
    }

    // Método static en interfaz (Java 8+)
    static boolean puedeVolarAlto(Volable v) {
        return v.getAltitudMaxima() > 1000;
    }
}

interface Nadable {
    void nadar();
    default String getNombre() { return "Nadador desconocido"; }
}

interface Saltable {
    void saltar();
    default String getNombre() { return "Saltador desconocido"; }
}

// ====================================================================
// IMPLEMENTACIONES
// ====================================================================

class Pajaro extends Animal implements Volable {
    public Pajaro(String nombre) { super(nombre); }

    @Override public void hacerSonido() { System.out.println(nombre + ": ¡Pío pío!"); }
    @Override public void despegar()    { System.out.println(nombre + " abre las alas"); }
    @Override public void volar()       { System.out.println(nombre + " vuela por el cielo"); }
    @Override public void aterrizar()   { System.out.println(nombre + " posa en una rama"); }
    @Override public int  getAltitudMaxima() { return 3000; }
}

class Avion implements Volable {
    private String modelo;
    public Avion(String modelo) { this.modelo = modelo; }

    @Override public void despegar()   { System.out.println(modelo + " acelera por la pista"); }
    @Override public void volar()      { System.out.println(modelo + " vuela a 10.000m"); }
    @Override public void aterrizar()  { System.out.println(modelo + " toca la pista"); }
    @Override public int  getAltitudMaxima() { return 12000; }
}

class Dron implements Volable {
    private String modelo;
    public Dron(String modelo) { this.modelo = modelo; }

    @Override public void despegar()   { System.out.println(modelo + " eleva rotores"); }
    @Override public void volar()      { System.out.println(modelo + " patrulla la zona"); }
    @Override public void aterrizar()  { System.out.println(modelo + " aterriza suavemente"); }
    @Override public int  getAltitudMaxima() { return 500; }
}

// Implementa múltiples interfaces
class Anfibio {
    private String nombre;
    public Anfibio(String nombre) { this.nombre = nombre; }

    // Implementa Nadable
    public void nadar()  { System.out.println(nombre + " nada en el estanque"); }
    // Implementa Saltable
    public void saltar() { System.out.println(nombre + " salta entre lirios"); }
    public String getNombre() { return nombre; }
}

// ====================================================================
// INTERFAZ CON default (Figura2)
// ====================================================================

interface Figura2 {
    double calcularArea();
    double calcularPerimetro();

    // Método default con lógica basada en otros métodos de la interfaz
    default boolean esGrande() { return calcularArea() > 20; }
    default String resumen()   {
        return String.format("área=%.2f, perímetro=%.2f", calcularArea(), calcularPerimetro());
    }
}

class Cuadrado2 implements Figura2 {
    private double lado;
    public Cuadrado2(double lado) { this.lado = lado; }
    @Override public double calcularArea()       { return lado * lado; }
    @Override public double calcularPerimetro()  { return 4 * lado; }
}

class Triangulo2 implements Figura2 {
    private double a, b, c;
    public Triangulo2(double a, double b, double c) { this.a=a; this.b=b; this.c=c; }
    @Override public double calcularPerimetro() { return a + b + c; }
    @Override public double calcularArea() {
        double s = calcularPerimetro() / 2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }
}

// Interfaz funcional (exactamente 1 método abstracto)
@FunctionalInterface
interface Transformador {
    int transformar(int n);
}
