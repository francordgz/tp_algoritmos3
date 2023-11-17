package src.main.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.main.Modelo.Pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class VistaPokemonesController {
    private Scene escena;
    @FXML
    private ListView<Button> pokemonListView;

    private PokemonButtonController pokemonButtonController;

    public void llenarLista(List<Pokemon> pokemones) {
        String path = "/src/main/Vista/PokemonButton.fxml";
        try {
            pokemonListView.getItems().clear();
            for (Pokemon pokemon : pokemones) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/Vista/PokemonButton.fxml"));
                Button button = loader.load();
                PokemonButtonController pokemonButtonController = loader.getController();
                pokemonButtonController.setPokemonInfo(pokemon);
                pokemonListView.getItems().add(button);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public Scene getEscena() {
        return escena;
    }

    public void handleButtonClick(ActionEvent event) {
        // Your implementation here
    }
}
