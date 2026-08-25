package tipos_genericos;
/**
 * GENÉRICOS — BOUNDS Y WILDCARDS
 * =================================
 *
 * BOUNDED TYPE PARAMETERS (tipos acotados):
 *   <T extends Clase>         → T debe ser Clase o subclase (upper bound)
 *   <T extends A & B & C>     → T debe implementar A, B y C (multi-bound)
 *   <T super Clase>           → NO existe para parámetros, solo en wildcards
 *
 * WILDCARDS (comodines):
 *   <?>                       → cualquier tipo (unbounded)
 *   <? extends T>             → T o subclase (upper bounded wildcard)
 *   <? super T>               → T o superclase (lower bounded wildcard)
 *
 * REGLA PECS (Producer Extends, Consumer Super):
 *   Si lees de la estructura  → usa <? extends T>  (produce datos)
 *   Si escribes en estructura → usa <? super T>    (consume datos)
 *   Si lees y escribes        → usa <T> sin wildcard
 */
import java.util.ArrayList;
import java.util.List;

public class GenericosWildcards {

    public static void main(String[] args) {

        // ================================================================
        // A. UPPER BOUND — <T extends Tipo>
        //    T debe ser Tipo o alguna subclase
        // ================================================================
        System.out.println("========== A. Upper bound <T extends> ==========");

        // Solo acepta Number y subclases (Integer, Double, Long…)
        Caja<Integer> cajaI = new Caja<>(10);
        Caja<Double>  cajaD = new Caja<>(3.14);
        Caja<Long>    cajaL = new Caja<>(100L);
        // Caja<String> cajaS = new Caja<>("x"); // ERROR: String no es Number

        System.out.printf("Integer suma: %.2f%n", cajaI.sumaConOtra(cajaD));
        System.out.println("Mayor entre 10 y 3.14: " + cajaI.esMayorQue(cajaD));

        // Método con upper bound
        System.out.println("Suma lista Integer : " + sumarLista(List.of(1, 2, 3, 4, 5)));
        System.out.println("Suma lista Double  : " + sumarLista(List.of(1.1, 2.2, 3.3)));
        System.out.println("Suma lista Long    : " + sumarLista(List.of(10L, 20L, 30L)));

        // ================================================================
        // B. MULTIPLE BOUNDS — <T extends A & B>
        //    T debe cumplir TODOS los bounds
        // ================================================================
        System.out.println("\n========== B. Multiple bounds ==========");

        // Caja que requiere que T sea Comparable Y Serializable
        CajaMayor<Integer> ci = new CajaMayor<>(42);
        CajaMayor<String>  cs = new CajaMayor<>("Java");
        CajaMayor<Double>  cd = new CajaMayor<>(3.14);

        System.out.println("Mayor(42, 10)     : " + ci.mayorQue(10));
        System.out.println("Mayor(Java, Go)   : " + cs.mayorQue("Go"));
        System.out.println("Clonados Integer  : " + ci.clonarN(3));

        // Método con múltiples bounds
        System.out.println("Mayor de lista    : " + encontrarMayor(List.of(3, 1, 4, 1, 5, 9)));
        System.out.println("Mayor de strings  : " + encontrarMayor(List.of("banana", "manzana", "cereza")));

        // ================================================================
        // C. WILDCARD UNBOUNDED <?>
        //    Acepta cualquier tipo pero solo se puede LEER (Object)
        // ================================================================
        System.out.println("\n========== C. Wildcard <?> ==========");

        List<Integer> listaInt  = List.of(1, 2, 3);
        List<String>  listaStr  = List.of("a", "b", "c");
        List<Double>  listaDbl  = List.of(1.1, 2.2, 3.3);

        // La misma función acepta cualquier tipo de lista
        imprimirLista(listaInt);
        imprimirLista(listaStr);
        imprimirLista(listaDbl);

        // Con <?> solo puedes leer como Object, no puedes añadir
        List<?> cualquiera = listaInt;
        Object elemento = cualquiera.get(0); // OK: lee como Object
        // cualquiera.add(1); // ERROR de compilación: no se puede añadir

        // ================================================================
        // D. UPPER BOUNDED WILDCARD <? extends T>
        //    Puedes LEER como T, pero NO puedes añadir (producer)
        // ================================================================
        System.out.println("\n========== D. Upper bounded <? extends T> ==========");

        List<Integer> enteros  = List.of(1, 2, 3, 4, 5);
        List<Double>  decimales = List.of(1.5, 2.5, 3.5);
        List<Long>    largos   = List.of(10L, 20L, 30L);

        // Acepta cualquier lista de Number o subclase
        System.out.println("Suma enteros  : " + sumarWildcard(enteros));
        System.out.println("Suma decimales: " + sumarWildcard(decimales));
        System.out.println("Suma largos   : " + sumarWildcard(largos));

        // NO puedes añadir a una lista <? extends Number>
        List<? extends Number> soloLeer = enteros;
        // soloLeer.add(1); // ERROR: no se puede añadir (no se sabe el tipo exacto)
        Number n = soloLeer.get(0); // OK: se puede leer como Number

        // Copiar desde lista de subclase
        List<Integer> origen = new ArrayList<>(List.of(1, 2, 3));
        List<Number>  destino = new ArrayList<>();
        copiarExtends(origen, destino);
        System.out.println("Copia extends : " + destino);

        // ================================================================
        // E. LOWER BOUNDED WILDCARD <? super T>
        //    Puedes ESCRIBIR T, pero al leer solo obtienes Object (consumer)
        // ================================================================
        System.out.println("\n========== E. Lower bounded <? super T> ==========");

        List<Number>  listaNum    = new ArrayList<>();
        List<Object>  listaObj    = new ArrayList<>();

        // Acepta List<Integer>, List<Number> o List<Object>
        añadirEnteros(listaNum);  // Number es superclase de Integer → OK
        añadirEnteros(listaObj);  // Object es superclase de Integer → OK
        System.out.println("listaNum con enteros: " + listaNum);
        System.out.println("listaObj con enteros: " + listaObj);

        // Al leer de <? super Integer>, solo se garantiza Object
        List<? super Integer> soloEscribir = listaNum;
        soloEscribir.add(99);             // OK: añadir Integer
        Object obj = soloEscribir.get(0); // solo Object al leer
        System.out.println("Leído como Object: " + obj);

        // ================================================================
        // F. REGLA PECS EN PRÁCTICA — copiar listas
        // ================================================================
        System.out.println("\n========== F. PECS — Producer Extends Consumer Super ==========");

        List<Integer> produccion = List.of(10, 20, 30, 40, 50);
        List<Number>  consumo    = new ArrayList<>();

        // src: producer → extends   (leemos de ahí)
        // dst: consumer → super     (escribimos ahí)
        copiarPECS(produccion, consumo);
        System.out.println("PECS copia: " + consumo);

        // ================================================================
        // G. RESTRICCIONES DE LOS GENÉRICOS (type erasure)
        // ================================================================
        System.out.println("\n========== G. Restricciones (type erasure) ==========");

        System.out.println("Java borra los tipos genéricos en tiempo de ejecución (type erasure).");
        System.out.println("Consecuencias:");
        System.out.println("  1. No se puede usar instanceof con tipo genérico:");
        System.out.println("     obj instanceof Caja<String>  → ERROR");
        System.out.println("     obj instanceof Caja<?>       → OK");

        Object obj2 = new Caja<String>("test");
        System.out.println("  instanceof Caja<?>: " + (obj2 instanceof Caja<?>)); // OK

        System.out.println("\n  2. No se pueden crear arrays de tipos genéricos:");
        System.out.println("     new Caja<String>[10]         → ERROR");
        System.out.println("     new Caja<?>[10]              → OK");

        System.out.println("\n  3. No se pueden crear instancias de T:");
        System.out.println("     new T()                      → ERROR");
        System.out.println("     Solución: pasar Class<T> como parámetro");

        // Workaround: Class<T> como parámetro
        Fabrica<String>  fabStr = new Fabrica<>(String.class);
        Fabrica<Integer> fabInt = new Fabrica<>(Integer.class);
        System.out.println("  Fabrica String: " + fabStr.getTipo());
        System.out.println("  Fabrica Integer: " + fabInt.getTipo());

        System.out.println("\n  4. Los genéricos no funcionan con primitivos:");
        System.out.println("     Caja<int>   → ERROR");
        System.out.println("     Caja<Integer> → OK (autoboxing)");

        // ================================================================
        // H. COMPARAR TIPOS GENÉRICOS
        // ================================================================
        System.out.println("\n========== H. Comparar tipos ==========");

        // Con Comparable
        System.out.println("max(3,7)      : " + max(3, 7));
        System.out.println("max(\"a\",\"z\") : " + max("a", "z"));
        System.out.println("min lista     : " + minLista(List.of(5, 2, 8, 1, 9)));
        System.out.println("max lista     : " + maxLista(List.of(5, 2, 8, 1, 9)));
    }

