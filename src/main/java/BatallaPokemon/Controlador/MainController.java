package BatallaPokemon.Controlador;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import BatallaPokemon.Controlador.Eventos.EligeHabilidadEvento;
import BatallaPokemon.Controlador.Eventos.EligeItemEvento;
import BatallaPokemon.Controlador.Eventos.EligePokemonEvento;
import BatallaPokemon.Modelo.Constant;
import BatallaPokemon.Modelo.Entrenador;
import BatallaPokemon.Modelo.Enums.Estados;
import BatallaPokemon.Modelo.Juego;
import BatallaPokemon.Modelo.Pokemon;
import BatallaPokemon.Controlador.Serializacion.InformeSerializer;
import BatallaPokemon.Controlador.Serializacion.PartidaDeserializer;

import java.io.IOException;
import java.io.InputStream;
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

    private Vistas escenaActual;

    public MainController(Juego juego, Stage primaryStage) {
        this.juego = juego;
        this.primaryStage = primaryStage;

        this.deserializarPartida();
        this.inicializarEscenasYClima();
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

        this.esperar(() -> {
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
        this.cambiarEscena(Vistas.POKEMONES);
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

    private void seleccionarPrimerPokemon(int opcion) {
        String nombre;
        Entrenador entrenador = this.juego.obtenerPrimerEntrenador();

        if (!entrenador.tienePokemonActual()) {
            nombre = entrenador.cambiarPokemon(opcion);
            this.vistaPokemonesController.setDialogo("Has eleigdo a " + nombre + "!");

            this.esperar(() -> {
                Entrenador entrenadorRival = MainController.this.juego.obtenerSegundoEntrenador();
                this.transicion(this.obtenerEscena(this.escenaActual),
                        () -> vistaPokemonesController.llenarLista(entrenadorRival.obtenerPokemones()));
                MainController.this.vistaPokemonesController.setDialogo(entrenadorRival.obtenerNombre() + ", elija un POKéMON");
            });
            return;
        }

        entrenador = this.juego.obtenerSegundoEntrenador();
        nombre = entrenador.cambiarPokemon(opcion);
        this.vistaPokemonesController.setDialogo("Has eleigdo a " + nombre + "!");
        this.esperar(() -> this.vistaPokemonesController.setDialogo("Comienza la BATALLA POKEMON!"));
        this.esperar(this::inicializarBatalla);
    }

    private void aplicarItemPokemon(int opcion) {
        if (!this.juego.itemAplicable(this.itemSeleccionado, opcion)) {
            vistaPokemonesController.setDialogo("No es posible aplicar el item debido al estado del pokemon");
            this.pausarEventos = false;
            return;
        }
        this.juego.usarItem(this.itemSeleccionado, opcion);
        this.vistaPokemonesController.setDialogo("Item aplicado!");
        this.itemSeleccionado = null;
        this.cambiarTurno();
    }

    private void cambiarPokemon(int opcion) {
        if(this.juego.pokemonEstaMuerto(opcion)) {
            this.vistaPokemonesController.setDialogo(this.juego.pokemonObtenerNombre(opcion) + " esta muerto!");
            this.pausarEventos = false;
            return;
        }
        this.vistaPokemonesController.setDialogo(this.juego.pokemonObtenerNombre(opcion) + " seleccionado!");
        this.juego.cambiarPokemon(opcion);
        this.cambiarTurno();
    }

    private void verMochilaHandler() {
        Entrenador actual = juego.obtenerEntrenadorActual();
        vistaItemsController.llenarLista(actual.obtenerItems());
        this.cambiarEscena(Vistas.ITEMS);
    }

    private void eligeItemHandler(int opcion) {
        Entrenador actual = juego.obtenerEntrenadorActual();
        this.itemSeleccionado = opcion;
        this.cambiarEscena(Vistas.POKEMONES);
        this.vistaPokemonesController.llenarLista(actual.obtenerPokemones());
        this.vistaPokemonesController.datosActual(this.juego.obtenerPokemonActual());
        this.vistaPokemonesController.setDialogo("Elige un Pokemon para aplicar item");
    }

    public void rendirseHandler() {
        this.juego.rendirse();
        this.terminar();
    }

    public void terminar() {
        crearInforme();
        String ganador = this.juego.obtenerEntrenadorRival().obtenerNombre();
        this.vistaCampoController.setDialogo(ganador + " ha ganado!!");
        this.fin();
    }

    public void fin() {
        String path = "/Imagenes/ricardofort-dancing.gif";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        Image image = new Image(imagen);
        ImageView imageView = new ImageView(image);
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene escenaFinal = new Scene(root, 600, 400);
        this.primaryStage.setTitle("Nacho subile un punto a Eze!");

        this.transicion(obtenerEscena(this.escenaActual), escenaFinal, () -> {}, 4);
        this.esperar(() -> this.primaryStage.close(), 8000);
    }

    private void volverHandler() {
        Vistas escena = Vistas.CAMPO;

        if (this.itemSeleccionado != null) {
            this.itemSeleccionado = null;
            escena = Vistas.ITEMS;
        }

        this.cambiarEscena(escena);
    }

    private void cambiarTurno() {
        this.esperar(this::cambiarTurnoWaitRun);
    }

    private void cambiarTurnoWaitRun() {
        if (this.cambiaElTurno)
            this.juego.cambiarTurno();
        else
            this.cambiaElTurno = true;

        this.juego.efectoClimatico();
        this.juego.actualizarEstadoPokemonActual();

        if (juego.terminado()) {
            this.terminar();
            return;
        }

        if (this.juego.pokemonActualTieneEstado(Estados.MUERTO)) {
            this.cambiarEscena(Vistas.SELECCION);
            this.vistaPokemonesController.llenarLista(this.juego.obtenerEntrenadorActual().obtenerPokemones());

            String nombre = this.juego.pokemonObtenerNombreActual();
            this.vistaPokemonesController.setDialogo(nombre + " ha muerto! Seleccione otro Pokemon");

            this.cambiaElTurno = false;
        } else
            this.cambiarEscena(Vistas.CAMPO);
    }

    private void esperar(Runnable codigo) {
        this.esperar(codigo, 2000);
    }

    private void esperar(Runnable codigo, int milis) {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(milis));
        pauseTransition.setOnFinished(event -> codigo.run());
        pauseTransition.play();
    }

    private void cambiarEscena(Vistas escena) {
        this.transicion(this.obtenerEscena(this.escenaActual), this.obtenerEscena(escena), this::actualizarDatos, 1);
        this.escenaActual = escena;
    }

    public void actualizarDatos() {
        Entrenador actual = this.juego.obtenerEntrenadorActual();
        Entrenador rival = this.juego.obtenerEntrenadorRival();
        this.vistaCampoController.setDatos(actual, rival);
        this.vistaCampoController.setClima(this.juego.obtenerClima().getNombre());
    }

    private void transicion(Scene scene, Runnable codigo) {
        transicion(scene, scene, codigo, 1);
    }

    private void transicion(Scene fromScene, Scene toScene, Runnable codigo, int segundos) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(segundos * 1100), fromScene.getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(segundos), toScene.getRoot());
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        fadeOut.setOnFinished(e -> {
            fadeIn.play();
            codigo.run();
            this.esperar(() -> primaryStage.setScene(toScene), 100);
        });
        fadeOut.play();

        fadeIn.setOnFinished(e -> this.pausarEventos = false);
    }

    private void inicializarBatalla() {
        this.juego.inicializarTurnos();
        this.juego.efectoClimatico();
        this.cargarEscena("Pokemones");
        this.cambiarEscena(Vistas.CAMPO);
    }

    private void inicializarEscenasYClima() {
        this.cargarEscena("PrimeraSeleccion");
        this.cargarEscena("Campo");
        this.cargarEscena("Items");
        vistaCampoController.setClima(this.juego.inicializarClima().getNombre());

        this.escenaActual = Vistas.SELECCION;
        this.transicion(obtenerEscena(this.escenaActual),
                () -> vistaPokemonesController.llenarLista(this.juego.obtenerPrimerEntrenador().obtenerPokemones()));

        String nombre = this.juego.obtenerPrimerEntrenador().obtenerNombre();
        this.vistaPokemonesController.setDialogo(nombre + ", elija un POKéMON");
    }

    private Scene obtenerEscena(Vistas escena) {
        if (escena == Vistas.CAMPO)
            return vistaCampoController.getEscena();
        if (escena == Vistas.ITEMS)
            return vistaItemsController.getEscena();
        if (escena == Vistas.POKEMONES)
            this.cargarEscena("Pokemones");
        else
            this.cargarEscena("PrimeraSeleccion");

        return this.vistaPokemonesController.getEscena();
    }

    private void cargarEscena(String nombreFXML) {
        String path = "/Vista/Vista" + nombreFXML + ".fxml";
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

    public void deserializarPartida() {
        String partidaJSON = "Serial/partida.json";
        String pokemonsJSON = "Serial/pokemons.json";
        String habilidadesJSON = "Serial/habilidades.json";
        String itemsJSON = "Serial/items.json";

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
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error al crear informe JSON");
        }
    }
}