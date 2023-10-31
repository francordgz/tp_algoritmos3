package src.main.Item;
import src.main.Pokemon;

public abstract class Item {

    private final String nombre;
    int id;
    private int cantidad;

    public Item(String nombre, int cantidad, int id) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.id = id;
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

    public int obtenerId() {
        return this.id;
    }
}