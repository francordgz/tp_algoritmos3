package src.main.Modelo.Clima;

import src.main.Modelo.Pokemon;

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
