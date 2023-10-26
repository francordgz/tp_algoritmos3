package src.main.Item;
import src.main.Enums.TipoModificacion;
import src.main.Pokemon;

public class ItemEstadistica extends Item {
    
    private final TipoModificacion tipo;

    public ItemEstadistica(String nombre, TipoModificacion tipo, int cantidad) {
        super(nombre, cantidad);
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
}
