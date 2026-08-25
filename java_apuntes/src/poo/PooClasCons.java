package poo;
/**
 * POO — CLASES, OBJETOS Y CONSTRUCTORES
 * ========================================
 * La Programación Orientada a Objetos (POO) organiza el código en
 * objetos que combinan ESTADO (campos) y COMPORTAMIENTO (métodos).
 *
 * PILARES DE LA POO:
 *   1. Encapsulación  → ocultar datos internos
 *   2. Herencia       → reutilizar código entre clases
 *   3. Polimorfismo   → misma interfaz, distintos comportamientos
 *   4. Abstracción    → modelar el mundo real simplificado
 *
 * TERMINOLOGÍA:
 *   Clase    → plantilla / molde
 *   Objeto   → instancia de una clase
 *   Campo    → variable que pertenece a un objeto
 *   Método   → función que pertenece a un objeto
 *   this     → referencia al objeto actual
 */
public class PooClasCons {

    public static void main(String[] args) {

        // ================================================================
        // A. CREAR OBJETOS
        // ================================================================
        System.out.println("========== A. Crear objetos ==========");

        // new invoca el constructor y devuelve una referencia
        Persona p1 = new Persona("William", 25);
        Persona p2 = new Persona("Ana", 30);
        Persona p3 = new Persona(); // constructor sin argumentos

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        // ================================================================
        // B. ACCEDER A CAMPOS Y MÉTODOS
        // ================================================================
        System.out.println("\n========== B. Acceso a campos y métodos ==========");

        System.out.println("Nombre : " + p1.getNombre());
        System.out.println("Edad   : " + p1.getEdad());
        p1.setEdad(26);
        System.out.println("Nueva edad: " + p1.getEdad());
        p1.saludar();
        System.out.println("¿Es mayor de edad? " + p1.esMayorDeEdad());

        // ================================================================
        // C. VARIABLES DE INSTANCIA VS VARIABLES DE CLASE (static)
        // ================================================================
        System.out.println("\n========== C. Instancia vs static ==========");

        // Cada objeto tiene su propia copia de los campos de instancia
        p1.setNombre("William García");
        System.out.println("p1.nombre: " + p1.getNombre()); // "William García"
        System.out.println("p2.nombre: " + p2.getNombre()); // "Ana" (no cambió)

        // Los campos static son compartidos por TODOS los objetos
        System.out.println("Total personas creadas: " + Persona.getTotalPersonas());

        // ================================================================
        // D. REFERENCIAS Y null
        // ================================================================
        System.out.println("\n========== D. Referencias y null ==========");

        Persona ref1 = p1;       // ref1 y p1 apuntan al MISMO objeto
        ref1.setNombre("Cambiado por ref1");
        System.out.println("p1 tras cambio por ref1: " + p1.getNombre()); // cambió

        Persona nulo = null;     // referencia que no apunta a ningún objeto
        System.out.println("nulo == null: " + (nulo == null));
        try {
            nulo.saludar();      // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("NullPointerException al llamar método en null");
        }

        // Comprobar antes de usar
        if (nulo != null) {
            nulo.saludar();
        } else {
            System.out.println("Objeto es null, no se puede llamar al método");
        }

        // ================================================================
        // E. MÉTODO toString()
        // ================================================================
        System.out.println("\n========== E. toString() ==========");
        Persona p4 = new Persona("Carlos", 28);
        System.out.println(p4);              // llama automáticamente a toString()
        System.out.println(p4.toString());   // explícito
        System.out.println("Persona: " + p4); // concatenación también llama toString()

        // ================================================================
        // F. MÉTODO equals() y hashCode()
        // ================================================================
        System.out.println("\n========== F. equals() y hashCode() ==========");
        Persona a = new Persona("Laura", 22);
        Persona b = new Persona("Laura", 22);
        Persona c = a;

        System.out.println("a == b (refs)    : " + (a == b));          // false
        System.out.println("a == c (refs)    : " + (a == c));          // true (misma ref)
        System.out.println("a.equals(b)      : " + a.equals(b));       // true (mismo contenido)
        System.out.println("a.hashCode()==b  : " + (a.hashCode() == b.hashCode())); // true

        // ================================================================
        // G. PASO POR VALOR vs PASO POR REFERENCIA
        // ================================================================
        System.out.println("\n========== G. Paso por valor / referencia ==========");

        // Primitivos: se pasa una COPIA del valor
        int x = 10;
        duplicarPrimitivo(x);
        System.out.println("x tras duplicarPrimitivo: " + x); // 10 (no cambió)

        // Objetos: se pasa una COPIA DE LA REFERENCIA
        // El objeto SÍ puede ser modificado desde el método
        Persona pRef = new Persona("Original", 20);
        modificarPersona(pRef);
        System.out.println("Tras modificarPersona: " + pRef.getNombre()); // "Modificado"

        // Pero reasignar la referencia dentro del método no afecta fuera
        Persona pOrig = new Persona("Fuera", 30);
        reasignarPersona(pOrig);
        System.out.println("Tras reasignarPersona: " + pOrig.getNombre()); // "Fuera" (no cambió)
    }

    static void duplicarPrimitivo(int n) { n *= 2; } // copia local

    static void modificarPersona(Persona p) {
        p.setNombre("Modificado"); // modifica el objeto real
    }

    static void reasignarPersona(Persona p) {
        p = new Persona("NuevoObjeto", 99); // solo cambia la copia local
    }
}

// ====================================================================
// CLASE Persona — definida fuera del main pero en el mismo fichero
// ====================================================================
class Persona {

    // ----------------------------------------------------------------
    // CAMPOS DE INSTANCIA (privados — encapsulación)
    // ----------------------------------------------------------------
    private String nombre;
    private int    edad;

    // CAMPO DE CLASE (static — compartido por todos los objetos)
    private static int totalPersonas = 0;

    // ----------------------------------------------------------------
    // CONSTRUCTORES
    // ----------------------------------------------------------------

    // Constructor sin argumentos (por defecto)
    public Persona() {
        this("Desconocido", 0); // delegar al constructor principal
    }

    // Constructor con todos los campos
    public Persona(String nombre, int edad) {
        // 'this.campo' para distinguir campo de parámetro
        this.nombre = nombre;
        this.edad   = edad;
        totalPersonas++;
    }

    // Constructor copia
    public Persona(Persona otra) {
        this(otra.nombre, otra.edad);
    }

    // ----------------------------------------------------------------
    // MÉTODOS DE INSTANCIA
    // ----------------------------------------------------------------

    public void saludar() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años.");
    }

    public boolean esMayorDeEdad() {
        return edad >= 18;
    }

    public void cumplirAnios() {
        edad++;
        System.out.println(nombre + " cumple años. Ahora tiene " + edad);
    }

    // ----------------------------------------------------------------
    // GETTERS Y SETTERS
    // ----------------------------------------------------------------

    public String getNombre() { return nombre; }
    public int    getEdad()   { return edad; }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre;
        }
    }

    public void setEdad(int edad) {
        if (edad >= 0 && edad <= 150) {
            this.edad = edad;
        }
    }

    // MÉTODO DE CLASE (static)
    public static int getTotalPersonas() { return totalPersonas; }

    // ----------------------------------------------------------------
    // MÉTODOS HEREDADOS DE Object
    // ----------------------------------------------------------------

    @Override
    public String toString() {
        return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                    // misma referencia
        if (obj == null) return false;                   // null
        if (!(obj instanceof Persona)) return false;     // tipo diferente
        Persona otra = (Persona) obj;
        return edad == otra.edad && nombre.equals(otra.nombre);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nombre, edad);
    }
}
