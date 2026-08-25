package math;

/**
 * CLASE MATH — REFERENCIA COMPLETA
 * ==================================
 * java.lang.Math es una clase final con solo miembros estáticos.
 * No se puede instanciar ni extender.
 * Todos los métodos operan con double salvo los que indican otro tipo.
 *
 * CONSTANTES:
 *   Math.PI  → 3.141592653589793
 *   Math.E   → 2.718281828459045
 *
 * CATEGORÍAS DE MÉTODOS:
 *   - Valor absoluto y signo
 *   - Redondeo
 *   - Mínimo y máximo
 *   - Potencia, raíz y logaritmo
 *   - Trigonometría
 *   - Números aleatorios
 *   - Operaciones exactas (lanzan excepción en overflow)
 *   - Hipotenusa y exponencial
 */
public class ClaseMath {

    public static void main(String[] args) {

        // ================================================================
        // A. CONSTANTES
        // ================================================================
        System.out.println("========== A. Constantes ==========");
        System.out.println("Math.PI  = " + Math.PI);   // 3.141592653589793
        System.out.println("Math.E   = " + Math.E);    // 2.718281828459045

        // Usos comunes de PI
        double radio = 5.0;
        System.out.println("\nCircunferencia (2πr) : " + 2 * Math.PI * radio);
        System.out.println("Área círculo (πr²)   : " + Math.PI * radio * radio);
        System.out.println("Volumen esfera (4/3πr³): " + (4.0/3) * Math.PI * Math.pow(radio, 3));

        // ================================================================
        // B. VALOR ABSOLUTO — abs()
        // ================================================================
        System.out.println("\n========== B. abs() ==========");
        System.out.println("abs(-5)      = " + Math.abs(-5));       // int
        System.out.println("abs(-5L)     = " + Math.abs(-5L));      // long
        System.out.println("abs(-3.14f)  = " + Math.abs(-3.14f));   // float
        System.out.println("abs(-3.14)   = " + Math.abs(-3.14));    // double
        System.out.println("abs(0)       = " + Math.abs(0));
        System.out.println("abs(Integer.MIN_VALUE) = " + Math.abs(Integer.MIN_VALUE));
        // ¡TRAMPA! Integer.MIN_VALUE no tiene positivo equivalente en int → devuelve negativo
        System.out.println("  ↑ OJO: Integer.MIN_VALUE (-2147483648) desborda a sí mismo");

        // ================================================================
        // C. REDONDEO — floor, ceil, round, rint, truncate
        // ================================================================
        System.out.println("\n========== C. Redondeo ==========");
        double[] vals = {2.1, 2.5, 2.9, -2.1, -2.5, -2.9};
        System.out.printf("%-8s %-10s %-10s %-10s %-10s%n", "valor", "floor", "ceil", "round", "rint");
        System.out.println("-".repeat(50));
        for (double v : vals) {
            System.out.printf("%-8.1f %-10.1f %-10.1f %-10d %-10.1f%n",
                    v,
                    Math.floor(v),   // hacia -∞ (siempre hacia abajo)
                    Math.ceil(v),    // hacia +∞ (siempre hacia arriba)
                    Math.round(v),   // al entero más cercano (half-up)
                    Math.rint(v));   // al entero más cercano (half-even = banker's rounding)
        }

        // Diferencia round vs rint en el caso .5
        System.out.println("\nround(2.5)  = " + Math.round(2.5));   // 3  (half-up)
        System.out.println("round(3.5)  = " + Math.round(3.5));   // 4  (half-up)
        System.out.println("rint(2.5)   = " + Math.rint(2.5));    // 2.0 (half-even: redondea al par)
        System.out.println("rint(3.5)   = " + Math.rint(3.5));    // 4.0 (half-even: redondea al par)

        // Truncar decimales (casting a int)
        System.out.println("\n(int) 3.9   = " + (int) 3.9);  // 3 — trunca, no redondea

        // ================================================================
        // D. MÍNIMO Y MÁXIMO — min(), max()
        // ================================================================
        System.out.println("\n========== D. min() y max() ==========");
        System.out.println("max(3, 7)        = " + Math.max(3, 7));
        System.out.println("min(3, 7)        = " + Math.min(3, 7));
        System.out.println("max(-5, -2)      = " + Math.max(-5, -2));   // -2
        System.out.println("max(3.14, 2.71)  = " + Math.max(3.14, 2.71));
        System.out.println("max(Long.MAX, 0) = " + Math.max(Long.MAX_VALUE, 0L));

        // Clamp manual (limitar un valor a un rango)
        double valor = 150.0, minVal = 0.0, maxVal = 100.0;
        double clamp = Math.max(minVal, Math.min(maxVal, valor));
        System.out.println("\nclamp(150, 0, 100) = " + clamp); // 100.0

        // ================================================================
        // E. POTENCIA Y RAÍCES — pow(), sqrt(), cbrt()
        // ================================================================
        System.out.println("\n========== E. Potencia y raíces ==========");
        System.out.println("pow(2, 10)    = " + (int) Math.pow(2, 10));  // 1024
        System.out.println("pow(3, 3)     = " + Math.pow(3, 3));          // 27.0
        System.out.println("pow(9, 0.5)   = " + Math.pow(9, 0.5));        // 3.0 (= sqrt)
        System.out.println("pow(2, -1)    = " + Math.pow(2, -1));         // 0.5
        System.out.println("pow(0, 0)     = " + Math.pow(0, 0));          // 1.0 (por convención)
        System.out.println("pow(-2, 3)    = " + Math.pow(-2, 3));         // -8.0

        System.out.println("\nsqrt(144)     = " + Math.sqrt(144));         // 12.0
        System.out.println("sqrt(2)       = " + Math.sqrt(2));            // 1.4142...
        System.out.println("sqrt(-1)      = " + Math.sqrt(-1));           // NaN

        System.out.println("\ncbrt(27)      = " + Math.cbrt(27));          // 3.0 (raíz cúbica)
        System.out.println("cbrt(-8)      = " + Math.cbrt(-8));           // -2.0
        System.out.println("cbrt(125)     = " + Math.cbrt(125));          // 5.0

        // ================================================================
        // F. LOGARITMOS — log(), log10(), log1p()
        // ================================================================
        System.out.println("\n========== F. Logaritmos ==========");
        System.out.println("log(E)        = " + Math.log(Math.E));       // 1.0  (ln natural)
        System.out.println("log(1)        = " + Math.log(1));            // 0.0
        System.out.println("log(0)        = " + Math.log(0));            // -Infinity
        System.out.println("log(-1)       = " + Math.log(-1));           // NaN

        System.out.println("\nlog10(100)    = " + Math.log10(100));       // 2.0
        System.out.println("log10(1000)   = " + Math.log10(1000));       // 3.0
        System.out.println("log10(1)      = " + Math.log10(1));          // 0.0

        // Logaritmo en base n usando cambio de base: log_n(x) = log(x)/log(n)
        double logBase2de8 = Math.log(8) / Math.log(2);
        System.out.println("\nlog2(8)       = " + logBase2de8);           // 3.0

        // log1p(x) = log(1 + x) — más preciso para valores x pequeños
        System.out.println("log1p(0)      = " + Math.log1p(0));          // 0.0
        System.out.println("log1p(E-1)    = " + Math.log1p(Math.E - 1)); // 1.0

        // ================================================================
        // G. EXPONENCIAL — exp(), expm1()
        // ================================================================
        System.out.println("\n========== G. Exponencial ==========");
        System.out.println("exp(0)        = " + Math.exp(0));    // 1.0  (e^0)
        System.out.println("exp(1)        = " + Math.exp(1));    // e    (e^1)
        System.out.println("exp(2)        = " + Math.exp(2));    // e²
        System.out.println("exp(-1)       = " + Math.exp(-1));   // 1/e

        // expm1(x) = e^x - 1 — más preciso para x cercano a 0
        System.out.println("expm1(0)      = " + Math.expm1(0));  // 0.0

        // ================================================================
        // H. TRIGONOMETRÍA — sin, cos, tan, asin, acos, atan, atan2
        //    Todos trabajan en RADIANES. Para grados: Math.toRadians()
        // ================================================================
        System.out.println("\n========== H. Trigonometría ==========");

        // Conversión grados ↔ radianes
        System.out.println("toRadians(180) = " + Math.toRadians(180));  // π
        System.out.println("toDegrees(PI)  = " + Math.toDegrees(Math.PI)); // 180.0

        // Funciones básicas en ángulos comunes
        double[] grados = {0, 30, 45, 60, 90, 180, 270, 360};
        System.out.printf("%n%-6s %-12s %-12s %-12s%n", "grados", "sin", "cos", "tan");
        System.out.println("-".repeat(44));
        for (double g : grados) {
            double r = Math.toRadians(g);
            double tan = Math.tan(r);
            System.out.printf("%-6.0f %-12.6f %-12.6f %-12s%n",
                    g,
                    Math.sin(r),
                    Math.cos(r),
                    (Math.abs(tan) > 1e10 ? "∞" : String.format("%.6f", tan)));
        }

        // Funciones inversas (devuelven radianes)
        System.out.println("\nasin(1.0) en grados  = " + Math.toDegrees(Math.asin(1.0))); // 90
        System.out.println("acos(1.0) en grados  = " + Math.toDegrees(Math.acos(1.0))); // 0
        System.out.println("atan(1.0) en grados  = " + Math.toDegrees(Math.atan(1.0))); // 45

        // atan2(y, x) — ángulo del vector (x,y) respecto al eje X [-π, π]
        System.out.println("\natan2(1, 1) grados   = " + Math.toDegrees(Math.atan2(1, 1)));   // 45
        System.out.println("atan2(1, -1) grados  = " + Math.toDegrees(Math.atan2(1, -1)));  // 135
        System.out.println("atan2(-1, 0) grados  = " + Math.toDegrees(Math.atan2(-1, 0)));  // -90

        // Hiperbólicas
        System.out.println("\nsinh(1)  = " + Math.sinh(1));
        System.out.println("cosh(0)  = " + Math.cosh(0));  // 1.0
        System.out.println("tanh(1)  = " + Math.tanh(1));

        // ================================================================
        // I. HIPOTENUSA — hypot()
        // ================================================================
        System.out.println("\n========== I. hypot() ==========");
        // hypot(x, y) = sqrt(x² + y²) — sin overflow/underflow intermedios
        System.out.println("hypot(3, 4)    = " + Math.hypot(3, 4));   // 5.0
        System.out.println("hypot(5, 12)   = " + Math.hypot(5, 12));  // 13.0
        System.out.println("hypot(8, 15)   = " + Math.hypot(8, 15));  // 17.0

        // Distancia entre dos puntos
        double x1 = 0, y1 = 0, x2 = 3, y2 = 4;
        double distancia = Math.hypot(x2 - x1, y2 - y1);
        System.out.println("Distancia (0,0)→(3,4) = " + distancia); // 5.0

        // ================================================================
        // J. SIGNO — signum(), copySign()
        // ================================================================
        System.out.println("\n========== J. signum() y copySign() ==========");
        System.out.println("signum(-5.0)   = " + Math.signum(-5.0));  // -1.0
        System.out.println("signum(0.0)    = " + Math.signum(0.0));   //  0.0
        System.out.println("signum(5.0)    = " + Math.signum(5.0));   //  1.0

        // copySign(magnitud, signo) — aplica el signo del segundo al primero
        System.out.println("copySign(3,-2) = " + Math.copySign(3.0, -2.0));  // -3.0
        System.out.println("copySign(-3,2) = " + Math.copySign(-3.0, 2.0)); //  3.0

        // ================================================================
        // K. NÚMEROS ALEATORIOS — random()
        // ================================================================
        System.out.println("\n========== K. random() ==========");
        // Devuelve double en [0.0, 1.0)
        System.out.println("random()           = " + Math.random());

        // Entero aleatorio en [min, max] inclusive
        int min = 1, max = 10;
        int aleatorio = (int)(Math.random() * (max - min + 1)) + min;
        System.out.println("Entero [1,10]      = " + aleatorio);

        // Entero aleatorio en [0, n)
        int n = 6; // como un dado
        int dado = (int)(Math.random() * n) + 1;
        System.out.println("Tirada de dado     = " + dado);

        // Simular 10 lanzamientos de moneda
        System.out.print("10 monedas         : ");
        for (int i = 0; i < 10; i++) {
            System.out.print(Math.random() < 0.5 ? "C " : "X ");
        }
        System.out.println();

        // Para más control usar java.util.Random o SecureRandom
        System.out.println("(Para más control: java.util.Random o SecureRandom)");

        // ================================================================
        // L. OPERACIONES EXACTAS — addExact, subtractExact, multiplyExact…
        //    Lanzan ArithmeticException en lugar de hacer overflow silencioso
        // ================================================================
        System.out.println("\n========== L. Operaciones exactas (Java 8+) ==========");

        System.out.println("addExact(100, 200)      = " + Math.addExact(100, 200));
        System.out.println("subtractExact(100, 30)  = " + Math.subtractExact(100, 30));
        System.out.println("multiplyExact(100, 200) = " + Math.multiplyExact(100, 200));
        System.out.println("incrementExact(99)      = " + Math.incrementExact(99));
        System.out.println("decrementExact(99)      = " + Math.decrementExact(99));
        System.out.println("negateExact(-5)         = " + Math.negateExact(-5));
        System.out.println("toIntExact(100L)        = " + Math.toIntExact(100L));

        // Detectar overflow
        try {
            int overflow = Math.addExact(Integer.MAX_VALUE, 1);
        } catch (ArithmeticException e) {
            System.out.println("addExact overflow → " + e.getMessage());
        }

        try {
            int overflow = Math.multiplyExact(Integer.MAX_VALUE, 2);
        } catch (ArithmeticException e) {
            System.out.println("multiplyExact overflow → " + e.getMessage());
        }

        try {
            int overflow = Math.toIntExact(Long.MAX_VALUE);
        } catch (ArithmeticException e) {
            System.out.println("toIntExact overflow → " + e.getMessage());
        }

        // ================================================================
        // M. FLOOR/CEIL DIVISION Y MÓDULO — floorDiv, floorMod, ceilDiv
        // ================================================================
        System.out.println("\n========== M. floorDiv, floorMod, ceilDiv (Java 8/18+) ==========");

        // División entera normal: trunca hacia cero
        System.out.println("7 / 2            = " + (7 / 2));            //  3
        System.out.println("-7 / 2           = " + (-7 / 2));           // -3 (trunca hacia 0)

        // floorDiv: trunca hacia -∞
        System.out.println("floorDiv(7,2)    = " + Math.floorDiv(7, 2));   //  3
        System.out.println("floorDiv(-7,2)   = " + Math.floorDiv(-7, 2));  // -4 (hacia -∞)

        // Módulo normal: signo del dividendo
        System.out.println("\n-7 % 2           = " + (-7 % 2));          // -1
        System.out.println("7 % -2           = " + (7 % -2));           //  1

        // floorMod: signo del divisor (resultado siempre no negativo si divisor > 0)
        System.out.println("floorMod(-7,2)   = " + Math.floorMod(-7, 2));  //  1
        System.out.println("floorMod(7,-2)   = " + Math.floorMod(7, -2));  // -1
        System.out.println("floorMod(-7,-2)  = " + Math.floorMod(-7, -2)); // -1

        // Útil para índices circulares (nunca negativo)
        int tamaño = 5;
        for (int i = -3; i <= 7; i++) {
            int idxCircular = Math.floorMod(i, tamaño);
            System.out.print("floorMod(" + i + "," + tamaño + ")=" + idxCircular + "  ");
        }
        System.out.println();

        // ceilDiv (Java 18+): división hacia +∞
        // System.out.println("ceilDiv(7,2)     = " + Math.ceilDiv(7, 2));  // 4

        // ================================================================
        // N. CASOS DE USO PRÁCTICOS
        // ================================================================
        System.out.println("\n========== N. Casos de uso ==========");

        // 1. Redondear a N decimales
        double precio = 3.14159265;
        int decimales = 2;
        double redondeado = Math.round(precio * Math.pow(10, decimales)) / Math.pow(10, decimales);
        System.out.println("Redondear 3.14159 a 2 dec: " + redondeado);

        // 2. ¿Es potencia de 2?
        for (int v : new int[]{1, 2, 3, 4, 8, 9, 16, 17}) {
            boolean esPot2 = v > 0 && (v & (v - 1)) == 0;
            System.out.println(v + " es potencia de 2: " + esPot2);
        }

        // 3. Normalizar ángulo a [0, 360)
        double[] angulos = {-90, 370, 720, -450};
        for (double ang : angulos) {
            double normalizado = ((ang % 360) + 360) % 360;
            System.out.println("Normalizar " + ang + "° → " + normalizado + "°");
        }

        // 4. Interpolación lineal (lerp)
        double a = 0, b = 100, t = 0.3; // t en [0,1]
        double lerp = a + (b - a) * t;
        System.out.println("\nLerp(0,100,0.3) = " + lerp); // 30.0

        // 5. Distancia Manhattan vs Euclídea
        double ax = 1, ay = 1, bx = 4, by = 5;
        double euclid   = Math.hypot(bx - ax, by - ay);
        double manhattan = Math.abs(bx - ax) + Math.abs(by - ay);
        System.out.println("\nDistancia euclídea  : " + euclid);
        System.out.println("Distancia manhattan : " + manhattan);
    }
}