package operadores;
/**
 * OPERADORES BIT A BIT Y DE DESPLAZAMIENTO
 * ==========================================
 * Operan directamente sobre los bits de los valores enteros
 * (byte, short, int, long, char).
 *
 *  &    AND bit a bit
 *  |    OR  bit a bit
 *  ^    XOR bit a bit
 *  ~    NOT bit a bit (complemento a uno)
 *
 *  <<   Desplazamiento a la izquierda  (signed)
 *  >>   Desplazamiento a la derecha    (signed, rellena con el bit de signo)
 *  >>>  Desplazamiento a la derecha    (unsigned, rellena siempre con 0)
 */
public class OperadoresBit {

    public static void main(String[] args) {

        // ================================================================
        // A. REPRESENTACIÓN BINARIA
        // ================================================================
        System.out.println("========== A. Representación binaria ==========");
        int a = 0b0000_1010; // 10 en decimal
        int b = 0b0000_1100; // 12 en decimal

        System.out.println("a = " + a + "  →  " + padBin(a));
        System.out.println("b = " + b + "  →  " + padBin(b));

        // ================================================================
        // B. AND BIT A BIT (&)
        //    Resultado = 1 solo si ambos bits son 1
        //    0b1010
        //  & 0b1100
        //  --------
        //    0b1000 = 8
        // ================================================================
        System.out.println("\n========== B. AND bit a bit (&) ==========");
        int andResult = a & b;
        System.out.println(padBin(a) + "  (" + a + ")");
        System.out.println(padBin(b) + "  (" + b + ")");
        System.out.println("& " + padBin(andResult) + "  (" + andResult + ")");

        // Uso práctico: comprobar si un bit específico está activo (máscara)
        int flags  = 0b0000_1011; // bits 0, 1 y 3 activos
        int mascaraBit1 = 0b0000_0010; // bit 1
        boolean bit1activo = (flags & mascaraBit1) != 0;
        System.out.println("\nflags  = " + padBin(flags));
        System.out.println("Bit 1 activo: " + bit1activo); // true

        // Comprobar si número es par (bit 0 = 0 si par)
        for (int i = 0; i <= 5; i++) {
            System.out.println(i + " es " + ((i & 1) == 0 ? "par" : "impar") + " (& 1)");
        }

        // ================================================================
        // C. OR BIT A BIT (|)
        //    Resultado = 1 si al menos un bit es 1
        //    0b1010
        //  | 0b1100
        //  --------
        //    0b1110 = 14
        // ================================================================
        System.out.println("\n========== C. OR bit a bit (|) ==========");
        int orResult = a | b;
        System.out.println(padBin(a) + "  (" + a + ")");
        System.out.println(padBin(b) + "  (" + b + ")");
        System.out.println("| " + padBin(orResult) + "  (" + orResult + ")");

        // Uso práctico: activar un bit (set flag)
        int permisos  = 0b0000_0100; // solo permiso de lectura (bit 2)
        int escritura = 0b0000_0010; // permiso de escritura (bit 1)
        permisos = permisos | escritura; // activar escritura
        System.out.println("\nPermisos con escritura: " + padBin(permisos)); // 0b0000_0110

        // ================================================================
        // D. XOR BIT A BIT (^)
        //    Resultado = 1 si los bits son DISTINTOS
        //    0b1010
        //  ^ 0b1100
        //  --------
        //    0b0110 = 6
        // ================================================================
        System.out.println("\n========== D. XOR bit a bit (^) ==========");
        int xorResult = a ^ b;
        System.out.println(padBin(a) + "  (" + a + ")");
        System.out.println(padBin(b) + "  (" + b + ")");
        System.out.println("^ " + padBin(xorResult) + "  (" + xorResult + ")");

        // Uso práctico: toggle (cambiar estado de un bit)
        int estado   = 0b0000_0101;
        int toggleBit2 = 0b0000_0100; // bit 2
        estado = estado ^ toggleBit2; // desactiva bit 2
        System.out.println("\nDespués de toggle bit 2: " + padBin(estado));
        estado = estado ^ toggleBit2; // lo reactiva
        System.out.println("Después de toggle x2   : " + padBin(estado));

        // XOR para intercambiar variables SIN variable temporal
        System.out.println("\nSwap con XOR:");
        int x = 5, y = 9;
        System.out.println("Antes:  x=" + x + ", y=" + y);
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("Después: x=" + x + ", y=" + y);

        // ================================================================
        // E. NOT BIT A BIT (~) — complemento a uno
        //    Invierte todos los bits: ~n = -(n+1)
        // ================================================================
        System.out.println("\n========== E. NOT bit a bit (~) ==========");
        int n = 5;
        System.out.println("~5  = " + (~n));  // -6
        System.out.println("~0  = " + (~0));  // -1
        System.out.println("~-1 = " + (~-1)); //  0

        // ================================================================
        // F. DESPLAZAMIENTO A LA IZQUIERDA (<<)
        //    Cada posición desplazada = multiplicar por 2
        //    Rellena con 0 por la derecha
        // ================================================================
        System.out.println("\n========== F. Desplazamiento izquierda (<<) ==========");
        int val = 1;
        for (int i = 0; i <= 8; i++) {
            System.out.println("1 << " + i + " = " + (val << i) +
                               "  [" + padBin(val << i) + "]");
        }
        // Multiplicación rápida por potencias de 2
        System.out.println("\n100 << 3 = " + (100 << 3) + "  (100 * 8 = 800)");

        // ================================================================
        // G. DESPLAZAMIENTO A LA DERECHA SIGNED (>>)
        //    Cada posición = dividir por 2 (parte entera)
        //    Rellena con el BIT DE SIGNO (0 si positivo, 1 si negativo)
        // ================================================================
        System.out.println("\n========== G. Desplazamiento derecha signed (>>) ==========");
        System.out.println("100 >> 1 = " + (100 >> 1) + "  (100/2)");
        System.out.println("100 >> 2 = " + (100 >> 2) + "  (100/4)");
        System.out.println("100 >> 3 = " + (100 >> 3) + "  (100/8)");

        int negativo = -16;
        System.out.println("\n-16 >> 1 = " + (negativo >> 1)); // -8  (conserva signo)
        System.out.println("-16 >> 2 = " + (negativo >> 2)); // -4

        // ================================================================
        // H. DESPLAZAMIENTO A LA DERECHA UNSIGNED (>>>)
        //    Rellena SIEMPRE con 0 (ignora el signo)
        // ================================================================
        System.out.println("\n========== H. Desplazamiento derecha unsigned (>>>) ==========");
        System.out.println("-1   >>> 1  = " + (-1 >>> 1));   // 2147483647 (Integer.MAX_VALUE)
        System.out.println("-1   >>> 28 = " + (-1 >>> 28));  // 15
        System.out.println("100  >>> 2  = " + (100 >>> 2));  // 25 (igual que >> para positivos)

        // ================================================================
        // I. CASOS DE USO REALES
        // ================================================================
        System.out.println("\n========== I. Casos de uso reales ==========");

        // 1. Codificar/decodificar color RGB en un int
        int r = 255, g = 128, bColor = 0;
        int rgb = (r << 16) | (g << 8) | bColor;
        System.out.println("RGB codificado : " + rgb + " = #" + Integer.toHexString(rgb).toUpperCase());
        int rDec = (rgb >> 16) & 0xFF;
        int gDec = (rgb >> 8)  & 0xFF;
        int bDec =  rgb        & 0xFF;
        System.out.println("Decodificado: R=" + rDec + " G=" + gDec + " B=" + bDec);

        // 2. Potencia de 2 más cercana (por arriba)
        int num = 100;
        int pot2 = 1;
        while (pot2 < num) pot2 <<= 1;
        System.out.println("\nPotencia de 2 >= " + num + " → " + pot2); // 128

        // 3. Sistema de permisos con bits (estilo UNIX)
        final int LEER    = 0b100; // 4
        final int ESCRIBIR= 0b010; // 2
        final int EJECUTAR= 0b001; // 1

        int miPermiso = LEER | ESCRIBIR; // 6 = 0b110
        System.out.println("\nMi permiso: " + padBin(miPermiso));
        System.out.println("¿Puede leer?    " + ((miPermiso & LEER)     != 0));
        System.out.println("¿Puede escribir?" + ((miPermiso & ESCRIBIR) != 0));
        System.out.println("¿Puede ejecutar?" + ((miPermiso & EJECUTAR) != 0));
    }

    // Muestra un int como 8 bits con prefijo 0b
    static String padBin(int n) {
        return "0b" + String.format("%8s", Integer.toBinaryString(n & 0xFF)).replace(' ', '0');
    }
}