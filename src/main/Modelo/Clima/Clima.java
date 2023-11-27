package src.main.Modelo.Clima;

import src.main.Modelo.Pokemon;

public interface Clima {
    double afectarAtaque(Pokemon pokemon, double ataque);
    void efectoClimatico(Pokemon pokemon);

    String getNombre();
}
