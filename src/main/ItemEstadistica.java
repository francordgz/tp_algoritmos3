package src.main;

enum tipoModificacion {ATAQUE, DEFENSA}
public class ItemEstadistica extends Item{
    tipoModificacion tipo;

    public ItemEstadistica(String nombre, tipoModificacion tipo, int cantidad) {
        super(nombre, cantidad);
        this.tipo = tipo;
    }

    public void modificarEstadistica(Pokemon pokemon) {
        switch (tipo) {
            case ATAQUE:
                pokemon.modificarAtaque((int)(pokemon.obtenerAtaque() * 0.1));
                break;
            case DEFENSA:
                pokemon.modificarDefensa((int)(pokemon.obtenerDefensa() * 0.1));
                break;
            default:
                // Default secuencia de sentencias.
        }
    }
}
