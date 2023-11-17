package src.main.Controlador;

import javafx.scene.Scene;

public class VistaPokemonesController {
    private Scene escena;
    private Scene escenaPrimeraSeleccion;

    public VistaPokemonesController(Scene escena, Scene escenaPrimeraSeleccion) {
        this.escena = escena;
        this.escenaPrimeraSeleccion = escenaPrimeraSeleccion;
    }

    public Scene getEscena() {
        return escena;
    }

    public Scene getEscenaPrimeraSeleccion() {
        return escenaPrimeraSeleccion;
    }
}
