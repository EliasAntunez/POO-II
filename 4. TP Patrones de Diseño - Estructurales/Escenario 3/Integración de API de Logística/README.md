# Escenario 3: Integración de API de Logística

## Análisis del Escenario 1

### Clases principales:

- IServicioEnvio (interfaz interna que usa el resto del sistema).

### Clases externas (proporcionadas por el SDK .jar):

- ApiLogisticaVeloz

- Cotizacion (devuelta por cotizarEnvio)

- DatosEnvio (param para enviarPaquete)

### Problemas / síntomas:

Interfaces diferentes: calcularCosto(String codigoPostal) y cotizarEnvio(int cpDestino).

- Tipos distintos: String e int para código postal.

- Métodos distintos: despacharPedido(...) vs enviarPaquete(DatosEnvio).

- Acoplamiento: si el resto del sistema usa IServicioEnvio no queremos que dependa de la API concreta.

## Identificación de patrones

### Adapter. Resuelve la incompatibilidad de interfaces y tipos entre IServicioEnvio y ApiLogisticaVeloz.

Su intención es convertir la interfaz de una clase en otra que el cliente espera. Aquí el cliente (el e-commerce) espera IServicioEnvio; la librería externa tiene otra interfaz. El adaptador encapsula la conversión de tipos (String→int, construcción de DatosEnvio, etc.) y delega las llamadas.

### Facade. Permite que el sistema pida envíos sin saber qué proveedor se usa (útil si hay 2+ proveedores).

Su intención es proporcionar una interfaz unificada para un subsistema complejo. Como tenemos múltiples proveedores, el EnvíoFacade oculta la lógica de selección/estrategia y reduce acoplamiento en el resto del sistema.

## Diagrama de clases

# Diagrama de Clases — Integración Logística Veloz (Adapter + Facade)

# Diagrama de Clases — Integración Logística Veloz (Adapter + Facade)

# Diagrama de Clases — Integración Logística Veloz (Adapter + Facade)

classDiagram
direction LR

class IServicioEnvio {
    <<interface>>
    + float calcularCosto(String codigoPostal)
    + String obtenerTiempoEstimado(String codigoPostal)
    + String despacharPedido(String direccion, String codigoPostal, String idPedido)
}

class LogisticaVelozAdapter {
    - ApiLogisticaVeloz api
    + LogisticaVelozAdapter()
    + float calcularCosto(String codigoPostal)
    + String obtenerTiempoEstimado(String codigoPostal)
    + String despacharPedido(String direccion, String codigoPostal, String idPedido)
    - int parseCodigoPostal(String codigoPostal)
}

IServicioEnvio <|.. LogisticaVelozAdapter
LogisticaVelozAdapter --> ApiLogisticaVeloz : usa

class ApiLogisticaVeloz {
    + Cotizacion cotizarEnvio(int cpDestino)
    + String enviarPaquete(DatosEnvio datos)
}

class Cotizacion {
    - float costo
    - int dias
    + Cotizacion(float costo, int dias)
    + float getCosto()
    + int getDias()
}

class DatosEnvio {
    - String direccion
    - int cpDestino
    - String idPedido
    + DatosEnvio(String direccion, int cpDestino, String idPedido)
    + String getDireccion()
    + int getCpDestino()
    + String getIdPedido()
}

ApiLogisticaVeloz --> Cotizacion : retorna
ApiLogisticaVeloz --> DatosEnvio : requiere

class EnvioFacade {
    - Map~String, IServicioEnvio~ proveedores
    - String proveedorPorDefecto
    + EnvioFacade()
    + void registrarProveedor(String id, IServicioEnvio servicio, boolean porDefecto)
    + float calcularCosto(String proveedorId, String codigoPostal)
    + String obtenerTiempoEstimado(String proveedorId, String codigoPostal)
    + String despacharPedido(String proveedorId, String direccion, String codigoPostal, String idPedido)
    - IServicioEnvio elegirProveedor(String proveedorId)
}

EnvioFacade o-- IServicioEnvio

note for LogisticaVelozAdapter "Adapter que adapta la API externa (ApiLogisticaVeloz) a la interfaz interna IServicioEnvio. Convierte tipos y delega llamadas."
note for EnvioFacade "Fachada que centraliza el acceso a distintos proveedores de envío."
