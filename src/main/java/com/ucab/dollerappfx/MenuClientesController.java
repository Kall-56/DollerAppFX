package com.ucab.dollerappfx;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MenuClientesController {

    @FXML
    private Label clientesLabel;

    @FXML
    private Label nomCliente;
    
    @FXML
    private Label lbDiasVisitMd;

    @FXML
    private Label lbFrecueMdFm;
    
     @FXML
    private Label lbCedula;

    @FXML
    private Label lbDirect;

    @FXML
    private Label lbEmailMdFm;

    @FXML
    private Label lbEspecsMd;

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
    private Pane infoCliente;

    @FXML
    private TextField tfBuscarCliente;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableView<Evento> tbEventos;


    @FXML
    private Pane vistaCliente;

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

    public String getClientesLabel() {
        return clientesLabel.getText();
    }

    public void verInfoCliente(Cliente cliente) {
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

    public void establecerCliente(Cliente cliente) {
        nomCliente.setText(cliente.getNombreCliente());
        lbCedula.setText(cliente.getTipoIdentidad()+" "+cliente.getDocIdentidad());
        lbNumero.setText(""+cliente.getNumTLF());
        lbDirect.setText(cliente.getDireccion());

        if (cliente instanceof Medico) { // Ahora los atributos específicos
            lbEmailMdFm.setText(((Medico) cliente).getEmail());
            lbEmailMdFm.setVisible(true);

            lbDiasVisitMd.setText(((Medico) cliente).getDiasVisita());
            lbDiasVisitMd.setVisible(true);

            lbEspecsMd.setText(((Medico) cliente).getEspecialidad());
            lbEspecsMd.setVisible(true);

            lbFrecueMdFm.setText(""+((Medico) cliente).getFrecuencia());
            lbFrecueMdFm.setVisible(true);

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
            lbDiasVisitMd.setVisible(false);
            lbEspecsMd.setVisible(false);
            lbFrecueMdFm.setVisible(false);
            lbHorarioMd.setVisible(false);
            lbCadenaFm.setVisible(false);
            lbDrogeriaFm.setVisible(false);
        } else if (cliente instanceof Farmacia) {
            lbEmailMdFm.setText(((Farmacia) cliente).getEmail());
            lbEmailMdFm.setVisible(true);

            lbFrecueMdFm.setText(""+((Farmacia) cliente).getFrecuencia());
            lbFrecueMdFm.setVisible(true);

            lbPersonContacFmInst.setText(((Farmacia) cliente).getPersonaContacto());
            lbPersonContacFmInst.setVisible(true);

            lbCadenaFm.setText(((Farmacia) cliente).getCadena());
            lbCadenaFm.setVisible(true);

            lbDrogeriaFm.setText(((Farmacia) cliente).getDrogueria());
            lbDrogeriaFm.setVisible(true);


            lbEspecsMd.setVisible(false);
            lbDiasVisitMd.setVisible(false);
            lbHorarioMd.setVisible(false);
            lbFacultadInst.setVisible(false);
        }
        tbEventos.setItems(FXCollections.observableArrayList(cliente.getEventoList()));
        tbEventos.refresh();
    }

    public void cambio(boolean a, boolean b) {
        infoCliente.setVisible(a);
        vistaCliente.setVisible(b);
    }

    public <T> void establecerClase(Class<T> clase, ObservableList<T> lista) {
        tfBuscarCliente.setText("");
        TablasController.establecerTipoTabla((TableView<T>) tablaClientes, clase, lista);
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

    public void filtroTabla(ObservableList<Cliente> lista) {
        FilteredList<Cliente> listaFiltro = new FilteredList<>(lista, p -> true);

        tfBuscarCliente.textProperty().addListener((observable, viejo, nuevo) -> {
            listaFiltro.setPredicate(cliente -> {
                if (viejo == null || nuevo.isEmpty()) {
                    return true;
                }

                String filtroMinuscula = nuevo.toLowerCase();

                if (cliente.getNombreCliente().toLowerCase().contains(filtroMinuscula)) {
                    return true;
                } else if (String.valueOf(cliente.getNumTLF()).contains(filtroMinuscula)) {
                    return true;
                } else if (cliente.getDireccion().toLowerCase().contains(filtroMinuscula)) {
                    return true;
                } else if (String.valueOf(cliente.getDocIdentidad()).contains(filtroMinuscula)) {
                    return true;
                }

                if (cliente instanceof Medico) {
                    return ((Medico) cliente).getEspecialidad().toLowerCase().contains(filtroMinuscula);
                }
                else if (cliente instanceof Farmacia) {
                    if (((Farmacia) cliente).getPersonaContacto().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    } else if (((Farmacia) cliente).getCadena().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    } else if (((Farmacia) cliente).getDrogueria().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    }
                }
                else if (cliente instanceof Institucion) {
                    if (((Institucion) cliente).getPersonaContacto().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    } else if (((Institucion) cliente).getEdfFacultad().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    }
                }
                return false;
            });
        });
        tablaClientes.setItems(listaFiltro);
    }

    public void initialize() {
        // La Tabla de Eventos
        tbEventos.getColumns().clear();

        TableColumn<Evento, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        TableColumn<Evento, String> fechaCol = new TableColumn<>("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Evento, String> semanaCol = new TableColumn<>("Semana");
        semanaCol.setCellValueFactory(new PropertyValueFactory<>("semana"));

        TableColumn<Evento, String> descripCol = new TableColumn<>("Descripción");
        descripCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tbEventos.getColumns().add(nombreCol);
        tbEventos.getColumns().add(fechaCol);
        tbEventos.getColumns().add(semanaCol);
        tbEventos.getColumns().add(descripCol);
    }
}
