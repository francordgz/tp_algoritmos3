package src.main.Serializacion;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.Entrenador;
import src.main.Item.Item;


import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PartidaDeserializer {
    final File partidaJSON;
    PokemonDeserializer pokemonDeserializer;
    ItemDeserializer itemDeserializer;

    public PartidaDeserializer(String partidaJSON, String pokemonsJSON, String habilidadesJSON, String itemsJSON)
            throws FileNotFoundException, URISyntaxException
    {
        this.partidaJSON = recursoFile(partidaJSON);

        this.pokemonDeserializer = new PokemonDeserializer(
                recursoFile(pokemonsJSON), recursoFile(habilidadesJSON));

        this.itemDeserializer = new ItemDeserializer(recursoFile(itemsJSON));
    }

    public List<Entrenador> deserealizarPartida() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode partidaJSON = objectMapper.readTree(this.partidaJSON);

        List<Entrenador> entrenadores = new ArrayList<>();
        for (JsonNode entrenadorNode: partidaJSON) {
            Entrenador entrenador = new Entrenador(entrenadorNode.get("nombre").asText());
            crearItems(entrenador, entrenadorNode.get("items"));
            crearPokemons(entrenador, entrenadorNode.get("pokemons"));
            entrenadores.add(entrenador);
        }

        return entrenadores;
    }

    private void crearItems(Entrenador entrenador, JsonNode itemsNode) {
        itemsNode.fields().forEachRemaining(entry -> {
            int id = Integer.parseInt(entry.getKey());
            int cantidad = entry.getValue().asInt();

            try {
                Item item = itemDeserializer.encontrarItem(id, cantidad);
                entrenador.agregarItem(item);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void crearPokemons(Entrenador entrenador, JsonNode pokemonsNode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> pokemonsIDs = objectMapper.convertValue(pokemonsNode, new TypeReference<>() {});
        for (int id : pokemonsIDs) {
            entrenador.agregarPokemon(pokemonDeserializer.crearPokemon(id));
        }
    }

    private File recursoFile(String nombreRecurso) throws URISyntaxException, FileNotFoundException {
        URL resourceURL = ClassLoader.getSystemResource(nombreRecurso);

        if (resourceURL == null) throw new FileNotFoundException();
        return new File(resourceURL.toURI());
    }
}
