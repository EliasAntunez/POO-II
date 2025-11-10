public class ProcesadorDeImpuestos {
    public MontosCalculados calcularImpuestos(DatosFiscales datos) {
        System.out.println("PASO 5: Calculando impuestos basados en: " + datos.getInfo());
        // Usa la clase MontosCalculados directamente
        return new MontosCalculados(45000.50);
    }
}
