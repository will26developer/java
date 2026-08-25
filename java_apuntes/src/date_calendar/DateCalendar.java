package date_calendar;

/**
 * DATE Y CALENDAR — API LEGACY
 * ==============================
 * java.util.Date  → representa un instante en el tiempo (milisegundos desde epoch)
 * java.util.Calendar → API para manipular fechas (año, mes, día, hora…)
 *
 * NOTA: Estas clases son anteriores a Java 8. Tienen problemas de diseño
 * conocidos (mutabilidad, meses base 0, no thread-safe…).
 * En código moderno se prefiere java.time (LocalDate, LocalDateTime…).
 * Pero es importante conocerlas porque aparecen en código legacy.
 */
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class DateCalendar {

    public static void main(String[] args) throws ParseException {

        // ================================================================
        // A. java.util.Date
        // ================================================================
        System.out.println("========== A. java.util.Date ==========");

        // Fecha y hora actuales
        Date ahora = new Date();
        System.out.println("new Date()         : " + ahora);

        // Date internamente almacena milisegundos desde el epoch
        // (1 enero 1970 00:00:00 UTC)
        long millis = ahora.getTime();
        System.out.println("getTime() (ms)     : " + millis);

        // Crear Date a partir de milisegundos
        Date epoch = new Date(0);
        System.out.println("Epoch (ms=0)       : " + epoch);

        Date fechaEspecifica = new Date(1_000_000_000_000L); // ~2001
        System.out.println("1_000_000_000_000L : " + fechaEspecifica);

        // Comparación de fechas
        Date fecha1 = new Date(1000L);
        Date fecha2 = new Date(2000L);
        System.out.println("\nfecha1.before(fecha2) : " + fecha1.before(fecha2)); // true
        System.out.println("fecha1.after(fecha2)  : " + fecha1.after(fecha2));  // false
        System.out.println("fecha1.equals(fecha2) : " + fecha1.equals(fecha2)); // false
        System.out.println("fecha1.compareTo(f2)  : " + fecha1.compareTo(fecha2)); // negativo

        // Modificar una fecha (mutar — uno de sus problemas)
        Date mutable = new Date();
        mutable.setTime(0L); // ahora apunta al epoch
        System.out.println("\nDespués de setTime(0) : " + mutable);

        // Métodos deprecados de Date (no usar, solo conocer)
        // date.getYear()  → devuelve años desde 1900 (confuso)
        // date.getMonth() → devuelve 0-11 (confuso)
        // Usa Calendar o java.time en su lugar

        // ================================================================
        // B. SimpleDateFormat — formatear y parsear fechas
        // ================================================================
        System.out.println("\n========== B. SimpleDateFormat ==========");

        Date hoy = new Date();

        // Patrones de formato
        // y=año, M=mes, d=día, H=hora(0-23), h=hora(1-12), m=min, s=seg, S=ms
        // E=día semana, MMMM=mes completo, a=AM/PM, z=zona horaria

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdf4 = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new java.util.Locale("es", "ES"));
        SimpleDateFormat sdf5 = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");

        System.out.println("dd/MM/yyyy          : " + sdf1.format(hoy));
        System.out.println("yyyy-MM-dd          : " + sdf2.format(hoy));
        System.out.println("dd/MM/yyyy HH:mm:ss : " + sdf3.format(hoy));
        System.out.println("Formato largo (ES)  : " + sdf4.format(hoy));
        System.out.println("Con AM/PM           : " + sdf5.format(hoy));

        // Parsear String → Date
        System.out.println("\n--- Parsear String a Date ---");
        String textoFecha = "25/12/2024";
        Date fechaNavidad = sdf1.parse(textoFecha);
        System.out.println("Parseada : " + fechaNavidad);
        System.out.println("Formateada: " + sdf3.format(fechaNavidad));

        // Parsear con hora
        SimpleDateFormat sdfHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date conHora = sdfHora.parse("15/03/2024 14:30:00");
        System.out.println("Con hora : " + sdfHora.format(conHora));

        // ================================================================
        // C. java.util.Calendar
        // ================================================================
        System.out.println("\n========== C. java.util.Calendar ==========");

        // Obtener instancia (por defecto con zona horaria local)
        Calendar cal = Calendar.getInstance();
        System.out.println("Calendar.getInstance() : " + cal.getTime());

        // ----------------------------------------------------------------
        // Obtener campos individuales
        // ----------------------------------------------------------------
        System.out.println("\n--- Obtener campos ---");
        int año    = cal.get(Calendar.YEAR);
        int mes    = cal.get(Calendar.MONTH);          // ¡OJO! 0=enero, 11=diciembre
        int dia    = cal.get(Calendar.DAY_OF_MONTH);
        int hora   = cal.get(Calendar.HOUR_OF_DAY);    // 0-23
        int minuto = cal.get(Calendar.MINUTE);
        int segundo= cal.get(Calendar.SECOND);
        int diaSem = cal.get(Calendar.DAY_OF_WEEK);    // 1=domingo, 7=sábado
        int semAño = cal.get(Calendar.WEEK_OF_YEAR);

        System.out.println("YEAR            : " + año);
        System.out.println("MONTH (0-11)    : " + mes + " → mes real: " + (mes + 1));
        System.out.println("DAY_OF_MONTH    : " + dia);
        System.out.println("HOUR_OF_DAY     : " + hora);
        System.out.println("MINUTE          : " + minuto);
        System.out.println("SECOND          : " + segundo);
        System.out.println("DAY_OF_WEEK     : " + diaSem + " (1=Dom, 7=Sáb)");
        System.out.println("WEEK_OF_YEAR    : " + semAño);

        // ----------------------------------------------------------------
        // Establecer campos
        // ----------------------------------------------------------------
        System.out.println("\n--- Establecer campos ---");
        Calendar navidad = Calendar.getInstance();
        navidad.set(Calendar.YEAR,         2024);
        navidad.set(Calendar.MONTH,        Calendar.DECEMBER); // constante legible
        navidad.set(Calendar.DAY_OF_MONTH, 25);
        navidad.set(Calendar.HOUR_OF_DAY,  0);
        navidad.set(Calendar.MINUTE,       0);
        navidad.set(Calendar.SECOND,       0);
        navidad.set(Calendar.MILLISECOND,  0);
        System.out.println("Navidad 2024    : " + sdf3.format(navidad.getTime()));

        // set() con año, mes, día en una sola línea
        Calendar reyes = Calendar.getInstance();
        reyes.set(2025, Calendar.JANUARY, 6); // mes con constante
        System.out.println("Reyes Magos     : " + sdf1.format(reyes.getTime()));

        // ----------------------------------------------------------------
        // Aritmética de fechas con add() y roll()
        // ----------------------------------------------------------------
        System.out.println("\n--- Aritmética de fechas ---");
        Calendar base = Calendar.getInstance();
        base.set(2024, Calendar.MARCH, 15);
        System.out.println("Base            : " + sdf1.format(base.getTime()));

        // add() — suma/resta con desbordamiento (si supera el mes, cambia de mes)
        Calendar mas30 = (Calendar) base.clone();
        mas30.add(Calendar.DAY_OF_MONTH, 30);
        System.out.println("+ 30 días       : " + sdf1.format(mas30.getTime())); // 14/04

        Calendar mas6meses = (Calendar) base.clone();
        mas6meses.add(Calendar.MONTH, 6);
        System.out.println("+ 6 meses       : " + sdf1.format(mas6meses.getTime()));

        Calendar menos1año = (Calendar) base.clone();
        menos1año.add(Calendar.YEAR, -1);
        System.out.println("- 1 año         : " + sdf1.format(menos1año.getTime()));

        // roll() — suma pero NO desborda al campo superior
        Calendar roll = (Calendar) base.clone();
        roll.set(2024, Calendar.DECEMBER, 25);
        roll.roll(Calendar.MONTH, 2); // suma 2 meses pero no cambia el año
        System.out.println("roll +2 meses   : " + sdf1.format(roll.getTime())); // febrero, mismo año

        // ----------------------------------------------------------------
        // Diferencia entre dos fechas
        // ----------------------------------------------------------------
        System.out.println("\n--- Diferencia entre fechas ---");
        Calendar inicio = Calendar.getInstance();
        inicio.set(2024, Calendar.JANUARY, 1);
        Calendar fin = Calendar.getInstance();
        fin.set(2024, Calendar.DECEMBER, 31);

        long diffMs   = fin.getTimeInMillis() - inicio.getTimeInMillis();
        long diffDias = diffMs / (1000 * 60 * 60 * 24);
        System.out.println("Días entre 01/01 y 31/12/2024: " + diffDias);

        // ----------------------------------------------------------------
        // Zonas horarias
        // ----------------------------------------------------------------
        System.out.println("\n--- Zonas horarias ---");
        Calendar madridCal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
        Calendar tokyoCal  = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        Calendar nycCal    = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));

        SimpleDateFormat sdfTZ = new SimpleDateFormat("HH:mm z");
        sdfTZ.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        System.out.println("Madrid  : " + sdfTZ.format(madridCal.getTime()));
        sdfTZ.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        System.out.println("Tokio   : " + sdfTZ.format(tokyoCal.getTime()));
        sdfTZ.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println("NYC     : " + sdfTZ.format(nycCal.getTime()));

        // ----------------------------------------------------------------
        // Convertir entre Date y Calendar
        // ----------------------------------------------------------------
        System.out.println("\n--- Convertir Date ↔ Calendar ---");
        Date dateObj = new Date();
        Calendar calObj = Calendar.getInstance();
        calObj.setTime(dateObj);              // Date → Calendar
        Date deVuelta = calObj.getTime();     // Calendar → Date
        System.out.println("Date → Calendar → Date: " + sdf3.format(deVuelta));

        // ================================================================
        // D. CONSTANTES DE CALENDAR
        // ================================================================
        System.out.println("\n========== D. Constantes útiles ==========");
        System.out.println("JANUARY   = " + Calendar.JANUARY);    // 0
        System.out.println("FEBRUARY  = " + Calendar.FEBRUARY);   // 1
        System.out.println("DECEMBER  = " + Calendar.DECEMBER);   // 11
        System.out.println("SUNDAY    = " + Calendar.SUNDAY);     // 1
        System.out.println("MONDAY    = " + Calendar.MONDAY);     // 2
        System.out.println("SATURDAY  = " + Calendar.SATURDAY);   // 7
        System.out.println("AM        = " + Calendar.AM);         // 0
        System.out.println("PM        = " + Calendar.PM);         // 1

        // ================================================================
        // E. PROBLEMAS CONOCIDOS (por qué se creó java.time)
        // ================================================================
        System.out.println("\n========== E. Problemas de la API legacy ==========");
        System.out.println("1. Date es MUTABLE → peligroso compartirlo entre objetos.");
        System.out.println("2. Los meses en Calendar son base 0 (enero=0) → errores frecuentes.");
        System.out.println("3. No thread-safe → problemas en entornos concurrentes.");
        System.out.println("4. SimpleDateFormat no es thread-safe.");
        System.out.println("5. API confusa: Date tiene métodos de Calendar y viceversa.");
        System.out.println("→ Solución: usar java.time (LocalDate, LocalDateTime, ZonedDateTime)");
    }
}