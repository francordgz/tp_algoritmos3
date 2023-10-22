package src.main;

public class ClimaNormal implements Clima {
    @Override
    public double afectarAtaque(Pokemon pokemon, double ataque) {
        return ataque;
    }

    @Override
    public void efectoClimatico(Pokemon pokemon) {
    }
}
