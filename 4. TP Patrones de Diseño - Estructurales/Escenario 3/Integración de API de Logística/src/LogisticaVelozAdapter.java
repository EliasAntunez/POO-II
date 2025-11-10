import externo.ApiLogisticaVeloz;
import externo.Cotizacion;
import externo.DatosEnvio;

public class LogisticaVelozAdapter implements IServicioEnvio {
    private ApiLogisticaVeloz api;

    public LogisticaVelozAdapter() {
        this.api = new ApiLogisticaVeloz();
    }

    private int parseCodigoPostal(String codigoPostal) {
        // Estrategia simple: quitar espacios y parsear. Podés adaptar según reglas reales.
        if (codigoPostal == null) throw new IllegalArgumentException("Código postal nulo");
        String limpio = codigoPostal.replaceAll("\\D+", ""); // conservar sólo dígitos
        if (limpio.isEmpty()) throw new IllegalArgumentException("Código postal inválido: " + codigoPostal);
        try {
            return Integer.parseInt(limpio);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Código postal no convertible a entero: " + codigoPostal, e);
        }
    }

    @Override
    public float calcularCosto(String codigoPostal) {
        int cp = parseCodigoPostal(codigoPostal);
        Cotizacion cot = api.cotizarEnvio(cp);
        return cot.getCosto();
    }

    @Override
    public String obtenerTiempoEstimado(String codigoPostal) {
        int cp = parseCodigoPostal(codigoPostal);
        Cotizacion cot = api.cotizarEnvio(cp);
        int dias = cot.getDias();
        return dias + " días";
    }

    @Override
    public String despacharPedido(String direccion, String codigoPostal, String idPedido) {
        int cp = parseCodigoPostal(codigoPostal);
        DatosEnvio datos = new DatosEnvio(direccion, cp, idPedido);
        String tracking = api.enviarPaquete(datos);
        return tracking;
    }
}
