import src.main.Enums.Atributos;
import src.main.Habilidad.Habilidad;
import src.main.Habilidad.HabilidadEstadistica;
import src.main.Pokemon;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HabilidadEstadisticaTest {

    @Test
    public void testModificarEstadoVida() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VIDA, 5, 20, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).curar(20);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoAtaque() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.ATAQUE, 5, 10, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarAtaque(10);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoDefensa() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.DEFENSA, 5, 15, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarDefensa(15);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoVelocidad() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VELOCIDAD, 5, 25, true);
        Pokemon pokemon = mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarVelocidad(25);
        assertEquals(habilidadEstadistica.obtenerUsos(), 4);
    }

    @Test
    public void testAfectarRival() {
        Habilidad habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VIDA, 5, 20, true);
        assertTrue(habilidadEstadistica.AfectarRival());

        habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.ATAQUE, 5, 10, false);
        assertFalse(habilidadEstadistica.AfectarRival());
    }
}
