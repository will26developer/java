package api_colecciones;
/**
 * COLLECTIONS API — LIST
 * ========================
 * List es una colección ORDENADA que permite duplicados e indexación.
 *
 * IMPLEMENTACIONES PRINCIPALES:
 *   ArrayList   → array dinámico, acceso O(1), inserción/borrado O(n)
 *   LinkedList  → lista doblemente enlazada, acceso O(n), insert/delete O(1)
 *   Vector      → como ArrayList pero sincronizado (legacy, evitar)
 *   Stack       → LIFO basado en Vector (legacy, usar Deque)
 *
 * CUÁNDO USAR CADA UNA:
 *   ArrayList  → lectura frecuente, pocas inserciones en medio
 *   LinkedList → muchas inserciones/borrados al inicio o en medio
 */
import java.util.*;
import java.util.stream.Collectors;

public class ColeccionesLista {

    public static void main(String[] args) {

        // ================================================================
        // A. CREAR LISTAS
        // ================================================================
        System.out.println("========== A. Crear listas ==========");

        // ArrayList — la más usada
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("Go");

        // LinkedList
        List<String> linkedList = new LinkedList<>();
        linkedList.add("Uno");
        linkedList.add("Dos");

        // List.of — INMUTABLE (Java 9+), no permite null ni modificar
        List<String> inmutable = List.of("a", "b", "c");

        // List.copyOf — copia inmutable (Java 10+)
        List<String> copia = List.copyOf(arrayList);

        // Arrays.asList — tamaño fijo pero permite set()
        List<String> fija = Arrays.asList("x", "y", "z");

        // Con tamaño inicial (optimización)
        List<Integer> conCapacidad = new ArrayList<>(100);

        // Desde otra colección
        List<String> desdeOtra = new ArrayList<>(inmutable);

        System.out.println("ArrayList  : " + arrayList);
        System.out.println("Inmutable  : " + inmutable);
        System.out.println("Arrays.asList: " + fija);

        // ================================================================
        // B. OPERACIONES BÁSICAS
        // ================================================================
        System.out.println("\n========== B. Operaciones básicas ==========");

        List<String> lista = new ArrayList<>(List.of("Ana", "Ben", "Cara", "David"));

        // Añadir
        lista.add("Eva");                    // al final
        lista.add(0, "Aaron");               // en posición
        lista.addAll(List.of("Fiona", "Gil")); // colección al final
        lista.addAll(2, List.of("X", "Y"));  // colección en posición
        System.out.println("Tras adds   : " + lista);

        // Leer
        System.out.println("get(0)      : " + lista.get(0));
        System.out.println("get(last)   : " + lista.get(lista.size() - 1));
        System.out.println("size()      : " + lista.size());
        System.out.println("isEmpty()   : " + lista.isEmpty());
        System.out.println("contains(Ana): " + lista.contains("Ana"));
        System.out.println("indexOf(X)  : " + lista.indexOf("X"));
        System.out.println("lastIndexOf : " + lista.lastIndexOf("X"));

        // Modificar
        lista.set(0, "AARON"); // reemplazar en posición
        System.out.println("Tras set(0) : " + lista.get(0));

        // Eliminar
        lista.remove("X");     // por objeto
        lista.remove(0);       // por índice
        lista.removeAll(List.of("Y", "Fiona", "Gil")); // varios
        System.out.println("Tras removes: " + lista);

        // Sublist — vista del original (cambios se reflejan en ambos)
        List<String> sub = lista.subList(1, 3); // [inicio, fin)
        System.out.println("subList(1,3): " + sub);

        // ================================================================
        // C. BÚSQUEDA Y COMPROBACIÓN
        // ================================================================
        System.out.println("\n========== C. Búsqueda ==========");

        List<Integer> nums = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9, 2, 6, 5, 3));

        System.out.println("contains(9)    : " + nums.contains(9));
        System.out.println("containsAll    : " + nums.containsAll(List.of(1, 5)));
        System.out.println("indexOf(5)     : " + nums.indexOf(5));     // primera
        System.out.println("lastIndexOf(5) : " + nums.lastIndexOf(5)); // última

        // Búsqueda binaria (requiere ordenar primero)
        List<Integer> ordenada = new ArrayList<>(nums);
        Collections.sort(ordenada);
        int idx = Collections.binarySearch(ordenada, 5);
        System.out.println("binarySearch(5): índice " + idx + " en " + ordenada);

        // ================================================================
        // D. ORDENAR
        // ================================================================
        System.out.println("\n========== D. Ordenar ==========");

        List<String> nombres = new ArrayList<>(List.of("Carlos", "Ana", "Beatriz", "David", "Eva"));

        // Orden natural (Comparable)
        Collections.sort(nombres);
        System.out.println("Natural asc  : " + nombres);

        // Orden inverso
        nombres.sort(Comparator.reverseOrder());
        System.out.println("Natural desc : " + nombres);

        // Con Comparator personalizado
        nombres.sort(Comparator.comparingInt(String::length));
        System.out.println("Por longitud : " + nombres);

        // Comparator compuesto: primero longitud, luego alfabético
        nombres.sort(Comparator.comparingInt(String::length)
                               .thenComparing(Comparator.naturalOrder()));
        System.out.println("Long+alfab   : " + nombres);

        // Ordenar lista de objetos
        List<Persona2> personas = new ArrayList<>(List.of(
            new Persona2("Carlos", 30),
            new Persona2("Ana", 25),
            new Persona2("Beatriz", 30),
            new Persona2("David", 22)
        ));
        personas.sort(Comparator.comparingInt(Persona2::getEdad)
                                .thenComparing(Persona2::getNombre));
        System.out.println("Personas por edad,nombre: " + personas);

        // ================================================================
        // E. RECORRER
        // ================================================================
        System.out.println("\n========== E. Recorrer ==========");

        List<Integer> datos = List.of(1, 2, 3, 4, 5);

        // for-each
        System.out.print("for-each   : ");
        for (int n : datos) System.out.print(n + " ");
        System.out.println();

        // forEach con lambda
        System.out.print("forEach    : ");
        datos.forEach(n -> System.out.print(n * 2 + " "));
        System.out.println();

        // Iterator
        System.out.print("Iterator   : ");
        Iterator<Integer> it = new ArrayList<>(datos).iterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();

        // ListIterator — permite recorrer en ambas direcciones y modificar
        List<String> editable = new ArrayList<>(List.of("a", "b", "c", "d"));
        ListIterator<String> lit = editable.listIterator();
        while (lit.hasNext()) {
            String val = lit.next();
            lit.set(val.toUpperCase()); // modificar mientras se itera
        }
        System.out.println("ListIterator uppercase: " + editable);

        // Eliminar mientras se itera (sin ConcurrentModificationException)
        List<Integer> conPares = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Iterator<Integer> itElim = conPares.iterator();
        while (itElim.hasNext()) {
            if (itElim.next() % 2 == 0) itElim.remove(); // eliminar pares
        }
        System.out.println("Eliminados pares: " + conPares);

        // removeIf — forma moderna (Java 8+)
        List<Integer> conImpares = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        conImpares.removeIf(n -> n % 2 != 0);
        System.out.println("removeIf impares: " + conImpares);

        // replaceAll — transformar todos los elementos (Java 8+)
        List<String> palabras = new ArrayList<>(List.of("hola", "mundo", "java"));
        palabras.replaceAll(String::toUpperCase);
        System.out.println("replaceAll upper: " + palabras);

        // ================================================================
        // F. OPERACIONES DE COLECCIONES
        // ================================================================
        System.out.println("\n========== F. Collections (utilidades) ==========");

        List<Integer> lista2 = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9, 2, 6));

        System.out.println("min           : " + Collections.min(lista2));
        System.out.println("max           : " + Collections.max(lista2));
        System.out.println("frequency(1)  : " + Collections.frequency(lista2, 1));

        Collections.shuffle(lista2);
        System.out.println("shuffle       : " + lista2);

        Collections.sort(lista2);
        Collections.reverse(lista2);
        System.out.println("sort+reverse  : " + lista2);

        Collections.fill(lista2, 0);
        System.out.println("fill(0)       : " + lista2);

        // nCopies — lista inmutable con n copias del elemento
        List<String> copias = Collections.nCopies(5, "Java");
        System.out.println("nCopies       : " + copias);

        // unmodifiableList — envolver para que no se pueda modificar
        List<String> nomod = Collections.unmodifiableList(new ArrayList<>(List.of("a","b","c")));
        try {
            nomod.add("d");
        } catch (UnsupportedOperationException e) {
            System.out.println("unmodifiable: no se puede modificar");
        }

        // synchronizedList — thread-safe
        List<String> sync = Collections.synchronizedList(new ArrayList<>());
        System.out.println("synchronized list creada");

        // ================================================================
        // G. LINKEDLIST COMO DEQUE (doble cola)
        // ================================================================
        System.out.println("\n========== G. LinkedList como Deque ==========");

        LinkedList<String> deque = new LinkedList<>();
        deque.addFirst("primero");
        deque.addLast("último");
        deque.addFirst("nuevo primero");

        System.out.println("Deque        : " + deque);
        System.out.println("peekFirst()  : " + deque.peekFirst());
        System.out.println("peekLast()   : " + deque.peekLast());
        System.out.println("pollFirst()  : " + deque.pollFirst());
        System.out.println("Tras poll    : " + deque);

        // ================================================================
        // H. CONVERSIONES
        // ================================================================
        System.out.println("\n========== H. Conversiones ==========");

        // Array → List
        String[] array = {"Java", "Python", "Go"};
        List<String> desdeArray1 = Arrays.asList(array);          // tamaño fijo
        List<String> desdeArray2 = new ArrayList<>(Arrays.asList(array)); // mutable

        // List → Array
        String[] hastaArray = desdeArray2.toArray(new String[0]);
        System.out.println("toArray: " + Arrays.toString(hastaArray));

        // List → Stream → List
        List<Integer> numsList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> paresList = numsList.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("pares : " + paresList);

        // List → Set (elimina duplicados)
        List<Integer> conDups = List.of(1, 2, 2, 3, 3, 3);
        Set<Integer>  sinDups = new HashSet<>(conDups);
        System.out.println("a Set : " + sinDups);
    }
}

class Persona2 {
    private String nombre;
    private int    edad;
    public Persona2(String nombre, int edad) { this.nombre = nombre; this.edad = edad; }
    public String getNombre() { return nombre; }
    public int    getEdad()   { return edad; }
    @Override public String toString() { return nombre + "(" + edad + ")"; }
}
