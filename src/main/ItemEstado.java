package src.main;

public class ItemEstado extends Item{
    public ItemEstado(String nombre, int cantidad) {
        super(nombre, cantidad);
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        pokemon.modificarEstado(estados.NORMAL);
    }
}
