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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MenuClientesController {

    @FXML
    private Button btCrear;

    @FXML
    private Pane btCrearNuevo;

    @FXML
    private Button btModificar;

    @FXML
    private Pane btVolver;

    @FXML
    private Label cadena;

    @FXML
    private ChoiceBox<String> cbSemana;

    @FXML
    private Label clientesLabel;

    @FXML
    private Label correo;

    @FXML
    private Pane crearEvento;

    @FXML
    private Label diasVisita;

    @FXML
    private Label drogueria;

    @FXML
    private Label drogueria1;

    @FXML
    private Label edfFacultad;

    @FXML
    private Label especialidad;

    @FXML
    private DatePicker fecha;

    @FXML
    private Label frecuencia;

    @FXML
    private Label horario;

    @FXML
    private Pane infoCliente;

    @FXML
    private Pane crearCliente;

    @FXML
    private Label lbCadenaFm;

    @FXML
    private Label lbCedula;

    @FXML
    private Label lbDiasVisitMd;

    @FXML
    private Label lbDirect;

    @FXML
    private Label lbDrogeriaFm;

    @FXML
    private Label lbEmailMdFm;

    @FXML
    private Label lbEspecsMd;

    @FXML
    private Label lbFacultadInst;

    @FXML
    private Label lbFrecueMdFm;

    @FXML
    private Label lbHorarioMd;

    @FXML
    private Label lbNumero;

    @FXML
    private Label lbPersonContacFmInst;

    @FXML
    private Label nomCliente;

    @FXML
    private Label persContacto;

    @FXML
    private TextArea taDescripcion;

    @FXML
    private TextArea taObservaciones;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableView<Evento> tbEventos;

    @FXML
    private TextField tfBuscarCliente;

    @FXML
    private TextField tfBuscarEvento;

    @FXML
    private TextField tfTituloEvento;

    @FXML
    private Pane verEventos;

    @FXML
    private Pane vistaCliente;

    @FXML
    private Pane btCrearCliente;

    @FXML
    private Pane btVerCrear;

    @FXML
    private TextField fldCadenaFm;

    @FXML
    private TextField fldCedula;

    @FXML
    private TextField fldDiasVisitMd;

    @FXML
    private TextField fldDirect;

    @FXML
    private TextField fldDrogeriaFm;

    @FXML
    private TextField fldEmailMdFm;

    @FXML
    private TextField fldEspecMd;

    @FXML
    private TextField fldFacultadInst;

    @FXML
    private TextField fldFrecueMdFm;

    @FXML
    private TextField fldHorarioMd;

    @FXML
    private TextField fldNombre;

    @FXML
    private TextField fldNumber;

    @FXML
    private TextField fldPersonContacFmInst;

     @FXML
    private ChoiceBox<?> chTypeCedula;

    @FXML
    private ChoiceBox<?> chnumber;

    @FXML
    private Label lbCrearNuevo;

    private Cliente clienteActual;

    private Evento eventoActual;

    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    void btCrearNuevoPressed(MouseEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        boolean titulo = ValidacionesController.validarCampos(tfTituloEvento, "^.{1,30}$");
        boolean descripcion = ValidacionesController.validarCampos(taDescripcion, "^[\\s\\S]{0,100}$");
        LocalDate date = fecha.getValue();
        if (titulo && descripcion && (date != null)) {
            Evento evento = new Evento(tfTituloEvento.getText(),
                    clienteActual.getNombreCliente(),
                    formato.format(date),
                    obtenerSemana(cbSemana),
                    taDescripcion.getText());

            if (lbCrearNuevo.getText().equals("Crear")) {
                clienteActual.registrarEvento(evento);
                MenuPrincipalController.listEventos.add(evento);

                alerta.setContentText("EVENTO AGREGADO CON ÉXITO");
                alerta.setTitle("Evento creado");
            } else if (lbCrearNuevo.getText().equals("Modificar")) {
                clienteActual.eliminarEvento(eventoActual);
                clienteActual.registrarEvento(evento);
                MenuPrincipalController.listEventos.remove(eventoActual);
                MenuPrincipalController.listEventos.add(evento);

                alerta.setContentText("EVENTO MODIFICADO CON ÉXITO");
                alerta.setTitle("Evento modificado");
            }

            reiniciarCampos();
            verEventos.setVisible(true);
            crearEvento.setVisible(false);
            tbEventos.setItems(FXCollections.observableArrayList(clienteActual.getEventoList()));
            tbEventos.refresh();

        } else {

            alerta.setContentText("ALGÚN ATRIBUTO ERRÓNEO");
            alerta.setTitle("Atributo erróneo");
        }
        alerta.setHeaderText("");
        alerta.showAndWait();
    }

    public void reiniciarCampos() {
        tfTituloEvento.clear();
        taDescripcion.clear();
        fecha.getEditor().clear();
        cbSemana.setValue("Semana 1");
    }

    public int obtenerSemana(ChoiceBox<String> semana) {
        String s = semana.getValue().replace("Semana", "").trim();
        return Integer.parseInt(s);
    }

    @FXML
    void btCrearPressed(MouseEvent event) {
        verEventos.setVisible(false);
        crearEvento.setVisible(true);

        lbCrearNuevo.setText("Crear");
    }

    @FXML
    void btModificarPressed(MouseEvent event) {
        eventoActual = tbEventos.getSelectionModel().getSelectedItem();
        if (eventoActual != null) {
            lbCrearNuevo.setText("Modificar");
            tfTituloEvento.setText(eventoActual.getTitulo());
            LocalDate date = LocalDate.parse(eventoActual.getFecha(), formato);
            fecha.setValue(date);
            taDescripcion.setText(eventoActual.getDescripcion());
            cbSemana.setValue("Semana " + eventoActual.getSemana());

            verEventos.setVisible(false);
            crearEvento.setVisible(true);
        }
    }

    @FXML
    void volverPressed(MouseEvent event) {
            verEventos.setVisible(true);
            crearEvento.setVisible(false);
            reiniciarCampos();
    }

    @FXML
    void crearPressed(MouseEvent event) {

    }

    @FXML
    void manejarClicks(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                clienteActual = tablaClientes.getSelectionModel().getSelectedItem();
                verInfoCliente(clienteActual);
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
            filtroEventos(FXCollections.observableArrayList(cliente.getEventoList()));
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Seleccione un cliente");
            alerta.setHeaderText("No ha seleccionado ningún cliente en la tabla");
            alerta.setContentText("Haga click sobre un cliente para ver su información");
            alerta.showAndWait();
        }
    }

    // Método para cambiar los labels correspondientes al cliente
    @FXML
    void btVerCrearClicked(MouseEvent event) {
        vistaCliente.setVisible(false);
        crearCliente.setVisible(true);
    }

    public void establecerCliente(Cliente cliente) {
        nomCliente.setText(cliente.getNombreCliente());
        lbCedula.setText(cliente.getTipoIdentidad()+" "+cliente.getDocIdentidad());
        lbNumero.setText(cliente.getNumTLF());
        lbDirect.setText(cliente.getDireccion());
        taObservaciones.setText(cliente.getDescripcion());

        if (cliente instanceof Medico) {
            lbEmailMdFm.setText(((Medico) cliente).getEmail());
            lbEmailMdFm.setVisible(true);
            correo.setVisible(true);

            lbDiasVisitMd.setText(((Medico) cliente).getDiasVisita());
            lbDiasVisitMd.setVisible(true);
            diasVisita.setVisible(true);

            lbEspecsMd.setText(((Medico) cliente).getEspecialidad());
            lbEspecsMd.setVisible(true);
            especialidad.setVisible(true);

            lbFrecueMdFm.setText(""+((Medico) cliente).getFrecuencia());
            lbFrecueMdFm.setVisible(true);
            frecuencia.setVisible(true);

            lbHorarioMd.setText(((Medico) cliente).getHorario());
            lbHorarioMd.setVisible(true);
            horario.setVisible(true);


            lbCadenaFm.setVisible(false);
            cadena.setVisible(false);
            lbDrogeriaFm.setVisible(false);
            drogueria.setVisible(false);
            lbPersonContacFmInst.setVisible(false);
            persContacto.setVisible(false);
            lbFacultadInst.setVisible(false);
            edfFacultad.setVisible(false);
        } else if (cliente instanceof Institucion) {
            lbPersonContacFmInst.setText(((Institucion) cliente).getPersonaContacto());
            lbPersonContacFmInst.setVisible(true);
            persContacto.setVisible(true);

            lbFacultadInst.setText(((Institucion) cliente).getEdfFacultad());
            lbFacultadInst.setVisible(true);
            edfFacultad.setVisible(true);

            lbEmailMdFm.setVisible(false);
            correo.setVisible(false);
            lbDiasVisitMd.setVisible(false);
            diasVisita.setVisible(false);
            lbEspecsMd.setVisible(false);
            especialidad.setVisible(false);
            lbFrecueMdFm.setVisible(false);
            frecuencia.setVisible(false);
            lbHorarioMd.setVisible(false);
            horario.setVisible(false);
            lbCadenaFm.setVisible(false);
            cadena.setVisible(false);
            lbDrogeriaFm.setVisible(false);
            drogueria.setVisible(false);
        } else if (cliente instanceof Farmacia) {
            lbEmailMdFm.setText(((Farmacia) cliente).getEmail());
            lbEmailMdFm.setVisible(true);
            correo.setVisible(true);

            lbFrecueMdFm.setText(""+((Farmacia) cliente).getFrecuencia());
            lbFrecueMdFm.setVisible(true);
            frecuencia.setVisible(true);

            lbPersonContacFmInst.setText(((Farmacia) cliente).getPersonaContacto());
            lbPersonContacFmInst.setVisible(true);
            persContacto.setVisible(true);

            lbCadenaFm.setText(((Farmacia) cliente).getCadena());
            lbCadenaFm.setVisible(true);
            cadena.setVisible(true);

            lbDrogeriaFm.setText(((Farmacia) cliente).getDrogueria());
            lbDrogeriaFm.setVisible(true);
            drogueria.setVisible(true);

            lbEspecsMd.setVisible(false);
            especialidad.setVisible(false);
            lbDiasVisitMd.setVisible(false);
            diasVisita.setVisible(false);
            lbHorarioMd.setVisible(false);
            horario.setVisible(false);
            lbFacultadInst.setVisible(false);
            edfFacultad.setVisible(false);
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

    public void filtroClientes(ObservableList<Cliente> lista) {
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

    public void filtroEventos(ObservableList<Evento> lista) {
        FilteredList<Evento> listaFiltro = new FilteredList<>(lista, p -> true);

        tfBuscarEvento.textProperty().addListener((observable, viejo, nuevo) -> {
            listaFiltro.setPredicate(evento -> {
                if (viejo == null || nuevo.isEmpty()) {
                    return true;
                }

                String filtroMinuscula = nuevo.toLowerCase();

                if (evento.getTitulo().toLowerCase().contains(filtroMinuscula)) {
                    return true;
                } else if (evento.getFecha().contains(filtroMinuscula)) {
                    return true;
                } else if (evento.getDescripcion().contains(filtroMinuscula)) {
                    return true;
                }
                return false;
            });
        });
        tbEventos.setItems(listaFiltro);

    }

    public void initialize() {
        // La Tabla de Eventos
        tbEventos.getColumns().clear();

        TableColumn<Evento, String> nombreCol = new TableColumn<>("Título Actividad");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));

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

        ObservableList<String> opciones = FXCollections.observableArrayList("Semana 1", "Semana 2", "Semana 3", "Semana 4", "Semana 5");
        cbSemana.setItems(opciones);
        cbSemana.setValue("Semana 1");
    }
}
