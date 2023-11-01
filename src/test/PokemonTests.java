package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Pokemon;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTests {
    Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        pokemon = new Pokemon(0, "Picachu", Tipo.RAYO, 20, 5, 25, 15, "", 5, List.of());
    }

    @Test
    public void pokemonEnvenenadoMuereTest() {
        int vidaMaxima = 20;
        pokemon.recibirAtaque(9);
        pokemon.agregarEstado(Estados.ENVENENADO);

        int vidaActual = pokemon.obtenerVidaActual();
        int turnosHastaMorir = (int) (vidaActual / (vidaMaxima * 0.05));

        if (vidaMaxima % vidaActual != 0) {
            turnosHastaMorir += 1;
        }

        for (int i = 0; i < turnosHastaMorir; i++) {
            pokemon.actualizarEstado();
        }
        assertTrue(pokemon.tieneEstado(Estados.MUERTO));
    }


    @Test
    public void pokemonPierdeEstadosTest() {
        pokemon.agregarEstado(Estados.DORMIDO);
        pokemon.agregarEstado(Estados.PARALIZADO);
        pokemon.agregarEstado(Estados.ENVENENADO);

        pokemon.removerEstado(Estados.PARALIZADO);
        assertFalse(pokemon.tieneEstado(Estados.PARALIZADO));

        pokemon.removerEstado(Estados.DORMIDO);
        assertFalse(pokemon.tieneEstado(Estados.DORMIDO));

        pokemon.removerEstado(Estados.ENVENENADO);
        assertFalse(pokemon.tieneEstado(Estados.ENVENENADO));
        assertTrue(pokemon.tieneEstado(Estados.NORMAL));
    }

    @Test
    public void pokemonAgregaEstadosTest() {
        assertTrue(pokemon.tieneEstado(Estados.NORMAL));

        pokemon.agregarEstado(Estados.DORMIDO);

        assertTrue(pokemon.tieneEstado(Estados.DORMIDO));
    }

    @Test
    public void pokemonRecibeDanioYmuereTest() {
        pokemon.recibirAtaque(19);

        assertEquals(1, pokemon.obtenerVidaActual());

        pokemon.recibirAtaque(1);

        assertEquals(0, pokemon.obtenerVidaActual());

        assertTrue(pokemon.estaMuerto());
    }

    @Test
    public void pokemonSeDespiertaTest() {
        pokemon.agregarEstado(Estados.DORMIDO);

        pokemon.actualizarEstadoDormido();
        pokemon.actualizarEstadoDormido();
        pokemon.actualizarEstadoDormido();
        pokemon.actualizarEstadoDormido();

        ///Pokemon a se desperto si o si con esa cantidad de turnos///

        assertTrue(pokemon.tieneEstado(Estados.NORMAL));


    }


    @Test
    public void pokemonRemueveEstadoConfusoYPierdeVidaTest() {
        pokemon.agregarEstado(Estados.CONFUSO);

        pokemon.actualizarEstadoConfuso();
        pokemon.actualizarEstadoConfuso();
        pokemon.actualizarEstadoConfuso();
        pokemon.actualizarEstadoConfuso();

        assertTrue(pokemon.tieneEstado(Estados.NORMAL));
    }

    @Test
    public void PokemonSeCuraBien() {
        pokemon.recibirAtaque(19);

        assertEquals(1, pokemon.obtenerVidaActual());

        pokemon.curar(19);

        assertEquals(20, pokemon.obtenerVidaActual());
    }


    @Test
    public void pokemonRevive() {
        pokemon.recibirAtaque(20);

        assertTrue(pokemon.estaMuerto());

        pokemon.revivir();

        assertFalse(pokemon.estaMuerto());
    }

    @Test
    public void PokemonDormidoNoAtaca() {
        pokemon.agregarEstado(Estados.DORMIDO);
        assertFalse(pokemon.puedeAtacar());
    }

    @Test
    public void PokemonSeCuraCuandoNecesita() {
        pokemon.agregarEstado(Estados.PARALIZADO);
        assertTrue(pokemon.necesitaCurarse());
    }
}