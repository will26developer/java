package poo;
/**
 * HERENCIA
 * =========
 * La herencia permite que una clase (subclase/hija) herede campos
 * y métodos de otra clase (superclase/padre), reutilizando código
 * y estableciendo relaciones "ES-UN".
 *
 * PALABRAS CLAVE:
 *   extends  → establece la herencia
 *   super    → referencia a la superclase
 *   @Override→ indica que se sobreescribe un método
 *   final    → en clase/método: impide heredar/sobreescribir
 *
 * REGLAS:
 *   - Java solo admite herencia SIMPLE (una sola clase padre)
 *   - Toda clase hereda implícitamente de Object
 *   - El constructor de la subclase llama a super() como primera instrucción
 *   - Los miembros private NO se heredan (pero existen en el objeto)
 */
public class Herencia {

    public static void main(String[] args) {

        // ================================================================
        // A. CREAR OBJETOS DE SUBCLASES
        // ================================================================
        System.out.println("========== A. Jerarquía Figura ==========");

        Rectangulo rect = new Rectangulo(5, 3);
        Circulo2   circ = new Circulo2(4);
        Triangulo  tri  = new Triangulo(3, 4, 5);

        System.out.println(rect);
        System.out.println(circ);
        System.out.println(tri);

        System.out.printf("Rect  → área=%.2f, perímetro=%.2f%n",
                rect.calcularArea(), rect.calcularPerimetro());
        System.out.printf("Circ  → área=%.2f, perímetro=%.2f%n",
                circ.calcularArea(), circ.calcularPerimetro());
        System.out.printf("Tri   → área=%.2f, perímetro=%.2f%n",
                tri.calcularArea(), tri.calcularPerimetro());

        // ================================================================
        // B. POLIMORFISMO DE SUBTIPO
        //    Una variable de tipo padre puede apuntar a un hijo
        // ================================================================
        System.out.println("\n========== B. Polimorfismo de subtipo ==========");

        Figura[] figuras = {
            new Rectangulo(4, 6),
            new Circulo2(3),
            new Triangulo(5, 12, 13),
            new Rectangulo(2, 2)
        };

        double areaTotal = 0;
        for (Figura f : figuras) {
            System.out.printf("%-20s área=%.2f%n", f, f.calcularArea());
            areaTotal += f.calcularArea();
        }
        System.out.printf("Área total: %.2f%n", areaTotal);

        // ================================================================
        // C. instanceof Y CASTING
        // ================================================================
        System.out.println("\n========== C. instanceof y casting ==========");

        for (Figura f : figuras) {
            if (f instanceof Rectangulo r) {      // pattern matching Java 16+
                System.out.println("Rectángulo  ancho=" + r.getAncho() + " alto=" + r.getAlto());
            } else if (f instanceof Circulo2 c) {
                System.out.println("Círculo     radio=" + c.getRadio());
            } else if (f instanceof Triangulo t) {
                System.out.println("Triángulo   " + t.getTipoTriangulo());
            }
        }

        // ================================================================
        // D. LLAMADAS A super
        // ================================================================
        System.out.println("\n========== D. super ==========");

        Cuadrado cuad = new Cuadrado(4);
        System.out.println(cuad);
        System.out.printf("Área=%.2f, Perímetro=%.2f%n",
                cuad.calcularArea(), cuad.calcularPerimetro());

        // ================================================================
        // E. JERARQUÍA DE EMPLEADOS
        // ================================================================
        System.out.println("\n========== E. Jerarquía Empleado ==========");

        Empleado2    emp  = new Empleado2("Laura", 2000);
        Gerente      ger  = new Gerente("Carlos", 3000, 500);
        Desarrollador dev = new Desarrollador("William", 2500, "Java");

        Empleado2[] equipo = {emp, ger, dev};
        double nomina = 0;
        for (Empleado2 e : equipo) {
            System.out.printf("%-12s salario=%.2f, total=%.2f%n",
                    e.getNombre(), e.getSalario(), e.calcularSalarioTotal());
            nomina += e.calcularSalarioTotal();
        }
        System.out.printf("Nómina total: %.2f%n", nomina);

        // ================================================================
        // F. CLASE final — no se puede extender
        // ================================================================
        System.out.println("\n========== F. Clase final ==========");
        System.out.println("Las clases final no se pueden heredar.");
        System.out.println("Ejemplo: String, Integer, Math son clases final.");
        ClaseFinal cf = new ClaseFinal("Inmutable");
        System.out.println(cf);

        // ================================================================
        // G. HERENCIA DE Object
        // ================================================================
        System.out.println("\n========== G. Herencia de Object ==========");
        Object obj = new Rectangulo(3, 4);
        System.out.println("getClass()    : " + obj.getClass().getSimpleName());
        System.out.println("getClass full : " + obj.getClass().getName());
        System.out.println("hashCode()    : " + obj.hashCode());
        System.out.println("toString()    : " + obj.toString());
        System.out.println("instanceof Figura  : " + (obj instanceof Figura));
        System.out.println("instanceof Object  : " + (obj instanceof Object)); // siempre true
    }
}

