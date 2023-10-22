package src.main;

import src.main.Enums.Tipo;

public class ClimaTormentaDeArena implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        if (pokemon.obtenerTipo() == Tipo.TIERRA || pokemon.obtenerTipo() == Tipo.ROCA)
            return ataque*1.1;

        return ataque;
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
        if (pokemon.obtenerTipo() == Tipo.TIERRA || pokemon.obtenerTipo() == Tipo.ROCA)
            return;

        pokemon.recibirDanio((pokemon.obtenerVidaMaxima()*0.03));
    }
}
