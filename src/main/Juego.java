package src.main;

import src.main.Clima.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import src.main.Enums.Estados;
import src.main.Serializacion.PartidaDeserializer;

public class Juego {
    private final AdministradorDeTurnos administrador;
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private Clima clima;
    private final double[][] efectividades;
    private Boolean terminado;

    public Juego() {
        this.administrador = new AdministradorDeTurnos();
        this.administrador.modificarDiasDelClimaActual(1);
        this.clima = new ClimaNormal();
        this.efectividades = Constant.crearEfectividades();
        this.terminado = false;
    }

    public void deserializarPartida(String partidaJSON, String pokemonsJSON, String habilidadesJSON, String itemsJSON) {
        try {
            PartidaDeserializer partidaDeserializer = new PartidaDeserializer(
                    partidaJSON, pokemonsJSON, habilidadesJSON, itemsJSON
            );

            List<Entrenador> entrenadores = partidaDeserializer.deserealizarPartida();
            this.entrenador1 = entrenadores.get(0);
            this.entrenador2 = entrenadores.get(1);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error al leer archivos JSON");
        }
    }

    public Entrenador obtenerPrimerEntrenador() {
        return entrenador1;
    }

    public Entrenador obtenerSegundoEntrenador() {
        return entrenador2;
    }

    public void modificarClima(Clima clima) {
        this.clima = clima;
    }

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

    public void efectoClimatico() {
        Pokemon pokemonActual = this.administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        Pokemon pokemonRival = this.administrador.obtenerEntrenadorRivalActual().obtenerPokemonActual();

        this.clima.efectoClimatico(pokemonActual);
        this.clima.efectoClimatico(pokemonRival);
    }

    public Boolean validarHabilidad(int opcion) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.validarHabilidad(opcion);
    }

    public double atacar(int habilidad) {
        System.out.println("Atacar");
        Pokemon pokemonActual = administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        Pokemon pokemonRival = administrador.obtenerEntrenadorRivalActual().obtenerPokemonActual();

        if (pokemonActual.tieneEstado(Estados.CONFUSO))
            pokemonActual.actualizarEstadoConfuso();

        double ataque = pokemonActual.atacar(habilidad, pokemonRival, efectividades);
        ataque = this.clima.afectarAtaque(pokemonActual, ataque);

        if (pokemonActual.tieneEstado(Estados.PARALIZADO)) {
            Boolean probabilidad = calcularProbabilidad();
            if (!probabilidad)
                ataque = 0;
        }

        pokemonRival.recibirDanio(ataque);
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

    public void actualizarEstado(){
        Pokemon actual = this.administrador.obtenerEntrenadorActual().obtenerPokemonActual();
        actual.actualizarEstado();
    }

    public Boolean pokemonActualTieneEstado(Estados estado) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.pokemonActualTieneEstado(estado);
    }

    public void usarItem(int item, int pokemon) {
        this.administrador.obtenerEntrenadorActual().usarItem(item, pokemon);
    }

    public Integer obtenerCantidadDeItems() {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.obtenerCantidadDeItems();
    }

    public Boolean validarItem(Integer opcion) {
        Entrenador entrenador = this.administrador.obtenerEntrenadorActual();
        return entrenador.validarItem(opcion);
    }

    public Boolean itemAplicable(Integer opcion, Integer pokemon) {
        Entrenador entrenadorActual = this.administrador.obtenerEntrenadorActual();

        return entrenadorActual.puedeAplicarItem(opcion, pokemon);
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

    public Entrenador obtenerGanador() {
        Entrenador entrenador = this.entrenador1;
        if (entrenador.esGanador()) return entrenador;
        return this.entrenador2;
    }
}
