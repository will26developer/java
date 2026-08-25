package arrays_primitivos;
/**
 * ARRAYS MULTIDIMENSIONALES Y JAGGED ARRAYS

 * ===========================================
 * Java soporta arrays de arrays (no matrices contiguas como C).
 *
 * int[][] matriz = new int[3][4];  → array de 3 arrays de 4 ints
 * int[][] jagged = new int[3][];   → array de 3 arrays de tamaño variable
 *
 * DIMENSIONES:
 *   2D → tabla / matriz
 *   3D → cubo / libro de tablas
 *   nD → casos especiales (raro más de 3D)
 */
import java.util.Arrays;

public class ArraysMultidimensionales {

    public static void main(String[] args) {

        // ================================================================
        // A. ARRAYS 2D — DECLARACIÓN E INICIALIZACIÓN
        // ================================================================
        System.out.println("========== A. Arrays 2D ==========");

        // Forma 1: con new
        int[][] m1 = new int[3][4]; // 3 filas, 4 columnas, todo a 0
        System.out.println("new int[3][4]:");
        imprimir2D(m1);

        // Forma 2: inicialización directa
        int[][] m2 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Inicialización directa:");
        imprimir2D(m2);

        // Forma 3: declarar y luego inicializar
        int[][] m3;
        m3 = new int[][]{{1,0},{0,1}}; // matriz identidad 2x2
        System.out.println("Identidad 2x2:");
        imprimir2D(m3);

        // ================================================================
        // B. ACCESO A ELEMENTOS
        // ================================================================
        System.out.println("========== B. Acceso a elementos ==========");
        int[][] tabla = {
            {10, 20, 30},
            {40, 50, 60},
            {70, 80, 90}
        };

        System.out.println("tabla[0][0] = " + tabla[0][0]); // 10 (fila 0, col 0)
        System.out.println("tabla[1][2] = " + tabla[1][2]); // 60 (fila 1, col 2)
        System.out.println("tabla[2][1] = " + tabla[2][1]); // 80 (fila 2, col 1)

        // Número de filas y columnas
        System.out.println("Filas       = " + tabla.length);        // 3
        System.out.println("Columnas    = " + tabla[0].length);     // 3

        // Modificar
        tabla[1][1] = 99;
        System.out.println("Tras tabla[1][1]=99:");
        imprimir2D(tabla);

        // ================================================================
        // C. RECORRER UN ARRAY 2D
        // ================================================================
        System.out.println("========== C. Recorrer 2D ==========");
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};

        // Con for clásico (acceso a índices)
        System.out.println("for clásico:");
        for (int f = 0; f < mat.length; f++) {
            for (int c = 0; c < mat[f].length; c++) {
                System.out.printf("%3d", mat[f][c]);
            }
            System.out.println();
        }

        // Con for-each
        System.out.println("for-each:");
        for (int[] fila : mat) {
            for (int val : fila) {
                System.out.printf("%3d", val);
            }
            System.out.println();
        }

        // Suma de todos los elementos
        int suma = 0;
        for (int[] fila : mat) for (int v : fila) suma += v;
        System.out.println("Suma total: " + suma);

        // Suma de cada fila
        System.out.print("Suma filas: ");
        for (int[] fila : mat) {
            int sf = 0; for (int v : fila) sf += v;
            System.out.print(sf + " ");
        }
        System.out.println();

        // Suma de cada columna
        System.out.print("Suma cols : ");
        for (int c = 0; c < mat[0].length; c++) {
            int sc = 0; for (int[] fila : mat) sc += fila[c];
            System.out.print(sc + " ");
        }
        System.out.println();

        // ================================================================
        // D. OPERACIONES DE MATRIZ
        // ================================================================
        System.out.println("\n========== D. Operaciones de matriz ==========");

        int[][] A = {{1,2},{3,4}};
        int[][] B = {{5,6},{7,8}};

        // Suma de matrices
        int[][] suma2 = sumarMatrices(A, B);
        System.out.println("A + B:");
        imprimir2D(suma2);

        // Multiplicación de matrices
        int[][] producto = multiplicarMatrices(A, B);
        System.out.println("A * B:");
        imprimir2D(producto);

        // Transpuesta
        int[][] transpuesta = transponer(A);
        System.out.println("Transpuesta de A:");
        imprimir2D(transpuesta);

        // Diagonal principal
        System.out.print("Diagonal de A: ");
        for (int i = 0; i < A.length; i++) System.out.print(A[i][i] + " ");
        System.out.println();

