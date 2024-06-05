package com.ucab.dollerappfx;

import Classes.Cliente;
import Classes.Farmacia;
import Classes.Institucion;
import Classes.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class MenuClientesController {

    @FXML
    private Label clientesLabel;

    @FXML
    private Pane infoCliente;

    @FXML
    private TableView<?> tablaClientes;

    @FXML
    private Pane vistaCliente;

    public <T> void establecerClase(Class<T> clase, ObservableList<T> lista) {
        TablasController.establecerTipoTabla((TableView<T>) tablaClientes, clase, lista); // Es una advertencia porque la tabla no tiene una clase definida al momento de inicializar
        if (clase == Medico.class) {
            clientesLabel.setText("Médicos");
        }
        else if (clase == Farmacia.class) {
            clientesLabel.setText("Farmacias");
        }
        else if (clase == Institucion.class) {
            clientesLabel.setText("Instituciones");
        }
    }

    public void initialize() {
        // Si
    }
}
