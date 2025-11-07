package main;
import java.time.LocalDate;
import main.config.GestorConfiguracion;
import main.factory.RenderizadorFactory;
import main.factory.TipoReporte;
import main.renderizador.Renderizador;
import main.reporte.Orientacion;
import main.reporte.Reporte;
import main.reporte.Reporte.ReporteBuilder;

// ===============================================
// CLASE PRINCIPAL: Main - Clase principal que demuestra el uso de los tres patrones creacionales
// ===============================================

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE REPORTES - PATRONES CREACIONALES          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        // ============================================
        // DEMOSTRACIÓN PATRÓN SINGLETON
        // ============================================
        System.out.println("1️⃣  PATRÓN SINGLETON - Gestor de Configuración");
        System.out.println("─────────────────────────────────────────────────");
        
        GestorConfiguracion config1 = GestorConfiguracion.getInstance();
        GestorConfiguracion config2 = GestorConfiguracion.getInstance();
        
        System.out.println("Configuración 1: " + config1);
        System.out.println("Configuración 2: " + config2);
        System.out.println("¿Son la misma instancia? " + (config1 == config2));
        System.out.println();
        
        // Modificar configuración
        config1.setPathReportes("/nuevo/path/reportes");
        System.out.println("Después de modificar config1:");
        System.out.println("Path en config2: " + config2.getPathReportes());
        System.out.println("✓ Unicidad garantizada\n");
        
        // // ============================================
        // // DEMOSTRACIÓN PATRÓN BUILDER
        // // ============================================
        System.out.println("2️⃣  PATRÓN BUILDER - Construcción de Reportes");
        System.out.println("─────────────────────────────────────────────────");
        
        // Reporte simple (solo obligatorios)
        Reporte reporteSimple = new ReporteBuilder(
            "Reporte de Ventas Q1",
            "Análisis de ventas del primer trimestre"
        ).build();
        
        System.out.println("Reporte Simple:");
        System.out.println(reporteSimple);
        System.out.println();
        
        // Reporte complejo (con varios opcionales)
        Reporte reporteComplejo = new ReporteBuilder(
            "Análisis Financiero Anual",
            "Detalle completo de estados financieros y proyecciones"
        )
        .encabezado("Departamento de Finanzas - Año Fiscal 2024")
        .pieDePagina("Confidencial - Solo uso interno")
        .fecha(LocalDate.of(2024, 12, 31))
        .autor("Juan Pérez")
        .orientacion(Orientacion.HORIZONTAL)
        .build();
        
        System.out.println("Reporte Complejo:");
        System.out.println(reporteComplejo);
        System.out.println("✓ Construcción fluida sin constructores telescópicos\n");
        
        // ============================================
        // DEMOSTRACIÓN PATRÓN FACTORY METHOD
        // ============================================
        System.out.println("3️⃣  PATRÓN FACTORY METHOD - Creación de Renderizadores");
        System.out.println("─────────────────────────────────────────────────");
        
        // Crear diferentes renderizadores sin acoplamiento
        Renderizador renderizadorPDF = RenderizadorFactory.crearRenderizador(TipoReporte.PDF);
        Renderizador renderizadorExcel = RenderizadorFactory.crearRenderizador(TipoReporte.EXCEL);
        
        // Renderizar el mismo reporte en diferentes formatos
        System.out.println("Renderizando en múltiples formatos:\n");
        renderizadorPDF.renderizar(reporteComplejo);
        renderizadorExcel.renderizar(reporteComplejo);
        
        System.out.println("✓ Desacoplamiento logrado - Fácil extensión\n");
        
        // ============================================
        // SIMULACIÓN DE USO REAL
        // ============================================
        System.out.println("4️⃣  SIMULACIÓN DE USO REAL");
        System.out.println("─────────────────────────────────────────────────");
        
        // Módulo de Marketing genera un reporte
        System.out.println("📊 Módulo de Marketing:");
        Reporte reporteMarketing = new ReporteBuilder(
            "Campaña Digital Q2",
            "Métricas de redes sociales y conversión"
        )
        .autor("María González - Marketing")
        .fecha(LocalDate.now())
        .build();
        
        Renderizador rendMarketing = RenderizadorFactory.crearRenderizador(TipoReporte.EXCEL);
        rendMarketing.renderizar(reporteMarketing);
        
        // Módulo de RRHH genera un reporte
        System.out.println("👥 Módulo de RRHH:");
        Reporte reporteRRHH = new ReporteBuilder(
            "Nómina Mensual",
            "Detalle de sueldos y beneficios del personal"
        )
        .autor("Carlos Ramírez - RRHH")
        .pieDePagina("Documento confidencial")
        .orientacion(Orientacion.VERTICAL)
        .build();
        
        Renderizador rendRRHH = RenderizadorFactory.crearRenderizador(TipoReporte.PDF);
        rendRRHH.renderizar(reporteRRHH);
        
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║  ✓ DEMOSTRACIÓN COMPLETADA EXITOSAMENTE              ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
    }
}
