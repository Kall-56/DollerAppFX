module com.ucab.dollerappfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.ucab.dollerappfx to javafx.fxml;
    opens Classes to javafx.base;
    exports com.ucab.dollerappfx;
}
