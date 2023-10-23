package src.main;
import src.main.Enums.Atributos;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Habilidad.Habilidad;
import src.main.Habilidad.HabilidadAtaque;
import src.main.Habilidad.HabilidadEstadistica;
import src.main.Habilidad.HabilidadEstado;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class Pokedex {
    private static class PokemonData {
        protected Tipo tipo;
        protected int vidaMaxima;
        protected int defensa;
        protected int ataque;
        protected int velocidad;
        protected String historia;
        protected List<Habilidad> habilidades;
        protected int nivel;

        protected PokemonData(Tipo tipo, int vidaMaxima, int defensa, int ataque, int velocidad, String historia, List<Habilidad> habilidades,int nivel) {
            this.tipo = tipo;
            this.vidaMaxima = vidaMaxima;
            this.defensa = defensa;
            this.ataque = ataque;
            this.velocidad = velocidad;
            this.historia = historia;
            this.habilidades = habilidades;
            this.nivel = nivel;
        }
    }

    Map<String, PokemonData> pokemonMap;

    public Pokedex() {
        this.pokemonMap = new HashMap<String, PokemonData>();

        //PIKACHU
        pokemonMap.put("Pikachu", new PokemonData(Tipo.RAYO, 100, 5, 25, 15,
                "Este Pokémon es conocido por su cola en forma de rayo y su capacidad para generar electricidad.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 100, false),
                        new HabilidadAtaque("Impactrueno", "Rayo", 1, 120, true),
                        new HabilidadEstadistica("Aumentar ataque", Atributos.ATAQUE, 2, 10,true),
                        new HabilidadEstado("Paralizar", 2, Estados.PARALIZADO),
                        new HabilidadEstado("Confundir", 1, Estados.CONFUSO)
                ),5
        ));

        pokemonMap.put("Bulbasur", new PokemonData(Tipo.PLANTA, 120, 10, 10, 10,
                "Bulbasur lleva una planta en su espalda, que crece a medida que evoluciona.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 100, false),
                        new HabilidadAtaque("Latigo cepa", "Planta", 2, 90, true),
                        new HabilidadEstadistica("Aumentar ataque", Atributos.ATAQUE, 2, 10,false),
                        new HabilidadEstado("Envenenar", 2, Estados.ENVENENADO),
                        new HabilidadEstado("Confundir", 1, Estados.CONFUSO)
                ),20
        ));

        pokemonMap.put("Venusar", new PokemonData(Tipo.VENENO, 70, 10, 20, 12,
                "Venusar es la evolución final de Bulbasur y tiene una flor gigante en su espalda.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 5, 100, false),
                        new HabilidadAtaque("Latigo cepa", "Planta", 3, 150, true),
                        new HabilidadEstadistica("Aumentar ataque", Atributos.ATAQUE, 5, 15, false),
                        new HabilidadEstado("Envenenar", 5, Estados.ENVENENADO),
                        new HabilidadEstado("Confundir", 1, Estados.CONFUSO)
                ),9
        ));

        pokemonMap.put("Charmander", new PokemonData(Tipo.FUEGO, 100, 5, 35, 25,
                "Charmander es un pequeño dragón de fuego que evoluciona en Charizard.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 100, false),
                        new HabilidadAtaque("Lanzallamas", "Fuego", 2, 100, true),
                        new HabilidadEstadistica("Aumentar ataque", Atributos.ATAQUE, 2, 10,true),
                        new HabilidadEstado("Paralizar", 2, Estados.PARALIZADO),
                        new HabilidadEstado("Confundir", 2, Estados.CONFUSO)
                ),6
        ));

        pokemonMap.put("Charizard", new PokemonData(Tipo.DRAGON, 120, 2, 15, 50,
                "Charizard es un poderoso dragón volador con aliento de fuego.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 100, false),
                        new HabilidadAtaque("Lanzallamas", "Fuego", 4, 125, true),
                        new HabilidadEstadistica("Aumentar ataque", Atributos.ATAQUE, 2, 10,false),
                        new HabilidadEstado("Paralizar", 4, Estados.PARALIZADO),
                        new HabilidadEstado("Confundir", 2, Estados.CONFUSO)
                ),8
        ));

        pokemonMap.put("Squirtle", new PokemonData(Tipo.AGUA, 150, 4, 18, 17,
                "Squirtle es un Pokémon tortuga acuático con la habilidad de lanzar agua desde su boca.",
                Arrays.asList(
                        new HabilidadAtaque("Golpe", "Normal", 3, 100, false),
                        new HabilidadAtaque("Pistola agua", "Agua", 2, 90, true),
                        new HabilidadEstadistica("Aumentar ataque", Atributos.ATAQUE, 2, 10,false),
                        new HabilidadEstado("DORMIDO", 1, Estados.DORMIDO),
                        new HabilidadEstado("Confundir", 2, Estados.CONFUSO)
                ),9
        ));

        pokemonMap.put("Magikarp", new PokemonData(Tipo.AGUA, 10, 1, 10, 15,
                "Magikarp es un Pokémon débil que se transforma en Gyarados cuando evoluciona.",
                Arrays.asList(
                        new HabilidadAtaque("Salpicar", "Normal", 1, 0, false),
                        new HabilidadAtaque("Giro rápido", "Agua", 2, 60, true),
                        new HabilidadEstadistica("Aumentar ataque", Atributos.ATAQUE, 1, 5,true),
                        new HabilidadEstado("Paralizar", 0, Estados.PARALIZADO),
                        new HabilidadEstado("Confundir", 1, Estados.CONFUSO)
                ),1
        ));

        pokemonMap.put("Raichu", new PokemonData(Tipo.RAYO, 150, 3, 25, 15,
                "Raichu es la evolución de Pikachu y es conocido por su velocidad y poder eléctrico.",
                Arrays.asList(
                        new HabilidadAtaque("Impactrueno", "Rayo", 3, 110, true),
                        new HabilidadAtaque("Ataque rápido", "Normal", 2, 60, false),
                        new HabilidadEstadistica("Aumentar velocidad", Atributos.VELOCIDAD, 2, 15,true),
                        new HabilidadEstado("Paralizar", 0, Estados.PARALIZADO),
                        new HabilidadEstado("Confundir", 1, Estados.CONFUSO)
                ),3
        ));

        pokemonMap.put("Kadabra", new PokemonData(Tipo.PSIQUICO, 140, 3, 30, 20,
                "Kadabra es un Pokémon psíquico con habilidades mentales poderosas.",
                Arrays.asList(
                        new HabilidadAtaque("Confusión", "Psiquico", 3, 90, true),
                        new HabilidadAtaque("Psicoonda", "Psiquico", 4, 120, true),
                        new HabilidadEstadistica("Aumentar defensa", Atributos.DEFENSA, 2, 20,false),
                        new HabilidadEstado("DORMIDO", 1, Estados.DORMIDO),
                        new HabilidadEstado("Confundir", 1, Estados.CONFUSO)
                ),5
        ));

        pokemonMap.put("Clefable", new PokemonData(Tipo.FANTASMA, 30, 3, 20, 15,
                "Clefable es un Pokémon de tipo hada conocido por su gracia y encanto.",
                Arrays.asList(
                        new HabilidadAtaque("Encanto", "Hada", 2, 70, true),
                        new HabilidadAtaque("Beso mágico", "Hada", 3, 80, false),
                        new HabilidadEstadistica("Aumentar defensa", Atributos.DEFENSA, 2, 15,true),
                        new HabilidadEstado("Envenenar", 0, Estados.ENVENENADO),
                        new HabilidadEstado("Confundir", 2, Estados.CONFUSO)
                ),9
        ));

        pokemonMap.put("Ekans", new PokemonData(Tipo.VENENO, 120, 5, 30, 20,
                "Ekans es una serpiente venenosa que puede paralizar a sus presas.",
                Arrays.asList(
                        new HabilidadAtaque("Mordisco", "Veneno", 3, 50, false),
                        new HabilidadAtaque("Acido", "Veneno", 2, 70, true),
                        new HabilidadEstadistica("Aumentar velocidad", Atributos.VELOCIDAD, 2, 10,true),
                        new HabilidadEstado("Paralizar", 2, Estados.PARALIZADO),
                        new HabilidadEstado("Confundir", 2, Estados.CONFUSO)
                ),2
        ));

        pokemonMap.put("Rattata", new PokemonData(Tipo.BICHO, 100, 4, 35, 45,
                "Rattata es un roedor ágil y astuto con dientes afilados.",
                Arrays.asList(
                        new HabilidadAtaque("Mordisco", "Normal", 3, 60, false),
                        new HabilidadAtaque("Hipercolmillo", "Normal", 4, 80, true),
                        new HabilidadEstadistica("Aumentar velocidad", Atributos.VELOCIDAD, 2, 10,false),
                        new HabilidadEstado("Paralizar", 0, Estados.PARALIZADO),
                        new HabilidadEstado("Confundir", 2, Estados.CONFUSO)
                ),2
        ));

    }

    public Pokemon crearPokemon(String nombre) {
        if (pokemonMap.containsKey(nombre)) {
            PokemonData data = pokemonMap.get(nombre);
                return new Pokemon(
                        nombre,
                        data.tipo,
                        data.vidaMaxima,
                        data.defensa,
                        data.ataque,
                        data.velocidad,
                        data.historia,
                        data.habilidades,
                        data.nivel

                );
        } else {
            throw new IllegalArgumentException("Pokemon no encontrado: " + nombre);
        }
    }
}