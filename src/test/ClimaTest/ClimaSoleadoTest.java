package src.test.ClimaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.Modelo.Clima.ClimaSoleado;
import src.main.Modelo.Enums.Tipo;
import src.main.Modelo.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClimaSoleadoTest {
    private final ClimaSoleado climaSoleado = new ClimaSoleado();
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        pokemon = mock(Pokemon.class);
    }

    @Test
    public void testAfectarAtaqueFuego() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.FUEGO);
        double ataque = climaSoleado.afectarAtaque(pokemon, 100.0);
        assertEquals(110.0, ataque, 0.01);
    }

    @Test
    public void testAfectarAtaqueNoFuego() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        double ataque = climaSoleado.afectarAtaque(pokemon, 100.0);
        assertEquals(100.0, ataque, 0.01);
    }
}