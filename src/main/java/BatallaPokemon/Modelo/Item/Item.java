package BatallaPokemon.Modelo.Item;
import BatallaPokemon.Modelo.Pokemon;

public abstract class Item {

    private final String nombre;
    private final int id;
    private int cantidad;

    public Item(String nombre, int cantidad, int id) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.id = id;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerId() {
        return this.id;
    }

    public int obtenerCantidad() {
        return cantidad;
    }

    public void usarItem(Pokemon pokemon) {
    }

    public abstract Boolean esAplicable(Pokemon pokemon);

    public void decrementarCantidad() {
        this.cantidad -= 1;
    }
}