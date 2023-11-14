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




