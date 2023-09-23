package src.main;

public class ItemRevivir extends Item{
    public ItemRevivir(String nombre, int cantidad) {
        super(nombre, cantidad);
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        pokemon.modificarVida(pokemon.obtenerVidaMaxima());
    }
}
