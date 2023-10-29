package src.main.Item;
import src.main.Pokemon;

public abstract class Item {

    protected String nombre;
    protected int cantidad;

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

    public boolean esAplicable(Pokemon pokemon) {
        return true;
    }

    public void usarItem(Pokemon pokemon) {
    }

    public void decrementarCantidad() {
        this.cantidad -= 1;
    }
}