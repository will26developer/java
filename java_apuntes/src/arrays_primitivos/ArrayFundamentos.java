package arrays_primitivos;
/**
 * ARRAYS PRIMITIVOS — FUNDAMENTOS
 * =================================
 * Un array es una estructura de datos que almacena elementos del
 * MISMO tipo en posiciones contiguas de memoria.
 * CARACTERÍSTICAS:
 *   - Tamaño FIJO una vez creado (no puede crecer ni reducirse)
 *   - Índices base 0: primer elemento = arr[0], último = arr[n-1]
 *   - Acceso O(1) — acceso directo por índice
 *   - Los arrays son OBJETOS en Java (heredan de Object)
 *   - Se almacenan en el heap
 *
 * TIPOS DE ARRAYS:
 *   - Unidimensionales (vectores)
 *   - Multidimensionales (matrices, cubos…)
 *   - Irregulares (jagged arrays)
 */
public class ArrayFundamentos {

    public static void main(String[] args) {

        // ================================================================
        // A. DECLARACIÓN E INICIALIZACIÓN
        // ================================================================
        System.out.println("========== A. Declaración e Inicialización ==========");

        // 1. Declarar y luego inicializar
        int[] numeros;                    // declaración (no hay array todavía)
        numeros = new int[5];             // creación: 5 enteros, todos a 0
        System.out.println("new int[5]       : " + java.util.Arrays.toString(numeros));

        // 2. Declarar e inicializar en la misma línea
        int[] pares = new int[]{2, 4, 6, 8, 10};
        System.out.println("new int[]{...}   : " + java.util.Arrays.toString(pares));

        // 3. Forma abreviada (inicialización directa)
        int[] impares = {1, 3, 5, 7, 9};
        System.out.println("{1,3,5,7,9}      : " + java.util.Arrays.toString(impares));

        // 4. Todos los tipos primitivos
        byte[]    bytes    = {1, 2, 3};
        short[]   shorts   = {100, 200, 300};
        long[]    longs    = {10L, 20L, 30L};
        float[]   floats   = {1.1f, 2.2f, 3.3f};
        double[]  doubles  = {1.1, 2.2, 3.3};
        boolean[] bools    = {true, false, true};
        char[]    chars    = {'J', 'a', 'v', 'a'};

        System.out.println("\nbyte[]   : " + java.util.Arrays.toString(bytes));
        System.out.println("short[]  : " + java.util.Arrays.toString(shorts));
        System.out.println("long[]   : " + java.util.Arrays.toString(longs));
        System.out.println("float[]  : " + java.util.Arrays.toString(floats));
        System.out.println("double[] : " + java.util.Arrays.toString(doubles));
        System.out.println("boolean[]: " + java.util.Arrays.toString(bools));
        System.out.println("char[]   : " + java.util.Arrays.toString(chars));
        System.out.println("char[] como String: " + new String(chars)); // "Java"

        // ================================================================
        // B. VALORES POR DEFECTO
        // ================================================================
        System.out.println("\n========== B. Valores por defecto ==========");
        // Al crear un array con new, los elementos se inicializan
        // automáticamente al valor por defecto del tipo.

        int[]     defInt  = new int[3];
        double[]  defDbl  = new double[3];
        boolean[] defBool = new boolean[3];
        char[]    defChar = new char[3];

        System.out.println("int[]     defecto: " + java.util.Arrays.toString(defInt));   // [0, 0, 0]
        System.out.println("double[]  defecto: " + java.util.Arrays.toString(defDbl));   // [0.0, 0.0, 0.0]
        System.out.println("boolean[] defecto: " + java.util.Arrays.toString(defBool));  // [false, false, false]
        System.out.println("char[]    defecto: " + java.util.Arrays.toString(defChar));  // [\0, \0, \0]

        // ================================================================
        // C. ACCESO Y MODIFICACIÓN
        // ================================================================
        System.out.println("\n========== C. Acceso y Modificación ==========");
        int[] arr = {10, 20, 30, 40, 50};

        // Leer por índice
        System.out.println("arr[0]  = " + arr[0]);   // 10 (primero)
        System.out.println("arr[4]  = " + arr[4]);   // 50 (último)
        System.out.println("arr[arr.length-1] = " + arr[arr.length - 1]); // último siempre

        // Modificar por índice
        arr[2] = 99;
        System.out.println("Tras arr[2]=99: " + java.util.Arrays.toString(arr));

        // ArrayIndexOutOfBoundsException — acceder fuera del rango
        try {
            int error = arr[10]; // índice fuera de rango
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage()); // Index 10 out of bounds for length 5
        }

