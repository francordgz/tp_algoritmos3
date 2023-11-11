package src.main.Modelo.Item;
import src.main.Modelo.Enums.Estados;
import src.main.Modelo.Pokemon;

public class ItemEstado extends Item {
    public ItemEstado(String nombre, int cantidad, int id) {
        super(nombre, cantidad, id);
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        pokemon.agregarEstado(Estados.NORMAL);
        decrementarCantidad();
    }

    @Override
    public Boolean esAplicable(Pokemon pokemon) {
        return pokemon.necesitaCurarse();
    }
}