    // ----------------------------------------------------------------
    // MÉTODOS CON BOUNDS
    // ----------------------------------------------------------------

    // Upper bound en método: solo Number y subclases
    static double sumarLista(List<? extends Number> lista) {
        double suma = 0;
        for (Number n : lista) suma += n.doubleValue();
        return suma;
    }

    // Unbounded wildcard: solo lectura como Object
    static void imprimirLista(List<?> lista) {
        System.out.print("Lista<?> : ");
        lista.forEach(e -> System.out.print(e + " "));
        System.out.println();
    }

    // Upper bounded wildcard — leer de extends
    static double sumarWildcard(List<? extends Number> lista) {
        return lista.stream().mapToDouble(Number::doubleValue).sum();
    }

    // PECS: src produce (extends), dst consume (super)
    static <T> void copiarPECS(List<? extends T> src, List<? super T> dst) {
        for (T elemento : src) dst.add(elemento);
    }

    // Lower bounded: añadir Integer a lista de Integer o superior
    static void añadirEnteros(List<? super Integer> lista) {
        for (int i = 1; i <= 5; i++) lista.add(i);
    }

    // Copiar con extends
    static void copiarExtends(List<? extends Number> src, List<Number> dst) {
        dst.addAll(src);
    }

    // Multiple bounds: T debe ser Comparable
    static <T extends Comparable<T>> T encontrarMayor(List<T> lista) {
        T mayor = lista.get(0);
        for (T e : lista) if (e.compareTo(mayor) > 0) mayor = e;
        return mayor;
    }

