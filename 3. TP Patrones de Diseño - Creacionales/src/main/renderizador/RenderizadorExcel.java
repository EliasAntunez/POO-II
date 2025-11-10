package main.renderizador;
import main.config.GestorConfiguracion;
import main.reporte.Reporte;

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
