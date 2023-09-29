package src.main;

public class ItemCuracion extends Item {
    int poder;
    public ItemCuracion(int poder, String nombre, int cantidad) {
        super(nombre, cantidad);
        this.poder = poder;
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        pokemon.curar(poder);
    }
}
