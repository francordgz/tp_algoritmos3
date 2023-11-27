package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import src.main.Controlador.Eventos.EligeItemEvento;
import src.main.Controlador.Eventos.VolverEvento;
import src.main.Controlador.Nodos.ItemButtonController;
import src.main.Controlador.Nodos.ItemPaneController;
import src.main.Modelo.Item.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VistaItemsController {
    @FXML
    public Button salir;
    public ImageView mochilaImagen;
    public ImageView background;
    @FXML
    private ListView<Button> itemListView;

    @FXML
    private AnchorPane infoItemPane;

    List<Pane> descripciones;
    private Map<Integer, String> descripcionesItems;
    private Map<Integer, String> imagenesItems;
    private Scene escena;

    @FXML
    public void initialize() {
        this.salir.setOnAction(e -> salir.fireEvent(new VolverEvento()));

        String path = "/Imagenes/bgs/menu_selecciones.png";
        InputStream imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        this.background.setImage(new Image(imagen));

        path = "/Imagenes/mochila.png";
        imagen = getClass().getResourceAsStream(path);
        assert imagen != null;
        this.mochilaImagen.setImage(new Image(imagen));
    }


    private void configurarVisibilidades() {
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
        int opcion = 0;
        try {
            for (Item item : items) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                Pane itemPane = loader.load();
                ItemPaneController itemPaneController = loader.getController();
                itemPane.setVisible(false);
                itemPaneController.setItemInfo(item);
                infoItemPane.getChildren().add(itemPane);
                descripciones.add(itemPane);
                opcion++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void llenarLista(List<Item> items) {
        String buttonPath = "/src/main/Vista/ItemButton.fxml";
        int opcion = 0;
        try {
            itemListView.getItems().clear();
            for (Item item : items) {
                if (item.obtenerCantidad() <= 0) continue;
                FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonPath));
                Button button = loader.load();
                ItemButtonController itemButtonController = loader.getController();
                itemButtonController.setItemInfo(item);
                int finalOpcion = opcion;
                button.setOnAction(e -> button.fireEvent(new EligeItemEvento(finalOpcion)));
                itemListView.getItems().add(button);
                opcion++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        agregarDescripcionItems(items);
        configurarVisibilidades();
    }

    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public Scene getEscena() {
        return escena;
    }
}
