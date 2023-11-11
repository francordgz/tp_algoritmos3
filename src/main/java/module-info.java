module src.main {
    requires javafx.controls;
    requires javafx.fxml;
    opens src.main to javafx.fxml;
    exports src.main;
}