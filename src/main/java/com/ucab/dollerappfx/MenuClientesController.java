package com.ucab.dollerappfx;

import Classes.Cliente;
import Classes.Farmacia;
import Classes.Institucion;
import Classes.Medico;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
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
    private TextField tfBuscarCliente;

    @FXML
    private TableView<Cliente> tablaClientes;

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
        // Los otros atributos comunes

        if (cliente instanceof Medico) { // Ahora los atributos específicos

        } else if (cliente instanceof Institucion) {

        } else if (cliente instanceof Farmacia) {

        }
    }

    public void cambio(boolean a, boolean b) {
        infoCliente.setVisible(a);
        vistaCliente.setVisible(b);
    }

    public <T> void establecerClase(Class<T> clase, ObservableList<T> lista) {
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
        // NO SE
    }
}
