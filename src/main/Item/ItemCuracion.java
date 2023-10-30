package src.main.Item;
import src.main.Pokemon;

public class ItemCuracion extends Item {
    private final int poder;

    public ItemCuracion(int poder, String nombre, int id, int cantidad) {
        super(nombre, cantidad, id);
        this.poder = poder;

        if (nombre.equals("Hiperpocion") && poder == 100 && cantidad > 1)
            while(this.obtenerCantidad() > 1) this.decrementarCantidad();
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
