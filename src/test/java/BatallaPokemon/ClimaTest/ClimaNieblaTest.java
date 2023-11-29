package BatallaPokemon.ClimaTest;

import BatallaPokemon.Modelo.Clima.ClimaNiebla;
import BatallaPokemon.Modelo.Enums.Tipo;
import BatallaPokemon.Modelo.Pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClimaNieblaTest {
    private final ClimaNiebla climaNiebla = new ClimaNiebla();
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        pokemon = mock(Pokemon.class);
    }

    @Test
    public void testAfectarAtaqueFantasma() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.FANTASMA);
        double ataque = climaNiebla.afectarAtaque(pokemon, 100.0);
        assertEquals(110.0, ataque, 0.01);
    }

    @Test
    public void testAfectarAtaquePsiquico() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.PSIQUICO);
        double ataque = climaNiebla.afectarAtaque(pokemon, 100.0);
        assertEquals(110.0, ataque, 0.01);
    }

    @Test
    public void testAfectarAtaqueNoFantasmaOPsiquico() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        double ataque = climaNiebla.afectarAtaque(pokemon, 100.0);
        assertEquals(100.0, ataque, 0.01);
    }
}