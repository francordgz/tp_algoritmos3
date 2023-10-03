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

    public boolean esAplicable(Pokemon pokemon) {
        return cantidad > 0;
    }

    public void usarItem(Pokemon pokemon) {
        
    }
}