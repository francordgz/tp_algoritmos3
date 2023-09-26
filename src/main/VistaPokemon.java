package src.main;

public class VistaPokemon extends VistaJuego {
    static public void mostarCampo(Entrenador entrenadorActual, Entrenador entrenadorRival) {
        System.out.println("Rival: " + entrenadorRival.obtenerNombre());
        infoPokemon(entrenadorRival.obtenerPokemonActual(), false);

        System.out.println("Tu: " + entrenadorActual.obtenerNombre());
        infoPokemon(entrenadorActual.obtenerPokemonActual(), true);
    }

    static public void mostrarPokemon(Entrenador entrenador) {
        infoPokemon(entrenador.obtenerPokemonActual(), false);
    }

    // Devuelve la cantidad de Pokemones
    static public int mostrarTodosLosPokemones(Entrenador entrenador) {

        int i = 1;

        System.out.println("Seleccione una opcion:");
        for (Pokemon pokemon : entrenador.obtenerPokemones()) {
                System.out.println(i + ":");
                infoPokemon(pokemon, true);
                i++;
        }
        System.out.println((0)+ ": Volver atrás");

        return i;
    }

    private static void infoPokemon(Pokemon pokemon, Boolean mostrarAtaqueDefensa) {

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

    private static String estadoString(estados estadoActual) {
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
