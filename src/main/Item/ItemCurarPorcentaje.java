package src.main.Item;

import src.main.Pokemon;

public class ItemCurarPorcentaje extends Item{

    private int porcentaje;
    public ItemCurarPorcentaje(String nombre, int cantidad, int porcentaje) {
        super(nombre, cantidad);
        this.porcentaje = porcentaje;
    }

    public void usarItem(Pokemon pokemon) {
        int cantidadDeVida = pokemon.obtenerVidaMaxima()*porcentaje;
        cantidadDeVida = cantidadDeVida/100;
        pokemon.curar(cantidadDeVida);
    }
}
