package src.main.Item;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.Enums.TipoModificacion;

import java.io.File;
import java.io.IOException;

public class DeserealizadorItem {
    final File itemJSON;

    public DeserealizadorItem(String rutaJSONitems) {
        this.itemJSON = new File(rutaJSONitems);
    }

    public Item encontrarItem(int id, int cantidad) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode itemJSON = objectMapper.readTree(this.itemJSON);

        for (JsonNode item : itemJSON) {
            if (item.get("id").asInt() == id) {
                String nombre = item.get("nombre").asText();

                if (nombre.equals("CuraTodo")) return new ItemEstado(nombre, cantidad);

                if (nombre.equals("Revivir")) return new ItemRevivir(nombre, cantidad);

                if (item.has("poder")) {
                    return new ItemCuracion(item.get("poder").asInt(), nombre, cantidad);
                }

                if (item.has("porcentaje")) {
                    return new ItemCurarPorcentaje(nombre, item.get("porcentaje").asInt(), cantidad);
                }

                if (item.has("tipoModifacion")) {
                    return new ItemEstadistica(nombre,
                            stringTipoModificacion(item.get("tipoModifacion").asText()), cantidad);
                }
            }
        }
        throw new IllegalArgumentException("Item no encontrado: " + id);
    }

    private TipoModificacion stringTipoModificacion (String tipo) {
        return switch(tipo) {
            case "ataque" -> TipoModificacion.ATAQUE;
            case "defensa" -> TipoModificacion.DEFENSA;
            default -> null;
        };
    }
}
