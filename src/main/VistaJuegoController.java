package src.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class VistaJuegoController {

    @FXML
    private ImageView opponentPokemonImageView;

    @FXML
    private ImageView playerPokemonImageView;

    @FXML
    private Button attackButton;

    @FXML
    public void initialize() {
        setPokemonImages();
    }

    private void setPokemonImages() {
        try {

            InputStream opponentInputStream = getClass().getResourceAsStream("MyriamBregman.png");

            InputStream playerInputStream = getClass().getResourceAsStream("Massa.png");

            if (opponentInputStream != null && playerInputStream != null) {
                Image opponentImage = new Image(opponentInputStream);
                Image playerImage = new Image(playerInputStream);

                opponentPokemonImageView.setImage(opponentImage);
                playerPokemonImageView.setImage(playerImage);
            } else {
                System.err.println("Error Cargando la imagen.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAttackButtonClick() {

    }
}