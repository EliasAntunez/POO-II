
public interface IServicioEnvio {
    float calcularCosto(String codigoPostal);
    String obtenerTiempoEstimado(String codigoPostal);
    String despacharPedido(String direccion, String codigoPostal, String idPedido);
}
