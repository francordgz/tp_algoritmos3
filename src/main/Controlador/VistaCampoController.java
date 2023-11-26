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
import javafx.stage.Stage;
import src.main.Controlador.Eventos.RendirseEvento;
import src.main.Modelo.Juego;
import src.main.Modelo.Entrenador;
import src.main.Modelo.Juego;

import java.io.InputStream;

public class VistaCampoController {
    private Scene escena;


    private Scene escenaPokemon;

    private Scene escenaItems;

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



    Juego juego;

    Stage primaryStage;


    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public void setEscenaItems(Scene escena1){
        this.escenaItems = escena1;
    }

    public Scene getEscena() {
        return escena;
    }


    public void SetJuego(Juego Unjuego){

        this.juego = Unjuego;

    }

    public void setStage(Stage unStage){

        this.primaryStage = unStage;

    }

    @FXML
    public void initialize() {

        setPokemonImages("/src/main/Imagenes/MyriamBregman.png", "/src/main/Imagenes/Massa.png");

        this.jugadorNombre.setText("Massa");
        this.jugadorNivel.setText("Nv 99");
        setJugadorVida(14, 20);

        this.rivalNombre.setText("Myriam");
        this.rivalNivel.setText("Nv 65");
        setRivalVida(3, 10);

        this.dialogo.setText("Que debe hacer Sergio?");

        botonRendirse.setOnAction(e -> botonRendirse.fireEvent(new RendirseEvento()));

        botonPokemones.setOnAction(e -> handlePokemones());

        botonMochila.setOnAction(e -> handleMochila());

        botonRendirse.setOnAction(e -> handleRendirse());



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
        InputStream playerImageFile =  getClass().getResourceAsStream(playerImagePath);
        assert playerImageFile != null;
        Image playerImage = new Image(playerImageFile);

        this.rivalImagen.setImage(opponentImage);
        this.jugadorImagen.setImage(playerImage);

    }



    private void handlePokemones() {

        this.primaryStage.setScene(escenaPokemon);

    }

    private void handleMochila() {

        this.primaryStage.setScene(escenaItems);
    }

    private void handleRendirse() {


    }

    private void handleLucha() {

    }


    public void setEscenaPokemones(Scene pokemonesBatalla) {

        this.escenaPokemon = pokemonesBatalla;
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




