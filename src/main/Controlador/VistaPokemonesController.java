package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import src.main.Controlador.Eventos.EligePokemonEvento;
import src.main.Controlador.Eventos.VolverEvento;
import src.main.Controlador.Nodos.PokemonButtonController;
import src.main.Modelo.Pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static src.main.Controlador.VistaCampoController.getColor;

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
    private Label dialogo;

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
                } else datosActual(pokemonActual);
                opcion++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void llenarLista(List<Pokemon> pokemones) {
        this.llenarLista(pokemones, null);
    }

    public void datosActual (Pokemon pokemonActual) {
        String nombre = pokemonActual.obtenerNombre();
        actualImagen.setImage(getImagen(nombre));
        actualNombre.setText(nombre);

        if (!pokemonActual.estaNormal())
            actualEstado.setText(pokemonActual.obtenerEstados() + "");

        actualNivel.setText("Nivel " + pokemonActual.obtenerNivel());
        actualTipo.setText("Tipo: " + pokemonActual.obtenerTipo());

        int vidaActual = pokemonActual.obtenerVidaActual();
        int vidaMaxima = pokemonActual.obtenerVidaMaxima();
        actualVida.setText(vidaActual + "/" + vidaMaxima);
        double porcentaje = (double) vidaActual/vidaMaxima;
        actualVidaPorcentaje.setProgress(porcentaje);
        String style = String.format("-fx-accent: %s;", getColor(porcentaje));
        actualVidaPorcentaje.setStyle(style);
    }

    private Image getImagen(String nombre) {
        String path = "/Imagenes/pokemon/frente_gif/" + nombre + ".gif";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        return new Image(imagen);
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
