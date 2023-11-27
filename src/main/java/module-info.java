module src.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.junit.jupiter.api;
    requires org.mockito;
    opens src.main to javafx.fxml;
    exports src.main;
    exports src.main.Controlador;
    exports src.main.Modelo;
    opens src.main.Controlador to javafx.fxml;
    exports src.main.Controlador.Eventos;
    opens src.main.Controlador.Eventos to javafx.fxml;
    exports src.main.Controlador.Nodos;
    opens src.main.Controlador.Nodos to javafx.fxml;
}