package src.main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.InputStream;

public class VistaCampoController {
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
    public void initialize() {
        setPokemonImages("Imagenes/MyriamBregman.png", "Imagenes/Massa.png");

        this.jugadorNombre.setText("Massa");
        this.jugadorNivel.setText("Nv 99");
        setJugadorVida(14, 20);

        this.rivalNombre.setText("Myriam");
        this.rivalNivel.setText("Nv 65");
        setRivalVida(3, 10);

        this.dialogo.setText("Que debe hacer Sergio?");
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
}




