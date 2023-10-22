package src.main;

import src.main.Enums.Tipo;

public class ClimaSoleado implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        if (pokemon.obtenerTipo() == Tipo.FUEGO)
            return ataque*1.1;

        return ataque;
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
    }
}
