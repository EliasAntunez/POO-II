package externo;

public class DatosEnvio {
    private String direccion;
    private int cpDestino;
    private String idPedido;
    public DatosEnvio(String direccion, int cpDestino, String idPedido) {
        this.direccion = direccion; this.cpDestino = cpDestino; this.idPedido = idPedido;
    }
    // getters...
   public String getDireccion() {
       return direccion;
   }

   public int getCpDestino() {
       return cpDestino;
   }

   public String getIdPedido() {
       return idPedido;
   }
}
