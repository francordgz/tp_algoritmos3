package BatallaPokemon.ClimaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import BatallaPokemon.Modelo.Clima.ClimaLluvia;
import BatallaPokemon.Modelo.Enums.Tipo;
import BatallaPokemon.Modelo.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClimaLluviaTest {
    private final ClimaLluvia climaLluvia = new ClimaLluvia();
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        pokemon = mock(Pokemon.class);
    }

    @Test
    public void testAfectarAtaqueAgua() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.AGUA);
        double ataque = climaLluvia.afectarAtaque(pokemon, 100.0);
        assertEquals(110.0, ataque, 0.01);
    }

    @Test
    public void testAfectarAtaquePlanta() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.PLANTA);
        double ataque = climaLluvia.afectarAtaque(pokemon, 100.0);
        assertEquals(110.0, ataque, 0.01);
    }

    @Test
    public void testAfectarAtaqueNoAguaOPlanta() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        double ataque = climaLluvia.afectarAtaque(pokemon, 100.0);
        assertEquals(100.0, ataque, 0.01);
    }
}