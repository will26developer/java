package tipos_genericos;
/**
 * GENÉRICOS — INTERFACES, HERENCIA Y CASOS DE USO REALES
 * =========================================================
 * Cómo usar genéricos en:
 *   - Interfaces genéricas
 *   - Herencia con genéricos
 *   - Repositorio genérico (patrón común en Spring/JPA)
 *   - Result/Optional propio
 *   - Funciones de orden superior genéricas
 */
import java.util.*;
import java.util.function.*;

public class GenericosAvanzados {

    public static void main(String[] args) {

        // ================================================================
        // A. INTERFACES GENÉRICAS
        // ================================================================
        System.out.println("========== A. Interfaces genéricas ==========");

        Transformador<String, Integer> longitud   = String::length;
        Transformador<Integer, String> aString    = Object::toString;
        Transformador<String, String>  mayusculas = String::toUpperCase;
        Transformador<Integer, Boolean> esPar     = n -> n % 2 == 0;

        System.out.println("longitud(\"Java\")  : " + longitud.aplicar("Java"));
        System.out.println("aString(42)       : " + aString.aplicar(42));
        System.out.println("mayúsculas        : " + mayusculas.aplicar("hola mundo"));
        System.out.println("esPar(4)          : " + esPar.aplicar(4));
        System.out.println("esPar(7)          : " + esPar.aplicar(7));

        // Composición: aplicar una transformación sobre el resultado de otra
        Transformador<String, Boolean> esLarga = longitud.andThen(esPar)::aplicar;
        System.out.println("esLarga(\"Java\")   : " + esLarga.aplicar("Java"));     // 4 → true
        System.out.println("esLarga(\"Go\")     : " + esLarga.aplicar("Go"));       // 2 → true

        // ================================================================
        // B. REPOSITORIO GENÉRICO
        //    Patrón típico en aplicaciones empresariales (Spring/JPA)
        // ================================================================
        System.out.println("\n========== B. Repositorio genérico ==========");

        RepositorioMemoria<Usuario, Integer> repoUsuarios = new RepositorioMemoria<>();
        repoUsuarios.guardar(new Usuario(1, "William", "william@email.com"));
        repoUsuarios.guardar(new Usuario(2, "Ana",     "ana@email.com"));
        repoUsuarios.guardar(new Usuario(3, "Carlos",  "carlos@email.com"));

        // findById devuelve Optional
        repoUsuarios.buscarPorId(2)
                .ifPresentOrElse(
                    u -> System.out.println("Encontrado: " + u),
                    ()  -> System.out.println("No encontrado")
                );

        repoUsuarios.buscarPorId(99)
                .ifPresentOrElse(
                    u -> System.out.println("Encontrado: " + u),
                    ()  -> System.out.println("Usuario 99 no encontrado")
                );

        System.out.println("Todos: " + repoUsuarios.buscarTodos());
        System.out.println("Total: " + repoUsuarios.contar());

        repoUsuarios.eliminar(2);
        System.out.println("Tras eliminar 2: " + repoUsuarios.buscarTodos());

        // El mismo repositorio con otro tipo
        RepositorioMemoria<Producto, String> repoProductos = new RepositorioMemoria<>();
        repoProductos.guardar(new Producto("P001", "Teclado", 49.99));
        repoProductos.guardar(new Producto("P002", "Ratón", 29.99));
        System.out.println("Productos: " + repoProductos.buscarTodos());

        // ================================================================
        // C. RESULT<T> — manejo funcional de errores (similar a Optional)
        // ================================================================
        System.out.println("\n========== C. Result<T> ==========");

        Result<Integer> exito  = Result.ok(42);
        Result<Integer> fallo  = Result.error("Valor no encontrado");
        Result<Integer> fallo2 = Result.error("Operación inválida");

        System.out.println("exito.esOk()     : " + exito.esOk());
        System.out.println("fallo.esOk()     : " + fallo.esOk());
        System.out.println("exito.valor()    : " + exito.valor());
        System.out.println("fallo.error()    : " + fallo.error());
        System.out.println("exito o 0        : " + exito.valorODefecto(0));
        System.out.println("fallo o 0        : " + fallo.valorODefecto(0));

        // Encadenar operaciones
        Result<String> resultado = parsearEntero("42")
                .map(n -> n * 2)
                .map(Object::toString);
        System.out.println("Encadenado OK    : " + resultado);

        Result<String> error = parsearEntero("abc")
                .map(n -> n * 2)
                .map(Object::toString);
        System.out.println("Encadenado Error : " + error);

        // ================================================================
        // D. PIPELINE FUNCIONAL GENÉRICO
        // ================================================================
        System.out.println("\n========== D. Pipeline funcional ==========");

        List<String> lenguajes = List.of("Java", "Python", "TypeScript", "Go", "Rust", "C++");

        // Pipeline: filtrar → transformar → reducir
        String resultado2 = Pipeline.of(lenguajes)
                .filtrar(s -> s.length() > 3)
                .transformar(String::toUpperCase)
                .transformar(s -> s + "!")
                .reducir(String.join(", ", lenguajes)); // fallback si lista vacía

        System.out.println("Pipeline resultado: ");
        Pipeline.of(lenguajes)
                .filtrar(s -> s.length() > 3)
                .transformar(String::toUpperCase)
                .obtener()
                .forEach(s -> System.out.println("  " + s));

        // ================================================================
        // E. CACHÉ GENÉRICA CON TTL
        // ================================================================
        System.out.println("\n========== E. Caché genérica ==========");

        Cache<String, String> cache = new Cache<>(3); // máximo 3 entradas
        cache.put("usuario:1", "William");
        cache.put("usuario:2", "Ana");
        cache.put("config:tema", "oscuro");

        System.out.println("usuario:1   : " + cache.get("usuario:1"));
        System.out.println("no existe   : " + cache.get("usuario:99"));
        System.out.println("Tamaño      : " + cache.tamaño());

        cache.put("nuevo", "valor");       // supera el límite → elimina el más antiguo
        System.out.println("Tamaño tras add: " + cache.tamaño());

        // ================================================================
        // F. FUNCIÓN DE ORDEN SUPERIOR GENÉRICA
        // ================================================================
        System.out.println("\n========== F. Funciones de orden superior ==========");

        List<Integer> numeros = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        // map: transforma cada elemento
        List<Integer> cuadrados = Funcional.map(numeros, n -> n * n);
        System.out.println("map(n²)     : " + cuadrados);

        // filter: filtra elementos
        List<Integer> pares = Funcional.filter(numeros, n -> n % 2 == 0);
        System.out.println("filter(par) : " + pares);

        // reduce: combina todos en uno
        int suma = Funcional.reduce(numeros, 0, Integer::sum);
        System.out.println("reduce(+)   : " + suma);

        // flatMap: aplana lista de listas
        List<List<Integer>> anidada = List.of(List.of(1,2), List.of(3,4), List.of(5));
        List<Integer> plana = Funcional.flatMap(anidada, x -> x);
        System.out.println("flatMap     : " + plana);

        // zip: combina dos listas elemento a elemento
        List<String> nombres = List.of("Ana", "Ben", "Cara");
        List<Integer> edades = List.of(25, 30, 22);
        List<String> zipeado = Funcional.zip(nombres, edades,
                (n, e) -> n + "(" + e + ")");
        System.out.println("zip         : " + zipeado);
    }

