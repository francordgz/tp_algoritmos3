package BatallaPokemon.Modelo.Clima;

import BatallaPokemon.Modelo.Pokemon;

public class ClimaNormal implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        return ataque;
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
    }

    @Override
    public String getNombre() {
        return "normal";
    }
}
