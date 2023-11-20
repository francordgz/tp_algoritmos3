package src.main.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import src.main.Modelo.Item.Item;

public class ItemButtonController {
    @FXML
    private Label nombreLabel;
    @FXML
    private Label cantidadLabel;

    public void setItemInfo(Item item) {
        String cantiadad = ((Integer)item.obtenerCantidad()).toString();
        this.nombreLabel.setText(item.obtenerNombre());
        this.cantidadLabel.setText("x" + cantiadad);
    }
}

