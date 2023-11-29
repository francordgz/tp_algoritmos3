package BatallaPokemon.HabilidadTest;

import BatallaPokemon.Modelo.Enums.Atributos;
import BatallaPokemon.Modelo.Habilidad.HabilidadEstadistica;
import BatallaPokemon.Modelo.Pokemon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class HabilidadEstadisticaTest {

    @Test
    public void testModificarEstadoVida() {
        HabilidadEstadistica habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VIDA, 5, 0,20, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).curar(20);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoAtaque() {
        HabilidadEstadistica habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.ATAQUE, 5, 0, 10, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarAtaque(10);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoDefensa() {
        HabilidadEstadistica habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.DEFENSA, 5, 0,15, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarDefensa(15);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoVelocidad() {
        HabilidadEstadistica habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VELOCIDAD, 5, 0, 25, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarVelocidad(25);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }
}