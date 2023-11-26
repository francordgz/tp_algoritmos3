package src.main.Controlador;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.main.Modelo.Pokemon;

import java.io.InputStream;

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
        this.estadosLabel.setText(pokemon.obtenerEstados() + ""); // TODO: You need to define how you want to display states

        int vidaActual = pokemon.obtenerVidaActual();
        int vidaMaxima = pokemon.obtenerVidaMaxima();
        this.progressBar.setProgress((double) vidaActual/vidaMaxima);
        this.hpLabel.setText("HP: " + vidaActual + " / " + vidaMaxima);
    }

    private Image getImagen(String nombre) {
        String path = "/Imagenes/pokemon/frente_gif/" + nombre + ".gif";
        InputStream imagen = getClass().getResourceAsStream(path);
        if (imagen != null) {
            return new Image(imagen);
        } else {
            System.err.println("Image not found for: " + nombre);
            return null;
        }
    }
}
