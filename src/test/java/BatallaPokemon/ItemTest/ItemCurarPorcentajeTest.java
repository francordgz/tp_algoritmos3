package BatallaPokemon.ItemTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import BatallaPokemon.Modelo.Constant;
import BatallaPokemon.Modelo.Item.ItemCurarPorcentaje;
import BatallaPokemon.Modelo.Pokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ItemCurarPorcentajeTest {
    ItemCurarPorcentaje item;

    @BeforeEach
    public void setUp() {
        this.item = new ItemCurarPorcentaje("Pocion molesta alumnos", 2, 0, Constant.TERCIO);
    }
    @Test
    public void obtenerNombreTest() {
        assertEquals("Pocion molesta alumnos", item.obtenerNombre());
    }

    @Test
    public void obtenerCantidadTest() {
        assertEquals(2, item.obtenerCantidad());
    }

    @Test
    public void decrementarCantidadTest() {
        item.decrementarCantidad();
        assertEquals(1, item.obtenerCantidad());
    }

    @Test
    public void esAplicableTest() {
        Pokemon pokemon = Mockito.mock(Pokemon.class);

        when(pokemon.estaMuerto()).thenReturn(false);
        assertTrue(item.esAplicable(pokemon));

        when(pokemon.estaMuerto()).thenReturn(true);
        assertFalse(item.esAplicable(pokemon));
    }

    @Test
    public void usarItemTest() {
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.obtenerVidaMaxima()).thenReturn(100);
        when(pokemon.obtenerVidaActual()).thenReturn(90);

        item.usarItem(pokemon);
        Mockito.verify(pokemon).curar(100 * Constant.TERCIO / 100);
    }
}
