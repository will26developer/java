package tipos_genericos;
/**
 * TIPOS GENÉRICOS — FUNDAMENTOS
 * ================================
 * Los genéricos permiten escribir código que funciona con cualquier
 * tipo de dato manteniendo la seguridad de tipos en tiempo de compilación.
 *
 * Sin genéricos → se usa Object → hay que hacer casting → errores en runtime
 * Con genéricos → el tipo se comprueba en compilación → código más seguro
 *
 * SINTAXIS:
 *   <T>        → un tipo genérico
 *   <T, U>     → dos tipos genéricos
 *   <T extends Clase> → T debe ser Clase o subclase
 *   <?>        → wildcard: cualquier tipo desconocido
 *   <? extends T> → wildcard acotado superior
 *   <? super T>   → wildcard acotado inferior
 *
 * CONVENCIONES DE NOMBRES:
 *   T → Type (tipo general)
 *   E → Element (elementos de colecciones)
 *   K → Key (clave en mapas)
 *   V → Value (valor en mapas)
 *   N → Number (numérico)
 *   R → Return (tipo de retorno)
 */
public class GenericosFundamentos {

    public static void main(String[] args) {

        // ================================================================
        // A. EL PROBLEMA SIN GENÉRICOS
        // ================================================================
        System.out.println("========== A. Sin genéricos (problemático) ==========");

        // Sin genéricos: todo es Object → casting manual → ClassCastException
        CajaObject cajaObj = new CajaObject(42);
        int valor = (int) cajaObj.getContenido(); // casting manual
        System.out.println("Valor sin genérico: " + valor);

        CajaObject cajaObj2 = new CajaObject("Hola");
        try {
            int error = (int) cajaObj2.getContenido(); // ClassCastException en runtime
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage());
        }

        // ================================================================
        // B. CLASE GENÉRICA CON UN TIPO
        // ================================================================
        System.out.println("\n========== B. Clase genérica <T> ==========");

        Caja<Integer> cajaInt  = new Caja<>(42);
        Caja<String>  cajaStr  = new Caja<>("Java");
        Caja<Double>  cajaDbl  = new Caja<>(3.14);
        Caja<Boolean> cajaBool = new Caja<>(true);

        // No hace falta casting — el compilador ya sabe el tipo
        int    i  = cajaInt.getContenido();
        String s  = cajaStr.getContenido();
        double d  = cajaDbl.getContenido();
        boolean b = cajaBool.getContenido();

        System.out.println("Integer : " + cajaInt);
        System.out.println("String  : " + cajaStr);
        System.out.println("Double  : " + cajaDbl);
        System.out.println("Boolean : " + cajaBool);

        // El compilador detecta errores en compilación, no en runtime
        // cajaInt.setContenido("error"); // ERROR DE COMPILACIÓN

        // Métodos de la caja
        System.out.println("\nvacía?   : " + cajaInt.estaVacia());
        cajaInt.vaciar();
        System.out.println("vacía?   : " + cajaInt.estaVacia());
        cajaInt.setContenido(100);
        System.out.println("nuevo val: " + cajaInt.getContenido());

        // ================================================================
        // C. CLASE GENÉRICA CON DOS TIPOS
        // ================================================================
        System.out.println("\n========== C. Clase genérica <K, V> ==========");

        Par<String, Integer>   par1 = new Par<>("edad", 25);
        Par<String, String>    par2 = new Par<>("nombre", "William");
        Par<Integer, Double>   par3 = new Par<>(1, 3.14);
        Par<String, Boolean>   par4 = new Par<>("activo", true);

        System.out.println(par1);
        System.out.println(par2);
        System.out.println(par3);
        System.out.println(par4);

        // Swap: intercambiar clave y valor
        Par<Integer, String> swap = par1.swap();
        System.out.println("Swap: " + swap);

        // ================================================================
        // D. MÉTODO GENÉRICO
        // ================================================================
        System.out.println("\n========== D. Métodos genéricos ==========");

        // El tipo se infiere automáticamente del argumento
        System.out.println("imprimir(42)      : "); imprimir(42);
        System.out.println("imprimir(\"Java\")  : "); imprimir("Java");
        System.out.println("imprimir(true)    : "); imprimir(true);

        // Método genérico que devuelve valor
        String primerStr  = primero("a", "b", "c");
        Integer primerInt = primero(10, 20, 30);
        System.out.println("primero strings   : " + primerStr);
        System.out.println("primero ints      : " + primerInt);

