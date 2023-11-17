package src.main.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.main.Modelo.Pokemon;

import java.io.InputStream;

public class PokemonButtonController {
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

    private Pokemon pokemonAsociado;

    public void setPokemonInfo(Pokemon pokemon) {
        // Set the information from the Pokemon object to the UI elements
        //imageView.setImage(new Image(pokemon.getImagePath())); // Assuming Pokemon class has a method getImagePath()
        InputStream temporal = getClass().getResourceAsStream("/src/main/Imagenes/Massa.png");
        assert temporal != null;
        this.pokemonImage.setImage(new Image(temporal));
        this.nombreLabel.setText(pokemon.obtenerNombre());
        this.nivelLabel.setText("Nv " + pokemon.obtenerNivel());
        this.estadosLabel.setText("Logica Estados"); // You need to define how you want to display states

        int vidaActual = pokemon.obtenerVidaActual();
        int vidaMaxima = pokemon.obtenerVidaMaxima();
        this.progressBar.setProgress((double) vidaActual/vidaMaxima);
        this.hpLabel.setText("HP: " + vidaActual + " / " + vidaMaxima);

        this.pokemonAsociado = pokemon;
    }

    public Pokemon handleButtonClick() {
        System.out.println(pokemonAsociado.obtenerNombre());
        return this.pokemonAsociado;
    }
}
