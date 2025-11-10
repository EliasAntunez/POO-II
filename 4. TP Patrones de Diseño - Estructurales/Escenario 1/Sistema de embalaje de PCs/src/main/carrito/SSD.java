package main.carrito;

public class SSD implements Componente {
    @Override
    public float getPrecio() { return 100; }
    @Override
    public String getDescripcion() { return "Disco SSD"; }
}