        // Índice negativo también lanza excepción
        try {
            int error = arr[-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Índice negativo: " + e.getMessage());
        }

        // ================================================================
        // D. PROPIEDAD length
        // ================================================================
        System.out.println("\n========== D. length ==========");
        int[] a = {1, 2, 3, 4, 5};
        System.out.println("a.length       = " + a.length);       // 5
        System.out.println("Último índice  = " + (a.length - 1)); // 4
        System.out.println("Array vacío [] = " + new int[0].length); // 0

        // length es una PROPIEDAD, no un método (sin paréntesis)
        // Strings tienen length() CON paréntesis
        // Arrays tienen length SIN paréntesis

        // ================================================================
        // E. RECORRER UN ARRAY
        // ================================================================
        System.out.println("\n========== E. Recorrer arrays ==========");
        int[] datos = {3, 7, 1, 9, 4, 6, 2, 8, 5};

        // 1. for clásico (cuando necesitas el índice)
        System.out.print("for clásico : ");
        for (int i = 0; i < datos.length; i++) {
            System.out.print(datos[i] + " ");
        }
        System.out.println();

        // 2. for-each (cuando NO necesitas el índice)
        System.out.print("for-each    : ");
        for (int n : datos) {
            System.out.print(n + " ");
        }
        System.out.println();

        // 3. Recorrer al revés
        System.out.print("al revés    : ");
        for (int i = datos.length - 1; i >= 0; i--) {
            System.out.print(datos[i] + " ");
        }
        System.out.println();

        // 4. Solo pares del array
        System.out.print("solo pares  : ");
        for (int n : datos) {
            if (n % 2 == 0) System.out.print(n + " ");
        }
        System.out.println();

        // ================================================================
        // F. OPERACIONES COMUNES
        // ================================================================
        System.out.println("\n========== F. Operaciones comunes ==========");
        int[] nums = {4, 7, 2, 9, 1, 5, 3, 8, 6};

        // Suma y media
        int suma = 0;
        for (int n : nums) suma += n;
        double media = (double) suma / nums.length;
        System.out.println("Suma   : " + suma);
        System.out.printf("Media  : %.2f%n", media);

        // Máximo y mínimo
        int max = nums[0], min = nums[0];
        for (int n : nums) {
            if (n > max) max = n;
            if (n < min) min = n;
        }
        System.out.println("Máximo : " + max);
        System.out.println("Mínimo : " + min);

        // Buscar un elemento (búsqueda lineal)
        int buscar = 5;
        int posicion = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == buscar) { posicion = i; break; }
        }
        System.out.println("Posición de " + buscar + ": " + posicion);

        // Contar ocurrencias
        int[] conOcurrencias = {1, 2, 3, 2, 1, 2, 4, 2};
        int ocurrencias = 0;
        for (int n : conOcurrencias) if (n == 2) ocurrencias++;
        System.out.println("Ocurrencias de 2: " + ocurrencias);

        // ================================================================
        // G. COPIAR ARRAYS
        // ================================================================
        System.out.println("\n========== G. Copiar arrays ==========");
        int[] original = {1, 2, 3, 4, 5};

        // MAL: asignación copia la REFERENCIA, no los datos
        int[] referencia = original;
        referencia[0] = 99;
        System.out.println("original tras referencia[0]=99: "
                + java.util.Arrays.toString(original)); // [99, 2, 3, 4, 5] ← cambió!

        // Restablecer
        original[0] = 1;

        // BIEN 1: System.arraycopy (más rápido)
        int[] copia1 = new int[original.length];
        System.arraycopy(original, 0, copia1, 0, original.length);
        copia1[0] = 99;
        System.out.println("original tras copia1[0]=99: "
                + java.util.Arrays.toString(original)); // [1, 2, 3, 4, 5] ← NO cambió

        // BIEN 2: Arrays.copyOf
        int[] copia2 = java.util.Arrays.copyOf(original, original.length);
        System.out.println("Arrays.copyOf: " + java.util.Arrays.toString(copia2));

        // Arrays.copyOf con tamaño mayor (rellena con 0)
        int[] copiaGrande = java.util.Arrays.copyOf(original, 8);
        System.out.println("copyOf más grande: " + java.util.Arrays.toString(copiaGrande));

        // Arrays.copyOf con tamaño menor (trunca)
        int[] copiaChica = java.util.Arrays.copyOf(original, 3);
        System.out.println("copyOf más pequeño: " + java.util.Arrays.toString(copiaChica));

        // BIEN 3: Arrays.copyOfRange (subarray)
        int[] sub = java.util.Arrays.copyOfRange(original, 1, 4); // [inicio, fin)
        System.out.println("copyOfRange(1,4): " + java.util.Arrays.toString(sub)); // [2, 3, 4]

        // BIEN 4: clone()
        int[] copia3 = original.clone();
        System.out.println("clone(): " + java.util.Arrays.toString(copia3));

        // ================================================================
        // H. COMPARAR ARRAYS
        // ================================================================
        System.out.println("\n========== H. Comparar arrays ==========");
        int[] x = {1, 2, 3};
        int[] y = {1, 2, 3};
        int[] z = {1, 2, 4};

        // MAL: == compara referencias
        System.out.println("x == y (referencias): " + (x == y));       // false

        // BIEN: Arrays.equals compara contenido
        System.out.println("Arrays.equals(x, y) : " + java.util.Arrays.equals(x, y)); // true
        System.out.println("Arrays.equals(x, z) : " + java.util.Arrays.equals(x, z)); // false

        // Arrays.compare — orden lexicográfico (Java 9+)
        // 0=iguales, <0=x<y, >0=x>y
        System.out.println("Arrays.compare(x,y) : " + java.util.Arrays.compare(x, y)); // 0
        System.out.println("Arrays.compare(x,z) : " + java.util.Arrays.compare(x, z)); // <0

        // ================================================================
        // I. RELLENAR — Arrays.fill()
        // ================================================================
        System.out.println("\n========== I. Arrays.fill() ==========");
        int[] relleno = new int[5];
        java.util.Arrays.fill(relleno, 7);
        System.out.println("fill(7)       : " + java.util.Arrays.toString(relleno));

        // fill en rango [inicio, fin)
        java.util.Arrays.fill(relleno, 1, 4, 0);
        System.out.println("fill(1,4,0)   : " + java.util.Arrays.toString(relleno));

        double[] rellenoDbl = new double[4];
        java.util.Arrays.fill(rellenoDbl, 3.14);
        System.out.println("fill(3.14)    : " + java.util.Arrays.toString(rellenoDbl));
    }
}
