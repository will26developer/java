package flujo_control;

/**
 * SENTENCIAS CONDICIONALES: if / else / switch
 * =============================================
 * Permiten ejecutar bloques de código distintos según una condición.
 *
 *  if (condición) { ... }
 *  if (...) { ... } else { ... }
 *  if (...) { ... } else if (...) { ... } else { ... }
 *  switch (expresión) { case valor: ... }
 *  switch expression  (Java 14+)
 */
public class Condicionales {

    public static void main(String[] args) {

        // ================================================================
        // A. IF SIMPLE
        // ================================================================
        System.out.println("========== A. if simple ==========");
        int temperatura = 35;

        if (temperatura > 30) {
            System.out.println("Hace mucho calor (" + temperatura + "°C)");
        }

        // Sin llaves: válido solo cuando hay UNA sentencia (no recomendado)
        if (temperatura > 0)
            System.out.println("No está bajo cero");

        // ================================================================
        // B. IF - ELSE
        // ================================================================
        System.out.println("\n========== B. if - else ==========");
        int hora = 14;

        if (hora < 12) {
            System.out.println("Buenos días");
        } else {
            System.out.println("Buenas tardes/noches");
        }

        // ================================================================
        // C. IF - ELSE IF - ELSE
        // ================================================================
        System.out.println("\n========== C. if - else if - else ==========");
        int nota = 73;

        if (nota >= 90) {
            System.out.println("Sobresaliente");
        } else if (nota >= 70) {
            System.out.println("Notable");
        } else if (nota >= 50) {
            System.out.println("Aprobado");
        } else {
            System.out.println("Suspenso");
        }

        // Con ternario (compacto para asignación)
        String resultado = nota >= 50 ? "Aprobado" : "Suspenso";
        System.out.println("Ternario: " + resultado);

        // ================================================================
        // D. IF ANIDADO
        // ================================================================
        System.out.println("\n========== D. if anidado ==========");
        int edad  = 20;
        boolean carnet = true;

        if (edad >= 18) {
            if (carnet) {
                System.out.println("Puede conducir");
            } else {
                System.out.println("Mayor de edad pero sin carnet");
            }
        } else {
            System.out.println("Menor de edad, no puede conducir");
        }

        // Equivalente con && (más limpio)
        if (edad >= 18 && carnet) {
            System.out.println("(con &&) Puede conducir");
        }

        // ================================================================
        // E. SWITCH CLÁSICO
        // ================================================================
        System.out.println("\n========== E. switch clásico ==========");
        int diaSemana = 3;

        switch (diaSemana) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miércoles");
                break;
            case 4:
                System.out.println("Jueves");
                break;
            case 5:
                System.out.println("Viernes");
                break;
            case 6:
            case 7:
                System.out.println("Fin de semana");   // fall-through intencional
                break;
            default:
                System.out.println("Día inválido");
        }

        // Switch con String
        String idioma = "es";
        switch (idioma) {
            case "es":
                System.out.println("Hola");
                break;
            case "en":
                System.out.println("Hello");
                break;
            case "fr":
                System.out.println("Bonjour");
                break;
            default:
                System.out.println("Idioma no reconocido");
        }

        // PELIGRO: fall-through accidental (olvidar break)
        System.out.println("\n--- Fall-through accidental ---");
        int x = 1;
        switch (x) {
            case 1:
                System.out.println("Caso 1");
                // sin break → cae al siguiente
            case 2:
                System.out.println("Caso 2 (fall-through!)");
                break;
            case 3:
                System.out.println("Caso 3");
        }

        // ================================================================
        // F. SWITCH EXPRESSION (Java 14+ estable)
        //    - Usa -> en lugar de : y break
        //    - No tiene fall-through
        //    - Puede devolver un valor
        // ================================================================
        System.out.println("\n========== F. switch expression (Java 14+) ==========");

        // Con ->  (sin fall-through, sin break)
        int dia = 6;
        String tipoDia = switch (dia) {
            case 1, 2, 3, 4, 5 -> "Día laborable";
            case 6, 7           -> "Fin de semana";
            default             -> "Inválido";
        };
        System.out.println("Día " + dia + " → " + tipoDia);

        // Con yield (cuando se necesita lógica dentro del bloque)
        int mes = 2;
        int diasDelMes = switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11            -> 30;
            case 2 -> {
                // Podría verificar año bisiesto aquí
                yield 28; // yield devuelve el valor en bloques {}
            }
            default -> throw new IllegalArgumentException("Mes inválido: " + mes);
        };
        System.out.println("Días en mes " + mes + ": " + diasDelMes);

        // Switch expression con String
        String comando = "inicio";
        String respuesta = switch (comando) {
            case "inicio"  -> "Sistema arrancado";
            case "parar"   -> "Sistema detenido";
            case "reiniciar" -> {
                System.out.println("  [reiniciando...]");
                yield "Sistema reiniciado";
            }
            default -> "Comando desconocido: " + comando;
        };
        System.out.println(respuesta);

        // ================================================================
        // G. SWITCH CON ENUMS (muy común en la práctica)
        // ================================================================
        System.out.println("\n========== G. switch con enum ==========");
        Estacion estacion = Estacion.VERANO;

        String actividad = switch (estacion) {
            case PRIMAVERA -> "Senderismo";
            case VERANO    -> "Playa";
            case OTOÑO     -> "Recolectar setas";
            case INVIERNO  -> "Esquí";
        };
        System.out.println(estacion + " → " + actividad);

        // ================================================================
        // H. BUENAS PRÁCTICAS
        // ================================================================
        System.out.println("\n========== H. Buenas prácticas ==========");
        System.out.println("1. Usa switch expression (Java 14+) cuando puedas: sin fall-through ni break.");
        System.out.println("2. Pon siempre llaves {} en if/else aunque haya una sola línea.");
        System.out.println("3. Coloca el caso más probable primero en if-else if para rendimiento.");
        System.out.println("4. Prefiere && / || a ifs anidados cuando la lógica lo permite.");
        System.out.println("5. Usa default en switch para manejar valores inesperados.");
    }

    enum Estacion { PRIMAVERA, VERANO, OTOÑO, INVIERNO }
}