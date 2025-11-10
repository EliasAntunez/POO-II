package main;

import java.util.ArrayList;
import java.util.List;
import main.carrito.*;

public class Main {
    public static void main(String[] args) {
        // Componentes simples
        Componente cpu = new CPU();
        Componente ram1 = new MemoriaRAM();
        Componente ram2 = new MemoriaRAM();
        Componente ssd = new SSD();

        // Placa Madre compuesta
        ComponenteCompuesto placaMadre = new ComponenteCompuesto("Placa Madre", 150);
        placaMadre.agregar(cpu);
        placaMadre.agregar(ram1);
        placaMadre.agregar(ram2);

        // Gabinete que contiene la placa madre
        ComponenteCompuesto gabinete = new ComponenteCompuesto("Gabinete", 120);
        gabinete.agregar(placaMadre);

        // Aplicar extras al gabinete
        Componente gabineteConExtras = new GarantiaExtendida(new ServicioInstalacion(gabinete));

        // Aplicar extras a un componente simple
        Componente ssdConGarantia = new GarantiaExtendida(ssd);

        // Carrito de compras
        List<Componente> carrito = new ArrayList<>();
        carrito.add(gabineteConExtras);
        carrito.add(ssdConGarantia);

        // Mostrar resumen
        float total = 0;
        System.out.println("Carrito de Compras:");
        for (Componente c : carrito) {
            System.out.println("- " + c.getDescripcion() + " -> " + c.getPrecio());
            total += c.getPrecio();
        }

        System.out.println("Total: $" + total);
    }
}

