/*
package src.test.HabilidadTest;

import src.main.Modelo.Enums.Atributos;
import src.main.Modelo.Habilidad.Habilidad;
import src.main.Modelo.Habilidad.HabilidadEstadistica;
import src.main.Modelo.Pokemon;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class HabilidadEstadisticaTest {

    @Test
    public void testModificarEstadoVida() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VIDA, 5, 0,20, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).curar(20);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoAtaque() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.ATAQUE, 5, 0, 10, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarAtaque(10);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoDefensa() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.DEFENSA, 5, 0,15, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarDefensa(15);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoVelocidad() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VELOCIDAD, 5, 0, 25, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarVelocidad(25);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testAfectarRival() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VIDA, 5, 20, 0, true);
        assertTrue(habilidadEstadistica.AfectarRival());

        habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.ATAQUE, 5, 0,10, false);
        assertFalse(habilidadEstadistica.AfectarRival());
    }
}
*/