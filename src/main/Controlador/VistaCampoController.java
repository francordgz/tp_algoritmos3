package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import src.main.Controlador.Eventos.RendirseEvento;
import src.main.Controlador.Eventos.VerMochilaEvento;
import src.main.Controlador.Eventos.VerPokemonesEvento;
import src.main.Modelo.Pokemon;

import java.io.InputStream;

public class VistaCampoController {
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
    }

    public void setDatos(Pokemon pokemonActual, Pokemon pokemonRival) {
        String nombre = pokemonActual.obtenerNombre();
        this.jugadorImagen.setImage(getImagen(nombre, true));
        this.jugadorNombre.setText(nombre);
        this.dialogo.setText("Que debe hacer " + nombre + "?");

        this.jugadorNivel.setText("Nv " + pokemonActual.obtenerNivel());
        setJugadorVida(pokemonActual.obtenerVidaActual(), pokemonActual.obtenerVidaMaxima());

        nombre = pokemonRival.obtenerNombre();
        this.rivalImagen.setImage(getImagen(nombre, false));
        this.rivalNombre.setText(nombre);
        this.rivalNivel.setText("Nv " + pokemonRival.obtenerNivel());
        setRivalVida(pokemonRival.obtenerVidaActual(), pokemonRival.obtenerVidaMaxima());
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

    private Image getImagen(String nombre, boolean dorso) {
        String path = "/Imagenes/pokemon/";
        if (dorso) path = path + "dorso";
        else path = path + "frente";
        path = path + "_gif/" + nombre + ".gif";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        return new Image(imagen);
    }

    public void handleHabilidad1() {

    }

    public void handleHabilidad2() {

    }

    public void handleHabilidad3() {

    }

    public void handleHabilidad4() {

    }


}




