package src.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.Item.DeserealizadorItem;
import src.main.Item.ItemCuracion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeserealizadorPartida {
    File partidaJSON = new File("src/resources/data/partida.json");
    Pokedex pokedex;
    DeserealizadorItem deserealizadorItem;

    public DeserealizadorPartida() {
        this.pokedex = new Pokedex(
                "src/resources/data/pokemons.json",
                "src/resources/data/habilidades.json");
        this.deserealizadorItem = new DeserealizadorItem("src/resources/data/items.json");
    }

    public List<Entrenador> deserealizarPartida() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode partidaJSON = objectMapper.readTree(this.partidaJSON);

        List<Entrenador> entrenadores = new ArrayList<>();
        for (JsonNode entrenadorNode: partidaJSON) {
            Entrenador entrenador = new Entrenador(entrenadorNode.get("nombre").asText());
            //crearItems(entrenador, entrenadorNode.get("items"));
            crearPokemons(entrenador, entrenadorNode.get("pokemons"));
            entrenador.agregarItem(new ItemCuracion(30, "A", 4));
            entrenadores.add(entrenador);
        }

        return entrenadores;
    }

    private void crearItems(Entrenador entrenador, JsonNode itemsNode) {
        itemsNode.fields().forEachRemaining(entry -> {
            int id = Integer.parseInt(entry.getKey());
            int cantidad = entry.getValue().asInt();

            try {
                entrenador.agregarItem(deserealizadorItem.encontrarItem(id, cantidad));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void crearPokemons(Entrenador entrenador, JsonNode pokemonsNode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> pokemonsIDs = objectMapper.convertValue(pokemonsNode, new TypeReference<>() {});
        for (int id : pokemonsIDs) {
            entrenador.agregarPokemon(pokedex.crearPokemon(id));
        }
    }
}
