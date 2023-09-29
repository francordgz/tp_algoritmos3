package src.main;

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

    public void usarItem(Pokemon pokemon) {
        
    }
}