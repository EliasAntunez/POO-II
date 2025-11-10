package main.carrito;

public class GarantiaExtendida extends ExtraDecorator {
    public GarantiaExtendida(Componente componente) { super(componente); }

    @Override
    public float getPrecio() {
        return componente.getPrecio() * 1.10f;
    }

    @Override
    public String getDescripcion() {
        return componente.getDescripcion() + " + Garantía Extendida";
    }
}
