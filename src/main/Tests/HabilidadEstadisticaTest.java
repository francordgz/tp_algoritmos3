import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HabilidadEstadisticaTest {

    @Test
    public void testModificarEstadoVida() {
        habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VIDA, 5, 20, true);
        pokemon = Mockito.mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).curar(20);
        assertEquals(habilidadEstadistica.obtenerUsos, 4);
    }

    @Test
    public void testModificarEstadoAtaque() {
        habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.ATAQUE, 5, 10, true);
        pokemon = Mockito.mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarAtaque(10);
        assertEquals(habilidadEstadistica.obtenerUsos, 4);
    }

    @Test
    public void testModificarEstadoDefensa() {
        habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.DEFENSA, 5, 15, true);
        pokemon = Mockito.mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarDefensa(15);
        assertEquals(habilidadEstadistica.obtenerUsos, 4);
    }

    @Test
    public void testModificarEstadoVelocidad() {
        habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VELOCIDAD, 5, 25, true);
        pokemon = Mockito.mock(Pokemon.class);
        habilidadEstadistica.modificarEstado(pokemon);

        verify(pokemon, times(1)).modificarVelocidad(25);
        assertEquals(habilidadEstadistica.obtenerUsos, 4);
    }

    @Test
    public void testAfectarRival() {
        habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.VIDA, 5, 20, true);
        assertTrue(habilidadEstadistica.AfectarRival());

        habilidadEstadistica = new HabilidadEstadistica("HabilidadEstadisticaTest", Atributos.ATAQUE, 5, 10, false);
        assertFalse(habilidadEstadistica.AfectarRival());
    }
}
