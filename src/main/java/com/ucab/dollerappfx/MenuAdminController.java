package com.ucab.dollerappfx;

import Classes.ATM;
import Classes.Cliente;
import Classes.Evento;
import java.io.IOException;
import java.util.Optional;

import ManejadorBD.ManejadorBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class MenuAdminController {

    @FXML
    private TableView<ATM> tbATMs;

    @FXML
    private TableView<Cliente> tbCLIENTEs;

    @FXML
    private TableView<Evento> tbEVENTOs;

        
    @FXML
    private Pane btCerrarSecion;
    
    @FXML
    private Label lbCerrarSeccion;

    @FXML
    private Pane btDeseleccion;

    @FXML
    private Label lbDeseleccion;

    private ATM atmActual;


    @FXML
    public void btCerrarSecionClicked(MouseEvent event) throws IOException{
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Cerrar Sesion");
        alerta.setHeaderText("");
        alerta.setContentText("¿Está seguro que quiere cerrar la sesión?");
        ButtonType confirmButton = ButtonType.OK;
        ButtonType cancelButton = ButtonType.CANCEL;

        alerta.getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = alerta.showAndWait();

        if (result.isPresent() && result.get() == confirmButton) {
            App.setRoot("LogIn");
        }
    }
    @FXML
    void btCerrarSecionEntered(MouseEvent event) {
        btCerrarSecion.setStyle("-fx-background-color: #bababa;"+"-fx-border-color: #9c9c9c;"+"-fx-background-radius: 5;"+"-fx-border-radius: 2;");
        lbCerrarSeccion.setTextFill(Color.WHITE);
    }
    @FXML
    void btCerrarSecionExited(MouseEvent event) {
        btCerrarSecion.setStyle("-fx-background-color: white;"+"-fx-border-color: #969696;"+"-fx-background-radius: 5;"+"-fx-border-radius: 2;");
        lbCerrarSeccion.setTextFill(Color.web("#969696"));
    }

    @FXML
    void atmSeleccionado(MouseEvent event) throws ClassNotFoundException {
        if (event.getButton() == MouseButton.PRIMARY) {
            ATM atm = tbATMs.getSelectionModel().getSelectedItem();
            if (atm != null && event.getClickCount() == 2) {
                btDeseleccion.setDisable(false);
                String nombre = atm.getNomUsuario();

                ObservableList<Cliente> clientes = FXCollections.observableArrayList();
                clientes.addAll(FXCollections.observableArrayList(App.manejadorBD.DevolverMedicos(nombre)));
                clientes.addAll(FXCollections.observableArrayList(App.manejadorBD.DevolverInstitucion(nombre)));
                clientes.addAll(FXCollections.observableArrayList(App.manejadorBD.DevolverFarmacias(nombre)));

                ObservableList<Evento> eventos = FXCollections.observableArrayList(App.manejadorBD.DevolverEvento(nombre));

                tbEVENTOs.setItems(eventos);
                tbEVENTOs.refresh();
                TablasController.establecerTipoTabla(tbCLIENTEs, Cliente.class, clientes);
            } else if (atm != null && event.getClickCount() == 1) {
                atmActual = atm;
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Seleccione un ATM");
                alerta.setHeaderText("No ha seleccionado ningún ATM en la tabla");
                alerta.setContentText("Haga click o doble click sobre un cliente para ver su información");
                alerta.showAndWait();
            }
        }
    }

    @FXML
    void btDeseleccionPressed(MouseEvent event) throws ClassNotFoundException {
        btDeseleccion.setDisable(true);
        tbATMs.getSelectionModel().clearSelection();

        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        clientes.addAll(FXCollections.observableArrayList(App.manejadorBD.DevolverMedicoADMIN(App.admin.getNomUsuario())));
        clientes.addAll(FXCollections.observableArrayList(App.manejadorBD.DevolverFarmaciaADMIN(App.admin.getNomUsuario())));
        clientes.addAll(FXCollections.observableArrayList(App.manejadorBD.DevolverInstitucionADMIN(App.admin.getNomUsuario())));

        TablasController.establecerTipoTabla(tbCLIENTEs, Cliente.class, clientes);

        ObservableList<Evento> eventos = FXCollections.observableArrayList();
        eventos.addAll(App.manejadorBD.DevolverEventoADMIN(App.admin.getNomUsuario()));
        tbEVENTOs.setItems(eventos);
        tbEVENTOs.refresh();
    }

    @FXML
    void btDeseleccionEntered(MouseEvent event) {
        btDeseleccion.setStyle("-fx-background-color: #bababa;"+"-fx-border-color: #9c9c9c;"+"-fx-background-radius: 5;"+"-fx-border-radius: 2;");
        lbDeseleccion.setTextFill(Color.WHITE);
    }

    @FXML
    void btDeseleccionExited(MouseEvent event) {
        btDeseleccion.setStyle("-fx-background-color: white;"+"-fx-border-color: #969696;"+"-fx-background-radius: 5;"+"-fx-border-radius: 2;");
        lbDeseleccion.setTextFill(Color.web("#969696"));
    }

    public void initialize() throws ClassNotFoundException {
        btDeseleccion.setDisable(true);
        // TABLA ATM
        tbATMs.getColumns().clear();

        TableColumn<ATM, String> nombreAtmCol = new TableColumn<>("Usuario ATM");
        nombreAtmCol.setCellValueFactory(new PropertyValueFactory<>("nomUsuario"));

        TableColumn<ATM, String> territorioCol = new TableColumn<>("Territorio");
        territorioCol.setCellValueFactory(new PropertyValueFactory<>("territorio"));

        TableColumn<ATM, String> correoCol = new TableColumn<>("Correo");
        correoCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        tbATMs.getColumns().add(nombreAtmCol);
        tbATMs.getColumns().add(territorioCol);
        tbATMs.getColumns().add(correoCol);

        tbATMs.setItems(FXCollections.observableArrayList(App.manejadorBD.DevolverATM(App.admin.getNomUsuario())));
        tbATMs.refresh();

        // TABLA CLIENTES
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
            clientes.addAll(FXCollections.observableArrayList(App.manejadorBD.DevolverMedicoADMIN(App.admin.getNomUsuario())));
            clientes.addAll(FXCollections.observableArrayList(App.manejadorBD.DevolverFarmaciaADMIN(App.admin.getNomUsuario())));
            clientes.addAll(FXCollections.observableArrayList(App.manejadorBD.DevolverInstitucionADMIN(App.admin.getNomUsuario())));
        
        TablasController.establecerTipoTabla(tbCLIENTEs, Cliente.class, clientes);

        // TABLA DE EVENTOS
        tbEVENTOs.getColumns().clear();

        TableColumn<Evento, String> nombreCol = new TableColumn<>("Cliente");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        TableColumn<Evento, String> tituloCol = new TableColumn<>("Título Actividad");
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<Evento, String> fechaCol = new TableColumn<>("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Evento, String> semanaCol = new TableColumn<>("Semana");
        semanaCol.setCellValueFactory(new PropertyValueFactory<>("semana"));

        TableColumn<Evento, String> descripCol = new TableColumn<>("Descripción");
        descripCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tbEVENTOs.getColumns().add(nombreCol);
        tbEVENTOs.getColumns().add(tituloCol);
        tbEVENTOs.getColumns().add(fechaCol);
        tbEVENTOs.getColumns().add(semanaCol);
        tbEVENTOs.getColumns().add(descripCol);

        ObservableList<Evento> eventos = FXCollections.observableArrayList();
        eventos.addAll(App.manejadorBD.DevolverEventoADMIN(App.admin.getNomUsuario()));
        tbEVENTOs.setItems(eventos);
        tbEVENTOs.refresh();        
    }    
    
}
