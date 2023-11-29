package BatallaPokemon.Modelo.Clima;

import BatallaPokemon.Modelo.Enums.Tipo;
import BatallaPokemon.Modelo.Pokemon;

public class ClimaLluvia implements Clima {

    public String getNombre() {
        return "lluvia";
    }

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
