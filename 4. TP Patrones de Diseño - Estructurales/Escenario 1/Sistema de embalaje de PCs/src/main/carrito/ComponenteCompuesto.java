package main.carrito;
import java.util.ArrayList;
import java.util.List;

// Composite
public class ComponenteCompuesto implements Componente {
    private final String descripcion;
    private final float precioBase;
    private final List<Componente> componentes = new ArrayList<>();

    public ComponenteCompuesto(String descripcion, float precioBase) {
        this.descripcion = descripcion;
        this.precioBase = precioBase;
    }

    public void agregar(Componente c) {
        componentes.add(c);
    }

    @Override
    public float getPrecio() {
        float total = precioBase;
        for (Componente c : componentes) total += c.getPrecio();
        return total;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }
}
