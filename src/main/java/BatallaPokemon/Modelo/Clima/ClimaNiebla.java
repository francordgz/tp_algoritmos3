package BatallaPokemon.Modelo.Clima;

import BatallaPokemon.Modelo.Enums.Tipo;
import BatallaPokemon.Modelo.Pokemon;

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

    @Override
    public String getNombre() {
        return "niebla";
    }
}
