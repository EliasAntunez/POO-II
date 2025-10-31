package main.java.reportes.renderizador;
import main.java.reportes.reporte.Reporte;
import main.java.reportes.config.GestorConfiguracion;

/**
 * Implementación concreta: Renderizador Excel
 */

public class RenderizadorExcel implements Renderizador {
    @Override
    public void renderizar(Reporte reporte) {
        GestorConfiguracion config = GestorConfiguracion.getInstance();
        System.out.println("=== RENDERIZANDO REPORTE EXCEL ===");
        System.out.println("Guardando en: " + config.getPathReportes());
        System.out.println("Título: " + reporte.getTitulo());
        System.out.println("Formato: Excel XLSX con múltiples hojas");
        System.out.println("Generando gráficos automáticos...");
        System.out.println("==================================\n");
    }
    
    @Override
    public String obtenerExtension() {
        return ".xlsx";
    }
}
