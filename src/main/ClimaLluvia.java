package src.main;

import src.main.Enums.Tipo;

public class ClimaLluvia implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        if (pokemon.obtenerTipo() == Tipo.AGUA || pokemon.obtenerTipo() == Tipo.PLANTA)
            return ataque*1.1;

        return ataque;
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
    }
}
