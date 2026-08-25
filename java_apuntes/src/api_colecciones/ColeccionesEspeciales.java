package api_colecciones;
/**
 * COLLECTIONS API — QUEUE, DEQUE, STACK Y ESPECIALES
 * =====================================================
 *
 * QUEUE (cola FIFO):
 *   LinkedList       → cola doblemente enlazada
 *   PriorityQueue    → cola de prioridad (min-heap por defecto)
 *   ArrayDeque       → cola circular con array (más eficiente)
 *
 * DEQUE (doble cola — puede usarse como pila o cola):
 *   ArrayDeque       → implementación recomendada
 *   LinkedList       → también implementa Deque
 *
 * COLECCIONES ESPECIALES:
 *   Collections.unmodifiable*  → envolturas inmutables
 *   Collections.synchronized*  → envolturas thread-safe
 *   java.util.concurrent.*     → concurrentes de alto rendimiento
 */
import java.util.*;
import java.util.concurrent.*;

public class ColeccionesEspeciales {

    public static void main(String[] args) throws InterruptedException {

        // ================================================================
        // A. QUEUE — cola FIFO
        // ================================================================
        System.out.println("========== A. Queue (FIFO) ==========");

        Queue<String> cola = new LinkedList<>();

        // offer/add — añadir al final (offer no lanza excepción, add sí)
        cola.offer("primero");
        cola.offer("segundo");
        cola.offer("tercero");
        System.out.println("Cola         : " + cola);

        // peek/element — ver el frente SIN quitar
        System.out.println("peek()       : " + cola.peek());   // null si vacía
        System.out.println("element()    : " + cola.element()); // excepción si vacía

        // poll/remove — quitar del frente
        System.out.println("poll()       : " + cola.poll());   // null si vacía
        System.out.println("remove()     : " + cola.remove()); // excepción si vacía
        System.out.println("Cola tras 2 polls: " + cola);

        System.out.println("size()       : " + cola.size());
        System.out.println("isEmpty()    : " + cola.isEmpty());

        // ================================================================
        // B. PRIORITYQUEUE — cola de prioridad
        // ================================================================
        System.out.println("\n========== B. PriorityQueue ==========");

        // Min-heap por defecto (menor valor = mayor prioridad)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5);
        pq.offer(1);
        pq.offer(8);
        pq.offer(3);
        pq.offer(7);

        System.out.print("Extracción (min primero): ");
        while (!pq.isEmpty()) System.out.print(pq.poll() + " "); // 1 3 5 7 8
        System.out.println();

        // Max-heap con Comparator.reverseOrder()
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(List.of(5, 1, 8, 3, 7));
        System.out.print("Max-heap      : ");
        while (!maxHeap.isEmpty()) System.out.print(maxHeap.poll() + " "); // 8 7 5 3 1
        System.out.println();

        // PriorityQueue con objetos y Comparator
        PriorityQueue<Tarea> tareas = new PriorityQueue<>(
            Comparator.comparingInt(Tarea::getPrioridad)
        );
        tareas.offer(new Tarea("Actualizar docs", 3));
        tareas.offer(new Tarea("Corregir bug crítico", 1));
        tareas.offer(new Tarea("Refactor módulo", 2));
        tareas.offer(new Tarea("Reunión", 1));

        System.out.println("Tareas por prioridad:");
        while (!tareas.isEmpty()) System.out.println("  " + tareas.poll());

        // ================================================================
        // C. DEQUE — doble cola (pila + cola)
        // ================================================================
        System.out.println("\n========== C. Deque (ArrayDeque) ==========");

        Deque<String> deque = new ArrayDeque<>();

        // Añadir por ambos extremos
        deque.addFirst("primero");
        deque.addLast("último");
        deque.offerFirst("nuevo primero");
        deque.offerLast("nuevo último");
        System.out.println("Deque        : " + deque);

        // Ver sin quitar
        System.out.println("peekFirst()  : " + deque.peekFirst());
        System.out.println("peekLast()   : " + deque.peekLast());

        // Quitar de ambos extremos
        System.out.println("pollFirst()  : " + deque.pollFirst());
        System.out.println("pollLast()   : " + deque.pollLast());
        System.out.println("Tras polls   : " + deque);

        // ================================================================
        // D. ARRAYDEQUE COMO PILA (más eficiente que Stack)
        // ================================================================
        System.out.println("\n========== D. ArrayDeque como Pila (LIFO) ==========");

        Deque<String> pila = new ArrayDeque<>();
        pila.push("primero");  // addFirst()
        pila.push("segundo");
        pila.push("tercero");
        System.out.println("Pila         : " + pila);
        System.out.println("peek()       : " + pila.peek());  // peekFirst()
        System.out.println("pop()        : " + pila.pop());   // removeFirst()
        System.out.println("Tras pop     : " + pila);

        // Caso de uso: evaluar expresión con paréntesis
        String[] expresiones = {"(()())", "(()", "({[]})", "([)]"};
        for (String expr : expresiones) {
            System.out.println(expr + " → " + verificarParentesis(expr));
        }

        // Caso de uso: historial de navegación
        System.out.println("\n--- Historial de navegación ---");
        Deque<String> historial = new ArrayDeque<>();
        navegar(historial, "google.com");
        navegar(historial, "github.com");
        navegar(historial, "stackoverflow.com");
        retroceder(historial);
        retroceder(historial);

        // ================================================================
        // E. COLLECTIONS — UTILIDADES
        // ================================================================
        System.out.println("\n========== E. Collections (utilidades) ==========");

        List<Integer> lista = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9, 2, 6));

        // Estadísticas
        System.out.println("min          : " + Collections.min(lista));
        System.out.println("max          : " + Collections.max(lista));
        System.out.println("frequency(1) : " + Collections.frequency(lista, 1));

        // Modificaciones
        Collections.sort(lista);
        System.out.println("sort         : " + lista);
        Collections.reverse(lista);
        System.out.println("reverse      : " + lista);
        Collections.shuffle(lista, new Random(42));
        System.out.println("shuffle      : " + lista);
        Collections.rotate(lista, 2); // rota a la derecha 2 posiciones
        System.out.println("rotate(2)    : " + lista);
        Collections.swap(lista, 0, lista.size()-1);
        System.out.println("swap(0,last) : " + lista);

        // Relleno
        List<String> relleno = new ArrayList<>(Collections.nCopies(5, "X"));
        Collections.fill(relleno, "Y");
        System.out.println("fill(Y)      : " + relleno);

        // disjoint — true si no tienen elementos en común
        System.out.println("disjoint {1,2} {3,4}: " +
                Collections.disjoint(Set.of(1,2), Set.of(3,4)));
        System.out.println("disjoint {1,2} {2,3}: " +
                Collections.disjoint(Set.of(1,2), Set.of(2,3)));

        // ================================================================
        // F. COLECCIONES INMUTABLES
        // ================================================================
        System.out.println("\n========== F. Inmutables ==========");

        // Java 9+ (preferidas)
        List<String>  listaIn  = List.of("a", "b", "c");
        Set<String>   setIn    = Set.of("x", "y", "z");
        Map<String,Integer> mapIn = Map.of("uno", 1, "dos", 2);

        // Legacy (Collections.unmodifiable*)
        List<String> wrappedList = Collections.unmodifiableList(new ArrayList<>(listaIn));
        Set<String>  wrappedSet  = Collections.unmodifiableSet(new HashSet<>(setIn));
        Map<String,Integer> wrappedMap = Collections.unmodifiableMap(new HashMap<>(mapIn));

        try { listaIn.add("d"); } catch (UnsupportedOperationException e) {
            System.out.println("List.of inmutable: " + e.getClass().getSimpleName());
        }
        try { wrappedList.add("d"); } catch (UnsupportedOperationException e) {
            System.out.println("unmodifiableList : " + e.getClass().getSimpleName());
        }

        // ================================================================
        // G. COLECCIONES THREAD-SAFE
        // ================================================================
        System.out.println("\n========== G. Thread-safe ==========");

        // synchronized wrappers (bloqueo grueso — toda la colección)
        List<String>      syncList = Collections.synchronizedList(new ArrayList<>());
        Map<String,Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

        // ConcurrentHashMap — más eficiente (bloqueo fino por segmento)
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("a", 1);
        concurrentMap.put("b", 2);

        // CopyOnWriteArrayList — optimizado para muchas lecturas, pocas escrituras
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add("elemento1");
        cowList.add("elemento2");
        System.out.println("CopyOnWrite  : " + cowList);

        // BlockingQueue — para productor-consumidor
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);
        blockingQueue.offer("tarea1");
        blockingQueue.offer("tarea2");
        System.out.println("BlockingQueue: " + blockingQueue);
        System.out.println("poll()       : " + blockingQueue.poll());

        // ================================================================
        // H. COMPARATIVA DE COLECCIONES
        // ================================================================
        System.out.println("\n========== H. Guía de selección ==========");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "Colección", "Orden", "Duplicados", "Caso de uso");
        System.out.println("-".repeat(66));
        System.out.printf("%-20s %-12s %-12s %-20s%n", "ArrayList",       "Inserción", "Sí",  "Lista general, acceso por índice");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "LinkedList",      "Inserción", "Sí",  "Muchas inserciones/borrados");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "HashSet",         "Ninguno",   "No",  "Unicidad, búsqueda O(1)");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "LinkedHashSet",   "Inserción", "No",  "Unicidad + orden inserción");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "TreeSet",         "Natural",   "No",  "Unicidad ordenada");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "HashMap",         "Ninguno",   "No",  "Mapa general, O(1)");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "LinkedHashMap",   "Inserción", "No",  "Mapa + orden inserción/LRU");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "TreeMap",         "Natural",   "No",  "Mapa ordenado por clave");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "ArrayDeque",      "LIFO/FIFO", "Sí",  "Pila o cola eficiente");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "PriorityQueue",   "Prioridad", "Sí",  "Elemento con mayor prioridad");
        System.out.printf("%-20s %-12s %-12s %-20s%n", "ConcurrentHashMap","Ninguno",  "No",  "Mapa thread-safe eficiente");
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static String verificarParentesis(String expr) {
        Deque<Character> pila = new ArrayDeque<>();
        for (char c : expr.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                pila.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (pila.isEmpty()) return "No balanceada";
                char tope = pila.pop();
                if ((c == ')' && tope != '(') ||
                    (c == '}' && tope != '{') ||
                    (c == ']' && tope != '[')) return "No balanceada";
            }
        }
        return pila.isEmpty() ? "Balanceada ✓" : "No balanceada";
    }

    static void navegar(Deque<String> historial, String url) {
        historial.push(url);
        System.out.println("Navegando a: " + url);
    }

    static void retroceder(Deque<String> historial) {
        if (historial.size() > 1) {
            String actual = historial.pop();
            System.out.println("Volviendo de " + actual + " → " + historial.peek());
        }
    }
}

class Tarea {
    private String nombre;
    private int    prioridad; // 1 = alta, 5 = baja

    public Tarea(String nombre, int prioridad) {
        this.nombre    = nombre;
        this.prioridad = prioridad;
    }

    public int    getPrioridad() { return prioridad; }
    public String getNombre()    { return nombre; }

    @Override
    public String toString() {
        return "[P" + prioridad + "] " + nombre;
    }
}
