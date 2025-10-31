package main.java.reportes.renderizador;
import main.java.reportes.reporte.Reporte;

/**
 * Interfaz Renderizador - Define el contrato para todos los renderizadores
 */

public interface Renderizador {
    void renderizar(Reporte reporte);
    String obtenerExtension();
}
