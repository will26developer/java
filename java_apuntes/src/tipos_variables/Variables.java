package tipos_variables;

public class Variables {
    // ====================================================================
    // VARIABLES DE CLASE (CAMPOS ESTÁTICOS)
    // Pertenecen a la clase, no a ningún objeto concreto.
    // Existen durante toda la vida del programa.
    // ====================================================================
    static int contadorGlobal = 0;
    static final double PI = 3.141592653589793; // constante (final)
 
    // ====================================================================
    // VARIABLES DE INSTANCIA (CAMPOS DE OBJETO)
    // Pertenecen a cada objeto creado de esta clase.
    // ====================================================================
    int id;
    String nombre;
 
    // Constructor para inicializar las variables de instancia
    public Variables(int id, String nombre) {
        this.id     = id;
        this.nombre = nombre;
        contadorGlobal++;
    }
 
    // ====================================================================
    // MÉTODO MAIN — aquí se demuestran los tipos de variables locales
    // ====================================================================
    public static void main(String[] args) {
 
        // ----------------------------------------------------------------
        // 1. DECLARACIÓN SIN INICIALIZACIÓN
        //    La variable existe pero aún no tiene valor asignado.
        //    No se puede leer hasta asignarle un valor.
        // ----------------------------------------------------------------
        int edadSinValor;
        // System.out.println(edadSinValor); // ERROR de compilación
        edadSinValor = 25; // ahora sí se puede usar
        System.out.println("Edad: " + edadSinValor);
 
        // ----------------------------------------------------------------
        // 2. DECLARACIÓN CON INICIALIZACIÓN
        //    Se declara y se asigna en la misma línea.
        // ----------------------------------------------------------------
        int     anio         = 2024;
        double  precio       = 19.99;
        char    inicial      = 'W';
        boolean activo       = true;
        String  saludo       = "Hola, mundo"; // String es objeto, no primitivo
        System.out.println("\n=== Variables inicializadas ===");
        System.out.println("Año     : " + anio);
        System.out.println("Precio  : " + precio);
        System.out.println("Inicial : " + inicial);
        System.out.println("Activo  : " + activo);
        System.out.println("Saludo  : " + saludo);
 
        // ----------------------------------------------------------------
        // 3. INFERENCIA DE TIPO CON var (Java 10+)
        //    El compilador deduce el tipo a partir del valor asignado.
        //    Solo disponible en variables locales.
        // ----------------------------------------------------------------
        var numeroVar  = 42;           // el compilador infiere int
        var textoVar   = "Java 10+";   // el compilador infiere String
        var decimalVar = 3.14;         // el compilador infiere double
        System.out.println("\n=== var (inferencia de tipo) ===");
        System.out.println("numeroVar  : " + numeroVar  + " [int]");
        System.out.println("textoVar   : " + textoVar   + " [String]");
        System.out.println("decimalVar : " + decimalVar + " [double]");
 
        // ----------------------------------------------------------------
        // 4. CONSTANTES (final)
        //    Su valor no puede cambiar una vez asignado.
        //    Convención: MAYÚSCULAS_CON_GUIONES_BAJOS
        // ----------------------------------------------------------------
        final int MAX_INTENTOS = 3;
        final String NOMBRE_APP = "MiApp";
        // MAX_INTENTOS = 5; // ERROR de compilación: no se puede reasignar
        System.out.println("\n=== Constantes (final) ===");
        System.out.println("MAX_INTENTOS : " + MAX_INTENTOS);
        System.out.println("NOMBRE_APP   : " + NOMBRE_APP);
        System.out.println("PI (clase)   : " + PI);
 
        // ----------------------------------------------------------------
        // 5. MÚLTIPLES VARIABLES DEL MISMO TIPO EN UNA LÍNEA
        //    Válido pero puede reducir legibilidad; úsalo con moderación.
        // ----------------------------------------------------------------
        int x = 1, y = 2, z = 3;
        System.out.println("\n=== Múltiples variables en una línea ===");
        System.out.println("x=" + x + ", y=" + y + ", z=" + z);
 
        // ----------------------------------------------------------------
        // 6. REASIGNACIÓN DE VARIABLES
        //    Una variable (no final) puede cambiar su valor en cualquier
        //    momento, pero el tipo siempre se mantiene.
        // ----------------------------------------------------------------
        int puntuacion = 0;
        System.out.println("\n=== Reasignación ===");
        System.out.println("Puntuación inicial: " + puntuacion);
        puntuacion = 10;
        System.out.println("Después de +10    : " + puntuacion);
        puntuacion += 5; // equivale a: puntuacion = puntuacion + 5
        System.out.println("Después de +=5    : " + puntuacion);
 
        // ----------------------------------------------------------------
        // 7. ÁMBITO (SCOPE) DE VARIABLES
        //    Una variable solo es accesible dentro del bloque {} donde
        //    fue declarada.
        // ----------------------------------------------------------------
        System.out.println("\n=== Ámbito (scope) ===");
        {
            int variableInterna = 99; // solo existe dentro de este bloque
            System.out.println("Dentro del bloque: " + variableInterna);
        }
        // System.out.println(variableInterna); // ERROR: fuera de ámbito
 
        // Ámbito en bucle
        for (int i = 0; i < 3; i++) {
            int cuadrado = i * i; // 'i' y 'cuadrado' solo existen aquí
            System.out.println("i=" + i + ", i²=" + cuadrado);
        }
        // System.out.println(i); // ERROR: 'i' no existe fuera del for
 
        // ----------------------------------------------------------------
        // 8. VARIABLES DE INSTANCIA en acción
        // ----------------------------------------------------------------
        System.out.println("\n=== Variables de instancia ===");
        Variables obj1 = new Variables(1, "Alice");
        Variables obj2 = new Variables(2, "Bob");
        System.out.println("Objeto 1: id=" + obj1.id + ", nombre=" + obj1.nombre);
        System.out.println("Objeto 2: id=" + obj2.id + ", nombre=" + obj2.nombre);
        System.out.println("Contador global (estático): " + contadorGlobal);
 
        // ----------------------------------------------------------------
        // 9. CONVERSIÓN DE TIPOS (CASTING)
        //    Widening (implícito): de tipo menor a mayor, sin pérdida.
        //    Narrowing (explícito): de tipo mayor a menor, puede perder datos.
        // ----------------------------------------------------------------
        System.out.println("\n=== Casting ===");
        int    entero  = 150;
        double doble   = entero;          // widening: int → double (automático)
        System.out.println("int → double  : " + doble);
 
        double decimal = 9.99;
        int    truncado = (int) decimal;  // narrowing: double → int (explícito, pierde decimales)
        System.out.println("double → int  : " + truncado + " (se trunca 9.99 → 9)");
 
        // ----------------------------------------------------------------
        // CONVENCIONES DE NOMBRADO
        // ----------------------------------------------------------------
        System.out.println("\n=== Convenciones ===");
        System.out.println("Variables y métodos : camelCase     → miVariable, calcularTotal()");
        System.out.println("Clases              : PascalCase    → MiClase, ListaDeCompras");
        System.out.println("Constantes          : UPPER_SNAKE   → MAX_SIZE, PI");
        System.out.println("Paquetes            : minúsculas    → com.empresa.proyecto");
    }

}
