package src.main.Serializacion;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.Entrenador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartidaDeserializer {
    File partidaJSON = new File("src/resources/data/partida.json");
    PokemonDeserializer pokemonDeserializer;
    ItemDeserializer itemDeserializer;

    public PartidaDeserializer() {
        this.pokemonDeserializer = new PokemonDeserializer(
                "src/resources/data/pokemons.json",
                "src/resources/data/habilidades.json");
        this.itemDeserializer = new ItemDeserializer("src/resources/data/items.json");
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
                entrenador.agregarItem(itemDeserializer.encontrarItem(id, cantidad));
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
}
