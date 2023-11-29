module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.mockito;

    exports BatallaPokemon.Controlador;
    exports BatallaPokemon.Modelo;

    opens BatallaPokemon.Controlador to javafx.fxml;
    opens BatallaPokemon.Controlador.Eventos to javafx.fxml;
    opens BatallaPokemon.Controlador.Nodos to javafx.fxml;

    exports BatallaPokemon;
    opens BatallaPokemon to javafx.fxml;
}