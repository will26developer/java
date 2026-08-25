package api_colecciones;

/**
 * STREAM API Y OPERACIONES FUNCIONALES (Java 8+)
 * =================================================
 * Los Streams permiten procesar colecciones de forma declarativa
 * (qué hacer, no cómo hacerlo).
 *
 * CARACTERÍSTICAS:
 *   - No almacenan datos (son vistas de la fuente)
 *   - Son lazy: las operaciones intermedias no se ejecutan hasta
 *     que se llama a una operación terminal
 *   - No son reutilizables: se consumen una vez
 *   - No modifican la fuente
 *
 * OPERACIONES INTERMEDIAS (devuelven Stream, lazy):
 *   filter, map, flatMap, distinct, sorted, peek, limit, skip, mapToInt...
 *
 * OPERACIONES TERMINALES (consumen el stream):
 *   collect, forEach, count, findFirst, findAny, anyMatch, allMatch,
 *   noneMatch, min, max, sum, average, reduce, toArray
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamAPI {

    public static void main(String[] args) {

        // ================================================================
        // A. CREAR STREAMS
        // ================================================================
        System.out.println("========== A. Crear Streams ==========");

        // Desde colección
        List<String> lista = List.of("Java", "Python", "Go", "Rust");
        Stream<String> s1 = lista.stream();

        // Desde array
        Stream<String> s2 = Arrays.stream(new String[] { "a", "b", "c" });

        // Stream.of
        Stream<Integer> s3 = Stream.of(1, 2, 3, 4, 5);

        // Stream vacío
        Stream<String> vacio = Stream.empty();

        // Stream infinito — generate (debe limitarse con limit)
        Stream<Double> randoms = Stream.generate(Math::random).limit(5);
        System.out.print("Randoms   : ");
        randoms.forEach(r -> System.out.printf("%.2f ", r));
        System.out.println();

        // Stream infinito — iterate
        Stream<Integer> pares = Stream.iterate(0, n -> n + 2).limit(8);
        System.out.print("Pares     : ");
        pares.forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Stream.iterate con predicado (Java 9+)
        Stream<Integer> hasta20 = Stream.iterate(0, n -> n < 20, n -> n + 3);
        System.out.print("0,3,6...  : ");
        hasta20.forEach(n -> System.out.print(n + " "));
        System.out.println();

        // IntStream, LongStream, DoubleStream (primitivos, más eficientes)
        IntStream.range(1, 6).forEach(n -> System.out.print(n + " ")); // 1 2 3 4 5
        System.out.println();
        IntStream.rangeClosed(1, 5).forEach(n -> System.out.print(n + " ")); // 1 2 3 4 5
        System.out.println();

        // ================================================================
        // B. OPERACIONES INTERMEDIAS
        // ================================================================
        System.out.println("\n========== B. Operaciones intermedias ==========");

        List<String> lenguajes = List.of("Java", "Python", "Go", "JavaScript", "TypeScript", "Rust", "Go");

        // filter — filtrar elementos
        System.out.println("filter(>3chars): " +
                lenguajes.stream().filter(s -> s.length() > 3).collect(Collectors.toList()));

        // map — transformar cada elemento
        System.out.println("map(upper)     : " +
                lenguajes.stream().map(String::toUpperCase).collect(Collectors.toList()));

        // mapToInt — convertir a IntStream
        System.out.println("mapToInt(len)  : " +
                Arrays.toString(lenguajes.stream().mapToInt(String::length).toArray()));

        // distinct — eliminar duplicados
        System.out.println("distinct       : " +
                lenguajes.stream().distinct().collect(Collectors.toList()));

        // sorted — ordenar
        System.out.println("sorted natural : " +
                lenguajes.stream().distinct().sorted().collect(Collectors.toList()));

        System.out.println("sorted por len : " +
                lenguajes.stream().distinct()
                        .sorted(Comparator.comparingInt(String::length))
                        .collect(Collectors.toList()));

        // limit y skip — paginar
        System.out.println("limit(3)       : " +
                lenguajes.stream().limit(3).collect(Collectors.toList()));
        System.out.println("skip(3)        : " +
                lenguajes.stream().skip(3).collect(Collectors.toList()));
        System.out.println("skip(2)limit(3): " +
                lenguajes.stream().skip(2).limit(3).collect(Collectors.toList()));

        // peek — para debugging (no modifica, solo observa)
        System.out.print("peek debug     : ");
        lenguajes.stream()
                .filter(s -> s.length() > 3)
                .peek(s -> System.out.print("[" + s + "] "))
                .count();
        System.out.println();

        // flatMap — aplanar streams anidados
        List<List<Integer>> anidada = List.of(List.of(1, 2, 3), List.of(4, 5), List.of(6, 7, 8, 9));
        List<Integer> plana = anidada.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("flatMap        : " + plana);

        // flatMap con strings
        List<String> frases = List.of("hola mundo", "java es genial");
        List<String> palabras = frases.stream()
                .flatMap(f -> Arrays.stream(f.split(" ")))
                .collect(Collectors.toList());
        System.out.println("flatMap palabras: " + palabras);

        // ================================================================
        // C. OPERACIONES TERMINALES — MATCH Y FIND
        // ================================================================
        System.out.println("\n========== C. Match y Find ==========");

        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("anyMatch(>8)   : " + nums.stream().anyMatch(n -> n > 8));
        System.out.println("allMatch(>0)   : " + nums.stream().allMatch(n -> n > 0));
        System.out.println("noneMatch(<0)  : " + nums.stream().noneMatch(n -> n < 0));
        System.out.println("findFirst(>5)  : " + nums.stream().filter(n -> n > 5).findFirst());
        System.out.println("findAny(par)   : " + nums.stream().filter(n -> n % 2 == 0).findAny());

        // ================================================================
        // D. OPERACIONES TERMINALES — REDUCCIÓN
        // ================================================================
        System.out.println("\n========== D. Reducción ==========");

        // count, sum, min, max, average en IntStream
        IntStream intS = nums.stream().mapToInt(Integer::intValue);
        // No se puede reusar; usar de nuevo
        System.out.println("count          : " + nums.stream().count());
        System.out.println("sum            : " + nums.stream().mapToInt(n -> n).sum());
        System.out.println("average        : " + nums.stream().mapToInt(n -> n).average());
        System.out.println("min            : " + nums.stream().mapToInt(n -> n).min());
        System.out.println("max            : " + nums.stream().mapToInt(n -> n).max());

        // IntSummaryStatistics
        IntSummaryStatistics stats = nums.stream().mapToInt(n -> n).summaryStatistics();
        System.out.println("stats          : " + stats);

        // reduce — combinar elementos
        Optional<Integer> producto = nums.stream().reduce((a, b) -> a * b);
        System.out.println("reduce(*)      : " + producto);

        int sumaConIdentidad = nums.stream().reduce(0, Integer::sum);
        System.out.println("reduce(+,0)    : " + sumaConIdentidad);

        // ================================================================
        // E. COLLECTORS — RECOPILAR RESULTADOS
        // ================================================================
        System.out.println("\n========== E. Collectors ==========");

        List<String> lang = List.of("Java", "Python", "Go", "Rust", "C++", "Java");

        // toList, toSet, toUnmodifiableList
        List<String> toList = lang.stream().collect(Collectors.toList());
        Set<String> toSet = lang.stream().collect(Collectors.toSet());
        System.out.println("toList         : " + toList);
        System.out.println("toSet (únicos) : " + new TreeSet<>(toSet));

        // joining — concatenar strings
        String join1 = lang.stream().collect(Collectors.joining());
        String join2 = lang.stream().distinct().collect(Collectors.joining(", "));
        String join3 = lang.stream().distinct().collect(Collectors.joining(", ", "[", "]"));
        System.out.println("joining()      : " + join1);
        System.out.println("joining(', ')  : " + join2);
        System.out.println("joining([])    : " + join3);

        // counting
        System.out.println("counting       : " + lang.stream().collect(Collectors.counting()));

        // groupingBy — agrupar por criterio
        Map<Integer, List<String>> porLongitud = lang.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("groupingBy len : " + new TreeMap<>(porLongitud));

        // groupingBy con downstream collector
        Map<Integer, Long> countByLen = lang.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("groupBy+count  : " + new TreeMap<>(countByLen));

        Map<Integer, String> joinByLen = lang.stream()
                .collect(Collectors.groupingBy(String::length,
                        Collectors.joining(",")));
        System.out.println("groupBy+join   : " + new TreeMap<>(joinByLen));

        // partitioningBy — dividir en true/false
        Map<Boolean, List<String>> particion = lang.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 3));
        System.out.println("partition >3   : " + particion);

        // toMap
        Map<String, Integer> langLen = lang.stream().distinct()
                .collect(Collectors.toMap(
                        s -> s, // clave
                        String::length, // valor
                        (v1, v2) -> v1 // resolver duplicados
                ));
        System.out.println("toMap(s,len)   : " + new TreeMap<>(langLen));

        // summarizingInt
        IntSummaryStatistics lenStats = lang.stream()
                .collect(Collectors.summarizingInt(String::length));
        System.out.printf("summarizing    : avg=%.1f, min=%d, max=%d%n",
                lenStats.getAverage(), lenStats.getMin(), lenStats.getMax());

        // ================================================================
        // F. STREAMS CON OBJETOS — CASO REAL
        // ================================================================
        System.out.println("\n========== F. Streams con objetos ==========");

        List<Empleado3> empleados = List.of(
                new Empleado3("William", "Backend", 2500.0),
                new Empleado3("Ana", "Frontend", 2200.0),
                new Empleado3("Carlos", "Backend", 2800.0),
                new Empleado3("Beatriz", "DevOps", 3000.0),
                new Empleado3("David", "Frontend", 2100.0),
                new Empleado3("Eva", "Backend", 2600.0));

        // Empleados de Backend con salario > 2500, ordenados por salario desc
        System.out.println("Backend >2500:");
        empleados.stream()
                .filter(e -> e.getDept().equals("Backend"))
                .filter(e -> e.getSalario() > 2500)
                .sorted(Comparator.comparingDouble(Empleado3::getSalario).reversed())
                .forEach(e -> System.out.println("  " + e));

        // Salario promedio por departamento
        System.out.println("Media por dept:");
        empleados.stream()
                .collect(Collectors.groupingBy(Empleado3::getDept,
                        Collectors.averagingDouble(Empleado3::getSalario)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.printf("  %-10s %.2f€%n", e.getKey(), e.getValue()));

        // Nómina total
        double nomina = empleados.stream()
                .mapToDouble(Empleado3::getSalario)
                .sum();
        System.out.printf("Nómina total: %.2f€%n", nomina);

        // Empleado con mayor salario
        empleados.stream()
                .max(Comparator.comparingDouble(Empleado3::getSalario))
                .ifPresent(e -> System.out.println("Mayor salario: " + e));

        // Nombres de todos, ordenados
        String nombres = empleados.stream()
                .map(Empleado3::getNombre)
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println("Nombres: " + nombres);

        // ================================================================
        // G. PARALLEL STREAMS
        // ================================================================
        System.out.println("\n========== G. Parallel Streams ==========");

        long N = 10_000_000L;

        long t1 = System.nanoTime();
        long sumaSeq = LongStream.rangeClosed(1, N).sum();
        long t2 = System.nanoTime();

        long t3 = System.nanoTime();
        long sumaPar = LongStream.rangeClosed(1, N).parallel().sum();
        long t4 = System.nanoTime();

        System.out.printf("Suma seq  (10M): %d en %.1f ms%n", sumaSeq, (t2 - t1) / 1e6);
        System.out.printf("Suma par  (10M): %d en %.1f ms%n", sumaPar, (t4 - t3) / 1e6);
        System.out.println("parallel() → útil para operaciones costosas sobre datos grandes");
        System.out.println("Cuidado: no usar con estado mutable o I/O");
    }
}

class Empleado3 {
    private String nombre, dept;
    private double salario;

    public Empleado3(String n, String d, double s) {
        nombre = n;
        dept = d;
        salario = s;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDept() {
        return dept;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %.2f€", nombre, dept, salario);
    }
}
