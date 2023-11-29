package BatallaPokemon.Modelo.Clima;

import BatallaPokemon.Modelo.Enums.Tipo;
import BatallaPokemon.Modelo.Pokemon;

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

    @Override
    public String getNombre() {
        return "soleado";
    }
}
