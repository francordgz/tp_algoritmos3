package src.main;

import src.main.Clima.*;
import src.main.Item.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.main.Enums.Estados;
import src.main.Enums.TipoModificacion;

public class Juego {

    private AdministradorDeTurnos administrador;
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private Entrenador ganador;
    private Clima clima;
    private double[][] efectividades;
    private Boolean terminado;

    public Juego() {
        this.administrador = new AdministradorDeTurnos();
        this.administrador.modificarDiasDelClimaActual(1);
        this.clima = new ClimaNormal();
        this.efectividades = Constant.crearEfectividades();
        this.terminado = false;
    }

    public Entrenador obtenerPrimerEntrenador() {
        return entrenador1;
    }

    public Entrenador obtenerSegundoEntrenador() {
        return entrenador2;
    }

    public Entrenador obtenerGanador() {
        return this.ganador;
    }

    public void asignarEntrenadores(Entrenador primerEntrenador, Entrenador segundoEntrenador) {
        this.entrenador1 = primerEntrenador;
        this.entrenador2 = segundoEntrenador;
    }

    //TODO: Esto es mejorable
    public void inicializarClima() {
        Random rand = new Random();
        int probabilidad = rand.nextInt(100);
        if (probabilidad > 66) {
            probabilidad = rand.nextInt(600);
            if (probabilidad <100)
                this.modificarClima(new ClimaSoleado());
            else if (probabilidad <200)
                this.modificarClima(new ClimaLluvia());
            else if (probabilidad <300)
                this.modificarClima(new ClimaTormentaDeArena());
            else if (probabilidad <400)
                this.modificarClima(new ClimaNiebla());
            else if (probabilidad <500)
                this.modificarClima(new ClimaTormentaDeRayos());
            else
                this.modificarClima(new ClimaHuracan());
        }
    }

    public void modificarClima(Clima clima) {
        this.clima = clima;
    }

    public void efectoClimatico() {
        Pokemon pokemonActual = this.administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        Pokemon pokemonRival = this.administrador.obtenerEntrenadorRivalActual().obtenerPokemonActual();
        this.clima.efectoClimatico(pokemonActual);
        this.clima.efectoClimatico(pokemonRival);
    }

    public void actualizarClima() {
        Integer dias = this.administrador.obtenerDiasDelClimaActual();

        if (dias == 5)
            this.clima = new ClimaNormal();
        else
            this.administrador.modificarDiasDelClimaActual(dias + 1);
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

    public void cambiarTurno() {
        this.administrador.cambiarTurno();
    }

    public void usarItem(int item, int pokemon) {
        this.administrador.obtenerEntrenadorActual().usarItem(item, pokemon);
    }

    public void usarHabilidad(int habilidad) {
        Entrenador entrenadorActual = this.administrador.obtenerEntrenadorActual();
        Entrenador entrenadorRival = this.administrador.obtenerEntrenadorRivalActual();
        Pokemon actual = entrenadorActual.obtenerPokemonActual();
        Pokemon rival = entrenadorRival.obtenerPokemonActual();

        if (!actual.habilidades(habilidad).AfectarRival()) {
            actual.UsarHabilidad(habilidad, actual);
        } else {
            actual.UsarHabilidad(habilidad, rival);
        }
        if (actual.tieneEstado(Estados.CONFUSO)) {
            actual.actualizarEstadoConfuso();
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
            setsDeIems.get(i).add(new ItemCuracion(100, "Hiperpocion", 3));
            setsDeIems.get(i).add(new ItemCurarPorcentaje("Pocion molesta alumnos", 2, 33));
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

        double ataque = pokemonActual.atacar(habilidad, pokemonRival, efectividades);
        ataque = this.clima.afectarAtaque(pokemonActual, ataque);
        pokemonRival.recibirDanio(ataque);

        if (pokemonActual.tieneEstado(Estados.CONFUSO)) {
            pokemonActual.actualizarEstadoConfuso();
        }
        return ataque;
    }

    public void actualizarEstado(){
        Pokemon actual = this.administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        actual.actualizarEstado();
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
