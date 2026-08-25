package tipos_variables;

public class TiposPrimitivos {
    public static void main(String[] args) {
                // ----------------------------------------------------------------
        // 1. byte
        //    - Tamaño: 8 bits (1 byte)
        //    - Rango: -128 a 127
        //    - Útil para ahorrar memoria en arrays grandes
        // ----------------------------------------------------------------
        byte numeroByte = 100;
        byte byteMinimo  = -128;
        byte byteMaximo  =  127;
        System.out.println("=== byte ===");
        System.out.println("Valor     : " + numeroByte);
        System.out.println("Mínimo    : " + byteMinimo);
        System.out.println("Máximo    : " + byteMaximo);
        System.out.println("Tamaño    : " + Byte.SIZE + " bits");
 
        // ----------------------------------------------------------------
        // 2. short
        //    - Tamaño: 16 bits (2 bytes)
        //    - Rango: -32.768 a 32.767
        // ----------------------------------------------------------------
        short numeroShort = 30000;
        short shortMinimo = Short.MIN_VALUE; // -32768
        short shortMaximo = Short.MAX_VALUE; //  32767
        System.out.println("\n=== short ===");
        System.out.println("Valor     : " + numeroShort);
        System.out.println("Mínimo    : " + shortMinimo);
        System.out.println("Máximo    : " + shortMaximo);
        System.out.println("Tamaño    : " + Short.SIZE + " bits");
 
        // ----------------------------------------------------------------
        // 3. int
        //    - Tamaño: 32 bits (4 bytes)
        //    - Rango: -2.147.483.648 a 2.147.483.647
        //    - Es el tipo entero más usado en Java
        // ----------------------------------------------------------------
        int numeroInt  = 2_000_000;   // Los guiones bajos mejoran la legibilidad
        int intMinimo  = Integer.MIN_VALUE;
        int intMaximo  = Integer.MAX_VALUE;
        System.out.println("\n=== int ===");
        System.out.println("Valor     : " + numeroInt);
        System.out.println("Mínimo    : " + intMinimo);
        System.out.println("Máximo    : " + intMaximo);
        System.out.println("Tamaño    : " + Integer.SIZE + " bits");
 
        // ----------------------------------------------------------------
        // 4. long
        //    - Tamaño: 64 bits (8 bytes)
        //    - Rango: -9.223.372.036.854.775.808 a 9.223.372.036.854.775.807
        //    - Se añade la letra L al final del literal
        // ----------------------------------------------------------------
        long numeroLong = 9_000_000_000L;
        long longMinimo = Long.MIN_VALUE;
        long longMaximo = Long.MAX_VALUE;
        System.out.println("\n=== long ===");
        System.out.println("Valor     : " + numeroLong);
        System.out.println("Mínimo    : " + longMinimo);
        System.out.println("Máximo    : " + longMaximo);
        System.out.println("Tamaño    : " + Long.SIZE + " bits");
 
        // ----------------------------------------------------------------
        // 5. float
        //    - Tamaño: 32 bits (4 bytes), coma flotante de precisión simple
        //    - Se añade la letra f al final del literal
        //    - Precisión: ~7 dígitos decimales
        // ----------------------------------------------------------------
        float numeroFloat  = 3.14f;
        float floatMinimo  = Float.MIN_VALUE;  // Valor positivo mínimo
        float floatMaximo  = Float.MAX_VALUE;
        System.out.println("\n=== float ===");
        System.out.println("Valor     : " + numeroFloat);
        System.out.println("Mínimo +  : " + floatMinimo);
        System.out.println("Máximo    : " + floatMaximo);
        System.out.println("Tamaño    : " + Float.SIZE + " bits");
 
        // ----------------------------------------------------------------
        // 6. double
        //    - Tamaño: 64 bits (8 bytes), coma flotante de doble precisión
        //    - Es el tipo decimal por defecto en Java
        //    - Precisión: ~15-16 dígitos decimales
        // ----------------------------------------------------------------
        double numeroDouble = 3.141592653589793;
        double doubleMinimo = Double.MIN_VALUE;
        double doubleMaximo = Double.MAX_VALUE;
        System.out.println("\n=== double ===");
        System.out.println("Valor     : " + numeroDouble);
        System.out.println("Mínimo +  : " + doubleMinimo);
        System.out.println("Máximo    : " + doubleMaximo);
        System.out.println("Tamaño    : " + Double.SIZE + " bits");
 
        // ----------------------------------------------------------------
        // 7. char
        //    - Tamaño: 16 bits (2 bytes), Unicode UTF-16
        //    - Rango: '\u0000' (0) a '\uffff' (65.535)
        //    - Se declara con comillas simples
        // ----------------------------------------------------------------
        char letra        = 'A';
        char unicode      = '\u0041';  // También es 'A'
        char charMinimo   = Character.MIN_VALUE;
        char charMaximo   = Character.MAX_VALUE;
        System.out.println("\n=== char ===");
        System.out.println("Valor     : " + letra);
        System.out.println("Unicode   : " + unicode);
        System.out.println("Como int  : " + (int) letra);   // Casting a int = 65
        System.out.println("Mínimo    : " + (int) charMinimo);
        System.out.println("Máximo    : " + (int) charMaximo);
        System.out.println("Tamaño    : " + Character.SIZE + " bits");
 
        // ----------------------------------------------------------------
        // 8. boolean
        //    - Solo puede ser true o false
        //    - Tamaño no definido exactamente por la JVM (depende de la impl.)
        //    - Usado para condiciones y control de flujo
        // ----------------------------------------------------------------
        boolean verdadero = true;
        boolean falso     = false;
        System.out.println("\n=== boolean ===");
        System.out.println("Verdadero : " + verdadero);
        System.out.println("Falso     : " + falso);
 
        // ----------------------------------------------------------------
        // TABLA RESUMEN
        // ----------------------------------------------------------------
        System.out.println("\n========== RESUMEN TIPOS PRIMITIVOS ==========");
        System.out.printf("%-10s %-8s %-25s %-25s%n", "Tipo", "Bits", "Mínimo", "Máximo");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-10s %-8d %-25d %-25d%n", "byte",   8,  Byte.MIN_VALUE,    Byte.MAX_VALUE);
        System.out.printf("%-10s %-8d %-25d %-25d%n", "short", 16,  Short.MIN_VALUE,   Short.MAX_VALUE);
        System.out.printf("%-10s %-8d %-25d %-25d%n", "int",   32,  Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("%-10s %-8d %-25d %-25d%n", "long",  64,  Long.MIN_VALUE,    Long.MAX_VALUE);
        System.out.printf("%-10s %-8s %-25s %-25s%n", "float",  32, Float.MIN_VALUE+"",  Float.MAX_VALUE+"");
        System.out.printf("%-10s %-8s %-25s %-25s%n", "double", 64, Double.MIN_VALUE+"", Double.MAX_VALUE+"");
        System.out.printf("%-10s %-8d %-25s %-25s%n", "char",  16, "\\u0000 (0)", "\\uffff (65535)");
        System.out.printf("%-10s %-8s %-25s %-25s%n", "boolean","?", "false", "true");
    

    }
}
