module com.ucab.dollerappfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ucab.dollerappfx to javafx.fxml;
    exports com.ucab.dollerappfx;
}
