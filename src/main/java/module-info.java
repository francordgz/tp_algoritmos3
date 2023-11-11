module src.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.junit.jupiter.api;
    requires org.mockito;
    opens src.main to javafx.fxml;
    exports src.main;
}