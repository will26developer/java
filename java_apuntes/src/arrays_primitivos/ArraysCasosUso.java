package arrays_primitivos;
/**
 * ARRAYS PRIMITIVOS — CASOS DE USO PRÁCTICOS
 * ============================================
 * Algoritmos y patrones reales resueltos con arrays.
 */
import java.util.Arrays;

public class ArraysCasosUso {

    public static void main(String[] args) {

        // ================================================================
        // 1. ESTADÍSTICAS COMPLETAS DE UN ARRAY
        // ================================================================
        System.out.println("========== 1. Estadísticas ==========");
        int[] datos = {4, 7, 2, 9, 1, 5, 3, 8, 6, 5, 2, 7, 5};

        System.out.println("Datos  : " + Arrays.toString(datos));
        System.out.printf("Suma   : %d%n", suma(datos));
        System.out.printf("Media  : %.2f%n", media(datos));
        System.out.printf("Máximo : %d%n", maximo(datos));
        System.out.printf("Mínimo : %d%n", minimo(datos));
        System.out.printf("Mediana: %.1f%n", mediana(datos.clone()));
        System.out.printf("Moda   : %d%n", moda(datos));
        System.out.printf("Rango  : %d%n", maximo(datos) - minimo(datos));

        // ================================================================
        // 2. ROTAR UN ARRAY
        // ================================================================
        System.out.println("\n========== 2. Rotación ==========");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original      : " + Arrays.toString(arr));

        rotarDerecha(arr, 2);
        System.out.println("Rotar der 2   : " + Arrays.toString(arr)); // [4, 5, 1, 2, 3]

        rotarIzquierda(arr, 2);
        System.out.println("Rotar izq 2   : " + Arrays.toString(arr)); // [1, 2, 3, 4, 5]

        // ================================================================
        // 3. ELIMINAR DUPLICADOS
        // ================================================================
        System.out.println("\n========== 3. Eliminar duplicados ==========");
        int[] conDups = {1, 3, 2, 1, 5, 3, 4, 2, 5};
        int[] sinDups = eliminarDuplicados(conDups);
        System.out.println("Con dups  : " + Arrays.toString(conDups));
        System.out.println("Sin dups  : " + Arrays.toString(sinDups));

        // ================================================================
        // 4. INTERSECCIÓN Y UNIÓN DE ARRAYS
        // ================================================================
        System.out.println("\n========== 4. Intersección y Unión ==========");
        int[] setA = {1, 2, 3, 4, 5};
        int[] setB = {3, 4, 5, 6, 7};

        int[] interseccion = interseccion(setA, setB);
        int[] union        = union(setA, setB);

        System.out.println("A            : " + Arrays.toString(setA));
        System.out.println("B            : " + Arrays.toString(setB));
        System.out.println("Intersección : " + Arrays.toString(interseccion));
        System.out.println("Unión        : " + Arrays.toString(union));

        // ================================================================
        // 5. APLANAR ARRAY 2D A 1D
        // ================================================================
        System.out.println("\n========== 5. Aplanar 2D → 1D ==========");
        int[][] matriz = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] plano = aplanar(matriz);
        System.out.println("Plano : " + Arrays.toString(plano));

        // ================================================================
        // 6. FRECUENCIA DE ELEMENTOS
        // ================================================================
        System.out.println("\n========== 6. Frecuencia ==========");
        int[] vals = {1, 3, 2, 1, 5, 3, 3, 2, 5, 1};
        int[] freqs = frecuencia(vals, 6); // valores del 0 al 5
        System.out.println("Valores   : " + Arrays.toString(vals));
        System.out.println("Frecuencia:");
        for (int i = 1; i < freqs.length; i++) {
            if (freqs[i] > 0) System.out.println("  " + i + " aparece " + freqs[i] + " veces");
        }

        // ================================================================
        // 7. ENCONTRAR EL SEGUNDO MÁXIMO
        // ================================================================
        System.out.println("\n========== 7. Segundo máximo ==========");
        int[] nums = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        System.out.println("Array         : " + Arrays.toString(nums));
        System.out.println("Máximo        : " + maximo(nums));
        System.out.println("Segundo máx   : " + segundoMaximo(nums));

        // ================================================================
        // 8. VERIFICAR SI UN ARRAY ES PALÍNDROMO
        // ================================================================
        System.out.println("\n========== 8. Palíndromo ==========");
        int[][] tests = {{1,2,3,2,1}, {1,2,3,4,5}, {1,2,2,1}, {5}};
        for (int[] t : tests) {
            System.out.println(Arrays.toString(t) + " → " + esPalindromo(t));
        }

        // ================================================================
        // 9. MEZCLAR (SHUFFLE) UN ARRAY — Fisher-Yates
        // ================================================================
        System.out.println("\n========== 9. Mezclar (shuffle) ==========");
        int[] baraja = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Antes  : " + Arrays.toString(baraja));
        fisherYates(baraja);
        System.out.println("Después: " + Arrays.toString(baraja));

        // ================================================================
        // 10. COMPRIMIR/CODIFICAR RUN-LENGTH ENCODING
        // ================================================================
        System.out.println("\n========== 10. Run-Length Encoding ==========");
        int[] rle = {1, 1, 1, 2, 2, 3, 1, 1, 4, 4, 4, 4};
        System.out.println("Original    : " + Arrays.toString(rle));
        System.out.println("RLE         : " + rleEncode(rle));

