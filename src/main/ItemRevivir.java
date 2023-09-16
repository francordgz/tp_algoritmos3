package src.main;

public class ItemRevivir extends Item{
    public ItemRevivir(String nombre, int cantidad) {
        super(nombre, cantidad);
    }

    public void revivir(Pokemon pokemon) {
        pokemon.modificarVida(pokemon.obtenerVidaMaxima());
    }
}
