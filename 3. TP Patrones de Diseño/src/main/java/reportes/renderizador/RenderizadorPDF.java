package main.java.reportes.renderizador;
import main.java.reportes.reporte.Reporte;
import main.java.reportes.config.GestorConfiguracion;

/**
 * Implementación concreta: Renderizador PDF
 */

public class RenderizadorPDF implements Renderizador {
    @Override
    public void renderizar(Reporte reporte) {
        GestorConfiguracion config = GestorConfiguracion.getInstance();
        System.out.println("=== RENDERIZANDO REPORTE PDF ===");
        System.out.println("Guardando en: " + config.getPathReportes());
        System.out.println("Título: " + reporte.getTitulo());
        System.out.println("Formato: PDF con fuentes embebidas");
        System.out.println("Orientación: " + reporte.getOrientacion());
        System.out.println("================================\n");
    }
    
    @Override
    public String obtenerExtension() {
        return ".pdf";
    }
}
