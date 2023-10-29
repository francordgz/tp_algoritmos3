package src.test.HabilidadTest;

import org.junit.jupiter.api.*;
import src.main.Enums.Estados;
import src.main.Habilidad.HabilidadEstado;
import src.main.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HabilidadEstadoTest {
    private HabilidadEstado habilidadEstado;
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        pokemon = mock(Pokemon.class);
    }

    @Test
    public void testModificarEstadoEnvenenado() {
        habilidadEstado = new HabilidadEstado("HabilidadEstadoEnvenenado", 5, Estados.ENVENENADO);
        habilidadEstado.modificarEstado(pokemon);

        verify(pokemon, times(1)).agregarEstado(Estados.ENVENENADO);
        assertEquals(habilidadEstado.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoDormido() {
        habilidadEstado = new HabilidadEstado("HabilidadEstadoDormido", 5, Estados.DORMIDO);
        habilidadEstado.modificarEstado(pokemon);

        verify(pokemon, times(1)).agregarEstado(Estados.DORMIDO);
        assertEquals(habilidadEstado.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoParalizado() {
        habilidadEstado = new HabilidadEstado("HabilidadEstadoParalizado", 5, Estados.PARALIZADO);
        habilidadEstado.modificarEstado(pokemon);

        verify(pokemon, times(1)).agregarEstado(Estados.PARALIZADO);
        assertEquals(habilidadEstado.obtenerUsos(), 4);
    }

    @Test
    public void testModificarEstadoConfuso() {
        habilidadEstado = new HabilidadEstado("HabilidadEstadoConfuso", 5, Estados.CONFUSO);
        habilidadEstado.modificarEstado(pokemon);

        verify(pokemon, times(1)).agregarEstado(Estados.CONFUSO);
        assertEquals(habilidadEstado.obtenerUsos(), 4);
    }
}