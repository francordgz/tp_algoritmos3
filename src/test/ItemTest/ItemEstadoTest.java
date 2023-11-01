package src.test.ItemTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.main.Enums.Estados;
import src.main.Item.ItemEstado;
import src.main.Pokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ItemEstadoTest {
    private ItemEstado item;
    private Pokemon pokemon;
    @BeforeEach
    public void setUp() {
        this.item = new ItemEstado("CuraTodo", 3, 0);

        this.pokemon = Mockito.mock(Pokemon.class);
    }

    @Test
    public void esAplicableTest() {
        when(pokemon.necesitaCurarse()).thenReturn(false);
        assertFalse(item.esAplicable(pokemon));

        when(pokemon.necesitaCurarse()).thenReturn(true);
        assertTrue(item.esAplicable(pokemon));
    }

   @Test
    public void usarItemTest() {
        item.usarItem(this.pokemon);
        Mockito.verify(pokemon).agregarEstado(Estados.NORMAL);
       assertEquals(2, item.obtenerCantidad());
    }
}
