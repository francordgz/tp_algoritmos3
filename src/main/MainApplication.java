package src.main;

import javafx.application.Application;
import javafx.stage.Stage;
import src.main.Controlador.Eventos.*;
import src.main.Controlador.MainController;
import src.main.Modelo.Juego;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Subanle un punto a Eze");
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
}