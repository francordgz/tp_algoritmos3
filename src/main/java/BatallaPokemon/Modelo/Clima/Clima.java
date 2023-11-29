package BatallaPokemon.Modelo.Clima;

import BatallaPokemon.Modelo.Pokemon;

public interface Clima {
    double afectarAtaque(Pokemon pokemon, double ataque);
    void efectoClimatico(Pokemon pokemon);

    String getNombre();
}
