package src.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.Enums.Atributos;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Habilidad.Habilidad;
import src.main.Habilidad.HabilidadAtaque;
import src.main.Habilidad.HabilidadEstadistica;
import src.main.Habilidad.HabilidadEstado;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pokedex {

    final File JSONPokemones;
    final File JSONHabilidades;

    public Pokedex(String rutaJSONPokemones, String rutaJSONHabilidades) {
        this.JSONPokemones = new File(rutaJSONPokemones);
        this.JSONHabilidades = new File(rutaJSONHabilidades);
    }

    public Pokemon crearPokemon(String nombre) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(this.JSONPokemones);

            if (jsonNode.has(nombre)) {
                JsonNode data = jsonNode.get(nombre);
                List<Integer> habilidadesId = objectMapper.convertValue(data.get("habilidades"), new TypeReference<>() {});
                return new Pokemon(
                        nombre,
                        stringTipo(data.get("tipo").asText()),
                        data.get("vidaMaxima").asInt(),
                        data.get("defensa").asInt(),
                        data.get("ataque").asInt(),
                        data.get("velocidad").asInt(),
                        data.get("historia").asText(),
                        data.get("nivel").asInt(),
                        parseHabilidades(habilidadesId)
                );
            } else {
                throw new IllegalArgumentException("Pokemon no encontrado: " + nombre);
            }
    }

    private List<Habilidad> parseHabilidades(List<Integer> habilidadesId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(this.JSONHabilidades);
        List<Habilidad> habilidades = new ArrayList<>();

        for (int id : habilidadesId) {
            if(jsonNode.has(id)) {
                JsonNode data = jsonNode.get(id);
                String nombre = data.get("nombre").asText();
                int usos = data.get("usos").asInt();

                if (data.has("estado")) {
                    habilidades.add(new HabilidadEstado(nombre, usos, stringEstado(data.get("estado").asText())));
                } else {
                    int poder = data.get("poder").asInt();
                    if (data.has("atributo")) {
                        habilidades.add(new HabilidadEstadistica(
                                nombre,
                                stringAtributos(data.get("atributo").asText()),
                                usos,
                                poder,
                                data.get("afectarRival").asBoolean()));
                    } else habilidades.add(new HabilidadAtaque(nombre, usos, poder, data.get("mismoTipo").asBoolean()));
                }
            }
            else throw new IllegalArgumentException("Habilidad no encontrada: " + id);
        }

        return habilidades;
    }

        private static Tipo stringTipo(String tipoStr) {
        return switch (tipoStr) {
            case "agua" -> Tipo.AGUA;
            case "bicho" -> Tipo.BICHO;
            case "dragon" -> Tipo.DRAGON;
            case "rayo" -> Tipo.RAYO;
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

    public static Estados stringEstado(String estadoStr) {
        return switch (estadoStr) {
            case "normal" -> Estados.NORMAL;
            case "envenenado" -> Estados.ENVENENADO;
            case "dormido" -> Estados.DORMIDO;
            case "paralizado" -> Estados.PARALIZADO;
            case "muerto" -> Estados.MUERTO;
            case "confuso" -> Estados.CONFUSO;
            default -> null;
        };
    }

    public static Atributos stringAtributos(String atributoStr) {
        return switch (atributoStr) {
            case "vida" -> Atributos.VIDA;
            case "ataque" -> Atributos.ATAQUE;
            case "velocidad" -> Atributos.VELOCIDAD;
            case "defensa" -> Atributos.DEFENSA;
            default -> null;
        };
    }
}