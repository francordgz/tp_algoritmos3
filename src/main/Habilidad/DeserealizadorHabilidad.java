package src.main.Habilidad;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.Enums.Atributos;
import src.main.Enums.Estados;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeserealizadorHabilidad {
    final File habilidadesJSON;
    public DeserealizadorHabilidad(String rutaJSONHabilidades) {
        this.habilidadesJSON = new File(rutaJSONHabilidades);
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
