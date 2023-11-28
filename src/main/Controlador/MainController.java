package src.main.Controlador;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import src.main.Controlador.Eventos.EligeHabilidadEvento;
import src.main.Controlador.Eventos.EligeItemEvento;
import src.main.Controlador.Eventos.EligePokemonEvento;
import src.main.Modelo.Constant;
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
    private boolean pausarEventos = false;

    private Vistas ultimaVista = Vistas.SELECCION;

    public MainController(Juego juego, Stage primaryStage) {
        this.juego = juego;
        this.primaryStage = primaryStage;

        deserializarPartida();
        inicializarEscenasYClima();
    }

    @Override
    public void handle(Event customEvent) {
        if (pausarEventos) return;
        this.pausarEventos = true;
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

    public void habilidadHandler(int opcion) {

        if (!juego.validarHabilidad(opcion)) {
            this.vistaCampoController.setDialogo("Esa habilidad no tiene más usos!");
            this.pausarEventos = false;
            return;
        }

        if (this.juego.estaDormido()) {
            vistaCampoController.setDialogo("El Pokemon esta dormido, no puede atacar");
            this.pausarEventos = false;
            return;
        }

        Pokemon pokemonActual = this.juego.obtenerPokemonActual();
        String nombreHabilidad = pokemonActual.obtenerHabilidades().get(opcion).obtenerNombre();
        String nombre = pokemonActual.obtenerNombre();
        this.vistaCampoController.setDialogo(nombre + " ha usado " + nombreHabilidad + "!");

        esperar(2, () -> {
            if (!this.juego.puedeAtacarParalisis()) {
                vistaCampoController.setDialogo(nombre + " esta paralizado y no ataca!");
            } else if (this.juego.seLastimaASiMismo()) {
                vistaCampoController.setDialogo(nombre + " se lastima a si mismo en la confusion!");
            } else {
                Double efectividad = this.juego.usarHabilidad(opcion);
                if (efectividad == null) this.vistaCampoController.setDialogo(nombre + " se cambia su estado!");
                else if (efectividad == Constant.TERCIO) this.vistaCampoController.setDialogo(nombre + " cambia el clima!");
                else this.vistaCampoController.mostrarEfectividad(efectividad);
            }

            this.cambiarTurno();
        });
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
            this.pausarEventos = false;
            return;
        }
        this.juego.usarItem(this.itemSeleccionado, opcion);
        this.itemSeleccionado = null;
        this.cambiarTurno();
    }

    private void cambiarPokemon(int opcion) {
        if(this.juego.pokemonEstaMuerto(opcion)) {
            this.vistaPokemonesController.setDialogo(this.juego.pokemonObtenerNombre(opcion) + " esta muerto!");
            return;
        }
        this.vistaPokemonesController.setDialogo(this.juego.pokemonObtenerNombre(opcion) + " seleccionado!");
        this.juego.cambiarPokemon(opcion);
        this.cambiarTurno();
    }

    private void seleccionarPrimerPokemon(int opcion) {
        String nombre;
        Entrenador entrenador = this.juego.obtenerPrimerEntrenador();

        if (!entrenador.tienePokemonActual()) {
            nombre = entrenador.cambiarPokemon(opcion);
            this.vistaPokemonesController.setDialogo("Has eleigdo a " + nombre + "!");

            esperar(2, () -> {
                Entrenador entrenadorRival = MainController.this.juego.obtenerSegundoEntrenador();
                Scene escena = this.getEscena(this.ultimaVista);
                transicion(escena, escena, () -> vistaPokemonesController.llenarLista(entrenadorRival.obtenerPokemones()), 1);
                MainController.this.vistaPokemonesController.setDialogo("Elige un POKéMON");
            });
            return;
        }

        entrenador = this.juego.obtenerSegundoEntrenador();
        nombre = entrenador.cambiarPokemon(opcion);
        this.vistaPokemonesController.setDialogo("Has eleigdo a " + nombre + "!");

        this.inicializarBatalla();
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
        //crearInforme();
        this.primaryStage.close();
    }

    private void esperar(int segundos, Runnable codigo) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(segundos));
        pauseTransition.setOnFinished(event -> codigo.run());
        pauseTransition.play();
    }

    private void volverHandler() {
        this.itemSeleccionado = null;
        cambiarEscena(Vistas.CAMPO, 0);
    }

    private void cambiarTurno() {
        esperar(2, this::cambiarTurnoWaitRun);
    }

    private void cambiarTurnoWaitRun() {
        if (this.cambiaElTurno) this.juego.cambiarTurno();
        else this.cambiaElTurno = true;

        this.juego.efectoClimatico();
        this.juego.actualizarEstadoPokemonActual();

        Entrenador actual = this.juego.obtenerEntrenadorActual();
        if (juego.terminado()) {
            terminar();
            return;
        }

        if (this.juego.pokemonActualTieneEstado(Estados.MUERTO)) {
            cambiarEscena(Vistas.SELECCION);
            this.vistaPokemonesController.llenarLista(actual.obtenerPokemones());

            String nombre = this.juego.pokemonObtenerNombreActual();
            this.vistaPokemonesController.setDialogo(nombre + "ha muerto! Seleccione otro Pokemon");

            this.cambiaElTurno = false;
        } else cambiarEscena(Vistas.CAMPO);
    }

    public void actualizarDatos() {
        Entrenador actual = this.juego.obtenerEntrenadorActual();
        Entrenador rival = this.juego.obtenerEntrenadorRival();
        this.vistaCampoController.setDatos(actual, rival);
        this.vistaCampoController.setClima(this.juego.obtenerClima().getNombre());
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
        this.juego.efectoClimatico();
        this.cargarEscena("Pokemones");
        this.cambiarEscena(Vistas.CAMPO, 4);
    }

    private void cambiarEscena(Vistas escena, int segundos) {
        transicion(getEscena(this.ultimaVista), getEscena(escena), this::actualizarDatos, segundos);
        this.ultimaVista = escena;
    }

    private void cambiarEscena(Vistas escena) {
        cambiarEscena(escena, 1);
    }

    private Scene getEscena(Vistas escena) {
        if (escena == Vistas.CAMPO) return vistaCampoController.getEscena();
        if (escena == Vistas.ITEMS) return vistaItemsController.getEscena();
        if (escena == Vistas.POKEMONES) cargarEscena("Pokemones");
        else cargarEscena("PrimeraSeleccion");
        return this.vistaPokemonesController.getEscena();
    }

    private void transicion(Scene fromScene, Scene toScene, Runnable codigo, int segundos) {
        // Create fade transitions
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(segundos), fromScene.getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        if (segundos == 0) segundos = 1;
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(segundos), toScene.getRoot());
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        // Set the action on scene change
        fadeOut.setOnFinished(e -> {
            codigo.run();
            this.pausarEventos = false;
            fadeIn.play();
            primaryStage.setScene(toScene);
        });

        fadeOut.play();
    }


    private void inicializarEscenasYClima() {
        cargarEscena("PrimeraSeleccion");
        cargarEscena("Campo");
        cargarEscena("Items");
        vistaCampoController.setClima(this.juego.inicializarClima().getNombre());

        Scene escena = getEscena(this.ultimaVista);
        transicion(escena, escena,
                () -> vistaPokemonesController.llenarLista(this.juego.obtenerPrimerEntrenador().obtenerPokemones()), 1);
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

        String cssPath = "/Stylesheets/" + nombreCSS + ".css";
        escena.getStylesheets().add(Objects.requireNonNull(getClass()
                .getResource(cssPath)).toExternalForm());
    }
}