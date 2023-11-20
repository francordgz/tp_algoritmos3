package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import src.main.Modelo.Item.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VistaItemsController {
    @FXML
    private ListView<Button> itemListView;

    @FXML
    private AnchorPane infoItemPane;

    List<Pane> descripciones;
    private Map<Integer, String> descripcionesItems;
    private Map<Integer, String> imagenesItems;
    private Scene escena;

    public void setElementosScene(List<Item> items) {
        llenarListaItems(items);
        agregarDescripcionItems(items);
        configurarVisibilidades();
    }

    private void configurarVisibilidades() {
        System.out.println("entro");
        int i = 0;
        for (Button button : this.itemListView.getItems()) {
            int finalI = i;
            button.setOnMouseEntered(event -> descripciones.get(finalI).setVisible(true));
            int finalI1 = i;
            button.setOnMouseExited(event -> descripciones.get(finalI1).setVisible(false));
            i++;
        }
    }
    private void agregarDescripcionItems(List<Item> items) {
        this.descripciones = new ArrayList<Pane>();
        String path = "/src/main/Vista/ItemPane.fxml";
        try {
            for (Item item : items) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                Pane itemPane = loader.load();
                ItemPaneController itemPaneController = loader.getController();
                // Ocultamiento de Pane´s
                itemPane.setVisible(false);
                itemPaneController.setItemInfo(item);

                infoItemPane.getChildren().add(itemPane);
                descripciones.add(itemPane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void llenarListaItems(List<Item> items) {
        String buttonPath = "/src/main/Vista/ItemButton.fxml";
        try {
            itemListView.getItems().clear();
            for (Item item : items) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonPath));
                Button button = loader.load();
                ItemButtonController itemButtonController = loader.getController();
                itemButtonController.setItemInfo(item);

                itemListView.getItems().add(button);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public Scene getEscena() {
        return escena;
    }
}
