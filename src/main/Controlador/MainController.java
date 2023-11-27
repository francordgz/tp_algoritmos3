package src.main.Controlador;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.main.Controlador.Eventos.EligeHabilidadEvento;
import src.main.Controlador.Eventos.EligeItemEvento;
import src.main.Controlador.Eventos.EligePokemonEvento;
import src.main.Modelo.Entrenador;
import src.main.Modelo.Enums.Estados;
import src.main.Modelo.Juego;
import src.main.Modelo.Pokemon;
import src.main.Modelo.Serializacion.InformeSerializer;
import src.main.Modelo.Serializacion.PartidaDeserializer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainController implements EventHandler<Event> {
    Juego juego;
    Stage primaryStage;

    private Integer itemSeleccionado = null;

    private boolean cambiaElTurno = true;

    @FXML
    VistaCampoController vistaCampoController;
    @FXML
    VistaItemsController vistaItemsController;
    @FXML
    VistaPokemonesController vistaPokemonesController;

    public MainController(Juego juego, Stage primaryStage) {
        this.juego = juego;
        this.primaryStage = primaryStage;

        deserializarPartida();
        inicializarEscenasYClima();
    }

    @Override
    public void handle(Event customEvent) {
        switch (customEvent.getEventType().getName()) {
            case "Elige Habilidad":
                EligeHabilidadEvento eventoHabilidadPokemon = (EligeHabilidadEvento) customEvent;
                this.habilidadHandler(eventoHabilidadPokemon.getOpcion());
                break;
            case "Ver Pokemones":
                this.verPokemonesHandler();
                break;
            case "Elige Pokemon":
                EligePokemonEvento eventoEligePokemon = (EligePokemonEvento) customEvent;
                this.eligePokemonHandler(eventoEligePokemon.getOpcion());
                break;
            case "Ver Mochila":
                this.verMochilaHandler();
                break;
            case "Elige Item":
                EligeItemEvento eventoEligeItem = (EligeItemEvento) customEvent;
                this.eligeItemHandler(eventoEligeItem.getOpcion());
                break;
            case "Rendirse":
                this.rendirseHandler();
                break;
            case "Volver":
                this.volverHandler();
                break;
        }
    }

    public void habilidadHandler(int opcion){
        if (this.juego.pokemonActualTieneEstado(Estados.DORMIDO)) {
            vistaCampoController.setDialogo("El Pokemon esta dormido, no puede atacar");
            return;
        }

        Pokemon pokemonActual = this.juego.obtenerEntrenadorActual().obtenerPokemonActual();
        String nombreHabilidad = pokemonActual.obtenerHabilidades().get(opcion).obtenerNombre();
        String nombre = pokemonActual.obtenerNombre();
        this.vistaCampoController.setDialogo(nombre + " ha usado " + nombreHabilidad + "!");

        esperar(1);

        boolean ataqueEfectivo = atacar(opcion);
        if (!ataqueEfectivo)
            this.vistaCampoController.setDialogo(nombre + " esta paralizado! No ataca!");
        else this.vistaCampoController.titilar();

        esperar(2);

        this.cambiarTurno();
    }

    private boolean atacar(int opcion) {
        if (opcion < 2) {
            double ataque = this.juego.atacar(opcion);
            return ataque != 0 || !this.juego.pokemonActualTieneEstado(Estados.PARALIZADO);
        }

        if (opcion < 5) return this.juego.usarHabilidad(opcion);

        return this.juego.usarHabilidadClima(opcion);

    }

    private void verPokemonesHandler() {
        Entrenador actual = juego.obtenerEntrenadorActual();
        cambiarEscena(Vistas.POKEMONES);
        vistaPokemonesController.llenarLista(actual.obtenerPokemones(), actual.obtenerPokemonActual());
    }

    public void eligePokemonHandler(int opcion) {
        if (!this.juego.turnosAsignados()) {
            this.seleccionarPrimerPokemon(opcion);
            return;
        }

        if (itemSeleccionado != null) aplicarItemPokemon(opcion);
        else cambiarPokemon(opcion);
    }

    private void aplicarItemPokemon(int opcion) {
        if (!this.juego.itemAplicable(this.itemSeleccionado, opcion)) {
            vistaPokemonesController.setDialogo("No es posible aplicar el item debido al estado del pokemon");
            return;
        }
        this.juego.usarItem(this.itemSeleccionado, opcion);
        this.itemSeleccionado = null;
        this.cambiarTurno();
    }

    private void cambiarPokemon(int opcion) {
        Entrenador entrenadorActual = this.juego.obtenerEntrenadorActual();
        Pokemon pokemonSeleccionado = entrenadorActual.obtenerPokemones().get(opcion);
        if(pokemonSeleccionado.estaMuerto()) {
            this.vistaPokemonesController.setDialogo(pokemonSeleccionado.obtenerNombre() + " esta muerto!");
            return;
        }
        entrenadorActual.cambiarPokemon(opcion);
        this.cambiarTurno();
    }

    private void seleccionarPrimerPokemon(int opcion) {
        String nombre;
        Entrenador entrenador = this.juego.obtenerPrimerEntrenador();

        if (!entrenador.tienePokemonActual()) {
            nombre = entrenador.cambiarPokemon(opcion);
            this.vistaPokemonesController.setDialogo("Has eleigdo a " + nombre + "!");

            esperar(2);

            entrenador = this.juego.obtenerSegundoEntrenador();
            this.vistaPokemonesController.llenarLista(entrenador.obtenerPokemones());
            return;
        }

        entrenador = this.juego.obtenerSegundoEntrenador();
        nombre = entrenador.cambiarPokemon(opcion);
        this.vistaPokemonesController.setDialogo("Has eleigdo a " + nombre + "!");

        esperar(2);
        inicializarBatalla();
    }

    private void verMochilaHandler() {
        Entrenador actual = juego.obtenerEntrenadorActual();
        vistaItemsController.llenarLista(actual.obtenerItems());
        cambiarEscena(Vistas.ITEMS);
    }

    private void eligeItemHandler(int opcion) {
        Entrenador actual = juego.obtenerEntrenadorActual();
        this.itemSeleccionado = opcion;
        cambiarEscena(Vistas.POKEMONES);
        this.vistaPokemonesController.llenarLista(actual.obtenerPokemones());
    }

    public void rendirseHandler() {
        terminar();
    }

    public void terminar() {
        juego.obtenerEntrenadorRival().marcarComoGanador();
        //crearInforme();
        this.primaryStage.close();
    }

    private void volverHandler() {
        this.itemSeleccionado = null;
        cambiarEscena(Vistas.CAMPO);
    }

    private void cambiarTurno() {
        if (this.cambiaElTurno) this.juego.cambiarTurno();
        else this.cambiaElTurno = true;

        this.actualizarDatos();
        this.juego.efectoClimatico();

        Entrenador actual = this.juego.obtenerEntrenadorActual();

        if (!actual.tienePokemonesConVida()) {
            terminar();
            return;
        }

        if (this.juego.pokemonActualTieneEstado(Estados.MUERTO)) {
            cambiarEscena(Vistas.SELECCION);
            this.vistaPokemonesController.llenarLista(actual.obtenerPokemones());
            this.cambiaElTurno = false;
        } else cambiarEscena(Vistas.CAMPO);
    }

    public void actualizarDatos() {
        Entrenador actual = this.juego.obtenerEntrenadorActual();
        Entrenador rival = this.juego.obtenerEntrenadorRival();
        this.vistaCampoController.setDatos(actual, rival);
        this.vistaCampoController.setClima(this.juego.obtenerClima().getNombre());
    }

    private void esperar(int segundos) {
        /*
        synchronized (lock) {
            try {
                lock.wait(segundos * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        */
    }

    public void deserializarPartida() {
        String partidaJSON = "partida.json";
        String pokemonsJSON = "pokemons.json";
        String habilidadesJSON = "habilidades.json";
        String itemsJSON = "items.json";

        try {
            PartidaDeserializer partidaDeserializer = new PartidaDeserializer(
                    partidaJSON, pokemonsJSON, habilidadesJSON, itemsJSON
            );

            List<Entrenador> entrenadores = partidaDeserializer.deserealizarPartida();
            this.juego.asignarEntrenadores(entrenadores.get(0), entrenadores.get(1));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error al leer archivos JSON");
        }
    }

    private void crearInforme() {
        List<Entrenador> entrenadores = new ArrayList<>();
        entrenadores.add(juego.obtenerPrimerEntrenador());
        entrenadores.add(juego.obtenerSegundoEntrenador());

        try {
            String informePath = InformeSerializer.serializeJSON(entrenadores, "informe.json");
            System.out.println("El informe ha sido creado en: " + informePath);
        } catch (IOException e) {
            throw new RuntimeException("Error al crear informe JSON");
        }
    }

    private void inicializarBatalla() {
        this.juego.inicializarTurnos();
        this.cargarEscena("Pokemones");
        this.cambiarEscena(Vistas.CAMPO);
        actualizarDatos();
        this.juego.efectoClimatico();
    }

    private void cambiarEscena(Vistas escena) {
        switch (escena) {
            case CAMPO:
                this.primaryStage.setScene(this.vistaCampoController.getEscena());
                this.actualizarDatos();
                break;
            case ITEMS:
                this.primaryStage.setScene(this.vistaItemsController.getEscena());
                break;
            case POKEMONES:
                cargarEscena("Pokemones");
                this.primaryStage.setScene(this.vistaPokemonesController.getEscena());
                break;
            case SELECCION:
                cargarEscena("PrimeraSeleccion");
                this.primaryStage.setScene(this.vistaPokemonesController.getEscena());
        }
    }

    private void inicializarEscenasYClima() {
        cargarEscena("PrimeraSeleccion");
        cargarEscena("Campo");
        cargarEscena("Items");
        cambiarEscena(Vistas.SELECCION);
        this.vistaPokemonesController.llenarLista(this.juego.obtenerPrimerEntrenador().obtenerPokemones());
        vistaCampoController.setClima(this.juego.inicializarClima().getNombre());
    }

    private void cargarEscena(String nombreFXML) {
        String path = "/src/main/Vista/Vista" + nombreFXML + ".fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        Scene escena;
        try {
            escena = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException("Error al cargas la escena de " + nombreFXML + "!");
        }
        setEstilo(escena, nombreFXML);

        switch (nombreFXML) {
            case "Campo":
                this.vistaCampoController = loader.getController();
                this.vistaCampoController.setEscena(escena);
                break;
            case "Items":
                this.vistaItemsController = loader.getController();
                this.vistaItemsController.setEscena(escena);
                break;
            default:
                this.vistaPokemonesController = loader.getController();
                this.vistaPokemonesController.setEscena(escena);
                if (nombreFXML.equals("Pokemones")) vistaPokemonesController.setSalir();
        }
    }

    private void setEstilo(Scene escena, String nombreFXML) {
        String nombreCSS = "pokemones";
        if (nombreFXML.equals("Items")) nombreCSS = "mochila";
        if (nombreFXML.equals("Campo")) nombreCSS = "campo";

        String cssPath = "/" + nombreCSS + ".css";
        escena.getStylesheets().add(Objects.requireNonNull(getClass()
                .getResource(cssPath)).toExternalForm());
    }
}