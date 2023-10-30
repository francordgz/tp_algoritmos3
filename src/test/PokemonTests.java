package src.test;

import org.junit.jupiter.api.*;
import src.main.Enums.Atributos;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Habilidad.HabilidadAtaque;
import src.main.Habilidad.HabilidadEstadistica;
import src.main.Habilidad.HabilidadEstado;
import src.main.Pokemon;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokemonTests {


    @Test
    public void test01Pokemon() {


        Pokemon p = new Pokemon("Picachu",Tipo.RAYO, 100, 5, 25, 15,
                "Este Pokémon es conocido por su cola en forma de rayo y su capacidad para generar electricidad.", Arrays.asList(
                new HabilidadAtaque("Golpe", "Normal", 3, 100, false),
                new HabilidadAtaque("Impactrueno", "Rayo", 1, 120, true),
                new HabilidadEstadistica("Aumentar ataque", Atributos.ATAQUE, 2, 10,true),
                new HabilidadEstado("Paralizar", 2, Estados.PARALIZADO),
                new HabilidadEstado("Confundir", 1, Estados.CONFUSO)
        ),5);

        assertEquals(p, p);


    }

    @Test
    public void pokemonEnvenenadoMuereTest() {
        int vidaMaxima = 20;
        Pokemon pokemon = new Pokemon("Picachu", Tipo.RAYO, vidaMaxima, 5,
                25, 15, "", Arrays.asList(), 5);
        pokemon.recibirDanio(9);
        pokemon.agregarEstado(Estados.ENVENENADO);

        int vidaActual = pokemon.obtenerVidaActual();
        int turnosHastaMorir = (int) (vidaActual / (vidaMaxima * 0.05));

        if (vidaMaxima % vidaActual != 0) { turnosHastaMorir += 1; }

        for (int i = 0; i < turnosHastaMorir; i++) {
            pokemon.actualizarEstado();
        }
        assertTrue(pokemon.tieneEstado(Estados.MUERTO));
    }


    @Test
    public void pokemonPierdeEstadosTest() {
        Pokemon pokemon = new Pokemon("Picachu", Tipo.RAYO, 20, 5,
                25, 15, "", Arrays.asList(), 5);

        pokemon.agregarEstado(Estados.DORMIDO);
        pokemon.agregarEstado(Estados.PARALIZADO);
        pokemon.agregarEstado(Estados.ENVENENADO);

        pokemon.removerEstado(Estados.PARALIZADO);
        assertEquals(false, pokemon.tieneEstado(Estados.PARALIZADO));

        pokemon.removerEstado(Estados.DORMIDO);
        assertEquals(false, pokemon.tieneEstado(Estados.DORMIDO));

        pokemon.removerEstado(Estados.ENVENENADO);
        assertEquals(false, pokemon.tieneEstado(Estados.ENVENENADO));
        assertEquals(true, pokemon.tieneEstado(Estados.NORMAL));
    }

    @Test
    public void pokemonAgregaEstadosTest(){
        Pokemon pokemon = new Pokemon("Picachu", Tipo.RAYO, 20, 5,
                25, 15, "", Arrays.asList(), 5);

        assertEquals(true,pokemon.tieneEstado(Estados.NORMAL));

        pokemon.agregarEstado(Estados.DORMIDO);

        assertEquals(true, pokemon.tieneEstado(Estados.DORMIDO));


    }

    @Test
    public void pokemonRecibeDanioYmuereTest(){
        Pokemon pokemon = new Pokemon("Picachu", Tipo.RAYO, 20, 5,
                25, 15, "", Arrays.asList(), 5);

        pokemon.recibirDanio(19);

        assertEquals(1,pokemon.obtenerVidaActual());

        pokemon.recibirDanio(1);

        assertEquals(0,pokemon.obtenerVidaActual());

        assertEquals(true,pokemon.estaMuerto());


    }

    @Test
    public void pokemonSeDespiertaTest(){
        Pokemon pokemon = new Pokemon("Picachu", Tipo.RAYO, 20, 5,
                25, 15, "", Arrays.asList(), 5);

        pokemon.agregarEstado(Estados.DORMIDO);

        pokemon.actualizarEstadoDormido();
        pokemon.actualizarEstadoDormido();
        pokemon.actualizarEstadoDormido();
        pokemon.actualizarEstadoDormido();

        ///Pokemon a se desperto si o si con esa cantidad de turnos///

        assertEquals(true,pokemon.tieneEstado(Estados.NORMAL));


    }


    @Test
    public void pokemonRemueveEstadoConfusoYPierdeVidaTest(){

        Pokemon pokemon = new Pokemon("Picachu", Tipo.RAYO, 20, 5,
                25, 15, "", Arrays.asList(), 5);

        pokemon.agregarEstado(Estados.CONFUSO);

        pokemon.actualizarEstadoConfuso();
        pokemon.actualizarEstadoConfuso();
        pokemon.actualizarEstadoConfuso();
        pokemon.actualizarEstadoConfuso();

        assertEquals(true,pokemon.tieneEstado(Estados.NORMAL));

        /// Aveces test puede fallar porque capaz no pierde vida pero es muy raro. Es para
        /// verificar que esta perdiendo vida

        assertEquals(true,pokemon.obtenerVidaActual() < 20);



    }

    @Test
    public void PokemonSeCuraBien(){

        Pokemon pokemon = new Pokemon("Picachu", Tipo.RAYO, 20, 5,
                25, 15, "", Arrays.asList(), 5);

        pokemon.recibirDanio(19);

        assertEquals(1,pokemon.obtenerVidaActual());

        pokemon.curar(19);

        assertEquals(20,pokemon.obtenerVidaActual());

    }








}