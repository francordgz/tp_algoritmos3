package src.main.Modelo;

import src.main.Modelo.Clima.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import src.main.Modelo.Enums.Estados;
import src.main.Modelo.Serializacion.PartidaDeserializer;

public class Juego {
    private final AdministradorDeTurnos administrador;
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private Clima clima;
    private Boolean terminado;

    public Juego() {
        this.administrador = new AdministradorDeTurnos();
        this.administrador.modificarDiasDelClimaActual(1);
        this.clima = new ClimaNormal();
        this.terminado = false;
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

    public Entrenador obtenerGanador() {
        Entrenador entrenador = this.entrenador1;
        if (entrenador.esGanador())
            return entrenador;

        return this.entrenador2;
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
        Pokemon pokemonActual = administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        Pokemon pokemonRival = administrador.obtenerEntrenadorRivalActual().obtenerPokemonActual();

        if (pokemonActual.tieneEstado(Estados.CONFUSO))
            pokemonActual.actualizarEstadoConfuso();

        double ataque = pokemonActual.atacar(habilidad, pokemonRival);
        ataque = this.clima.afectarAtaque(pokemonActual, ataque);

        if (pokemonActual.tieneEstado(Estados.PARALIZADO)) {
            Boolean probabilidad = calcularProbabilidad();
            if (!probabilidad)
                ataque = 0;
        }

        pokemonRival.recibirAtaque(ataque);

        return ataque;
    }

    private Boolean calcularProbabilidad(){
        Random rand = new Random();
        int probabilidad = rand.nextInt(2);

        return probabilidad == 1;
    }

    public Boolean usarHabilidad(int habilidad) {
        Boolean probabilidad = calcularProbabilidad();
        Entrenador entrenadorActual = this.administrador.obtenerEntrenadorActual();
        Entrenador entrenadorRival = this.administrador.obtenerEntrenadorRivalActual();

        Pokemon pokemonActual = entrenadorActual.obtenerPokemonActual();
        Pokemon pokemonRival = entrenadorRival.obtenerPokemonActual();

        if (pokemonActual.tieneEstado(Estados.CONFUSO))
            pokemonActual.actualizarEstadoConfuso();

        if (pokemonActual.tieneEstado(Estados.PARALIZADO) && !probabilidad)
            return false;

        if (!pokemonActual.habilidades(habilidad).AfectarRival())
            pokemonActual.UsarHabilidad(habilidad, pokemonActual);
        else
            pokemonActual.UsarHabilidad(habilidad, pokemonRival);

        return true;
    }

    public Boolean usarHabilidadClima(int habilidad) {
        Boolean probabilidad = calcularProbabilidad();
        Entrenador entrenadorActual = this.administrador.obtenerEntrenadorActual();
        Pokemon pokemonActual = entrenadorActual.obtenerPokemonActual();

        if (pokemonActual.tieneEstado(Estados.CONFUSO))
            pokemonActual.actualizarEstadoConfuso();

        if (pokemonActual.tieneEstado(Estados.PARALIZADO) && !probabilidad)
            return false;

        this.modificarClima(pokemonActual.habilidades(habilidad).modificarClima());

        return true;
    }

    public Boolean validarHabilidad(int opcion) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.validarHabilidad(opcion);
    }

    public String cambiarPokemon(Entrenador entrenador, Integer opcion) {
        return entrenador.cambiarPokemon(opcion);
    }

    public Integer obtenerCantidadDePokemones(Entrenador entrenador) {
        return entrenador.obtenerCantidadDePokemones();
    }

    public void actualizarEstadoPokemonActual(){
        Pokemon actual = this.administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        actual.actualizarEstado();
    }

    public Boolean pokemonActualTieneEstado(Estados estado) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.pokemonActualTieneEstado(estado);
    }

    public Boolean pokemonRivalTieneEstado(Estados estado) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorRivalActual();
        return entrenador.pokemonActualTieneEstado(estado);
    }

    public Boolean pokemonEstaMuerto(Integer opcion) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.pokemonEstaMuerto(opcion);
    }

    public Boolean validarPokemon(Integer opcion) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.validarPokemon(opcion, entrenador.obtenerPokemonActual());
    }

    public void usarItem(int item, int pokemon) {
        this.administrador.obtenerEntrenadorActual().usarItem(item, pokemon);
    }

    public Boolean validarItem(Integer opcion) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.validarItem(opcion);
    }

    public Integer obtenerCantidadDeItems() {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.obtenerCantidadDeItems();
    }

    public Boolean itemAplicable(Integer opcion, Integer pokemon) {
        Entrenador entrenadorActual = this.administrador.obtenerEntrenadorActual();
        return entrenadorActual.itemAplicable(opcion, pokemon);
    }

    public void rendirse() {
        this.administrador.obtenerEntrenadorRivalActual().marcarComoGanador();
        this.terminado = true;
    }

    public boolean terminado() {
        if (!administrador.obtenerEntrenadorActual().tienePokemonesConVida()) {
            obtenerEntrenadorRival().marcarComoGanador();
            return true;
        }
        return this.terminado;
    }

    public void deserializarPartida(String partidaJSON, String pokemonsJSON, String habilidadesJSON, String itemsJSON) {
        try {
            PartidaDeserializer partidaDeserializer = new PartidaDeserializer(
                    partidaJSON, pokemonsJSON, habilidadesJSON, itemsJSON
            );

            List<Entrenador> entrenadores = partidaDeserializer.deserealizarPartida();
            this.asignarEntrenadores(entrenadores.get(0), entrenadores.get(1));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error al leer archivos JSON");
        }
    }
}
