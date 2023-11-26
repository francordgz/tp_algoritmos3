package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.main.Modelo.Item.Item;

import java.io.InputStream;

public class ItemPaneController {
    @FXML
    private TextArea itemDescription;

    @FXML
    private ImageView itemImage;

    public void setItemInfo(Item item) {
        int id = item.obtenerId();
        String imagenPath = obtenerImagen(id);
        String descripcion = obtenerDescripcion(id);

        InputStream temporal = getClass().getResourceAsStream(imagenPath);
        assert temporal != null;
        this.itemImage.setImage(new Image(temporal));
        this.itemDescription.setText(descripcion);
    }

    private String obtenerImagen(int idItem) {
        String path = "/Imagenes/items/";
        return switch(idItem) {
            case 0 -> path + "pocion.png";
            case 1 -> path + "mega_pocion.png";
            case 2 -> path + "hiper_pocion.png";
            case 3 -> path + "pocion_molesta_alumnos.png";
            case 4 -> path + "ataque.png";
            case 5 -> path + "defensa.png";
            case 6 -> path + "cura_todo.png";
            case 7 -> path + "revivir.png";
            default -> "";
        };
    }

    private String obtenerDescripcion(int idItem) {
        return switch (idItem) {
            case 0 -> "Cura 20 puntos de vida a un Pokemon.";
            case 1 -> "Cura 50 puntos de vida a un Pokemon.";
            case 2 -> "Cura 100 puntos de vida a un Pokemon.";
            case 3 -> "Cura un tercio de los puntos de vida originales de un Pokemon.";
            case 4 -> "Aumenta un 10% el ataque del Pokemon en el campo de batalla.";
            case 5 -> "Aumenta un 10% la defensa del Pokemon en el campo de batalla.";
            case 6 -> "Al ser utilizada quita a un Pokemon los estados que tenga.";
            case 7 -> "Al ser aplicada sobre un pokemon sin vida, este la recuperará en su totalidad.";
            default -> "";
        };
    }
}
