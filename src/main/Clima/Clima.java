package src.main.Clima;

import src.main.Pokemon;

public interface Clima {
    public double afectarAtaque(Pokemon pokemon, double ataque);
    public void efectoClimatico(Pokemon pokemon);
}
