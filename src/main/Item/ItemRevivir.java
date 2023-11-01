package src.main.Item;
import src.main.Pokemon;

public class ItemRevivir extends Item {
    public ItemRevivir(String nombre, int cantidad, int id) {
        super(nombre, cantidad, id);
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        pokemon.revivir();
        decrementarCantidad();
    }

    @Override
    public Boolean esAplicable(Pokemon pokemon) {
        return pokemon.estaMuerto();
    }
}