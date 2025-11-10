package main.carrito;


public abstract class ExtraDecorator implements Componente {
    protected Componente componente;

    public ExtraDecorator(Componente componente) {
        this.componente = componente;
    }
}

