package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import src.main.Controlador.Eventos.EligePokemonEvento;
import src.main.Controlador.Eventos.VolverEvento;
import src.main.Modelo.Pokemon;

import java.io.IOException;
import java.util.List;

public class VistaPokemonesController {
    @FXML
    public ProgressBar actualVidaPorcentaje;
    @FXML
    public ImageView actualImagen;
    @FXML
    public Label actualNombre;
    @FXML
    public Label actualVida;
    @FXML
    public Label actualEstado;
    @FXML
    public Label actualTipo;
    @FXML
    public Label actualNivel;
    @FXML
    public Button salir;
    private Scene escena;
    @FXML
    private ListView<Button> pokemonListView;
    @FXML
    private Text dialogo;

    @FXML
    public void setSalir() {
        salir.setOnAction(e -> salir.fireEvent(new VolverEvento()));
    }

    public void llenarLista(List<Pokemon> pokemones, Pokemon pokemonActual) {
        String path = "/src/main/Vista/PokemonButton.fxml";
        int opcion = 0;
        try {
            pokemonListView.getItems().clear();
            for (Pokemon pokemon : pokemones) {
                if (pokemon != pokemonActual) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                    Button button = loader.load();

                    PokemonButtonController pokemonButtonController = loader.getController();
                    pokemonButtonController.setPokemonInfo(pokemon);

                    int finalOpcion = opcion;
                    button.setOnAction(e -> button.fireEvent(new EligePokemonEvento(finalOpcion)));
                    pokemonListView.getItems().add(button);
                } else {
                    actualNombre.setText(pokemonActual.obtenerNombre());
                    actualVida.setText(pokemonActual.obtenerVidaActual() + "/" + pokemonActual.obtenerVidaMaxima());
                    actualEstado.setText(pokemonActual.obtenerEstados() + ""); // TODO: Logica
                    actualNivel.setText(pokemonActual.obtenerNivel() + "");
                    actualTipo.setText(pokemonActual.obtenerTipo() + "");
                }
                opcion++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void llenarLista(List<Pokemon> pokemones) {
        this.llenarLista(pokemones, null);
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
