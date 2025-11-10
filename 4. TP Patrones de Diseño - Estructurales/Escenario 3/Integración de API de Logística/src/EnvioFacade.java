// EnvioFacade.java (Facade para múltiples proveedores)
import java.util.Map;
import java.util.HashMap;

public class EnvioFacade {
    private Map<String, IServicioEnvio> proveedores = new HashMap<>();
    private String proveedorPorDefecto;

    public EnvioFacade() {}

    public void registrarProveedor(String id, IServicioEnvio servicio, boolean porDefecto) {
        proveedores.put(id, servicio);
        if (porDefecto || proveedorPorDefecto == null) proveedorPorDefecto = id;
    }

    public float calcularCosto(String proveedorId, String codigoPostal) {
        IServicioEnvio s = elegirProveedor(proveedorId);
        return s.calcularCosto(codigoPostal);
    }

    public String obtenerTiempoEstimado(String proveedorId, String codigoPostal) {
        IServicioEnvio s = elegirProveedor(proveedorId);
        return s.obtenerTiempoEstimado(codigoPostal);
    }

    public String despacharPedido(String proveedorId, String direccion, String codigoPostal, String idPedido) {
        IServicioEnvio s = elegirProveedor(proveedorId);
        return s.despacharPedido(direccion, codigoPostal, idPedido);
    }

    private IServicioEnvio elegirProveedor(String proveedorId) {
        String id = proveedorId != null ? proveedorId : proveedorPorDefecto;
        IServicioEnvio s = proveedores.get(id);
        if (s == null) throw new IllegalArgumentException("Proveedor no registrado: " + id);
        return s;
    }
}
