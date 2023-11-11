package src.test.ClimaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.Modelo.Clima.ClimaTormentaDeArena;
import src.main.Modelo.Enums.Tipo;
import src.main.Modelo.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class ClimaTormentaDeArenaTest {
    private final ClimaTormentaDeArena climaTormentaDeArena = new ClimaTormentaDeArena();
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        pokemon = mock(Pokemon.class);
    }

    @Test
    public void testAfectarAtaqueTierra() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.TIERRA);
        double ataque = climaTormentaDeArena.afectarAtaque(pokemon, 100.0);
        assertEquals(110.0, ataque, 0.01);
    }

    @Test
    public void testAfectarAtaqueRoca() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.ROCA);
        double ataque = climaTormentaDeArena.afectarAtaque(pokemon, 100.0);
        assertEquals(110.0, ataque, 0.01);
    }

    @Test
    public void testAfectarAtaqueNoTierraORoca() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        double ataque = climaTormentaDeArena.afectarAtaque(pokemon, 100.0);
        assertEquals(100.0, ataque, 0.01);
    }

    @Test
    public void testEfectoClimaticoTierra() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.TIERRA);
        climaTormentaDeArena.efectoClimatico(pokemon);
        verify(pokemon, never()).recibirAtaque(anyDouble());
    }

    @Test
    public void testEfectoClimaticoRoca() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.ROCA);
        climaTormentaDeArena.efectoClimatico(pokemon);
        verify(pokemon, never()).recibirAtaque(anyDouble());
    }

    @Test
    public void testEfectoClimaticoNoTierraORoca() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        when(pokemon.obtenerVidaMaxima()).thenReturn(100);

        climaTormentaDeArena.efectoClimatico(pokemon);
        verify(pokemon).recibirAtaque(100*0.03);
    }
}
