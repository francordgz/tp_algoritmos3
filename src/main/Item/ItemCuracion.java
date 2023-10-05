package src.main.Item;
import src.main.Pokemon;

public class ItemCuracion extends Item {
    private int poder;

    public ItemCuracion(int poder, String nombre, int cantidad) {
        super(nombre, cantidad);
        this.poder = poder;
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        pokemon.curar(poder);
        decrementarCantidad();
    }

    @Override
    public boolean esAplicable(Pokemon pokemon) {
        return !pokemon.estaMuerto();
    }
}
