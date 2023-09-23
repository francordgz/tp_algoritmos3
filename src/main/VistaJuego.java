package src.main;

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
        Pokemon pokemonRival = entrenadorRival.obtenerPokemonActual();
        System.out.println(pokemonRival.obtenerNombre() + ":");
        System.out.println("Vida: " + pokemonRival.vidaActual);
        System.out.println("Tipo: " + pokemonRival.obtenerTipo());
        System.out.println("Nivel: " + pokemonRival.obtenerNivel());

        estados estadoActual = pokemonRival.obtenerEstado();
        if(estadoActual != estados.NORMAL) {
            System.out.println("Estado: " + estadoString(estadoActual));
        }

        System.out.println("Tu: " + entrenadorActual.getNombre());
        Pokemon pokemonActual = entrenadorActual.obtenerPokemonActual();
        System.out.println(pokemonActual.obtenerNombre() + ":");
        System.out.println("Vida: " + pokemonActual.vidaActual);
        System.out.println("Tipo: " + pokemonActual.obtenerTipo());
        System.out.println("Nivel: " + pokemonActual.obtenerNivel());

        estadoActual = pokemonActual.obtenerEstado();
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

}
