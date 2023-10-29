package src.main;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import src.main.Enums.Estados;
import src.main.Item.Item;

import java.io.IOException;

public class EntrenadorSerializer extends JsonSerializer<Entrenador> {
    @Override
    public void serialize(Entrenador entrenador, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("nombre", entrenador.obtenerNombre());
        jsonGenerator.writeBooleanField("ganador", entrenador.esGanador());

        jsonGenerator.writeArrayFieldStart("pokemones");
        for (Pokemon pokemon : entrenador.obtenerPokemones()) {
            jsonGenerator.writeNumberField("id", pokemon.obtenerId());
            jsonGenerator.writeNumberField("vidaRestante", pokemon.obtenerVidaActual());
            for (Estados estado: pokemon.obtenerEstados()) {
                if (estado != Estados.NORMAL) {
                    jsonGenerator.writeStringField("estado", estadoString(estado));
                }
            }
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("items");
        for (Item item : entrenador.obtenerItems()) {
            int cantidad = item.obtenerCantidad();
            if (cantidad > 0)
                jsonGenerator.writeNumberField(Integer.toString(item.obtenerId()), cantidad);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }

    private static String estadoString(Estados estadoActual) {
        return switch (estadoActual) {
            case NORMAL -> "Normal";
            case ENVENENADO -> "Envenenado";
            case DORMIDO -> "Dormido";
            case PARALIZADO -> "Paralizado";
            case MUERTO -> "Muerto";
            case CONFUSO -> "Confuso";
        };
    }
}