        // ================================================================
        // 11. BUSQUEDA BINARIA MANUAL
        // ================================================================
        System.out.println("\n========== 11. Búsqueda binaria ==========");
        int[] sorted = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println("Array  : " + Arrays.toString(sorted));
        System.out.println("Buscar 23 → índice: " + busquedaBinaria(sorted, 23));
        System.out.println("Buscar 10 → índice: " + busquedaBinaria(sorted, 10));

        // ================================================================
        // 12. CONVERTIR ARRAY A STRING Y VICEVERSA
        // ================================================================
        System.out.println("\n========== 12. Conversiones ==========");
        char[] letras = {'H', 'o', 'l', 'a'};
        String cadena = new String(letras);
        char[] deVuelta = cadena.toCharArray();
        System.out.println("char[] → String : " + cadena);
        System.out.println("String → char[] : " + Arrays.toString(deVuelta));

        // int[] a CSV String
        int[] numArr = {1, 2, 3, 4, 5};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numArr.length; i++) {
            sb.append(numArr[i]);
            if (i < numArr.length - 1) sb.append(",");
        }
        System.out.println("int[] → CSV : " + sb);

        // CSV String a int[]
        String csv = "10,20,30,40,50";
        String[] partes = csv.split(",");
        int[] desdeCSV = new int[partes.length];
        for (int i = 0; i < partes.length; i++) desdeCSV[i] = Integer.parseInt(partes[i]);
        System.out.println("CSV → int[] : " + Arrays.toString(desdeCSV));
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static int    suma(int[] a)   { int s=0; for(int n:a) s+=n; return s; }
    static double media(int[] a)  { return (double) suma(a) / a.length; }
    static int    maximo(int[] a) { int m=a[0]; for(int n:a) if(n>m) m=n; return m; }
    static int    minimo(int[] a) { int m=a[0]; for(int n:a) if(n<m) m=n; return m; }

    static double mediana(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        return n % 2 == 0 ? (a[n/2-1] + a[n/2]) / 2.0 : a[n/2];
    }

    static int moda(int[] a) {
        int max = maximo(a);
        int[] freq = new int[max + 1];
        for (int n : a) freq[n]++;
        int moda = 0;
        for (int i = 0; i <= max; i++) if (freq[i] > freq[moda]) moda = i;
        return moda;
    }

    static void rotarDerecha(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        invertirRango(arr, 0, n - 1);
        invertirRango(arr, 0, k - 1);
        invertirRango(arr, k, n - 1);
    }

    static void rotarIzquierda(int[] arr, int k) {
        rotarDerecha(arr, arr.length - (k % arr.length));
    }

    static void invertirRango(int[] arr, int l, int r) {
        while (l < r) { int t = arr[l]; arr[l++] = arr[r]; arr[r--] = t; }
    }

    static int[] eliminarDuplicados(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int count = 0;
        for (int i = 0; i < sorted.length; i++)
            if (i == 0 || sorted[i] != sorted[i-1]) count++;
        int[] res = new int[count];
        int idx = 0;
        for (int i = 0; i < sorted.length; i++)
            if (i == 0 || sorted[i] != sorted[i-1]) res[idx++] = sorted[i];
        return res;
    }

    static int[] interseccion(int[] a, int[] b) {
        int[] sorted_b = b.clone();
        Arrays.sort(sorted_b);
        int count = 0;
        int[] temp = new int[Math.min(a.length, b.length)];
        for (int n : a)
            if (Arrays.binarySearch(sorted_b, n) >= 0) temp[count++] = n;
        return Arrays.copyOf(temp, count);
    }

    static int[] union(int[] a, int[] b) {
        int[] combined = new int[a.length + b.length];
        System.arraycopy(a, 0, combined, 0, a.length);
        System.arraycopy(b, 0, combined, a.length, b.length);
        return eliminarDuplicados(combined);
    }

    static int[] aplanar(int[][] m) {
        int total = 0;
        for (int[] f : m) total += f.length;
        int[] res = new int[total];
        int idx = 0;
        for (int[] f : m) for (int v : f) res[idx++] = v;
        return res;
    }

    static int[] frecuencia(int[] arr, int max) {
        int[] freq = new int[max];
        for (int n : arr) if (n < max) freq[n]++;
        return freq;
    }

    static int segundoMaximo(int[] arr) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        for (int n : arr) {
            if (n > max1) { max2 = max1; max1 = n; }
            else if (n > max2 && n != max1) max2 = n;
        }
        return max2;
    }

    static boolean esPalindromo(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) if (arr[l++] != arr[r--]) return false;
        return true;
    }

    static void fisherYates(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));
            int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
        }
    }

    static String rleEncode(int[] arr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < arr.length) {
            int count = 1;
            while (i + count < arr.length && arr[i + count] == arr[i]) count++;
            sb.append(arr[i]).append("x").append(count).append(" ");
            i += count;
        }
        return sb.toString().trim();
    }

    static int busquedaBinaria(int[] arr, int objetivo) {
        int izq = 0, der = arr.length - 1;
        while (izq <= der) {
            int mid = izq + (der - izq) / 2;
            if      (arr[mid] == objetivo) return mid;
            else if (arr[mid] < objetivo)  izq = mid + 1;
            else                           der = mid - 1;
        }
        return -1;
    }
}
