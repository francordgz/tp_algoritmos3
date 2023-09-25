package src.main;

import java.util.LinkedList;
import java.util.List;

public class VistaPokemon extends VistaJuego{
    public void mostarCampo(Entrenador entrenadorActual, Entrenador entrenadorRival) {
        System.out.println("Rival: " + entrenadorRival.getNombre());
        infoPokemon(entrenadorRival.obtenerPokemonActual(), false);

        System.out.println("Tu: " + entrenadorActual.getNombre());
        infoPokemon(entrenadorActual.obtenerPokemonActual(), true);
    }

    // Devuelve la cantidad de Pokemones
    public int mostarPokemones(Entrenador entrenador) {
        List<Pokemon> muertos =  new LinkedList<Pokemon>();
        int i = 0;

        System.out.println("Seleccione una opcion:");

        for (Pokemon pokemon : entrenador.obtenerPokemones()) {
                i++;
                System.out.println(i + ":");
                infoPokemon(pokemon, true);
        }

        System.out.println((i + 1)+ ": Volver atrás");

        return i;
    }

    public void infoPokemon(Pokemon pokemon, Boolean mostrarAtaqueDefensa) {
        System.out.println(pokemon.obtenerNombre() + ":");
        System.out.println("Vida: " + pokemon.vidaActual +
                ", Tipo: " + tipoString(pokemon.obtenerTipo()) +
                ", Nivel: " + pokemon.obtenerNivel());
        estados estadoActual = pokemon.obtenerEstado();
        if(estadoActual != estados.NORMAL && estadoActual != estados.MUERTO) {
            System.out.println("Estado: " + estadoString(estadoActual));
        }
        if (mostrarAtaqueDefensa) {
            System.out.println("Ataque: " + pokemon.obtenerAtaque() + ", Defensa: " + pokemon.obtenerDefensa());
        }
    }

    public String estadoString(estados estadoActual) {
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
    public String tipoString(Tipo tipo) {
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
