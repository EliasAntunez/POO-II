package main.carrito;

public class CPU implements Componente {
    @Override
    public float getPrecio() { return 250; }
    @Override
    public String getDescripcion() { return "CPU"; }
}