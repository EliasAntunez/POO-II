
public class Main {
    public static void main(String[] args) {
        EnvioFacade facade = new EnvioFacade();

        // Registrar LogisticaVeloz mediante su Adapter
        IServicioEnvio logisticaVeloz = new LogisticaVelozAdapter();
        facade.registrarProveedor("veloz", logisticaVeloz, true);

        // Uso desde el sistema
        String cp = "3370";
        float costo = facade.calcularCosto("veloz", cp);
        String tiempo = facade.obtenerTiempoEstimado("veloz", cp);
        String tracking = facade.despacharPedido("veloz", "Av. Principal 123", cp, "PED-001");

        System.out.println("Costo: " + costo);
        System.out.println("Tiempo estimado: " + tiempo);
        System.out.println("Tracking: " + tracking);
    }
}