        // ================================================================
        // E. JAGGED ARRAYS — filas de tamaño variable
        // ================================================================
        System.out.println("\n========== E. Jagged Arrays ==========");

        // Declarar sin fijar columnas
        int[][] jagged = new int[4][];
        jagged[0] = new int[]{1};
        jagged[1] = new int[]{2, 3};
        jagged[2] = new int[]{4, 5, 6};
        jagged[3] = new int[]{7, 8, 9, 10};

        System.out.println("Jagged array (triángulo):");
        for (int f = 0; f < jagged.length; f++) {
            System.out.print("  fila " + f + " (" + jagged[f].length + " elem): ");
            System.out.println(Arrays.toString(jagged[f]));
        }

        // Triángulo de Pascal (jagged clásico)
        System.out.println("\nTriángulo de Pascal (6 filas):");
        int[][] pascal = trianguloPascal(6);
        for (int[] fila : pascal) {
            for (int v : fila) System.out.printf("%4d", v);
            System.out.println();
        }

        // ================================================================
        // F. ARRAYS 3D
        // ================================================================
        System.out.println("\n========== F. Arrays 3D ==========");

        // 3D: capas × filas × columnas
        int[][][] cubo = new int[2][3][4]; // 2 capas, 3 filas, 4 columnas

        // Rellenar con índices
        int val = 1;
        for (int capa = 0; capa < cubo.length; capa++)
            for (int fila = 0; fila < cubo[capa].length; fila++)
                for (int col = 0; col < cubo[capa][fila].length; col++)
                    cubo[capa][fila][col] = val++;

        // Imprimir capa por capa
        for (int capa = 0; capa < cubo.length; capa++) {
            System.out.println("Capa " + capa + ":");
            for (int[] fila : cubo[capa]) {
                System.out.print("  ");
                for (int c : fila) System.out.printf("%3d", c);
                System.out.println();
            }
        }

        // deepToString para arrays 3D
        System.out.println("deepToString: " + Arrays.deepToString(cubo));

        // ================================================================
        // G. COPIAR ARRAYS 2D
        // ================================================================
        System.out.println("\n========== G. Copiar arrays 2D ==========");
        int[][] orig = {{1,2,3},{4,5,6},{7,8,9}};

        // Copia superficial (SHALLOW) — copia las referencias de fila
        int[][] shallow = orig.clone();
        shallow[0][0] = 99; // modifica la fila compartida
        System.out.println("orig tras shallow[0][0]=99: " + Arrays.deepToString(orig)); // cambió!

        // Restablecer
        orig[0][0] = 1;

        // Copia profunda (DEEP) — copia cada fila
        int[][] deep = new int[orig.length][];
        for (int i = 0; i < orig.length; i++) {
            deep[i] = orig[i].clone(); // copia cada fila individualmente
        }
        deep[0][0] = 99;
        System.out.println("orig tras deep[0][0]=99   : " + Arrays.deepToString(orig)); // NO cambió
        System.out.println("deep                      : " + Arrays.deepToString(deep));
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static void imprimir2D(int[][] m) {
        for (int[] fila : m) {
            System.out.print("  ");
            for (int v : fila) System.out.printf("%4d", v);
            System.out.println();
        }
    }

    static int[][] sumarMatrices(int[][] A, int[][] B) {
        int f = A.length, c = A[0].length;
        int[][] R = new int[f][c];
        for (int i = 0; i < f; i++)
            for (int j = 0; j < c; j++)
                R[i][j] = A[i][j] + B[i][j];
        return R;
    }

    static int[][] multiplicarMatrices(int[][] A, int[][] B) {
        int f = A.length, c = B[0].length, k = B.length;
        int[][] R = new int[f][c];
        for (int i = 0; i < f; i++)
            for (int j = 0; j < c; j++)
                for (int n = 0; n < k; n++)
                    R[i][j] += A[i][n] * B[n][j];
        return R;
    }

    static int[][] transponer(int[][] A) {
        int f = A.length, c = A[0].length;
        int[][] T = new int[c][f];
        for (int i = 0; i < f; i++)
            for (int j = 0; j < c; j++)
                T[j][i] = A[i][j];
        return T;
    }

    static int[][] trianguloPascal(int n) {
        int[][] p = new int[n][];
        for (int i = 0; i < n; i++) {
            p[i] = new int[i + 1];
            p[i][0] = p[i][i] = 1;
            for (int j = 1; j < i; j++)
                p[i][j] = p[i-1][j-1] + p[i-1][j];
        }
        return p;
    }
}
