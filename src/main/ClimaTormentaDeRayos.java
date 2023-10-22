package src.main;

import src.main.Enums.Tipo;

public class ClimaTormentaDeRayos implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        if (pokemon.obtenerTipo() == Tipo.RAYO)
            return ataque*1.1;

        return ataque;
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
        if (pokemon.obtenerTipo() == Tipo.RAYO)
            return;

        pokemon.recibirDanio((pokemon.obtenerVidaMaxima()*0.03));
    }
}
