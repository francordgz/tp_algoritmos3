package src.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import src.main.Vista.VistaJuego;

import java.io.InputStream;

public class VistaJuegoController {
    @FXML
    public Text rivalPokemonNivel;
    @FXML
    public Text rivalPokemonNombre;
    @FXML
    public Rectangle rivalPokemonVida;
    @FXML
    public Pane rivalPokemonHealthBar;
    @FXML
    public Text rivalPokemonRestantes;
    @FXML
    public Text jugadorPokemonNombre;
    @FXML
    public Text jugadorPokemonNivel;
    @FXML
    public Rectangle jugadorPokemonVida;
    @FXML
    public Text jugadorPokemonVidaNumero;
    @FXML
    public Text jugadorPokemonRestantes;
    @FXML
    public Pane jugadorPokemonHealthBar;
    @FXML
    public Text dialogo;
    @FXML
    private ImageView opponentPokemonImageView;
    @FXML
    private ImageView playerPokemonImageView;

    @FXML
    private Button attackButton;

    @FXML
    public void initialize() {
        setPokemonImages();

        setJugadorPokemonStats("Massa", 99, 9);
        setJugadorVida(18, 20);

        setRivalPokemonStats("Myriam", 22, 3);
        setRivalVida(3, 10);

        this.dialogo.setText("Que debe hacer Sergio?");
    }

    private void setJugadorPokemonStats(String nombre, int nivel, int pokemonesRestantes) {
        this.jugadorPokemonNombre.setText(nombre);
        this.jugadorPokemonNivel.setText("Nv " + nivel);
        this.jugadorPokemonRestantes.setText("P" + pokemonesRestantes);
    }

    private void setRivalPokemonStats(String nombre, int nivel, int pokemonesRestantes) {
        this.rivalPokemonNombre.setText(nombre);
        this.rivalPokemonNivel.setText("Nv " + nivel);
        this.rivalPokemonRestantes.setText("P" + pokemonesRestantes);
    }

    private void setJugadorVida(int vidaActual, int vidaMaxima) {
        this.jugadorPokemonVidaNumero.setText("50/50");
        //double percentage = Math.max(0.0, Math.min(1.0, percentage));
        double percentage = (double) vidaActual /vidaMaxima;

        if (percentage < 0.75) jugadorPokemonVida.setFill(Color.rgb(173, 255, 47));
        if (percentage < 0.5) jugadorPokemonVida.setFill(Color.rgb(255, 255, 102));
        if (percentage < 0.25) jugadorPokemonVida.setFill(Color.rgb(255, 69, 0));
        if (percentage < 0.1) jugadorPokemonVida.setFill(Color.RED);
        // Update the width of the filled portion based on the percentage
        double barWidth = jugadorPokemonHealthBar.getPrefWidth();
        this.jugadorPokemonVida.setWidth(percentage * barWidth);
    }

    private void setRivalVida(int vidaActual, int vidaMaxima) {

        //double percentage = Math.max(0.0, Math.min(1.0, percentage));
        double percentage = (double) vidaActual /vidaMaxima;
        if (percentage < 0.75) rivalPokemonVida.setFill(Color.rgb(173, 255, 47));
        if (percentage < 0.5) rivalPokemonVida.setFill(Color.rgb(255, 255, 102));
        if (percentage < 0.25) rivalPokemonVida.setFill(Color.rgb(255, 69, 0));
        if (percentage < 0.1) rivalPokemonVida.setFill(Color.RED);
        // Update the width of the filled portion based on the percentage
        double barWidth = rivalPokemonHealthBar.getPrefWidth();
        this.rivalPokemonVida.setWidth(percentage * barWidth);
    }

    private void setPokemonImages() {
        try {
            String opponentImagePath = "Imagenes/MyriamBregman.png";
            String playerImagePath = "Imagenes/Massa.png";

            InputStream opponentImageFile = getClass().getResourceAsStream(opponentImagePath);
            assert opponentImageFile != null;
            Image opponentImage = new Image(opponentImageFile);
            InputStream playerImageFile =  getClass().getResourceAsStream(playerImagePath);
            assert playerImageFile != null;
            Image playerImage = new Image(playerImageFile);

            opponentPokemonImageView.setImage(opponentImage);
            playerPokemonImageView.setImage(playerImage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAttackButtonClick() {

    }
}




