package BatallaPokemon.Modelo.Serializacion;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import BatallaPokemon.Modelo.Entrenador;
import BatallaPokemon.Modelo.Enums.Estados;
import BatallaPokemon.Modelo.Item.Item;
import BatallaPokemon.Modelo.Pokemon;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class InformeSerializer {
    public static String serializeJSON(List<Entrenador> entrenadores, String nombre) throws IOException, URISyntaxException {
        File informe = informeFile(nombre);
        JsonFactory factory = new JsonFactory();
        JsonGenerator jsonGenerator = factory.createGenerator(informe, JsonEncoding.UTF8);

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

        return informe.getPath();
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

    private static File informeFile(String nombre) throws URISyntaxException {
        URL resourcesURL = InformeSerializer.class.getResource("");
        assert resourcesURL != null;
        String resourcesPath = new File(resourcesURL.toURI()).getPath();
        return new File(resourcesPath, nombre);
    }
}

