package operadores;
/**
 * OPERADORES ARITMÉTICOS
 * =======================
 * Realizan operaciones matemáticas sobre valores numéricos.
 *
 *  +   Suma
 *  -   Resta
 *  *   Multiplicación
 *  /   División
 *  %   Módulo (resto de la división entera)
 *  ++  Incremento
 *  --  Decremento
 */
public class OperadoresAritmeticos {

    public static void main(String[] args) {

        // ----------------------------------------------------------------
        // 1. OPERACIONES BÁSICAS
        // ----------------------------------------------------------------
        System.out.println("========== Operaciones básicas ==========");
        int a = 17, b = 5;

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b  = " + (a + b));   // 22
        System.out.println("a - b  = " + (a - b));   // 12
        System.out.println("a * b  = " + (a * b));   // 85
        System.out.println("a / b  = " + (a / b));   // 3  (división ENTERA)
        System.out.println("a % b  = " + (a % b));   // 2  (resto)

        // ----------------------------------------------------------------
        // 2. DIVISIÓN ENTERA VS DECIMAL
        // ----------------------------------------------------------------
        System.out.println("\n========== División entera vs decimal ==========");
        int    x = 7, y = 2;
        double xd = 7.0, yd = 2.0;

        System.out.println("7 / 2            = " + (x / y));     // 3  (entero)
        System.out.println("7.0 / 2.0        = " + (xd / yd));   // 3.5
        System.out.println("(double) 7 / 2   = " + ((double) x / y)); // 3.5  casting
        System.out.println("7 / 2.0          = " + (x / yd));    // 3.5  promoción automática

        // ----------------------------------------------------------------
        // 3. MÓDULO (%)
        // ----------------------------------------------------------------
        System.out.println("\n========== Módulo ==========");
        System.out.println("10 % 3  = " + (10 % 3));   // 1
        System.out.println("10 % 2  = " + (10 % 2));   // 0  (par)
        System.out.println("-7 % 3  = " + (-7 % 3));   // -1 (signo del dividendo en Java)
        System.out.println("7 % -3  = " + (7 % -3));   // 1

        // Uso práctico: comprobar par/impar
        for (int i = 1; i <= 6; i++) {
            System.out.println(i + " es " + (i % 2 == 0 ? "par" : "impar"));
        }

        // ----------------------------------------------------------------
        // 4. INCREMENTO Y DECREMENTO
        //    Prefijo  (++i / --i): modifica ANTES de usar el valor
        //    Postfijo (i++ / i--): usa el valor y LUEGO modifica
        // ----------------------------------------------------------------
        System.out.println("\n========== Incremento y Decremento ==========");
        int n = 5;

        System.out.println("n           = " + n);      // 5
        System.out.println("n++         = " + n++);    // 5  (usa 5, luego n=6)
        System.out.println("n (después) = " + n);      // 6
        System.out.println("++n         = " + ++n);    // 7  (n=7, luego usa 7)
        System.out.println("n--         = " + n--);    // 7  (usa 7, luego n=6)
        System.out.println("--n         = " + --n);    // 5  (n=5, luego usa 5)

        // ----------------------------------------------------------------
        // 5. OVERFLOW Y UNDERFLOW
        //    Cuando el resultado supera el rango del tipo, "da la vuelta"
        // ----------------------------------------------------------------
        System.out.println("\n========== Overflow / Underflow ==========");
        int maxInt = Integer.MAX_VALUE;  // 2_147_483_647
        System.out.println("MAX_VALUE      = " + maxInt);
        System.out.println("MAX_VALUE + 1  = " + (maxInt + 1)); // overflow → -2147483648

        int minInt = Integer.MIN_VALUE;
        System.out.println("MIN_VALUE      = " + minInt);
        System.out.println("MIN_VALUE - 1  = " + (minInt - 1)); // underflow → 2147483647

        // Para evitar overflow usar long o Math.addExact
        try {
            int resultado = Math.addExact(maxInt, 1);
        } catch (ArithmeticException e) {
            System.out.println("Math.addExact detectó overflow: " + e.getMessage());
        }

        // ----------------------------------------------------------------
        // 6. OPERACIONES CON DISTINTOS TIPOS (PROMOCIÓN)
        //    Java promociona automáticamente al tipo de mayor rango.
        //    byte/short/char → int en expresiones
        // ----------------------------------------------------------------
        System.out.println("\n========== Promoción de tipos ==========");
        byte  byt = 10;
        short sht = 20;
        int   resultado = byt + sht; // byte + short → int
        System.out.println("byte + short   = " + resultado + " [int]");

        int   entero  = 5;
        long  largo   = 10L;
        long  rLong   = entero + largo; // int + long → long
        System.out.println("int + long     = " + rLong + " [long]");

        long  l2      = 3L;
        float fl      = 1.5f;
        float rFloat  = l2 + fl; // long + float → float
        System.out.println("long + float   = " + rFloat + " [float]");

        float  f2     = 2.0f;
        double db     = 1.5;
        double rDouble = f2 + db; // float + double → double
        System.out.println("float + double = " + rDouble + " [double]");

        // ----------------------------------------------------------------
        // 7. MÉTODOS MATEMÁTICOS RELACIONADOS (java.lang.Math)
        // ----------------------------------------------------------------
        System.out.println("\n========== java.lang.Math ==========");
        System.out.println("abs(-9)        = " + Math.abs(-9));
        System.out.println("pow(2, 10)     = " + (int) Math.pow(2, 10));
        System.out.println("sqrt(144)      = " + Math.sqrt(144));
        System.out.println("cbrt(27)       = " + Math.cbrt(27));
        System.out.println("max(8, 13)     = " + Math.max(8, 13));
        System.out.println("min(8, 13)     = " + Math.min(8, 13));
        System.out.println("floor(3.9)     = " + Math.floor(3.9));
        System.out.println("ceil(3.1)      = " + Math.ceil(3.1));
        System.out.println("round(3.5)     = " + Math.round(3.5));
        System.out.println("log(Math.E)    = " + Math.log(Math.E));
        System.out.println("random()       = " + Math.random()); // [0.0, 1.0)

        // Número aleatorio entre 1 y 100
        int aleatorio = (int) (Math.random() * 100) + 1;
        System.out.println("Aleatorio 1-100= " + aleatorio);
    }
}