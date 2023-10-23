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
        String estadosPokemon = "";
        imprimir(pokemon.obtenerNombre() + ":");
        imprimir("Vida: " + pokemon.obtenerVidaActual() +
                ", Tipo: " + tipoString(pokemon.obtenerTipo()) +
                ", Nivel: " + pokemon.obtenerNivel());
        List<Estados> estados = pokemon.obtenerEstados();
        if(!pokemon.tieneEstado(Estados.NORMAL) && !pokemon.tieneEstado(Estados.MUERTO)) {
            for(int i = 0; i < estados.size(); i++) {
                estadosPokemon += estadoString(estados.get(i)) + ", ";
            }
            estadosPokemon = estadosPokemon.substring(0, estadosPokemon.length() - 2);
            imprimir("Estados: " + estadosPokemon);
        }
        if (mostrarAtaqueDefensa) {
            imprimir("Ataque: " + pokemon.obtenerAtaque() + ", Defensa: " + pokemon.obtenerDefensa());
        }
    }

    private static String estadoString(Estados estadoActual) {
        switch (estadoActual) {
            case NORMAL:
                return "Normal";
            case ENVENENADO:
                return "Envenenado";
            case DORMIDO:
                return "Dormido";
            case PARALIZADO:
                return "Paralizado";
            case MUERTO:
                return "Muerto";
            case CONFUSO:
                return "Confuso";
            default:
                return "Desconocido";
        }
    }

    private static String tipoString(Tipo tipo) {
        switch (tipo) {
            case AGUA:
                return "Agua";
            case BICHO:
                return "Bicho";
            case DRAGON:
                return "Dragón";
            case RAYO:
                return "Electrico";
            case FANTASMA:
                return "Fantasma";
            case FUEGO:
                return "Fuego";
            case HIELO:
                return "Hielo";
            case LUCHA:
                return "Lucha";
            case NORMAL:
                return "Normal";
            case PLANTA:
                return "Planta";
            case PSIQUICO:
                return "Psíquico";
            case ROCA:
                return "Roca";
            case TIERRA:
                return "Tierra";
            case VENENO:
                return "Veneno";
            case VOLADOR:
                return "Volador";
            default:
                return "Desconocido";
        }
    }
}
