package date_calendar;

/**
 * JAVA.TIME — API MODERNA DE FECHAS (Java 8+)
 * =============================================
 * Introducida en Java 8 (JSR-310) para reemplazar Date y Calendar.
 * Inmutable, thread-safe, diseño limpio inspirado en Joda-Time.
 *
 *  LocalDate        → fecha sin hora ni zona (2024-03-15)
 *  LocalTime        → hora sin fecha ni zona (14:30:00)
 *  LocalDateTime    → fecha + hora sin zona  (2024-03-15T14:30:00)
 *  ZonedDateTime    → fecha + hora + zona    (2024-03-15T14:30:00+01:00[Europe/Madrid])
 *  Instant          → instante UTC (equivalente moderno de Date)
 *  Duration         → cantidad de tiempo en horas/minutos/segundos
 *  Period           → cantidad de tiempo en años/meses/días
 *  DateTimeFormatter→ formatear y parsear (reemplaza SimpleDateFormat)
 */
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class JavaTime {

    public static void main(String[] args) {

        // ================================================================
        // A. LocalDate — solo fecha, sin hora ni zona horaria
        // ================================================================
        System.out.println("========== A. LocalDate ==========");

        LocalDate hoy       = LocalDate.now();
        LocalDate ayer      = LocalDate.now().minusDays(1);
        LocalDate navidad   = LocalDate.of(2024, 12, 25);
        LocalDate parseada  = LocalDate.parse("2024-03-15"); // formato ISO por defecto

        System.out.println("hoy           : " + hoy);
        System.out.println("ayer          : " + ayer);
        System.out.println("navidad       : " + navidad);
        System.out.println("parseada      : " + parseada);

        // Obtener campos
        System.out.println("\ngetYear()     : " + hoy.getYear());
        System.out.println("getMonth()    : " + hoy.getMonth());        // enum Month
        System.out.println("getMonthValue(): " + hoy.getMonthValue());  // 1-12 (no 0-11!)
        System.out.println("getDayOfMonth(): " + hoy.getDayOfMonth());
        System.out.println("getDayOfWeek(): " + hoy.getDayOfWeek());    // enum DayOfWeek
        System.out.println("getDayOfYear(): " + hoy.getDayOfYear());
        System.out.println("isLeapYear()  : " + hoy.isLeapYear());
        System.out.println("lengthOfMonth(): " + hoy.lengthOfMonth());  // días del mes

        // Aritmética — todos los métodos devuelven una NUEVA instancia (inmutable)
        System.out.println("\n--- Aritmética ---");
        System.out.println("+ 10 días     : " + hoy.plusDays(10));
        System.out.println("+ 2 meses     : " + hoy.plusMonths(2));
        System.out.println("+ 1 año       : " + hoy.plusYears(1));
        System.out.println("- 1 semana    : " + hoy.minusWeeks(1));

        // Comparación
        System.out.println("\n--- Comparación ---");
        System.out.println("isAfter(ayer)  : " + hoy.isAfter(ayer));
        System.out.println("isBefore(nav.) : " + hoy.isBefore(navidad));
        System.out.println("isEqual(hoy)   : " + hoy.isEqual(LocalDate.now()));

        // ================================================================
        // B. LocalTime — solo hora, sin fecha ni zona
        // ================================================================
        System.out.println("\n========== B. LocalTime ==========");

        LocalTime ahora     = LocalTime.now();
        LocalTime mediodia  = LocalTime.of(12, 0);
        LocalTime especifica = LocalTime.of(14, 30, 45, 123_000_000); // h,m,s,nano

        System.out.println("ahora         : " + ahora);
        System.out.println("mediodía      : " + mediodia);
        System.out.println("específica    : " + especifica);

        System.out.println("getHour()     : " + ahora.getHour());
        System.out.println("getMinute()   : " + ahora.getMinute());
        System.out.println("getSecond()   : " + ahora.getSecond());

        System.out.println("+ 90 min      : " + ahora.plusMinutes(90));
        System.out.println("isBefore(12h) : " + ahora.isBefore(mediodia));

        // Constantes útiles
        System.out.println("MIN           : " + LocalTime.MIN);  // 00:00
        System.out.println("MAX           : " + LocalTime.MAX);  // 23:59:59.999999999
        System.out.println("MIDNIGHT      : " + LocalTime.MIDNIGHT);
        System.out.println("NOON          : " + LocalTime.NOON);

        // ================================================================
        // C. LocalDateTime — fecha + hora, sin zona
        // ================================================================
        System.out.println("\n========== C. LocalDateTime ==========");

        LocalDateTime ahora2    = LocalDateTime.now();
        LocalDateTime especif2  = LocalDateTime.of(2024, Month.MARCH, 15, 14, 30, 0);
        LocalDateTime combinado = LocalDateTime.of(LocalDate.of(2024, 6, 1), LocalTime.of(9, 0));

        System.out.println("ahora         : " + ahora2);
        System.out.println("específico    : " + especif2);
        System.out.println("combinado     : " + combinado);

        // Extraer partes
        System.out.println("toLocalDate() : " + ahora2.toLocalDate());
        System.out.println("toLocalTime() : " + ahora2.toLocalTime());

        // Modificar con with()
        LocalDateTime modificado = ahora2
                .withYear(2025)
                .withMonth(1)
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0);
        System.out.println("Año Nuevo 2025: " + modificado);

        // ================================================================
        // D. ZonedDateTime — fecha + hora + zona horaria
        // ================================================================
        System.out.println("\n========== D. ZonedDateTime ==========");

        ZonedDateTime madrid  = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));
        ZonedDateTime tokyo   = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime nyc     = ZonedDateTime.now(ZoneId.of("America/New_York"));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z");
        System.out.println("Madrid  : " + madrid.format(fmt));
        System.out.println("Tokyo   : " + tokyo.format(fmt));
        System.out.println("NYC     : " + nyc.format(fmt));

        // Convertir entre zonas
        ZonedDateTime madridEnTokyo = madrid.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("Madrid → Tokyo: " + madridEnTokyo.format(fmt));

        // Listar zonas disponibles (hay más de 600)
        System.out.println("Total zonas disponibles: " + ZoneId.getAvailableZoneIds().size());

        // ================================================================
        // E. Instant — instante en UTC (reemplaza Date)
        // ================================================================
        System.out.println("\n========== E. Instant ==========");

        Instant ahora3   = Instant.now();
        Instant epoch    = Instant.EPOCH;
        Instant especif3 = Instant.ofEpochMilli(1_000_000_000_000L);

        System.out.println("ahora         : " + ahora3);
        System.out.println("epoch         : " + epoch);
        System.out.println("de millis     : " + especif3);
        System.out.println("toEpochMilli(): " + ahora3.toEpochMilli());

        // Convertir Instant ↔ ZonedDateTime
        ZonedDateTime zdt = ahora3.atZone(ZoneId.of("Europe/Madrid"));
        Instant deZDT     = zdt.toInstant();
        System.out.println("Instant→ZDT   : " + zdt.format(fmt));

        // ================================================================
        // F. Period — diferencia en años/meses/días
        // ================================================================
        System.out.println("\n========== F. Period ==========");

        LocalDate nacimiento = LocalDate.of(1995, 6, 15);
        LocalDate hoy2       = LocalDate.now();
        Period edad          = Period.between(nacimiento, hoy2);

        System.out.println("Nacimiento    : " + nacimiento);
        System.out.println("Hoy           : " + hoy2);
        System.out.println("Edad          : " + edad.getYears() + " años, " +
                edad.getMonths() + " meses, " + edad.getDays() + " días");

        // Crear Period directamente
        Period tresAnios = Period.of(3, 2, 10); // 3 años, 2 meses, 10 días
        LocalDate futuro = hoy2.plus(tresAnios);
        System.out.println("+ 3a 2m 10d   : " + futuro);

        System.out.println("isNegative()  : " + Period.between(hoy2, nacimiento).isNegative());

        // ================================================================
        // G. Duration — diferencia en horas/minutos/segundos
        // ================================================================
        System.out.println("\n========== G. Duration ==========");

        LocalTime inicio  = LocalTime.of(9, 0);
        LocalTime finTrab = LocalTime.of(17, 30);
        Duration jornada  = Duration.between(inicio, finTrab);

        System.out.println("Jornada       : " + jornada);
        System.out.println("toHours()     : " + jornada.toHours());
        System.out.println("toMinutes()   : " + jornada.toMinutes());
        System.out.println("toSeconds()   : " + jornada.toSeconds());

        Duration dosHoras = Duration.ofHours(2);
        Duration tresMins = Duration.ofMinutes(3);
        System.out.println("2h + 3min     : " + dosHoras.plus(tresMins));

        // ================================================================
        // H. ChronoUnit — diferencia en una unidad concreta
        // ================================================================
        System.out.println("\n========== H. ChronoUnit ==========");

        LocalDate inicio2 = LocalDate.of(2024, 1, 1);
        LocalDate fin2    = LocalDate.of(2024, 12, 31);

        System.out.println("Días entre    : " + ChronoUnit.DAYS.between(inicio2, fin2));
        System.out.println("Semanas entre : " + ChronoUnit.WEEKS.between(inicio2, fin2));
        System.out.println("Meses entre   : " + ChronoUnit.MONTHS.between(inicio2, fin2));

        LocalDateTime ldt1 = LocalDateTime.of(2024, 1, 1, 8, 0);
        LocalDateTime ldt2 = LocalDateTime.of(2024, 1, 2, 20, 0);
        System.out.println("Horas entre   : " + ChronoUnit.HOURS.between(ldt1, ldt2));

        // ================================================================
        // I. DateTimeFormatter — formatear y parsear
        // ================================================================
        System.out.println("\n========== I. DateTimeFormatter ==========");

        LocalDateTime dt = LocalDateTime.now();

        // Formatos predefinidos
        System.out.println("ISO_DATE       : " + dt.format(DateTimeFormatter.ISO_DATE));
        System.out.println("ISO_DATE_TIME  : " + dt.format(DateTimeFormatter.ISO_DATE_TIME));

        // Formatos con patrón personalizado
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter f3 = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        DateTimeFormatter f4 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        System.out.println("dd/MM/yyyy     : " + dt.format(f1));
        System.out.println("con hora       : " + dt.format(f2));
        System.out.println("largo ES       : " + dt.format(f3));
        System.out.println("ISO manual     : " + dt.format(f4));

        // Estilos predefinidos con Locale
        DateTimeFormatter estilo = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT)
                .withLocale(new Locale("es", "ES"));
        System.out.println("FULL/SHORT ES  : " + dt.format(estilo));

        // Parsear String → LocalDate / LocalDateTime
        LocalDate   ldP = LocalDate.parse("25/12/2024", f1);
        LocalDateTime ldtP = LocalDateTime.parse("25/12/2024 10:30:00", f2);
        System.out.println("\nParseado LD    : " + ldP);
        System.out.println("Parseado LDT   : " + ldtP);

        // ================================================================
        // J. TEMPORAL ADJUSTERS — ajustes avanzados de fecha
        // ================================================================
        System.out.println("\n========== J. TemporalAdjusters ==========");

        LocalDate fecha = LocalDate.of(2024, 5, 15);
        System.out.println("Base           : " + fecha);
        System.out.println("Primer día mes : " + fecha.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("Último día mes : " + fecha.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("Primer día año : " + fecha.with(TemporalAdjusters.firstDayOfYear()));
        System.out.println("Último día año : " + fecha.with(TemporalAdjusters.lastDayOfYear()));
        System.out.println("Próx. lunes    : " + fecha.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        System.out.println("Lunes anterior : " + fecha.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));
        System.out.println("1er lunes mes  : " + fecha.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        System.out.println("Último viernes : " + fecha.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));

        // ================================================================
        // K. CONVERSIÓN LEGACY: Date ↔ java.time
        // ================================================================
        System.out.println("\n========== K. Interoperabilidad con Date/Calendar ==========");

        // java.util.Date → Instant → LocalDateTime
        java.util.Date legacyDate = new java.util.Date();
        Instant instDe     = legacyDate.toInstant();
        LocalDateTime ldtDe = LocalDateTime.ofInstant(instDe, ZoneId.systemDefault());
        System.out.println("Date→LDT       : " + ldtDe.format(f2));

        // LocalDateTime → java.util.Date
        LocalDateTime ldt3 = LocalDateTime.now();
        java.util.Date dateNew = java.util.Date.from(
                ldt3.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("LDT→Date       : " + dateNew);

        // Calendar → LocalDate
        java.util.Calendar calLeg = java.util.Calendar.getInstance();
        LocalDate ldDeCal = calLeg.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        System.out.println("Calendar→LD    : " + ldDeCal);
    }
}
