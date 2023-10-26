package src.main.Vista;

import src.main.Entrenador;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Pokemon;

import java.util.List;

public class VistaPokemon extends Vista {
    static public void mostrarCampo(Entrenador entrenadorActual, Entrenador entrenadorRival) {
        imprimir("\nRival: " + entrenadorRival.obtenerNombre());
        infoPokemon(entrenadorRival.obtenerPokemonActual(), false);
        imprimir("");

        imprimir("Tu: " + entrenadorActual.obtenerNombre());
        infoPokemon(entrenadorActual.obtenerPokemonActual(), false);
    }

    static public void mostrarPokemones(Entrenador entrenador, boolean seleccionObligatoria) {
        int i = 1;
        if (seleccionObligatoria) imprimir("\n" + entrenador.obtenerNombre() + ", seleccione un Pokemon:\n");
        else imprimir("\nSeleccione un Pokemon:\n");

        for (Pokemon pokemon : entrenador.obtenerPokemones()) {
                imprimir(i + ":");
                infoPokemon(pokemon, true);
                i++;
        }

        if (!seleccionObligatoria) imprimir("0: Volver atrás");
    }

    private static void infoPokemon(Pokemon pokemon, Boolean mostrarAtaqueDefensa) {
        StringBuilder estadosPokemon = new StringBuilder();
        imprimir(pokemon.obtenerNombre() + ":");
        imprimir("Vida: " + pokemon.obtenerVidaActual() +
                ", Tipo: " + tipoString(pokemon.obtenerTipo()) +
                ", Nivel: " + pokemon.obtenerNivel());
        List<Estados> estados = pokemon.obtenerEstados();
        if(!pokemon.tieneEstado(Estados.NORMAL) && !pokemon.tieneEstado(Estados.MUERTO)) {
            for (Estados estado : estados) {
                estadosPokemon.append(estadoString(estado)).append(", ");
            }
            estadosPokemon = new StringBuilder(estadosPokemon.substring(0, estadosPokemon.length() - 2));
            imprimir("Estados: " + estadosPokemon);
        }
        if (mostrarAtaqueDefensa) {
            imprimir("Ataque: " + pokemon.obtenerAtaque() + ", Defensa: " + pokemon.obtenerDefensa());
        }
    }

    private static String estadoString(Estados estadoActual) {
        return switch (estadoActual) {
            case NORMAL -> "Normal";
            case ENVENENADO -> "Envenenado";
            case DORMIDO -> "Dormido";
            case PARALIZADO -> "Paralizado";
            case MUERTO -> "Muerto";
            case CONFUSO -> "Confuso";
            default -> "Desconocido";
        };
    }

    private static String tipoString(Tipo tipo) {
        return switch (tipo) {
            case AGUA -> "Agua";
            case BICHO -> "Bicho";
            case DRAGON -> "Dragón";
            case RAYO -> "Electrico";
            case FANTASMA -> "Fantasma";
            case FUEGO -> "Fuego";
            case HIELO -> "Hielo";
            case LUCHA -> "Lucha";
            case NORMAL -> "Normal";
            case PLANTA -> "Planta";
            case PSIQUICO -> "Psíquico";
            case ROCA -> "Roca";
            case TIERRA -> "Tierra";
            case VENENO -> "Veneno";
            case VOLADOR -> "Volador";
            default -> "Desconocido";
        };
    }
}
