package src.main.Item;
import src.main.Enums.Estados;
import src.main.Pokemon;

public class ItemEstado extends Item {
    public ItemEstado(String nombre, int cantidad) {
        super(nombre, cantidad);
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        pokemon.agregarEstado(Estados.NORMAL);
        decrementarCantidad();
    }

    @Override
    public boolean esAplicable(Pokemon pokemon) {
        return pokemon.necesitaCurarse();
    }
}
