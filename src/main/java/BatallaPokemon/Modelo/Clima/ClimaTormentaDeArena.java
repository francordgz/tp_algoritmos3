package BatallaPokemon.Modelo.Clima;

import BatallaPokemon.Modelo.Enums.Tipo;
import BatallaPokemon.Modelo.Pokemon;

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

        pokemon.recibirAtaque((pokemon.obtenerVidaMaxima()*0.03));
    }

    @Override
    public String getNombre() {
        return "tormenta_de_arena";
    }
}
