import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import static org.junit.Assert.Equals;
import static org.mockito.Mockito.mock;

public class EntrenadorTest {
    private Entrenador entrenador;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Item item1;
    private Item item2;

    @Before
    public void setUp() {
        entrenador = new Entrenador("Ash");
        pokemon1 = Mockito.mock(Pokemon.class);
        pokemon2 = Mockito.mock(Pokemon.class);
        item1 = Mockito.mock(Item.class);
        item2 = Mockito.mock(Item.class);
    }

    @Test
    public void testObtenerNombre() {
        assertEquals("Ash", entrenador.obtenerNombre());
    }

    @Test
    public void testObtenerPokemones() {
        entrenador.agregarPokemon(pokemon1);
        entrenador.agregarPokemon(pokemon2);
        List<Pokemon> pokemones = entrenador.obtenerPokemones();
        assertEquals(2, pokemones.size());
        assertTrue(pokemones.contains(pokemon1));
        assertTrue(pokemones.contains(pokemon2));
    }

    @Test
    public void testObtenerPokemonActual() {
        assertNull(entrenador.obtenerPokemonActual());
        entrenador.agregarPokemon(pokemon1);
        entrenador.cambiarPokemon(0);
        assertEquals(pokemon1, entrenador.obtenerPokemonActual());
    }

    @Test
    public void testObtenerItems() {
        entrenador.agregarItem(item1);
        entrenador.agregarItem(item2);
        List<Item> items = entrenador.obtenerItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    @Test
    public void testAgregarPokemon() {
        entrenador.agregarPokemon(pokemon1);
        List<Pokemon> pokemones = entrenador.obtenerPokemones();
        assertEquals(1, pokemones.size());
        assertTrue(pokemones.contains(pokemon1));
    }

    @Test
    public void testAgregarItem() {
        entrenador.agregarItem(item1);
        List<Item> items = entrenador.obtenerItems();
        assertEquals(1, items.size());
        assertTrue(items.contains(item1));
    }

    @Test
    public void testCambiarPokemon() {
        entrenador.agregarPokemon(pokemon1);
        entrenador.agregarPokemon(pokemon2);
        entrenador.cambiarPokemon(1);
        assertEquals(pokemon2, entrenador.obtenerPokemonActual());
    }

    @Test
    public void testUsarItem() {
        entrenador.agregarPokemon(pokemon1);
        entrenador.agregarItem(item1);
        entrenador.cambiarPokemon(0);
        entrenador.usarItem(0, 0);
        assertFalse(pokemon1.estaMuerto());
    }

    @Test
    public void testTienePokemonesConVida() {
        entrenador.agregarPokemon(pokemon1);
        assertTrue(entrenador.tienePokemonesConVida());
        pokemon1.recibirDanio(10000);
        assertFalse(entrenador.tienePokemonesConVida());
    }

    @Test
    public void testPuedeAplicarItem() {
        entrenador.agregarPokemon(pokemon1);
        entrenador.agregarItem(item1);
        assertTrue(entrenador.puedeAplicarItem(0, 0));
    }
}