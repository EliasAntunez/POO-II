# Descripción del Diagrama de Clases - Tienda Online

El sistema representa una **tienda online** donde los usuarios pueden ser **clientes** o **administradores**.

* Los **clientes** poseen un **carrito de compras** que contiene **detalles de carrito**, donde se almacenan los productos seleccionados y sus cantidades.
* Al confirmar la compra, se genera un **pedido** compuesto por **detalles de pedido**, y se asocia un **pago** que registra el monto, la fecha y el método de pago utilizado.
* Los **productos** pertenecen a una **categoría** y son gestionados por los administradores, quienes pueden agregarlos, modificarlos o eliminarlos.
* Cada cliente puede registrar múltiples **direcciones de envío** para sus pedidos.
* La **tienda** agrupa los productos disponibles y centraliza la gestión general del sistema.

Se emplean relaciones de:

* **Herencia** entre `Usuario`, `Cliente` y `Administrador`.
* **Composición** entre `Carrito`–`DetalleCarrito` y `Pedido`–`DetallePedido`.
* **Agregación** entre `Cliente` y `DireccionEnvio`, y entre `Tienda` y `Producto`.