    static Result<Integer> parsearEntero(String s) {
        try {
            return Result.ok(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Result.error("No es un número: " + s);
        }
    }
}

// ====================================================================
// INTERFAZ GENÉRICA CON DOS TIPOS
// ====================================================================
@FunctionalInterface
interface Transformador<E, S> {
    S aplicar(E entrada);

    default <R> Transformador<E, R> andThen(Transformador<S, R> siguiente) {
        return entrada -> siguiente.aplicar(this.aplicar(entrada));
    }
}

// ====================================================================
// INTERFAZ REPOSITORIO GENÉRICO
// ====================================================================
interface Repositorio<T, ID> {
    void guardar(T entidad);
    Optional<T> buscarPorId(ID id);
    List<T> buscarTodos();
    void eliminar(ID id);
    long contar();
}

// Implementación en memoria
class RepositorioMemoria<T extends Identificable<ID>, ID>
        implements Repositorio<T, ID> {

    private Map<ID, T> almacen = new LinkedHashMap<>();

    @Override public void     guardar(T e)        { almacen.put(e.getId(), e); }
    @Override public Optional<T> buscarPorId(ID id){ return Optional.ofNullable(almacen.get(id)); }
    @Override public List<T>  buscarTodos()        { return new ArrayList<>(almacen.values()); }
    @Override public void     eliminar(ID id)      { almacen.remove(id); }
    @Override public long     contar()             { return almacen.size(); }
}

interface Identificable<ID> { ID getId(); }

class Usuario implements Identificable<Integer> {
    private int id; private String nombre, email;
    public Usuario(int id, String nombre, String email) { this.id=id; this.nombre=nombre; this.email=email; }
    @Override public Integer getId() { return id; }
    @Override public String toString() { return "Usuario[" + id + ":" + nombre + "]"; }
}

class Producto implements Identificable<String> {
    private String id, nombre; private double precio;
    public Producto(String id, String nombre, double precio) { this.id=id; this.nombre=nombre; this.precio=precio; }
    @Override public String getId() { return id; }
    @Override public String toString() { return String.format("Producto[%s:%s %.2f€]", id, nombre, precio); }
}

// ====================================================================
// RESULT<T> — contenedor de éxito o error
// ====================================================================
class Result<T> {
    private final T      valor;
    private final String error;
    private final boolean ok;

