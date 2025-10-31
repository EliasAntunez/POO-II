package main.java.reportes.config;

// ===============================================
// PATRÓN SINGLETON: Garantizar una única instancia de configuración global
// ===============================================

public class GestorConfiguracion {
    private static GestorConfiguracion instancia;
    
    // Configuraciones globales
    private String urlBd;
    private String userBd;
    private String pathReportes;
    
    // Constructor privado para evitar instanciación externa
    private GestorConfiguracion() {
        // Valores por defecto
        this.urlBd = "jdbc:postgresql://localhost:5432/reportes_db";
        this.userBd = "admin";
        this.pathReportes = "/var/reportes/output";
    }
    
    // Método sincronizado para obtener la única instancia
    public static synchronized GestorConfiguracion getInstance() {
        if (instancia == null) {
            instancia = new GestorConfiguracion();
        }
        return instancia;
    }
    
    // Getters y Setters
    public String getUrlBd() {
        return urlBd;
    }
    
    public void setUrlBd(String urlBd) {
        this.urlBd = urlBd;
    }
    
    public String getUserBd() {
        return userBd;
    }
    
    public void setUserBd(String userBd) {
        this.userBd = userBd;
    }
    
    public String getPathReportes() {
        return pathReportes;
    }
    
    public void setPathReportes(String pathReportes) {
        this.pathReportes = pathReportes;
    }
    
    @Override
    public String toString() {
        return "GestorConfiguracion{" +
                "urlBd='" + urlBd + '\'' +
                ", userBd='" + userBd + '\'' +
                ", pathReportes='" + pathReportes + '\'' +
                '}';
    }
}
