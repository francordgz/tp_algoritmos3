package BatallaPokemon.Modelo.Item;
import BatallaPokemon.Modelo.Enums.TipoModificacion;
import BatallaPokemon.Modelo.Pokemon;

public class ItemEstadistica extends Item {
    
    private final TipoModificacion tipo;

    public ItemEstadistica(String nombre, TipoModificacion tipo, int id, int cantidad) {
        super(nombre, cantidad, id);
        this.tipo = tipo;
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        switch (tipo) {
            case ATAQUE:
                pokemon.modificarAtaque((int)(pokemon.obtenerAtaque() * 0.1));
                break;
            case DEFENSA:
                pokemon.modificarDefensa((int)(pokemon.obtenerDefensa() * 0.1));
                break;
            default:
                throw new RuntimeException("Error en el item!");
        }
        decrementarCantidad();
    }

    @Override
    public Boolean esAplicable(Pokemon pokemon) {
        return !pokemon.estaMuerto();
    }
}
