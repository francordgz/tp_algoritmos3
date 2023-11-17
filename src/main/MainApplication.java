package src.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pelea Politicos");
        primaryStage.setResizable(false);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VistaCampo.fxml")));

        Scene campo = new Scene(root);
        campo.getStylesheets().add(String.valueOf(getClass().getResource("styles.css")));

        primaryStage.setScene(campo);
        primaryStage.show();
    }


}