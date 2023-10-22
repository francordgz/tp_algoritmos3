package src.main.Item;
import src.main.Pokemon;

public class ItemCuracion extends Item {
    private int poder;

    public ItemCuracion(int poder, String nombre, int cantidad) {
        super(nombre, cantidad);
        if (nombre.equals("Hiperpocion") && poder == 100 && cantidad > 1)
            this.cantidad = 1;

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
