package api_colecciones;
/**
 * COLLECTIONS API — SET
 * ======================
 * Set es una colección que NO permite duplicados.
 *
 * IMPLEMENTACIONES:
 *   HashSet        → sin orden garantizado, O(1) promedio
 *   LinkedHashSet  → mantiene orden de inserción, O(1) promedio
 *   TreeSet        → ordenado naturalmente o por Comparator, O(log n)
 *   EnumSet        → optimizado para enums, muy eficiente
 *
 * CUÁNDO USAR:
 *   HashSet       → máxima velocidad, orden no importa
 *   LinkedHashSet → velocidad + orden de inserción
 *   TreeSet       → necesitas los elementos ordenados
 *   EnumSet       → trabajar con enums
 */
import java.util.*;
import java.util.stream.Collectors;

public class ColeccionesSet {

    public static void main(String[] args) {

        // ================================================================
        // A. CREAR SETS
        // ================================================================
        System.out.println("========== A. Crear Sets ==========");

        // HashSet — sin orden
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Java");
        hashSet.add("Python");
        hashSet.add("Go");
        hashSet.add("Java"); // duplicado ignorado
        System.out.println("HashSet     : " + hashSet); // orden no garantizado

        // LinkedHashSet — mantiene orden de inserción
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("Java");
        linkedSet.add("Python");
        linkedSet.add("Go");
        linkedSet.add("Java"); // duplicado ignorado
        System.out.println("LinkedHashSet: " + linkedSet); // [Java, Python, Go]

        // TreeSet — ordenado naturalmente
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Java");
        treeSet.add("Python");
        treeSet.add("Go");
        treeSet.add("C++");
        System.out.println("TreeSet     : " + treeSet); // [C++, Go, Java, Python]

        // Set.of — inmutable (Java 9+)
        Set<String> inmutable = Set.of("a", "b", "c");
        System.out.println("Set.of      : " + inmutable);

        // Set.copyOf — copia inmutable (Java 10+)
        Set<String> copiaSe = Set.copyOf(hashSet);
        System.out.println("Set.copyOf  : " + copiaSe);

        // ================================================================
        // B. OPERACIONES BÁSICAS
        // ================================================================
        System.out.println("\n========== B. Operaciones básicas ==========");

        Set<Integer> set = new HashSet<>(Set.of(1, 2, 3, 4, 5));

        System.out.println("size()      : " + set.size());
        System.out.println("isEmpty()   : " + set.isEmpty());
        System.out.println("contains(3) : " + set.contains(3));
        System.out.println("contains(9) : " + set.contains(9));

        set.add(6);
        boolean añadido = set.add(3); // ya existe → false
        System.out.println("add(6)      : " + set);
        System.out.println("add(3) ok?  : " + añadido); // false

        set.remove(1);
        System.out.println("remove(1)   : " + set);

        boolean contieneAll = set.containsAll(Set.of(2, 3, 4));
        System.out.println("containsAll : " + contieneAll);

        // ================================================================
        // C. OPERACIONES DE CONJUNTOS
        // ================================================================
        System.out.println("\n========== C. Operaciones de conjuntos ==========");

        Set<Integer> A = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> B = new HashSet<>(Set.of(3, 4, 5, 6, 7));

        // UNIÓN: A ∪ B
        Set<Integer> union = new HashSet<>(A);
        union.addAll(B);
        System.out.println("A           : " + new TreeSet<>(A));
        System.out.println("B           : " + new TreeSet<>(B));
        System.out.println("A ∪ B       : " + new TreeSet<>(union));

        // INTERSECCIÓN: A ∩ B
        Set<Integer> interseccion = new HashSet<>(A);
        interseccion.retainAll(B);
        System.out.println("A ∩ B       : " + new TreeSet<>(interseccion));

        // DIFERENCIA: A - B
        Set<Integer> diferencia = new HashSet<>(A);
        diferencia.removeAll(B);
        System.out.println("A - B       : " + new TreeSet<>(diferencia));

        // DIFERENCIA SIMÉTRICA: (A ∪ B) - (A ∩ B)
        Set<Integer> simetrica = new HashSet<>(union);
        simetrica.removeAll(interseccion);
        System.out.println("A △ B       : " + new TreeSet<>(simetrica));

        // ¿Es subconjunto? A.containsAll(B) → ¿B ⊆ A?
        Set<Integer> sub = Set.of(3, 4, 5);
        System.out.println("{3,4,5} ⊆ A : " + A.containsAll(sub));
        System.out.println("A ⊆ {3,4,5} : " + sub.containsAll(A));

        // ================================================================
        // D. TREESET — OPERACIONES ORDENADAS
        // ================================================================
        System.out.println("\n========== D. TreeSet — operaciones ordenadas ==========");

        TreeSet<Integer> tree = new TreeSet<>(List.of(5, 2, 8, 1, 7, 3, 9, 4, 6));
        System.out.println("TreeSet     : " + tree);
        System.out.println("first()     : " + tree.first());
        System.out.println("last()      : " + tree.last());
        System.out.println("floor(5)    : " + tree.floor(5));    // ≤ 5 más cercano
        System.out.println("ceiling(5)  : " + tree.ceiling(5));  // ≥ 5 más cercano
        System.out.println("lower(5)    : " + tree.lower(5));    // < 5 más cercano
        System.out.println("higher(5)   : " + tree.higher(5));   // > 5 más cercano

        // Subconjuntos
        System.out.println("headSet(<5) : " + tree.headSet(5));      // < 5
        System.out.println("tailSet(≥5) : " + tree.tailSet(5));      // ≥ 5
        System.out.println("subSet(3,7) : " + tree.subSet(3, 7));    // [3,7)
        System.out.println("subSet(3,7] : " + tree.subSet(3, true, 7, true)); // [3,7]

        // Orden inverso
        System.out.println("descendingSet: " + tree.descendingSet());

        // pollFirst/pollLast — extrae y elimina
        TreeSet<Integer> poll = new TreeSet<>(tree);
        System.out.println("pollFirst() : " + poll.pollFirst() + " → " + poll);
        System.out.println("pollLast()  : " + poll.pollLast()  + " → " + poll);

        // ================================================================
        // E. TREESET CON COMPARATOR PERSONALIZADO
        // ================================================================
        System.out.println("\n========== E. TreeSet con Comparator ==========");

        // Ordenar strings por longitud, luego alfabético
        TreeSet<String> porLongitud = new TreeSet<>(
            Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder())
        );
        porLongitud.addAll(List.of("Java", "Python", "Go", "C++", "Rust", "TypeScript", "C"));
        System.out.println("Por longitud: " + porLongitud);

