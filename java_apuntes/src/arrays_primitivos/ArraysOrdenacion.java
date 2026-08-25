package arrays_primitivos;
/**
 * CLASE ARRAYS Y ORDENACIÓN
 * ==========================
 * java.util.Arrays contiene métodos estáticos para manipular arrays:
 * ordenar, buscar, comparar, rellenar, convertir a String, etc.
 *
 * ALGORITMOS DE ORDENACIÓN IMPLEMENTADOS POR LA JVM:
 *   - Primitivos: Dual-Pivot Quicksort (O(n log n) promedio)
 *   - Objetos:    TimSort (O(n log n) garantizado, estable)
 */
import java.util.Arrays;

public class ArraysOrdenacion {

    public static void main(String[] args) {

        // ================================================================
        // A. Arrays.sort() — ordenación ascendente
        // ================================================================
        System.out.println("========== A. Arrays.sort() ==========");

        // Ordenar int[]
        int[] nums = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        System.out.println("Antes  : " + Arrays.toString(nums));
        Arrays.sort(nums);
        System.out.println("Después: " + Arrays.toString(nums));

        // Ordenar solo un rango [inicio, fin)
        int[] parcial = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        Arrays.sort(parcial, 2, 6); // ordena índices 2,3,4,5
        System.out.println("sort(2,6): " + Arrays.toString(parcial));

        // Ordenar otros tipos primitivos
        double[] dbs = {3.14, 1.41, 2.71, 0.57};
        Arrays.sort(dbs);
        System.out.println("double[] : " + Arrays.toString(dbs));

        char[] chars = {'j', 'a', 'v', 'A', 'Z'};
        Arrays.sort(chars);
        System.out.println("char[]   : " + Arrays.toString(chars)); // [A, Z, a, j, v]

        // ================================================================
        // B. ORDENAR DESCENDENTE — no hay soporte nativo para primitivos
        // ================================================================
        System.out.println("\n========== B. Ordenar descendente ==========");

        // Opción 1: ordenar y luego invertir manualmente
        int[] desc = {5, 2, 8, 1, 9, 3};
        Arrays.sort(desc);
        invertir(desc);
        System.out.println("Descendente: " + Arrays.toString(desc));

        // Opción 2: usar Integer[] (wrapper) con Comparator
        Integer[] descWrapper = {5, 2, 8, 1, 9, 3};
        Arrays.sort(descWrapper, (a, b) -> b - a);
        System.out.println("Integer[] desc: " + Arrays.toString(descWrapper));

        // Opción 3: con Comparator.reverseOrder()
        Integer[] descWrapper2 = {5, 2, 8, 1, 9, 3};
        Arrays.sort(descWrapper2, java.util.Comparator.reverseOrder());
        System.out.println("reverseOrder: " + Arrays.toString(descWrapper2));

        // ================================================================
        // C. Arrays.binarySearch() — búsqueda binaria
        //    REQUIERE que el array esté ORDENADO previamente
        // ================================================================
        System.out.println("\n========== C. Arrays.binarySearch() ==========");
        int[] ordenado = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

        System.out.println("Array: " + Arrays.toString(ordenado));
        System.out.println("binarySearch(7)  = " + Arrays.binarySearch(ordenado, 7));  //  3
        System.out.println("binarySearch(1)  = " + Arrays.binarySearch(ordenado, 1));  //  0
        System.out.println("binarySearch(19) = " + Arrays.binarySearch(ordenado, 19)); //  9

        // Si no existe: devuelve -(punto_inserción) - 1
        int noExiste = Arrays.binarySearch(ordenado, 6);
        System.out.println("binarySearch(6)  = " + noExiste); // -4 (6 iría en índice 3)
        // Para obtener el punto de inserción: -(resultado + 1)
        System.out.println("Punto inserción de 6: " + (-(noExiste + 1)));

        // Buscar en rango [inicio, fin)
        System.out.println("binarySearch(3,8,11) = " + Arrays.binarySearch(ordenado, 3, 8, 11)); // 5

        // ================================================================
        // D. Arrays.toString() y Arrays.deepToString()
        // ================================================================
        System.out.println("\n========== D. toString() ==========");

        int[]    arr1D = {1, 2, 3, 4, 5};
        int[][]  arr2D = {{1, 2}, {3, 4}, {5, 6}};
        int[][][] arr3D = {{{1,2},{3,4}}, {{5,6},{7,8}}};

        System.out.println("toString 1D  : " + Arrays.toString(arr1D));
        System.out.println("toString 2D  : " + Arrays.toString(arr2D));  // referencias!
        System.out.println("deepToString2D: " + Arrays.deepToString(arr2D));  // contenido
        System.out.println("deepToString3D: " + Arrays.deepToString(arr3D));  // contenido

        // ================================================================
        // E. Arrays.equals() y Arrays.deepEquals()
        // ================================================================
        System.out.println("\n========== E. equals() ==========");

        int[] e1 = {1, 2, 3};
        int[] e2 = {1, 2, 3};
        int[] e3 = {1, 2, 4};
        System.out.println("equals({1,2,3},{1,2,3}): " + Arrays.equals(e1, e2)); // true
        System.out.println("equals({1,2,3},{1,2,4}): " + Arrays.equals(e1, e3)); // false

        int[][] m1 = {{1,2},{3,4}};
        int[][] m2 = {{1,2},{3,4}};
        System.out.println("equals 2D (mal)  : " + Arrays.equals(m1, m2));     // false!
        System.out.println("deepEquals 2D    : " + Arrays.deepEquals(m1, m2)); // true

        // ================================================================
        // F. Arrays.fill() y Arrays.setAll()
        // ================================================================
        System.out.println("\n========== F. fill() y setAll() ==========");

        int[] fill1 = new int[5];
        Arrays.fill(fill1, 10);
        System.out.println("fill(10)       : " + Arrays.toString(fill1));

        // setAll — rellena con una función del índice (Java 8+)
        int[] setAll1 = new int[6];
        Arrays.setAll(setAll1, i -> i * i); // i² para cada posición
        System.out.println("setAll(i->i*i) : " + Arrays.toString(setAll1)); // [0,1,4,9,16,25]

        int[] setAll2 = new int[5];
        Arrays.setAll(setAll2, i -> (i + 1) * 10); // 10,20,30,40,50
        System.out.println("setAll(i*10)   : " + Arrays.toString(setAll2));

        double[] setAllDbl = new double[5];
        Arrays.setAll(setAllDbl, i -> Math.pow(2, i)); // potencias de 2
        System.out.println("setAll(2^i)    : " + Arrays.toString(setAllDbl));

        // parallelSetAll — versión paralela para arrays grandes (Java 8+)
        int[] grande = new int[10];
        Arrays.parallelSetAll(grande, i -> i * 2);
        System.out.println("parallelSetAll : " + Arrays.toString(grande));

        // ================================================================
        // G. Arrays.copyOf() y Arrays.copyOfRange()
        // ================================================================
        System.out.println("\n========== G. copyOf() y copyOfRange() ==========");

        int[] base = {1, 2, 3, 4, 5};
        System.out.println("base           : " + Arrays.toString(base));

        int[] mismoTamaño = Arrays.copyOf(base, 5);
        System.out.println("copyOf(5)      : " + Arrays.toString(mismoTamaño));

        int[] masGrande = Arrays.copyOf(base, 8); // rellena con 0
        System.out.println("copyOf(8)      : " + Arrays.toString(masGrande));

        int[] masChico = Arrays.copyOf(base, 3);  // trunca
        System.out.println("copyOf(3)      : " + Arrays.toString(masChico));

        int[] rango = Arrays.copyOfRange(base, 1, 4); // [1,4) → índices 1,2,3
        System.out.println("copyOfRange(1,4): " + Arrays.toString(rango));

        // ================================================================
        // H. Arrays.stream() — convertir a Stream para operaciones funcionales
        // ================================================================
        System.out.println("\n========== H. Arrays.stream() ==========");

        int[] datos = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int suma = Arrays.stream(datos).sum();
        System.out.println("sum()          : " + suma);

        double media = Arrays.stream(datos).average().orElse(0);
        System.out.printf("average()      : %.1f%n", media);

        int maximo = Arrays.stream(datos).max().getAsInt();
        int minimo = Arrays.stream(datos).min().getAsInt();
        System.out.println("max()          : " + maximo);
        System.out.println("min()          : " + minimo);

        long cuenta = Arrays.stream(datos).filter(n -> n % 2 == 0).count();
        System.out.println("Pares (count)  : " + cuenta);

        int sumaPares = Arrays.stream(datos).filter(n -> n % 2 == 0).sum();
        System.out.println("Suma pares     : " + sumaPares);

        // Convertir int[] a Integer[]
        Integer[] wrappers = Arrays.stream(datos).boxed().toArray(Integer[]::new);
        System.out.println("boxed→Integer[]: " + Arrays.toString(wrappers));

        // ================================================================
        // I. ALGORITMOS DE ORDENACIÓN MANUALES (educativo)
        // ================================================================
        System.out.println("\n========== I. Algoritmos de ordenación ==========");

        // Bubble Sort
        int[] burbuja = {64, 25, 12, 22, 11};
        bubbleSort(burbuja);
        System.out.println("Bubble Sort    : " + Arrays.toString(burbuja));

        // Selection Sort
        int[] seleccion = {64, 25, 12, 22, 11};
        selectionSort(seleccion);
        System.out.println("Selection Sort : " + Arrays.toString(seleccion));

        // Insertion Sort
        int[] insercion = {64, 25, 12, 22, 11};
        insertionSort(insercion);
        System.out.println("Insertion Sort : " + Arrays.toString(insercion));

        // Benchmark: Arrays.sort vs burbuja
        int N = 10_000;
        int[] grande1 = new int[N];
        int[] grande2 = new int[N];
        for (int i = 0; i < N; i++) grande1[i] = grande2[i] = (int)(Math.random() * N);

        long t1 = System.nanoTime();
        bubbleSort(grande1);
        long t2 = System.nanoTime();
        Arrays.sort(grande2);
        long t3 = System.nanoTime();

        System.out.printf("%nBenchmark %d elementos:%n", N);
        System.out.printf("  BubbleSort   : %6.2f ms%n", (t2 - t1) / 1e6);
        System.out.printf("  Arrays.sort  : %6.2f ms%n", (t3 - t2) / 1e6);
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static void invertir(int[] arr) {
        int izq = 0, der = arr.length - 1;
        while (izq < der) {
            int temp = arr[izq];
            arr[izq++] = arr[der];
            arr[der--] = temp;
        }
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean cambio = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp;
                    cambio = true;
                }
            }
            if (!cambio) break; // optimización: si no hubo cambios, ya está ordenado
        }
    }

    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[minIdx]; arr[minIdx] = arr[i]; arr[i] = temp;
        }
    }

    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int clave = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > clave) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = clave;
        }
    }
}
