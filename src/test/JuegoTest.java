package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.Modelo.Clima.ClimaNormal;
import src.main.Modelo.Clima.ClimaSoleado;
import src.main.Modelo.Clima.ClimaTormentaDeRayos;
import src.main.Modelo.Entrenador;
import src.main.Modelo.Enums.Tipo;
import src.main.Modelo.Juego;
import src.main.Modelo.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.*;

public class JuegoTest {
    private Juego juego;

    @BeforeEach
    public void nuevoJuego() {
        juego = new Juego ();
    }

    @Test
    public void juegoAsignaEntrenadoresYlosDevuelveTest() {
        Entrenador entrenador1 = new Entrenador("Ash");
        Entrenador entrenador2 = new Entrenador("Eze");

        juego.asignarEntrenadores(entrenador1,entrenador2);

        assertEquals(entrenador1,juego.obtenerPrimerEntrenador());
        assertEquals(entrenador2,juego.obtenerSegundoEntrenador());
    }

    @Test
    public void juegoInicializaClimaTest(){
        juego.inicializarClima();
        assertNotSame(juego.obtenerClima(), new ClimaNormal());
    }

    @Test
    public void juegoActualizaBienClimaTest(){
        juego.inicializarClima();

        juego.actualizarClima();
        juego.actualizarClima();
        juego.actualizarClima();
        juego.actualizarClima();
        juego.actualizarClima();
        juego.actualizarClima();

        assertEquals(ClimaNormal.class, juego.obtenerClima().getClass());
    }

    @Test
    void efectoClimaticoTest() {
        Pokemon pokemon = mock(Pokemon.class);
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        when(pokemon.obtenerVidaMaxima()).thenReturn(100);

        juego.modificarClima(new ClimaSoleado());
        juego.obtenerClima().efectoClimatico(pokemon);
        verify(pokemon, never()).recibirAtaque(anyDouble());

        juego.modificarClima(new ClimaTormentaDeRayos());
        juego.obtenerClima().efectoClimatico(pokemon);
        verify(pokemon).recibirAtaque(anyDouble());
    }
}
