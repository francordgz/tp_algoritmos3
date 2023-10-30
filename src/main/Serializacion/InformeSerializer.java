package src.main.Serializacion;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import src.main.Entrenador;
import src.main.Enums.Estados;
import src.main.Item.Item;
import src.main.Pokemon;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InformeSerializer {
    public static void serializeJSON(List<Entrenador> entrenadores) throws IOException {
        String path = "src/resources/data/informe.json";
        JsonFactory factory = new JsonFactory();
        JsonGenerator jsonGenerator = factory.createGenerator(new File(path), JsonEncoding.UTF8);

        jsonGenerator.writeStartArray();
        for (Entrenador entrenador: entrenadores) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("nombre", entrenador.obtenerNombre());
            jsonGenerator.writeBooleanField("ganador", entrenador.esGanador());

            jsonGenerator.writeArrayFieldStart("pokemones");
            for (Pokemon pokemon : entrenador.obtenerPokemones()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", pokemon.obtenerId());
                jsonGenerator.writeNumberField("vidaRestante", pokemon.obtenerVidaActual());
                for (Estados estado : pokemon.obtenerEstados()) {
                    if (estado != Estados.NORMAL) {
                        jsonGenerator.writeStringField("estado", estadoString(estado));
                    }
                }
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();

            jsonGenerator.writeFieldName("items");
            jsonGenerator.writeStartObject();
            for (Item item : entrenador.obtenerItems()) {
                int cantidad = item.obtenerCantidad();
                if (cantidad > 0)
                    jsonGenerator.writeNumberField(Integer.toString(item.obtenerId()), cantidad);
            }
            jsonGenerator.writeEndObject();

            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.close();
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

