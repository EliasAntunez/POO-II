package main.java.reportes.factory;
import main.java.reportes.renderizador.*;

// ===============================================
// PATRÓN FACTORY METHOD: Crear renderizadores sin acoplar al cliente con clases concretas
// ===============================================

public class RenderizadorFactory {
    /**
     * Método Factory que crea el renderizador apropiado según el tipo
     * @param tipo Tipo de reporte a crear
     * @return Implementación concreta de Renderizador
     * @throws IllegalArgumentException si el tipo no es soportado
     */
    public static Renderizador crearRenderizador(TipoReporte tipo) {
        switch (tipo) {
            case PDF:
                return new RenderizadorPDF();
            case EXCEL:
                return new RenderizadorExcel();
            case CSV:
                return new RenderizadorCSV();
            default:
                throw new IllegalArgumentException("Tipo de reporte no soportado: " + tipo);
        }
    }
}
