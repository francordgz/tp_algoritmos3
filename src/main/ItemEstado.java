package src.main;

public class ItemEstado extends Item{
    public ItemEstado(String nombre, int cantidad) {
        super(nombre, cantidad);
    }

    public void curarEstado(Pokemon pokemon) {
        pokemon.modificarEstado(estados.NORMAL);
    }
}
