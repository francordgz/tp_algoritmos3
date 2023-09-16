package src.main;

public class ItemCuracion extends Item {
    int poder;
    public ItemCuracion(int poder, String nombre) {
        this.poder = poder;
        this.nombre = nombre;
    }

    public void curar(Pokemon pokemon) {
        pokemon.modificarVida(poder);
    }
}