    // Comparar dos elementos
    static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    static <T extends Comparable<T>> T minLista(List<T> lista) {
        return lista.stream().min(java.util.Comparator.naturalOrder()).orElseThrow();
    }

    static <T extends Comparable<T>> T maxLista(List<T> lista) {
        return lista.stream().max(java.util.Comparator.naturalOrder()).orElseThrow();
    }
}

// ====================================================================
// CLASES GENÉRICAS CON BOUNDS
// ====================================================================

// T debe ser Number — solo tipos numéricos
class Caja<T extends Number> {
    private T contenido;

    public Caja(T contenido) { this.contenido = contenido; }
    public T getContenido()  { return contenido; }

    public double getValorDouble() { return contenido.doubleValue(); }

    public double sumaConOtra(Caja<? extends Number> otra) {
        return this.getValorDouble() + otra.getValorDouble();
    }

    public boolean esMayorQue(Caja<? extends Number> otra) {
        return this.getValorDouble() > otra.getValorDouble();
    }

    @Override
    public String toString() { return "Caja[" + contenido + "]"; }
}

// T debe ser Comparable Y Serializable (multi-bound)
class CajaMayor<T extends Comparable<T> & java.io.Serializable> {
    private T contenido;

    public CajaMayor(T contenido) { this.contenido = contenido; }
    public T getContenido() { return contenido; }

    public boolean mayorQue(T otro) {
        return contenido.compareTo(otro) > 0;
    }

    // Devuelve una lista con n copias del contenido
    public List<T> clonarN(int n) {
        List<T> lista = new ArrayList<>();
        for (int i = 0; i < n; i++) lista.add(contenido);
        return lista;
    }
}

// Workaround para crear instancias de T con Class<T>
class Fabrica<T> {
    private final Class<T> tipo;

    public Fabrica(Class<T> tipo) { this.tipo = tipo; }
    public String getTipo()       { return tipo.getSimpleName(); }

    public T crear() throws Exception {
        return tipo.getDeclaredConstructor().newInstance();
    }
}
