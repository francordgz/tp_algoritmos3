import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import static org.junit.Assert.Equals;
import static org.mockito.Mockito.mock;

public class EntrenadorTest {
    private Entrenador entrenador;

    pokemon1 =Mockito.mock(Pokemon .class);
    pokemon2 =Mockito.mock(Pokemon .class);
    item1 =Mockito.mock(Item .class);
    item2 =Mockito.mock(Item .class);

    @BeforeEach
    public void nuevoEntrenador() {
        entrenador = new Entrenador("Ash");
    }

    @Test
    public void obtenerNombreTest() {
        entrenador = new Entrenador("Ash");
        assertEquals("Ash", entrenador.obtenerNombre());
    }

    @Test
    public void obtenerPokemonesTest() {
        pokemon1 = Mockito.mock(Pokemon.class);
        pokemon2 = Mockito.mock(Pokemon.class);

        entrenador.agregarPokemon(pokemon1);
        entrenador.agregarPokemon(pokemon2);

        List<Pokemon> pokemones = entrenador.obtenerPokemones();

        assertEquals(2, pokemones.size());
        assertTrue(pokemones.contains(pokemon1));
        assertTrue(pokemones.contains(pokemon2));
    }

    @Test
    public void obtenerPokemonActualTest() {
        assertNull(entrenador.obtenerPokemonActual());

        pokemon = Mockito.mock(Pokemon.class);
        entrenador.agregarPokemon(pokemon);
        entrenador.cambiarPokemon(0);

        assertEquals(pokemon, entrenador.obtenerPokemonActual());
    }

    @Test
    public void obtenerItemsTest() {
        item1 = Mockito.mock(Item.class);
        item2 = Mockito.mock(Item.class);
        entrenador.agregarItem(item1);
        entrenador.agregarItem(item2);

        List<Item> items = entrenador.obtenerItems();

        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    @Test
    public void agregarPokemonTest() {
        pokemon = Mockito.mock(Pokemon.class)
        entrenador.agregarPokemon(pokemon);

        List<Pokemon> pokemones = entrenador.obtenerPokemones();

        assertEquals(1, pokemones.size());
        assertTrue(pokemones.contains(pokemon));
    }

    @Test
    public void agregarItemTest() {
        item = Mockito.mock(Item.class)

        entrenador.agregarItem(item);

        List<Item> items = entrenador.obtenerItems();

        assertEquals(1, items.size());
        assertTrue(items.contains(item));
    }

    @Test
    public void cambiarPokemonTest() {
        pokemon1 = Mockito.mock(Pokemon.class);
        pokemon2 = Mockito.mock(Pokemon.class);
        entrenador.agregarPokemon(pokemon1);
        entrenador.agregarPokemon(pokemon2);

        entrenador.cambiarPokemon(0)
        assertEquals(pokemon1, entrenador.obtenerPokemonActual());

        entrenador.cambiarPokemon(1);
        assertEquals(pokemon2, entrenador.obtenerPokemonActual());
    }

    @Test
    public void tienePokemonesConVidaTest() {
        pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.estaMuerto()).thenReturn(false);
        entrenador.agregarPokemon(pokemon);

        assertTrue(entrenador.tienePokemonesConVida());

        when(pokemon.estaMuerto()).thenReturn(true);
        assertFalse(entrenador.tienePokemonesConVida());
    }
}