package src.main;

import javafx.application.Application;
import javafx.stage.Stage;
import src.main.Controlador.MainController;
import src.main.Modelo.Juego;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pelea Politicos");
        primaryStage.setResizable(false);

        MainController controlador = new MainController(new Juego(), primaryStage);

        /*
        while (!controlador.juegoTerminado()) {
            contraldor.jugar() ???
        }
        */
    }
}