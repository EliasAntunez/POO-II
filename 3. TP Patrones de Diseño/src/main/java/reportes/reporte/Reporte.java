package main.java.reportes.reporte;
import java.time.LocalDate;

// ===============================================
// MODELO: Clase Reporte
// ===============================================

public class Reporte {
    // Atributos obligatorios
    private final String titulo;
    private final String cuerpoPrincipal;
    
    // Atributos opcionales
    private final String encabezado;
    private final String pieDePagina;
    private final LocalDate fecha;
    private final String autor;
    private final Orientacion orientacion;
    
    // Constructor privado - solo accesible desde el Builder
    private Reporte(ReporteBuilder builder) {
        this.titulo = builder.titulo;
        this.cuerpoPrincipal = builder.cuerpoPrincipal;
        this.encabezado = builder.encabezado;
        this.pieDePagina = builder.pieDePagina;
        this.fecha = builder.fecha;
        this.autor = builder.autor;
        this.orientacion = builder.orientacion;
    }
    
    // Getters
    public String getTitulo() {
        return titulo;
    }
    
    public String getCuerpoPrincipal() {
        return cuerpoPrincipal;
    }
    
    public String getEncabezado() {
        return encabezado;
    }
    
    public String getPieDePagina() {
        return pieDePagina;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public Orientacion getOrientacion() {
        return orientacion;
    }
    
    @Override
    public String toString() {
        return "Reporte{" +
                "\n  titulo='" + titulo + '\'' +
                "\n  cuerpoPrincipal='" + cuerpoPrincipal + '\'' +
                "\n  encabezado='" + encabezado + '\'' +
                "\n  pieDePagina='" + pieDePagina + '\'' +
                "\n  fecha=" + fecha +
                "\n  autor='" + autor + '\'' +
                "\n  orientacion=" + orientacion +
                "\n}";
    }
    
    // Clase Builder interna estática
    public static class ReporteBuilder {
        // Obligatorios
        private final String titulo;
        private final String cuerpoPrincipal;
        
        // Opcionales con valores por defecto
        private String encabezado = "";
        private String pieDePagina = "";
        private LocalDate fecha = LocalDate.now();
        private String autor = "Sistema";
        private Orientacion orientacion = Orientacion.VERTICAL;
        
        // Constructor con parámetros obligatorios
        public ReporteBuilder(String titulo, String cuerpoPrincipal) {
            this.titulo = titulo;
            this.cuerpoPrincipal = cuerpoPrincipal;
        }
        
        // Métodos fluidos para parámetros opcionales
        public ReporteBuilder encabezado(String encabezado) {
            this.encabezado = encabezado;
            return this;
        }
        
        public ReporteBuilder pieDePagina(String pieDePagina) {
            this.pieDePagina = pieDePagina;
            return this;
        }
        
        public ReporteBuilder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }
        
        public ReporteBuilder autor(String autor) {
            this.autor = autor;
            return this;
        }
        
        public ReporteBuilder orientacion(Orientacion orientacion) {
            this.orientacion = orientacion;
            return this;
        }
        
        // Método de construcción final
        public Reporte build() {
            return new Reporte(this);
        }
    }

}