        // Intercambiar posiciones en array
        Integer[] arr = {1, 2, 3, 4, 5};
        System.out.println("Antes swap: " + java.util.Arrays.toString(arr));
        intercambiar(arr, 0, 4);
        System.out.println("Tras swap : " + java.util.Arrays.toString(arr));

        // Copiar array genérico
        String[] origen  = {"Java", "Python", "Go"};
        String[] destino = new String[origen.length];
        copiar(origen, destino);
        System.out.println("Copia     : " + java.util.Arrays.toString(destino));

        // ================================================================
        // E. INFERENCIA DE TIPO (DIAMOND OPERATOR <>)
        // ================================================================
        System.out.println("\n========== E. Diamond operator <> ==========");

        // Java 7+: el compilador infiere el tipo del lado derecho
        Caja<String>    inferida1 = new Caja<>("inferida"); // <> sin tipo
        Par<String,Integer> inf2  = new Par<>("key", 99);

        // Java 10+: var también puede inferir tipos genéricos
        var inferida3 = new Caja<>(3.14);  // Caja<Double>
        System.out.println("inferida1: " + inferida1);
        System.out.println("inferida3: " + inferida3.getContenido().getClass().getSimpleName());

        // ================================================================
        // F. TIPOS PRIMITIVOS Y AUTOBOXING
        // ================================================================
        System.out.println("\n========== F. Primitivos y autoboxing ==========");

        // Los genéricos NO admiten tipos primitivos directamente
        // Caja<int> ERROR → usar Integer
        Caja<Integer> autobox = new Caja<>(42); // autoboxing: int → Integer
        int resultado = autobox.getContenido();  // unboxing: Integer → int
        System.out.println("Autoboxing : " + resultado);

        // ================================================================
        // G. RAW TYPES — tipos sin parámetro (código legacy)
        // ================================================================
        System.out.println("\n========== G. Raw types (evitar) ==========");

        // Raw type: Caja sin <T> — equivale a Caja<Object>
        // Genera warnings del compilador
        @SuppressWarnings("rawtypes")
        Caja raw = new Caja("raw sin tipo");
        @SuppressWarnings("unchecked")
        String rawStr = (String) raw.getContenido(); // requiere casting manual
        System.out.println("Raw type (evitar): " + rawStr);
        System.out.println("→ Siempre usa tipos parametrizados <T> en código nuevo");
    }

    // ----------------------------------------------------------------
    // MÉTODOS GENÉRICOS
    // ----------------------------------------------------------------

    // <T> antes del tipo de retorno declara el parámetro de tipo
    static <T> void imprimir(T elemento) {
        System.out.println("  [" + elemento.getClass().getSimpleName() + "] " + elemento);
    }

    @SafeVarargs
    static <T> T primero(T... elementos) {
        if (elementos.length == 0) throw new IllegalArgumentException("Sin elementos");
        return elementos[0];
    }

    static <T> void intercambiar(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static <T> void copiar(T[] origen, T[] destino) {
        for (int i = 0; i < Math.min(origen.length, destino.length); i++) {
            destino[i] = origen[i];
        }
    }
}

// ====================================================================
// CLASES
// ====================================================================

// Sin genéricos (problemático)
class CajaObject {
    private Object contenido;
    public CajaObject(Object contenido) { this.contenido = contenido; }
    public Object getContenido() { return contenido; }
}

// Con genérico — UN tipo
class Caja<T> {
    private T contenido;

    public Caja(T contenido) { this.contenido = contenido; }
    public Caja() { this.contenido = null; }

    public T       getContenido()         { return contenido; }
    public void    setContenido(T valor)  { this.contenido = valor; }
    public boolean estaVacia()            { return contenido == null; }
    public void    vaciar()               { this.contenido = null; }

    public String getTipo() {
        return contenido == null ? "null" : contenido.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Caja<" + getTipo() + ">[" + contenido + "]";
    }
}

// Con genérico — DOS tipos
class Par<K, V> {
    private final K clave;
    private final V valor;

    public Par(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public K getClave() { return clave; }
    public V getValor() { return valor; }

    // Devuelve un nuevo Par con los tipos intercambiados
    public Par<V, K> swap() { return new Par<>(valor, clave); }

    @Override
    public String toString() {
        return "Par(" + clave + " → " + valor + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Par<?, ?> otro)) return false;
        return java.util.Objects.equals(clave, otro.clave)
            && java.util.Objects.equals(valor, otro.valor);
    }

    @Override
    public int hashCode() { return java.util.Objects.hash(clave, valor); }
}
