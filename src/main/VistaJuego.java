package src.main;

import java.util.LinkedList;
import java.util.List;

public class VistaJuego {
    public void mostrarMenu() {
        System.out.println("Selecciona una opcion");
        System.out.println("1 : Ver Campo" );
        System.out.println("2 : Usar Habilidad");
        System.out.println("3 : Usar Item");
        System.out.println("4 : Cambiar Pokemon");
        System.out.println("5 : Rendirse");
    }

    public void mostrarHabilidades(Pokemon pokemon) {
        System.out.println("Selecciona una opcion");
        int i = 1;
        for (Habilidad habilidad : pokemon.obtenerHabilidades()) {
            if (habilidad.getUsos() > 0) {
                System.out.println(i + " : " + habilidad.obtenerNombre() + " (Usos: " + habilidad.getUsos() + ")");
                i++;
            }
        }
    }

    public void mostarPokemones(Entrenador entrenador) {
        System.out.println("Selecciona una opcion");
        List<Pokemon> muertos =  new LinkedList<Pokemon>();
        int i = 1;
        for (Pokemon pokemon : entrenador.obtenerPokemones()) {
            if (pokemon.obtenerEstado() == estados.MUERTO) {
                muertos.add(pokemon);
            } else {
                System.out.println(i + " : " + pokemon.obtenerNombre() +
                        " (Tipo: " + tipoString(pokemon.obtenerTipo()) + ", Nivel: " + pokemon.obtenerNivel() + ")");
                i++;
            }
        }
        System.out.println("---");
        System.out.println("MUERTOS: ");
        for (Pokemon pokemon : muertos) {
            System.out.println(i + " : " + pokemon.obtenerNombre() +
                    " (Tipo: " + tipoString(pokemon.obtenerTipo()) + ", Nivel: " + pokemon.obtenerNivel() + ")");
        }
    }

    public void mostrarItems(Entrenador entrenador) {
        System.out.println("Selecciona una opcion");
        int i = 1;
        for (Item item : entrenador.getItems()) {
            if (item.obtenerCantidad() > 0) {
                System.out.println(i + " : " + item.obtenerNombre() + " (Cantidad: " + item.obtenerCantidad() + ")");
                i++;
            }
        }
    }

    public void mostarCampo(Entrenador entrenadorActual, Entrenador entrenadorRival) {
        System.out.println("Rival: " + entrenadorRival.getNombre());
        infoPokemon(entrenadorRival.obtenerPokemonActual());

        System.out.println("Tu: " + entrenadorActual.getNombre());
        infoPokemon(entrenadorActual.obtenerPokemonActual());
    }

    public void infoPokemon(Pokemon pokemon) {
        System.out.println(pokemon.obtenerNombre() + ":");
        System.out.println("Vida: " + pokemon.vidaActual);
        System.out.println("Tipo: " + tipoString(pokemon.obtenerTipo()));
        System.out.println("Nivel: " + pokemon.obtenerNivel());
        estados estadoActual = pokemon.obtenerEstado();
        if(estadoActual != estados.NORMAL) {
            System.out.println("Estado: " + estadoString(estadoActual));
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
