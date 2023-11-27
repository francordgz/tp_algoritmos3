package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import src.main.Controlador.Eventos.EligePokemonEvento;
import src.main.Controlador.Eventos.VolverEvento;
import src.main.Controlador.Nodos.PokemonButtonController;
import src.main.Modelo.Pokemon;

import java.io.IOException;
import java.io.InputStream;
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
                    String nombre = pokemonActual.obtenerNombre();
                    actualImagen.setImage(getImagen(nombre));
                    actualNombre.setText(nombre);

                    actualEstado.setText(pokemonActual.obtenerEstados() + ""); // TODO: Logica
                    actualNivel.setText(pokemonActual.obtenerNivel() + "");
                    actualTipo.setText(pokemonActual.obtenerTipo() + "");

                    int vidaActual = pokemonActual.obtenerVidaActual();
                    int vidaMaxima = pokemonActual.obtenerVidaMaxima();
                    actualVida.setText(vidaActual + "/" + vidaMaxima);
                    double porcentaje = (double) vidaActual/vidaMaxima;
                    actualVidaPorcentaje.setProgress(porcentaje);
                    String style = String.format("-fx-accent: %s;", getColor(porcentaje));
                    actualVidaPorcentaje.setStyle(style);
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

    private Image getImagen(String nombre) {
        String path = "/Imagenes/pokemon/frente_gif/" + nombre + ".gif";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        return new Image(imagen);
    }

    private String getColor(double percentage) {
        if (percentage < 0.1) return "#FF0000";  // Red
        if (percentage < 0.25) return "#FF4500"; // Orange-Red
        if (percentage < 0.5) return "#FFFF66";  // Yellow
        if (percentage < 0.75) return "#ADFF2F"; // Green-Yellow
        return "#00FF00"; // Green
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
