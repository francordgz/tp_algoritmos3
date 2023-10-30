package src.test;

import org.mockito.Mockito;
import org.junit.jupiter.api.*;
import src.main.Clima.ClimaLluvia;
import src.main.Juego;
import src.main.Entrenador;
import src.main.Item.Item;
import src.main.Pokemon;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JuegoTest {

    ///No se utiliza mock en esta clase debido a que es como un test de integracion

    private Juego juego;

    @BeforeEach
    public void nuevoJuego() {
        juego = new Juego ();


    }

    @Test
    public void juegoAsignaEntrenadoresYlosDevuelve() {

        Entrenador entrenador1 = new Entrenador("ash");
        Entrenador entrenador2 = new Entrenador("Eze");


        juego.asignarEntrenadores(entrenador1,entrenador2);

        assertEquals(entrenador1,juego.obtenerPrimerEntrenador());
        assertEquals(entrenador2,juego.obtenerSegundoEntrenador());


    }









}
