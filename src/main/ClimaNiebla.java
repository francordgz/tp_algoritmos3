package src.main;

import src.main.Enums.Tipo;

public class ClimaNiebla implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        if (pokemon.obtenerTipo() == Tipo.FANTASMA || pokemon.obtenerTipo() == Tipo.PSIQUICO)
            return ataque*1.1;

        return ataque;
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
    }
}
