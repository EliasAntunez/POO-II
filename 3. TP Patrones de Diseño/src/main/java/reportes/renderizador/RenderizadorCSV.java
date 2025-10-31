package main.java.reportes.renderizador;
import main.java.reportes.reporte.Reporte;
import main.java.reportes.config.GestorConfiguracion;

/**
 * Implementación concreta: Renderizador CSV
 */

public class RenderizadorCSV implements Renderizador {
    @Override
    public void renderizar(Reporte reporte) {
        GestorConfiguracion config = GestorConfiguracion.getInstance();
        System.out.println("=== RENDERIZANDO REPORTE CSV ===");
        System.out.println("Guardando en: " + config.getPathReportes());
        System.out.println("Título: " + reporte.getTitulo());
        System.out.println("Formato: CSV delimitado por comas");
        System.out.println("Encoding: UTF-8");
        System.out.println("================================\n");
    }
    
    @Override
    public String obtenerExtension() {
        return ".csv";
    }
}
