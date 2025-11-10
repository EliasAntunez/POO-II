package externo;

// --- STUBS que simulan la librería externa ---

public class ApiLogisticaVeloz {
    public Cotizacion cotizarEnvio(int cpDestino) {
        return new Cotizacion(150.50f, 2);
    }
    public String enviarPaquete(DatosEnvio datos) {
        return "LV-987654321";
    }
}
