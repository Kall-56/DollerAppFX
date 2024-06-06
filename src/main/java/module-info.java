module com.ucab.dollerappfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.ucab.dollerappfx to javafx.fxml;

    exports com.ucab.dollerappfx;
    exports Classes;
}
    