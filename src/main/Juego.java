package src.main;

import src.main.Item.*;

import java.util.ArrayList;
import java.util.List;

import src.main.Enums.TipoModificacion;

public class Juego {

    Entrenador entrenador1;
    Entrenador entrenador2;
    Entrenador ganador;
    AdministradorDeTurnos administrador;
    Boolean terminado;
    double[][] efectividades;

    public Juego() {
        this.efectividades = Constant.crearEfectividades();
        this.administrador = new AdministradorDeTurnos();
        this.terminado = false;
    }

    public void crearPokemones() {
        Pokedex pokedex = new Pokedex();

        entrenador1.agregarPokemon(pokedex.crearPokemon("Pikachu"));
        entrenador1.agregarPokemon(pokedex.crearPokemon("Bulbasur"));
        entrenador1.agregarPokemon(pokedex.crearPokemon("Venusar"));
        entrenador1.agregarPokemon(pokedex.crearPokemon("Charmander"));
        entrenador1.agregarPokemon(pokedex.crearPokemon("Charizard"));
        entrenador1.agregarPokemon(pokedex.crearPokemon("Squirtle"));

        entrenador2.agregarPokemon(pokedex.crearPokemon("Magikarp"));
        entrenador2.agregarPokemon(pokedex.crearPokemon("Raichu"));
        entrenador2.agregarPokemon(pokedex.crearPokemon("Kadabra"));
        entrenador2.agregarPokemon(pokedex.crearPokemon("Clefable"));
        entrenador2.agregarPokemon(pokedex.crearPokemon("Ekans"));
        entrenador2.agregarPokemon(pokedex.crearPokemon("Rattata"));
    }

    public void crearItems() {

        List<Item> items = new ArrayList<Item>();

        items.add(new ItemCuracion(20, "Pocion", 3));
        items.add(new ItemCuracion(50, "MegaPocion", 2));
        items.add(new ItemCuracion(100, "Hiperpocion", 1));
        items.add(new ItemEstadistica("Ataque", TipoModificacion.ATAQUE, 2));
        items.add(new ItemEstadistica("Defensa", TipoModificacion.DEFENSA, 1));
        items.add(new ItemEstado("CuraTodo", 3));
        items.add(new ItemRevivir("Revivir", 1));

        for (Item item: items) {
            entrenador1.agregarItem(item);
            entrenador2.agregarItem(item);
        }
    }

    public Entrenador obtenerPrimerEntrenador() {
        return entrenador1;
    }

    public Entrenador obtenerSegundoEntrenador() {
        return entrenador2;
    }

    public Entrenador obtenerEntrenadorActual() {
        return this.administrador.obtenerEntrenadorActual();
    }

    public Entrenador obtenerEntrenadorRival() {
        return this.administrador.obtenerEntrenadorRivalActual();
    }

    public void inicializarTurnos() {
        this.administrador.asignarPrimerTurno(this.entrenador1, this.entrenador2);
    }

    public void usarHabilidad(int habilidad) {
        Entrenador entrenadorActual = this.administrador.obtenerEntrenadorActual();
        Entrenador entrenadorRival = this.administrador.obtenerEntrenadorRivalActual();

        if (!entrenadorActual.obtenerPokemonActual().habilidades(habilidad).AfectarRival()) {
            entrenadorActual.obtenerPokemonActual().UsarHabilidad(habilidad, entrenadorActual.obtenerPokemonActual());
        } else {
            entrenadorActual.obtenerPokemonActual().UsarHabilidad(habilidad, entrenadorRival.obtenerPokemonActual());
        }
    }


    public void atacar(int habilidad) {
        Pokemon pokemonActual = administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        Pokemon pokemonRival = administrador.obtenerEntrenadorRivalActual().obtenerPokemonActual();
        pokemonActual.atacar(habilidad, pokemonRival, efectividades);
    }

    public void usarItem(int item) {
        this.administrador.obtenerEntrenadorActual().usarItem(item);
    }


    public void usarTurno() {
        this.administrador.cambiarTurno();
    }

    public void rendirse() {
        this.ganador = this.administrador.obtenerEntrenadorRivalActual();
        this.terminado = true;
    }

    public boolean terminado() {
        if (!administrador.obtenerEntrenadorActual().tienePokemonesConVida()) {
            this.ganador = obtenerEntrenadorRival();
            return true;
        }

        return this.terminado;
    }
}
