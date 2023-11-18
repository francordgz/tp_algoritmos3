package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.main.Controlador.Eventos.EligePokemonEvento;
import src.main.Controlador.Eventos.EligePokemonEventoHandler;
import src.main.Modelo.Entrenador;
import src.main.Modelo.Juego;
import src.main.Modelo.Serializacion.InformeSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainController implements EligePokemonEventoHandler {
    Juego juego;
    Stage primaryStage;

    @FXML
    VistaCampoController vistaCampoController;
    VistaMochilaController vistaMochilaController;

    @FXML
    VistaPokemonesController vistaPokemonesController;

    public MainController(Juego juego, Stage primaryStage) {
        this.juego = juego;
        this.primaryStage = primaryStage;

        this.juego.deserializarPartida(
                "partida.json",
                "pokemons.json",
                "habilidades.json",
                "items.json"
        );

        try {
            inicializarPrimeraSeleccion();
            inicializarCampo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.primaryStage.setScene(getEscena("pokemones"));

        this.vistaPokemonesController.llenarLista(this.juego.obtenerPrimerEntrenador().obtenerPokemones());
        this.juego.inicializarClima();
    }

    private void inicializarCampo() throws IOException {
        String path = "/src/main/Vista/VistaCampo.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        Scene escena = new Scene(loader.load());
        setEstilo(escena, "campo");

        this.vistaCampoController = loader.getController();
        this.vistaCampoController.setEscena(escena);
    }

    private void inicializarMochila() throws IOException {
        String path = "/src/main/Vista/VistaMochila.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        Scene escena = new Scene(loader.load());
        setEstilo(escena, "mochila");

        this.vistaMochilaController = loader.getController();
        this.vistaMochilaController.setEscena(escena);
    }

    private void inicializarPokemones() throws IOException {
        String path = "/src/main/Vista/VistaPokemones.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        Scene escena = new Scene(loader.load());
        setEstilo(escena, "pokemones");

        this.vistaPokemonesController = loader.getController();
        this.vistaPokemonesController.setEscena(escena);
    }

    private void inicializarPrimeraSeleccion() throws IOException {
        String path = "/src/main/Vista/VistaPrimeraSeleccion.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        Scene escena = new Scene(loader.load());
        setEstilo(escena, "pokemones");

        this.vistaPokemonesController = loader.getController();
        this.vistaPokemonesController.setEscena(escena);
    }

    private void setEstilo(Scene escena, String estilo) {
        String cssPath = "/" + estilo + ".css";
        escena.getStylesheets().add(Objects.requireNonNull(getClass()
                .getResource(cssPath)).toExternalForm());
    }

    private Scene getEscena(String nombre) {
        return switch (nombre) {
            case "pokemones" -> this.vistaPokemonesController.getEscena();
            case "mochila" -> this.vistaMochilaController.getEscena();
            case "campo" -> this.vistaCampoController.getEscena();
            default -> throw new IllegalStateException("Unexpected value: " + nombre);
        };
    }

    private void seleccionarPrimerPokemon(Entrenador entrenador, int opcion) {
        String nombre = this.juego.cambiarPokemon(entrenador, opcion);
        System.out.println(nombre);
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

    public boolean juegoTerminado() {
        return this.juego.terminado();
    }

    public void terminar() {
        crearInforme();
    }

    @Override
    public void handle(EligePokemonEvento event) {
        int opcion = event.getOpcion();
        String nombre;
        Entrenador entrenador = juego.obtenerPrimerEntrenador();
        if (entrenador.obtenerPokemonActual() == null) {
            nombre = entrenador.cambiarPokemon(opcion);
            this.vistaPokemonesController.setDialogo(nombre);
            this.vistaPokemonesController.llenarLista(this.juego.obtenerSegundoEntrenador().obtenerPokemones());
            this.vistaPokemonesController.setDialogo("Elegir un POKéMON");
            return;
        }
        nombre = juego.obtenerSegundoEntrenador().cambiarPokemon(opcion);
        this.vistaPokemonesController.setDialogo(nombre);
        this.juego.inicializarTurnos();
        this.primaryStage.setScene(getEscena("campo"));
    }
}

/*
    public void menuPrincipal() {
        int opcion;
        boolean turnoTerminado = false;
        boolean mostrar = true;

        this.juego.efectoClimatico();
        Entrenador entrenadorActual = this.juego.obtenerEntrenadorActual();
        Pokemon pokemonActual = entrenadorActual.obtenerPokemonActual();

        if (this.juego.pokemonActualTieneEstado(Estados.MUERTO)) {
            VistaJuego.imprimirMismaLinea(pokemonActual.obtenerNombre() + " ha muerto!");
            this.seleccionarPokemon(entrenadorActual, true);
            turnoTerminado = true;
        } else if (this.juego.pokemonRivalTieneEstado(Estados.MUERTO)) {
            VistaJuego.imprimirMismaLinea(pokemonActual.obtenerNombre() + " ha muerto!");
            this.seleccionarPokemon(this.juego.obtenerEntrenadorRival(), true);
        }

        this.juego.actualizarEstadoPokemonActual();

        while (!turnoTerminado) {
            if (mostrar)
                VistaJuego.mostrarMenu(entrenadorActual.obtenerNombre());
            else
                mostrar = true;

            opcion = leerInt();
            switch (opcion) {
                case 1:
                    VistaPokemon.mostrarCampo(entrenadorActual, this.juego.obtenerEntrenadorRival());
                    break;
                case 2:
                    turnoTerminado = this.seleccionarHabilidad();
                    break;
                case 3:
                    turnoTerminado = this.seleccionarItem();
                    break;
                case 4:
                    turnoTerminado = this.seleccionarPokemon(entrenadorActual, false);
                    break;
                case 5:
                    this.juego.rendirse();
                    return;
                default:
                    VistaJuego.imprimir("Seleccione una opción correcta!");
                    mostrar = false;
            }
        }

        this.juego.cambiarTurno();
        this.juego.actualizarClima();
    }

    private boolean seleccionarPokemon(Entrenador entrenador, boolean seleccionObligatoria) {
        boolean opcionValida = false;
        int opcion = src.main.Modelo.Constant.NULA;

        while (!opcionValida) {
            opcion = pedirPokemon(entrenador, seleccionObligatoria);

            if (opcion == Constant.SALIR - 1) return false;

            if (this.juego.pokemonEstaMuerto(opcion))
                VistaJuego.imprimir("Ese Pokemon esta muerto!");
            else if (this.juego.validarPokemon(opcion))
                VistaJuego.imprimir("Se debe elegir un Pokemon distinto al actual mientras él siga con vida!");
            else
                opcionValida = true;
        }

        String nombrePokemon = this.juego.cambiarPokemon(entrenador, opcion);
        VistaJuego.imprimir(entrenador.obtenerNombre() + " ha elegido a " + nombrePokemon);
        return true;
    }

    private int pedirPokemon(Entrenador entrenador, boolean seleccionObligatoria){
        int opcion = Constant.SALIR;
        VistaPokemon.mostrarPokemones(entrenador, seleccionObligatoria);
        boolean opcionValida = false;

        while (!opcionValida) {
            opcion = leerInt();

            if (opcion < Constant.SALIR || opcion > this.juego.obtenerCantidadDePokemones(entrenador))
                VistaJuego.imprimir("Seleccione una opción correcta!");
            else if (opcion == Constant.SALIR && seleccionObligatoria)
                VistaJuego.imprimir("Seleccione una opción correcta!");
            else if (opcion == Constant.SALIR)
                return Constant.SALIR - 1;
            else
                opcionValida = true;
        }

        return opcion - 1;
    }

    public Boolean seleccionarHabilidad(){
        double ataque;
        Boolean ataqueEfectivo = true;
        int opcion = pedirHabilidad();
        int habilidadSeleccionada = opcion - 1;

        if (this.juego.pokemonActualTieneEstado(Estados.DORMIDO)) {
            VistaJuego.imprimir("El Pokemon esta dormido, no puede atacar");
            return true;
        }

        switch (opcion) {
            case Constant.SALIR:
                return false;
            case 1, 2:
                ataque = this.juego.atacar(habilidadSeleccionada);

                if (ataque == 0 && this.juego.pokemonActualTieneEstado(Estados.PARALIZADO)) {
                    ataqueEfectivo = false;
                    break;
                }
                VistaJuego.mostrarEfectividad(ataque);
                break;

            case 3, 4, 5:
                ataqueEfectivo = this.juego.usarHabilidad(habilidadSeleccionada);
                break;

            case 6:
                ataqueEfectivo = this.juego.usarHabilidadClima(habilidadSeleccionada);
                break;
        }
        if (!ataqueEfectivo)
            VistaJuego.imprimir("El pokemon esta paralizado!");

        return true;
    }

    private int pedirHabilidad() {
        int opcion = Constant.SALIR;
        boolean habilidadValida = false;

        VistaHabilidad.mostrarHabilidades(this.juego.obtenerEntrenadorActual().obtenerPokemonActual());

        while (!habilidadValida) {
            opcion = leerInt();

            if (opcion < Constant.SALIR || opcion > 6)
                VistaJuego.imprimir("Seleccione una opción correcta!");
            else if (opcion == Constant.SALIR)
                return opcion;
            else if (!this.juego.validarHabilidad(opcion - 1))
                VistaJuego.imprimir("Esta habilidad no tiene más usos");
            else
                habilidadValida = true;
        }

        return opcion;
    }

    private Boolean seleccionarItem() {
        int pokemonSeleccionado = pedirPokemon(this.juego.obtenerEntrenadorActual(), false);

        if (pokemonSeleccionado == Constant.NOT_INT)
            return false;

        int opcion = pedirItem(pokemonSeleccionado);

        if (opcion == Constant.SALIR)
            return false;

        this.juego.usarItem(opcion - 1, pokemonSeleccionado);
        VistaItem.notificarUsoDeItem(this.juego.obtenerEntrenadorActual(), opcion - 1);
        return true;
    }

    private Integer pedirItem(Integer pokemon) {
        int opcion = Constant.SALIR;
        boolean itemValido = false;
        VistaItem.mostrarItems(this.juego.obtenerEntrenadorActual());

        while (!itemValido) {
            opcion = leerInt();

            if (opcion < Constant.SALIR || opcion > this.juego.obtenerCantidadDeItems())
                VistaJuego.imprimir("Seleccione una opción correcta!");
            else if (opcion == Constant.SALIR)
                return opcion;

            if (!this.juego.validarItem(opcion - 1))
                VistaJuego.imprimir("Esta item no tiene más usos");
            else if (!this.juego.itemAplicable(opcion - 1, pokemon))
                VistaJuego.imprimir("No es posible aplicar el item debido al estado del Pokemon seleccionado");
            else
                itemValido = true;
        }
        return opcion;
    }

*/