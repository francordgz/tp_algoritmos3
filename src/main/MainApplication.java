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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VistaJuego.fxml")));
        primaryStage.setTitle("Pelea Politicos");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("styles.css")));
        primaryStage.setScene(scene);

        primaryStage.show();
    }


}