package src.main.Modelo.Serializacion;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.Modelo.Enums.Tipo;
import src.main.Modelo.Pokemon;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PokemonDeserializer { // Rest In Peace "Pokedex" (2023 - 2023)

    final File pokemonsJSON;
    final HabilidadDeserializer habilidadDeserializer;

    public PokemonDeserializer(File pokemonsJSON, File habilidadesJSON) {
        this.pokemonsJSON = pokemonsJSON;
        this.habilidadDeserializer = new HabilidadDeserializer(habilidadesJSON);
    }

    public Pokemon crearPokemon(int id) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode pokemonJSON = objectMapper.readTree(this.pokemonsJSON);
            JsonNode pokemon = encontrarPokemon(id, pokemonJSON);

            List<Integer> habilidadesId = objectMapper.convertValue(pokemon.get("habilidades"), new TypeReference<>() {});
            return new Pokemon(
                    id,
                    pokemon.get("nombre").asText(),
                    stringTipo(pokemon.get("tipo").asText()),
                    pokemon.get("vidaMaxima").asInt(),
                    pokemon.get("defensa").asInt(),
                    pokemon.get("ataque").asInt(),
                    pokemon.get("velocidad").asInt(),
                    pokemon.get("historia").asText(),
                    pokemon.get("nivel").asInt(),
                    this.habilidadDeserializer.crearHabilidades(habilidadesId)
            );
    }

    private JsonNode encontrarPokemon(int id, JsonNode pokemonJSON) {
        for (JsonNode pokemon : pokemonJSON) {
            if (pokemon.get("id").asInt() == id) return pokemon;
        }
        throw new IllegalArgumentException("Pokemon no encontrado: " + id);
    }

        private static Tipo stringTipo(String tipoStr) {
        return switch (tipoStr) {
            case "agua" -> Tipo.AGUA;
            case "bicho" -> Tipo.BICHO;
            case "dragon" -> Tipo.DRAGON;
            case "electrico" -> Tipo.RAYO;
            case "fantasma" -> Tipo.FANTASMA;
            case "fuego" -> Tipo.FUEGO;
            case "hielo" -> Tipo.HIELO;
            case "lucha" -> Tipo.LUCHA;
            case "normal" -> Tipo.NORMAL;
            case "planta" -> Tipo.PLANTA;
            case "psiquico" -> Tipo.PSIQUICO;
            case "roca" -> Tipo.ROCA;
            case "tierra" -> Tipo.TIERRA;
            case "veneno" -> Tipo.VENENO;
            case "volador" -> Tipo.VOLADOR;
            default -> throw new IllegalStateException("Unexpected value: " + tipoStr);
        };
    }
}