package src.main.Clima;

import src.main.Pokemon;

public class ClimaNormal implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        return ataque;
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
    }
}