        // Orden inverso de strings
        TreeSet<String> inverso = new TreeSet<>(Comparator.reverseOrder());
        inverso.addAll(List.of("Java", "Python", "Go"));
        System.out.println("Inverso     : " + inverso);

        // ================================================================
        // F. ENUMSET
        // ================================================================
        System.out.println("\n========== F. EnumSet ==========");

        // EnumSet.of — crea con elementos específicos
        EnumSet<DiaSemana2> laborables = EnumSet.of(
            DiaSemana2.LUNES, DiaSemana2.MARTES, DiaSemana2.MIERCOLES,
            DiaSemana2.JUEVES, DiaSemana2.VIERNES
        );
        System.out.println("Laborables  : " + laborables);

        // EnumSet.complementOf — complemento
        EnumSet<DiaSemana2> finDeSemana = EnumSet.complementOf(laborables);
        System.out.println("Fin semana  : " + finDeSemana);

        // EnumSet.allOf — todos los valores
        EnumSet<DiaSemana2> todos = EnumSet.allOf(DiaSemana2.class);
        System.out.println("Todos       : " + todos);

        // EnumSet.range — rango
        EnumSet<DiaSemana2> mediosSemana = EnumSet.range(DiaSemana2.MARTES, DiaSemana2.JUEVES);
        System.out.println("Mar-Jue     : " + mediosSemana);

        // ================================================================
        // G. ELIMINAR DUPLICADOS DE UNA LISTA
        // ================================================================
        System.out.println("\n========== G. Usos prácticos ==========");

        // Eliminar duplicados manteniendo orden de inserción
        List<String> conDups = List.of("Java", "Python", "Java", "Go", "Python", "Rust");
        List<String> sinDups = new ArrayList<>(new LinkedHashSet<>(conDups));
        System.out.println("Con dups    : " + conDups);
        System.out.println("Sin dups    : " + sinDups);

        // Encontrar elementos únicos (solo en una lista)
        List<String> lista1 = List.of("a", "b", "c", "d");
        List<String> lista2 = List.of("c", "d", "e", "f");
        Set<String> soloEn1 = new HashSet<>(lista1);
        soloEn1.removeAll(new HashSet<>(lista2));
        System.out.println("Solo en lista1: " + soloEn1);

        // Verificar si dos listas tienen los mismos elementos (sin importar orden)
        List<Integer> l1 = List.of(1, 2, 3, 4);
        List<Integer> l2 = List.of(4, 3, 2, 1);
        boolean mismoContenido = new HashSet<>(l1).equals(new HashSet<>(l2));
        System.out.println("Mismo contenido: " + mismoContenido);

        // Contar elementos únicos
        List<Integer> nums = List.of(1, 2, 2, 3, 3, 3, 4);
        long unicos = nums.stream().distinct().count();
        System.out.println("Únicos en " + nums + ": " + unicos);

        // ================================================================
        // H. RENDIMIENTO COMPARADO
        // ================================================================
        System.out.println("\n========== H. Rendimiento (1M elementos) ==========");

        int N = 1_000_000;
        Random rnd = new Random(42);

        // HashSet
        Set<Integer> hs = new HashSet<>();
        long t1 = System.nanoTime();
        for (int i = 0; i < N; i++) hs.add(rnd.nextInt(N));
        long t2 = System.nanoTime();
        System.out.printf("HashSet add 1M    : %5.1f ms%n", (t2-t1)/1e6);

        // TreeSet
        Set<Integer> ts = new TreeSet<>();
        long t3 = System.nanoTime();
        for (int i = 0; i < N/10; i++) ts.add(rnd.nextInt(N)); // 100k para que sea razonable
        long t4 = System.nanoTime();
        System.out.printf("TreeSet add 100k  : %5.1f ms%n", (t4-t3)/1e6);

        // Contains
        long t5 = System.nanoTime();
        boolean c1 = hs.contains(500000);
        long t6 = System.nanoTime();
        System.out.printf("HashSet contains  : %5.0f ns%n", (double)(t6-t5));

        long t7 = System.nanoTime();
        boolean c2 = ts.contains(500000);
        long t8 = System.nanoTime();
        System.out.printf("TreeSet contains  : %5.0f ns%n", (double)(t8-t7));
    }
}

enum DiaSemana2 { LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO }
