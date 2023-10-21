package src.main;

import src.main.Enums.Climas;
import src.main.Item.*;

import java.util.ArrayList;
import java.util.List;

import src.main.Enums.TipoModificacion;

public class Juego {

    private AdministradorDeTurnos administrador;
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private Entrenador ganador;
    private Climas climas;
    private double[][] efectividades;
    private Boolean terminado;

    public Juego() {
        this.efectividades = Constant.crearEfectividades();
        this.administrador = new AdministradorDeTurnos();
        this.terminado = false;
    }

    public void inicializarTurnos() {
        this.administrador.asignarPrimerTurno(this.entrenador1, this.entrenador2);
    }

    public void asignarEntrenadores(Entrenador primerEntrenador, Entrenador segundoEntrenador) {
        this.entrenador1 = primerEntrenador;
        this.entrenador2 = segundoEntrenador;
    }

    public Entrenador obtenerGanador() { return this.ganador; }

    public Entrenador obtenerPrimerEntrenador() { return entrenador1; }

    public Entrenador obtenerSegundoEntrenador() { return entrenador2; }

    public Entrenador obtenerEntrenadorActual() { return this.administrador.obtenerEntrenadorActual(); }

    public Entrenador obtenerEntrenadorRival() { return this.administrador.obtenerEntrenadorRivalActual(); }

    public void usarItem(int item, int pokemon) {
        this.administrador.obtenerEntrenadorActual().usarItem(item, pokemon);
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

        List<List<Item>> setsDeIems = new ArrayList<List<Item>>();
        
        for (int i = 0; i < 2; i ++) {
            setsDeIems.add(new ArrayList<Item>());
            setsDeIems.get(i).add(new ItemCuracion(20, "Pocion", 3));
            setsDeIems.get(i).add(new ItemCuracion(50, "MegaPocion", 2));
            setsDeIems.get(i).add(new ItemCuracion(100, "Hiperpocion", 1));
            setsDeIems.get(i).add(new ItemEstadistica("Ataque", TipoModificacion.ATAQUE, 2));
            setsDeIems.get(i).add(new ItemEstadistica("Defensa", TipoModificacion.DEFENSA, 1));
            setsDeIems.get(i).add(new ItemEstado("CuraTodo", 3));
            setsDeIems.get(i).add(new ItemRevivir("Revivir", 1));
        }
        List<Item> primerSetItems = setsDeIems.get(0);
        for (Item item: primerSetItems) { entrenador1.agregarItem(item); }

        List<Item> segundoSetItems = setsDeIems.get(1);
        for (Item item: segundoSetItems) { entrenador2.agregarItem(item); }
    }

    public double atacar(int habilidad) {
        Pokemon pokemonActual = administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        Pokemon pokemonRival = administrador.obtenerEntrenadorRivalActual().obtenerPokemonActual();
        return pokemonActual.atacar(habilidad, pokemonRival, efectividades);
    }

    public void actualizarEstado(){
        this.administrador.obtenerEntrenadorActual().obtenerPokemonActual().actualizarEstado();
    }

    public void cambiarTurno() {
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
