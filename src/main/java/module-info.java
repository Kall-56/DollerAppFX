module com.ucab.dollerappfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.ucab.dollerappfx to javafx.fxml;

    exports com.ucab.dollerappfx;
    exports Classes;
    requires mysql.connector.j;
}
    