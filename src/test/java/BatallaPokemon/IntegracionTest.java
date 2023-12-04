package BatallaPokemon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import BatallaPokemon.Modelo.Clima.ClimaLluvia;
import BatallaPokemon.Modelo.Clima.ClimaNormal;
import BatallaPokemon.Modelo.Entrenador;
import BatallaPokemon.Modelo.Enums.Estados;
import BatallaPokemon.Modelo.Juego;
import BatallaPokemon.Modelo.Pokemon;
import BatallaPokemon.Controlador.Serializacion.InformeSerializer;
import BatallaPokemon.Controlador.Serializacion.PartidaDeserializer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {
    Juego juego = new Juego();

    public void deserializarPartida(
            String partidaJSON, String pokemonsJSON, String habilidadesJSON, String itemsJSON)
            throws IOException, URISyntaxException {
        PartidaDeserializer partidaDeserializer = new PartidaDeserializer(
                partidaJSON, pokemonsJSON, habilidadesJSON, itemsJSON
        );

        List<Entrenador> entrenadores = partidaDeserializer.deserealizarPartida();
        this.juego.asignarEntrenadores(entrenadores.get(0), entrenadores.get(1));
    }

    @Test
    public void deserializarArchivoInvalidoTest() {
        try {
            deserializarPartida("", "", "", "");
        } catch (IOException | URISyntaxException | IllegalArgumentException e) {
            assertNull(juego.obtenerPrimerEntrenador());
            assertNull(juego.obtenerSegundoEntrenador());
        }
    }

    @Test
    public void partidaTest() {

        String partidaJSON = "Serial/partidaTest.json";
        String pokemonsJSON = "Serial/pokemonsTest.json";
        String habilidadesJSON = "Serial/habilidadesTest.json";
        String itemsJSON = "Serial/itemsTest.json";

        try {
            deserializarPartida(partidaJSON, pokemonsJSON, habilidadesJSON, itemsJSON);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error al leer archivos JSON");
        }

        this.juego.modificarClima(new ClimaNormal());
        this.juego.obtenerPrimerEntrenador().cambiarPokemon(0);
        this.juego.obtenerSegundoEntrenador().cambiarPokemon(0);
        this.juego.inicializarTurnos();

        assertEquals(juego.obtenerPrimerEntrenador().obtenerNombre(), "Entrenador1");
        assertEquals(juego.obtenerSegundoEntrenador().obtenerNombre(), "Entrenador2");

        this.juego.cambiarTurno();

        assertTrue(juego.usarHabilidadClima(5));
        assertEquals(this.juego.obtenerClima().getClass(), ClimaLluvia.class);

        this.juego.cambiarTurno();

        juego.usarItem(2, 0);
        Pokemon actual = juego.obtenerEntrenadorActual().obtenerPokemonActual();
        assertFalse(actual.tieneEstado(Estados.PARALIZADO));
        int cantidad = juego.obtenerEntrenadorActual().obtenerItems().get(2).obtenerCantidad();
        assertEquals(cantidad, 0);

        this.juego.cambiarTurno();

        juego.usarHabilidad(3);
        Pokemon afectado = juego.obtenerEntrenadorRival().obtenerPokemonActual();
        assertEquals(afectado.obtenerEstados().get(0), Estados.ENVENENADO);

        juego.cambiarTurno();

        this.juego.rendirse();
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
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error al crear informeTest!");
        }

        List<String> nombres = new ArrayList<>();
        for (JsonNode entrenadorNode : jsonNode) {
            nombres.add(entrenadorNode.get("nombre").asText());
        }

        assertEquals(nombres.get(0), "Entrenador1");
        assertEquals(nombres.get(1), "Entrenador2");
    }
}
