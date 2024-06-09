package com.ucab.dollerappfx;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MenuClientesController {

    @FXML
    private ImageView btAtras;

    @FXML
    private Pane btCrear;

    @FXML
    private Pane btCrearNuevo;

    @FXML
    private Pane btModificar;

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
    private Pane btVolverClientes;

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
    private TextField fldFormatoMd;

    @FXML
    private TextField fldPersonContacFmInst;

     @FXML
    private ChoiceBox<Character> chTypeCedula;

    @FXML
    private ChoiceBox<String> chnumber;

    @FXML
    private Label lbCrearNuevo;

    private Cliente clienteActual;

    private Evento eventoActual;

    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    void btAtrasClicked(MouseEvent event) {
        infoCliente.setVisible(false);
        vistaCliente.setVisible(true);

    }

    @FXML
    void btVolverClientesPressed(MouseEvent event) {
        vistaCliente.setVisible(true);
        crearCliente.setVisible(false);

        reinicarCamposCliente();
    }

    @FXML
    void btCrearNuevoPressed(MouseEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        boolean titulo = ValidacionesController.validarCampos(tfTituloEvento, "^.{1,30}$", lbCrearNuevo);
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
                App.listEventos.add(evento);

                alerta.setContentText("EVENTO AGREGADO CON ÉXITO");
                alerta.setTitle("Evento creado");
            } else if (lbCrearNuevo.getText().equals("Modificar")) {
                clienteActual.eliminarEvento(eventoActual);
                clienteActual.registrarEvento(evento);
                App.listEventos.remove(eventoActual);
                App.listEventos.add(evento);

                alerta.setContentText("EVENTO MODIFICADO CON ÉXITO");
                alerta.setTitle("Evento modificado");
            }

            reiniciarCamposEvento();
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

    public void reiniciarCamposEvento() {
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
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Seleccione un evento");
            alerta.setHeaderText("");
            alerta.setContentText("No ha seleccionado ningún evento en la tabla");
            alerta.showAndWait();
        }
    }

    @FXML
    void volverPressed(MouseEvent event) {
            verEventos.setVisible(true);
            crearEvento.setVisible(false);
            reiniciarCamposEvento();
    }

    @FXML
    void btCrearClienteClicked(MouseEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Crear Cliente");
        alerta.setHeaderText("ESTÁ SEGURO DE CREAR EL CLIENTE?");
        alerta.setContentText("UNA VEZ CREADO NO SE PUEDE MODIFICAR");
        ButtonType confirmButton = ButtonType.OK;
        ButtonType cancelButton = ButtonType.CANCEL;

        alerta.getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = alerta.showAndWait();

        if (result.isPresent() && result.get() == confirmButton) {
            String clase = clientesLabel.getText();

            boolean nombreValido = ValidacionesController.validarCampos(fldNombre, "^[a-zA-Z ]{0,50}$", lbCrearNuevo);
            boolean numValido = ValidacionesController.validarCampos(fldNumber, "^[0-9]{7}$", lbCrearNuevo);
            boolean ciValido = ValidacionesController.validarCampos(fldCedula, "^[0-9]{6,9}$", lbCrearNuevo);
            boolean direcValido = ValidacionesController.validarCampos(fldDirect, "^[a-zA-Z0-9#. ]{0,70}$", lbCrearNuevo);

            boolean[] atributosValidos = {nombreValido, numValido, ciValido, direcValido};

            if (clase.equals("Médicos")) {
                boolean espValido = ValidacionesController.validarCampos(fldEspecMd, "^[a-zA-Z ]{0,20}$", lbCrearNuevo);
                boolean emailValido = ValidacionesController.validarCampos(fldEmailMdFm, "^[A-Za-z0-9+_.-]+@(.+)$", lbCrearNuevo);
                boolean frecValido = ValidacionesController.validarCampos(fldFrecueMdFm, "^[0-9]{1}$", lbCrearNuevo);
                boolean diasValido = ValidacionesController.validarCampos(fldDiasVisitMd, "^[a-zA-Z- ]{0,50}$", lbCrearNuevo);
                boolean horarioValido = ValidacionesController.validarCampos(fldHorarioMd, "^[0-9- ]{0,10}$", lbCrearNuevo);
                boolean formatoValido = ValidacionesController.validarCampos(fldFormatoMd, "^[a-zA-Z ]{0,20}$", lbCrearNuevo);

                atributosValidos = new boolean[] {nombreValido, numValido, ciValido, direcValido, espValido, emailValido, frecValido, diasValido, horarioValido, formatoValido};

            } else if (clase.equals("Farmacias")) {
                boolean perValido = ValidacionesController.validarCampos(fldPersonContacFmInst, "^[a-zA-Z ]{0,20}$", lbCrearNuevo);
                boolean emailValido = ValidacionesController.validarCampos(fldEmailMdFm, "^[A-Za-z0-9+_.-]+@(.+)$", lbCrearNuevo);
                boolean frecValido = ValidacionesController.validarCampos(fldFrecueMdFm, "^[0-9]{1}$", lbCrearNuevo);
                boolean cadValido = ValidacionesController.validarCampos(fldCadenaFm, "^[a-zA-Z ]{0,20}$", lbCrearNuevo);
                boolean drogValido = ValidacionesController.validarCampos(fldDrogeriaFm, "^[a-zA-Z ]{0,20}$", lbCrearNuevo);

                atributosValidos = new boolean[] {nombreValido, numValido, ciValido, direcValido, perValido, emailValido, frecValido, cadValido, drogValido};
            } else if (clase.equals("Instituciones")) {
                boolean perValido = ValidacionesController.validarCampos(fldPersonContacFmInst, "^[a-zA-Z ]{0,20}$", lbCrearNuevo);
                boolean facValido = ValidacionesController.validarCampos(fldFacultadInst, "^[a-zA-Z ]{0,30}$", lbCrearNuevo);

                atributosValidos = new boolean[] {nombreValido, numValido, ciValido, direcValido, perValido, facValido};
            }

            boolean valido = true;
            for (boolean atributo: atributosValidos) {
                if (!atributo) {
                    valido = false;
                    break; // Tal vez haya que quitarlo
                }
            }
            if (valido) {
                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                if (clase.equals("Médicos")) {
                    Medico med = new Medico(fldNombre.getText(),
                                            fldDirect.getText(),
                                            chnumber.getValue() + fldNumber.getText(),
                                            chTypeCedula.getValue(),
                                            Integer.parseInt(fldCedula.getText()),
                                            "",
                                            fldEspecMd.getText(),
                                            fldEmailMdFm.getText(),
                                            Integer.parseInt(fldFrecueMdFm.getText()),
                                            fldDiasVisitMd.getText(),
                                            fldHorarioMd.getText(),
                                            fldFormatoMd.getText());
                    App.listMedicos.add(med);
                    establecerClase(Medico.class, App.listMedicos);
                    LogInController.usuario.registrarCliente(med);

                } else if (clase.equals("Farmacias")) {
                    Farmacia far = new Farmacia(fldNombre.getText(),
                                                fldDirect.getText(),
                                                chnumber.getValue() + fldNumber.getText(),
                                                chTypeCedula.getValue(),
                                                Integer.parseInt(fldCedula.getText()),
                                                "",
                                                fldPersonContacFmInst.getText(),
                                                fldEmailMdFm.getText(),
                                                Integer.parseInt(fldFrecueMdFm.getText()),
                                                fldCadenaFm.getText(),
                                                fldDrogeriaFm.getText());
                    App.listFarmacias.add(far);
                    establecerClase(Farmacia.class, App.listFarmacias);
                    LogInController.usuario.registrarCliente(far);

                } else if (clase.equals("Instituciones")) {
                    Institucion inst = new Institucion(fldNombre.getText(),
                                                       fldDirect.getText(),
                                                       chnumber.getValue() + fldNumber.getText(),
                                                       chTypeCedula.getValue(),
                                                       Integer.parseInt(fldCedula.getText()),
                                                       "",
                                                       fldPersonContacFmInst.getText(),
                                                       fldFacultadInst.getText());
                    App.listInstituciones.add(inst);
                    establecerClase(Institucion.class, App.listInstituciones);
                    LogInController.usuario.registrarCliente(inst);
                }
                alerta2.setHeaderText("");
                alerta2.setContentText("CLIENTE AGREGADO CON ÉXITO");
                alerta2.setTitle("Cliente creado");

                reinicarCamposCliente();
                vistaCliente.setVisible(true);
                crearCliente.setVisible(false);
                tablaClientes.refresh();
                alerta2.showAndWait();
            } else {
                alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setHeaderText("");
                alerta.setContentText("Revise que no haya ningún campo con formato erróneo");
                alerta.setTitle("Error");
                alerta.showAndWait();
            }
        }
    }
    
    public void reinicarCamposCliente() {
        fldNombre.clear();
        fldNumber.clear();
        fldCedula.clear();
        fldDirect.clear();
        fldEspecMd.clear();
        fldEmailMdFm.clear();
        fldFrecueMdFm.clear();
        fldDiasVisitMd.clear();
        fldHorarioMd.clear();
        fldFormatoMd.clear();
        fldPersonContacFmInst.clear();
        fldCadenaFm.clear();
        fldDrogeriaFm.clear();
        fldFacultadInst.clear();
        chnumber.setValue("0212");
        chTypeCedula.setValue('V');
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
            tfBuscarCliente.clear();
            visibilityChange(true, false, false);
            verEventos.setVisible(true);
            crearEvento.setVisible(false);
            establecerCliente(cliente);
            filtroEventos(FXCollections.observableArrayList(cliente.getEventoList()));
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Seleccione un cliente");
            alerta.setHeaderText("No ha seleccionado ningún cliente en la tabla");
            alerta.setContentText("Haga doble click sobre un cliente para ver su información");
            alerta.showAndWait();
        }
    }


    @FXML
    void btVerCrearClicked(MouseEvent event) {
        vistaCliente.setVisible(false);
        crearCliente.setVisible(true);
    }

    // Método para cambiar los labels correspondientes al cliente
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

    public void visibilityChange(boolean a, boolean b, boolean c) {
        infoCliente.setVisible(a);
        vistaCliente.setVisible(b);
        crearCliente.setVisible(c);
    }

    public <T> void establecerClase(Class<T> clase, ObservableList<T> lista) {
        tfBuscarCliente.setText("");
        TablasController.establecerTipoTabla((TableView<T>) tablaClientes, clase, lista);
        if (clase == Medico.class) {
            clientesLabel.setText("Médicos");

            fldEspecMd.setVisible(true);
            fldEmailMdFm.setVisible(true);
            fldFrecueMdFm.setVisible(true);
            fldDiasVisitMd.setVisible(true);
            fldHorarioMd.setVisible(true);
            fldFormatoMd.setVisible(true);

            fldPersonContacFmInst.setVisible(false);
            fldCadenaFm.setVisible(false);
            fldDrogeriaFm.setVisible(false);
            fldFacultadInst.setVisible(false);
        }
        else if (clase == Farmacia.class) {
            clientesLabel.setText("Farmacias");

            fldPersonContacFmInst.setVisible(true);
            fldEmailMdFm.setVisible(true);
            fldFrecueMdFm.setVisible(true);
            fldCadenaFm.setVisible(true);
            fldDrogeriaFm.setVisible(true);

            fldFormatoMd.setVisible(false);
            fldEspecMd.setVisible(false);
            fldDiasVisitMd.setVisible(false);
            fldHorarioMd.setVisible(false);
            fldFacultadInst.setVisible(false);
        }
        else if (clase == Institucion.class) {
            clientesLabel.setText("Instituciones");

            fldPersonContacFmInst.setVisible(true);
            fldFacultadInst.setVisible(true);

            fldFormatoMd.setVisible(false);
            fldEspecMd.setVisible(false);
            fldEmailMdFm.setVisible(false);
            fldFrecueMdFm.setVisible(false);
            fldDiasVisitMd.setVisible(false);
            fldHorarioMd.setVisible(false);
            fldCadenaFm.setVisible(false);
            fldDrogeriaFm.setVisible(false);
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

        opciones = FXCollections.observableArrayList("0212", "0412", "0414", "0416", "0424", "0426");
        chnumber.setItems(opciones);
        chnumber.setValue("0212");

        ObservableList<Character> opciones2 = FXCollections.observableArrayList('V', 'J');
        chTypeCedula.setItems(opciones2);
        chTypeCedula.setValue('V');

        taObservaciones.textProperty().addListener(((observable, oldValue, newValue) -> {
            clienteActual.setDescripcion(newValue);
        }));

        btAtras.setVisible(false);
        infoCliente.visibleProperty().addListener((observable, oldValue, newValue) -> {
            btAtras.setVisible(newValue);
            if (newValue) {
                tfBuscarEvento.clear();
                tbEventos.getSelectionModel().clearSelection();
            } else {
                tablaClientes.refresh();
                tablaClientes.getSelectionModel().clearSelection();
            }
        });
    }
}
