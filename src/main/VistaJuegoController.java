package src.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
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


            String opponentImagePath = "C:/Users/Ezela/tp3algoritmos3/src/main/Imagenes/MyriamBregman.png";
            String playerImagePath = "C:/Users/Ezela/tp3algoritmos3/src/main/Imagenes/Massa.png";

            File opponentImageFile = new File(opponentImagePath);
            File playerImageFile = new File(playerImagePath);

            Image opponentImage = new Image(opponentImageFile.toURI().toString());
            Image playerImage = new Image(playerImageFile.toURI().toString());

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




