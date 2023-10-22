package src.main;

import src.main.Enums.Tipo;

public class ClimaHuracan implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        if (pokemon.obtenerTipo() == Tipo.VOLADOR)
            return ataque*1.1;

        return ataque;
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
        if (pokemon.obtenerTipo() == Tipo.VOLADOR)
            return;

        pokemon.recibirDanio((pokemon.obtenerVidaMaxima()*0.03));
    }
}
