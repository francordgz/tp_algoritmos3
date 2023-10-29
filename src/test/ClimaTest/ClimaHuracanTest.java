package src.test.ClimaTest;

import org.mockito.Mockito;
import org.junit.jupiter.api.*;
import src.main.Clima.ClimaHuracan;
import src.main.Enums.Tipo;
import src.main.Pokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClimaHuracanTest {
    private final ClimaHuracan climaHuracan = new ClimaHuracan();
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        pokemon = mock(Pokemon.class);
    }

    @Test
    public void testAfectarAtaqueVolador() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.VOLADOR);
        double ataque = climaHuracan.afectarAtaque(pokemon, 100.0);
        assertEquals(110.0, ataque, 0.01);
    }

    @Test
    public void testAfectarAtaqueNoVolador() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        double ataque = climaHuracan.afectarAtaque(pokemon, 100.0);
        assertEquals(100.0, ataque, 0.01);
    }

    @Test
    public void testEfectoClimaticoVolador() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.VOLADOR);
        climaHuracan.efectoClimatico(pokemon);
        Mockito.verify(pokemon, never()).recibirDanio(100*0.03);
    }

    @Test
    public void testEfectoClimaticoNoVolador() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        when(pokemon.obtenerVidaMaxima()).thenReturn(100);

        climaHuracan.efectoClimatico(pokemon);
        Mockito.verify(pokemon, times(1)).recibirDanio(100*0.03);
    }
}