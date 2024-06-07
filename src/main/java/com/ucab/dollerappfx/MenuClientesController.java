package com.ucab.dollerappfx;

import Classes.Cliente;
import Classes.Farmacia;
import Classes.Institucion;
import Classes.Medico;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MenuClientesController {

    @FXML
    private Label clientesLabel;

    @FXML
    private Label nomCliente;

    @FXML
    private Pane infoCliente;

    @FXML
    private Label lbCedula;

    @FXML
    private Label lbDiasVisitMdFm;

    @FXML
    private Label lbDirect;

    @FXML
    private Label lbEmailMdFm;

    @FXML
    private Label lbEspecsMd;

    @FXML
    private Label lbFrecueMd;

    @FXML
    private Label lbHorarioMd;

    @FXML
    private Label lbNumero;
    
    @FXML
    private Label lbPersonContacFmInst;
            
    @FXML
    private Label lbCadenaFm;
            
    @FXML
    private Label lbDrogeriaFm;
    
    @FXML
    private Label lbFacultadInst;

    @FXML
    private TableView<? extends Cliente> tablaClientes;

    @FXML
    private Pane vistaCliente;
    
    public String getClientesLabel() {
        return clientesLabel.getText();
    }

    @FXML
    void manejarClicks(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 1) {
                // Abrir la mini ventana
            } else if (event.getClickCount() == 2) {
                verInfoCliente(tablaClientes.getSelectionModel().getSelectedItem());
            }
        }
    }

    public <T extends Cliente> void verInfoCliente(T cliente) {
        if (cliente != null) {
            cambio(true, false);
            establecerCliente(cliente);
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Seleccione un cliente");
            alerta.setHeaderText("No ha seleccionado ningún cliente en la tabla");
            alerta.setContentText("Haga click sobre un cliente para ver su información");
            alerta.showAndWait();
        }
    }

    public <T extends Cliente> void establecerCliente(T cliente) {
        nomCliente.setText(cliente.getNombreCliente());
        lbCedula.setText(cliente.getTipoIdentidad()+" "+cliente.getDocIdentidad());
        lbNumero.setText(""+cliente.getNumTLF());
        lbDirect.setText(cliente.getDireccion());

        if (cliente instanceof Medico) { // Ahora los atributos específicos
            lbEmailMdFm.setText(((Medico) cliente).getEmail());
            lbEmailMdFm.setVisible(true);
            
            lbDiasVisitMdFm.setText(((Medico) cliente).getDiasVisita());
            lbDiasVisitMdFm.setVisible(true);
            
            lbEspecsMd.setText(((Medico) cliente).getEspecialidad());
            lbEspecsMd.setVisible(true);
            
            lbFrecueMd.setText(""+((Medico) cliente).getFrecuencia());
            lbFrecueMd.setVisible(true);
            
            lbHorarioMd.setText(((Medico) cliente).getHorario());
            lbHorarioMd.setVisible(true);
            
            
            lbCadenaFm.setVisible(false);
            lbDrogeriaFm.setVisible(false);
            lbPersonContacFmInst.setVisible(false);
            lbFacultadInst.setVisible(false);
            
        } else if (cliente instanceof Institucion) {
            lbPersonContacFmInst.setText(((Institucion) cliente).getPersonaContacto());
            lbPersonContacFmInst.setVisible(true);
            
            lbFacultadInst.setText(((Institucion) cliente).getEdfFacultad());
            lbFacultadInst.setVisible(true);
            
            
            lbEmailMdFm.setVisible(false);
            lbDiasVisitMdFm.setVisible(false);
            lbEspecsMd.setVisible(false);
            lbFrecueMd.setVisible(false);
            lbHorarioMd.setVisible(false);
            lbCadenaFm.setVisible(false);
            lbDrogeriaFm.setVisible(false);
            
        } else if (cliente instanceof Farmacia) {
            //lbEmailMdFm.setText();
            lbEmailMdFm.setVisible(true);
            
            //lbDiasVisitMdFm.setText(); faltan getters
            lbDiasVisitMdFm.setVisible(true);
            
            //lbPersonContacFmInst.setText();
            lbPersonContacFmInst.setVisible(true);
            
            //lbCadenaFm.setText();
            lbCadenaFm.setVisible(true);
            
            //lbDrogeriaFm.setText();
            lbDrogeriaFm.setVisible(true);
            
            
            lbEspecsMd.setVisible(false);
            lbFrecueMd.setVisible(false);
            lbHorarioMd.setVisible(false);
            lbFacultadInst.setVisible(false);
        }
    }

    public void cambio(boolean a, boolean b) {
        infoCliente.setVisible(a);
        vistaCliente.setVisible(b);
    }

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
