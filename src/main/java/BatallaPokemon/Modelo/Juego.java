package BatallaPokemon.Modelo;

import BatallaPokemon.Modelo.Clima.*;
import BatallaPokemon.Modelo.Enums.Estados;
import BatallaPokemon.Modelo.Habilidad.HabilidadClima;

import java.util.Random;

public class Juego {
    private final AdministradorDeTurnos administrador;
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private Clima clima;

    public Juego() {
        this.administrador = new AdministradorDeTurnos();
        this.administrador.modificarDiasDelClimaActual(1);
        this.clima = new ClimaNormal();
    }

    public Entrenador obtenerPrimerEntrenador() {
        return entrenador1;
    }

    public Entrenador obtenerSegundoEntrenador() {
        return entrenador2;
    }

    public boolean turnosAsignados() {
        return this.administrador.turnosAsignados();
    }

    public Clima obtenerClima() {
        return this.clima;
    }

    public void asignarEntrenadores(Entrenador primerEntrenador, Entrenador segundoEntrenador) {
        this.entrenador1 = primerEntrenador;
        this.entrenador2 = segundoEntrenador;
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
        this.actualizarClima();
    }

    public void modificarClima(Clima clima) {
        this.clima = clima;
        this.administrador.modificarDiasDelClimaActual(1);
    }

    public Clima inicializarClima() {
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

        return clima;
    }

    public void actualizarClima() {
        Integer dias = this.administrador.obtenerDiasDelClimaActual();

        if (dias == 5)
            this.clima = new ClimaNormal();
        else
            this.administrador.modificarDiasDelClimaActual(dias + 1);
    }

    public void efectoClimatico() {
        Pokemon pokemonActual = this.administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        Pokemon pokemonRival = this.administrador.obtenerEntrenadorRivalActual().obtenerPokemonActual();

        this.clima.efectoClimatico(pokemonActual);
        this.clima.efectoClimatico(pokemonRival);
    }

    public double atacar(int habilidad) {
        Pokemon pokemonActual = this.obtenerPokemonActual();
        Pokemon pokemonRival = this.obtenerPokemonRival();

        double efectividad = Constant.calcularEfectividad(pokemonActual.obtenerTipo(), pokemonRival.obtenerTipo());

        double ataque = pokemonActual.atacar(habilidad, pokemonRival, efectividad);
        ataque = this.clima.afectarAtaque(pokemonActual, ataque);

        pokemonRival.recibirAtaque(ataque);
        return efectividad;
    }

    private Boolean calcularProbabilidad(){
        Random rand = new Random();
        int probabilidad = rand.nextInt(2);

        return probabilidad == 1;
    }

    public Boolean seLastimaASiMismo() {
        Pokemon pokemonActual = this.obtenerPokemonActual();
        if (pokemonActual.tieneEstado(Estados.CONFUSO))
            return pokemonActual.actualizarEstadoConfuso();
        return false;
    }

    public Boolean puedeAtacarParalisis() {
        Pokemon pokemonActual = this.obtenerPokemonActual();
        return !pokemonActual.tieneEstado(Estados.PARALIZADO) || calcularProbabilidad();
    }

    public Double usarHabilidad(int opcion) {
        switch (this.obtenerPokemonActual().habilidades(opcion).getTipoHabilidad()) {
            case "ataque":
                return atacar(opcion);
            case "clima":
                usarHabilidadClima(opcion);
                return (double) Constant.TERCIO;
            case "estado":
                usarHabilidadEstado(opcion);
                return (double) Constant.SIMPLE;
            case "estadistica":
                if (usarHabilidadEstadistica(opcion)) return (double) Constant.SIMPLE;
                return null;
        }
        throw new RuntimeException("Habilidad Inválida");
    }

    private void usarHabilidadEstado(int habilidad) {
        obtenerPokemonActual().usarHabilidadEstado(habilidad, this.obtenerPokemonRival());
    }

    public boolean usarHabilidadEstadistica(int habilidad) {
        return obtenerPokemonActual().usarHabilidadEstadistica(habilidad, this.obtenerPokemonRival());
    }

    public Boolean estaDormido() {
        return this.pokemonActualTieneEstado(Estados.DORMIDO);
    }

    public Boolean usarHabilidadClima(int habilidad) {
        Entrenador entrenadorActual = this.administrador.obtenerEntrenadorActual();
        Pokemon pokemonActual = entrenadorActual.obtenerPokemonActual();

        HabilidadClima habilidadClima = (HabilidadClima) pokemonActual.habilidades(habilidad);
        this.modificarClima(habilidadClima.modificarClima());

        return true;
    }

    public Boolean validarHabilidad(int opcion) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.validarHabilidad(opcion);
    }

    public void actualizarEstadoPokemonActual(){
        this.obtenerPokemonActual().actualizarEstado();
    }

    public Boolean pokemonActualTieneEstado(Estados estado) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.pokemonActualTieneEstado(estado);
    }

    public Boolean pokemonEstaMuerto(int opcion) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.pokemonEstaMuerto(opcion);
    }

    public String pokemonObtenerNombre(int opcion) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.pokemonObtenerNombre(opcion);
    }

    public String pokemonObtenerNombreActual() {
        return this.obtenerPokemonActual().obtenerNombre();
    }

    public void cambiarPokemon(int opcion) {
        this.obtenerEntrenadorActual().cambiarPokemon(opcion);
    }

    public void usarItem(int item, int pokemon) {
        this.obtenerEntrenadorActual().usarItem(item, pokemon);
    }

    public Boolean itemAplicable(Integer opcion, Integer pokemon) {
        Entrenador entrenadorActual = this.administrador.obtenerEntrenadorActual();
        return entrenadorActual.itemAplicable(opcion, pokemon);
    }

    public void rendirse() {
        this.administrador.obtenerEntrenadorRivalActual().marcarComoGanador();
    }

    public boolean terminado() {
        if (!administrador.obtenerEntrenadorActual().tienePokemonesConVida()) {
            obtenerEntrenadorRival().marcarComoGanador();
            return true;
        }
        return false;
    }

    public Pokemon obtenerPokemonActual() {
        return this.obtenerEntrenadorActual().obtenerPokemonActual();
    }

    public Pokemon obtenerPokemonRival() {
        return this.obtenerEntrenadorRival().obtenerPokemonActual();
    }
}
