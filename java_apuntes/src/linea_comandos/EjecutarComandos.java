package linea_comandos;

/**
 * PROCESSBUILDER — EJECUTAR COMANDOS DEL SO
 * ===========================================
 * Permite lanzar procesos externos (comandos del sistema operativo)
 * desde Java y controlar su entrada, salida y errores.
 *
 * CLASES PRINCIPALES:
 *   ProcessBuilder → configura y lanza el proceso
 *   Process        → representa el proceso en ejecución
 *   Runtime.exec() → forma antigua (menos flexible)
 *
 * CASOS DE USO:
 *   - Ejecutar scripts de shell / batch
 *   - Llamar a programas externos (git, ffmpeg, python…)
 *   - Automatizar tareas del sistema operativo
 */
import java.io.*;
import java.util.List;
import java.util.Map;

public class EjecutarComandos {

    // Detectar SO para usar el comando correcto
    static final boolean ES_WINDOWS = System.getProperty("os.name")
            .toLowerCase().contains("win");

    public static void main(String[] args) throws Exception {

        // ================================================================
        // A. COMANDO SIMPLE
        // ================================================================
        System.out.println("========== A. Comando simple ==========");

        // En Linux/Mac: "ls" / En Windows: "dir"
        List<String> comando = ES_WINDOWS
                ? List.of("cmd.exe", "/c", "dir")
                : List.of("ls", "-la");

        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectErrorStream(true); // combina stderr con stdout
        Process proceso = pb.start();

        // Leer la salida del proceso
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(proceso.getInputStream()))) {
            String linea;
            int maxLineas = 10; // limitamos la salida para no llenar la consola
            int count = 0;
            while ((linea = br.readLine()) != null && count++ < maxLineas) {
                System.out.println("  " + linea);
            }
        }

        int codigoSalida = proceso.waitFor(); // espera a que termine
        System.out.println("Código de salida: " + codigoSalida); // 0 = éxito

        // ================================================================
        // B. CAPTURAR SALIDA COMO STRING
        // ================================================================
        System.out.println("\n========== B. Capturar salida ==========");

        String salida = ejecutarComando(
                ES_WINDOWS ? List.of("cmd.exe", "/c", "echo Hola desde el SO")
                        : List.of("echo", "Hola desde el SO")
        );
        System.out.println("Salida: " + salida.trim());

        // Obtener nombre del host
        String hostname = ejecutarComando(
                ES_WINDOWS ? List.of("cmd.exe", "/c", "hostname")
                        : List.of("hostname")
        );
        System.out.println("Hostname: " + hostname.trim());

        // Fecha del sistema
        String fecha = ejecutarComando(
                ES_WINDOWS ? List.of("cmd.exe", "/c", "date", "/t")
                        : List.of("date")
        );
        System.out.println("Fecha SO: " + fecha.trim());

        // ================================================================
        // C. DIRECTORIO DE TRABAJO
        // ================================================================
        System.out.println("\n========== C. Directorio de trabajo ==========");

        ProcessBuilder pbDir = new ProcessBuilder(
                ES_WINDOWS ? List.of("cmd.exe", "/c", "cd")
                        : List.of("pwd")
        );
        pbDir.directory(new File(System.getProperty("user.home"))); // cambia el dir
        pbDir.redirectErrorStream(true);
        Process pDir = pbDir.start();
        String dirActual = new String(pDir.getInputStream().readAllBytes()).trim();
        System.out.println("Directorio home: " + dirActual);
        pDir.waitFor();

        // ================================================================
        // D. VARIABLES DE ENTORNO EN EL PROCESO
        // ================================================================
        System.out.println("\n========== D. Variables de entorno ==========");

        ProcessBuilder pbEnv = new ProcessBuilder(
                ES_WINDOWS ? List.of("cmd.exe", "/c", "echo %MI_VAR%")
                        : List.of("sh", "-c", "echo $MI_VAR")
        );
        // Obtener y modificar las variables de entorno del proceso hijo
        Map<String, String> env = pbEnv.environment();
        env.put("MI_VAR", "ValorPersonalizado");
        pbEnv.redirectErrorStream(true);
        Process pEnv = pbEnv.start();
        String salidaEnv = new String(pEnv.getInputStream().readAllBytes()).trim();
        System.out.println("MI_VAR en proceso hijo: " + salidaEnv);
        pEnv.waitFor();

        // ================================================================
        // E. REDIRIGIR SALIDA A UN ARCHIVO
        // ================================================================
        System.out.println("\n========== E. Redirigir a archivo ==========");

        File archivoSalida = new File("salida_proceso.txt");
        ProcessBuilder pbFile = new ProcessBuilder(
                ES_WINDOWS ? List.of("cmd.exe", "/c", "echo Guardado en archivo")
                        : List.of("echo", "Guardado en archivo")
        );
        pbFile.redirectOutput(archivoSalida);  // stdout → archivo
        pbFile.redirectErrorStream(true);
        Process pFile = pbFile.start();
        pFile.waitFor();

        // Leer el archivo que acabamos de crear
        if (archivoSalida.exists()) {
            System.out.println("Contenido del archivo:");
            try (BufferedReader br = new BufferedReader(new FileReader(archivoSalida))) {
                br.lines().forEach(l -> System.out.println("  " + l));
            }
            archivoSalida.delete(); // limpieza
        }

        // ================================================================
        // F. TIMEOUT — evitar que un proceso cuelgue para siempre
        // ================================================================
        System.out.println("\n========== F. Timeout ==========");

        ProcessBuilder pbTimeout = new ProcessBuilder(
                ES_WINDOWS ? List.of("cmd.exe", "/c", "echo rapido")
                        : List.of("echo", "rápido")
        );
        pbTimeout.redirectErrorStream(true);
        Process pTimeout = pbTimeout.start();

        // Esperar máximo 5 segundos
        boolean terminoATiempo = pTimeout.waitFor(5, java.util.concurrent.TimeUnit.SECONDS);
        if (terminoATiempo) {
            String out = new String(pTimeout.getInputStream().readAllBytes()).trim();
            System.out.println("Terminó a tiempo: " + out);
            System.out.println("Código de salida: " + pTimeout.exitValue());
        } else {
            pTimeout.destroyForcibly(); // matar proceso si no terminó
            System.out.println("Proceso superó el timeout, fue terminado.");
        }

        // ================================================================
        // G. PIPELINE — encadenar comandos (Linux/Mac)
        // ================================================================
        System.out.println("\n========== G. Pipeline de comandos ==========");

        if (!ES_WINDOWS) {
            // Equivale a: echo "Java Python TypeScript Go" | tr ' ' '\n' | sort
            ProcessBuilder pbPipeline = new ProcessBuilder(
                    "sh", "-c", "echo 'Java Python TypeScript Go' | tr ' ' '\\n' | sort"
            );
            pbPipeline.redirectErrorStream(true);
            Process pPipeline = pbPipeline.start();
            String salidaPipeline = new String(pPipeline.getInputStream().readAllBytes());
            System.out.println("Pipeline (echo | tr | sort):\n" + salidaPipeline.trim());
            pPipeline.waitFor();
        } else {
            System.out.println("(Pipeline de ejemplo solo en Linux/Mac)");
        }

        // ================================================================
        // H. Runtime.exec() — forma antigua (conocerla por código legacy)
        // ================================================================
        System.out.println("\n========== H. Runtime.exec() (legacy) ==========");
        System.out.println("Runtime.exec() es la forma antigua de lanzar procesos.");
        System.out.println("ProcessBuilder es preferible porque:");
        System.out.println("  - Permite configurar directorio de trabajo");
        System.out.println("  - Permite modificar variables de entorno");
        System.out.println("  - Mejor manejo de streams");
        System.out.println("  - No tiene problemas con espacios en argumentos");

        // Ejemplo básico con Runtime.exec()
        Process legacyProc = Runtime.getRuntime().exec(
                ES_WINDOWS ? new String[]{"cmd.exe", "/c", "echo legacy"}
                        : new String[]{"echo", "legacy"}
        );
        String legacySalida = new String(legacyProc.getInputStream().readAllBytes()).trim();
        legacyProc.waitFor();
        System.out.println("Runtime.exec() salida: " + legacySalida);

        // ================================================================
        // I. LEER STDERR Y STDOUT POR SEPARADO
        // ================================================================
        System.out.println("\n========== I. Separar stdout y stderr ==========");

        // Sin redirectErrorStream → stdout y stderr son streams separados
        ProcessBuilder pbSep = new ProcessBuilder(
                ES_WINDOWS ? List.of("cmd.exe", "/c", "echo stdout && echo stderr 1>&2")
                        : List.of("sh", "-c", "echo stdout; echo stderr >&2")
        );
        // NO llamar redirectErrorStream(true) para mantenerlos separados
        Process pSep = pbSep.start();

        String stdout = new String(pSep.getInputStream().readAllBytes()).trim();
        String stderr = new String(pSep.getErrorStream().readAllBytes()).trim();
        pSep.waitFor();

        System.out.println("STDOUT: " + stdout);
        System.out.println("STDERR: " + stderr);

        // ================================================================
        // J. BUENAS PRÁCTICAS
        // ================================================================
        System.out.println("\n========== J. Buenas prácticas ==========");
        System.out.println("1. Siempre llama a waitFor() o consume los streams para evitar bloqueos.");
        System.out.println("2. Usa redirectErrorStream(true) si no necesitas distinguir stdout/stderr.");
        System.out.println("3. Añade timeout con waitFor(n, TimeUnit) en procesos que pueden colgarse.");
        System.out.println("4. Pasa los argumentos como List<String>, no como String único con espacios.");
        System.out.println("5. Verifica el código de salida: 0 = éxito, cualquier otro = error.");
        System.out.println("6. Detecta el SO con System.getProperty(\"os.name\") para portabilidad.");
    }

    // ----------------------------------------------------------------
    // MÉTODO AUXILIAR: ejecutar comando y devolver salida como String
    // ----------------------------------------------------------------
    static String ejecutarComando(List<String> comandos) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        String salida = new String(p.getInputStream().readAllBytes());
        p.waitFor();
        return salida;
    }
}