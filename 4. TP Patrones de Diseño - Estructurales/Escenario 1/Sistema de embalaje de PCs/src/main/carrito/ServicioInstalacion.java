package main.carrito;

public class ServicioInstalacion extends ExtraDecorator {
    public ServicioInstalacion(Componente componente) { super(componente); }

    @Override
    public float getPrecio() {
        return componente.getPrecio() + 50;
    }

    @Override
    public String getDescripcion() {
        return componente.getDescripcion() + " + Servicio Instalación";
    }
}