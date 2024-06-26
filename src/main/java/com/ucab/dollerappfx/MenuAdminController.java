package com.ucab.dollerappfx;

import Classes.ATM;
import Classes.Cliente;
import Classes.Evento;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
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
    private Pane btDeselect;

    private ObservableList<Cliente> todosCliente;

    private ObservableList<Evento> todosEvento;

    @FXML
    public void btCerrarSecionClicked(MouseEvent event) throws IOException{
        App.setRoot("LogIn");
    }
    @FXML
    void btCerrarSecionEntered(MouseEvent event) {
        btCerrarSecion.setStyle("-fx-background-color: #bababa;"+"-fx-border-color: #9c9c9c;"+"-fx-background-radius: 5;"+"-fx-border-radius: 2;");
        lbCerrarSeccion.setTextFill(Color.web("#9c9c9c"));
    }
    @FXML
    void btCerrarSecionExited(MouseEvent event) {
        btCerrarSecion.setStyle("-fx-background-color: white;"+"-fx-border-color: #969696;"+"-fx-background-radius: 5;"+"-fx-border-radius: 2;");
        lbCerrarSeccion.setTextFill(Color.web("#969696"));
    }

    @FXML
    void filtrarATM(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            ATM atmActual = tbATMs.getSelectionModel().getSelectedItem();

            if (atmActual != null) {
                ObservableList<Cliente> clientes = FXCollections.observableArrayList();
                clientes.addAll(atmActual.getMedicoList());
                clientes.addAll(atmActual.getFarmaciaList());
                clientes.addAll(atmActual.getInstitucionList());

                TablasController.establecerTipoTabla(tbCLIENTEs, Cliente.class, clientes);

                ObservableList<Evento> eventos = FXCollections.observableArrayList(atmActual.getEventoList());
                tbEVENTOs.setItems(eventos);
                tbEVENTOs.refresh();
            }
        }
    }

    @FXML
    void btDeselectClicked(MouseEvent event) {
        TablasController.establecerTipoTabla(tbCLIENTEs, Cliente.class, todosCliente);
        tbEVENTOs.setItems(todosEvento);
        tbEVENTOs.refresh();

        tbATMs.getSelectionModel().clearSelection();
    }

    public void initialize() {
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

        tbATMs.setItems(FXCollections.observableArrayList(App.admin.getAtmList()));
        tbATMs.refresh();

        // TABLA CLIENTES
        todosCliente = FXCollections.observableArrayList();
        for (ATM atm: App.admin.getAtmList()) {
            todosCliente.addAll(FXCollections.observableArrayList(atm.getMedicoList()));
            todosCliente.addAll(FXCollections.observableArrayList(atm.getFarmaciaList()));
            todosCliente.addAll(FXCollections.observableArrayList(atm.getInstitucionList()));
        }
        TablasController.establecerTipoTabla(tbCLIENTEs, Cliente.class, todosCliente);

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

        todosEvento = FXCollections.observableArrayList();
        for (ATM atm: App.admin.getAtmList()) {
            todosEvento.addAll(atm.getEventoList());
        }
        tbEVENTOs.setItems(todosEvento);
        tbEVENTOs.refresh();        
    }    
    
}