// ====================================================================
// JERARQUÍA DE FIGURAS
// ====================================================================

abstract class Figura {
    private String color;

    public Figura(String color) { this.color = color; }
    public Figura() { this("negro"); }

    public String getColor() { return color; }
    public void   setColor(String c) { this.color = c; }

    // Métodos abstractos: cada subclase DEBE implementarlos
    public abstract double calcularArea();
    public abstract double calcularPerimetro();

    // Método concreto compartido
    public void describir() {
        System.out.printf("Figura %s: área=%.2f%n", getClass().getSimpleName(), calcularArea());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[color=" + color + "]";
    }
}

class Rectangulo extends Figura {
    private double ancho, alto;

    public Rectangulo(double ancho, double alto) {
        super("azul");       // llama al constructor de Figura
        this.ancho = ancho;
        this.alto  = alto;
    }

    public double getAncho() { return ancho; }
    public double getAlto()  { return alto; }

    @Override
    public double calcularArea()       { return ancho * alto; }

    @Override
    public double calcularPerimetro()  { return 2 * (ancho + alto); }

    @Override
    public String toString() {
        return super.toString() + String.format(", ancho=%.1f, alto=%.1f", ancho, alto);
    }
}

// Cuadrado ES-UN Rectangulo
class Cuadrado extends Rectangulo {
    public Cuadrado(double lado) {
        super(lado, lado); // delega al constructor de Rectangulo
    }

    public double getLado() { return getAncho(); }

    @Override
    public String toString() {
        return "Cuadrado[lado=" + getLado() + "]";
    }
}

class Circulo2 extends Figura {
    private double radio;

    public Circulo2(double radio) {
        super("rojo");
        this.radio = radio;
    }

    public double getRadio() { return radio; }

    @Override
    public double calcularArea()      { return Math.PI * radio * radio; }

    @Override
    public double calcularPerimetro() { return 2 * Math.PI * radio; }

    @Override
    public String toString() {
        return super.toString() + String.format(", radio=%.1f", radio);
    }
}

class Triangulo extends Figura {
    private double a, b, c;

    public Triangulo(double a, double b, double c) {
        super("verde");
        this.a = a; this.b = b; this.c = c;
    }

    @Override
    public double calcularPerimetro() { return a + b + c; }

    @Override
    public double calcularArea() {
        // Fórmula de Herón
        double s = calcularPerimetro() / 2;
        return Math.sqrt(s * (s-a) * (s-b) * (s-c));
    }

    public String getTipoTriangulo() {
        if (a == b && b == c) return "equilátero";
        if (a == b || b == c || a == c) return "isósceles";
        return "escaleno";
    }
}

// ====================================================================
// JERARQUÍA DE EMPLEADOS
// ====================================================================

class Empleado2 {
    private String nombre;
    private double salario;

    public Empleado2(String nombre, double salario) {
        this.nombre  = nombre;
        this.salario = salario;
    }

    public String getNombre()  { return nombre; }
    public double getSalario() { return salario; }

    // Método que las subclases pueden sobreescribir
    public double calcularSalarioTotal() { return salario; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + nombre + "]";
    }
}

class Gerente extends Empleado2 {
    private double bonus;

    public Gerente(String nombre, double salario, double bonus) {
        super(nombre, salario); // constructor del padre
        this.bonus = bonus;
    }

    @Override
    public double calcularSalarioTotal() {
        return super.calcularSalarioTotal() + bonus; // llama al padre + añade
    }
}

class Desarrollador extends Empleado2 {
    private String tecnologia;
    private static final double PLUS_TECNOLOGIA = 300.0;

    public Desarrollador(String nombre, double salario, String tecnologia) {
        super(nombre, salario);
        this.tecnologia = tecnologia;
    }

    public String getTecnologia() { return tecnologia; }

    @Override
    public double calcularSalarioTotal() {
        return super.calcularSalarioTotal() + PLUS_TECNOLOGIA;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + tecnologia + "]";
    }
}

// Clase final: no puede tener subclases
final class ClaseFinal {
    private final String valor;
    public ClaseFinal(String valor) { this.valor = valor; }
    @Override public String toString() { return "ClaseFinal[" + valor + "]"; }
}
