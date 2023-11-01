package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.main.Entrenador;
import src.main.Enums.Estados;
import src.main.Item.Item;
import src.main.Pokemon;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EntrenadorTest {
    private Entrenador entrenador;

    @BeforeEach
    public void nuevoEntrenador() {
        entrenador = new Entrenador("Ash");
    }

    @Test
    public void obtenerNombreTest() {
        assertEquals("Ash", entrenador.obtenerNombre());
    }

    @Test
    public void obtenerPokemonesTest() {
        Pokemon pokemon1 = Mockito.mock(Pokemon.class);
        Pokemon pokemon2 = Mockito.mock(Pokemon.class);

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

        Pokemon pokemon = Mockito.mock(Pokemon.class);
        entrenador.agregarPokemon(pokemon);
        entrenador.cambiarPokemon(0);

        assertEquals(pokemon, entrenador.obtenerPokemonActual());
    }

    @Test
    public void obtenerItemsTest() {
        Item item1 = Mockito.mock(Item.class);
        Item item2 = Mockito.mock(Item.class);
        entrenador.agregarItem(item1);
        entrenador.agregarItem(item2);

        List<Item> items = entrenador.obtenerItems();

        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    @Test
    public void agregarPokemonTest() {
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        entrenador.agregarPokemon(pokemon);

        List<Pokemon> pokemones = entrenador.obtenerPokemones();

        assertEquals(1, pokemones.size());
        assertTrue(pokemones.contains(pokemon));
    }

    @Test
    public void agregarItemTest() {
        Item item = Mockito.mock(Item.class);

        entrenador.agregarItem(item);

        List<Item> items = entrenador.obtenerItems();

        assertEquals(1, items.size());
        assertTrue(items.contains(item));
    }

    @Test
    public void cambiarPokemonTest() {
        Pokemon pokemon1 = Mockito.mock(Pokemon.class);
        Pokemon pokemon2 = Mockito.mock(Pokemon.class);
        entrenador.agregarPokemon(pokemon1);
        entrenador.agregarPokemon(pokemon2);

        entrenador.cambiarPokemon(0);
        assertEquals(pokemon1, entrenador.obtenerPokemonActual());

        entrenador.cambiarPokemon(1);
        assertEquals(pokemon2, entrenador.obtenerPokemonActual());
    }

    @Test
    public void tienePokemonesConVidaTest() {
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.estaMuerto()).thenReturn(false);
        entrenador.agregarPokemon(pokemon);

        assertTrue(entrenador.tienePokemonesConVida());

        when(pokemon.estaMuerto()).thenReturn(true);
        assertFalse(entrenador.tienePokemonesConVida());
    }


    @Test
    public void ValidarItemTest() {
        Item item = Mockito.mock(Item.class);
        when(item.obtenerCantidad()).thenReturn(2);
        entrenador.agregarItem(item);
        assertTrue(entrenador.validarItem(0));
    }

    @Test
    public void puedeAplicarItem() {
        Item item = Mockito.mock(Item.class);
        Pokemon pokemon = Mockito.mock(Pokemon.class);

        when(item.esAplicable(pokemon)).thenReturn(true);

        entrenador.agregarItem(item);
        entrenador.agregarPokemon(pokemon);

        assertTrue(entrenador.puedeAplicarItem(0, 0));
    }

    @Test
    public void pokemonEstaMuerto() {
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.tieneEstado(Estados.MUERTO)).thenReturn(true);
        entrenador.agregarPokemon(pokemon);

        assertTrue(entrenador.pokemonEstaMuerto(0));
    }
}