package src.test.ItemTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.main.Item.Item;
import src.main.Item.ItemCuracion;
import src.main.Pokemon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ItemCuracionTest {
    private List<Item> items;

    @BeforeEach
    public void setUp() {
        this.items = new ArrayList<>();
        items.add(new ItemCuracion(20, "Pocion", 0, 3));
        items.add(new ItemCuracion(50, "MegaPocion", 0, 2));
        items.add(new ItemCuracion(100, "HiperPocion", 0, 3));
    }

    @Test
    public void obtenerNombreTest() {
        assertEquals("Pocion",items.get(0).obtenerNombre());
        assertEquals("MegaPocion",items.get(1).obtenerNombre());
        assertEquals("HiperPocion",items.get(2).obtenerNombre());
    }

    @Test
    public void obtenerCantidadTest() {
        assertEquals(3,items.get(0).obtenerCantidad());
        assertEquals(2,items.get(1).obtenerCantidad());
        assertEquals(1,items.get(2).obtenerCantidad());

        // Este último es una HiperPocion y, por lo tanto, solo tiene 1
    }

    @Test
    public void decrementarCantidadTest() {
        items.get(0).decrementarCantidad();
        items.get(1).decrementarCantidad();
        items.get(2).decrementarCantidad();

        assertEquals(2, items.get(0).obtenerCantidad());
        assertEquals(1, items.get(1).obtenerCantidad());
        assertEquals(0, items.get(2).obtenerCantidad());

        // Este último es una HiperPocion y, por lo tanto, solo se puede usar una vez
    }

    @Test
    public void esAplicableTest() {
        Pokemon pokemon = Mockito.mock(Pokemon.class);

        when(pokemon.estaMuerto()).thenReturn(false);
        assertTrue(items.get(0).esAplicable(pokemon));

        when(pokemon.estaMuerto()).thenReturn(true);
        assertFalse(items.get(1).esAplicable(pokemon));
    }

    @Test
    public void usarItemTest() {
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.obtenerVidaMaxima()).thenReturn(100);
        when(pokemon.obtenerVidaActual()).thenReturn(90);

        items.get(0).usarItem(pokemon);
        Mockito.verify(pokemon).curar(20);
    }
}
