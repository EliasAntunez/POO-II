public class ReporteFacade {
    // Referencias a todas las clases del subsistema
    private final ConectorBD conector;
    private final LectorDeDatos lector;
    private final ServicioWebAFIP servicioAfip;
    private final ProcesadorDeImpuestos procesador;
    private final RenderizadorPDF renderizador;

    public ReporteFacade() {
        // Inicializa todos los componentes
        this.conector = new ConectorBD();
        this.lector = new LectorDeDatos();
        this.servicioAfip = new ServicioWebAFIP();
        this.procesador = new ProcesadorDeImpuestos();
        this.renderizador = new RenderizadorPDF();
    }
    /**
     * El método simple que el cliente usa.
     */
    public void generarReporteFiscal(String idCliente) {
        System.out.println("--- FACADE: Iniciando proceso de reporte ---");
        
        // Orquestación de los 6 pasos
        conector.conectar(); 
        String cuit = lector.obtenerCuit(idCliente);
        servicioAfip.autenticar(); 
        DatosFiscales datos = servicioAfip.obtenerDatosFiscales(cuit);
        MontosCalculados montos = procesador.calcularImpuestos(datos);
        renderizador.generarPDF(montos); 
        
        System.out.println("--- FACADE: Proceso finalizado. ---");
    }
}
