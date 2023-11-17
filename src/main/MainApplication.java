package src.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pelea Politicos");
        primaryStage.setResizable(false);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Vista/VistaCampo.fxml")));
        Scene campo = new Scene(root);
        campo.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/campo.css")).toExternalForm());

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Vista/VistaMochila.fxml")));
        Scene mochila = new Scene(root);

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Vista/VistaPokemones.fxml")));
        Scene pokemones = new Scene(root);
        pokemones.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/pokemones.css")).toExternalForm());

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Vista/VistaPrimeraSeleccion.fxml")));
        Scene primeraSeleccion = new Scene(root);
        primeraSeleccion.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/pokemones.css")).toExternalForm());

        primaryStage.setScene(pokemones);
        primaryStage.show();
    }


}