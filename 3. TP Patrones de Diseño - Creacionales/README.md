# Sistema de Configuración y Renderizado de Reportes

## Descripción del Proyecto

Este proyecto implementa un sistema de generación de reportes aplicando tres patrones de diseño creacionales: Singleton, Factory Method y Builder. El sistema permite la creación flexible de reportes en múltiples formatos (PDF, Excel, CSV) con una arquitectura desacoplada y fácilmente extensible.

## Requerimiento 1: Motor de Renderizado

**Patrón Seleccionado:** Factory Method

**Justificación**

Se eligió el patrón Factory Method para resolver el problema de creación de renderizadores por las siguientes razones:

1. **Desacoplamiento del Cliente**

El código cliente (módulos de Finanzas, Marketing, RRHH) no necesita conocer las clases concretas _RenderizadorPDF_, _RenderizadorExcel_ o _RenderizadorCSV_. Solo interactúa con:

- La interfaz _Renderizador_
- La clase _RenderizadorFactory_
- El enum _TipoReporte_

2. **Principio Abierto/Cerrado**

El sistema está abierto a la extensión pero cerrado a la modificación:

- Para agregar un nuevo formato (ej. XML), solo se necesita:
  1. Crear una nueva clase _RenderizadorXML_ que implemente _Renderizador_
  2. Agregar XML al enum _TipoReporte_
  3. Agregar un caso en el switch de la _Factory_

NO es necesario modificar el código cliente existente en los módulos.

3. **Encapsulación de la Lógica de Creación**

Toda la lógica de instanciación está centralizada en _RenderizadorFactory_, lo que facilita:

- Mantenimiento del código
- Testing unitario
- Cambios en la lógica de creación sin afectar a los clientes

### Problemas que Evita

- **Acoplamiento directo:** Sin el patrón, cada módulo tendría código como:

```java
if (formato.equals("PDF")) {
    renderizador = new RenderizadorPDF();
} else if (formato.equals("Excel")) {
    renderizador = new RenderizadorExcel();
}
// Esto viola el principio de responsabilidad única
```

- **Código duplicado:** La lógica de creación se repetiría en múltiples lugares.
- **Dificultad para extender:** Agregar un nuevo formato requeriría modificar todos los módulos.

## Requerimiento 2: Construcción de Reportes

**Patrón Seleccionado:** Builder (Fluent Interface)

**Justificación**

Se eligió el patrón Builder para la construcción de objetos _Reporte_ por las siguientes razones:

1. **Evita el Constructor Telescópico**

Sin el patrón, necesitaríamos múltiples constructores sobrecargados:

```java
// Antipatrón: Constructor Telescópico
public Reporte(String titulo, String cuerpo) { ... }
public Reporte(String titulo, String cuerpo, String encabezado) { ... }
public Reporte(String titulo, String cuerpo, String encabezado, String pie) { ... }
// ... y así hasta 7+ variantes
```

Con Builder:

```java
// Solución elegante y legible
Reporte reporte = new Reporte.ReporteBuilder("Título", "Cuerpo")
    .encabezado("Encabezado opcional")
    .autor("Juan Pérez")
    .fecha(LocalDate.now())
    .build();
```

2. **Manejo Elegante de Parámetros Opcionales**

El patrón permite:

- Parámetros obligatorios en el constructor del Builder (_titulo_, _cuerpoPrincipal_)
- Parámetros opcionales mediante métodos fluidos con valores por defecto
- No pasar null explícitamente

3. **Código Legible y Autodocumentado**

El código se lee casi como lenguaje natural:

```java
new ReporteBuilder("Análisis Financiero", "Contenido del reporte")
    .autor("María González")
    .orientacion(Orientacion.HORIZONTAL)
    .pieDePagina("Confidencial")
    .build();
```

Cada línea indica claramente qué parámetro se está configurando.

4. **Inmutabilidad del Objeto Final**

Una vez construido el _Reporte_:

- Todos sus atributos son final
- Solo tiene getters, no setters
- Es thread-safe por naturaleza

5. **Validación Centralizada**

Se puede agregar validación en el método build():

```java
public Reporte build() {
    if (titulo == null || titulo.isEmpty()) {
        throw new IllegalStateException("El título es obligatorio");
    }
    return new Reporte(this);
}
```

### Problemas Específicos que Resuelve

- **Constructor con muchos parámetros:**

```java
// Ilegible y propenso a errores
new Reporte("Título", "Cuerpo", null, "Pie", null, "Autor", Orientacion.VERTICAL);
```

- **Confusión en el orden de parámetros:**
  - ¿Qué es el tercer parámetro?
  - ¿En qué orden van los opcionales?

- **Múltiples constructores con combinaciones explosivas:**
  - Con 5 parámetros opcionales necesitaríamos 2^5 = 32 constructores


**Con Builder:** Un solo constructor, interfaz fluida, código claro.

## Requerimiento 3: Gestor de Configuración Global

**Patrón Seleccionado:** Singleton

**Justificación**

Se eligió el patrón Singleton para _GestorConfiguracion_ por las siguientes razones:

1. **Garantía de Unicidad**

Solo debe existir una única instancia de configuración en toda la aplicación porque:

- Múltiples instancias causarían inconsistencias (cada una con valores diferentes)
- Es ineficiente cargar la configuración múltiples veces
- Los cambios en la configuración deben ser globalmente visibles

2. **Punto de Acceso Global**

Todos los módulos (Finanzas, Marketing, RRHH, sistema de reportes) necesitan acceder a la misma configuración:

```java
// Desde cualquier parte del sistema
GestorConfiguracion config = GestorConfiguracion.getInstance();
String path = config.getPathReportes();
```

3. **Implementación Thread-Safe**

Se utilizó el método sincronizado para garantizar seguridad en entornos concurrentes:

```java
public static synchronized GestorConfiguracion getInstance() {
    if (instancia == null) {
        instancia = new GestorConfiguracion();
    }
    return instancia;
}
```

Esto previene que múltiples hilos creen instancias simultáneas.

### Cómo se Garantiza la Unicidad

1. **Constructor privado:** Imposibilita la instanciación con `new` desde fuera de la clase

```java
private GestorConfiguracion() { ... }
```

2. **Atributo estático privado:** Almacena la única instancia

```java
private static GestorConfiguracion instancia;
```

3. **Método estático sincronizado:** Crea la instancia solo si no existe (Lazy Initialization)

```java
public static synchronized GestorConfiguracion getInstance()
```

4. **Inicialización perezosa:** La instancia se crea solo cuando se solicita por primera vez, ahorrando recursos

### Problemas que Evita

```java
// Cada módulo crea su propia configuración
GestorConfiguracion configFinanzas = new GestorConfiguracion();
GestorConfiguracion configMarketing = new GestorConfiguracion();
```

- **Inconsistencias de datos:** Si Finanzas cambia el _pathReportes_, Marketing no se entera
- **Desperdicio de memoria:** Múltiples objetos con la misma información


**Con Singleton:** Una sola fuente de verdad, consistencia garantizada
