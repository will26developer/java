package operadores;
/**
 * OPERADORES RELACIONALES, LÓGICOS Y DE ASIGNACIÓN
 * ==================================================
 *
 * RELACIONALES (devuelven boolean):
 *   ==   igual a
 *   !=   distinto de
 *   >    mayor que
 *   <    menor que
 *   >=   mayor o igual
 *   <=   menor o igual
 *
 * LÓGICOS:
 *   &&   AND lógico (cortocircuito)
 *   ||   OR lógico  (cortocircuito)
 *   !    NOT lógico
 *   &    AND sin cortocircuito
 *   |    OR sin cortocircuito
 *   ^    XOR lógico
 *
 * ASIGNACIÓN:
 *   =    asignación simple
 *   +=  -=  *=  /=  %=   compuesta aritmética
 *   &=  |=  ^=  <<=  >>=  >>>=  compuesta bit a bit
 */
public class OperadoresLogicosRelacionalesAsignacion {

    public static void main(String[] args) {

        // ================================================================
        // A. OPERADORES RELACIONALES
        // ================================================================
        System.out.println("========== A. Relacionales ==========");
        int a = 10, b = 20, c = 10;

        System.out.println("a=" + a + ", b=" + b + ", c=" + c);
        System.out.println("a == c  → " + (a == c));  // true
        System.out.println("a == b  → " + (a == b));  // false
        System.out.println("a != b  → " + (a != b));  // true
        System.out.println("a > b   → " + (a > b));   // false
        System.out.println("a < b   → " + (a < b));   // true
        System.out.println("a >= c  → " + (a >= c));  // true
        System.out.println("a <= b  → " + (a <= b));  // true

        // CUIDADO: comparar objetos con == compara referencias, no contenido
        System.out.println("\n--- Comparar objetos (==) vs equals() ---");
        String s1 = new String("Java");
        String s2 = new String("Java");
        System.out.println("s1 == s2        → " + (s1 == s2));        // false (dist. ref.)
        System.out.println("s1.equals(s2)   → " + (s1.equals(s2)));   // true

        // ================================================================
        // B. OPERADORES LÓGICOS
        // ================================================================
        System.out.println("\n========== B. Lógicos ==========");
        boolean t = true, f = false;

        // AND (&&) — ambos deben ser true
        System.out.println("true  && true   → " + (t && t));  // true
        System.out.println("true  && false  → " + (t && f));  // false
        System.out.println("false && true   → " + (f && t));  // false
        System.out.println("false && false  → " + (f && f));  // false

        // OR (||) — al menos uno debe ser true
        System.out.println("\ntrue  || true   → " + (t || t)); // true
        System.out.println("true  || false  → " + (t || f)); // true
        System.out.println("false || true   → " + (f || t)); // true
        System.out.println("false || false  → " + (f || f)); // false

        // NOT (!) — invierte el valor
        System.out.println("\n!true           → " + (!t));     // false
        System.out.println("!false          → " + (!f));     // true

        // XOR (^) — true si los operandos son DISTINTOS
        System.out.println("\ntrue  ^ true    → " + (t ^ t)); // false
        System.out.println("true  ^ false   → " + (t ^ f)); // true
        System.out.println("false ^ false   → " + (f ^ f)); // false

        // ----------------------------------------------------------------
        // CORTOCIRCUITO: && y || no evalúan el segundo operando si
        // el resultado ya está determinado por el primero.
        // ----------------------------------------------------------------
        System.out.println("\n--- Cortocircuito ---");
        int contador = 0;

        // false && ... → el segundo operando NO se evalúa
        boolean r1 = false && (++contador > 0);
        System.out.println("false && (++contador>0) → contador=" + contador); // 0

        // true || ... → el segundo operando NO se evalúa
        boolean r2 = true || (++contador > 0);
        System.out.println("true  || (++contador>0) → contador=" + contador); // 0

        // Con & y | NO hay cortocircuito (siempre evalúa ambos lados)
        boolean r3 = false & (++contador > 0);
        System.out.println("false &  (++contador>0) → contador=" + contador); // 1

        boolean r4 = true | (++contador > 0);
        System.out.println("true  |  (++contador>0) → contador=" + contador); // 2

        // ----------------------------------------------------------------
        // CASO PRÁCTICO: null-safe antes de llamar a un método
        // ----------------------------------------------------------------
        System.out.println("\n--- Null-safe con cortocircuito ---");
        String texto = null;
        // Sin cortocircuito → NullPointerException
        // texto != null && texto.length() > 3  → seguro
        boolean seguro = texto != null && texto.length() > 3;
        System.out.println("texto != null && texto.length()>3 → " + seguro); // false, sin NPE

        // ================================================================
        // C. OPERADORES DE ASIGNACIÓN
        // ================================================================
        System.out.println("\n========== C. Asignación ==========");

        // Simple
        int n = 10;
        System.out.println("n = 10        → " + n);

        // Compuestos aritméticos
        n += 5;  System.out.println("n += 5        → " + n); // 15
        n -= 3;  System.out.println("n -= 3        → " + n); // 12
        n *= 2;  System.out.println("n *= 2        → " + n); // 24
        n /= 4;  System.out.println("n /= 4        → " + n); // 6
        n %= 4;  System.out.println("n %= 4        → " + n); // 2

        // Compuestos bit a bit (ver también OperadoresBit.java)
        int bits = 0b1010; // 10
        bits &= 0b1100;    System.out.println("bits &= 1100  → " + Integer.toBinaryString(bits)); // 1000
        bits = 0b1010;
        bits |= 0b0101;    System.out.println("bits |= 0101  → " + Integer.toBinaryString(bits)); // 1111
        bits = 0b1010;
        bits ^= 0b1111;    System.out.println("bits ^= 1111  → " + Integer.toBinaryString(bits)); // 0101
        bits = 8;
        bits <<= 1;        System.out.println("8 <<= 1       → " + bits); // 16
        bits >>= 2;        System.out.println("16 >>= 2      → " + bits); // 4

        // ================================================================
        // D. INSTANCEOF
        //    Comprueba si un objeto es instancia de una clase/interfaz
        // ================================================================
        System.out.println("\n========== D. instanceof ==========");
        Object obj1 = "Hola";
        Object obj2 = 42;
        Object obj3 = null;

        System.out.println("\"Hola\" instanceof String  → " + (obj1 instanceof String)); // true
        System.out.println("42    instanceof Integer  → " + (obj2 instanceof Integer)); // true
        System.out.println("\"Hola\" instanceof Integer → " + (obj1 instanceof Integer)); // false
        System.out.println("null  instanceof String   → " + (obj3 instanceof String));  // false (no NPE)

        // Pattern matching con instanceof (Java 16+)
        if (obj1 instanceof String s) {
            System.out.println("Pattern matching: longitud = " + s.length()); // no hace falta cast
        }

        // ================================================================
        // E. EXPRESIONES BOOLEANAS COMPLEJAS — EJEMPLOS PRÁCTICOS
        // ================================================================
        System.out.println("\n========== E. Expresiones complejas ==========");
        int edad    = 22;
        boolean carnet = true;

        // Puede conducir
        boolean puedeConducir = edad >= 18 && carnet;
        System.out.println("Puede conducir    → " + puedeConducir);

        // Descuento en tienda
        boolean esSocio    = false;
        boolean esVerano   = true;
        boolean hayDescuento = esSocio || esVerano;
        System.out.println("Hay descuento     → " + hayDescuento);

        // Validación de rango
        int nota = 7;
        boolean aprobado = nota >= 5 && nota <= 10;
        System.out.println("Nota " + nota + " aprobada → " + aprobado);

        // Negación de condición compuesta
        boolean sinStock = true, enReparacion = false;
        boolean disponible = !(sinStock || enReparacion);
        System.out.println("Disponible        → " + disponible);
    }
}