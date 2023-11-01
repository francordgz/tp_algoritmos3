package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import src.main.Clima.ClimaNormal;
import src.main.Clima.ClimaSoleado;
import src.main.Clima.ClimaTormentaDeRayos;
import src.main.Entrenador;
import src.main.Enums.Tipo;
import src.main.Juego;
import src.main.Pokemon;

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
        Pokemon pokemon = Mockito.mock(Pokemon.class);
        when(pokemon.obtenerTipo()).thenReturn(Tipo.NORMAL);
        when(pokemon.obtenerVidaMaxima()).thenReturn(100);

        juego.modificarClima(new ClimaSoleado());
        juego.getClima().efectoClimatico(pokemon);
        verify(pokemon, never()).recibirDanio(anyDouble());

        juego.modificarClima(new ClimaTormentaDeRayos());
        juego.getClima().efectoClimatico(pokemon);
        verify(pokemon).recibirDanio(anyDouble());
    }
}
