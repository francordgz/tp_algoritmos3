package src.main.Vista;

import src.main.Entrenador;
import src.main.Enums.Estados;
import src.main.Enums.Tipo;
import src.main.Pokemon;

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

        imprimir(pokemon.obtenerNombre() + ":");
        imprimir("Vida: " + pokemon.obtenerVidaActual() +
                ", Tipo: " + tipoString(pokemon.obtenerTipo()) +
                ", Nivel: " + pokemon.obtenerNivel());
        Estados estadoActual = pokemon.obtenerEstado();
        if(estadoActual != Estados.NORMAL && estadoActual != Estados.MUERTO) {
            imprimir("Estado: " + estadoString(estadoActual));
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
