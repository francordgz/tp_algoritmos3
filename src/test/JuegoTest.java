package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.Clima.ClimaNormal;
import src.main.Entrenador;
import src.main.Juego;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

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

    @Test
    public void JuegoInicializaUnClima(){

        juego.inicializarClima();

        assertNotSame(juego.obtenerClima(), new ClimaNormal());


    }

    @Test
    public void JuegoActualizaBienClima(){

        juego.inicializarClima();

        assertNotSame(juego.obtenerClima(), new ClimaNormal());

        juego.actualizarClima();
        juego.actualizarClima();
        juego.actualizarClima();
        juego.actualizarClima();
        juego.actualizarClima();
        juego.actualizarClima();


        assertEquals(ClimaNormal.class,juego.obtenerClima().getClass());


    }












}
