package src.main.Controlador.Nodos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.main.Modelo.Pokemon;

import java.io.InputStream;

import static src.main.Controlador.VistaCampoController.getColor;

public class PokemonButtonController  {
    @FXML
    private ImageView pokemonImage;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label nivelLabel;

    @FXML
    private Label estadosLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label hpLabel;

    public void setPokemonInfo(Pokemon pokemon) {
        this.pokemonImage.setImage(getImagen(pokemon.obtenerNombre()));
        this.nombreLabel.setText(pokemon.obtenerNombre());
        this.nivelLabel.setText("Nv " + pokemon.obtenerNivel());
        if (!pokemon.estaNormal())
            this.estadosLabel.setText(pokemon.obtenerEstados() + "");

        int vidaActual = pokemon.obtenerVidaActual();
        int vidaMaxima = pokemon.obtenerVidaMaxima();
        double porcentaje = (double) vidaActual/vidaMaxima;
        this.progressBar.setProgress(porcentaje);
        this.hpLabel.setText("HP: " + vidaActual + " / " + vidaMaxima);
        String style = String.format("-fx-accent: %s;", getColor(porcentaje));
        progressBar.setStyle(style);
    }

    private Image getImagen(String nombre) {
        String path = "/Imagenes/pokemon/miniatura_menu/" + nombre + ".png";
        InputStream imagen = getClass().getResourceAsStream(path);
        if (imagen != null) {
            return new Image(imagen);
        } else {
            System.err.println("Image not found for: " + nombre);
            return null;
        }
    }
}
