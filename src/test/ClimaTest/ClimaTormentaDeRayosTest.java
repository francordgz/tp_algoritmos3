package src.test.ClimaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.main.Clima.ClimaTormentaDeRayos;
import src.main.Enums.Tipo;
import src.main.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClimaTormentaDeRayosTest {
    private final ClimaTormentaDeRayos climaTormentaDeRayos = new ClimaTormentaDeRayos();
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        pokemon = mock(Pokemon.class);
    }

    @Test
    public void testAfectarAtaqueRayo() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.RAYO);
        double ataque = climaTormentaDeRayos.afectarAtaque(pokemon, 100.0);
        assertEquals(110.0, ataque, 0.01);
    }

    @Test
    public void testAfectarAtaqueNoRayo() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        double ataque = climaTormentaDeRayos.afectarAtaque(pokemon, 100.0);
        assertEquals(100.0, ataque, 0.01);
    }

    @Test
    public void testEfectoClimaticoRayo() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.RAYO);
        climaTormentaDeRayos.efectoClimatico(pokemon);
        Mockito.verify(pokemon, never()).recibirAtaque(100*0.03);
    }

    @Test
    public void testEfectoClimaticoNoRayo() {
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        when(pokemon.obtenerVidaMaxima()).thenReturn(100);

        climaTormentaDeRayos.efectoClimatico(pokemon);
        Mockito.verify(pokemon, times(1)).recibirAtaque(100*0.03);
    }
}