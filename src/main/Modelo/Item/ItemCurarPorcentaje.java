package src.main.Modelo.Item;

import src.main.Modelo.Pokemon;

public class ItemCurarPorcentaje extends Item{
    private final int porcentaje;

    public ItemCurarPorcentaje(String nombre, int cantidad, int id, int porcentaje) {
        super(nombre, cantidad, id);
        this.porcentaje = porcentaje;
    }

    @Override
    public void usarItem(Pokemon pokemon) {
        int cantidadDeVida = pokemon.obtenerVidaMaxima()*porcentaje;
        cantidadDeVida = cantidadDeVida/100;
        pokemon.curar(cantidadDeVida);
        decrementarCantidad();
    }

    @Override
    public Boolean esAplicable(Pokemon pokemon) {
        return !pokemon.estaMuerto();
    }
}
