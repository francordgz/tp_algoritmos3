package src.main.Modelo.Serializacion;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import src.main.Modelo.Clima.*;

import src.main.Modelo.Enums.Atributos;
import src.main.Modelo.Enums.Estados;

import src.main.Modelo.Habilidad.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabilidadDeserializer {
    final File habilidadesJSON;
    public HabilidadDeserializer(File habilidadesJSON) {
        this.habilidadesJSON = habilidadesJSON;
    }

    public List<Habilidad> crearHabilidades(List<Integer> habilidadesIDs) throws IOException {
        List<Habilidad> habilidades = new ArrayList<>();
        for (int id: habilidadesIDs) {
            habilidades.add(encontrarHabilidad(id));
        }
        return habilidades;
    }
    private Habilidad encontrarHabilidad(int id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode habilidadesJSON = objectMapper.readTree(this.habilidadesJSON);

        for (JsonNode habilidad : habilidadesJSON) {
            if (habilidad.get("id").asInt() == id) {
                String nombre = habilidad.get("nombre").asText();
                int usos = habilidad.get("usos").asInt();

                if (habilidad.has("clima")) {
                    return new HabilidadClima(nombre, usos, id, stringClima(habilidad.get("clima").asText()));
                }

                if (habilidad.has("estado")) {
                    return new HabilidadEstado(nombre, usos, id, stringEstado(habilidad.get("estado").asText()));
                } else {
                    int poder = habilidad.get("poder").asInt();
                    if (habilidad.has("atributo")) {
                        return new HabilidadEstadistica(
                                nombre,
                                stringAtributos(habilidad.get("atributo").asText()),
                                usos,
                                id,
                                poder,
                                habilidad.get("afectarRival").asBoolean());
                    } else return new HabilidadAtaque(nombre, usos, id, poder, habilidad.get("mismoTipo").asBoolean());
                }
            }
        }
        throw new IllegalArgumentException("Habilidad no encontrada: " + id);
    }

    private Estados stringEstado(String estadoStr) {
        return switch (estadoStr) {
            case "envenenado" -> Estados.ENVENENADO;
            case "dormido" -> Estados.DORMIDO;
            case "paralizado" -> Estados.PARALIZADO;
            case "confuso" -> Estados.CONFUSO;
            default -> throw new IllegalArgumentException("Estado Iválido: " + estadoStr);
        };
    }

    private Atributos stringAtributos(String atributoStr) {
        return switch (atributoStr) {
            case "vida" -> Atributos.VIDA;
            case "ataque" -> Atributos.ATAQUE;
            case "velocidad" -> Atributos.VELOCIDAD;
            case "defensa" -> Atributos.DEFENSA;
            default -> throw new IllegalArgumentException("Atributo Iválido: " + atributoStr);
        };
    }

    private Clima stringClima(String climaStr) {
        return switch (climaStr) {
            case "huracan" -> new ClimaHuracan();
            case "lluvia" -> new ClimaLluvia();
            case "niebla" -> new ClimaNiebla();
            case "soleado" -> new ClimaSoleado();
            case "tormentaDeArena" -> new ClimaTormentaDeArena();
            case "tormentaDeRayos" -> new ClimaTormentaDeRayos();
            default -> throw new IllegalArgumentException("Clima Inválido: " + climaStr);
        };
    }

}
