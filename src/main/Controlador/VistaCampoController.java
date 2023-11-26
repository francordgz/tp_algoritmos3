package src.main.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import src.main.Controlador.Eventos.*;
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
    }

    public void setDatos(Pokemon pokemonActual, Pokemon pokemonRival) {
        String nombre = pokemonActual.obtenerNombre();
        this.jugadorNombre.setText(nombre);
        this.dialogo.setText("Que debe hacer " + nombre + "?");

        this.jugadorNivel.setText("Nv " + pokemonActual.obtenerNivel());
        setJugadorVida(pokemonActual.obtenerVidaActual(), pokemonActual.obtenerVidaMaxima());

        this.rivalNombre.setText(pokemonRival.obtenerNombre());
        this.rivalNivel.setText("Nv " + pokemonRival.obtenerNivel());
        setRivalVida(pokemonRival.obtenerVidaActual(), pokemonRival.obtenerVidaMaxima());


        setPokemonImages("/src/main/Imagenes/MyriamBregman.png", "/src/main/Imagenes/Massa.png");
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

    private void setPokemonImages(String opponentImagePath, String playerImagePath) {
        InputStream opponentImageFile = getClass().getResourceAsStream(opponentImagePath);
        assert opponentImageFile != null;
        Image opponentImage = new Image(opponentImageFile);
        InputStream playerImageFile = getClass().getResourceAsStream(playerImagePath);
        assert playerImageFile != null;
        Image playerImage = new Image(playerImageFile);

        this.rivalImagen.setImage(opponentImage);
        this.jugadorImagen.setImage(playerImage);
    }

    public void handleHabilidad1(ActionEvent actionEvent) {

    }

    public void handleHabilidad2(ActionEvent actionEvent) {

    }

    public void handleHabilidad3(ActionEvent actionEvent) {

    }

    public void handleHabilidad4(ActionEvent actionEvent) {

    }


}




