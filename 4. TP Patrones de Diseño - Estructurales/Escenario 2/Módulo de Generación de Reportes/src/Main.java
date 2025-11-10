// Contenido para: src/main.java

/**
 * La clase principal debe ser 'public' y llamarse 'main' 
 * para coincidir con el nombre de archivo "main.java".
 * Esta clase contiene el método main, que actúa como nuestro "Cliente".
 */
public class Main {

    public static void main(String[] args) {
        // Instancia la fachada directamente (no necesita import)
        ReporteFacade fachadaDeReportes = new ReporteFacade();

        System.out.println("CLIENTE: Solicitando reporte para Cliente-001...");
        
        // Llama al método simplificado
        fachadaDeReportes.generarReporteFiscal("Cliente-001");
    }
}
