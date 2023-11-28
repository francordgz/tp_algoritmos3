package src.main.Modelo.Item;
import src.main.Modelo.Pokemon;

public class ItemCuracion extends Item {
    private final int poder;

    public ItemCuracion(int poder, String nombre, int id, int cantidad) {
        super(nombre, cantidad, id);
        this.poder = poder;

        if (nombre.equals("Hiperpocion") || poder >= 100)
            while(this.obtenerCantidad() > 1) this.decrementarCantidad();
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        pokemon.curar(poder);
        decrementarCantidad();
    }

    @Override
    public Boolean esAplicable(Pokemon pokemon) {
        return !pokemon.estaMuerto() && !pokemon.tieneVidaMaxima();
    }
}
