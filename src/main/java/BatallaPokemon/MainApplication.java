package BatallaPokemon;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import BatallaPokemon.Controlador.Eventos.*;
import BatallaPokemon.Controlador.MainController;
import BatallaPokemon.Modelo.Juego;

import java.io.InputStream;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Batalla Pokemon");
        primaryStage.getIcons().add(getIcon());
        primaryStage.setResizable(false);
        primaryStage.show();

        MainController controlador = new MainController(new Juego(), primaryStage);
        addHandlers(primaryStage, controlador);
    }

    private void addHandlers(Stage primaryStage, MainController controlador) {
        primaryStage.addEventHandler(EligePokemonEvento.ELIGE_POKEMON_EVENT, controlador);
        primaryStage.addEventHandler(RendirseEvento.RENDIRSE_EVENT, controlador);
        primaryStage.addEventHandler(VerPokemonesEvento.VER_POKEMONES_EVENTO, controlador);
        primaryStage.addEventHandler(VerMochilaEvento.VER_MOCHILA_EVENTO, controlador);
        primaryStage.addEventHandler(VolverEvento.VOLVER_EVENT, controlador);
        primaryStage.addEventHandler(EligeItemEvento.ELIGE_ITEM_EVENT, controlador);
        primaryStage.addEventHandler(EligeHabilidadEvento.ELIGE_HABILIDAD_EVENT,controlador);
    }

    private Image getIcon() {
        String path = "/Imagenes/pokemon/miniatura_menu/pikachu.png";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        return new Image(imagen);
    }
}