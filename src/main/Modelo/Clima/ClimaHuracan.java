package src.main.Modelo.Clima;

import src.main.Modelo.Enums.Tipo;
import src.main.Modelo.Pokemon;

public class ClimaHuracan implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        if (pokemon.obtenerTipo() == Tipo.VOLADOR)
            return ataque*1.1;

        return ataque;
    }

    public String getNombre() {
        return "huracan";
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
        if (pokemon.obtenerTipo() == Tipo.VOLADOR)
            return;

        pokemon.recibirAtaque((pokemon.obtenerVidaMaxima()*0.03));
    }
}
