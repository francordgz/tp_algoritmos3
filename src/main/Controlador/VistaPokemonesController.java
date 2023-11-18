package src.main.Controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.main.Modelo.Pokemon;

import java.io.IOException;
import java.util.List;

public class VistaPokemonesController {
    private Scene escena;
    @FXML
    private ListView<Button> pokemonListView;
    @FXML
    private Text dialogo;

    public void llenarLista(List<Pokemon> pokemones) {
        String path = "/src/main/Vista/PokemonButton.fxml";
        int opcion = 0;
        try {
            pokemonListView.getItems().clear();
            for (Pokemon pokemon : pokemones) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                Button button = loader.load();

                PokemonButtonController pokemonButtonController = loader.getController();
                pokemonButtonController.setPokemonInfo(pokemon, opcion);

                int finalOpcion = opcion;
                button.setOnAction(e -> button.fireEvent(new EligePokemonEvent(finalOpcion)));
                pokemonListView.getItems().add(button);
                opcion++;
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

    public void setDialogo(String texto) {
        this.dialogo.setText(texto);
    }
}
