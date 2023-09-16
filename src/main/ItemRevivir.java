package src.main;

public class ItemRevivir extends Item{
    public ItemRevivir(String nombre) {
        this.nombre = nombre;
    }

    public void revivir(Pokemon pokemon) {
        pokemon.modificarVida(pokemon.obtenerVidaMaxima());
    }
}