    private Result(T valor, String error, boolean ok) {
        this.valor = valor; this.error = error; this.ok = ok;
    }

    public static <T> Result<T> ok(T valor)       { return new Result<>(valor, null, true); }
    public static <T> Result<T> error(String msg) { return new Result<>(null, msg, false); }

    public boolean esOk()           { return ok; }
    public T       valor()          { if (!ok) throw new NoSuchElementException(error); return valor; }
    public String  error()          { return error; }
    public T       valorODefecto(T d){ return ok ? valor : d; }

    public <U> Result<U> map(Function<T, U> f) {
        return ok ? Result.ok(f.apply(valor)) : Result.error(error);
    }

    @Override public String toString() {
        return ok ? "Ok(" + valor + ")" : "Error(" + error + ")";
    }
}

// ====================================================================
// PIPELINE FUNCIONAL GENÉRICO
// ====================================================================
class Pipeline<T> {
    private List<T> datos;

    private Pipeline(List<T> datos) { this.datos = new ArrayList<>(datos); }

    public static <T> Pipeline<T> of(List<T> datos) { return new Pipeline<>(datos); }

    public Pipeline<T> filtrar(Predicate<T> pred) {
        datos.removeIf(pred.negate());
        return this;
    }

    public Pipeline<T> transformar(UnaryOperator<T> op) {
        datos.replaceAll(op);
        return this;
    }

    public T reducir(T identidad) {
        return datos.isEmpty() ? identidad : datos.get(0);
    }

    public List<T> obtener() { return Collections.unmodifiableList(datos); }
}

// ====================================================================
// CACHÉ GENÉRICA
// ====================================================================
class Cache<K, V> {
    private final int capacidad;
    private final LinkedHashMap<K, V> mapa;

    public Cache(int capacidad) {
        this.capacidad = capacidad;
        this.mapa = new LinkedHashMap<>(capacidad, 0.75f, true) {
            @Override protected boolean removeEldestEntry(Map.Entry<K,V> e) {
                return size() > capacidad;
            }
        };
    }

    public void     put(K k, V v)  { mapa.put(k, v); }
    public Optional<V> get(K k)    { return Optional.ofNullable(mapa.get(k)); }
    public int      tamaño()       { return mapa.size(); }
    public boolean  contiene(K k)  { return mapa.containsKey(k); }
}

// ====================================================================
// FUNCIONES DE ORDEN SUPERIOR GENÉRICAS
// ====================================================================
class Funcional {

    public static <T, R> List<R> map(List<T> lista, Function<T, R> f) {
        List<R> resultado = new ArrayList<>();
        for (T e : lista) resultado.add(f.apply(e));
        return resultado;
    }

    public static <T> List<T> filter(List<T> lista, Predicate<T> pred) {
        List<T> resultado = new ArrayList<>();
        for (T e : lista) if (pred.test(e)) resultado.add(e);
        return resultado;
    }

    public static <T> T reduce(List<T> lista, T identidad, BinaryOperator<T> op) {
        T acum = identidad;
        for (T e : lista) acum = op.apply(acum, e);
        return acum;
    }

    public static <T> List<T> flatMap(List<List<T>> lista, Function<List<T>, List<T>> f) {
        List<T> resultado = new ArrayList<>();
        for (List<T> sub : lista) resultado.addAll(f.apply(sub));
        return resultado;
    }

    public static <A, B, R> List<R> zip(List<A> la, List<B> lb, BiFunction<A, B, R> f) {
        List<R> resultado = new ArrayList<>();
        int n = Math.min(la.size(), lb.size());
        for (int i = 0; i < n; i++) resultado.add(f.apply(la.get(i), lb.get(i)));
        return resultado;
    }
}
