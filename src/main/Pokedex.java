package src.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

enum Tipo {
    RAYO, PLANTA, VENENO, FUEGO, DRAGON, AGUA, PSIQUICO, FANTASMA, BICHO
}
public class Pokedex {
    Map<String, List<Object>> pokemonMap;

    public Pokedex() {
        this.pokemonMap = new HashMap();

        //PIKACHU
        agregarPokemon(pokemonMap, "Pikachu",Tipo.RAYO, 100, 30, 25, 15,
                "Este Pokémon es conocido por su cola en forma de rayo y su capacidad para generar electricidad.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 10, false),
                        new HabilidadAtaque("Impactrueno", "Rayo", 1, 120, true),
                        new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10),
                        new HabilidadEstado("Paralizar",2,estado.PARALIZAR)
                ));

        agregarPokemon(pokemonMap, "Bulbasur", Tipo.PLANTA, 120, 40, 10, 10,
                "Bulbasur lleva una planta en su espalda, que crece a medida que evoluciona.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 10, false),
                        new HabilidadAtaque("Latigo cepa", "Planta", 2, 90, true),
                        new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10),
                        new HabilidadEstado("Envenenar", 2, estado.ENVENENAR)
                ));

        agregarPokemon(pokemonMap, "Venusar", Tipo.VENENO, 70, 100, 20, 12,
                "Venusar es la evolución final de Bulbasur y tiene una flor gigante en su espalda.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 5, 25, false),
                        new HabilidadAtaque("Latigo cepa", "Planta", 3, 150, true),
                        new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 5, 15),
                        new HabilidadEstado("Envenenar", 5, estado.ENVENENAR)
                ));

        agregarPokemon(pokemonMap, "Charmander", Tipo.FUEGO, 100, 50, 35, 25,
                "Charmander es un pequeño dragón de fuego que evoluciona en Charizard.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 10, false),
                        new HabilidadAtaque("Lanzallamas", "Fuego", 2, 100, true),
                        new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10),
                        new HabilidadEstado("Paralizar", 2, estado.PARALIZAR)
                ));

        agregarPokemon(pokemonMap, "Charizard", Tipo.DRAGON, 120, 25, 15, 50,
                "Charizard es un poderoso dragón volador con aliento de fuego.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 10, false),
                        new HabilidadAtaque("Lanzallamas", "Fuego", 4, 125, true),
                        new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10),
                        new HabilidadEstado("Paralizar", 4, estado.PARALIZAR)
                ));

        agregarPokemon(pokemonMap, "Squirtle", Tipo.AGUA, 150, 40, 18, 17,
                "Squirtle es un Pokémon tortuga acuático con la habilidad de lanzar agua desde su boca.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 10, false),
                        new HabilidadAtaque("Pistola agua", "Agua", 2, 90, true),
                        new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 2, 10),
                        new HabilidadEstado("Dormir", 1, estado.DORMIR)
                ));

        agregarPokemon(pokemonMap, "Magikarp", Tipo.AGUA, 10, 10, 10, 15,
                "Magikarp es un Pokémon débil que se transforma en Gyarados cuando evoluciona.",
                Arrays.asList(
                        new HabilidadAtaque("Salpicar", "Normal", 1, 0, false),
                        new HabilidadAtaque("Giro rápido", "Agua", 2, 60, true),
                        new HabilidadEstadistica("Aumentar ataque", atributos.ATAQUE, 1, 5),
                        new HabilidadEstado("Ninguno", 0, estado.NINGUNO)
                ));

        agregarPokemon(pokemonMap, "Raichu", Tipo.RAYO, 150, 30, 25, 15,
                "Raichu es la evolución de Pikachu y es conocido por su velocidad y poder eléctrico.",
                Arrays.asList(
                        new HabilidadAtaque("Impactrueno", "Rayo", 3, 110, true),
                        new HabilidadAtaque("Ataque rápido", "Normal", 2, 60, false),
                        new HabilidadEstadistica("Aumentar velocidad", atributos.VELOCIDAD, 2, 15),
                        new HabilidadEstado("Ninguno", 0, estado.NINGUNO)
                ));

        agregarPokemon(pokemonMap, "Kadabra", Tipo.PSIQUICO, 140, 35, 30, 20,
                "Kadabra es un Pokémon psíquico con habilidades mentales poderosas.",
                Arrays.asList(
                        new HabilidadAtaque("Confusión", "Psiquico", 3, 90, true),
                        new HabilidadAtaque("Psicoonda", "Psiquico", 4, 120, true),
                        new HabilidadEstadistica("Aumentar defensa", atributos.DEFENSA, 2, 20),
                        new HabilidadEstado("Dormir", 1, estado.DORMIR)
                ));

        agregarPokemon(pokemonMap, "Clefable", Tipo.FANTASMA, 30, 30, 20, 15,
                "Clefable es un Pokémon de tipo hada conocido por su gracia y encanto.",
                Arrays.asList(
                        new HabilidadAtaque("Encanto", "Hada", 2, 70, true),
                        new HabilidadAtaque("Beso mágico", "Hada", 3, 80, false),
                        new HabilidadEstadistica("Aumentar defensa", atributos.DEFENSA, 2, 15),
                        new HabilidadEstado("Ninguno", 0, estado.NINGUNO)
                ));
        agregarPokemon(pokemonMap, "Ekans", Tipo.VENENO, 120, 35, 30, 20,
                "Ekans es una serpiente venenosa que puede paralizar a sus presas.",
                Arrays.asList(
                        new HabilidadAtaque("Mordisco", "Veneno", 3, 50, false),
                        new HabilidadAtaque("Acido","Veneno", 2, 70, true),
                        new HabilidadEstadistica("Aumentar velocidad", atributos.VELOCIDAD, 2, 10),
                        new HabilidadEstado("Paralizar", 2, estado.PARALIZAR)
                ));

        agregarPokemon(pokemonMap, "Rattata", Tipo.BICHO, 100, 40, 35, 45,
                "Rattata es un roedor ágil y astuto con dientes afilados.",
                Arrays.asList(
                        new HabilidadAtaque("Mordisco", "Normal", 3, 60, false),
                        new HabilidadAtaque("Hipercolmillo", "Normal", 4, 80, true),
                        new HabilidadEstadistica("Aumentar velocidad", atributos.VELOCIDAD, 2, 10),
                        new HabilidadEstado("Ninguno", 0, estado.NINGUNO)
                ));
    }

    public Pokemon crearPokemon(String nombre) {
        if (pokemonMap.containsKey(nombre)) {
            List<Object> pokemonData = pokemonMap.get(nombre);
            return new Pokemon(
                    nombre,
                    (Tipo) pokemonData.get(0),
                    (int)(Integer) pokemonData.get(1),
                    (int)(Integer) pokemonData.get(2),
                    (int)(Integer) pokemonData.get(3),
                    (int)(Integer) pokemonData.get(4),
                    (String) pokemonData.get(5),
                    (List<Habilidad>) pokemonData.get(6)
            );
        } else {
            throw new IllegalArgumentException("Pokemon no encontrado: " + nombre);
        }
    }
    // Auxiliares:

    private static void agregarPokemon(Map<String, List<Object>> map, String nombre, Tipo tipo, int vidaMaxima, int defensa, int ataque, int velocidad, String historia, List<Habilidad> habilidades) {
        List<Object> values = new ArrayList<Object>();
        values.add(tipo);
        values.add(vidaMaxima);
        values.add(defensa);
        values.add(ataque);
        values.add(velocidad);
        values.add(historia);
        values.add(habilidades);
        map.put(nombre, values);
    }

}