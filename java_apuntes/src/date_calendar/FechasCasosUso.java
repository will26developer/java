package date_calendar;

/**
 * FECHAS — CASOS DE USO PRÁCTICOS
 * =================================
 * Problemas reales resueltos con java.time.
 */
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FechasCasosUso {

    public static void main(String[] args) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // ================================================================
        // 1. CALCULAR EDAD
        // ================================================================
        System.out.println("========== 1. Calcular edad ==========");
        LocalDate nacimiento = LocalDate.of(1998, 3, 20);
        LocalDate hoy        = LocalDate.now();
        int edadAnios = Period.between(nacimiento, hoy).getYears();
        System.out.println("Nacimiento : " + nacimiento.format(fmt));
        System.out.println("Edad       : " + edadAnios + " años");

        // ¿Ya cumplió años este año?
        LocalDate cumpleEsteAño = nacimiento.withYear(hoy.getYear());
        boolean yaCumplio = !hoy.isBefore(cumpleEsteAño);
        System.out.println("Ya cumplió : " + yaCumplio);

        // Días para el próximo cumpleaños
        LocalDate proximoCumple = yaCumplio
                ? cumpleEsteAño.plusYears(1)
                : cumpleEsteAño;
        long diasHastaCumple = ChronoUnit.DAYS.between(hoy, proximoCumple);
        System.out.println("Próximo cumple: " + proximoCumple.format(fmt)
                + " (en " + diasHastaCumple + " días)");

        // ================================================================
        // 2. VALIDAR SI UNA FECHA ES LABORABLE
        // ================================================================
        System.out.println("\n========== 2. ¿Día laborable? ==========");
        List<LocalDate> fechas = List.of(
                LocalDate.of(2024, 5, 13),  // lunes
                LocalDate.of(2024, 5, 18),  // sábado
                LocalDate.of(2024, 5, 19),  // domingo
                LocalDate.of(2024, 12, 25)  // navidad (miércoles)
        );
        List<LocalDate> festivos = List.of(
                LocalDate.of(2024, 12, 25),
                LocalDate.of(2024, 1, 1)
        );
        for (LocalDate f : fechas) {
            System.out.println(f.format(fmt) + " (" + f.getDayOfWeek() + ")"
                    + " → laborable: " + esLaborable(f, festivos));
        }

        // ================================================================
        // 3. GENERAR RANGO DE FECHAS
        // ================================================================
        System.out.println("\n========== 3. Rango de fechas ==========");
        LocalDate desde = LocalDate.of(2024, 3, 1);
        LocalDate hasta = LocalDate.of(2024, 3, 7);
        System.out.print("Del " + desde.format(fmt) + " al " + hasta.format(fmt) + ": ");
        LocalDate cursor = desde;
        while (!cursor.isAfter(hasta)) {
            System.out.print(cursor.getDayOfMonth() + " ");
            cursor = cursor.plusDays(1);
        }
        System.out.println();

        // Solo días laborables del rango
        System.out.print("Laborables: ");
        cursor = desde;
        while (!cursor.isAfter(hasta)) {
            if (esLaborable(cursor, festivos)) System.out.print(cursor.format(fmt) + " ");
            cursor = cursor.plusDays(1);
        }
        System.out.println();

        // ================================================================
        // 4. CALCULAR FECHA DE VENCIMIENTO / PLAZO
        // ================================================================
        System.out.println("\n========== 4. Fechas de vencimiento ==========");
        LocalDate compra      = LocalDate.now();
        LocalDate garantia    = compra.plusYears(2);
        LocalDate devolucion  = compra.plusDays(30);
        LocalDate factura     = compra.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        System.out.println("Compra       : " + compra.format(fmt));
        System.out.println("Fin garantía : " + garantia.format(fmt));
        System.out.println("Devolución   : " + devolucion.format(fmt));
        System.out.println("Venc. factura: " + factura.format(fmt));

        // ================================================================
        // 5. MEDIR TIEMPO DE EJECUCIÓN
        // ================================================================
        System.out.println("\n========== 5. Medir tiempo de ejecución ==========");
        Instant t0 = Instant.now();
        long suma = 0;
        for (int i = 0; i < 1_000_000; i++) suma += i;
        Instant t1 = Instant.now();
        Duration duracion = Duration.between(t0, t1);
        System.out.println("Suma 1M iter : " + suma);
        System.out.println("Tiempo       : " + duracion.toMillis() + " ms"
                + " (" + duracion.toNanos() + " ns)");

        // ================================================================
        // 6. AGENDA — PRÓXIMAS REUNIONES
        // ================================================================
        System.out.println("\n========== 6. Próximos lunes del año ==========");
        LocalDate hoy2 = LocalDate.now();
        LocalDate lunesActual = hoy2.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println("Próximos 5 lunes:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + lunesActual.format(
                    DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy", new Locale("es","ES"))));
            lunesActual = lunesActual.plusWeeks(1);
        }

        // ================================================================
        // 7. FORMATEAR PARA DISTINTOS CONTEXTOS
        // ================================================================
        System.out.println("\n========== 7. Formatos para distintos contextos ==========");
        LocalDateTime ahora = LocalDateTime.now();

        System.out.println("Base de datos (ISO)    : " + ahora.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("API REST               : " + ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        System.out.println("Interfaz usuario (ES)  : " + ahora.format(DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy, HH:mm", new Locale("es","ES"))));
        System.out.println("Solo fecha corta       : " + ahora.format(DateTimeFormatter.ofPattern("dd/MM/yy")));
        System.out.println("Solo hora              : " + ahora.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("Nombre día y mes       : " + ahora.format(DateTimeFormatter.ofPattern("EEEE d MMMM", new Locale("es","ES"))));

        // ================================================================
        // 8. COMPARAR RANGOS (¿Se solapan dos eventos?)
        // ================================================================
        System.out.println("\n========== 8. Solapamiento de eventos ==========");
        LocalDateTime ev1Inicio = LocalDateTime.of(2024, 5, 10, 10, 0);
        LocalDateTime ev1Fin    = LocalDateTime.of(2024, 5, 10, 12, 0);
        LocalDateTime ev2Inicio = LocalDateTime.of(2024, 5, 10, 11, 0);
        LocalDateTime ev2Fin    = LocalDateTime.of(2024, 5, 10, 13, 0);
        LocalDateTime ev3Inicio = LocalDateTime.of(2024, 5, 10, 13, 0);
        LocalDateTime ev3Fin    = LocalDateTime.of(2024, 5, 10, 14, 0);

        System.out.println("Ev1(10-12) ∩ Ev2(11-13): " + seSolapan(ev1Inicio, ev1Fin, ev2Inicio, ev2Fin)); // true
        System.out.println("Ev1(10-12) ∩ Ev3(13-14): " + seSolapan(ev1Inicio, ev1Fin, ev3Inicio, ev3Fin)); // false
    }

    // ----------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // ----------------------------------------------------------------

    static boolean esLaborable(LocalDate fecha, List<LocalDate> festivos) {
        DayOfWeek dia = fecha.getDayOfWeek();
        if (dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) return false;
        if (festivos.contains(fecha)) return false;
        return true;
    }

    static boolean seSolapan(LocalDateTime ini1, LocalDateTime fin1,
                             LocalDateTime ini2, LocalDateTime fin2) {
        return ini1.isBefore(fin2) && ini2.isBefore(fin1);
    }
}