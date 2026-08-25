package api_colecciones;
/**
 * COLLECTIONS API — MAP
 * ======================
 * Map almacena pares clave→valor. Las claves son únicas.
 *
 * IMPLEMENTACIONES:
 *   HashMap        → sin orden, O(1) promedio
 *   LinkedHashMap  → orden de inserción (o acceso), O(1) promedio
 *   TreeMap        → ordenado por clave, O(log n)
 *   Hashtable      → legacy, sincronizado (evitar, usar ConcurrentHashMap)
 *   EnumMap        → optimizado para enums como clave
 *   IdentityHashMap→ usa == en vez de equals para las claves
 *
 * CONDICIÓN PARA SER CLAVE:
 *   La clase debe implementar equals() y hashCode() correctamente
 */
import java.util.*;
import java.util.stream.*;

public class ColeccionesMap {

    public static void main(String[] args) {

        // ================================================================
        // A. CREAR MAPS
        // ================================================================
        System.out.println("========== A. Crear Maps ==========");

        // HashMap
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("uno", 1);
        hashMap.put("dos", 2);
        hashMap.put("tres", 3);
        System.out.println("HashMap     : " + hashMap); // orden no garantizado

        // LinkedHashMap — orden de inserción
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("uno", 1);
        linkedMap.put("dos", 2);
        linkedMap.put("tres", 3);
        System.out.println("LinkedHashMap: " + linkedMap); // {uno=1, dos=2, tres=3}

        // TreeMap — ordenado por clave
        Map<String, Integer> treeMap = new TreeMap<>(hashMap);
        System.out.println("TreeMap     : " + treeMap); // {dos=2, tres=3, uno=1}

        // Map.of — inmutable (Java 9+)
        Map<String, Integer> inmutable = Map.of("a", 1, "b", 2, "c", 3);
        System.out.println("Map.of      : " + inmutable);

        // Map.ofEntries — inmutable con más de 10 pares
        Map<String, Integer> entries = Map.ofEntries(
            Map.entry("Java", 1995),
            Map.entry("Python", 1991),
            Map.entry("Go", 2009),
            Map.entry("TypeScript", 2012)
        );
        System.out.println("Map.ofEntries: " + entries);

        // Map.copyOf (Java 10+)
        Map<String, Integer> copiaMapa = Map.copyOf(hashMap);

        // ================================================================
        // B. OPERACIONES BÁSICAS
        // ================================================================
        System.out.println("\n========== B. Operaciones básicas ==========");

        Map<String, Integer> edades = new HashMap<>();
        edades.put("Ana", 25);
        edades.put("Ben", 30);
        edades.put("Cara", 22);
        edades.put("David", 28);

        // Leer
        System.out.println("get(Ana)       : " + edades.get("Ana"));
        System.out.println("get(Zoe)       : " + edades.get("Zoe")); // null si no existe
        System.out.println("getOrDefault   : " + edades.getOrDefault("Zoe", -1));
        System.out.println("containsKey(Ben): " + edades.containsKey("Ben"));
        System.out.println("containsValue(22): " + edades.containsValue(22));
        System.out.println("size()         : " + edades.size());
        System.out.println("isEmpty()      : " + edades.isEmpty());

        // Añadir / reemplazar
        edades.put("Eva", 35);                           // añadir nueva
        edades.put("Ana", 26);                           // reemplaza el valor existente
        Integer anterior = edades.put("Ben", 31);        // devuelve el valor anterior
        System.out.println("Valor anterior de Ben: " + anterior);

        // putIfAbsent — solo añade si la clave NO existe
        edades.putIfAbsent("Ana", 99);    // Ana ya existe → no cambia
        edades.putIfAbsent("Fiona", 29);  // nueva → la añade
        System.out.println("putIfAbsent Ana: " + edades.get("Ana")); // 26 (no cambió)
        System.out.println("putIfAbsent Fio: " + edades.get("Fiona")); // 29

        // Eliminar
        edades.remove("Fiona");              // elimina por clave
        edades.remove("Eva", 99);            // elimina solo si clave+valor coinciden
        System.out.println("Tras removes: " + edades);

        // ================================================================
        // C. RECORRER UN MAP
        // ================================================================
        System.out.println("\n========== C. Recorrer ==========");

        Map<String, Integer> mapa = new LinkedHashMap<>(Map.of(
            "Java", 1995, "Python", 1991, "Go", 2009
        ));
        // Necesitamos LinkedHashMap para orden predecible en la salida
        Map<String, Integer> mapaOrdenado = new LinkedHashMap<>();
        mapaOrdenado.put("Java", 1995);
        mapaOrdenado.put("Python", 1991);
        mapaOrdenado.put("Go", 2009);

        // entrySet() — iterar sobre pares clave-valor
        System.out.println("entrySet:");
        for (Map.Entry<String, Integer> entry : mapaOrdenado.entrySet()) {
            System.out.println("  " + entry.getKey() + " → " + entry.getValue());
        }

        // keySet() — solo claves
        System.out.print("keySet  : ");
        mapaOrdenado.keySet().forEach(k -> System.out.print(k + " "));
        System.out.println();

        // values() — solo valores
        System.out.print("values  : ");
        mapaOrdenado.values().forEach(v -> System.out.print(v + " "));
        System.out.println();

        // forEach con BiConsumer (Java 8+)
        System.out.println("forEach:");
        mapaOrdenado.forEach((k, v) -> System.out.println("  " + k + " nació en " + v));

        // ================================================================
        // D. MÉTODOS FUNCIONALES (Java 8+)
        // ================================================================
        System.out.println("\n========== D. Métodos funcionales ==========");

        Map<String, Integer> stock = new HashMap<>();
        stock.put("Manzana", 10);
        stock.put("Pera", 5);
        stock.put("Naranja", 0);

        // compute — calcula el nuevo valor a partir de clave y valor actual
        stock.compute("Manzana", (k, v) -> v == null ? 1 : v + 5);
        System.out.println("compute Manzana +5: " + stock.get("Manzana")); // 15

        // computeIfAbsent — solo si la clave NO existe o su valor es null
        stock.computeIfAbsent("Kiwi", k -> 20);
        System.out.println("computeIfAbsent Kiwi: " + stock.get("Kiwi")); // 20
        stock.computeIfAbsent("Manzana", k -> 999); // ya existe → no cambia
        System.out.println("computeIfAbsent Manzana: " + stock.get("Manzana")); // 15

        // computeIfPresent — solo si la clave EXISTE
        stock.computeIfPresent("Pera", (k, v) -> v * 2);
        System.out.println("computeIfPresent Pera x2: " + stock.get("Pera")); // 10
        stock.computeIfPresent("Melón", (k, v) -> v * 2); // no existe → no hace nada

        // merge — combina el valor existente con el nuevo
        stock.merge("Naranja", 8, Integer::sum);  // 0 + 8 = 8
        stock.merge("Piña", 12, Integer::sum);    // nueva clave → 12
        stock.merge("Manzana", 5, Integer::sum);  // 15 + 5 = 20
        System.out.println("Tras merge: " + stock);

        // replaceAll — transforma todos los valores
        Map<String, String> textos = new HashMap<>(Map.of("a", "hola", "b", "mundo"));
        textos.replaceAll((k, v) -> v.toUpperCase());
        System.out.println("replaceAll upper: " + textos);

        // replace — reemplaza el valor de una clave
        stock.replace("Kiwi", 25);
        stock.replace("Kiwi", 25, 30); // solo reemplaza si valor actual coincide
        System.out.println("replace Kiwi: " + stock.get("Kiwi")); // 30

        // ================================================================
        // E. TREEMAP — OPERACIONES ORDENADAS
        // ================================================================
        System.out.println("\n========== E. TreeMap — operaciones ordenadas ==========");

        TreeMap<String, Integer> tm = new TreeMap<>();
        tm.put("banana", 3);
        tm.put("apple", 5);
        tm.put("cherry", 2);
        tm.put("date", 7);
        tm.put("elderberry", 1);

        System.out.println("TreeMap      : " + tm);
        System.out.println("firstKey()   : " + tm.firstKey());
        System.out.println("lastKey()    : " + tm.lastKey());
        System.out.println("floorKey(b)  : " + tm.floorKey("b"));   // ≤ "b"
        System.out.println("ceilingKey(b): " + tm.ceilingKey("b")); // ≥ "b"
        System.out.println("lowerKey(c)  : " + tm.lowerKey("cherry")); // < cherry
        System.out.println("higherKey(c) : " + tm.higherKey("cherry")); // > cherry

        // SubMaps
        System.out.println("headMap(<cherry): " + tm.headMap("cherry"));
        System.out.println("tailMap(≥cherry): " + tm.tailMap("cherry"));
        System.out.println("subMap(b..d)    : " + tm.subMap("banana", "date"));

        // pollFirstEntry / pollLastEntry
        System.out.println("pollFirst(): " + tm.pollFirstEntry());
        System.out.println("Tras poll   : " + tm);

        // ================================================================
        // F. CASOS DE USO PRÁCTICOS
        // ================================================================
        System.out.println("\n========== F. Casos de uso ==========");

        // 1. Contar frecuencia de palabras
        String texto = "java es genial java python es bueno java python";
        Map<String, Long> frecuencia = Arrays.stream(texto.split(" "))
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        new TreeMap<>(frecuencia).forEach((k, v) -> System.out.println("  " + k + ": " + v));

        // 2. Agrupar por longitud
        List<String> palabras = List.of("java", "python", "go", "rust", "c", "kotlin");
        Map<Integer, List<String>> porLongitud = palabras.stream()
                .collect(Collectors.groupingBy(String::length));
        new TreeMap<>(porLongitud).forEach((len, lista) ->
                System.out.println("  Longitud " + len + ": " + lista));

        // 3. Caché con LinkedHashMap LRU
        Map<Integer, String> lruCache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override protected boolean removeEldestEntry(Map.Entry<Integer,String> e) {
                return size() > 3; // máximo 3 entradas
            }
        };
        lruCache.put(1, "uno");
        lruCache.put(2, "dos");
        lruCache.put(3, "tres");
        lruCache.get(1);         // acceso → mueve "uno" al final
        lruCache.put(4, "cuatro"); // elimina el LRU (2 "dos")
        System.out.println("LRU cache: " + lruCache);

        // 4. Invertir un mapa (valor → clave)
        Map<String, Integer> original = Map.of("Java", 1, "Python", 2, "Go", 3);
        Map<Integer, String> invertido = new HashMap<>();
        original.forEach((k, v) -> invertido.put(v, k));
        System.out.println("Invertido: " + invertido);

        // 5. Mapa de mapas (mapa anidado)
        Map<String, Map<String, Integer>> departamentos = new HashMap<>();
        Map<String, Integer> backend  = new HashMap<>(); backend.put("Java", 5); backend.put("Python", 3);
        Map<String, Integer> frontend = new HashMap<>(); frontend.put("React", 4); frontend.put("Vue", 2);
        departamentos.put("Backend", backend);
        departamentos.put("Frontend", frontend);
        departamentos.forEach((dept, techs) ->
                System.out.println("  " + dept + ": " + techs));

        // 6. Ordenar mapa por valor
        Map<String, Integer> puntos = new HashMap<>(Map.of("Ana", 85, "Ben", 92, "Cara", 78, "David", 95));
        System.out.println("Por puntos desc:");
        puntos.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue()));
    }
}
