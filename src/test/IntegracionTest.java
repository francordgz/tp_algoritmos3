package src.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import src.main.Clima.ClimaLluvia;
import src.main.Clima.ClimaNormal;
import src.main.Entrenador;
import src.main.Enums.Estados;
import src.main.Juego;
import src.main.Pokemon;
import src.main.Serializacion.InformeSerializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {

    Juego juego = new Juego();
    @Test
    public void deserializarArchivoInvalidoTest() {
        String mensaje = "";
        try {
            this.juego.deserializarPartida("", "", "", "");
        } catch (RuntimeException e) {
            mensaje = e.getMessage();
        } finally {
            assertEquals(mensaje, "Error al leer archivos JSON");
        }

        assertNull(juego.obtenerPrimerEntrenador());
        assertNull(juego.obtenerSegundoEntrenador());
    }
    @Test
    public void partidaTest() {
        this.juego.deserializarPartida(
                "partidaTest.json",
                "pokemonsTest.json",
                "habilidadesTest.json",
                "itemsTest.json");

        this.juego.modificarClima(new ClimaNormal());
        this.juego.obtenerPrimerEntrenador().cambiarPokemon(0);
        this.juego.obtenerSegundoEntrenador().cambiarPokemon(0);
        this.juego.inicializarTurnos();

        assertEquals(juego.obtenerPrimerEntrenador().obtenerNombre(), "Entrenador1");
        assertEquals(juego.obtenerSegundoEntrenador().obtenerNombre(), "Entrenador2");

        double danio = this.juego.atacar(0);
        Pokemon pokemon = juego.obtenerEntrenadorRival().obtenerPokemonActual();
        int danioEsperado = pokemon.obtenerVidaMaxima() - pokemon.obtenerVidaActual();
        assertEquals(danio, danioEsperado, 1);

        this.juego.cambiarTurno();

        assertTrue(juego.usarHabilidadClima(5));
        assertEquals(this.juego.getClima().getClass(), ClimaLluvia.class);

        this.juego.cambiarTurno();

        juego.usarItem(2,0);
        Pokemon actual = juego.obtenerEntrenadorActual().obtenerPokemonActual();
        assertFalse(actual.tieneEstado(Estados.PARALIZADO));
        int cantidad = juego.obtenerEntrenadorActual().obtenerItems().get(2).obtenerCantidad();
        assertEquals(cantidad, 0);

        this.juego.cambiarTurno();

        assertTrue(juego.usarHabilidad(3));
        Pokemon afectado = juego.obtenerEntrenadorRival().obtenerPokemonActual();
        assertEquals(afectado.obtenerEstados().get(0), Estados.ENVENENADO);

        juego.cambiarTurno();

        this.juego.rendirse();
        assertTrue(this.juego.terminado());
        assertTrue(juego.obtenerEntrenadorRival().esGanador());
        assertFalse(juego.obtenerEntrenadorActual().esGanador());

        List<Entrenador> entrenadores = new ArrayList<>();
        entrenadores.add(juego.obtenerPrimerEntrenador());
        entrenadores.add(juego.obtenerSegundoEntrenador());

        String informePath;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        try {
            informePath = InformeSerializer.serializeJSON(entrenadores, "informeTest.json");
            jsonNode = objectMapper.readTree(new File(informePath));
        } catch (IOException e) {
            throw new RuntimeException("Error al crear informeTest!");
        }

        List<String> nombres = new ArrayList<>();
        for (JsonNode entrenadorNode: jsonNode) {
           nombres.add(entrenadorNode.get("nombre").asText());
        }

        assertEquals(nombres.get(0), "Entrenador1");
        assertEquals(nombres.get(1), "Entrenador2");
    }
}
