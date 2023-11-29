package BatallaPokemon.ItemTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import BatallaPokemon.Modelo.Item.ItemRevivir;
import BatallaPokemon.Modelo.Pokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ItemRevivirTest {
    private ItemRevivir item;
    private Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        this.item = new ItemRevivir("Revivir", 3, 0);
        this.pokemon = Mockito.mock(Pokemon.class);
    }

    @Test
    public void esAplicableTest() {
        when(pokemon.estaMuerto()).thenReturn(false);
        assertFalse(item.esAplicable(pokemon));

        when(pokemon.estaMuerto()).thenReturn(true);
        assertTrue(item.esAplicable(pokemon));
    }

    @Test
    public void usarItemTest() {
        item.usarItem(this.pokemon);
        Mockito.verify(pokemon).revivir();
        assertEquals(2, item.obtenerCantidad());
    }
}