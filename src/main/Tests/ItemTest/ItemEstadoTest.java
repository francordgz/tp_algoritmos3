package src.main.Tests.ItemTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Item.Item;
import src.main.Item.ItemCuracion;
import src.main.Item.ItemEstado;
import src.main.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemEstadoTest {
    private Item item;
    private Pokemon pokemon;
    @BeforeEach
    public void setUp() {
        this.item = new ItemEstado("CuraTodo", 3);

        this.pokemon = new Pokemon("Bulbasur", Tipo.PLANTA, 120, 10, 10, 10,
                "Bulbasur lleva una planta en su espalda, que crece a medida que evoluciona.", Arrays.asList(), 5);
    }

    @Test
    public void esAplicableTest() {
        assertEquals(false, item.esAplicable(pokemon));
        pokemon.agregarEstado(Estados.DORMIDO);
        assertEquals(true, item.esAplicable(pokemon));
    }

    @Test
    public void usarItemTest() {
        this.pokemon.agregarEstado(Estados.ENVENENADO);
        item.usarItem(this.pokemon);
        assertTrue(pokemon.tieneEstado(Estados.NORMAL));
        assertTrue(!pokemon.tieneEstado(Estados.ENVENENADO));
    }
}
