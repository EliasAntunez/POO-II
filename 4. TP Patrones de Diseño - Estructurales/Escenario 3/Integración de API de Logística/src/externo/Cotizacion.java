package externo;

public class Cotizacion {
    private float costo;
    private int dias;
    public Cotizacion(float costo, int dias) { this.costo = costo; this.dias = dias; }
    public float getCosto() { return costo; }
    public int getDias() { return dias; }
}