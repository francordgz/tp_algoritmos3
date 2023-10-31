package src.main.Serializacion;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.main.Enums.TipoModificacion;
import src.main.Item.*;

import java.io.File;
import java.io.IOException;

public class ItemDeserializer {
    final File itemsJSON;

    public ItemDeserializer(File itemsJSON) {
        this.itemsJSON = itemsJSON;
    }

    public Item encontrarItem(int id, int cantidad) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode itemNode = objectMapper.readTree(this.itemsJSON);


        for (JsonNode item : itemNode) {
            if (item.get("id").asInt() == id) {
                String nombre = item.get("nombre").asText();

                if (nombre.equals("CuraTodo")) return new ItemEstado(nombre, cantidad, id);

                if (nombre.equals("Revivir")) return new ItemRevivir(nombre, cantidad, id);

                if (item.has("poder")) {
                    return new ItemCuracion(item.get("poder").asInt(), nombre, id, cantidad);
                }

                if (item.has("porcentaje")) {
                    return new ItemCurarPorcentaje(nombre, cantidad, id, item.get("porcentaje").asInt());
                }

                if (item.has("tipoModificacion")) {
                    return new ItemEstadistica(nombre,
                            stringTipoModificacion(item.get("tipoModificacion").asText()), id, cantidad);
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
