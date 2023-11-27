package src.main.Modelo.Clima;

import src.main.Modelo.Enums.Tipo;
import src.main.Modelo.Pokemon;

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
