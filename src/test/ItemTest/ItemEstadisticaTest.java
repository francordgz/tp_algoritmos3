package src.test.ItemTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.main.Modelo.Enums.TipoModificacion;
import src.main.Modelo.Item.ItemEstadistica;
import src.main.Modelo.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ItemEstadisticaTest {

    ItemEstadistica itemAtaque;
    ItemEstadistica itemDefensa;

    @BeforeEach
    public void setUp() {
        itemAtaque = new ItemEstadistica("Ataque", TipoModificacion.ATAQUE, 0, 2);
        itemDefensa = new ItemEstadistica("Defensa", TipoModificacion.DEFENSA, 0, 1);
    }

    @Test
    public void obtenerNombreTest() {
        assertEquals("Ataque", itemAtaque.obtenerNombre());
        assertEquals("Defensa", itemDefensa.obtenerNombre());
    }

    @Test
    public void obtenerCantidadTest() {
        assertEquals(2, itemAtaque.obtenerCantidad());
        assertEquals(1, itemDefensa.obtenerCantidad());
    }

    @Test
    public void decrementarCantidadTest() {
        itemAtaque.decrementarCantidad();
        assertEquals(1, itemAtaque.obtenerCantidad());
    }

    @Test
    public void usarItemTest() {
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.obtenerAtaque()).thenReturn(10);
        when(pokemon.obtenerDefensa()).thenReturn(20);

        itemAtaque.usarItem(pokemon);
        itemDefensa.usarItem(pokemon);
        Mockito.verify(pokemon).modificarAtaque((int) (10*0.1));
        Mockito.verify(pokemon).modificarDefensa((int) (20*0.1));
    }
}