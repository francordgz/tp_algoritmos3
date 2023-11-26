package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import src.main.Controlador.Eventos.EligeHabilidadEvento;
import src.main.Controlador.Eventos.RendirseEvento;
import src.main.Controlador.Eventos.VerMochilaEvento;
import src.main.Controlador.Eventos.VerPokemonesEvento;
import src.main.Modelo.Entrenador;
import src.main.Modelo.Pokemon;

import java.io.InputStream;

public class VistaCampoController {
    public ImageView background;
    public Label estadosRival;
    public Label estadosActual;
    public Label cantidadPokemonesActual;
    public Label cantidadPokemonesRival;
    private Scene escena;
    @FXML
    public Label rivalNombre;
    @FXML
    public ProgressBar rivalVidaPorcentaje;
    @FXML
    public Label jugadorNombre;
    @FXML
    public ProgressBar jugadorVidaPorcentaje;
    @FXML
    public Label jugadorVidaNumero;
    @FXML
    public Text dialogo;
    @FXML
    public Label rivalNivel;
    @FXML
    private Label jugadorNivel;
    @FXML
    private ImageView rivalImagen;
    @FXML
    private ImageView jugadorImagen;
    @FXML
    private Button botonRendirse;

    @FXML
    private Button botonPokemones;

    @FXML
    private Button botonMochila;

    @FXML
    private MenuItem botonHabilidad1;

    @FXML
    private MenuItem botonHabilidad2;

    @FXML
    private MenuItem botonHabilidad3;

    @FXML
    private MenuItem botonHabilidad4;


    @FXML
    private MenuItem botonHabilidad5;


    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public Scene getEscena() {
        return escena;
    }




    @FXML
    public void initialize() {

        botonRendirse.setOnAction(e -> botonRendirse.fireEvent(new RendirseEvento()));

        botonPokemones.setOnAction(e -> botonPokemones.fireEvent(new VerPokemonesEvento()));

        botonMochila.setOnAction(e -> botonMochila.fireEvent(new VerMochilaEvento()));

        botonHabilidad1.setOnAction(e -> habilidad(1));

        botonHabilidad2.setOnAction(e -> habilidad(2));

        botonHabilidad3.setOnAction(e -> habilidad(3));

        botonHabilidad4.setOnAction(e -> habilidad(4));

        botonHabilidad5.setOnAction(e -> habilidad(5));

        actualizarClima("normal");
    }


    private void habilidad(int habilidad) {

        System.out.println("A");
        botonRendirse.fireEvent(new EligeHabilidadEvento(habilidad));


    }


    public void setDatos(Entrenador actual, Entrenador rival) {
        Pokemon pokemonActual = actual.obtenerPokemonActual();
        String nombre = pokemonActual.obtenerNombre();
        this.jugadorImagen.setImage(getImagenPokemon(nombre, true));
        this.jugadorNombre.setText(nombre);
        this.estadosActual.setText(pokemonActual.obtenerEstados() + "");
        this.dialogo.setText("Que debe hacer " + nombre + "?");

        this.jugadorNivel.setText("Nv " + pokemonActual.obtenerNivel());
        setJugadorVida(pokemonActual.obtenerVidaActual(), pokemonActual.obtenerVidaMaxima());

        Pokemon pokemonRival = rival.obtenerPokemonActual();
        nombre = pokemonRival.obtenerNombre();
        this.rivalImagen.setImage(getImagenPokemon(nombre, false));
        this.rivalNombre.setText(nombre);
        this.rivalNivel.setText("Nv " + pokemonRival.obtenerNivel());
        this.estadosRival.setText(pokemonRival.obtenerEstados() + "");
        setRivalVida(pokemonRival.obtenerVidaActual(), pokemonRival.obtenerVidaMaxima());

        this.cantidadPokemonesActual.setText("◓".repeat(actual.obtenerPokemones().size()));
        this.cantidadPokemonesRival.setText("◓".repeat(rival.obtenerPokemones().size()));

        botonHabilidad1.setText(pokemonActual.habilidades(0).obtenerNombre());
        botonHabilidad2.setText(pokemonActual.habilidades(1).obtenerNombre());
        botonHabilidad3.setText(pokemonActual.habilidades(2).obtenerNombre());
        botonHabilidad4.setText(pokemonActual.habilidades(3).obtenerNombre());
        botonHabilidad5.setText(pokemonActual.habilidades(4).obtenerNombre());


    }

    public void actualizarClima(String nombreClima) {
        this.background.setImage(getImagenClima(nombreClima));
    }

    private void setJugadorVida(int vidaActual, int vidaMaxima) {
        this.jugadorVidaNumero.setText(vidaActual + "/" + vidaMaxima);

        double porcentaje = (double) vidaActual/vidaMaxima;
        this.jugadorVidaPorcentaje.setProgress(porcentaje);

        String style = String.format("-fx-accent: %s;", getColor(porcentaje));
        jugadorVidaPorcentaje.setStyle(style);
    }

    private void setRivalVida(int vidaActual, int vidaMaxima) {
        double porcentaje = (double) vidaActual/vidaMaxima;
        this.rivalVidaPorcentaje.setProgress(porcentaje);

        String style = String.format("-fx-accent: %s;", getColor(porcentaje));
        rivalVidaPorcentaje.setStyle(style);
    }

    private String getColor(double percentage) {
        if (percentage < 0.1) return "#FF0000";  // Red
        if (percentage < 0.25) return "#FF4500"; // Orange-Red
        if (percentage < 0.5) return "#FFFF66";  // Yellow
        if (percentage < 0.75) return "#ADFF2F"; // Green-Yellow
        return "#00FF00"; // Green
    }

    private Image getImagenPokemon(String nombre, boolean dorso) {
        String path = "/Imagenes/pokemon/";
        if (dorso) path = path + "dorso";
        else path = path + "frente";
        path = path + "_gif/" + nombre + ".gif";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        return new Image(imagen);
    }

    private Image getImagenClima(String nombre) {
        String path = "/Imagenes/bgs/climas/" + nombre + ".png";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        return new Image(imagen);
    }




}




