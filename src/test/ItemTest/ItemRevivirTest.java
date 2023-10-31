package src.test.ItemTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Item.ItemRevivir;
import src.main.Pokemon;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemRevivirTest {
    private Pokemon pokemon;
    private ItemRevivir item;
    @BeforeEach
    public void setUp() {
        this.pokemon = new Pokemon(0, "Bulbasur", Tipo.PLANTA, 120, 10, 10, 10,
                "Bulbasur lleva una planta en su espalda, que crece a medida que evoluciona.", 5, Arrays.asList());
        this.item = new ItemRevivir("Revivir", 1, 0);
    }

    @Test
    public void esAplicableTest() {
        assertFalse(item.esAplicable(pokemon));
        pokemon.agregarEstado(Estados.MUERTO);
        assertTrue(item.esAplicable(pokemon));
    }

    @Test
    public void usarItemTest() {
        this.pokemon.agregarEstado(Estados.MUERTO);
        item.usarItem(pokemon);
        assertFalse(pokemon.tieneEstado(Estados.MUERTO));
        assertTrue(pokemon.tieneEstado(Estados.NORMAL));
    }
}
