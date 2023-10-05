package src.main.Item;
import src.main.Pokemon;

public abstract class Item {
    String nombre;
    int cantidad;

    public Item(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerCantidad() {
        return cantidad;
    }

    protected boolean quedanUsosDisponibles() { return this.cantidad > 0; }

    public boolean esAplicable(Pokemon pokemon) {
        return true;
    }

    public abstract void usarItem(Pokemon pokemon);

    protected void decrementarCantidad() { this.cantidad -= 1; }
}