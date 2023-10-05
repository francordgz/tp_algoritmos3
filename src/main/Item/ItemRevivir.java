package src.main.Item;
import src.main.Pokemon;

public class ItemRevivir extends Item {
    public ItemRevivir(String nombre, int cantidad) {
        super(nombre, cantidad);
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        pokemon.revivir();
        decrementarCantidad();
    }

    @Override
    public boolean esAplicable(Pokemon pokemon) {
        return pokemon.estaMuerto();
    }
}